
package xgxt.pjpy.xnmz;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.axis.client.Call;

import xgxt.action.Base;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 西南民族大学调用学生绩点CLIENT</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-11-17</p>
 */
public class XnmzWebServiceClient {

	private String endPointUri = null;//WEBSERVICE地址
	private String qNameStr = null;
	private String methodName = null;//调用方法名
	private String[] paramArr = null;
	private String soapActionUri = null;

	
	/**
	 * 生成构造函数
	 * @param endPointUri
	 * @param nameStr
	 * @param methodName
	 * @param paramArr
	 * @param soapActionUri
	 *//*
	public XnmzWebServiceClient(String endPointUri, String nameStr, String methodName, String[] paramArr, String soapActionUri) {
		//super();
		this.endPointUri = endPointUri;
		qNameStr = nameStr;
		this.methodName = methodName;
		this.paramArr = paramArr;
		this.soapActionUri = soapActionUri;
	}*/
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
	
	protected void addParameter(Call call,String[] params){
		for(int i=0;i<params.length;i++){
			call.addParameter(params[i], org.apache.axis.encoding.XMLType.XSD_DATE, javax.xml.rpc.ParameterMode.IN);
		}
		call.setReturnType(org.apache.axis.encoding.XMLType.XSD_ANY);
	}
	
	/**
	 * 学生成绩绩点数据同步,传入WEBSERVICE路径,学生学号和密码
	 * @param urlName
	 * @param stuxhmmList
	 * @return
	 * @throws Exception
	 */
	public boolean xscjjdSjtb(String urlName, ArrayList<String[]> stuxhmmList) throws Exception {
		boolean bFlag = false;
		
		return bFlag;
	}
	
	/**
	 * 获取WEBSERVICE路径
	 * @return
	 * @throws Exception
	 */
	public String getWebServiceUrl() throws Exception {
		String url = "";
		HashMap<String, String> rs = Base.getInitProperties();
		url = rs.get("xnmzWebserviceUri");
		return url;
	}
	
	public static void main(String[] args) throws Exception {
		XnmzWebServiceClient wc = new XnmzWebServiceClient();
		String url = wc.getWebServiceUrl();
		
		String[] aa = new String[]{"06090243", "0"};
		ArrayList<String[]> bb = new ArrayList<String[]>() ;
		bb.add(aa);
		wc.xscjjdSjtb(url, bb);
	}
}
