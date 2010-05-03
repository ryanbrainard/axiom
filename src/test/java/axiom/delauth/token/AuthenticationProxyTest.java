package axiom.delauth.token;


import axiom.delauth.token.AuthenticationProxy.MessageContextProvider;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.SforceServiceLocator;
import com.sforce.soap.enterprise.SoapBindingStub;
import com.sforce.soap.enterprise.fault.LoginFault;
import junit.framework.TestCase;
import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.mockito.Mockito.*;

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

		AuthenticationProxy proxy = new AuthenticationProxy(badAuthenticator, null, new SforceServiceLocator());

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
				} catch (AxisFault e) {
					//ignore. we're mocking, but can't throw anything else.
				}
				return messageContext;
			}
		};

        final SoapBindingStub soapBindingStub = mock(SoapBindingStub.class);
        final LoginResult loginResult = new LoginResult();
        loginResult.setSessionId("something unique");
        when(soapBindingStub.login(anyString(), anyString())).thenReturn(loginResult);
        
        final SforceServiceLocator sforceServiceLocator = spy(new SforceServiceLocator());
        when(sforceServiceLocator.getSoap()).thenReturn(soapBindingStub);

        AuthenticationProxy proxy = new AuthenticationProxy(authenticator, messageContextProvider, sforceServiceLocator);

        assertEquals(loginResult, proxy.login("username", "password"));
	}
}