/**
 * SoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sforce.soap.enterprise;

public class SoapBindingSkeleton implements com.sforce.soap.enterprise.Soap, org.apache.axis.wsdl.Skeleton {
    private com.sforce.soap.enterprise.Soap impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
     * Returns List of OperationDesc objects with this name
     */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List) _myOperations.get(methodName);
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
        org.apache.axis.description.ParameterDesc[] _params;
        _params = new org.apache.axis.description.ParameterDesc[]{
                new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "username"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false),
                new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "password"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("login", _params, new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "result"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "LoginResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "login"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("login") == null) {
            _myOperations.put("login", new java.util.ArrayList());
        }
        ((java.util.List) _myOperations.get("login")).add(_oper);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("InvalidIdFault");
        _fault.setQName(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "InvalidIdFault"));
        _fault.setClassName("com.sforce.soap.enterprise.fault.InvalidIdFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "InvalidIdFault"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("UnexpectedErrorFault");
        _fault.setQName(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "UnexpectedErrorFault"));
        _fault.setClassName("com.sforce.soap.enterprise.fault.UnexpectedErrorFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "UnexpectedErrorFault"));
        _oper.addFault(_fault);
        _fault = new org.apache.axis.description.FaultDesc();
        _fault.setName("LoginFault");
        _fault.setQName(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "LoginFault"));
        _fault.setClassName("com.sforce.soap.enterprise.fault.LoginFault");
        _fault.setXmlType(new javax.xml.namespace.QName("urn:fault.enterprise.soap.sforce.com", "LoginFault"));
        _oper.addFault(_fault);
    }

    public SoapBindingSkeleton() {
        this.impl = new axiom.delauth.token.AuthenticationProxy();
    }

    public SoapBindingSkeleton(com.sforce.soap.enterprise.Soap impl) {
        this.impl = impl;
    }

    public com.sforce.soap.enterprise.LoginResult login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException, com.sforce.soap.enterprise.fault.InvalidIdFault, com.sforce.soap.enterprise.fault.UnexpectedErrorFault, com.sforce.soap.enterprise.fault.LoginFault {
        com.sforce.soap.enterprise.LoginResult ret = impl.login(username, password);
        return ret;
    }

}
