package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.opensaml.common.SAMLObjectBuilder;
import org.opensaml.saml2.core.*;


/**
 * Factory for building SAML 2.0 Responses
 *
 * @author rbrainard
 */
public class Saml2ResponseFactory extends AbstractResponseFactory {

    private static Logger logger = Logger.getLogger(Saml2ResponseFactory.class);

    private String issuerValue;

    /**
     * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
     */
    protected Saml2ResponseFactory() {
    }


    /**
     * Template method for building SAML 2.0 Responses.
     * Cannot be overridden, but internally called methods
     * may be, such as
     * IdGenerator.generateId(),
     * AssertionFactory.buildAssertion,
     * buildStatus().
     */
    @Override
    final public Response buildResponse() throws IllegalStateException {
        logger.debug("Building Response");
        Response response = ((SAMLObjectBuilder<Response>) builderFactory.getBuilder(Response.DEFAULT_ELEMENT_NAME)).buildObject();
        response.setID(IdGenerator.generateId());

        if (getAssertionFactory() != null) {
            Assertion assertion = (Assertion) getAssertionFactory().buildAssertion();
            response.getAssertions().add(assertion);
        } else {
            throw new IllegalStateException("Assertion Factory must not set before building Response");
        }

        if (getRecipient() != null) {
            response.setDestination(getRecipient());
        } else {
            throw new IllegalStateException("Response must not be null when building Response");
        }

        if (issuerValue != null) {
            response.setIssuer(buildIssuer(issuerValue));
        } else {
            throw new IllegalStateException("IssuerValue must not be null when building Response");
        }

        response.setIssueInstant(new DateTime());
        response.setStatus(buildStatus(StatusCode.SUCCESS_URI));

        logger.info("Returning complete response with Id: " + response.getID() + " for recipient " + getRecipient());
        return response;
    }

    /**
     * Builds Status element for use by Response template and
     * can be overridden.
     *
     * @return
     */
    protected Status buildStatus(String statusCodeValue) {
        logger.debug("Building StatusCode");
        StatusCode statusCode = ((SAMLObjectBuilder<StatusCode>) builderFactory.getBuilder(StatusCode.DEFAULT_ELEMENT_NAME)).buildObject();
        statusCode.setValue(statusCodeValue);
        logger.debug("Set StatusCode to: " + statusCodeValue);

        logger.debug("Building Status");
        Status status = ((SAMLObjectBuilder<Status>) builderFactory.getBuilder(Status.DEFAULT_ELEMENT_NAME)).buildObject();
        status.setStatusCode(statusCode);

        return status;
    }

    /**
     * Builds Issuer element for use by Response template and
     * can be overridden.
     *
     * @return
     */
    protected Issuer buildIssuer(String issuerValue) {
        logger.debug("Building Issuer");
        Issuer issuer = ((SAMLObjectBuilder<Issuer>) builderFactory.getBuilder(Issuer.DEFAULT_ELEMENT_NAME)).buildObject();
        issuer.setValue(issuerValue);

        return issuer;
    }

    public String getIssuerValue() {
        return issuerValue;
    }

    public void setIssuerValue(String issuerValue) {
        this.issuerValue = issuerValue;
    }

}
