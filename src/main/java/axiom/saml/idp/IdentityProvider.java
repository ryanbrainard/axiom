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
	
	public String generateSerializedSamlResponse(){
		SignableSAMLObject response = generateSamlResponse();
				
		String responseString = XmlObjectSerializer.xmlObjectToString(response);
		logger.trace("SamlResponse String:\n" + responseString);
		
		return responseString;
	}

	protected SignableSAMLObject generateSamlResponse() {
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
		
		logger.debug("Returning SamlResponse");		
		return response;
	}

	
}
