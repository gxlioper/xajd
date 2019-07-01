/**
 * BjInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class BjInfo  implements java.io.Serializable {
    private java.lang.String BJDM;

    private java.lang.String JZGH;

    private java.lang.String ZYDM;

    private java.lang.String BMDM;

    private java.lang.String BJMC;

    private java.lang.String NJ;

    public BjInfo() {
    }

    public BjInfo(
           java.lang.String BJDM,
           java.lang.String JZGH,
           java.lang.String ZYDM,
           java.lang.String BMDM,
           java.lang.String BJMC,
           java.lang.String NJ) {
           this.BJDM = BJDM;
           this.JZGH = JZGH;
           this.ZYDM = ZYDM;
           this.BMDM = BMDM;
           this.BJMC = BJMC;
           this.NJ = NJ;
    }


    /**
     * Gets the BJDM value for this BjInfo.
     * 
     * @return BJDM
     */
    public java.lang.String getBJDM() {
        return BJDM;
    }


    /**
     * Sets the BJDM value for this BjInfo.
     * 
     * @param BJDM
     */
    public void setBJDM(java.lang.String BJDM) {
        this.BJDM = BJDM;
    }


    /**
     * Gets the JZGH value for this BjInfo.
     * 
     * @return JZGH
     */
    public java.lang.String getJZGH() {
        return JZGH;
    }


    /**
     * Sets the JZGH value for this BjInfo.
     * 
     * @param JZGH
     */
    public void setJZGH(java.lang.String JZGH) {
        this.JZGH = JZGH;
    }


    /**
     * Gets the ZYDM value for this BjInfo.
     * 
     * @return ZYDM
     */
    public java.lang.String getZYDM() {
        return ZYDM;
    }


    /**
     * Sets the ZYDM value for this BjInfo.
     * 
     * @param ZYDM
     */
    public void setZYDM(java.lang.String ZYDM) {
        this.ZYDM = ZYDM;
    }


    /**
     * Gets the BMDM value for this BjInfo.
     * 
     * @return BMDM
     */
    public java.lang.String getBMDM() {
        return BMDM;
    }


    /**
     * Sets the BMDM value for this BjInfo.
     * 
     * @param BMDM
     */
    public void setBMDM(java.lang.String BMDM) {
        this.BMDM = BMDM;
    }


    /**
     * Gets the BJMC value for this BjInfo.
     * 
     * @return BJMC
     */
    public java.lang.String getBJMC() {
        return BJMC;
    }


    /**
     * Sets the BJMC value for this BjInfo.
     * 
     * @param BJMC
     */
    public void setBJMC(java.lang.String BJMC) {
        this.BJMC = BJMC;
    }


    /**
     * Gets the NJ value for this BjInfo.
     * 
     * @return NJ
     */
    public java.lang.String getNJ() {
        return NJ;
    }


    /**
     * Sets the NJ value for this BjInfo.
     * 
     * @param NJ
     */
    public void setNJ(java.lang.String NJ) {
        this.NJ = NJ;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BjInfo)) return false;
        BjInfo other = (BjInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BJDM==null && other.getBJDM()==null) || 
             (this.BJDM!=null &&
              this.BJDM.equals(other.getBJDM()))) &&
            ((this.JZGH==null && other.getJZGH()==null) || 
             (this.JZGH!=null &&
              this.JZGH.equals(other.getJZGH()))) &&
            ((this.ZYDM==null && other.getZYDM()==null) || 
             (this.ZYDM!=null &&
              this.ZYDM.equals(other.getZYDM()))) &&
            ((this.BMDM==null && other.getBMDM()==null) || 
             (this.BMDM!=null &&
              this.BMDM.equals(other.getBMDM()))) &&
            ((this.BJMC==null && other.getBJMC()==null) || 
             (this.BJMC!=null &&
              this.BJMC.equals(other.getBJMC()))) &&
            ((this.NJ==null && other.getNJ()==null) || 
             (this.NJ!=null &&
              this.NJ.equals(other.getNJ())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBJDM() != null) {
            _hashCode += getBJDM().hashCode();
        }
        if (getJZGH() != null) {
            _hashCode += getJZGH().hashCode();
        }
        if (getZYDM() != null) {
            _hashCode += getZYDM().hashCode();
        }
        if (getBMDM() != null) {
            _hashCode += getBMDM().hashCode();
        }
        if (getBJMC() != null) {
            _hashCode += getBJMC().hashCode();
        }
        if (getNJ() != null) {
            _hashCode += getNJ().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BjInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "BjInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BJDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BJDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("JZGH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "JZGH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BJMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BJMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NJ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NJ"));
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
