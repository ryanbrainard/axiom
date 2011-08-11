package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.common.SignableSAMLObject;

public class IdentityProvider {
	private static Logger logger = Logger.getLogger(IdentityProvider.class);

	private final IdpConfiguration config;
	
	public IdentityProvider(IdpConfiguration config){
		logger.debug("Creating IdentityProvider based on IdpConfiguration");
		this.config = config;
	}
	
	public SignableSAMLObject generateSamlResponse(){
		logger.debug("Building unsigned SamlResponse");
		final SignableSAMLObject response = config.getResponseFactory().buildResponse();

		logger.debug("Signing and returning SamlResponse");
		return (SignableSAMLObject) config.getXmlObjectSigner().sign(response);
	}
}