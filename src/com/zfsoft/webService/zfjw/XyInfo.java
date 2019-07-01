/**
 * XyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class XyInfo  implements java.io.Serializable {
    private java.lang.String BMDM;

    private java.lang.String BMMC;

    private java.lang.String BMPYJC;

    private java.lang.String BMFDM;

    private java.lang.String BMJB;

    private java.lang.String BMLB;

    private java.lang.String BZ;

    public XyInfo() {
    }

    public XyInfo(
           java.lang.String BMDM,
           java.lang.String BMMC,
           java.lang.String BMPYJC,
           java.lang.String BMFDM,
           java.lang.String BMJB,
           java.lang.String BMLB,
           java.lang.String BZ) {
           this.BMDM = BMDM;
           this.BMMC = BMMC;
           this.BMPYJC = BMPYJC;
           this.BMFDM = BMFDM;
           this.BMJB = BMJB;
           this.BMLB = BMLB;
           this.BZ = BZ;
    }


    /**
     * Gets the BMDM value for this XyInfo.
     * 
     * @return BMDM
     */
    public java.lang.String getBMDM() {
        return BMDM;
    }


    /**
     * Sets the BMDM value for this XyInfo.
     * 
     * @param BMDM
     */
    public void setBMDM(java.lang.String BMDM) {
        this.BMDM = BMDM;
    }


    /**
     * Gets the BMMC value for this XyInfo.
     * 
     * @return BMMC
     */
    public java.lang.String getBMMC() {
        return BMMC;
    }


    /**
     * Sets the BMMC value for this XyInfo.
     * 
     * @param BMMC
     */
    public void setBMMC(java.lang.String BMMC) {
        this.BMMC = BMMC;
    }


    /**
     * Gets the BMPYJC value for this XyInfo.
     * 
     * @return BMPYJC
     */
    public java.lang.String getBMPYJC() {
        return BMPYJC;
    }


    /**
     * Sets the BMPYJC value for this XyInfo.
     * 
     * @param BMPYJC
     */
    public void setBMPYJC(java.lang.String BMPYJC) {
        this.BMPYJC = BMPYJC;
    }


    /**
     * Gets the BMFDM value for this XyInfo.
     * 
     * @return BMFDM
     */
    public java.lang.String getBMFDM() {
        return BMFDM;
    }


    /**
     * Sets the BMFDM value for this XyInfo.
     * 
     * @param BMFDM
     */
    public void setBMFDM(java.lang.String BMFDM) {
        this.BMFDM = BMFDM;
    }


    /**
     * Gets the BMJB value for this XyInfo.
     * 
     * @return BMJB
     */
    public java.lang.String getBMJB() {
        return BMJB;
    }


    /**
     * Sets the BMJB value for this XyInfo.
     * 
     * @param BMJB
     */
    public void setBMJB(java.lang.String BMJB) {
        this.BMJB = BMJB;
    }


    /**
     * Gets the BMLB value for this XyInfo.
     * 
     * @return BMLB
     */
    public java.lang.String getBMLB() {
        return BMLB;
    }


    /**
     * Sets the BMLB value for this XyInfo.
     * 
     * @param BMLB
     */
    public void setBMLB(java.lang.String BMLB) {
        this.BMLB = BMLB;
    }


    /**
     * Gets the BZ value for this XyInfo.
     * 
     * @return BZ
     */
    public java.lang.String getBZ() {
        return BZ;
    }


    /**
     * Sets the BZ value for this XyInfo.
     * 
     * @param BZ
     */
    public void setBZ(java.lang.String BZ) {
        this.BZ = BZ;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XyInfo)) return false;
        XyInfo other = (XyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.BMDM==null && other.getBMDM()==null) || 
             (this.BMDM!=null &&
              this.BMDM.equals(other.getBMDM()))) &&
            ((this.BMMC==null && other.getBMMC()==null) || 
             (this.BMMC!=null &&
              this.BMMC.equals(other.getBMMC()))) &&
            ((this.BMPYJC==null && other.getBMPYJC()==null) || 
             (this.BMPYJC!=null &&
              this.BMPYJC.equals(other.getBMPYJC()))) &&
            ((this.BMFDM==null && other.getBMFDM()==null) || 
             (this.BMFDM!=null &&
              this.BMFDM.equals(other.getBMFDM()))) &&
            ((this.BMJB==null && other.getBMJB()==null) || 
             (this.BMJB!=null &&
              this.BMJB.equals(other.getBMJB()))) &&
            ((this.BMLB==null && other.getBMLB()==null) || 
             (this.BMLB!=null &&
              this.BMLB.equals(other.getBMLB()))) &&
            ((this.BZ==null && other.getBZ()==null) || 
             (this.BZ!=null &&
              this.BZ.equals(other.getBZ())));
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
        if (getBMDM() != null) {
            _hashCode += getBMDM().hashCode();
        }
        if (getBMMC() != null) {
            _hashCode += getBMMC().hashCode();
        }
        if (getBMPYJC() != null) {
            _hashCode += getBMPYJC().hashCode();
        }
        if (getBMFDM() != null) {
            _hashCode += getBMFDM().hashCode();
        }
        if (getBMJB() != null) {
            _hashCode += getBMJB().hashCode();
        }
        if (getBMLB() != null) {
            _hashCode += getBMLB().hashCode();
        }
        if (getBZ() != null) {
            _hashCode += getBZ().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(XyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "XyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMPYJC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMPYJC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMFDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMFDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMJB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMJB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BMLB");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BMLB"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BZ");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BZ"));
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
