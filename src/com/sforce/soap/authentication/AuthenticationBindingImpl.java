package com.sforce.soap.authentication;

import axiom.delauth.token.TokenAuthenticator;

public class AuthenticationBindingImpl implements com.sforce.soap.authentication.AuthenticationPortType{
    public com.sforce.soap.authentication.AuthenticateResult authenticate(com.sforce.soap.authentication.Authenticate parameters) throws java.rmi.RemoteException {
    	
    	return new TokenAuthenticator().authenticate(parameters);
    	
    }

}
