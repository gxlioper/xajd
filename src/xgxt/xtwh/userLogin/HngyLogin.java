package xgxt.xtwh.userLogin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.base.Encrypt;
import xgxt.form.CommanForm;
import xgxt.utils.Fdypd;

public class HngyLogin {

	public String [] userLogin(String userName, HttpServletRequest request, CommanForm chkForm, ActionMapping mapping) {
		//		 河南工业统一身份验证学工认证
		String userType = "";
		Encrypt ept = new Encrypt();
		String password = ept.encrypt(request.getParameter("password"));
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "select szbm,xm,zdm from yhb where yhm=? and kl=?";
		String [] userChk = dao.getOneRs(sql, new String[] { userName,
				password }, new String[] { "szbm", "xm","zdm" });
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		}else{
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=? and mm=?";
			userChk = dao.getOneRs(sql, new String[] { userName,
					password }, new String[] { "szbm", "xm" ,"zdm"});
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			}else {
				chkForm.setErrMsg("用户名或密码错误！");
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
		userChk = new String []{userChk[0],userChk[1],userChk[2],userType};
		return userChk;
	}

}
