package xgxt.pjpy.ahjg;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description: 安徽建筑工业学院评奖评优通用DAO
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: 李涛
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time: 2008-06-16
 * </p>
 */
public class CommanDAO {

	/**
	 * 获取
	 * 
	 * @param xjbjQryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(XjBjQryModel xjbjQryModel)
			throws Exception {
		StringBuffer whereSql = new StringBuffer();

		return whereSql;
	}
	
	/**
	 * 用户当点登陆   
	 * @param yhm    用户名
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean ssoLogin(String yhm, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute("istysfrz", "yes");
		String userType = "";
		String userName = yhm;
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
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
				errMsg = "用户表中无该用户，无法登陆学工系统，请确认！！";
				//session.setAttribute("errMsg", errMsg);// 错误类型
				request.setAttribute("errMsg", errMsg);// 错误类型
				return false;
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

		userChk = new String[] { userChk[0], userChk[1], userChk[2], userType };

		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},
				"xxmc");

		/** 存储登录用户的基本信息,存储在session中 */
		session.setAttribute("pjzq", StandardOperation.getCollegePriseCycle());// 学校的评奖周期,xn
		// 为每学年评一次;xq
		// 为学期评一次
		session.setAttribute("userOnLine", userType);// 用户类型（学生、老师）
		session.setAttribute("userName", userName);// 登陆名
		session.setAttribute("userDep", userChk[0]);// 用户部门
		session.setAttribute("userNameReal", userChk[1]);// 用户姓名
		session.setAttribute("xxmc", xxmc);
		session.setAttribute("xxdm", xxdm);
		session.setAttribute("LoginXn", Base.currXn);
		String gyglySql = "select count(1) num from xg_gygl_new_gyfdyb where yhm = ?";
		String num = dao.getOneRs(gyglySql, new String[] { userName }, "num");
		String gyglyQx = !Base.isNull(num) && !"0".equalsIgnoreCase(num) ? "yes"
				: "no";
		session.setAttribute("gyglyQx", gyglyQx);
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
				errMsg = "学工部代码为空，无法登陆学工系统，请确认！！";
				//session.setAttribute("errMsg", errMsg);// 错误类型
				request.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				errMsg = "部门表中无该部门，无法登陆学工系统，请确认！！";
				//session.setAttribute("errMsg", errMsg);// 错误类型
				request.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
			session.setAttribute("bmmc", userTmp[1]);
			// TODO
			if (userTmp[0] != null && userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			} else if (!Base.isNull(userTmp[2])) {
				if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			} else {
				errMsg = "该用户部门类别为空，无法登陆学工系统，请确认！！";
				//session.setAttribute("errMsg", errMsg);// 错误类型
				request.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
		} else {
			userType = "stu";
		}
		session.setAttribute("userType", userType);// （四类）
		// 版本
		String edition = Base.getEdition();
		// 是否需要高级查询
		String superSearch = Base.getSuperSearch();

		// -------------------------2011.9.14
		// qlj-------------------------------------
		// ===================修改用户登陆时间 begin=======================

		// 格式: yyyy-mm-dd HH24:MI:SS
		String dlsj = GetTime.getNowTime4();
		// 将用户登陆时间设置到session中
		session.setAttribute("loginTime", dlsj);

		List<String> inputV = new ArrayList<String>();
		StringBuilder updateTime = new StringBuilder();

		String checkTime = " select count(1)num from xg_xtwh_yhdlb where yhm='"
				+ userName + "' and yhlx='" + userType + "' ";
		String number = dao.getOneRs(checkTime.toString(), new String[] {},
				"num");

		// ===================修改用户登陆时间 end=======================

		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);
		return true;
	}
	
	/**
	 * 获取用户名
	 * @param yhm
	 * @return
	 */
	public String getUserType(String yhm){
		String userName = yhm;
		DAO dao = DAO.getInstance();
		String userType="";
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
				userType="";
			}
		}
		return userType;
	}
}
