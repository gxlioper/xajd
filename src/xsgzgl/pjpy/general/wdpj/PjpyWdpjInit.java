package xsgzgl.pjpy.general.wdpj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xsgzgl.comm.BasicInit;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjLssbInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_通用_Init类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyWdpjInit extends BasicInit {

	/**
	 * 我的评奖_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initWdpj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);

		// 访问路径
		String path = "pjpy_general_wdpj.do";
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq };

		// ==================能否操作检测 begin==================
		String message = "";
		String operation = myService.getOperation("113");// 检测评奖申请流程
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}
		// =================能否操作检测 end ===================

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		List<HashMap<String, String>> lssbxmList = pjxmService.getLssbXmList();

		request.setAttribute("xmList", lssbxmList);

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
	}

	/**
	 * 学生申请_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initXssq(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		PjpyWdpjService wdpjService = new PjpyWdpjService();
		WdpjXssqInterface xssqService = myService.getWdpjXssqService(myForm);
		
		// 访问路径
		String path = "pjpy_general_wdpj.do";
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq };

		String message = "";
		String operation = myService.getOperation("113");// 检测评奖申请流程
		if ("later".equalsIgnoreCase(operation)) {
			message = "本步骤操作已经被相关用户提交，您不能再进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		} else if ("early".equalsIgnoreCase(operation)) {
			message = "本步骤操作的前一步尚未被相关用户提交，您不能对其进行相关操作，如有异议，请联系管理员！";
			request.setAttribute("operation", "no");
		}

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setMessage(message);
		// ====================初始化页面相关数据=====================
		// 项目性质列表
		List<HashMap<String, String>> xmxzList = wdpjService.getXmxzList();
		request.setAttribute("xmxzList", xmxzList);
	}

	/**
	 * 老师上报_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initLssb(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		PjszPjxmInterface pjxmService = myService.getPjszPjxmService(myForm);
		// 访问路径
		String path = "pjpy_general_wdpj.do";
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
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		// 班级代码
		String bjdm = request.getParameter("bjdm");

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "bjdm" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, bjdm };

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);

		List<HashMap<String, String>> lssbxmList = pjxmService.getLssbXmList();

		request.setAttribute("xmList", lssbxmList);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 学生结果_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXssqJgcx(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		WdpjXssqModel model = new WdpjXssqModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXssqInterface service = myService.getWdpjXssqService(myForm);
		WdpjJgcxInterface jgcxService = myService.getWdpjJgcxService(myForm);

		// 访问路径
		String path = "pjpy_general_wdpj.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 学号
		// String xh = request.getParameter("xh");
		// model.setXh(xh);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 查看信息
		String ckxx = request.getParameter("ckxx");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "ckxx" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, ckxx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================
		String xh = user.getUserName();

		List<HashMap<String, String>> xssqByZqInfo = service.getXssqByZq(model,
				user);

		List<String[]> xszcInfo = service.getXszcInfo(model, user);

		List<HashMap<String, String>> xssqInfo = jgcxService.getLspjList(xh);

		ArrayList<Object> zcxxByZqInfo = (ArrayList<Object>) service
				.getZcxxByZq(model, user);

		request.setAttribute("xssqByZqInfo", xssqByZqInfo);
		request.setAttribute("xssqInfo", xssqInfo);
		request.setAttribute("zcxxByZqInfo", zcxxByZqInfo);
		request.setAttribute("xszcInfo", xszcInfo);
	}

	/**
	 * 评奖项目审核_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXmsh(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		WdpjXmshModel model = new WdpjXmshModel();
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);

		String otherValue = request.getParameter("otherValue");
		String[] otherValues = null;
		if (!Base.isNull(otherValue)) {

			otherValues = otherValue.split("!!@@!!");
		}
		String tableName = "xg_view_pjpy_pjshdcb";
		// 访问路径
		String path = "pjpy_general_xmsh.do";
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
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		if (otherValues != null) {
			xmdm = otherValues[2];
		}
		model.setXmdm(xmdm);
		// 审批岗位
		String spgw = request.getParameter("spgw");
		if (otherValues != null) {
			spgw = otherValues[3];
		}
		model.setSpgw(spgw);
		// 其他字段
		// ture:本审核级别为第一级审核
		boolean bool = !service.checkFirstSpgw(model, user);

		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first",
				"firstSpgw" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw, "first",
				String.valueOf(bool) };

		rForm.setTableName(tableName);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================

		// 初始化项目列表(该方法增加审核开关和时间控制字段)
		List<HashMap<String, String>> cshXmList = service.getShxmList(model,
				user);
		request.setAttribute("cshXmList", cshXmList);

		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { pjxn });
		searchModel.setSearch_tj_xq(new String[] { pjxq });
		searchModel.setSearch_tj_shzt(new String[] { "未审核", "需重审" });

		myForm.setXmdm(xmdm);
		request.setAttribute("searchTj", searchModel);
	}

	/**
	 * 评奖项目审核【详细】_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initXmshDetail(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjXmshModel model = new WdpjXmshModel();

		// 访问路径
		String path = "pjpy_general_xmsh.do";
		// 学校代码
//		String xxdm = (String) session.getAttribute("xxdm");
//		myForm.setXxdm(xxdm);
//		// 学校拼音名称
//		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
//		myForm.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 审批岗位
		String spgw = request.getParameter("spgw");
		model.setSpgw(spgw);
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		String xh = request.getParameter("xh");
		model.setXh(new String[] { xh });
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		// ture:本审核级别为第一级审核
		boolean bool = !service.checkFirstSpgw(model, user);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw,
				String.valueOf(bool) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================
		// 评奖结果
		HashMap<String, Object> map = service.defaultWdpjXmsh(model, user);

		request.setAttribute("map", map);
	}

	/**
	 * 本次评奖_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initBcpj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();

		// 访问路径
		String path = "pjpy_general_bcpj.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);

		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		myForm.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		myForm.setPjxq(pjxq);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
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
	 * 本次评奖【详细】_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initBcpjDetail(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface xmshService = myService.getWdpjXmshService(myForm);
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);
		WdpjXmshModel model = new WdpjXmshModel();

		// 访问路径
		String path = "pjpy_general_xmsh.do";
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
		// 审批岗位
		String spgw = request.getParameter("spgw");
		model.setSpgw(spgw);
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		String xh = request.getParameter("xh");
		model.setXh(new String[] { xh });
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");

		String pkValue = request.getParameter("pkValue");
		String[] pkArr = pkValue.split("!!@@!!");

		model.setXmdm(pkArr[0]);
		model.setXh(new String[] { pkArr[1] });
		// ture:本审核级别为第一级审核
		boolean bool = !xmshService.checkFirstSpgw(model, user);

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "xmdm", "spgw", "first" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, xmdm, spgw,
				String.valueOf(bool) };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================
		// 评奖结果
		HashMap<String, Object> map = xmshService.defaultWdpjXmsh(model, user);

		request.setAttribute("map", map);
	}

	// ==================历史评奖 begin==============================

	/**
	 * 历史评奖_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initLspj(RequestForm rForm, PjpyGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);
		WdpjJgcxModel model = new WdpjJgcxModel();

		// 访问路径
		String path = "pjpy_general_lspj.do";
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
		// 视图
		String tableName = model.getSearch_table();
		// 表
		String realTable = model.getSave_table();
		// 用户身份
		String userStatus = user.getUserStatus();
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 指向
		String forward = request.getParameter("forward");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "userStatus", "forward" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, userStatus, forward };

		if (Base.isNull(forward)) {
			path = "general_pjpy.do?method=wdpjLspj";
		}
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);
	}

	/**
	 * 历史评奖【维护】_初始化数据
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initLspjUpdate(RequestForm rForm, PjpyGeneralForm myForm,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjJgcxInterface service = myService.getWdpjJgcxService(myForm);

		// 访问路径
		String path = "pjpy_general_bcpj.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================

		WdpjJgcxModel model = new WdpjJgcxModel();

		// 评奖结果
		HashMap<String, String> map = new HashMap<String, String>();
		if ("add".equalsIgnoreCase(doType)) {
			map.put("xq", "no");
			map.put("xmlx", "01");
		} else {
			// 主键
			String pkValue = request.getParameter("pkValue");
			model.setPkValue(new String[] { pkValue });
			map = service.getLspjMap(model);
		}

		request.setAttribute("rs", map);
	}

	// ==================历史评奖 end==============================

	/**
	 * 学生申请详细页面_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXssqDetail(RequestForm rForm, PjpyGeneralForm model,
			User user, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 访问路径
		String path = "pjpy_general_wdpj.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		model.setPjnd(pjnd);
		// 综测周期
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// 人员库
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		model.setPjzq(pjzq);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		model.setZcpm(zcpm);
		// 评奖学期名称
		String pjxqmc = jbszModel.getPjxqmc();

		// 参评组
		String cpz = jbszModel.getCpz();
		model.setCpz(cpz);
		// 操作类型
		String opera = request.getParameter("opera");

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setPath(path);

		// ====================初始化页面相关数据=====================
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXssqInterface service = myService.getWdpjXssqService(model);

		// 项目性质列表
		HashMap<String, Object> wdpjXssqInfo = (HashMap<String, Object>) service
				.defaultWdpjXssq(model, user);
		request.setAttribute("opera", opera);
		request.setAttribute("wdpjXssqInfo", wdpjXssqInfo);
	}

	/**
	 * 学生申请保存信息_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initSaveXssq(RequestForm rForm, PjpyGeneralForm model,
			BasicModel basicModel, User user, HttpServletRequest request)
			throws Exception {

		BasicService basicService = new BasicService();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		String xh = user.getUserName();

		HttpSession session = request.getSession();
		// 操作类型
		String opera = request.getParameter("opera");

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);

		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		HashMap<String, String> valueMap = new HashMap<String, String>();
		// 主键字段
		String[] save = null;

		String[] pkValue = null;
		if ("add".equalsIgnoreCase(opera)) {

			save = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh", "sqly",
					"sqsj", "sqjg" };

		} else if ("modi".equalsIgnoreCase(opera)) {

			save = new String[] { "sqly" };

			pkValue = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh" };

		}
		// --------------表名------------
		basicModel.setTableName("xg_pjpy_pjxmsqb");
		// --------------需要保存的值--------------------

		valueMap.putAll(basicService.getValueMap(request, save));

		valueMap.put("xh", xh);

		valueMap.put("pjxn", pjxn);

		valueMap.put("pjxq", pjxq);

		valueMap.put("pjnd", pjnd);

		valueMap.put("sqsj", GetTime.getNowTime2());

		basicModel.setValueMap(valueMap);

		basicModel.setPk(pkValue);

	}

	/**
	 * 学生申请保存信息_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initSaveLssb(RequestForm rForm, PjpyGeneralForm model,
			BasicModel basicModel, User user, HttpServletRequest request)
			throws Exception {

		BasicService basicService = new BasicService();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		String xh = request.getParameter("xh");

		String userName = user.getUserName();

		String userType = user.getUserType();

		if ("stu".equalsIgnoreCase(userType)) {
			xh = userName;
		}

		HttpSession session = request.getSession();
		// 操作类型
		String opera = request.getParameter("opera");

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);

		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		HashMap<String, String> valueMap = new HashMap<String, String>();
		// 主键字段
		String[] save = null;

		String[] pkValue = null;
		if ("add".equalsIgnoreCase(opera)) {

			save = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh", "sqly",
					"tjr", "sqsj" };

		} else if ("modi".equalsIgnoreCase(opera)) {

			save = new String[] { "sqly" };

			pkValue = new String[] { "xmdm", "pjxn", "pjxq", "pjnd", "xh" };

		}
		// --------------表名------------
		basicModel.setTableName("xg_pjpy_pjxmsqb");
		// --------------需要保存的值--------------------

		valueMap.putAll(basicService.getValueMap(request, save));
		
		valueMap.putAll(basicService.getValueMap(request, "xg_pjpy_pjxmsqb"));

		valueMap.put("xh", xh);

		valueMap.put("pjxn", pjxn);

		valueMap.put("pjxq", pjxq);

		valueMap.put("pjnd", pjnd);

		valueMap.put("sqsj", GetTime.getNowTime2());

		valueMap.put("tjr", user.getUserName());

		basicModel.setValueMap(valueMap);

		basicModel.setPk(pkValue);

	}

	/**
	 * 学生申请详细页面_初始化数据
	 * 
	 * @author qlj
	 * @throws Exception
	 * 
	 */
	public void initXmshDetail(RequestForm rForm, WdpjXmshModel xmshModel,
			PjpyGeneralForm model, User user, HttpServletRequest request)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		model.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = getXxmc(xxdm, model.getXxpymc());
		model.setXxpymc(xxpymc);

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		model.setPjxn(pjxn);
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		model.setPjxq(pjxq);
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		model.setPjnd(pjnd);
		// 评奖年度
		String pjxqmc = jbszModel.getPjxqmc();
		model.setPjxqmc(pjxqmc);

		// 综测周期
		String zczq = jbszModel.getZczq();
		model.setZczq(zczq);
		// 人员库
		String ryk = jbszModel.getRyk();
		model.setRyk(ryk);
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		model.setPjzq(pjzq);
		// 综测排名
		String zcpm = jbszModel.getZcpm();
		model.setZcpm(zcpm);
		// 参评组
		String cpz = jbszModel.getCpz();
		model.setCpz(cpz);
		// 操作类型
		String opera = request.getParameter("opera");

		// 其他字段
		String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc" };
		// 其他字段值
		String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		// ====================初始化页面相关数据=====================
		PjpyGeneralService myService = new PjpyGeneralService();
		WdpjXmshInterface service = myService.getWdpjXmshService(model);

		// 项目性质列表
		HashMap<String, Object> wdpjXssqInfo = (HashMap<String, Object>) service
				.defaultWdpjXmsh(xmshModel, user);
		request.setAttribute("opera", opera);
		request.setAttribute("wdpjXssqInfo", wdpjXssqInfo);
	}

	//
	// /**
	// * 评奖项目审核_初始化数据
	// *
	// * @param request
	// * @author 伟大的骆
	// * @throws Exception
	// *
	// */
	// public void initXmsb(RequestForm rForm, WdpjLssbModel model,
	// HttpServletRequest request) throws Exception {
	//
	// HttpSession session = request.getSession();
	//
	// PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
	//
	// // 功能模块
	// String gnmk = "pjpy";
	// // 系统字段设置
	// String menu = "pjxmsb";
	// // 操作类型（增加，修改，删除等）
	// String doType = request.getParameter("doType");
	// // 访问路径
	// String path = "pjpy_pjlc_xmsb.do";
	//
	// // 学校代码
	// String xxdm = (String) session.getAttribute("xxdm");
	// jbszModel.setXxdm(xxdm);
	// // 学校拼音名称
	// String xxpymc = getXxmc(xxdm, myForm.getXxpymc());
	// jbszModel.setXxpymc(xxpymc);
	//
	// // 评奖学年
	// String pjxn = jbszModel.getPjxn();
	// // 评奖学期
	// String pjxq = jbszModel.getPjxq();
	// // 评奖年度
	// String pjnd = jbszModel.getPjnd();
	// // 评奖学期名称
	// String pjxqmc = jbszModel.getPjxqmc();
	// // 项目代码
	// String xmdm = request.getParameter("xmdm");
	// xmdm = Base.isNull(xmdm) ? model.getXmdm() : xmdm;
	// // // 项目代码
	// String bjdm = model.getBjdm();
	// if (Base.isNull(model.getBjdm())) {
	// bjdm = request.getParameter("bjdm");
	// }
	// // model.setBjdm(bjdm);
	// // 项目设置model初始化
	// String pk = pjxn + pjxq + pjnd + xmdm;
	//
	// PjpyGeneralService myService = new PjpyGeneralService();
	// WdpjLssbInterface service = myService.getWdpjLssbService(jbszModel);
	//
	// // 表头
	// List<HashMap<String, String>> topTr = getDefaultValue(model);
	//
	// model.setPkValue(pk);
	// service.getXmszForXmdm(model);
	//
	// // 设置人数
	// String szrs = service.getXmszrs(model);
	// // 项目名称
	// String xmmc = model.getXmmc();
	// // 控制范围
	// String kzfw = model.getKzfw();
	//
	// // 其他字段
	// String[] qtzd = new String[] { "pjxn", "pjxq", "pjnd", "pjxqmc",
	// "xmdm", "bjdm", "szrs", "xmmc", "kzfw" };
	// // 其他字段值
	// String[] qtzdz = new String[] { pjxn, pjxq, pjnd, pjxqmc, xmdm, bjdm,
	// szrs, xmmc, kzfw };
	//
	// rForm.setQtzd(qtzd);
	// rForm.setQtzdz(qtzdz);
	//
	// rForm.setDoType(doType);
	// rForm.setGnmk(gnmk);
	// rForm.setMenu(menu);
	// rForm.setPath(path);
	// rForm.setTopTr(topTr);
	//
	// }

	private List<HashMap<String, String>> getDefaultValue(WdpjLssbModel model) {

		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("en", "xh");
		map.put("cn", "学号");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xm");
		map.put("cn", "姓名");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "xb");
		map.put("cn", "性别");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "bjmc");
		map.put("cn", "班级名称");
		topTr.add(map);

		map = new HashMap<String, String>();
		map.put("en", "zhf");
		map.put("cn", "综测分");
		topTr.add(map);

		// 综测排名
		String zcpm = "3";

		if ("3".equalsIgnoreCase(zcpm)) {
			map = new HashMap<String, String>();
			map.put("en", "zcfbjpm");
			map.put("cn", "班级排名");
			topTr.add(map);
		} else if ("2".equalsIgnoreCase(zcpm)) {
			map = new HashMap<String, String>();
			map.put("en", "zcfnjzypm");
			map.put("cn", "年级专业排名");
			topTr.add(map);
		}

		map = new HashMap<String, String>();
		map.put("en", "cz");
		map.put("cn", "操作");
		topTr.add(map);

		return topTr;
	}

}
