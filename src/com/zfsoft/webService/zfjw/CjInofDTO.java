/**
 * CjInofDTO.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class CjInofDTO  implements java.io.Serializable {
    private int count;

    private com.zfsoft.webService.zfjw.CjInfo[] cjInfo;

    public CjInofDTO() {
    }

    public CjInofDTO(
           int count,
           com.zfsoft.webService.zfjw.CjInfo[] cjInfo) {
           this.count = count;
           this.cjInfo = cjInfo;
    }


    /**
     * Gets the count value for this CjInofDTO.
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * Sets the count value for this CjInofDTO.
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Gets the cjInfo value for this CjInofDTO.
     * 
     * @return cjInfo
     */
    public com.zfsoft.webService.zfjw.CjInfo[] getCjInfo() {
        return cjInfo;
    }


    /**
     * Sets the cjInfo value for this CjInofDTO.
     * 
     * @param cjInfo
     */
    public void setCjInfo(com.zfsoft.webService.zfjw.CjInfo[] cjInfo) {
        this.cjInfo = cjInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CjInofDTO)) return false;
        CjInofDTO other = (CjInofDTO) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            ((this.cjInfo==null && other.getCjInfo()==null) || 
             (this.cjInfo!=null &&
              java.util.Arrays.equals(this.cjInfo, other.getCjInfo())));
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
        if (getCjInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCjInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCjInfo(), i);
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
        new org.apache.axis.description.TypeDesc(CjInofDTO.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "CjInofDTO"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cjInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CjInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "CjInfo"));
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
