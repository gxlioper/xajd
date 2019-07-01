
package xgxt.pjpy.WSForCjJd;


import java.util.Iterator;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class WebserviceClient {
	private String endPointUri = null;
	private String qNameStr = null;
	private String methodName = null;
	private String[] paramArr = null;
	private String soapActionUri = null;

	public WebserviceClient(){}

	/**
	 * @param endPointUri 要请求的webservice提供地址
	 * @param nameStr  
	 * @param methodName
	 * @param paramArr2
	 * @param soapActionUri
	 */
	public WebserviceClient(String endPointUri, String qnameStr, String methodName, String[] paramArr2, String soapActionUri) {
		//super();
		this.endPointUri = endPointUri;
		this.qNameStr = qnameStr;
		this.methodName = methodName;
		this.paramArr = paramArr2;
		this.soapActionUri = soapActionUri;
	}

	/**
	 * @param endPointUri 要请求的webservice提供地址
	 * @param nameStr  
	 * @param methodName
	 * @param paramArr2
	 * @param soapActionUri
	 */
	public WebserviceClient(String endPointUri,  String methodName, String[] paramArr2) {
		this.endPointUri = endPointUri;
		this.methodName = methodName;
		this.paramArr = paramArr2;
	}

	public String getSoapActionUri() {
		return soapActionUri;
	}
	public void setSoapActionUri(String soapActionUri) {
		this.soapActionUri = soapActionUri;
	}
	public String[] getParamArr() {
		return paramArr;
	}
	public void setParamArr(String[] paramArr) {
		this.paramArr = paramArr;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getQNameStr() {
		return qNameStr;
	}
	public void setQNameStr(String nameStr) {
		qNameStr = nameStr;
	}
	public String getEndPointUri() {
		return endPointUri;
	}
	public void setEndPointUri(String endPointUri) {
		this.endPointUri = endPointUri;
	}
	public String getCj(String xh){
//		Service service = new Service();
//		Call call = null;
		try{


		}catch(Exception e){

		}
		return "";
	}
	public String[] getJd(String xh,String mm){
		Service service = new Service();
		Call call = null;
		String[] result = null;
		try{
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(getEndPointUri()));
			call.setOperationName(new QName("http://www.zf_webservice.com/zfxsjd","zfxsjd"));
			call.addParameter("str_xh",org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN);
			call.addParameter("str_mm",org.apache.axis.encoding.XMLType.XSD_DATE,javax.xml.rpc.ParameterMode.IN);
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANY);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://www.zf_webservice.com/rpc");
			result = (String[])call.invoke(new Object[]{xh,mm});
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

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
	/**
	 * /*
	 *  0:正确
			1:输入的教师编号为空
			2:输入的密码为空
			3:用户不存在
			4:用户表中密码为空，请先初始化
			5:密码错误

	 * @param zgh
	 * @param mm
	 * @return
	 */
	public boolean getFdyMM(String zgh,String mm){
		boolean result = false;
		// TODO 从教务提供的服务中检测对应的职工号，密码是否正确

		Service service = new Service();
		Call call = null;
		try{
			
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(getEndPointUri()));
			call.setOperationName(new QName(getQNameStr(),getMethodName()));
			String[] params = getParamArr();
			addParameter(call,params,getSoapActionUri());
			int res = (Integer) call.invoke(new Object[]{zgh,mm});			
			System.out.println(res);
			switch( res ){
			case 0: result = true;break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5: result = false;break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	//06015125/12
	public boolean getCheckMsg(String username,String passward){
		boolean result = false;
		// TODO 从教务提供的服务中检测对应的职工号，密码是否正确

		Service service = new Service();
		Call call = null;
		try{
			
			call = (Call) service.createCall();
			call.setTargetEndpointAddress(new java.net.URL(getEndPointUri()));
			call.setOperationName(new QName(getQNameStr(),getMethodName()));
			String[] params = getParamArr();
			addParameter(call, params);
			String xmlStr = (String) call.invoke(new Object[]{username,passward});			
			 Document doc = DocumentHelper.parseText(xmlStr);
			 Element rootElt = doc.getRootElement(); // 获取根节点
			 Iterator iter = rootElt.elementIterator("ResultInfo");
			 int code=0;
			 while (iter.hasNext()) {  
	                Element recordEle = (Element) iter.next();  
	                 code = Integer.parseInt(recordEle.elementTextTrim("code")); 
			 }
			switch( code ){
			case 201: result = true;break;
			default: result = false;break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args){
		WebserviceClient wc = new WebserviceClient();
		
		wc.setEndPointUri("http://10.128.32.99/zjdxgc_new/service.asmx?wsdl");
//		wc.setMethodName("zfxsjd");//getTableColumnInfo
		wc.setParamArr(new String[]{"str_xh","str_mm"});
//		wc.setParamArr(new String[]{"part"});
		
		Service service = new Service();
		try{
			Call call = (Call)service.createCall();
//			call.registerTypeMapping(Column.class, new BeanSerializerFactory(Column.class,qn),new BeanDeserializerFactory(Column.class,qn));
			call.setTargetEndpointAddress(wc.getEndPointUri());	
			call.setOperationName(new QName("http://www.zf_webservice.com/zfxsjd","zfxsjd"));
//			call.setOperationName(wc.getMethodName());
			
			wc.addParameter(call, wc.getParamArr());
			call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI("http://www.zf_webservice.com/rpc");
			long a = System.currentTimeMillis();
			System.out.println(a);
			String[] i = (String[])call.invoke(new Object[]{"20070042","0"});
			long b = System.currentTimeMillis();
			System.out.println(b-a);
			for(String aa : i){
				String[] ar = aa.replace("/", "").replace("$", "@@").split("@@");
				for(@SuppressWarnings("unused")
				String bb : ar){
					System.out.print(b+"  ");
				}
				System.out.println("\n");
			}
			System.out.println(i + ":长度");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
