package axiom.delauth.token;


import junit.framework.TestCase;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;
import org.apache.log4j.Logger;
import org.junit.Test;
import static org.mockito.Mockito.*;

import axiom.delauth.token.AuthenticationProxy.MessageContextProvider;

import com.sforce.soap.enterprise.SforceServiceLocator;
import com.sforce.soap.enterprise.fault.LoginFault;

public class AuthenticationProxyTest extends TestCase {

	private static Logger logger = Logger.getLogger(AuthenticationProxyTest.class);

	@Test
	public void testBadLoginThrowsLoginFault() throws Exception {
		Authenticator badAuthenticator = new Authenticator() {
			@Override
			public boolean authenticate(String username, String password) {
				return false;
			}
		};
		AuthenticationProxy proxy = new AuthenticationProxy(badAuthenticator, null);
		try {
			proxy.login("username", "password");
			fail();
		} catch (LoginFault expectedException) {
			// expected.
		}
	}
	
	
	@Test
	public void testLogin() throws Exception {
		Authenticator authenticator = new Authenticator() {
			@Override
			public boolean authenticate(String username, String password) {
				return true;
			}
		};
		
		MessageContextProvider messageContextProvider = new MessageContextProvider(){
			@Override
			public MessageContext getMessageContext() {
				MessageContext messageContext = mock(MessageContext.class);
				try{
					Message message = mock(Message.class);
					when(messageContext.getRequestMessage()).thenReturn(message);
					SOAPEnvelope envelope = mock(SOAPEnvelope.class);
					when(message.getSOAPEnvelope()).thenReturn(envelope);
					//SOAPHeaderElement callOptionsElement = mock(SOAPHeaderElement.class);
					//when(envelope.getHeaderByName(new SforceServiceLocator().getServiceName().getNamespaceURI(), "CallOptions")).thenReturn(callOptionsElement);
				} catch (AxisFault e) {
					//ignore. we're mocking!
				}
				return messageContext;
			}
		};
		
		AuthenticationProxy proxy = new AuthenticationProxy(authenticator, messageContextProvider);
		try {
			proxy.login("username", "password");
			fail();
		} catch (LoginFault expectedException) {
			// expected.
		}
	}
}