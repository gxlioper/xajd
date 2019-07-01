package xgxt.webservice.xsxx;

import java.net.URL;

import xgxt.base.Encrypt;
import xgxt.comm.xml.XMLReader;

public class test {

	public static void main(String[] args) {

		System.out.println("yhhh");

		String host = XMLReader.getFlowControl("comm", "host");
		String url = host + "/xgxt/services/GetXsxxWebService";

		XsxxWebServiceImpServiceLocator locator = new XsxxWebServiceImpServiceLocator();
		try {
			XsxxWebServiceImp service = new GetXsxxWebServiceSoapBindingStub(
					new URL(url), locator);
			System.out.println(service.getXsxxXxws("3061001057"));
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
	}
}
