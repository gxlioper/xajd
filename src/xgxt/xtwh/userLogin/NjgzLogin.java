package xgxt.xtwh.userLogin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.CommanForm;

public class NjgzLogin {

	/**
	 * ��ssoCenterͨѶ��������web service��ȡ�����û�������Ϣ��json�ַ���
	 * 
	 * @param ssoCenter
	 *            sso���ĵ�ַ
	 * @param ssoId
	 *            sso����,��request.getParameter(��SSO_ID��)���
	 * @return JSONObject �����û���Ϣ��JSONObject
	 */
	private JSONObject getSSOUser(String ssoCenter, String ssoId)
			throws Exception {
		String endpoint = ssoCenter;
		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName("getSSOUser");
		String ret = (String) call.invoke(new Object[] { ssoId });
		if (ret != null) {
			return JSONObject.fromString(ret);
		}
		return null;
	}

	public String[] userLogin(CommanForm chkForm, HttpServletRequest request,
			ActionMapping mapping) {
		String ssoCenter = Base.getInitProperties().get("njgzUrl");
		String ssoId = request.getParameter("SSO_ID");
		LoginDbCenter loginClass = new LoginDbCenter();
		HashMap<String,String> map = new HashMap<String,String>();
		String []  userChk = null;
		try {
			JSONObject jobj = getSSOUser(ssoCenter, ssoId);
			String userName = jobj.getString("loginName");
			String xm = jobj.getString("realName");
			String userType = jobj.getString("userType");

			//String userName = request.getParameter("userName");
			
			map.put("userName", userName);
			map.put("xm", xm);
			map.put("userType", userType);
			userChk=loginClass.userLogin(map, request, chkForm, mapping);
			System.out.println("��¼��:" + jobj.getString("loginName"));
			System.out.println("����:" + jobj.getString("realName"));
			System.out.println("�û�����:" + jobj.getString("userType"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�����޷���ȡ�û�����");
		}
		return userChk;
	}
}
