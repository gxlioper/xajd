package xsgzgl.pjpy.general.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyZhcpInit extends BasicInit {

	/**
	 * 综测测评维护_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initZhcpMaintain(RequestForm rForm, PjpyGeneralForm model,
			User user, HttpServletRequest request) {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 访问路径
		String path = "pjpy_general_zhcp_maintain.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = GlobalsValue.getXxpymc(xxdm);
		model.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// 综测周期
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// 人员库
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户名
		String userName = user.getUserName();
		// 用户名
		String userType = user.getUserType();
		// 用户类型
		String yhlx = model.getYhlx();
		if (Fdypd.isFdy(userName)) {// 是否辅导员
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// 是否班主任
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校
			yhlx = "admin";
		} else {// 其他
			yhlx = user.getUserType();
		}
		model.setYhlx(yhlx);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 综合测评_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initZhcp(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);

		// 访问路径
		String path = "pjpy_general_zhcp_maintain.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 页面跳转
		String forward = request.getParameter("forward");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "forward" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, forward };

		// ====================初始化页面相关数据=====================

		// 初始化项目列表
		List<HashMap<String, String>> cshXmList = service.getCshXmList(user);

		if (cshXmList != null && cshXmList.size() == 1) {

			HashMap<String, String> cshXmMap = cshXmList.get(0);

			qtzd = new String[] { "pjxn", "pjxq", "forward", "xmdm", "xmmc",
					"lyb", "xmjb" };

			qtzdz = new String[] { pjxn, pjxq, forward, cshXmMap.get("xmdm"),
					cshXmMap.get("xmmc"), cshXmMap.get("lyb"),
					cshXmMap.get("xmjb") };
			cshXmList = new ArrayList<HashMap<String, String>>();
		}

		request.setAttribute("cshXmList", cshXmList);

		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { pjxn });
		searchModel.setSearch_tj_xq(new String[] { pjxq });

		request.setAttribute("searchTj", searchModel);

		// ==================能否操作检测 begin==================
		String message = "";
		String operation = myService.getOperation("112");
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}
		// =================能否操作检测 end ===================

		// ====================初始化页面相关数据 end=====================

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * 综测测评结果_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * @throws Exception 
	 * 
	 */
	public void initZhcpResult(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// 访问路径
		String path = "pjpy_general_zhcp_result.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		myForm.setPjnd(pjnd);
		String zczq = pjxn + "luojw" + pjxq + "luojw" + pjnd;
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		// 页面跳转
		String forward = request.getParameter("forward");
		// 人员库
		String ryk = jbszModel.getRyk();
		myForm.setRyk(ryk);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户名
		String userName = user.getUserName();
		// 用户名
		String userType = user.getUserType();
		// 用户类型
		String yhlx = myForm.getYhlx();
		if (Fdypd.isFdy(userName)) {// 是否辅导员
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// 是否班主任
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校
			yhlx = "admin";
		} else {// 其他
			yhlx = user.getUserType();
		}
		myForm.setYhlx(yhlx);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjzq", "zczq",
				"forward" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjzq, zczq, forward };
		
		// ==================能否操作检测 begin==================
		String message = "";
		String operation = myService.getOperation("116");// 综合分结果
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}else if("early".equalsIgnoreCase(operation)){
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}
		// =================能否操作检测 end ===================

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * 综测测评结果_初始化数据
	 * 
	 * @param request
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initKindChoose(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// 访问路径
		String path = "pjpy_general_zhcp_result.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 综测周期
		String zczq = jbszModel.getZczq();
		myForm.setZczq(zczq);
		// 人员库
		String ryk = jbszModel.getRyk();
		myForm.setRyk(ryk);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 用户名
		String userName = user.getUserName();
		// 用户名
		String userType = user.getUserType();
		// 用户类型
		String yhlx = myForm.getYhlx();
		if (Fdypd.isFdy(userName)) {// 是否辅导员
			yhlx = "fdy";
		} else if (Fdypd.isBzr(userName, "")) {// 是否班主任
			yhlx = "bzr";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校
			yhlx = "admin";
		} else {// 其他
			yhlx = user.getUserType();
		}
		myForm.setYhlx(yhlx);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq };

		// ====================初始化页面相关数据=====================
		request.setAttribute("checkKind", service.getCheckKind(myForm, user));

		request.setAttribute("topTr", service.getKindChoose(myForm, user));

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

}
