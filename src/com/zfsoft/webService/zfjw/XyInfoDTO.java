/**
 * XyInfoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class XyInfoDTO  implements java.io.Serializable {
    private int count;

    private com.zfsoft.webService.zfjw.XyInfo[] xyInfo;

    public XyInfoDTO() {
    }

    public XyInfoDTO(
           int count,
           com.zfsoft.webService.zfjw.XyInfo[] xyInfo) {
           this.count = count;
           this.xyInfo = xyInfo;
    }


    /**
     * Gets the count value for this XyInfoDTO.
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * Sets the count value for this XyInfoDTO.
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Gets the xyInfo value for this XyInfoDTO.
     * 
     * @return xyInfo
     */
    public com.zfsoft.webService.zfjw.XyInfo[] getXyInfo() {
        return xyInfo;
    }


    /**
     * Sets the xyInfo value for this XyInfoDTO.
     * 
     * @param xyInfo
     */
    public void setXyInfo(com.zfsoft.webService.zfjw.XyInfo[] xyInfo) {
        this.xyInfo = xyInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XyInfoDTO)) return false;
        XyInfoDTO other = (XyInfoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            ((this.xyInfo==null && other.getXyInfo()==null) || 
             (this.xyInfo!=null &&
              java.util.Arrays.equals(this.xyInfo, other.getXyInfo())));
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
        _hashCode += getCount();
        if (getXyInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXyInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXyInfo(), i);
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
        new org.apache.axis.description.TypeDesc(XyInfoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "XyInfoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xyInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XyInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "XyInfo"));
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
