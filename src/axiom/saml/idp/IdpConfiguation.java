package axiom.saml.idp;

import java.io.File;

import org.apache.log4j.Logger;
import org.opensaml.xml.signature.SignatureConstants;


/**
 * Represents the configuration used for initializing factories.
 * In general, all configuration should be completed in this class
 * first before getting factories; however, factory configurations
 * can be get/set directly after instantiation.
 * @author rbrainard
 *
 */
public class IdpConfiguation {
	private static Logger logger = Logger.getLogger(IdpConfiguation.class);

	private int    assertionExpiresInSeconds = 60;
	private String audience = "https://saml.salesforce.com";
	private String saml1AuthenticationMethod = "urn:oasis:names:tc:SAML:1.0:am:unspecified";
	private String saml1subjectConfirmationMethod = "urn:oasis:names:tc:SAML:1.0:cm:bearer";
	private String saml2subjectConfirmationMethod = "urn:oasis:names:tc:SAML:2.0:cm:bearer";
	private String saml2nameIdFormat = org.opensaml.saml2.core.NameIDType.UNSPECIFIED;
	private String saml2authnContextClassRef = org.opensaml.saml2.core.AuthnContext.UNSPECIFIED_AUTHN_CTX;
	
	private SamlVersion samlVersion;
	private SamlUserIdLocation samlUserIdLocation;
	
	private String issuer;
	private String recipient;
	private String userId;
	private String attributeName;
	private String attributeUri;
	
	private String ssoStartPage;
	private String startURL;
	private String logoutURL;
	
	private File keystoreFile;
	private char[] keystorePassword;
	private String keystoreAlias;
	private char[] keystoreAliasPassword;
	private CredentialType credentialType = CredentialType.X509_CREDENTIAL;
	private String canonicalizationAlgorithm = SignatureConstants.ALGO_ID_C14N_EXCL_OMIT_COMMENTS;
	private boolean includeKeyInfoInSignature = true;
	
	/**
	 * Generates an Response factory with a nested Assertion factory
	 * with the configuration set in this object.
	 * @return
	 * @throws IllegalStateException
	 */
	public AbstractResponseFactory getResponseFactory() throws IllegalStateException{
		AbstractResponseFactory responseFactory = null;
		AbstractAssertionFactory assertionFactory = null;

		switch (samlVersion) {
			case _1_1:
				logger.debug("Initializing Saml1ResponseFactory");
				responseFactory = new Saml1ResponseFactory();
				switch (samlUserIdLocation) {
					case SUBJECT:
						logger.debug("Initializing Saml1AssertionUserIdInSubjectFactory");
						assertionFactory = new Saml1AssertionUserIdInSubjectFactory();
						break;
					case ATTRIBUTE:
						logger.debug("Initializing Saml1AssertionUserIdInAttributeFactory");
						assertionFactory = new Saml1AssertionUserIdInAttributeFactory();
							((Saml1AssertionUserIdInAttributeFactory)assertionFactory).setAttributeName(attributeName);
							((Saml1AssertionUserIdInAttributeFactory)assertionFactory).setAttributeUri(attributeUri);
						break;
					default:
						throw new IllegalStateException("Invalid samlUserIdLocation");
				}
				
				logger.debug("Setting SAML 1 abstract assertion configurations");
				((Saml1AbstractAssertionFactory)assertionFactory).setSubjectConfirmationMethod(saml1subjectConfirmationMethod);
				((Saml1AbstractAssertionFactory)assertionFactory).setAuthenticationMethod(saml1AuthenticationMethod);
				break;
	
			case _2_0:
				logger.debug("Initializing Saml2ResponseFactory");
				responseFactory = new Saml2ResponseFactory();
					((Saml2ResponseFactory)responseFactory).setIssuerValue(issuer);
				switch (samlUserIdLocation) {
					case SUBJECT:
						logger.debug("Initializing Saml2AssertionUserIdInSubjectFactory and loading into Saml2ResponseFactory");
						assertionFactory = new Saml2AssertionUserIdInSubjectFactory();
						break;
					case ATTRIBUTE:
						logger.debug("Initializing Saml2AssertionUserIdInAttributeFactory and loading into Saml2ResponseFactory");
						assertionFactory = new Saml2AssertionUserIdInAttributeFactory();
							((Saml2AssertionUserIdInAttributeFactory)assertionFactory).setAttributeName(attributeName);
							((Saml2AssertionUserIdInAttributeFactory)assertionFactory).setAttributeUri(attributeUri);
						break;
					default:
						throw new IllegalStateException("Invalid samlUserIdLocation");
				}
				
				logger.debug("Setting SAML 2 abstract assertion configurations");
				((Saml2AbstractAssertionFactory)assertionFactory).setSubjectConfirmationMethod(saml2subjectConfirmationMethod);
				((Saml2AbstractAssertionFactory)assertionFactory).setAuthnContextClassRefValue(saml2authnContextClassRef);
				((Saml2AbstractAssertionFactory)assertionFactory).setNameIdFormat(saml2nameIdFormat);
				((Saml2AbstractAssertionFactory)assertionFactory).setRecipient(recipient);
				((Saml2AbstractAssertionFactory)assertionFactory).setSsoStartPage(ssoStartPage);
				((Saml2AbstractAssertionFactory)assertionFactory).setLogoutURL(logoutURL);
				break;
				
			default:
				throw new IllegalStateException("Invalid samlVersion");
		}
		
		logger.debug("Setting abstract assertion configurations");
		assertionFactory.setAudience(audience);
		assertionFactory.setExpiresInSeconds(assertionExpiresInSeconds);
		assertionFactory.setIssuer(issuer);
		assertionFactory.setUserId(userId);
		
		logger.debug("Setting recipient on Response as " + recipient);
		responseFactory.setRecipient(recipient);
		
		logger.debug("Enbedding AssertionFactory in ResponseFactory");
		responseFactory.setAssertionFactory(assertionFactory);
		
		logger.debug("Returning responseFactory");
		return responseFactory;
	}
	
