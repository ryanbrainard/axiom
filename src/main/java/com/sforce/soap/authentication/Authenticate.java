package com.sforce.soap.authentication;


public class Authenticate implements java.io.Serializable, org.apache.axis.encoding.AnyContentType {
    private java.lang.String username;

    private java.lang.String password;

    private java.lang.String sourceIp;

    private org.apache.axis.message.MessageElement[] _any;

    public Authenticate() {
    }

    public Authenticate(
            java.lang.String username,
            java.lang.String password,
            java.lang.String sourceIp,
            org.apache.axis.message.MessageElement[] _any) {
        this.username = username;
        this.password = password;
        this.sourceIp = sourceIp;
        this._any = _any;
    }


    /**
     * Gets the username value for this Authenticate.
     *
     * @return username
     */
    public java.lang.String getUsername() {
        return username;
    }


    /**
     * Sets the username value for this Authenticate.
     *
     * @param username
     */
    public void setUsername(java.lang.String username) {
        this.username = username;
    }


    /**
     * Gets the password value for this Authenticate.
     *
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this Authenticate.
     *
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the sourceIp value for this Authenticate.
     *
     * @return sourceIp
     */
    public java.lang.String getSourceIp() {
        return sourceIp;
    }


    /**
     * Sets the sourceIp value for this Authenticate.
     *
     * @param sourceIp
     */
    public void setSourceIp(java.lang.String sourceIp) {
        this.sourceIp = sourceIp;
    }


    /**
     * Gets the _any value for this Authenticate.
     *
     * @return _any
     */
    public org.apache.axis.message.MessageElement[] get_any() {
        return _any;
    }


    /**
     * Sets the _any value for this Authenticate.
     *
     * @param _any
     */
    public void set_any(org.apache.axis.message.MessageElement[] _any) {
        this._any = _any;
    }

    private java.lang.Object __equalsCalc = null;

    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Authenticate)) return false;
        Authenticate other = (Authenticate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                ((this.username == null && other.getUsername() == null) ||
                        (this.username != null &&
                                this.username.equals(other.getUsername()))) &&
                ((this.password == null && other.getPassword() == null) ||
                        (this.password != null &&
                                this.password.equals(other.getPassword()))) &&
                ((this.sourceIp == null && other.getSourceIp() == null) ||
                        (this.sourceIp != null &&
                                this.sourceIp.equals(other.getSourceIp()))) &&
                ((this._any == null && other.get_any() == null) ||
                        (this._any != null &&
                                java.util.Arrays.equals(this._any, other.get_any())));
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
        if (getUsername() != null) {
            _hashCode += getUsername().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getSourceIp() != null) {
            _hashCode += getSourceIp().hashCode();
        }
        if (get_any() != null) {
            for (int i = 0;
                 i < java.lang.reflect.Array.getLength(get_any());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(get_any(), i);
                if (obj != null &&
                        !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(Authenticate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "Authenticate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("username");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "username"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceIp");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:authentication.soap.sforce.com", "sourceIp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
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
