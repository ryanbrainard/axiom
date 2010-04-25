package axiom.saml.idp;

import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml1.core.Assertion;
import org.opensaml.saml1.core.Response;
import org.opensaml.saml1.core.Status;
import org.opensaml.saml1.core.StatusCode;


/**
 * Factory for building SAML 1.1 Responses
 * @author rbrainard
 *
 */
public class Saml1ResponseFactory extends AbstractResponseFactory{

	private static Logger logger = Logger.getLogger(Saml1ResponseFactory.class);

	/**
	 * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
	 */
	protected Saml1ResponseFactory(){}

	
	/**
	 * Template method for building SAML 1.1 Responses.
	 * Cannot be overridden, but internally called methods
	 * may be, such as 
	 * IdGenerator.generateId(), 
	 * AssertionFactory.buildAssertion, 
	 * buildStatus().
	 */
	@Override
	final public Response buildResponse() throws IllegalStateException{
		logger.debug("Building Response");
		Response response = ((SAMLObjectBuilder<Response>) builderFactory.getBuilder(Response.DEFAULT_ELEMENT_NAME)).buildObject();			
			response.setID(IdGenerator.generateId());	
			
			if(getAssertionFactory() != null){
				response.getAssertions().add((Assertion)getAssertionFactory().buildAssertion());
			} else {
				throw new IllegalStateException("Assertion Factory must not set before building Response");
			}
			
			if(getRecipient() != null){
				response.setRecipient(getRecipient());				
			} else {
				throw new IllegalStateException("Response must not be null when building Response");
			}
			response.setIssueInstant(new DateTime());
			response.setStatus(buildStatus(StatusCode.SUCCESS));
			
		logger.info("Returning complete response with Id: " + response.getID() + " for recipient " + getRecipient());
		return response;
	}

	/**
	 * Builds Status element for use by Response template and 
	 * can be overridden. 
	 * @return
	 */
	protected Status buildStatus(QName statusCodeValue) {
		logger.debug("Building StatusCode");
		StatusCode statusCode = ((SAMLObjectBuilder<StatusCode>) builderFactory.getBuilder(StatusCode.DEFAULT_ELEMENT_NAME)).buildObject();
			statusCode.setValue(statusCodeValue);
			logger.debug("Set StatusCode to: " + statusCodeValue);
		
		logger.debug("Building Status");
		Status status = ((SAMLObjectBuilder<Status>) builderFactory.getBuilder(Status.DEFAULT_ELEMENT_NAME)).buildObject();
			status.setStatusCode(statusCode);
		
		return status;
	}

	
}
