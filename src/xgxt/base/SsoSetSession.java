package xgxt.base;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.Fdypd;
import xgxt.utils.GetTime;

import com.zfsoft.zfca.tp.cas.client.ZfssoBean;
import com.zfsoft.zfca.tp.cas.client.ZfssoSetsessionService;
import common.Globals;

public class SsoSetSession implements ZfssoSetsessionService {
	private Log logger = LogFactory.getLog(SsoSetSession.class);
	public Boolean chkUserSession(ZfssoBean zfssobean) {
		Boolean res = false;
		String userName = "";
		// 校验用户session是否存在，存在返回true，失败返回false;
		HttpServletRequest request = zfssobean.getRequest();
		HttpSession session = request.getSession();
		
		if (null != session.getAttribute("userName") || null != session.getAttribute("jyweb_userName")) {
			String xgUserName = (String) session.getAttribute("userName");
			String jywebUserName = (String)session.getAttribute("jyweb_userName");
			
			userName = Base.isNull(xgUserName) ? jywebUserName : xgUserName;
			
		}
		
//		System.out.println("用户名："+ userName);
		
		if (null != userName && (zfssobean.getYhm().equalsIgnoreCase(userName))) {
			res = true;
		}
		
//		System.out.println("校验用户session是否存在:"+ res);
		
		return res;
	}
	
	
	
	
	public boolean setXgUserSession(ZfssoBean zfssobean) {
		HttpSession session = zfssobean.getRequest().getSession();
		session.setAttribute("istysfrz", "yes");
		String userType = "";
		String userName = zfssobean.getYhm();
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		
		logger.info("--------------userName:"+userName+"--------------");
		String sql = "select szbm,xm,zdm from yhb where yhm=? and qx='1' and zdm is not null";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "szbm", "xm", "zdm" });
		logger.info("--------------userChk:"+userChk+"--------------");
		if ((userChk != null) && (userChk.length == 3)) {
			userType = "teacher";
		} else {
			sql = "select xm,bmdm szbm,'6727' zdm from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] { "szbm", "xm", "zdm" });
			//华师大个性化判断
			if(Base.xxdm.equals("10698")&&(userChk == null || userChk.length == 0)){
				sql = "select xm,xydm szbm,'6727' zdm from view_xsxxb where xh=? and xjztm = '有学籍'";
				userChk = dao.getOneRs(sql, new String[] { userName },
						new String[] { "szbm", "xm", "zdm" });
			}
			if ((userChk != null) && (userChk.length == 3)) {
				userType = "student";
			} else {
				errMsg = "尊敬的用户： 对不起，你未被授权访问此业务系统。<br/>请确认获得相应权限后，再重新请求该资源。";
				System.out.println("用户表中无该用户，无法登陆学工系统，请确认！！");
				zfssobean.getSession().setAttribute("errMsg", errMsg);// 错误类型
				//if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
//					try {
//						//zfssobean.getSession().setAttribute("yhInfo", errMsg);// 错误类型
//						//zfssobean.getResponse().sendRedirect("/yhInfo.do");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				//}
				return false;
			}
		}
		
		if (userType.equalsIgnoreCase("teacher")) {
			boolean isFdy = Fdypd.isFdy(userName);
			session.setAttribute("isFdyUser", isFdy);
			session.setAttribute("isFdy", isFdy);
			session.setAttribute("isBzr", Fdypd.isBzr(userName,""));
			session.setAttribute("fdyQx", Fdypd.fdybjck(userName));
			session.setAttribute("bzrQx", Fdypd.bzrbjck(userName));
			// 是否兼任学院用户
			String sfjryx= Fdypd.checkSfjrXy(userName).get("sfjryx");
			// 非空取数据
			if(!Base.isNull(sfjryx)){
				session.setAttribute("sfjryx", sfjryx);
				
				// 用户权限选择默认由大到小
				if ("true".equalsIgnoreCase(sfjryx)){
					session.setAttribute("fdyQx", false);
					session.setAttribute("bzrQx", false);
				}
				
			}else{
				session.setAttribute("fdyQx", false);
				session.setAttribute("bzrQx", false);
				session.setAttribute("isFdy", false);
				session.setAttribute("isBzr", false);
			}
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
		
		//是否书院用户
		String sySql = "select sydm from fdyxxb where zgh=?";
		String sydm = dao.getOneRs(sySql, new String[] { userName },
				"sydm");
		String syQx = !Base.isNull(sydm) ? "yes" : "no";
		
		session.setAttribute("syQx", syQx);// 是否是书院用户
		session.setAttribute("gyglyQx", gyglyQx);
		session.setAttribute("LoginXq", Base.currXq);
		session.setAttribute("LoginZc", StandardOperation.getCurrZc());
		String openType = zfssobean.getRequest().getParameter("openType");
		if ("".equalsIgnoreCase(openType) || openType == null) {
			openType = "2";
		}
		session.setAttribute("openType", openType);
		
		//金华职业个性化处理，调用我的工作存储过程
		if (Globals.XXDM_JHZYJSXY.equalsIgnoreCase(Base.xxdm)) {
			String userStatus = "";
			boolean fdyQx = Fdypd.isFdy(userName);
			boolean bzrQx = Fdypd.isBzr(userName,"");
			if (bzrQx && fdyQx) {
				userStatus = "jd";// 班主任兼辅导员
			} else if (fdyQx) {
				userStatus = "fdy";// 辅导员
			} else if (bzrQx) {
				userStatus = "bzr";// 班主任
			} else if ("xy".equalsIgnoreCase(userType)) {
				userStatus = "xy";// 学院
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				userStatus = "xx";// 学校用户（管理员）
			} else {
				userStatus = "stu";// 学生
			}
			try {
				dao.runProcuder("{call pro_xg_xtwh_wdgz(?,?,?)}",new String[]{userName, userStatus, userChk[0]});
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				System.out.println("=====>未知错误，通过门户进入调用我的工作存储过程失败!======>");
				e.printStackTrace();
			}
		}
		//end
				
		/** 识别用户具体类型 */
		String adminDep = "";
		if (userType.equalsIgnoreCase("teacher")) {
			sql = "select xgbdm from xtszb where rownum=1";
			adminDep = dao.getOneRs(sql, new String[] {},
					new String[] { "xgbdm" })[0];
			if (Base.isNull(adminDep)) {
				errMsg = "学工部代码为空，无法登陆学工系统，请确认！！";
				System.out.println("学工部代码为空，无法登陆学工系统，请确认！！");
				session.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
			sql = "select (case bmjb when 1 then bmdm else bmfdm end) bmdm,bmmc,bmlb from zxbz_xxbmdm where bmdm=?";
			String[] userTmp = dao.getOneRs(sql, new String[] { userChk[0] },
					new String[] { "bmdm", "bmmc", "bmlb" });
			if (userTmp == null) {
				errMsg = "部门表中无该部门，无法登陆学工系统，请确认！！";
				System.out.println("部门表中无该部门，无法登陆学工系统，请确认！！");
				session.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
			session.setAttribute("bmmc", userTmp[1]);
			// TODO
			if (userTmp[0] != null && userTmp[0].equalsIgnoreCase(adminDep)) {
				userType = "admin";
			}else if (!Base.isNull(userTmp[2])) {
				if (userTmp[2].equalsIgnoreCase("5")) {
					userType = "xy";
				} else {
					userType = "xx";
				}
			}else{
				errMsg = "该用户部门类别为空，无法登陆学工系统，请确认！！";
				System.out.println("该用户部门类别为空，无法登陆学工系统，请确认！！");
				session.setAttribute("errMsg", errMsg);// 错误类型
				return false;
			}
			
			if (!Base.isNull(sydm)) {
				userType = "sy";
			}
		} else {
			userType = "stu";
		}
		session.setAttribute("userType", userType);// （四类）
//		 版本
		String edition = Base.getEdition();
		// 是否需要高级查询
		String superSearch = Base.getSuperSearch();
		
//		 -------------------------2011.9.14 qlj-------------------------------------
		// ===================修改用户登陆时间 begin=======================
		
		// 格式: yyyy-mm-dd HH24:MI:SS
		String dlsj=GetTime.getNowTime4();
		// 将用户登陆时间设置到session中
		session.setAttribute("loginTime",dlsj);
		
		List<String>inputV=new ArrayList<String>();
		StringBuilder updateTime=new StringBuilder();
		
		String checkTime=" select count(1)num from xg_xtwh_yhdlb where yhm='"+userName+"' and yhlx='"+userType+"' ";
		String number=dao.getOneRs(checkTime.toString(), new String[]{}, "num");
		
		// ===============判断用户是否存在 begin================
		
		
//		if("0".equalsIgnoreCase(number)){
//			updateTime.append(" insert into xg_xtwh_yhdlb(yhm,yhlx,dlsj)values(?,?,?) ");
//			inputV.add(userName);
//			inputV.add(userType);
//			inputV.add(dlsj);
//			try {
//				dao.runUpdate(updateTime.toString(), inputV.toArray(new String[]{}));
//			} catch (Exception e) {
//				// TODO 自动生成 catch 块
//				e.printStackTrace();
//			}
//		}else{
//			updateTime.append(" update xg_xtwh_yhdlb set dlsj=? where yhm=? and yhlx=? ");
//			inputV.add(dlsj);
//			inputV.add(userName);
//			inputV.add(userType);
//			try {
//				dao.runUpdate(updateTime.toString(), inputV.toArray(new String[]{}));
//			} catch (Exception e) {
//				// TODO 自动生成 catch 块
//				e.printStackTrace();
//			}
//		}
		// ===============判断用户是否存在 end================
		// ===================修改用户登陆时间 end=======================
		
		session.setAttribute("edition", edition);
		session.setAttribute("superSearch", superSearch);
		return true;
	}
	
	
	
	
	public boolean  setJywebUserSession(ZfssoBean zfssobean) {
		HttpSession session = zfssobean.getRequest().getSession();

		String userType = "";
		String userName = zfssobean.getYhm();
		String errMsg = "";
		String xxdm = StandardOperation.getXxdm();
		DAO dao = DAO.getInstance();
		
		String sql = "select a.*,'' shzt,(select zmc from yhzb where zdm=a.zdm) zmc from yhb a  where yhm=? ";
		String[] userChk = dao.getOneRs(sql, new String[] { userName },
				new String[] { "xm","szbm","shzt","zmc" });
	
//		System.out.println("查询用户表.....");
		
		
		if ((userChk != null) && (userChk.length == 4)) {
			userType = "xy";
			
//			System.out.println("学院用户.....");
		}else {
			System.out.println("学生用户.....");
			sql = "select xm,bmdm szbm,'6727' zdm,'学生' zmc from view_xsjbxx where xh=?";
			userChk = dao.getOneRs(sql, new String[] { userName },
					new String[] {"xm", "szbm","zdm","zmc" });
			//华师大个性化判断
			if(Base.xxdm.equals("10698")&&(userChk == null || userChk.length == 0)){
				sql = "select xm,xydm szbm,'6727' zdm,'学生' zmc from view_xsxxb where xh=? and xjztm = '有学籍'";
				userChk = dao.getOneRs(sql, new String[] { userName },
						new String[] {"xm", "szbm","zdm","zmc" });
			}
			if ((userChk != null) && (userChk.length == 4)) {
				userType = "stu";
			} else {
				errMsg = "尊敬的用户： 对不起，你未被授权访问此业务系统。<br/>请确认获得相应权限后，再重新请求该资源。";
				System.out.println("用户表中无该用户，无法登陆学工系统，请确认！！");
				zfssobean.getSession().setAttribute("errMsg", errMsg);// 错误类型
				//if (Globals.XXDM_ZJLG.equalsIgnoreCase(xxdm)) {
//					try {
//						zfssobean.getSession().setAttribute("yhInfo", errMsg);// 错误类型
//						zfssobean.getResponse().sendRedirect("/yhInfo.do");
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
				//}
				return false;
			}
		}
		
		if (!Base.isNull(userType)) {
			
			if ("就业网".equals(userChk[3])) {
				session.setAttribute("jyweb_userType", "admin");
			} else {
				session.setAttribute("jyweb_userType", userType);
			}
			System.out.println("就业网用户类型："+(String)session.getAttribute("jyweb_userType"));
			session.setAttribute("jyweb_userName", userName);
			session.setAttribute("jyweb_realName", userChk[0]);
			session.setAttribute("jyweb_userDep", userChk[1]);
			session.setAttribute("jyweb_dwshzt", "");
		
		}
		String xxmc = dao.getOneRs("select xxmc from xtszb", new String[] {},"xxmc");
		session.setAttribute("xxmc", xxmc);
		session.setAttribute("xxdm", xxdm);
		
		return true;
	}
	
	
	public Boolean setUserSession(ZfssoBean zfssobean) {
		
		SsoSetSession sso = new SsoSetSession();
		
//		System.out.println("调用方法：setXgUserSession");
		boolean xg = sso.setXgUserSession(zfssobean);
		
//		System.out.println("调用方法：setJywebUserSession");
		boolean jyweb = sso.setJywebUserSession(zfssobean);
		
//		System.out.println("准备返回：jyweb && xg");
		return xg && jyweb;

	}
	
}
