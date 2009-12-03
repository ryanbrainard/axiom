package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.common.SignableSAMLObject;
import org.opensaml.xml.signature.SignatureException;


public class IdentityProvider {
	private static Logger logger = Logger.getLogger(IdentityProvider.class);

	IdpConfiguation config;
	
	public IdentityProvider(IdpConfiguation config){
		logger.debug("Creating IdentityProvider based on IdpConfiguation");
		this.config = config;
	}
	
	public String generateSamlResponse(){
		logger.debug("Generating SamlResponse");

		SignableSAMLObject response = null;

		logger.debug("Building unsigned SamlResponse");
		response = config.getResponseFactory().buildResponse();
		
		logger.debug("Signing SamlResponse");
		try {
			response = (SignableSAMLObject) config.getXmlObjectSigner().sign(response);
		} catch (SignatureException e) {
			logger.error(e);
		}
				
		String responseString = XmlObjectSerializer.xmlObjectToString(response);

		logger.debug("Returning SamlResponse");
		logger.trace("SamlResponse String:\n" + responseString);
		return responseString;
	}
	
}
