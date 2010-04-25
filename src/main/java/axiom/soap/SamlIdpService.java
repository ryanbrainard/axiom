/**
 * SamlIdpService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.soap;

public interface SamlIdpService extends javax.xml.rpc.Service {
    public java.lang.String getsamlIdpAddress();

    public axiom.soap.SamlIdp getsamlIdp() throws javax.xml.rpc.ServiceException;

    public axiom.soap.SamlIdp getsamlIdp(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
