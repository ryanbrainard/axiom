package axiom.delauth.token;

import org.apache.log4j.Logger;

import com.sforce.soap.authentication.Authenticate;
import com.sforce.soap.authentication.AuthenticateResult;

public class TokenAuthenticator {
	private static Logger logger = Logger.getLogger(TokenAuthenticator.class);
	
	private TokenStore tokenStore = new TokenStorePojo();
	
    public AuthenticateResult authenticate(Authenticate parameters) throws java.rmi.RemoteException {
    	
    	boolean authenticated = tokenStore.confirmToken(parameters.getUsername(), parameters.getPassword());
    	logger.info("User " + parameters.getUsername() + " has been authenticated");
    	
    	return new AuthenticateResult(authenticated);
    	
    }

}
