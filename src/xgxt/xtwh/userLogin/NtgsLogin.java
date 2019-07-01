/**
 * @部门:学工产品事业部
 * @日期：2013-6-14 上午09:39:46 
 */  
package xgxt.xtwh.userLogin;

import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 南通高师登录
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路 [工号：982]
 * @时间： 2013-6-14 上午09:39:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class NtgsLogin {
	/**
	 * 通过该认证信息调用认证中心提供的Web Service接口获取用户信息和转向地址接口
	 * 
	 * @param ticket
	 *            认证信息
	 * @param valusercode
	 *            认证账户，由统一身份认证平台提供，默认账号是valuser，如果统一身份认证没有提供，那么就使用valuser;
	 * @return
	 */
	public static String TicketVal(String ticket, String valusercode,
			String wsUrl) {
		Service service = new Service();
		service.setMaintainSession(true);// 设置为保持会话
		try {
			Call call = (Call) service.createCall();
			// 设置访问点
			call.setTargetEndpointAddress(wsUrl);

			// 设置操作名
			call.setOperationName("getTicketVal");

			// 设置入口参数
			call.addParameter("ticket", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("valusercode", XMLType.XSD_STRING,
					ParameterMode.IN);
			// 设置返回参数类型
			call.setReturnType(XMLType.XSD_STRING);
			// 调用服务
			return call.invoke(new Object[] { ticket, valusercode }).toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		JSONObject jsonObj=JSONObject.fromObject("{'phoneNo':'null','idNo':'11111111111111111111','userIdentity':'教职工','userCode':'js','gotoUrl':'null','mobilePhoneNo':'11111111111111111111','userSex':'','userDept':'南通高等师范学校','userName':'张三','success':'true','userType':'其他'}");
		System.out.println("jsonObj:"+jsonObj);
		Iterator<String> it=jsonObj.keys();
		while(it.hasNext()){
			String key=it.next();
			System.out.println("key:"+key);
			System.out.println("value:"+jsonObj.get(key));
		}
		System.out.println("-------------json-end-------------");
	}
	public static String[] userLogin(HashMap<String, String> map,
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
