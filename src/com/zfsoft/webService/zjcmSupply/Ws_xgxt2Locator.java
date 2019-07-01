/**
 * Ws_xgxt2Locator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zjcmSupply;

public class Ws_xgxt2Locator extends org.apache.axis.client.Service implements com.zfsoft.webService.zjcmSupply.Ws_xgxt2 {

    public Ws_xgxt2Locator() {
    }


    public Ws_xgxt2Locator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Ws_xgxt2Locator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ws_xgxt2Soap12
    private java.lang.String ws_xgxt2Soap12_address = "http://ls.zjicm.edu.cn/WebService/ws_xgxt2.asmx";

    public java.lang.String getws_xgxt2Soap12Address() {
        return ws_xgxt2Soap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ws_xgxt2Soap12WSDDServiceName = "ws_xgxt2Soap12";

    public java.lang.String getws_xgxt2Soap12WSDDServiceName() {
        return ws_xgxt2Soap12WSDDServiceName;
    }

    public void setws_xgxt2Soap12WSDDServiceName(java.lang.String name) {
        ws_xgxt2Soap12WSDDServiceName = name;
    }

    public com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap getws_xgxt2Soap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ws_xgxt2Soap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getws_xgxt2Soap12(endpoint);
    }

    public com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap getws_xgxt2Soap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap12Stub _stub = new com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap12Stub(portAddress, this);
            _stub.setPortName(getws_xgxt2Soap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setws_xgxt2Soap12EndpointAddress(java.lang.String address) {
        ws_xgxt2Soap12_address = address;
    }


    // Use to get a proxy class for ws_xgxt2Soap
    private java.lang.String ws_xgxt2Soap_address = "http://ls.zjicm.edu.cn/WebService/ws_xgxt2.asmx";

    public java.lang.String getws_xgxt2SoapAddress() {
        return ws_xgxt2Soap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ws_xgxt2SoapWSDDServiceName = "ws_xgxt2Soap";

    public java.lang.String getws_xgxt2SoapWSDDServiceName() {
        return ws_xgxt2SoapWSDDServiceName;
    }

    public void setws_xgxt2SoapWSDDServiceName(java.lang.String name) {
        ws_xgxt2SoapWSDDServiceName = name;
    }

    public com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap getws_xgxt2Soap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ws_xgxt2Soap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getws_xgxt2Soap(endpoint);
    }

    public com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap getws_xgxt2Soap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zfsoft.webService.zjcmSupply.Ws_xgxt2SoapStub _stub = new com.zfsoft.webService.zjcmSupply.Ws_xgxt2SoapStub(portAddress, this);
            _stub.setPortName(getws_xgxt2SoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setws_xgxt2SoapEndpointAddress(java.lang.String address) {
        ws_xgxt2Soap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap12Stub _stub = new com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap12Stub(new java.net.URL(ws_xgxt2Soap12_address), this);
                _stub.setPortName(getws_xgxt2Soap12WSDDServiceName());
                return _stub;
            }
            if (com.zfsoft.webService.zjcmSupply.Ws_xgxt2Soap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zfsoft.webService.zjcmSupply.Ws_xgxt2SoapStub _stub = new com.zfsoft.webService.zjcmSupply.Ws_xgxt2SoapStub(new java.net.URL(ws_xgxt2Soap_address), this);
                _stub.setPortName(getws_xgxt2SoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ws_xgxt2Soap12".equals(inputPortName)) {
            return getws_xgxt2Soap12();
        }
        else if ("ws_xgxt2Soap".equals(inputPortName)) {
            return getws_xgxt2Soap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxt2");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxt2Soap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxt2Soap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ws_xgxt2Soap12".equals(portName)) {
            setws_xgxt2Soap12EndpointAddress(address);
        }
        else 
if ("ws_xgxt2Soap".equals(portName)) {
            setws_xgxt2SoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
