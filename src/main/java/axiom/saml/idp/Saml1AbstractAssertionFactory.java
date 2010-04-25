package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.common.SAMLVersion;
import org.opensaml.saml1.core.Assertion;
import org.opensaml.saml1.core.AttributeStatement;
import org.opensaml.saml1.core.Audience;
import org.opensaml.saml1.core.AudienceRestrictionCondition;
import org.opensaml.saml1.core.AuthenticationStatement;
import org.opensaml.saml1.core.Conditions;
import org.opensaml.saml1.core.ConfirmationMethod;
import org.opensaml.saml1.core.NameIdentifier;
import org.opensaml.saml1.core.Subject;
import org.opensaml.saml1.core.SubjectConfirmation;


/**
 * Subclasses extend this class to build SAML 1.1 Assertions. 
 * @author rbrainard
 *
 */
public abstract class Saml1AbstractAssertionFactory extends AbstractAssertionFactory {

	private static Logger logger = Logger.getLogger(Saml1AbstractAssertionFactory.class);

	private String authenticationMethod;
	
	/**
	 * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
	 */	
	protected Saml1AbstractAssertionFactory(){}
	
	/**
	 * Template method for building assertions. Cannot be overridden,
	 * but the hooks in the template must be overridden.
	 */
	@Override
    public final Assertion buildAssertion() throws IllegalStateException{		
		logger.debug("Building AuthenticationStatement");
		AuthenticationStatement authStatement = ((SAMLObjectBuilder<AuthenticationStatement>) builderFactory.getBuilder(AuthenticationStatement.DEFAULT_ELEMENT_NAME)).buildObject();
			authStatement.setSubject(buildSubject());
			authStatement.setAuthenticationMethod(authenticationMethod);
			authStatement.setAuthenticationInstant(new DateTime());

		logger.debug("Building Audience");
		Audience audience = ((SAMLObjectBuilder<Audience>) builderFactory.getBuilder(Audience.DEFAULT_ELEMENT_NAME)).buildObject();;
			audience.setUri(getAudience());

		logger.debug("Building AudienceRestrictionCondition");
		AudienceRestrictionCondition audienceRestrictionCondition = ((SAMLObjectBuilder<AudienceRestrictionCondition>) builderFactory.getBuilder(AudienceRestrictionCondition.DEFAULT_ELEMENT_NAME)).buildObject();
			audienceRestrictionCondition.getAudiences().add(audience);
		
		logger.debug("Building Conditions");
		Conditions conditions = ((SAMLObjectBuilder<Conditions>) builderFactory.getBuilder(Conditions.DEFAULT_ELEMENT_NAME)).buildObject();
			conditions.setNotBefore(new DateTime());
			conditions.setNotOnOrAfter(new DateTime().plusSeconds(getExpiresInSeconds()));
			conditions.getAudienceRestrictionConditions().add(audienceRestrictionCondition);
			
		logger.debug("Building Assertion");
		Assertion assertion = ((SAMLObjectBuilder<Assertion>) builderFactory.getBuilder(Assertion.DEFAULT_ELEMENT_NAME)).buildObject();
			if(getIssuer() != null){
				assertion.setIssuer(getIssuer());
			} else {
				throw new IllegalStateException("Issuer must not be null");
			}
			assertion.setIssueInstant(new DateTime());
			assertion.setVersion(SAMLVersion.VERSION_11);
			assertion.setID(IdGenerator.generateId());
			assertion.getAuthenticationStatements().add(authStatement);
			assertion.setConditions(conditions);
			
			AttributeStatement attributeStatement = buildAttributeStatement();
			if(attributeStatement != null){
				assertion.getAttributeStatements().add(attributeStatement);
			} else {
				//do nothing
			}
		
		logger.info("Returning complete assertion with Id: " + assertion.getID() + " for UserId: " + getUserId());
		return assertion;
	}
	
	/**
	 * Base Subject building method that takes nameId as an argument.
	 * Subclasses call this method from the overridden no-arg buildSubject
	 * and provide their specialized nameId.
	 * @param nameIdentifierValue
	 */
	protected Subject buildSubject(String nameIdentifierValue) throws IllegalStateException{
		logger.debug("Building NameIdentifier");
		NameIdentifier nameId = ((SAMLObjectBuilder<NameIdentifier>) builderFactory.getBuilder(NameIdentifier.DEFAULT_ELEMENT_NAME)).buildObject();
			if(nameIdentifierValue != null){
				nameId.setNameIdentifier(nameIdentifierValue);
			} else {
				throw new IllegalStateException("nameIdentifierValue must not be null");
			}
			logger.debug("NameIdentifier set to " + nameIdentifierValue);

		logger.debug("Building ConfirmationMethod");			
		ConfirmationMethod confirmationMethod = ((SAMLObjectBuilder<ConfirmationMethod>) builderFactory.getBuilder(ConfirmationMethod.DEFAULT_ELEMENT_NAME)).buildObject();
			confirmationMethod.setConfirmationMethod(getSubjectConfirmationMethod());
		
		logger.debug("Building SubjectConfirmation");	
		SubjectConfirmation subjectConfirmation = ((SAMLObjectBuilder<SubjectConfirmation>) builderFactory.getBuilder(SubjectConfirmation.DEFAULT_ELEMENT_NAME)).buildObject();
			subjectConfirmation.getConfirmationMethods().add(confirmationMethod);
		
		logger.debug("Building Subject");
		Subject subject = ((SAMLObjectBuilder<Subject>) builderFactory.getBuilder(Subject.DEFAULT_ELEMENT_NAME)).buildObject();
			subject.setNameIdentifier(nameId);
			subject.setSubjectConfirmation(subjectConfirmation);
		
		return subject;
	}

	/**
	 * Hook to be overridden by subclasses to provide Subject in the
	 * template, which then call back to buildSubject(String NameIdentifierValue)
	 * with their specific value for the name id
	 */
	protected abstract Subject buildSubject();
	
	/**
	 * Hook to be overridden by subclasses to provide 
	 * AttributeStatement, if one needs to exist.
	 */	
	protected abstract AttributeStatement buildAttributeStatement();
	
	public String getAuthenticationMethod() {
		return authenticationMethod;
	}

	public void setAuthenticationMethod(String authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}
	
}
