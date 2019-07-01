/**
 * ZyInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class ZyInfo  implements java.io.Serializable {
    private java.lang.String ZYDM;

    private java.lang.String BMDM;

    private java.lang.String XKMLDM;

    private java.lang.String ZYMC;

    private java.lang.String ZYYWMC;

    private java.lang.String JLNY;

    private java.lang.String GJZYDM;

    private java.lang.String KSDM;

    private java.lang.String ZYJC;

    public ZyInfo() {
    }

    public ZyInfo(
           java.lang.String ZYDM,
           java.lang.String BMDM,
           java.lang.String XKMLDM,
           java.lang.String ZYMC,
           java.lang.String ZYYWMC,
           java.lang.String JLNY,
           java.lang.String GJZYDM,
           java.lang.String KSDM,
           java.lang.String ZYJC) {
           this.ZYDM = ZYDM;
           this.BMDM = BMDM;
           this.XKMLDM = XKMLDM;
           this.ZYMC = ZYMC;
           this.ZYYWMC = ZYYWMC;
           this.JLNY = JLNY;
           this.GJZYDM = GJZYDM;
           this.KSDM = KSDM;
           this.ZYJC = ZYJC;
    }


    /**
     * Gets the ZYDM value for this ZyInfo.
     * 
     * @return ZYDM
     */
    public java.lang.String getZYDM() {
        return ZYDM;
    }


    /**
     * Sets the ZYDM value for this ZyInfo.
     * 
     * @param ZYDM
     */
    public void setZYDM(java.lang.String ZYDM) {
        this.ZYDM = ZYDM;
    }


    /**
     * Gets the BMDM value for this ZyInfo.
     * 
     * @return BMDM
     */
    public java.lang.String getBMDM() {
        return BMDM;
    }


    /**
     * Sets the BMDM value for this ZyInfo.
     * 
     * @param BMDM
     */
    public void setBMDM(java.lang.String BMDM) {
        this.BMDM = BMDM;
    }


    /**
     * Gets the XKMLDM value for this ZyInfo.
     * 
     * @return XKMLDM
     */
    public java.lang.String getXKMLDM() {
        return XKMLDM;
    }


    /**
     * Sets the XKMLDM value for this ZyInfo.
     * 
     * @param XKMLDM
     */
    public void setXKMLDM(java.lang.String XKMLDM) {
        this.XKMLDM = XKMLDM;
    }


    /**
     * Gets the ZYMC value for this ZyInfo.
     * 
     * @return ZYMC
     */
    public java.lang.String getZYMC() {
        return ZYMC;
    }


    /**
     * Sets the ZYMC value for this ZyInfo.
     * 
     * @param ZYMC
     */
    public void setZYMC(java.lang.String ZYMC) {
        this.ZYMC = ZYMC;
    }


    /**
     * Gets the ZYYWMC value for this ZyInfo.
     * 
     * @return ZYYWMC
     */
    public java.lang.String getZYYWMC() {
        return ZYYWMC;
    }


    /**
     * Sets the ZYYWMC value for this ZyInfo.
     * 
     * @param ZYYWMC
     */
    public void setZYYWMC(java.lang.String ZYYWMC) {
        this.ZYYWMC = ZYYWMC;
    }


    /**
     * Gets the JLNY value for this ZyInfo.
     * 
     * @return JLNY
     */
    public java.lang.String getJLNY() {
        return JLNY;
    }


    /**
     * Sets the JLNY value for this ZyInfo.
     * 
     * @param JLNY
     */
    public void setJLNY(java.lang.String JLNY) {
        this.JLNY = JLNY;
    }


    /**
     * Gets the GJZYDM value for this ZyInfo.
     * 
     * @return GJZYDM
     */
    public java.lang.String getGJZYDM() {
        return GJZYDM;
    }


    /**
     * Sets the GJZYDM value for this ZyInfo.
     * 
     * @param GJZYDM
     */
    public void setGJZYDM(java.lang.String GJZYDM) {
        this.GJZYDM = GJZYDM;
    }


    /**
     * Gets the KSDM value for this ZyInfo.
     * 
     * @return KSDM
     */
    public java.lang.String getKSDM() {
        return KSDM;
    }


    /**
     * Sets the KSDM value for this ZyInfo.
     * 
     * @param KSDM
     */
    public void setKSDM(java.lang.String KSDM) {
        this.KSDM = KSDM;
    }


    /**
     * Gets the ZYJC value for this ZyInfo.
     * 
     * @return ZYJC
     */
    public java.lang.String getZYJC() {
        return ZYJC;
    }


    /**
     * Sets the ZYJC value for this ZyInfo.
     * 
     * @param ZYJC
     */
    public void setZYJC(java.lang.String ZYJC) {
        this.ZYJC = ZYJC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ZyInfo)) return false;
        ZyInfo other = (ZyInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ZYDM==null && other.getZYDM()==null) || 
             (this.ZYDM!=null &&
              this.ZYDM.equals(other.getZYDM()))) &&
            ((this.BMDM==null && other.getBMDM()==null) || 
             (this.BMDM!=null &&
              this.BMDM.equals(other.getBMDM()))) &&
            ((this.XKMLDM==null && other.getXKMLDM()==null) || 
             (this.XKMLDM!=null &&
              this.XKMLDM.equals(other.getXKMLDM()))) &&
            ((this.ZYMC==null && other.getZYMC()==null) || 
             (this.ZYMC!=null &&
              this.ZYMC.equals(other.getZYMC()))) &&
            ((this.ZYYWMC==null && other.getZYYWMC()==null) || 
             (this.ZYYWMC!=null &&
              this.ZYYWMC.equals(other.getZYYWMC()))) &&
            ((this.JLNY==null && other.getJLNY()==null) || 
             (this.JLNY!=null &&
              this.JLNY.equals(other.getJLNY()))) &&
            ((this.GJZYDM==null && other.getGJZYDM()==null) || 
             (this.GJZYDM!=null &&
              this.GJZYDM.equals(other.getGJZYDM()))) &&
            ((this.KSDM==null && other.getKSDM()==null) || 
             (this.KSDM!=null &&
              this.KSDM.equals(other.getKSDM()))) &&
            ((this.ZYJC==null && other.getZYJC()==null) || 
             (this.ZYJC!=null &&
              this.ZYJC.equals(other.getZYJC())));
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
        if (getZYDM() != null) {
            _hashCode += getZYDM().hashCode();
        }
        if (getBMDM() != null) {
            _hashCode += getBMDM().hashCode();
        }
        if (getXKMLDM() != null) {
            _hashCode += getXKMLDM().hashCode();
        }
        if (getZYMC() != null) {
            _hashCode += getZYMC().hashCode();
        }
        if (getZYYWMC() != null) {
            _hashCode += getZYYWMC().hashCode();
        }
        if (getJLNY() != null) {
            _hashCode += getJLNY().hashCode();
        }
        if (getGJZYDM() != null) {
            _hashCode += getGJZYDM().hashCode();
        }
        if (getKSDM() != null) {
            _hashCode += getKSDM().hashCode();
        }
        if (getZYJC() != null) {
            _hashCode += getZYJC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ZyInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "ZyInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("XKMLDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XKMLDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYYWMC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYYWMC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("JLNY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "JLNY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GJZYDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GJZYDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("KSDM");
        elemField.setXmlName(new javax.xml.namespace.QName("", "KSDM"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZYJC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZYJC"));
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
