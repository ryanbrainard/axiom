package axiom.delauth.token;

import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.fault.*;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.*;
import org.apache.log4j.Logger;

import javax.xml.rpc.ServiceException;
import java.util.Iterator;


public class AuthenticationProxy implements com.sforce.soap.enterprise.Soap {

    private final static Logger logger = Logger.getLogger(AuthenticationProxy.class);
    private final SforceServiceLocator sforceServiceLocator;

    static interface MessageContextProvider {
        public MessageContext getMessageContext();
    }

    private Authenticator authenticator;
    private MessageContextProvider messageContextProvider;

    public AuthenticationProxy() {
        this(new DemoAuthenticator(),
             new MessageContextProvider() {
                    @Override
                    public MessageContext getMessageContext() {
                        return MessageContext.getCurrentContext();
                    }
             },
             new SforceServiceLocator());
    }

    public AuthenticationProxy(Authenticator authenticator, MessageContextProvider messageContextProvider, SforceServiceLocator sforceServiceLocator) {
        this.authenticator = authenticator;
        this.messageContextProvider = messageContextProvider;
        this.sforceServiceLocator = sforceServiceLocator;
    }

    public LoginResult login(String username, String password) throws java.rmi.RemoteException {

        if (!authenticator.authenticate(username, password)) {
            logger.debug("not authenticated by proxy");
            throw new LoginFault(ExceptionCode.INVALID_LOGIN, "Invalid Axiom proxy password");
        }

        logger.debug("authenticated by proxy");

        logger.debug("generating token...");
        final String token = new TokenGenerator().generateToken(username);

        try {
            logger.debug("creating client binding stub");
            SoapBindingStub soapBindingStub = (SoapBindingStub) sforceServiceLocator.getSoap();

            logger.debug("Extracting CallOptions header values");
            final MessageContext context = messageContextProvider.getMessageContext();
            final Message message = context.getRequestMessage();
            final SOAPEnvelope envelope = message.getSOAPEnvelope();
            final SOAPHeaderElement callOptionsElement = envelope.getHeaderByName(sforceServiceLocator.getServiceName().getNamespaceURI(), "CallOptions");

            if (callOptionsElement != null) {
                final CallOptions callOptionsHeader = new CallOptions();

                for (Iterator<MessageElement> i = callOptionsElement.getChildElements(); i.hasNext();) {
                    final MessageElement childElement = i.next();

                    if (childElement.getName().equalsIgnoreCase("client")) {
                        logger.debug("Setting CallOptions.client=" + childElement.getValue());
                        callOptionsHeader.setClient(childElement.getValue());
                    } else if (childElement.getName().equalsIgnoreCase("remoteApplication")) {
                        logger.debug("Setting CallOptions.remoteApplication=" + childElement.getValue());
                        callOptionsHeader.setRemoteApplication(childElement.getValue());
                    }

                }

                logger.debug("Setting CallOptions header on soapBindingStub");
                soapBindingStub.setHeader(sforceServiceLocator.getServiceName().getNamespaceURI(), "CallOption", callOptionsHeader);
            } else {
                logger.debug("no CallOptions in SOAP header");
            }

            logger.debug("calling out to real SFDC login");
            return soapBindingStub.login(username, token);
        } catch (ServiceException e) {
            logger.error("ServiceException", e);
            throw new UnexpectedErrorFault(ExceptionCode.UNKNOWN_EXCEPTION, "AXIOM PROXY SERVICE EXCEPTION: " + e.getMessage());
        }
    }

}
