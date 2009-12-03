package axiom.test;

import axiom.saml.idp.*;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.*;



public class TestIdentityProvider {

	private static Logger logger = Logger.getLogger(TestIdentityProvider.class);
	
	@BeforeClass
	public static void initLogging(){
		PropertyConfigurator.configure("WebContent/WEB-INF/config/log4j.properties");
	}
	
	IdpConfiguation config;
	
	@Before
	public void createConfiguration(){
		config = new IdpConfiguation();
		
		config.setKeystoreFile(new File("WebContent/WEB-INF/config/example.keystore"));
		config.setKeystoreAlias("samlIdpExample");
		config.setKeystorePassword("123456".toCharArray());
		config.setKeystoreAliasPassword("123456".toCharArray());
		
		config.setIssuer("tester");

//		config.setRecipient("https://login.salesforce.com");
//		config.setUserId("admin.test.rbrainard@salesforce.com");
		
		config.setRecipient("https://login-blitz03.soma.salesforce.com");
		config.setUserId("admin.na1-blitz03.rbrainard@salesforce.com");
	}

	@Test
	public void testConfigSaml1NameIdInSubject() {
		config.setSamlVersion(SamlVersion._1_1);
		
		config.setSamlUserIdLocation(SamlUserIdLocation.SUBJECT);
	}

	@Test
	public void testConfigSaml1NameIdInAttribute() {
		config.setSamlVersion(SamlVersion._1_1);
		
		config.setSamlUserIdLocation(SamlUserIdLocation.ATTRIBUTE);
		
		config.setAttributeName("testName");
		config.setAttributeUri("testUri");
	}	
	
	@Test
	public void testConfigSaml2NameIdInSubject() {
		config.setSamlVersion(SamlVersion._2_0);
		
		config.setSamlUserIdLocation(SamlUserIdLocation.SUBJECT);
	}

	@Test
	public void testConfigSaml2NameIdInAttribute() {
		config.setSamlVersion(SamlVersion._2_0);
		
		config.setSamlUserIdLocation(SamlUserIdLocation.ATTRIBUTE);
		
		config.setAttributeName("testName");
		config.setAttributeUri("testUri");
		
		config.setRecipient("https://login-blitz03.soma.salesforce.com/?saml=02HKiPoin4IMxEBzAk2_F4dhH8hdqXG99mphisns1p1JlhrOilbDjFOck2vrkSTjharCU.d0czKy4g4g==");
	}	
	
	@After
	public void testGenerateSamlResponse(){
		IdentityProvider idp = new IdentityProvider(config);
		logger.info("\n\n" + idp.generateSamlResponse() + "\n");
	}

}
