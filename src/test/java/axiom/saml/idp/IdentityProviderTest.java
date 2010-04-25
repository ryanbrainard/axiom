package axiom.saml.idp;

import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import junit.framework.TestCase;

import org.opensaml.common.SignableSAMLObject;
import org.opensaml.saml1.core.AuthenticationStatement;
import org.opensaml.xml.XMLObject;
import org.opensaml.xml.schema.XSString;
import org.opensaml.xml.signature.Signature;
import org.opensaml.xml.signature.SignatureValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentityProviderTest extends TestCase {
		
	public void testConfigSaml1NameIdInSubject() throws Exception {
		final IdpConfiguation config = getDefaultIdpConfiguation();
		
		config.setSamlVersion(SamlVersion._1_1);
		config.setSamlUserIdLocation(SamlUserIdLocation.SUBJECT);
		
		assertValidSamlResponse(config, generateSamlResponse(config));
	}
	
	public void testConfigSaml1NameIdInAttribute() throws Exception {
		final IdpConfiguation config = getDefaultIdpConfiguation();
		
		config.setSamlVersion(SamlVersion._1_1);
		config.setSamlUserIdLocation(SamlUserIdLocation.ATTRIBUTE);
		config.setAttributeName("testName");
		config.setAttributeUri("testUri");
		
		assertValidSamlResponse(config, generateSamlResponse(config));
	}	
	
	public void testConfigSaml2NameIdInSubject() throws Exception {
		final IdpConfiguation config = getDefaultIdpConfiguation();
		
		config.setSamlVersion(SamlVersion._2_0);
		config.setSamlUserIdLocation(SamlUserIdLocation.SUBJECT);
		
		assertValidSamlResponse(config, generateSamlResponse(config));
	}
	
	public void testConfigSaml2NameIdInAttribute() throws Exception {
		final IdpConfiguation config = getDefaultIdpConfiguation();
		
		config.setSamlVersion(SamlVersion._2_0);
		config.setSamlUserIdLocation(SamlUserIdLocation.ATTRIBUTE);
		config.setAttributeName("testName");
		config.setAttributeUri("testUri");
		config.setRecipient("https://login-blitz03.soma.salesforce.com/?saml=02HKiPoin4IMxEBzAk2_F4dhH8hdqXG99mphisns1p1JlhrOilbDjFOck2vrkSTjharCU.d0czKy4g4g==");;
		
		assertValidSamlResponse(config, generateSamlResponse(config));
	}	
	
	private IdpConfiguation getDefaultIdpConfiguation(){
		IdpConfiguation config = new IdpConfiguation();
		
		config.setKeystoreFile(new File(this.getClass().getResource("AxiomIdpExample.keystore").getFile()));
		assertTrue(config.getKeystoreFile().exists());
		config.setKeystoreAlias("axiom");
		config.setKeystorePassword("123456".toCharArray());
		config.setKeystoreAliasPassword("123456".toCharArray());
		
		config.setIssuer("tester");
		
		config.setRecipient("https://login-blitz03.soma.salesforce.com");
		config.setUserId("admin.na1-blitz03.rbrainard@salesforce.com");
		
		return config;
	}
	
	private  SignableSAMLObject generateSamlResponse(IdpConfiguation config){
		IdentityProvider idp = new IdentityProvider(config);
		return idp.generateSamlResponse(); 
	}
	
	private void assertValidSamlResponse(final IdpConfiguation config, final SignableSAMLObject response) throws Exception{
		final Signature signature;
		
		//Parse and assert differently based on SAML version.
		switch (config.getSamlVersion()) {
			case _1_1:
				//Response-level assertions
				final org.opensaml.saml1.core.Response saml1Response = (org.opensaml.saml1.core.Response) response;
				signature = saml1Response.getSignature(); //this gets asserted below in a separate thread
				assertEquals(config.getRecipient(), saml1Response.getRecipient());
				
				//Assertion-level assertions
				assertEquals(1, saml1Response.getAssertions().size());
				final org.opensaml.saml1.core.Assertion saml12Assertion = saml1Response.getAssertions().get(0);
				
				assertEquals(config.getIssuer(), saml12Assertion.getIssuer());
				
				final org.opensaml.saml1.core.Conditions saml1Conditions = saml12Assertion.getConditions();
				assertEquals(1, saml1Conditions.getAudienceRestrictionConditions().size());
				assertEquals(1, saml1Conditions.getAudienceRestrictionConditions().get(0).getAudiences().size());
				assertEquals(config.getAudience(), saml1Conditions.getAudienceRestrictionConditions().get(0).getAudiences().get(0).getUri());
				assertTrue(saml1Conditions.getNotBefore().isBeforeNow());
				assertEquals(config.getAssertionExpiresInSeconds(), saml1Conditions.getNotOnOrAfter().minus(saml1Conditions.getNotBefore().getMillis()).getSecondOfDay());
				
				assertEquals(1,	saml12Assertion.getAuthenticationStatements().size());
				final AuthenticationStatement saml1AuthenticationStatement = saml12Assertion.getAuthenticationStatements().get(0);
				assertEquals(config.getSaml1AuthenticationMethod(), saml1AuthenticationStatement.getAuthenticationMethod());		
				assertTrue(saml1AuthenticationStatement.getAuthenticationInstant().isBeforeNow());		
				
				//Parse and assert differently based on user Id location version.
				switch (config.getSamlUserIdLocation()) {
					case SUBJECT:
						assertEquals(1,	saml12Assertion.getAuthenticationStatements().size());
						assertEquals(config.getUserId(), saml1AuthenticationStatement.getSubject().getNameIdentifier().getNameIdentifier());		
						break;
					case ATTRIBUTE:
						assertNotSame(config.getUserId(), saml1AuthenticationStatement.getSubject().getNameIdentifier().getNameIdentifier());		
						final List<org.opensaml.saml1.core.AttributeStatement> saml1AttribtuteStatements = saml12Assertion.getAttributeStatements();
						assertEquals(1, saml1AttribtuteStatements.size());
						assertEquals(1, saml1AttribtuteStatements.get(0).getAttributes().size());
						final org.opensaml.saml1.core.Attribute saml1Attribute = saml1AttribtuteStatements.get(0).getAttributes().get(0);
						assertEquals(config.getAttributeName(), saml1Attribute.getAttributeName());
						assertEquals(config.getAttributeUri(), saml1Attribute.getAttributeNamespace());
						List<XMLObject> saml2AttribtuteValues = saml1Attribute.getAttributeValues();
						assertEquals(1, saml2AttribtuteValues.size());
						assertEquals(config.getUserId(), ((XSString) saml2AttribtuteValues.get(0)).getValue());
						break;
					default:
						fail("Invalid user id location");
						break;
				}
				
				break;
			case _2_0:
				//Response-level assertions
				final org.opensaml.saml2.core.Response saml2Response = (org.opensaml.saml2.core.Response) response;
				signature = saml2Response.getSignature(); //this gets asserted below in a separate thread
				assertEquals(config.getRecipient(), saml2Response.getDestination());
				assertEquals(config.getIssuer(), saml2Response.getIssuer().getValue());
				
				//Assertion-level assertions
				assertEquals(1, saml2Response.getAssertions().size());
				final org.opensaml.saml2.core.Assertion saml2Assertion = saml2Response.getAssertions().get(0);
				
				final org.opensaml.saml2.core.Conditions saml2Conditions = saml2Assertion.getConditions();
				assertEquals(1, saml2Conditions.getAudienceRestrictions().size());
				assertEquals(1, saml2Conditions.getAudienceRestrictions().get(0).getAudiences().size());
				assertEquals(config.getAudience(), saml2Conditions.getAudienceRestrictions().get(0).getAudiences().get(0).getAudienceURI());
				assertTrue(saml2Conditions.getNotBefore().isBeforeNow());
				assertEquals(config.getAssertionExpiresInSeconds(), saml2Conditions.getNotOnOrAfter().minus(saml2Conditions.getNotBefore().getMillis()).getSecondOfDay());
				
				assertEquals(1,	saml2Assertion.getAuthnStatements().size());
				final org.opensaml.saml2.core.AuthnStatement saml2AuthenticationStatement = saml2Assertion.getAuthnStatements().get(0);
				assertEquals(config.getSaml2AuthnContextClassRef(), saml2AuthenticationStatement.getAuthnContext().getAuthnContextClassRef().getAuthnContextClassRef());		
				assertTrue(saml2AuthenticationStatement.getAuthnInstant().isBeforeNow());		
				
				assertEquals(1, saml2Assertion.getSubject().getSubjectConfirmations().size());				
				assertEquals(config.getSaml2SubjectConfirmationMethod(), saml2Assertion.getSubject().getSubjectConfirmations().get(0).getMethod());				
				assertEquals(config.getRecipient(), saml2Assertion.getSubject().getSubjectConfirmations().get(0).getSubjectConfirmationData().getRecipient());				
				
				assertEquals(config.getSaml2NameIdFormat(), saml2Assertion.getSubject().getNameID().getFormat());
				
				//Parse and assert differently based on user Id location version.
				switch (config.getSamlUserIdLocation()) {
					case SUBJECT:
						assertEquals(config.getUserId(), saml2Assertion.getSubject().getNameID().getValue());				
						break;
					case ATTRIBUTE:
						final List<org.opensaml.saml2.core.AttributeStatement> saml2AttribtuteStatements = saml2Assertion.getAttributeStatements();
						assertEquals(1, saml2AttribtuteStatements.size());
						assertEquals(1, saml2AttribtuteStatements.get(0).getAttributes().size());
						final org.opensaml.saml2.core.Attribute saml2Attribute = saml2AttribtuteStatements.get(0).getAttributes().get(0);
						assertEquals(config.getAttributeName(), saml2Attribute.getName());
						assertEquals(config.getAttributeUri(), saml2Attribute.getNameFormat());
						final List<XMLObject> saml2AttribtuteValues = saml2Attribute.getAttributeValues();
						assertEquals(1, saml2AttribtuteValues.size());
						assertEquals(config.getUserId(), ((XSString) saml2AttribtuteValues.get(0)).getValue());
						break;
					default:
						fail("Invalid user id location");
						break;
				}
				
				break;
			default:
				fail("invalid SAML version");
				signature = null;
				break;
		}
		
		//Validate signature for both SAML1 and SAML2. Signature parsed out above in switch statements.
		final XmlObjectSigner signer = new XmlObjectSigner(
														   config.getKeystoreFile(), 
														   config.getKeystorePassword(),
														   config.getKeystoreAlias(), 
														   config.getKeystoreAliasPassword(),
														   config.getCredentialType());
		
		// Crazy workaround for bug in SignatureValidator that doesn't initialize
		// correctly with same key unless in separate threads. Using an AtomicReference to hold 
		// reference to any Exceptions that occur inside executor thread and fail if not null.
		final AtomicReference<Exception> innerThreadExceptions = new AtomicReference<Exception>();
		final ExecutorService signatureValidatorExecutor = Executors.newSingleThreadExecutor();
		signatureValidatorExecutor.execute(new Runnable(){	
			public void run(){
				SignatureValidator signatureValidatorLocal = new SignatureValidator(signer.getCredential());
				try {
					signatureValidatorLocal.validate(signature);
				} catch (Exception e) {
					innerThreadExceptions.set(e);
				}
			}
		});
		
		signatureValidatorExecutor.shutdown();
		signatureValidatorExecutor.awaitTermination(60, TimeUnit.SECONDS);
		
		assertNull("Error validating signature. Review logs.", innerThreadExceptions.get());
	}
	
}
