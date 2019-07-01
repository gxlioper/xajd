/**
 * XjydInfoDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class XjydInfoDTO  implements java.io.Serializable {
    private int count;

    private com.zfsoft.webService.zfjw.XjydInfo[] xjydInfo;

    public XjydInfoDTO() {
    }

    public XjydInfoDTO(
           int count,
           com.zfsoft.webService.zfjw.XjydInfo[] xjydInfo) {
           this.count = count;
           this.xjydInfo = xjydInfo;
    }


    /**
     * Gets the count value for this XjydInfoDTO.
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * Sets the count value for this XjydInfoDTO.
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Gets the xjydInfo value for this XjydInfoDTO.
     * 
     * @return xjydInfo
     */
    public com.zfsoft.webService.zfjw.XjydInfo[] getXjydInfo() {
        return xjydInfo;
    }


    /**
     * Sets the xjydInfo value for this XjydInfoDTO.
     * 
     * @param xjydInfo
     */
    public void setXjydInfo(com.zfsoft.webService.zfjw.XjydInfo[] xjydInfo) {
        this.xjydInfo = xjydInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof XjydInfoDTO)) return false;
        XjydInfoDTO other = (XjydInfoDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            ((this.xjydInfo==null && other.getXjydInfo()==null) || 
             (this.xjydInfo!=null &&
              java.util.Arrays.equals(this.xjydInfo, other.getXjydInfo())));
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
        if (getXjydInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getXjydInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getXjydInfo(), i);
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
        new org.apache.axis.description.TypeDesc(XjydInfoDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "XjydInfoDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("xjydInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XjydInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "XjydInfo"));
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
