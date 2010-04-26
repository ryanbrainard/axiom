package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.common.SignableSAMLObject;


public class IdentityProvider {
	private static Logger logger = Logger.getLogger(IdentityProvider.class);

	IdpConfiguation config;
	
	public IdentityProvider(IdpConfiguation config){
		logger.debug("Creating IdentityProvider based on IdpConfiguation");
		this.config = config;
	}
	
	public SignableSAMLObject generateSamlResponse(){
		logger.debug("Generating SamlResponse");

		SignableSAMLObject response = null;

		logger.debug("Building unsigned SamlResponse");
		response = config.getResponseFactory().buildResponse();
		
		logger.debug("Signing and returning SamlResponse");		
		return (SignableSAMLObject) config.getXmlObjectSigner().sign(response);
	}

	
}
