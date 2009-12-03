/**
 * AuthenticationProxy.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.delauth.token;

import java.util.Iterator;

import javax.xml.rpc.ServiceException;

import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.*;
import org.apache.log4j.Logger;

import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.fault.*;




public class AuthenticationProxy implements com.sforce.soap.enterprise.Soap{
    
	private static Logger logger = Logger.getLogger(AuthenticationProxy.class);
	
	private Authenticator authenticator = new DemoAuthenticator();
	
	public LoginResult login(String username, String password)  throws java.rmi.RemoteException, InvalidIdFault, UnexpectedErrorFault, LoginFault{
		
		if(authenticator.authenticate(username, password)){
			logger.debug("authenticated by proxy");
			
			logger.debug("generating token...");
			String token = new TokenGenerator().generateToken(username);
			
	    	try {
	    		logger.debug("creating client binding stub");
	    		SforceServiceLocator sforceServiceLocator = new SforceServiceLocator();
	    		SoapBindingStub soapBindingStub = (SoapBindingStub)sforceServiceLocator.getSoap();

	    		logger.debug("Extracting CallOptions header values");	
				MessageContext context = MessageContext.getCurrentContext();
			    Message message = context.getRequestMessage();
			    SOAPEnvelope envelope = message.getSOAPEnvelope();
			    SOAPHeaderElement callOptionsElement = envelope.getHeaderByName(sforceServiceLocator.getServiceName().getNamespaceURI(),"CallOptions");
			    			    
			    if(callOptionsElement != null){
			    	CallOptions callOptionsHeader = new CallOptions();
			    	
				    for(Iterator<MessageElement> i = callOptionsElement.getChildElements(); i.hasNext();){
				    	MessageElement childElement = i.next();
				    	
				    	if(childElement.getName().equalsIgnoreCase("client")){
				    		logger.debug("Setting CallOptions.client=" + childElement.getValue());	
				    		callOptionsHeader.setClient(childElement.getValue());
				    	} else if (childElement.getName().equalsIgnoreCase("remoteApplication")){
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
		
		} else {
			logger.debug("not authenticated by proxy");
			throw new LoginFault(ExceptionCode.INVALID_LOGIN, "Invalid Axiom proxy password");
		}
  	
    	
    }

}
