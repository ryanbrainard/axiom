package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.xml.io.*;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorFactory;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorManager;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.*;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.Signer;

import java.io.*;
import java.net.URL;
import java.security.*;
import java.security.cert.*;
import java.security.cert.X509Certificate;

/**
 * Signs XML Objects using config set in constructor. Also offers convenince method
 * for getting public certificate file.
 *
 * @author rbrainard
 */
public class XmlObjectSigner {
    private static Logger logger = Logger.getLogger(XmlObjectSigner.class);

    private Credential credential;
    private X509Certificate x509cert;

    private String canonicalizationAlgorithm;
    private boolean includeKeyInfoInSignature;

    /**
     * Constructor for XmlObjectSigner should only be called by IdpConfiguration
     * or subclasses
     *
     * @param keystoreFile
     * @param keystorePassword
     * @param keystoreAlias
     * @param keystoreAliasPassword
     * @param credentialType
     */
    protected XmlObjectSigner(URL keystoreFile, char[] keystorePassword, String keystoreAlias, char[] keystoreAliasPassword, CredentialType credentialType) {
        logger.debug("Initializing keystore");
        InputStream keystoreStream = null;
        try {
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            keystoreStream = keystoreFile.openStream();
            keystore.load(keystoreStream, keystorePassword);

            logger.debug("Loading X509 certificate from keystore");
            x509cert = (X509Certificate) keystore.getCertificate(keystoreAlias);

            logger.debug("Loading credential data from keystore");

            switch (credentialType) {
                case BASIC_CREDENTIAL:
                    BasicCredential basicCredential = new BasicCredential();
                    basicCredential.setPrivateKey((PrivateKey) keystore.getKey(keystoreAlias, keystoreAliasPassword));
                    basicCredential.setPublicKey(keystore.getCertificate(keystoreAlias).getPublicKey());

                    credential = basicCredential;
                    break;

                case X509_CREDENTIAL:
                    BasicX509Credential basicX509Credential = new BasicX509Credential();
                    basicX509Credential.setEntityCertificate(x509cert);
                    basicX509Credential.setPrivateKey((PrivateKey) keystore.getKey(keystoreAlias, keystoreAliasPassword));
                    basicX509Credential.setPublicKey(keystore.getCertificate(keystoreAlias).getPublicKey());

                    credential = basicX509Credential;
                    break;

                default:
                    throw new IllegalStateException("Invalid credentialType");
            }

        } catch (UnrecoverableKeyException e) {
            throw new RuntimeException(e);
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (CertificateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            throw new RuntimeException("Could not load keystore. Check alias names.", e);
        } finally {
            try {
                logger.debug("Closing keystore");
                if (keystoreStream != null) keystoreStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public SignableXMLObject sign(SignableXMLObject signableXmlObject) {
        logger.debug("Building unsigned Signature");
        Signature signature = (Signature) Configuration.getBuilderFactory().getBuilder(Signature.DEFAULT_ELEMENT_NAME).buildObject(Signature.DEFAULT_ELEMENT_NAME);
        signature.setSigningCredential(credential);
        signature.setCanonicalizationAlgorithm(canonicalizationAlgorithm);

        if (includeKeyInfoInSignature) {
            logger.debug("Building KeyInfo");
            KeyInfoGeneratorManager keyInfoGeneratorManager = Configuration.getGlobalSecurityConfiguration().getKeyInfoGeneratorManager().getDefaultManager();
            KeyInfoGeneratorFactory keyInfoGeneratorFactory = keyInfoGeneratorManager.getFactory(credential);
            KeyInfo keyInfo = null;
            try {
                keyInfo = keyInfoGeneratorFactory.newInstance().generate(credential);
            } catch (SecurityException e) {
                throw new RuntimeException(e);
            }
            signature.setKeyInfo(keyInfo);
        }

        logger.debug("Trying to match signature Algorithm to public key algorithm: " + credential.getPublicKey().getAlgorithm());
        if (credential.getPublicKey().getAlgorithm().equalsIgnoreCase("DSA")) {
            signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_DSA);
        } else if (credential.getPublicKey().getAlgorithm().equalsIgnoreCase("RSA")) {
            signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA);
        } else {
            throw new RuntimeException(new SignatureException("Unknown public key algorithm. Signature algorithm not set."));
        }
        //TODO: add more algos

        signableXmlObject.setSignature(signature);

        logger.debug("Marshalling signableXmlObject");
        MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();
        Marshaller marshaller = marshallerFactory.getMarshaller(signableXmlObject);
        try {
            marshaller.marshall(signableXmlObject);
        } catch (MarshallingException e) {
            throw new RuntimeException(e);
        }

        logger.debug("Signing signableXmlObject");
        try {
            Signer.signObject(signature);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        }

        logger.debug("Returning signed signableXmlObject");
        return signableXmlObject;
    }

    public Credential getCredential() {
        return credential;
    }

    public File getCertFile() throws CertificateEncodingException, IOException {
        byte[] certBytes = null;
        File certFile = null;

        logger.debug("Exporting certificate");
        certBytes = x509cert.getEncoded();

        FileOutputStream fos = null;
        try {
            certFile = File.createTempFile("samlIdpCert-", ".cer");
            fos = new FileOutputStream(certFile);
            fos.write(certBytes);
            logger.info("Certificate written to " + certFile.getCanonicalPath());
        } finally {
            if (fos != null) fos.close();
        }

        return certFile;
    }

    public String getCanonicalizationAlgorithm() {
        return canonicalizationAlgorithm;
    }

    public void setCanonicalizationAlgorithm(String canonicalizationAlgorithm) {
        this.canonicalizationAlgorithm = canonicalizationAlgorithm;
    }

    public boolean isIncludeKeyInfoInSignature() {
        return includeKeyInfoInSignature;
    }

    public void setIncludeKeyInfoInSignature(boolean includeKeyInfoInSignature) {
        this.includeKeyInfoInSignature = includeKeyInfoInSignature;
    }

}
