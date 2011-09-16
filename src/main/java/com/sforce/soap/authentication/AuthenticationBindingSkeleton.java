package com.sforce.soap.authentication;


public class AuthenticationBindingSkeleton implements com.sforce.soap.authentication.AuthenticationPortType, org.apache.axis.wsdl.Skeleton {
    private com.sforce.soap.authentication.AuthenticationPortType impl;
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
                new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "Authenticate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "Authenticate"), com.sforce.soap.authentication.Authenticate.class, false, false),
        };
        _oper = new org.apache.axis.description.OperationDesc("authenticate", _params, new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "AuthenticateResult"));
        _oper.setReturnType(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "AuthenticateResult"));
        _oper.setElementQName(new javax.xml.namespace.QName("", "Authenticate"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("authenticate") == null) {
            _myOperations.put("authenticate", new java.util.ArrayList());
        }
        ((java.util.List) _myOperations.get("authenticate")).add(_oper);
    }

    public AuthenticationBindingSkeleton() {
        this.impl = new com.sforce.soap.authentication.AuthenticationBindingImpl();
    }

    public AuthenticationBindingSkeleton(com.sforce.soap.authentication.AuthenticationPortType impl) {
        this.impl = impl;
    }

    public com.sforce.soap.authentication.AuthenticateResult authenticate(com.sforce.soap.authentication.Authenticate parameters) throws java.rmi.RemoteException {
        com.sforce.soap.authentication.AuthenticateResult ret = impl.authenticate(parameters);
        return ret;
    }

}
