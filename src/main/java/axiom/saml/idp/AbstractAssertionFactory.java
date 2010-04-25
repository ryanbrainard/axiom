package axiom.saml.idp;

import org.opensaml.common.SignableSAMLObject;

/**
 * Abstract factory used for building all versions and types of SAML Assertions.
 * Generally should be nested inside of a Response Factory for creating a complete
 * SAML Response including the Assertion.
 * @author rbrainard
 *
 */
public abstract class AbstractAssertionFactory extends AbstractXMLObjectFactory{
	private String issuer;
	private String userId;
	private String audience;
	private String subjectConfirmationMethod;
	private int expiresInSeconds;

	
	/**
	 * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
	 */
	protected AbstractAssertionFactory(){}

	public abstract SignableSAMLObject buildAssertion();
	
	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getExpiresInSeconds() {
		return expiresInSeconds;
	}

	public void setExpiresInSeconds(int expiresInSeconds) {
		this.expiresInSeconds = expiresInSeconds;
	}
	
	public String getSubjectConfirmationMethod() {
		return subjectConfirmationMethod;
	}

	public void setSubjectConfirmationMethod(String subjectConfirmationMethod) {
		this.subjectConfirmationMethod = subjectConfirmationMethod;
	}

}