	/**
	 * Generates an XmlObjectSigner
	 * with the configuration set in this object.
	 * @return
	 */
	public XmlObjectSigner getXmlObjectSigner() throws IllegalStateException{
		if(keystoreFile          != null &&
		   keystorePassword      != null && 
		   keystoreAlias         != null &&
		   keystoreAliasPassword != null &&
		   credentialType		 != null){
				logger.debug("Initializing new XmlObjectSigner with keystore configuations.");
				XmlObjectSigner xmlObjectSigner = new XmlObjectSigner(keystoreFile,keystorePassword,keystoreAlias,keystoreAliasPassword,credentialType);
					xmlObjectSigner.setCanonicalizationAlgorithm(canonicalizationAlgorithm);
					xmlObjectSigner.setIncludeKeyInfoInSignature(includeKeyInfoInSignature);
				return xmlObjectSigner;
		} else {
			throw new IllegalStateException("keystoreFile,keystorePassword,keystoreAlias,keystoreAliasPassword must be not be null.");
		}
	}

	public int getAssertionExpiresInSeconds() {
		return assertionExpiresInSeconds;
	}

	public void setAssertionExpiresInSeconds(int assertionExpiresInSeconds) {
		this.assertionExpiresInSeconds = assertionExpiresInSeconds;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getSaml1AuthenticationMethod() {
		return saml1AuthenticationMethod;
	}

	public void setSaml1AuthenticationMethod(String saml1AuthenticationMethod) {
		this.saml1AuthenticationMethod = saml1AuthenticationMethod;
	}

	public String getSaml1subjectConfirmationMethod() {
		return saml1subjectConfirmationMethod;
	}

	public void setSaml1subjectConfirmationMethod(
			String saml1subjectConfirmationMethod) {
		this.saml1subjectConfirmationMethod = saml1subjectConfirmationMethod;
	}

	public String getSaml2SubjectConfirmationMethod() {
		return saml2subjectConfirmationMethod;
	}

	public void setSaml2subjectConfirmationMethod(
			String saml2subjectConfirmationMethod) {
		this.saml2subjectConfirmationMethod = saml2subjectConfirmationMethod;
	}

	public String getSaml2NameIdFormat() {
		return saml2nameIdFormat;
	}

	public void setSaml2nameIdFormat(String saml2nameIdFormat) {
		this.saml2nameIdFormat = saml2nameIdFormat;
	}

	public String getSaml2AuthnContextClassRef() {
		return saml2authnContextClassRef;
	}

	public void setSaml2authnContextClassRef(String saml2authnContextClassRef) {
		this.saml2authnContextClassRef = saml2authnContextClassRef;
	}

	public char[] getKeystorePassword() {
		return keystorePassword;
	}

	public char[] getKeystoreAliasPassword() {
		return keystoreAliasPassword;
	}

	public SamlVersion getSamlVersion() {
		return samlVersion;
	}

	public void setSamlVersion(SamlVersion samlVersion) {
		this.samlVersion = samlVersion;
	}

	public SamlUserIdLocation getSamlUserIdLocation() {
		return samlUserIdLocation;
	}

	public void setSamlUserIdLocation(SamlUserIdLocation samlUserIdLocation) {
		this.samlUserIdLocation = samlUserIdLocation;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeUri() {
		return attributeUri;
	}

	public void setAttributeUri(String attributeUri) {
		this.attributeUri = attributeUri;
	}

	public String getSsoStartPage() {
		return ssoStartPage;
	}

	public void setSsoStartPage(String ssoStartPage) {
		this.ssoStartPage = ssoStartPage;
	}

	public String getLogoutURL() {
		return logoutURL;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}	
	
	public File getKeystoreFile() {
		return keystoreFile;
	}

	public void setKeystoreFile(File keystoreFile) {
		this.keystoreFile = keystoreFile;
	}

	public String getKeystoreAlias() {
		return keystoreAlias;
	}

	public void setKeystoreAlias(String keystoreAlias) {
		this.keystoreAlias = keystoreAlias;
	}

	public void setKeystorePassword(char[] keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	public void setKeystoreAliasPassword(char[] keystoreAliasPassword) {
		this.keystoreAliasPassword = keystoreAliasPassword;
	}

	public CredentialType getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(CredentialType credentialType) {
		this.credentialType = credentialType;
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

	public String getStartURL() {
		return startURL;
	}

	public void setStartURL(String startURL) {
		this.startURL = startURL;
	}
}
