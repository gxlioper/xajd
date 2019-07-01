package xgxt.studentInfo.xmlgxy.webservice;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import xgxt.action.Base;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺ����ѧ����Ϣ�� </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-12-19</p>
 */
public class WebServiceClientForXmlgxy {
	
	private String endPointUri = null;
	private String qNameStr = null;
	private String methodName = null;
	private String[] paramArr = null;
	private String soapActionUri = null;
	
	public WebServiceClientForXmlgxy(){}
	/**
	 * @param endPointUri Ҫ�����webservice�ṩ��ַ
	 * @param nameStr  
	 * @param methodName
	 * @param paramArr2
	 * @param soapActionUri
	 */
	public WebServiceClientForXmlgxy(String endPointUri, String qnameStr, String methodName, String[] paramArr2, String soapActionUri) {
		//super();
		this.endPointUri = endPointUri;
		this.qNameStr = qnameStr;
		this.methodName = methodName;
		this.paramArr = paramArr2;
		this.soapActionUri = soapActionUri;
	}
	
	/**
	 * ����ѧ��������Ϣ
	 * @param xh
	 * @return boolean
	 * */
	public boolean updateInfo(String ksh){
		WebServiceClientForXmlgxy wc = new WebServiceClientForXmlgxy();
		Base.getInitProperties().get("xmlgWebserviceUri");
		wc.setEndPointUri(Base.getInitProperties().get("xmlgWebserviceUri"));
		wc.setParamArr(new String[]{"part"});//��������
		
		Service service = new Service();
		try{
			Call call = (Call)service.createCall();
			call.setTargetEndpointAddress(wc.getEndPointUri());	
			call.setOperationName(new QName("http://tempuri.org/","RegisterStu"));//����
			
			wc.addParameter(call, wc.getParamArr());
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://tempuri.org/SinoSoftBiztalkStudentIn_orc_StudentIn_Port_StudentIn/RegisterStu");
			String returnValue = (String)call.invoke(new Object[]{ksh});
			System.out.println("����:" + ksh);
			System.out.println("����ֵ�� " + returnValue);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
//	
	
//	public boolean updateInfo(String xh){
//		WebServiceClientForXmlgxy wc = new WebServiceClientForXmlgxy();
////		Base.getInitProperties().get("xmlgWebserviceUri");
//		wc.setEndPointUri("http://localhost:8080/xgxt/services/GetDataServices?wsdl");
//		wc.setParamArr(new String[]{"part"});//��������
//		
//		Service service = new Service();
//		try{
//			Call call = (Call)service.createCall();
//			call.setTargetEndpointAddress(wc.getEndPointUri());	
//			call.setOperationName(new QName("http://localhost:8080/xgxt/services/GetDataServices","ChangeStudentInfo"));//����
//			
//			wc.addParameter(call, wc.getParamArr());
//			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("");
//			String returnValue = (String)call.invoke(new Object[]{xh});
//			System.out.println("����ֵ�� " + returnValue);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return true;
//	}
	
//	public static void main(String[] args){
//		updateInfo("123456");
//	}
	protected void addParameter(Call call,String[] params,String soapActionUri){
		for(int i=0;i<params.length;i++){
			call.addParameter(params[i], org.apache.axis.encoding.XMLType.XSD_DATE, javax.xml.rpc.ParameterMode.IN);
		}
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANY);
		call.setUseSOAPAction(true);
		call.setSOAPActionURI(soapActionUri);
	}

	protected void addParameter(Call call,String[] params){
		for(int i=0;i<params.length;i++){
			call.addParameter(params[i], org.apache.axis.encoding.XMLType.XSD_DATE, javax.xml.rpc.ParameterMode.IN);
		}
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANY);
	}
	
	public String getEndPointUri() {
		return endPointUri;
	}
	public void setEndPointUri(String endPointUri) {
		this.endPointUri = endPointUri;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String[] getParamArr() {
		return paramArr;
	}
	public void setParamArr(String[] paramArr) {
		this.paramArr = paramArr;
	}
	public String getQNameStr() {
		return qNameStr;
	}
	public void setQNameStr(String nameStr) {
		qNameStr = nameStr;
	}
	public String getSoapActionUri() {
		return soapActionUri;
	}
	public void setSoapActionUri(String soapActionUri) {
		this.soapActionUri = soapActionUri;
	}
}
