package xgxt.base;

import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import thauth.ThauthConst;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;

public class UserLoginCheck {
	/**
	 * 北京经济统一身份认证
	 * @param session
	 * @param userMap
	 */
	public HashMap<String, String> setAllLoginInfo(HttpServletRequest request,Hashtable<String, String> userTable){
	       DAO dao = DAO.getInstance();
			/** 存储登录用户的基本信息,存储在session中 */
	        HttpSession session = request.getSession();
			session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// 学校的评奖周期,xn
			// 为每学年评一次;xq
			// 为学期评一次
			String userType = userTable.get(ThauthConst.THAUTH_YHLB);// teacher/student
			String userName = userTable.get(ThauthConst.THAUTH_ZJH);
			String zjh      = userTable.get(ThauthConst.THAUTH_YHM);
			String sql = "";
			String[] userChk = new String[]{};
			HashMap<String, String> resultMap = new HashMap<String, String>(); 
			if("teacher".equalsIgnoreCase(userType)){
				userChk = dao.getOneRs("select bmdm szbm,xm from fdyxxb where zgh=?", new String[]{zjh}, new String[]{"szbm","xm"});
				if(userChk != null){
					session.setAttribute("isFdy", true);
				} else {
					sql = "select szbm,xm from yhb where yhm=?";
					userChk = dao.getOneRs(sql, new String[]{userName}, new String[]{"szbm","xm"});
					session.setAttribute("isFdy", false);
				}
				session.setAttribute("isBzr", Fdypd.isBzr(userName, ""));
				session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
				session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
			} else if("student".equalsIgnoreCase(userType)){
				sql = "select xm,bmdm szbm from view_xsjbxx where xh=?";
				userChk = dao.getOneRs(sql, new String[]{userName}, new String[]{"szbm","xm"});
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);
				session.setAttribute("fdyQx", false);
				session.setAttribute("bzrQx", false);
			} else {
				resultMap.put("errorMsg", "用户类型不正确！");
				session.setAttribute("isFdy", false);
				return resultMap;
			}
			
			
			String[] xx = dao.getOneRs("select xxdm,xxmc from xtszb where rownum = 1", new String[]{}, new String[]{"xxdm","xxmc"});
			
			session.setAttribute("userOnLine", userType);// 用户类型（学生、老师）
			session.setAttribute("userName", userName);  // 登陆名
			
			if(userChk == null){//数据库中不存在
				resultMap.put("errorMsg", "用户不存在！请核对后登陆！");
				session.setAttribute("isFdy", false);
				return resultMap;
			}else{
				session.setAttribute("userDep", (userChk!=null) && (userChk.length==2) ? userChk[0] :"");// 用户部门
				session.setAttribute("userNameReal", (userChk!=null) && (userChk.length==2) ? userChk[1] :"");// 用户姓名
			}
			session.setAttribute("xxdm", xx[0]);//学校代码
			session.setAttribute("xxmc", xx[1]);//学校名称
			// Modify 10.12 
			session.setAttribute("LoginXn", Base.currXn);
			session.setAttribute("LoginXq", Base.currXq);
			session.setAttribute("LoginZc", StandardOperation.getCurrZc());
			String openType = request.getParameter("openType");
			if ("".equalsIgnoreCase(openType) || openType == null) {
				openType = "2";
			}
			session.setAttribute("openType", openType);

			/** 识别用户具体类型 */
			String adminDep = "";
			if (userType.equalsIgnoreCase("teacher")) {
				sql = "select xgbdm from xtszb where rownum=1";
				adminDep = dao.getOneRs(sql, new String[] {},
						new String[] { "xgbdm" })[0];
				if (Base.isNull(adminDep)) {
					resultMap.put("errorMsg", "用户类型识别错误，请重新登陆。");
				}
				sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
				String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
						new String[] { "bmdm", "bmmc", "bmlb" });
				if (userTmp == null) {
					resultMap.put("errorMsg", "用户类型识别错误，请重新登陆。");
				}
				session.setAttribute("bmmc", userTmp[1]);
				if (userTmp[0].equalsIgnoreCase(adminDep)) {
					userType = "admin";
				} else if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			} else {
				userType = "stu";
			}
			session.setAttribute("userType", userType);// （四类）
			return resultMap;
	}
}
