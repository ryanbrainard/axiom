package com.sforce.soap.authentication;


public interface SforceAuthenticationService extends javax.xml.rpc.Service {

/**
 * Sforce Authentication Service
 */
    public java.lang.String getAuthenticationServiceAddress();

    public com.sforce.soap.authentication.AuthenticationPortType getAuthenticationService() throws javax.xml.rpc.ServiceException;

    public com.sforce.soap.authentication.AuthenticationPortType getAuthenticationService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
