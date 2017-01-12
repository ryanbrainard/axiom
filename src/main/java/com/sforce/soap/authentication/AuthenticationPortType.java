package com.sforce.soap.authentication;


public interface AuthenticationPortType extends java.rmi.Remote {

    /**
     * Are the supplied salesforce.com userId and password valid ?
     */
    public com.sforce.soap.authentication.AuthenticateResult authenticate(com.sforce.soap.authentication.Authenticate parameters) throws java.rmi.RemoteException;
}
