/**
 * AxiomSamlRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package axiom.soap;

public class AxiomSamlRequest  implements java.io.Serializable {
    private axiom.soap.SamlVersion samlVersion;

    private java.lang.String issuer;

    private java.lang.String recipient;

    private java.lang.String userId;

    private axiom.soap.SamlUserIdLocation samlUserIdLocation;

    private java.lang.String attributeName;

    private java.lang.String attributeUri;

    private java.lang.Boolean base64EncodeResponse;

    public AxiomSamlRequest() {
    }

    public AxiomSamlRequest(
           axiom.soap.SamlVersion samlVersion,
           java.lang.String issuer,
           java.lang.String recipient,
           java.lang.String userId,
           axiom.soap.SamlUserIdLocation samlUserIdLocation,
           java.lang.String attributeName,
           java.lang.String attributeUri,
           java.lang.Boolean base64EncodeResponse) {
           this.samlVersion = samlVersion;
           this.issuer = issuer;
           this.recipient = recipient;
           this.userId = userId;
           this.samlUserIdLocation = samlUserIdLocation;
           this.attributeName = attributeName;
           this.attributeUri = attributeUri;
           this.base64EncodeResponse = base64EncodeResponse;
    }


    /**
     * Gets the samlVersion value for this AxiomSamlRequest.
     * 
     * @return samlVersion
     */
    public axiom.soap.SamlVersion getSamlVersion() {
        return samlVersion;
    }


    /**
     * Sets the samlVersion value for this AxiomSamlRequest.
     * 
     * @param samlVersion
     */
    public void setSamlVersion(axiom.soap.SamlVersion samlVersion) {
        this.samlVersion = samlVersion;
    }


    /**
     * Gets the issuer value for this AxiomSamlRequest.
     * 
     * @return issuer
     */
    public java.lang.String getIssuer() {
        return issuer;
    }


    /**
     * Sets the issuer value for this AxiomSamlRequest.
     * 
     * @param issuer
     */
    public void setIssuer(java.lang.String issuer) {
        this.issuer = issuer;
    }


    /**
     * Gets the recipient value for this AxiomSamlRequest.
     * 
     * @return recipient
     */
    public java.lang.String getRecipient() {
        return recipient;
    }


    /**
     * Sets the recipient value for this AxiomSamlRequest.
     * 
     * @param recipient
     */
    public void setRecipient(java.lang.String recipient) {
        this.recipient = recipient;
    }


    /**
     * Gets the userId value for this AxiomSamlRequest.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this AxiomSamlRequest.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the samlUserIdLocation value for this AxiomSamlRequest.
     * 
     * @return samlUserIdLocation
     */
    public axiom.soap.SamlUserIdLocation getSamlUserIdLocation() {
        return samlUserIdLocation;
    }


    /**
     * Sets the samlUserIdLocation value for this AxiomSamlRequest.
     * 
     * @param samlUserIdLocation
     */
    public void setSamlUserIdLocation(axiom.soap.SamlUserIdLocation samlUserIdLocation) {
        this.samlUserIdLocation = samlUserIdLocation;
    }


    /**
     * Gets the attributeName value for this AxiomSamlRequest.
     * 
     * @return attributeName
     */
    public java.lang.String getAttributeName() {
        return attributeName;
    }


    /**
     * Sets the attributeName value for this AxiomSamlRequest.
     * 
     * @param attributeName
     */
    public void setAttributeName(java.lang.String attributeName) {
        this.attributeName = attributeName;
    }


    /**
     * Gets the attributeUri value for this AxiomSamlRequest.
     * 
     * @return attributeUri
     */
    public java.lang.String getAttributeUri() {
        return attributeUri;
    }


    /**
     * Sets the attributeUri value for this AxiomSamlRequest.
     * 
     * @param attributeUri
     */
    public void setAttributeUri(java.lang.String attributeUri) {
        this.attributeUri = attributeUri;
    }


    /**
     * Gets the base64EncodeResponse value for this AxiomSamlRequest.
     * 
     * @return base64EncodeResponse
     */
    public java.lang.Boolean getBase64EncodeResponse() {
        return base64EncodeResponse;
    }


    /**
     * Sets the base64EncodeResponse value for this AxiomSamlRequest.
     * 
     * @param base64EncodeResponse
     */
    public void setBase64EncodeResponse(java.lang.Boolean base64EncodeResponse) {
        this.base64EncodeResponse = base64EncodeResponse;
    }

    private java.lang.Object __equalsCalc = null;
    @Override
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AxiomSamlRequest)) return false;
        AxiomSamlRequest other = (AxiomSamlRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.samlVersion==null && other.getSamlVersion()==null) || 
             (this.samlVersion!=null &&
              this.samlVersion.equals(other.getSamlVersion()))) &&
            ((this.issuer==null && other.getIssuer()==null) || 
             (this.issuer!=null &&
              this.issuer.equals(other.getIssuer()))) &&
            ((this.recipient==null && other.getRecipient()==null) || 
             (this.recipient!=null &&
              this.recipient.equals(other.getRecipient()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.samlUserIdLocation==null && other.getSamlUserIdLocation()==null) || 
             (this.samlUserIdLocation!=null &&
              this.samlUserIdLocation.equals(other.getSamlUserIdLocation()))) &&
            ((this.attributeName==null && other.getAttributeName()==null) || 
             (this.attributeName!=null &&
              this.attributeName.equals(other.getAttributeName()))) &&
            ((this.attributeUri==null && other.getAttributeUri()==null) || 
             (this.attributeUri!=null &&
              this.attributeUri.equals(other.getAttributeUri()))) &&
            ((this.base64EncodeResponse==null && other.getBase64EncodeResponse()==null) || 
             (this.base64EncodeResponse!=null &&
              this.base64EncodeResponse.equals(other.getBase64EncodeResponse())));
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
        if (getSamlVersion() != null) {
            _hashCode += getSamlVersion().hashCode();
        }
        if (getIssuer() != null) {
            _hashCode += getIssuer().hashCode();
        }
        if (getRecipient() != null) {
            _hashCode += getRecipient().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getSamlUserIdLocation() != null) {
            _hashCode += getSamlUserIdLocation().hashCode();
        }
        if (getAttributeName() != null) {
            _hashCode += getAttributeName().hashCode();
        }
        if (getAttributeUri() != null) {
            _hashCode += getAttributeUri().hashCode();
        }
        if (getBase64EncodeResponse() != null) {
            _hashCode += getBase64EncodeResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AxiomSamlRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:axiom.soap", "AxiomSamlRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("samlVersion");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "samlVersion"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:axiom.soap", "SamlVersion"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("issuer");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "issuer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recipient");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "recipient"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "userId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("samlUserIdLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "samlUserIdLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("urn:axiom.soap", "SamlUserIdLocation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeName");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "attributeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("attributeUri");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "attributeUri"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("base64EncodeResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:axiom.soap", "base64EncodeResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
          new  org.apache.axis.encoding.ser.BeanSerializer(
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
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
