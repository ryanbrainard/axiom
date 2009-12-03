package axiom.saml.idp;

import org.apache.log4j.Logger;
import org.opensaml.common.SignableSAMLObject;

/**
 * Abstract factory used for building all versions and types of SAML Responses.
 * Contains a nested Assertion factory.
 * @author rbrainard
 *
 */
public abstract class AbstractResponseFactory extends AbstractXMLObjectFactory{
	
	@SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(AbstractResponseFactory.class);
	
	private String recipient;
	private AbstractAssertionFactory assertionFactory;
	
	/**
	 * No-arg constructor. Should only be created from IdpConfiguration or subclasses.
	 */
	protected AbstractResponseFactory(){}
	
	
    public abstract SignableSAMLObject buildResponse();
    
    public String getRecipient() {
		return recipient;
	}

    public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

    public AbstractAssertionFactory getAssertionFactory() {
		return assertionFactory;
	}

    public void setAssertionFactory(AbstractAssertionFactory assertionFactory) {
		this.assertionFactory = assertionFactory;
	}
	
}
