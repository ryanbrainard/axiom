/**
 * SamlIdpSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.soap;

public class SamlIdpSoapBindingSkeleton implements axiom.soap.SamlIdp, org.apache.axis.wsdl.Skeleton {
    private axiom.soap.SamlIdp impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:axiom.soap", "axiomSamlRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:axiom.soap", "AxiomSamlRequest"), axiom.soap.AxiomSamlRequest.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("generateSamlResponse", _params, new javax.xml.namespace.QName("urn:axiom.soap", "generateSamlResponseReturn"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:axiom.soap", "generateSamlResponse"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("generateSamlResponse") == null) {
            _myOperations.put("generateSamlResponse", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("generateSamlResponse")).add(_oper);
    }

    public SamlIdpSoapBindingSkeleton() {
        this.impl = new axiom.soap.SamlIdpSoapBindingImpl();
    }

    public SamlIdpSoapBindingSkeleton(axiom.soap.SamlIdp impl) {
        this.impl = impl;
    }
    public java.lang.String generateSamlResponse(axiom.soap.AxiomSamlRequest axiomSamlRequest) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.generateSamlResponse(axiomSamlRequest);
        return ret;
    }

}
