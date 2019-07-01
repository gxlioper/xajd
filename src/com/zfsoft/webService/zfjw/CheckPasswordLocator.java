/**
 * CheckPasswordLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.zfsoft.webService.zfjw;

public class CheckPasswordLocator extends org.apache.axis.client.Service implements com.zfsoft.webService.zfjw.CheckPassword {

    public CheckPasswordLocator() {
    }


    public CheckPasswordLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CheckPasswordLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for CheckPasswordSoap
    private java.lang.String CheckPasswordSoap_address = "http://10.71.32.140/zjdxgc_kf/Service.asmx";

    public java.lang.String getCheckPasswordSoapAddress() {
        return CheckPasswordSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CheckPasswordSoapWSDDServiceName = "CheckPasswordSoap";

    public java.lang.String getCheckPasswordSoapWSDDServiceName() {
        return CheckPasswordSoapWSDDServiceName;
    }

    public void setCheckPasswordSoapWSDDServiceName(java.lang.String name) {
        CheckPasswordSoapWSDDServiceName = name;
    }

    public com.zfsoft.webService.zfjw.CheckPasswordSoap_PortType getCheckPasswordSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(CheckPasswordSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCheckPasswordSoap(endpoint);
    }

    public com.zfsoft.webService.zfjw.CheckPasswordSoap_PortType getCheckPasswordSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.zfsoft.webService.zfjw.CheckPasswordSoapStub _stub = new com.zfsoft.webService.zfjw.CheckPasswordSoapStub(portAddress, this);
            _stub.setPortName(getCheckPasswordSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCheckPasswordSoapEndpointAddress(java.lang.String address) {
        CheckPasswordSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.zfsoft.webService.zfjw.CheckPasswordSoap_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.zfsoft.webService.zfjw.CheckPasswordSoapStub _stub = new com.zfsoft.webService.zfjw.CheckPasswordSoapStub(new java.net.URL(CheckPasswordSoap_address), this);
                _stub.setPortName(getCheckPasswordSoapWSDDServiceName());
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
        if ("CheckPasswordSoap".equals(inputPortName)) {
            return getCheckPasswordSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "CheckPassword");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "CheckPasswordSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("CheckPasswordSoap".equals(portName)) {
            setCheckPasswordSoapEndpointAddress(address);
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
