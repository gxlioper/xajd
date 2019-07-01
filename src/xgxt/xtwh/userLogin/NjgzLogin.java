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
	 * 和ssoCenter通讯，调用其web service获取包含用户基本信息的json字符串
	 * 
	 * @param ssoCenter
	 *            sso中心地址
	 * @param ssoId
	 *            sso令牌,从request.getParameter(“SSO_ID”)获得
	 * @return JSONObject 包含用户信息的JSONObject
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
			System.out.println("登录名:" + jobj.getString("loginName"));
			System.out.println("姓名:" + jobj.getString("realName"));
			System.out.println("用户类型:" + jobj.getString("userType"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("出错！无法获取用户名！");
		}
		return userChk;
	}
}
