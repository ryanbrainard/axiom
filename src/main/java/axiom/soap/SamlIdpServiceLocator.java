/**
 * SamlIdpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.soap;

public class SamlIdpServiceLocator extends org.apache.axis.client.Service implements axiom.soap.SamlIdpService {

    public SamlIdpServiceLocator() {
    }


    public SamlIdpServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SamlIdpServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for samlIdp
    private java.lang.String samlIdp_address = "http://localhost/axiom/services/samlIdp";

    public java.lang.String getsamlIdpAddress() {
        return samlIdp_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String samlIdpWSDDServiceName = "samlIdp";

    public java.lang.String getsamlIdpWSDDServiceName() {
        return samlIdpWSDDServiceName;
    }

    public void setsamlIdpWSDDServiceName(java.lang.String name) {
        samlIdpWSDDServiceName = name;
    }

    public axiom.soap.SamlIdp getsamlIdp() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(samlIdp_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getsamlIdp(endpoint);
    }

    public axiom.soap.SamlIdp getsamlIdp(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            axiom.soap.SamlIdpSoapBindingStub _stub = new axiom.soap.SamlIdpSoapBindingStub(portAddress, this);
            _stub.setPortName(getsamlIdpWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setsamlIdpEndpointAddress(java.lang.String address) {
        samlIdp_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @Override
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (axiom.soap.SamlIdp.class.isAssignableFrom(serviceEndpointInterface)) {
                axiom.soap.SamlIdpSoapBindingStub _stub = new axiom.soap.SamlIdpSoapBindingStub(new java.net.URL(samlIdp_address), this);
                _stub.setPortName(getsamlIdpWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
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
        if ("samlIdp".equals(inputPortName)) {
            return getsamlIdp();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    @Override
    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("urn:axiom.soap", "SamlIdpService");
    }

    private java.util.HashSet ports = null;

    @Override
    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("urn:axiom.soap", "samlIdp"));
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("samlIdp".equals(portName)) {
            setsamlIdpEndpointAddress(address);
        } else { // Unknown Port Name
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
