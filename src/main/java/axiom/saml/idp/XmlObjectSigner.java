package axiom.saml.idp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.log4j.Logger;
import org.opensaml.Configuration;
import org.opensaml.xml.io.Marshaller;
import org.opensaml.xml.io.MarshallerFactory;
import org.opensaml.xml.io.MarshallingException;
import org.opensaml.xml.security.SecurityException;
import org.opensaml.xml.security.credential.BasicCredential;
import org.opensaml.xml.security.credential.Credential;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorFactory;
import org.opensaml.xml.security.keyinfo.KeyInfoGeneratorManager;
import org.opensaml.xml.security.x509.BasicX509Credential;
import org.opensaml.xml.signature.KeyInfo;
import org.opensaml.xml.signature.SignableXMLObject;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureConstants;
import org.opensaml.xml.signature.SignatureException;
import org.opensaml.xml.signature.Signer;

/**
 * Signs XML Objects using config set in constructor. Also offers convenince method 
 * for getting public certificate file.
 * @author rbrainard
 *
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
	 * @param keystoreFile
	 * @param keystorePassword
	 * @param keystoreAlias
	 * @param keystoreAliasPassword
	 * @param credentialType
	 */
	protected XmlObjectSigner(File keystoreFile, char[] keystorePassword, String keystoreAlias, char[] keystoreAliasPassword, CredentialType credentialType) {
		logger.debug("Initializing keystore");
		FileInputStream keystoreStream = null;
		try {
			KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
			keystoreStream = new java.io.FileInputStream(keystoreFile);
			keystore.load(keystoreStream, keystorePassword);
			
			logger.debug("Loading X509 certificate from keystore");
			x509cert = (X509Certificate)keystore.getCertificate(keystoreAlias);
			
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
			logger.error(e);
		} catch (KeyStoreException e) {
			logger.error(e);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e);
		} catch (CertificateException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (NullPointerException e) {
			logger.error("Could not load keystore. Check alias names." + e);
		} finally {
			try {
				logger.debug("Closing keystore");
				if(keystoreStream != null) keystoreStream.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	public SignableXMLObject sign(SignableXMLObject signableXmlObject) throws SignatureException{
		logger.debug("Building unsigned Signature");
		Signature signature = (Signature)Configuration.getBuilderFactory().getBuilder(Signature.DEFAULT_ELEMENT_NAME).buildObject(Signature.DEFAULT_ELEMENT_NAME);
			signature.setSigningCredential(credential);
			signature.setCanonicalizationAlgorithm(canonicalizationAlgorithm);
			
			if(includeKeyInfoInSignature){
				logger.debug("Building KeyInfo");
				KeyInfoGeneratorManager keyInfoGeneratorManager = Configuration.getGlobalSecurityConfiguration().getKeyInfoGeneratorManager().getDefaultManager();
					KeyInfoGeneratorFactory keyInfoGeneratorFactory = keyInfoGeneratorManager.getFactory(credential);
					KeyInfo keyInfo = null;
					try {
						keyInfo = keyInfoGeneratorFactory.newInstance().generate(credential);
					} catch (SecurityException e1) {
						logger.error(e1);
					}
				signature.setKeyInfo(keyInfo);
			}
			
			logger.debug("Trying to match signature Algorithm to public key algorithm: " + credential.getPublicKey().getAlgorithm());
			if (credential.getPublicKey().getAlgorithm().equalsIgnoreCase("DSA")){
				signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_DSA);
			} else if (credential.getPublicKey().getAlgorithm().equalsIgnoreCase("RSA")){
				signature.setSignatureAlgorithm(SignatureConstants.ALGO_ID_SIGNATURE_RSA);
			} else {
				throw new SignatureException("Unknown public key algorithm. Signature algorithm not set.");
			}
			//TODO: add more algos
			
		signableXmlObject.setSignature(signature);
		
		logger.debug("Marshalling signableXmlObject");		
		MarshallerFactory marshallerFactory = Configuration.getMarshallerFactory();
		Marshaller marshaller = marshallerFactory.getMarshaller(signableXmlObject);
		try {
			marshaller.marshall(signableXmlObject);
		} catch (MarshallingException e) {
			logger.error(e);
		}

		logger.debug("Signing signableXmlObject");		
		Signer.signObject(signature);
		
		logger.debug("Returning signed signableXmlObject");		
		return signableXmlObject;
	}
	
	public Credential getCredential(){
		return credential;
	}
	
	public File getCertFile(){		
		byte[] certBytes = null; 
		File certFile = null;
		
		try {
			logger.debug("Exporting certificate");
			certBytes = x509cert.getEncoded();
		} catch (CertificateEncodingException e) {
			logger.error(e);
		}
		
		try {
			certFile = File.createTempFile("samlIdpCert-",".cer");
			FileOutputStream fos = new FileOutputStream(certFile);
			fos.write(certBytes);
			fos.close();
			logger.info("Certificate written to " + certFile.getCanonicalPath());
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
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
