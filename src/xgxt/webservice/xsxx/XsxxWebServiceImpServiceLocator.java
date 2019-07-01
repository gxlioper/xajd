/**
 * XsxxWebServiceImpServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package xgxt.webservice.xsxx;

import xgxt.comm.xml.XMLReader;

public class XsxxWebServiceImpServiceLocator extends
		org.apache.axis.client.Service implements
		xgxt.webservice.xsxx.XsxxWebServiceImpService {

	private java.lang.String host = XMLReader.getFlowControl("comm", "host");

	public XsxxWebServiceImpServiceLocator() {
	}

	public XsxxWebServiceImpServiceLocator(
			org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public XsxxWebServiceImpServiceLocator(java.lang.String wsdlLoc,
			javax.xml.namespace.QName sName)
			throws javax.xml.rpc.ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for GetXsxxWebService
	private java.lang.String GetXsxxWebService_address = host
			+ "/xgxt/services/GetXsxxWebService";

	public java.lang.String getGetXsxxWebServiceAddress() {
		return GetXsxxWebService_address;
	}

	// The WSDD service name defaults to the port name.
	private java.lang.String GetXsxxWebServiceWSDDServiceName = "GetXsxxWebService";

	public java.lang.String getGetXsxxWebServiceWSDDServiceName() {
		return GetXsxxWebServiceWSDDServiceName;
	}

	public void setGetXsxxWebServiceWSDDServiceName(java.lang.String name) {
		GetXsxxWebServiceWSDDServiceName = name;
	}

	public xgxt.webservice.xsxx.XsxxWebServiceImp getGetXsxxWebService()
			throws javax.xml.rpc.ServiceException {
		java.net.URL endpoint;
		try {
			endpoint = new java.net.URL(GetXsxxWebService_address);
		} catch (java.net.MalformedURLException e) {
			throw new javax.xml.rpc.ServiceException(e);
		}
		return getGetXsxxWebService(endpoint);
	}

	public xgxt.webservice.xsxx.XsxxWebServiceImp getGetXsxxWebService(
			java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
		try {
			xgxt.webservice.xsxx.GetXsxxWebServiceSoapBindingStub _stub = new xgxt.webservice.xsxx.GetXsxxWebServiceSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getGetXsxxWebServiceWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setGetXsxxWebServiceEndpointAddress(java.lang.String address) {
		GetXsxxWebService_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		try {
			if (xgxt.webservice.xsxx.XsxxWebServiceImp.class
					.isAssignableFrom(serviceEndpointInterface)) {
				xgxt.webservice.xsxx.GetXsxxWebServiceSoapBindingStub _stub = new xgxt.webservice.xsxx.GetXsxxWebServiceSoapBindingStub(
						new java.net.URL(GetXsxxWebService_address), this);
				_stub.setPortName(getGetXsxxWebServiceWSDDServiceName());
				return _stub;
			}
		} catch (java.lang.Throwable t) {
			throw new javax.xml.rpc.ServiceException(t);
		}
		throw new javax.xml.rpc.ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName,
			Class serviceEndpointInterface)
			throws javax.xml.rpc.ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		java.lang.String inputPortName = portName.getLocalPart();
		if ("GetXsxxWebService".equals(inputPortName)) {
			return getGetXsxxWebService();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((org.apache.axis.client.Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public javax.xml.namespace.QName getServiceName() {
		return new javax.xml.namespace.QName(host
				+ "/xgxt/services/GetXsxxWebService",
				"XsxxWebServiceImpService");
	}

	private java.util.HashSet ports = null;

	public java.util.Iterator getPorts() {
		if (ports == null) {
			ports = new java.util.HashSet();
			ports.add(new javax.xml.namespace.QName(host
					+ "/xgxt/services/GetXsxxWebService", "GetXsxxWebService"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(java.lang.String portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {

		if ("GetXsxxWebService".equals(portName)) {
			setGetXsxxWebServiceEndpointAddress(address);
		} else { // Unknown Port Name
			throw new javax.xml.rpc.ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(javax.xml.namespace.QName portName,
			java.lang.String address) throws javax.xml.rpc.ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
