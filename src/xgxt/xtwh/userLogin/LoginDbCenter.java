package xgxt.xtwh.userLogin;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;

public class LoginDbCenter {

	public String[] userLogin(HashMap<String, String> map,
			HttpServletRequest request, CommanForm chkForm,
			ActionMapping mapping) {
		// 统一身份验证学工认证
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
		if (!Base.isNull(userType)) {
			userChk = new String[] { userChk[0], userChk[1], userChk[2],
					userType, userName };
		}
		return userChk;
	}
}
