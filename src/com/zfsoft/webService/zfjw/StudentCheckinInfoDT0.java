/**
 * StudentCheckinInfoDT0.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class StudentCheckinInfoDT0  implements java.io.Serializable {
    private int count;

    private com.zfsoft.webService.zfjw.StudentCheckinInfo[] studentCheckinInfo;

    public StudentCheckinInfoDT0() {
    }

    public StudentCheckinInfoDT0(
           int count,
           com.zfsoft.webService.zfjw.StudentCheckinInfo[] studentCheckinInfo) {
           this.count = count;
           this.studentCheckinInfo = studentCheckinInfo;
    }


    /**
     * Gets the count value for this StudentCheckinInfoDT0.
     * 
     * @return count
     */
    public int getCount() {
        return count;
    }


    /**
     * Sets the count value for this StudentCheckinInfoDT0.
     * 
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }


    /**
     * Gets the studentCheckinInfo value for this StudentCheckinInfoDT0.
     * 
     * @return studentCheckinInfo
     */
    public com.zfsoft.webService.zfjw.StudentCheckinInfo[] getStudentCheckinInfo() {
        return studentCheckinInfo;
    }


    /**
     * Sets the studentCheckinInfo value for this StudentCheckinInfoDT0.
     * 
     * @param studentCheckinInfo
     */
    public void setStudentCheckinInfo(com.zfsoft.webService.zfjw.StudentCheckinInfo[] studentCheckinInfo) {
        this.studentCheckinInfo = studentCheckinInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StudentCheckinInfoDT0)) return false;
        StudentCheckinInfoDT0 other = (StudentCheckinInfoDT0) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.count == other.getCount() &&
            ((this.studentCheckinInfo==null && other.getStudentCheckinInfo()==null) || 
             (this.studentCheckinInfo!=null &&
              java.util.Arrays.equals(this.studentCheckinInfo, other.getStudentCheckinInfo())));
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
        if (getStudentCheckinInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStudentCheckinInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStudentCheckinInfo(), i);
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
        new org.apache.axis.description.TypeDesc(StudentCheckinInfoDT0.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "StudentCheckinInfoDT0"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("count");
        elemField.setXmlName(new javax.xml.namespace.QName("", "count"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("studentCheckinInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "studentCheckinInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://tempuri.org/", "StudentCheckinInfo"));
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
