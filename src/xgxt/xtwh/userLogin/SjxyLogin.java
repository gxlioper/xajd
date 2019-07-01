package xgxt.xtwh.userLogin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;

public class SjxyLogin {
	public void setXml(HashMap<String,String> map) throws Exception {

		String strEnterPoint = "WebService地址"; // webService的入口

		Service service = new Service();
		Call call = null;
		call = (Call) service.createCall();
		call.setOperationName(new QName("urn:DefaultNamespace", "userUpdate")); // userTicketCheck为令牌校验接口函数名
		call.setTargetEndpointAddress(new java.net.URL(strEnterPoint));

		// String strIP = "127.0.0.1";//getIpAddr(req); //获得客户端的IP地址
		// 返回验证是否通过，成功返回身份true，失败返回false
		
		String user_id = map.get("xh");
		String user_name = map.get("xm");
		String gender = map.get("xb");
		String type = map.get("type");
		String id_number = map.get("id_number");
		String email = map.get("lxdzxx");
		String mobile = map.get("sjhm");
		String depart = map.get("depart");
		
		StringBuffer strXML = new StringBuffer();
		strXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		strXML.append("<root act=\"add\" amount=\"2\"><user>");
		strXML.append("<user_id>" + user_id + "</user_id>");
		strXML.append("<user_name>" + user_name + "</user_name>");
		strXML.append("<gender>" + gender + "</gender>");
		strXML.append("<type>" + type + "</type>");
		strXML.append("<id_number>" + id_number + "</id_number>");
		strXML.append("<email>" + email + "</email>");
		strXML.append("<mobile>" + mobile + "</mobile>");
		strXML.append("<depart>" + depart + "</depart>");
		strXML.append("</user></root>");

		call.addParameter("op1", XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_STRING);
		String strReturn = (String) call.invoke(new Object[] { strXML.toString() }); // strBusiSysID
		// 为天空教室系统的编号

		if (!!strReturn.equals("00")) {
			// 验证失败
			// 转向错误页面
			return;
		}

		return;
	}

	public String[] userLogin(HashMap<String, String> map,
			HttpServletRequest request, CommanForm chkForm,
			ActionMapping mapping) throws Exception {
		// 三江学院统一身份验证学工认证
		String userType = "";
		String userName = map.get("userName");
		// String xxdm = map.get("xxdm");
		// String xxmc = map.get("xxmc");
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "select szbm,xm,zdm from yhb where yhm=?";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				chkForm.setErrMsg("不存在该用户！");
			}
		}

		if (userType.equalsIgnoreCase("teacher")) {
			boolean isFdy = Fdypd.isFdy(userName);
			session.setAttribute("isFdyUser", isFdy);
			session.setAttribute("isFdy", isFdy);
			session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
			session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
			session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
		} else {
			session.setAttribute("isFdyUser", false);
			session.setAttribute("isFdy", false);
			session.setAttribute("isBzr", false);
			session.setAttribute("fdyQx", false);
			session.setAttribute("bzrQx", false);
		}
		
		if (userChk != null) {
			userChk = new String[] { userChk[0], userChk[1], userChk[2],
					userType };
		}
		
		HashMap<String,String> xmlInfo = new HashMap<String,String>();
		if ("student".equalsIgnoreCase(userType)) {
			sql = "select xh,xm,xb,sfzh,sjhm,lxdzxx,xydm from view_xsjbxx where xh = ?";
			xmlInfo = dao.getMap(sql, new String[]{userName}, new String[]{"xh","xm","xb","sfzh","sjhm","lxdzxx","xydm"});
			xmlInfo.put("type", "学生");
		}else if("teacher".equalsIgnoreCase(userType)){
			sql = "select * from (select a.yhm xh,a.xm,b.xb,b.sfzh,b.yddh sjhm,b.dzyx lxdzxx,a.szbm xydm from yhb a left join fdyxxb b on a.yhm = b.zgh) where xh = ?";
			xmlInfo = dao.getMap(sql, new String[]{userName}, new String[]{"xh","xm","xb","sfzh","sjhm","lxdzxx","xydm"});
			xmlInfo.put("type", "老师");
		}
		if (xmlInfo != null && xmlInfo.size() > 0) {
			//setXml(xmlInfo);
		}
		
		return userChk;
	}
}
