/**
 * CallOptions.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sforce.soap.enterprise;

public class CallOptions implements java.io.Serializable {
    private java.lang.String client;

    private java.lang.String defaultNamespace;

    private java.lang.String remoteApplication;

    public CallOptions() {
    }

    public CallOptions(
            java.lang.String client,
            java.lang.String defaultNamespace,
            java.lang.String remoteApplication) {
        this.client = client;
        this.defaultNamespace = defaultNamespace;
        this.remoteApplication = remoteApplication;
    }


    /**
     * Gets the client value for this CallOptions.
     *
     * @return client
     */
    public java.lang.String getClient() {
        return client;
    }


    /**
     * Sets the client value for this CallOptions.
     *
     * @param client
     */
    public void setClient(java.lang.String client) {
        this.client = client;
    }


    /**
     * Gets the defaultNamespace value for this CallOptions.
     *
     * @return defaultNamespace
     */
    public java.lang.String getDefaultNamespace() {
        return defaultNamespace;
    }


    /**
     * Sets the defaultNamespace value for this CallOptions.
     *
     * @param defaultNamespace
     */
    public void setDefaultNamespace(java.lang.String defaultNamespace) {
        this.defaultNamespace = defaultNamespace;
    }


    /**
     * Gets the remoteApplication value for this CallOptions.
     *
     * @return remoteApplication
     */
    public java.lang.String getRemoteApplication() {
        return remoteApplication;
    }


    /**
     * Sets the remoteApplication value for this CallOptions.
     *
     * @param remoteApplication
     */
    public void setRemoteApplication(java.lang.String remoteApplication) {
        this.remoteApplication = remoteApplication;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CallOptions)) return false;
        CallOptions other = (CallOptions) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                ((this.client == null && other.getClient() == null) ||
                        (this.client != null &&
                                this.client.equals(other.getClient()))) &&
                ((this.defaultNamespace == null && other.getDefaultNamespace() == null) ||
                        (this.defaultNamespace != null &&
                                this.defaultNamespace.equals(other.getDefaultNamespace()))) &&
                ((this.remoteApplication == null && other.getRemoteApplication() == null) ||
                        (this.remoteApplication != null &&
                                this.remoteApplication.equals(other.getRemoteApplication())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    @Override
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getClient() != null) {
            _hashCode += getClient().hashCode();
        }
        if (getDefaultNamespace() != null) {
            _hashCode += getDefaultNamespace().hashCode();
        }
        if (getRemoteApplication() != null) {
            _hashCode += getRemoteApplication().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(CallOptions.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", ">CallOptions"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("client");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "client"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultNamespace");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "defaultNamespace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("remoteApplication");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:enterprise.soap.sforce.com", "remoteApplication"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
            java.lang.String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return
                new org.apache.axis.encoding.ser.BeanSerializer(
                        _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
            java.lang.String mechType,
            java.lang.Class _javaType,
            javax.xml.namespace.QName _xmlType) {
        return
                new org.apache.axis.encoding.ser.BeanDeserializer(
                        _javaType, _xmlType, typeDesc);
    }

}
