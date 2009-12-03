package com.sforce.soap.authentication;


public class SforceAuthenticationServiceLocator extends org.apache.axis.client.Service implements com.sforce.soap.authentication.SforceAuthenticationService {

/**
 * Sforce Authentication Service
 */

    public SforceAuthenticationServiceLocator() {
    }


    public SforceAuthenticationServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SforceAuthenticationServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for AuthenticationService
    private java.lang.String AuthenticationService_address = "http://localhost/";

    public java.lang.String getAuthenticationServiceAddress() {
        return AuthenticationService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AuthenticationServiceWSDDServiceName = "AuthenticationService";

    public java.lang.String getAuthenticationServiceWSDDServiceName() {
        return AuthenticationServiceWSDDServiceName;
    }

    public void setAuthenticationServiceWSDDServiceName(java.lang.String name) {
        AuthenticationServiceWSDDServiceName = name;
    }

    public com.sforce.soap.authentication.AuthenticationPortType getAuthenticationService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(AuthenticationService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAuthenticationService(endpoint);
    }

    public com.sforce.soap.authentication.AuthenticationPortType getAuthenticationService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sforce.soap.authentication.AuthenticationBindingStub _stub = new com.sforce.soap.authentication.AuthenticationBindingStub(portAddress, this);
            _stub.setPortName(getAuthenticationServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAuthenticationServiceEndpointAddress(java.lang.String address) {
        AuthenticationService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sforce.soap.authentication.AuthenticationPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sforce.soap.authentication.AuthenticationBindingStub _stub = new com.sforce.soap.authentication.AuthenticationBindingStub(new java.net.URL(AuthenticationService_address), this);
                _stub.setPortName(getAuthenticationServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("AuthenticationService".equals(inputPortName)) {
            return getAuthenticationService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "SforceAuthenticationService");
    }

    private java.util.HashSet ports = null;

    @Override
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "AuthenticationService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("AuthenticationService".equals(portName)) {
            setAuthenticationServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
