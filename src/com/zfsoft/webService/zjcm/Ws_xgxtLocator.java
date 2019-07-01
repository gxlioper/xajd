/**
 * Ws_xgxtLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zjcm;

import javax.xml.namespace.QName;

public class Ws_xgxtLocator extends org.apache.axis.client.Service implements com.zfsoft.webService.zjcm.Ws_xgxt {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ws_xgxtLocator() {
    }


    public Ws_xgxtLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public Ws_xgxtLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ws_xgxtSoap12
    private java.lang.String ws_xgxtSoap12_address = "http://ls.zjicm.edu.cn/WebService/ws_xgxt.asmx";

    public java.lang.String getws_xgxtSoap12Address() {
        return ws_xgxtSoap12_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ws_xgxtSoap12WSDDServiceName = "ws_xgxtSoap12";

    public java.lang.String getws_xgxtSoap12WSDDServiceName() {
        return ws_xgxtSoap12WSDDServiceName;
    }

    public void setws_xgxtSoap12WSDDServiceName(java.lang.String name) {
        ws_xgxtSoap12WSDDServiceName = name;
    }

    public com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType getws_xgxtSoap12() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ws_xgxtSoap12_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getws_xgxtSoap12(endpoint);
    }

    public com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType getws_xgxtSoap12(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub _stub = new com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub(portAddress, this);
            _stub.setPortName(getws_xgxtSoap12WSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setws_xgxtSoap12EndpointAddress(java.lang.String address) {
        ws_xgxtSoap12_address = address;
    }


    // Use to get a proxy class for ws_xgxtSoap
    private java.lang.String ws_xgxtSoap_address = "http://ls.zjicm.edu.cn/WebService/ws_xgxt.asmx";

    public java.lang.String getws_xgxtSoapAddress() {
        return ws_xgxtSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ws_xgxtSoapWSDDServiceName = "ws_xgxtSoap";

    public java.lang.String getws_xgxtSoapWSDDServiceName() {
        return ws_xgxtSoapWSDDServiceName;
    }

    public void setws_xgxtSoapWSDDServiceName(java.lang.String name) {
        ws_xgxtSoapWSDDServiceName = name;
    }

    public com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType getws_xgxtSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ws_xgxtSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getws_xgxtSoap(endpoint);
    }

    public com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType getws_xgxtSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zfsoft.webService.zjcm.Ws_xgxtSoap_BindingStub _stub = new com.zfsoft.webService.zjcm.Ws_xgxtSoap_BindingStub(portAddress, this);
            _stub.setPortName(getws_xgxtSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setws_xgxtSoapEndpointAddress(java.lang.String address) {
        ws_xgxtSoap_address = address;
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
            if (com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub _stub = new com.zfsoft.webService.zjcm.Ws_xgxtSoap12Stub(new java.net.URL(ws_xgxtSoap12_address), this);
                _stub.setPortName(getws_xgxtSoap12WSDDServiceName());
                return _stub;
            }
            if (com.zfsoft.webService.zjcm.Ws_xgxtSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zfsoft.webService.zjcm.Ws_xgxtSoap_BindingStub _stub = new com.zfsoft.webService.zjcm.Ws_xgxtSoap_BindingStub(new java.net.URL(ws_xgxtSoap_address), this);
                _stub.setPortName(getws_xgxtSoapWSDDServiceName());
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
        if ("ws_xgxtSoap12".equals(inputPortName)) {
            return getws_xgxtSoap12();
        }
        else if ("ws_xgxtSoap".equals(inputPortName)) {
            return getws_xgxtSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxt");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxtSoap12"));
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "ws_xgxtSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ws_xgxtSoap12".equals(portName)) {
            setws_xgxtSoap12EndpointAddress(address);
        }
        else 
if ("ws_xgxtSoap".equals(portName)) {
            setws_xgxtSoapEndpointAddress(address);
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
