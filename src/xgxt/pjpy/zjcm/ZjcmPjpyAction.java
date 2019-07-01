package xgxt.pjpy.zjcm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.guizhdx.GuizhdxService;
import xgxt.pjpy.tyb.zhszcp.service.PjpyZhcpjxjService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xtwh.xtwhOther.XtwhOtherService;

import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 浙江传媒学院评奖评优-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */

public class ZjcmPjpyAction extends DispatchAction {

	/**
	 * 测评组管理
	 * 
	 * @return ActionForward
	 */
	public ActionForward cpxzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String tableName = "";
		String realTable = "zjcm_cpz";
		myForm.setTableName(realTable);

		// 学院用户登陆初始化学院代码
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			request.setAttribute("xydm", userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// 保存参评小组
		if ("save".equalsIgnoreCase(doType)) {

			String[] zwdm = myForm.getZwdm();

			if (zwdm != null && zwdm.length > 0) {// 保存
				result = service.saveCpzInfo(myForm);
			} else {// 撤销
				result = service.cxCpzInfo(myForm);
			}
			request.setAttribute("result", result);
		}

		// 初始化列表值
		service.setList(myForm, request, "cpxz");

		request.setAttribute("path", "pjpy_cpxz.do");
		request.setAttribute("userType", userType);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("cpxzManage");
	}

	/**
	 * 评奖评优_条件设置
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		XtwhOtherService xtwhService = new XtwhOtherService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();
		

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_pjpy_tjsz";
		String realTable = "zjcm_pjpy_tjsz";
		String lx = request.getParameter("lx");
		lx = service.getPjpyTjszLx(lx);//Base.isNull(lx) ? "jxj" : lx;
		// 学校代码
		String xxdm = Base.xxdm;
		// 评奖是否需要学期
		boolean needXq = service.getNeedXq(xxdm);
		// 若学校评奖无学期，将该字段置为‘no’
		if (!needXq) {
			myForm.setXq("no");
		}
		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		//---------2010.10.14 by lr------------
		//判断是否可设置条件
		if(!GlobalsVariable.XTDM_ADMIN.equalsIgnoreCase(session.getAttribute("userType").toString()) 
				&&!xtwhService.gnkgFlag(GlobalsVariable.GNDM_PJPY_TJSZ)
				&& service.checkKgflag()){
			String msg = "设置功能暂时不开放操作，仅限查询！";
			request.setAttribute("yhInfo", msg);
		}
		//---------end2010.10.9 by lr------------

		// 批量删除条件
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||lx||jxjdm||tjzd||bjlx";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delPjpy(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 保存条件设置
		if ("save".equalsIgnoreCase(doType)) {

			String bjlx = Base.isNull(myForm.getBjlx()) ? "nobj" : myForm
					.getBjlx();

			String[] onezd = new String[] { "xn", "xq", "lx", "jxjdm", "tjzd",
					"tjlx", "tjz", "bjlx" };

			String pk = "xn||xq||lx||jxjdm||tjzd||bjlx";
			String pkValue = myForm.getXn() + myForm.getXq() + myForm.getLx()
					+ myForm.getJxjdm() + myForm.getTjzd() + bjlx;

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			if ("有".equalsIgnoreCase(myForm.getTjlx())
					|| "无".equalsIgnoreCase(myForm.getTjlx())) {
				model.setTjz(model.getTjlx());
			}

			model.setBjlx(bjlx);

			result = service.savePjpy(saveForm, model, request);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			// model.setLx(null);
			String[] colList = new String[] { "pk", "tj" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "tjsz");
		if("zhcpjxj".equalsIgnoreCase(lx)){
			//获取综合测评奖学金代码列表信息
			GuizhdxService gzdxService = new GuizhdxService();
			gzdxService.setList(request, GlobalsVariable.PJPY_ZHCPJXJ);
		}

		request.setAttribute("lx", lx);
		request.setAttribute("tjlx", myForm.getTjlx());
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("needXq", needXq);
		request.setAttribute("path", "pjpy_tjsz.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		request.setAttribute("pages", service.getPjpyTjszCard());
		return mapping.findForward("tjszManage");
	}

	/**
	 * 评奖评优_奖学金兼得设置维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jdszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 参数设置 - 兼得设置";
		String doType = request.getParameter("doType");
		String realTable = "zjcm_jdsz";

		boolean result = false;

		doType = Base.isNull(doType) ? "add" : doType;

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		// 初始化评奖学年，学期, 类型，奖学金（荣誉称号）
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期
		String lx = request.getParameter("lx");// 类型
		String jxjdm = request.getParameter("jxjdm");// 奖学金代码
		String rychdm = request.getParameter("rychdm");// 荣誉称号代码

		myForm.setLx(lx);
		myForm.setJxjdm(jxjdm);
		myForm.setRychdm(rychdm);
		myForm.setXn(xn);
		myForm.setXq(xq);
		myForm.setTableName(realTable);

		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("lx", lx);
		rs.put("jxjdm", jxjdm);
		rs.put("rychdm", rychdm);

		// 获得奖学金以及荣誉称号的总数
		List<HashMap<String, String>> rychList = service.getPjpyList("rychdmb",
				"rychdm", "rychmc", "", "", "");// 荣誉称号列表
		List<HashMap<String, String>> jxjList = service.getPjpyList("jxjdmb",
				"jxjdm", "jxjmc", "", "", "");// 奖学金列表
		if (rychList != null && rychList.size() > 4) {
			request.setAttribute("rychtd", 4 - rychList.size() % 4);// 补空格数
			request.setAttribute("rychnum", rychList.size() - 1);
		}
		if (jxjList != null && jxjList.size() > 0) {
			if (jxjList.size() % 4 != 0) {
				request.setAttribute("jxjtd", 4 - jxjList.size() % 4);// 补空格数
			}
			request.setAttribute("jxjnum", jxjList.size() - 1);
		}

		BeanUtils.copyProperties(model, myForm);

		// 保存兼得设置
		if ("save".equalsIgnoreCase(doType)) {

			result = service.saveJdqk(model);

			request.setAttribute("result", result);

		}

		// 清空兼得设置
		if ("cancel".equalsIgnoreCase(doType)) {

			result = service.cxJdsz(model);

			request.setAttribute("result", result);

		}

		// 查看已经设置完毕的奖学金（荣誉称号）兼得情况
		if ("view".equalsIgnoreCase(doType) || result) {
			// 确认被比较项目为奖学金还是荣誉称号
			xn = model.getXn();
			xq = model.getXq();
			lx = model.getLx();
			jxjdm = ("rych".equalsIgnoreCase(lx)) ? model.getRychdm() : model
					.getJxjdm();

			model.setJxjdm(jxjdm);

			List<HashMap<String, String>> rsList = service.getJxjjdList(model);
			if (rsList != null && rsList.size() > 0) {
				rs = rsList.get(0);
				if ("rych".equalsIgnoreCase(lx)) {
					rs.put("rychdm", rs.get("jxjdm"));
				}
				request.setAttribute("rsList", rsList);
				request.setAttribute("jdnum", rsList.size());
			}
		}

		// 初始化列表值
		service.setList(myForm, request, "jdsz");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("lx", lx);

		return mapping.findForward("jdszManage");
	}

	/**
	 * 评奖评优_智育分管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zyfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_zyf";
		String realTable = "zjcm_xfjdb";
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期

		// 初始化用户权限
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();
		String userName = session.getAttribute("userName").toString();
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);
		myForm.setUserName(userName);

		// 初始化评奖学年学期
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 初始化学院(访问者为学院)
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 计算评奖学年学期的智育分
		if ("js".equalsIgnoreCase(doType)) {
			String message = service.jsXfJd(model);
			result = "计算成功".equalsIgnoreCase(message) ? true : false;
			request.setAttribute("message", message);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "xn", "xqmc", "xh", "xm", "nj",
					"xymc", "zymc", "bjmc", "pycc", "xfjd", "zyf" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "zyf");

		request.setAttribute("path", "pjpy_zyf.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zyfManage");
	}

	/**
	 * 评奖评优_综合分管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zhfManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_zhf";
		String realTable = "zjcm_zhf";
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期

		// 初始化用户权限
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();
		String userName = session.getAttribute("userName").toString();
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);
		myForm.setUserName(userName);

		// 初始化评奖学年学期
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 初始化学院(访问者为学院)
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		BeanUtils.copyProperties(model, myForm);

		// ======================登陆者权限控制======================================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		// 学生用户
		if ("stu".equalsIgnoreCase(userType)) {
			if (service.isCpz(model)) {// 判断是否参评小组

				// 初始化访问者相关信息
				String pk = "xh";
				String pkValue = userName;
				String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

				HashMap<String, String> map = service.getPjpyInfo(
						"view_xsjbxx", pk, pkValue, colList);

				myForm.setLx("jxj");
				myForm.setNj(map.get("nj"));
				myForm.setXydm(map.get("xydm"));
				myForm.setZydm(map.get("zydm"));
				myForm.setBjdm(map.get("bjdm"));

			} else {
				String yhInfo = "综合分操作只能由参评小组进行操作，请确认！";
				request.setAttribute("yhInfo", yhInfo);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		// ======================end=====================================

		// ======================开关控制======================================
		if (!"xx".equalsIgnoreCase(userType)
				&& !"admin".equalsIgnoreCase(userType)) {
			// 判断是否有设置综合分录入开关
			String pk = "xn||xq||cpxy";
			String pkValue = xn + xq + userDep;
			String zhfkg = service
					.getOneValue("zjcm_cpz", "zhfkg", pk, pkValue);
			if (!"开".equalsIgnoreCase(zhfkg)) {
				String yhInfo = "综合分录入开关关闭或者未维护，请确认！";
				request.setAttribute("yhInfo", yhInfo);
				return new ActionForward("/yhInfo.do", false);
			}
		}
		// ======================end=====================================

		// 初始化各分值比例,以及提示
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = service.getZhfBl(model, bl);

		StringBuffer ts = new StringBuffer();
		if (map != null && map.size() > 0) {
			ts.append("注：");
			ts.append("德育分比例：" + map.get("dyfbl") + "%；");
			ts.append("智育分比例：" + map.get("zyfbl") + "%；");
			ts.append("体育分比例：" + map.get("tyfbl") + "%；");
			ts.append("能力分比例：" + map.get("nlfbl") + "%。");
		} else {
			ts.append("各分值比例还未设置。");
			request.setAttribute("nobl", "yes");
		}

		request.setAttribute("dyfbl", map.get("dyfbl"));
		request.setAttribute("zyfbl", map.get("zyfbl"));
		request.setAttribute("tyfbl", map.get("tyfbl"));
		request.setAttribute("nlfbl", map.get("nlfbl"));
		request.setAttribute("ts", ts.toString());

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 批量保存德育分，体育分，能力分
		if ("save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh", "dyf", "tyf", "nlf" };
			String[] onezd = new String[] { "xn", "xq" };
			String pk = "xn||xq||pjxh";
			String[] pjxh = myForm.getPjxh();
			String[] pkValue = new String[pjxh.length];

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i];
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// 计算评奖学年学期的综合分
		if ("js".equalsIgnoreCase(doType)) {
			result = service.jsZhf(model);
			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			topTr = service.getTopTr("zhf");
			rs = service.getZhfList(model);
		}

		// 初始化列表值
		service.setList(myForm, request, "zhf");

		request.setAttribute("path", "pjpy_zhf.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("zhfManage");
	}

	/**
	 * 评奖评优_综合分维护(比例)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward zhfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 综合分 - 比例设置";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期
		String pk = "xn||xq";
		String pkValue = xn + xq;
		String realTable = "zjcm_zhf_bl";
		boolean result = false;

		doType = Base.isNull(doType) ? "add" : doType;

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 保存比例设置
		if ("save".equalsIgnoreCase(doType)) {
			String[] onezd = new String[] { "xn", "xq", "dyfbl", "zyfbl",
					"tyfbl", "nlfbl" };
			pk = "xn||xq";
			pkValue = myForm.getXn() + myForm.getXq();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkValue });

			result = service.savePjpy(saveForm, model, request);
			request.setAttribute("result", result);
		}

		if ("add".equalsIgnoreCase(doType) || result) {
			String[] colList = new String[] { "xn", "xq", "dyfbl", "zyfbl",
					"tyfbl", "nlfbl" };
			rs = service.getPjpyInfo(realTable, pk, pkValue, colList);
			rs.put("xn", xn);
			rs.put("xq", xq);
		}

		// 初始化列表值
		service.setList(myForm, request, "zhf");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("zhfUpdate");
	}

	/**
	 * 评奖评优_报表统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String title = "评奖评优 - 统计分析 - 报表统计";
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String doType = request.getParameter("doType");
		String lx = myForm.getLx();// 报表类型

		// 初始化数据，本学年，学期
		String xn = Base.isNull(myForm.getXn()) ? Base.getJxjsqxn() : myForm
				.getXn();// 评奖学年
		String xq = Base.isNull(myForm.getXq()) ? Base.getJxjsqxq() : myForm
				.getXq();// 评奖学期
		myForm.setXn(xn);
		myForm.setXq(xq);

		// /学院用户访问
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		// 初始化列表值
		service.setList(myForm, request, "bbtj");

		BeanUtils.copyProperties(model, myForm);

		// 初始化各分值比例,以及提示
		String[] bl = new String[] { "dyfbl", "zyfbl", "tyfbl", "nlfbl" };
		HashMap<String, String> map = service.getZhfBl(model, bl);

		StringBuffer msg = new StringBuffer();
		if (map != null && map.size() > 0) {
			// ts.append("注：");
			// ts.append("德育分比例：" + map.get("dyfbl") + "%；");
			// ts.append("智育分比例：" + map.get("zyfbl") + "%；");
			// ts.append("体育分比例：" + map.get("tyfbl") + "%；");
			// ts.append("能力分比例：" + map.get("nlfbl") + "%。");
		} else {
			msg.append("综合分各分值比例还未设置。");
			request.setAttribute("msg", msg);
		}

		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			if ("zhbnoyj".equalsIgnoreCase(lx) || "zhbyj".equalsIgnoreCase(lx)) {

				service.printZhszcpb(model, response.getOutputStream());

			} else if ("zhblxy".equalsIgnoreCase(lx)) {// 学院综合测评比例表

				service.printZhszblb(model, response.getOutputStream());

			} else if ("jxjjetj".equalsIgnoreCase(lx)) {// 奖学金金额统计表

				service.printJxjjetjb(model, response.getOutputStream());

			} else if ("jxjhj".equalsIgnoreCase(lx)) {// 奖学金获奖名单打印

				if ("xy".equalsIgnoreCase(userType)) {
					model.setXydm(userDep);
				}
				service.printJxjhjmd(model, response.getOutputStream());

			} else if ("rychhj".equalsIgnoreCase(lx)) {// 荣誉称号获奖名单打印

				if ("xy".equalsIgnoreCase(userType)) {
					model.setXydm(userDep);
				}

				service.printRychhjmd(model, response.getOutputStream());

			} else if ("jxjjehz".equalsIgnoreCase(lx)) {// 奖学金金额汇总

				service.exportJxjjeHzData(model, response.getOutputStream());
			}
			return null;
		}

		request.setAttribute("title", title);
		request.setAttribute("yhlxList", service.queryYhlxList());
		request.setAttribute("userType", userType);

		return mapping.findForward("bbtjManage");
	}

	/**
	 * 评奖评优_文理科管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wlkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_wlk";
		String realTable = "zjcm_wlkb";

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "nj", "xymc", "zymc", "bjmc",
					"lxmc" };
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "wlk");

		request.setAttribute("path", "zjcm_pjpy_wlk.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("wlkManage");
	}

	/**
	 * 评奖评优_文理科维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wlkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_wlkb";
		String fs = request.getParameter("fs");

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 保存文理科
		if ("save".equalsIgnoreCase(doType)) {

			if (!"bj".equalsIgnoreCase(fs)) {
				result = service.saveWlk(model);
			} else {
				String[] arrzd = new String[] { "wlkbjdm" };
				String[] onezd = new String[] { "lx" };
				String pk = "wlkbjdm";
				String[] pkValue = myForm.getWlkbjdm();

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				result = service.savePjpy(saveForm, model);

				request.setAttribute("result", result);

			}
			request.setAttribute("result", result);
		}

		// 初始化列表值
		service.setList(myForm, request, "wlk");

		request.setAttribute("path", "zjcm_pjpy_wlk.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("wlkUpdate");
	}

	/**
	 * 评奖评优_奖学金(校内)申报
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();

		// 奖学金代码
		String jxjdm = myForm.getJxjdm();

		// 初始化评奖学年学期
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 判断访问者是否测评组用户
		model.setUserName(userName);
		model.setXn(xn);
		model.setXq(xq);

		if (!service.isCpz(model)) {
			String msg = "本模块只能由测评小组访问，请确认！";
			request.setAttribute("msg", msg);
		} else {

			// 初始化访问者相关信息
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setLx("jxj");
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));

			// 判断是否有设置综合分录入开关
			pk = "xn||xq||cpxy";
			pkValue = xn + xq + myForm.getXydm();
			String jxjkg = service
					.getOneValue("zjcm_cpz", "jxjkg", pk, pkValue);
			if ("关".equalsIgnoreCase(jxjkg)) {
				String msg = "奖学金申报开关关闭，请确认！";
				request.setAttribute("msg", msg);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 申报奖学金
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh" };
			String[] onezd = new String[] { "xn", "xq", "jxjdm" };
			String pk = "xn||xq||pjxh||jxjdm";
			String[] pjxh = myForm.getCheckVal();
			String[] pkValue = new String[pjxh.length];

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i] + jxjdm;
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setPjxh(pjxh);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			model.setPjxh(null);
			topTr = service.getTopTr("jxjxn");
			rs = service.getJxjSqXnList(model);
			// 有资格申报者学号
			String[] sbzxh = service.getJxjsbz(rs);
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// 初始化列表值
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "pjpy_jxjsq_xn.do");
		request.setAttribute("searchJxjdm", jxjdm);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jxjsqXnManage");
	}

	/**
	 * 评奖评优_奖学金(校内)申报(单个)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXnUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 校内奖学金 - 申报";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期
		String pkValue = request.getParameter("pk");// 主键
		String jxjdm = request.getParameter("jxjdm");// 奖学金代码
		String sel = request.getParameter("sel");// 是否勾选
		String sbznum = request.getParameter("sbznum");// 有资格申报该奖学金的人数
		String lx = request.getParameter("lx");// 上一条（下一条）
		String nextXh = "";// 次学号
		String swbj = "";// 收尾标记

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// 获得申请者相关信息
		String xh = Base.isNull(pkValue) ? myForm.getXh() : pkValue;
		jxjdm = Base.isNull(jxjdm) ? myForm.getJxjdm() : jxjdm;

		// 有资格申报者
		String[] sbzxh = null;
		if (!Base.isNull(sbznum)) {
			sbzxh = new String[Integer.parseInt(sbznum)];
			for (int i = 0; i < Integer.parseInt(sbznum); i++) {
				sbzxh[i] = request.getParameter("sbzxh" + String.valueOf(i));
			}
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// 学号顺序
		int xhnum = Base.isNull(request.getParameter("xhnum")) ? 0 : Integer
				.parseInt(request.getParameter("xhnum"));

		if (sbzxh != null && sbzxh.length > 0) {

			// 确认所打开的学号在所有资格学生中的顺序，以及其是否首尾
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(xh)) {
					xhnum = i;
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}

			// 判断是按的上一条还是下一条
			if ("before".equalsIgnoreCase(lx)) {
				xhnum = xhnum - 1;
				nextXh = sbzxh[xhnum];
			} else if ("next".equalsIgnoreCase(lx)) {
				xhnum = xhnum + 1;
				nextXh = sbzxh[xhnum];
			}

			// 判断上一条（下一条）在所有资格学生中的顺序，以及其是否首尾
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(nextXh)) {
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}
		}

		// 获得待展现学号
		xh = Base.isNull(nextXh) ? xh : nextXh;

		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setJxjdm(jxjdm);

		// 获得待展现者相关信息
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// 初始化列表值
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("xhnum", xhnum);
		request.setAttribute("swbj", swbj);
		request.setAttribute("sel", sel);
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("jxjsqXnUpdate");
	}

	/**
	 * 评奖评优_奖学金(校外)申报
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		// 初始化评奖学年学期
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 申报奖学金
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String xwjxjdm = myForm.getXwjxjdm();// 所申报奖学金代码
			String[] checkVal = myForm.getCheckVal();// 勾选者

			if (checkVal != null && checkVal.length > 0) {

				String[] arrzd = new String[] { "pjxh" };
				String[] onezd = new String[] { "xn", "xq", "jxjdm" };
				String pk = "pjxh||xn||xq||jxjdm";
				String[] pkValue_Temp = new String[checkVal.length];
				String[] pjxh_Temp = new String[checkVal.length];// 申报者学号

				// 构建主键
				int n = 0;
				for (int i = 0; i < checkVal.length; i++) {
					boolean flag = true;
					for (int j = i + 1; j < checkVal.length; j++) {
						if (checkVal[i].equalsIgnoreCase(checkVal[j])) {
							if (flag) {
								pjxh_Temp[n] = checkVal[i];
								pkValue_Temp[n] = checkVal[i] + xn + xq
										+ xwjxjdm;
								n++;
								flag = false;
								checkVal[j] = "";
							}
						}
					}
					if (flag && !Base.isNull(checkVal[i])) {
						pjxh_Temp[n] = checkVal[i];
						pkValue_Temp[n] = checkVal[i] + xn + xq + xwjxjdm;
						n++;
					}
				}

				String[] pkValue = new String[n];
				String[] pjxh = new String[n];// 申报者学号

				for (int i = 0; i < n; i++) {
					pjxh[i] = pjxh_Temp[i];
					pkValue[i] = pkValue_Temp[i];
				}

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setArrzd(arrzd);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(pkValue);

				// 设置保存数据的值
				model.setPjxh(pjxh);
				model.setXn(xn);
				model.setXq(xq);
				model.setJxjdm(xwjxjdm);

				// 执行保存操作
				result = service.savePjpy(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			model.setXxsh("通过");

			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"nj", "xymc", "zymc", "bjmc", "pycc", "bjlx", "yycj",
					"jxjmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "pjpy_jxjsq_xw.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("jxjsqXwManage");
	}

	/**
	 * 评奖评优_奖学金(校外)申报(单个)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjsqXwUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 校外奖学金 - 申报";
		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String pk = "pjxh||xn||xq||jxjdm";// 主键
		String pkValue = request.getParameter("pk");// 主键值

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> otherRs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 保存申报
		if ("save".equalsIgnoreCase(doType)) {
			boolean result = service.saveJxjsb(model, request);
			request.setAttribute("result", result);
		}

		// 申报者详细信息
		if ("sb".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			pk = "pk";
			String[] colList = new String[] { "xn", "xq", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "jxjmc", "fdyyj", "xyyj",
					"xxyj", "bz", "yycj" };
			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

			model.setXh(rs.get("xh"));
			model.setXn(rs.get("xn"));
			model.setXq(rs.get("xq"));
			// 获得待展现者相关信息
			otherRs = service.getJxjsqInfo(model);
		}

		// 初始化列表值
		service.setList(myForm, request, "jxjxw");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("otherRs", otherRs);

		return mapping.findForward("jxjsqXwUpdate");
	}

	/**
	 * 评奖评优_国家奖学金申报
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gjjxjSbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		// 初始化评奖学年学期
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 获得国家奖学金代码
		String jxjdm = service.getOneValue("jxjdmb", "jxjdm", "jxjmc", "国家奖学金");

		// 判断国家奖学金代码是否维护
		if (Base.isNull(jxjdm)) {
			String msg = "国家奖学金代码未维护，请确认！";
			request.setAttribute("msg", msg);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {

			model.setXxsh("通过");

			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"nj", "xymc", "zymc", "bjmc", "jxjmc" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "jxjxn");

		request.setAttribute("path", "zjcm_pjpy_gjjxjsb.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("gjjxjSbManage");
	}

	/**
	 * 评奖评优_国家奖学金申报（单个）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gjjxjSbUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 过奖奖学金 - 申报";
		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_jxjsq";
		String realTable = "zjcm_jxjsq";
		String pk = "pjxh||xn||xq||jxjdm";// 主键
		String pkValue = request.getParameter("pk");// 主键值
		String xh = request.getParameter("xh");// 学号
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		BeanUtils.copyProperties(model, myForm);

		// 保存申报
		if ("save".equalsIgnoreCase(doType)) {
			// 获得国家奖学金代码
			String jxjdm = service.getOneValue("jxjdmb", "jxjdm", "jxjmc",
					"国家奖学金");
			model.setJxjdm(jxjdm);
			boolean result = service.saveJxjsb(model, request);

			request.setAttribute("result", result);
		}

		// 学生基本信息
		xh = Base.isNull(xh) ? service.getOneValue(realTable, "pjxh", pk,
				pkValue) : xh;

		HashMap<String, String> stuInfo = service.getStuInfo(xh);
		stuInfo.put("xn", xn);
		stuInfo.put("xq", xq);

		// 获得待展现者相关信息
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setNj(stuInfo.get("nj"));
		model.setXydm(stuInfo.get("xydm"));
		model.setZydm(stuInfo.get("zydm"));
		model.setBjdm(stuInfo.get("bjdm"));

		HashMap<String, String> otherInfo = service.getGjjxjNeedInfo(model);

		// 初始化列表值
		service.setList(myForm, request, "");

		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("otherInfo", otherInfo);

		return mapping.findForward("gjjxjSbUpdate");
	}

	/**
	 * 评奖评优_国家奖学金打印
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String xh = myForm.getXh();
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期

		HashMap<String, String> stuInfo = service.getStuInfo(xh);

		stuInfo.put("xxmc", StandardOperation.getXxmc());
		stuInfo.put("xn", xn);
		stuInfo.put("xq", xq);

		// 拆分身份证号
		String sfzh = Base.chgNull(stuInfo.get("sfzh"), "", 1);
		String[] sF = new String[18];

		for (int i = 0; i < sfzh.length(); i++) {
			sF[i] = sfzh.substring(i, i + 1);
		}
		for (int i = 1; i < 19; i++) {
			stuInfo.put("sfzh" + i, sF[i - 1]);
		}

		// 获得待展现者相关信息
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setNj(stuInfo.get("nj"));
		model.setXydm(stuInfo.get("xydm"));
		model.setZydm(stuInfo.get("zydm"));
		model.setBjdm(stuInfo.get("bjdm"));

		HashMap<String, String> otherInfo = service.getGjjxjNeedInfo(model);

		request.setAttribute("rs", stuInfo);
		request.setAttribute("otherInfo", otherInfo);

		return mapping.findForward("gjjxjPrint");
	}

	/**
	 * 评奖评优_荣誉称号申报
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychsqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "zjcm_rychsqb";
		String userName = session.getAttribute("userName").toString();

		// 初始化评奖学年学期
		String xn = Base.getJxjsqxn();
		String xq = Base.getJxjsqxq();
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 判断访问者是否测评组用户
		model.setUserName(userName);
		model.setXn(xn);
		model.setXq(xq);

		if (!service.isCpz(model)) {
			String msg = "本模块只能由测评小组访问，请确认！";
			request.setAttribute("msg", msg);
		} else {

			// 初始化访问者相关信息
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setLx("rych");
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 申报荣誉称号
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {

			String[] arrzd = new String[] { "pjxh" };
			String[] onezd = new String[] { "xn", "xq", "rychdm" };
			String pk = "xn||xq||pjxh||rychdm";
			String[] pjxh = myForm.getCheckVal();
			String[] pkValue = new String[pjxh.length];
			String rychdm = myForm.getRychdm();

			for (int i = 0; i < pjxh.length; i++) {
				pkValue[i] = xn + xq + pjxh[i] + rychdm;
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setPjxh(pjxh);

			result = service.savePjpy(saveForm, model);

			request.setAttribute("result", result);
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			model.setPjxh(null);
			topTr = service.getTopTr("jxjxn");
			rs = service.getJxjSqXnList(model);
			// 有资格申报者学号
			String[] sbzxh = service.getJxjsbz(rs);
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// 初始化列表值
		service.setList(myForm, request, "rych");

		request.setAttribute("path", "pjpy_rychsq.do");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("rychsqManage");
	}

	/**
	 * 评奖评优_荣誉称号申报(单个)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychsqUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "评奖评优 - 荣誉称号 - 申报";
		String doType = request.getParameter("doType");
		String xn = Base.getJxjsqxn();// 评奖学年
		String xq = Base.getJxjsqxq();// 评奖学期
		String pkValue = request.getParameter("pk");// 主键
		String rychdm = request.getParameter("rychdm");// 荣誉称号代码
		String sel = request.getParameter("sel");// 是否勾选
		String sbznum = request.getParameter("sbznum");// 有资格申报该奖学金的人数
		String lx = request.getParameter("lx");// 上一条（下一条）
		String nextXh = "";// 次学号
		String swbj = "";// 收尾标记

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// 获得申请者相关信息
		String xh = Base.isNull(pkValue) ? myForm.getXh() : pkValue;
		rychdm = Base.isNull(rychdm) ? myForm.getRychdm() : rychdm;

		// 有资格申报者
		String[] sbzxh = null;
		if (!Base.isNull(sbznum)) {
			sbzxh = new String[Integer.parseInt(sbznum)];
			for (int i = 0; i < Integer.parseInt(sbznum); i++) {
				sbzxh[i] = request.getParameter("sbzxh" + String.valueOf(i));
			}
			if (sbzxh != null && sbzxh.length > 0) {
				request.setAttribute("sbzxh", sbzxh);
				request.setAttribute("sbznum", sbzxh.length);
			}
		}

		// 学号顺序
		int xhnum = Base.isNull(request.getParameter("xhnum")) ? 0 : Integer
				.parseInt(request.getParameter("xhnum"));

		if (sbzxh != null && sbzxh.length > 0) {

			// 确认所打开的学号在所有资格学生中的顺序，以及其是否首尾
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(xh)) {
					xhnum = i;
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}

			// 判断是按的上一条还是下一条
			if ("before".equalsIgnoreCase(lx)) {
				xhnum = xhnum - 1;
				nextXh = sbzxh[xhnum];
			} else if ("next".equalsIgnoreCase(lx)) {
				xhnum = xhnum + 1;
				nextXh = sbzxh[xhnum];
			}

			// 判断上一条（下一条）在所有资格学生中的顺序，以及其是否首尾
			for (int i = 0; i < sbzxh.length; i++) {
				if (sbzxh[i].equalsIgnoreCase(nextXh)) {
					if (i == 0) {
						swbj = "theFirst";
					} else if (i == sbzxh.length - 1) {
						swbj = "theLast";
					} else {
						swbj = "";
					}
					break;
				}
			}
		}

		// 获得待展现学号
		xh = Base.isNull(nextXh) ? xh : nextXh;

		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setRychdm(rychdm);

		// 获得待展现者相关信息
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// 初始化列表值
		service.setList(myForm, request, "rych");

		request.setAttribute("xhnum", xhnum);
		request.setAttribute("swbj", swbj);
		request.setAttribute("sel", sel);
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("rychsqUpdate");
	}

	/**
	 * 评奖评优_奖学金申请结果
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jxjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setLx("jxj");
		return sbjgManage(mapping, myForm, request, response);
	}

	/**
	 * 评奖评优_荣誉称号申请结果
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rychjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setLx("rych");
		return sbjgManage(mapping, myForm, request, response);
	}

	/**
	 * 评奖评优_申报结果
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sbjgManage(ActionMapping mapping, PjpyTyForm myForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		// 判断奖学金还是荣誉称号
		String lx = request.getParameter("lx");
		lx = Base.isNull(lx) ? myForm.getLx() : lx;
		String path = "jxj".equalsIgnoreCase(lx) ? "pjpy_jxjjg.do"
				: "pjpy_rychjg.do";
		String realTable = "jxj".equalsIgnoreCase(lx) ? "zjcm_jxjsq"
				: "zjcm_rychsqb";
		String tableName = "jxj".equalsIgnoreCase(lx) ? "view_zjcm_jxjsq"
				: "view_zjcm_rychsq";
		// 初始化用户信息
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String fdyQx = session.getAttribute("fdyQx").toString();
		String bzrQx = session.getAttribute("bzrQx").toString();

		if ("xy".equalsIgnoreCase(userType)) {// 学院用户访问
			myForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {// 学生用户访问
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "nj", "xydm", "zydm", "bjdm" };

			HashMap<String, String> map = service.getPjpyInfo("view_xsjbxx",
					pk, pkValue, colList);

			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
		}

		BeanUtils.copyProperties(model, myForm);

		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		// 删除奖学金(荣誉称号)
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {

			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "jxj".equalsIgnoreCase(lx) ? "pjxh||xn||xq||jxjdm"
						: "pjxh||xn||xq||rychdm";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delPjpy(saveForm, model);
				request.setAttribute("result", result);
			}

		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			model.setLx(null);
			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"xymc", "zymc", "bjmc", "jxjmc", "fdysh", "xysh", "xxsh" };
			if ("rych".equalsIgnoreCase(lx)) {
				colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
						"xymc", "zymc", "bjmc", "rychmc", "xysh", "xxsh" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}

		// 初始化列表值
		service.setList(myForm, request, "sbjg");

		request.setAttribute("path", path);
		request.setAttribute("lx", lx);
		request.setAttribute("userType", userType);
		request.setAttribute("fdyQx", fdyQx);
		request.setAttribute("bzrQx", bzrQx);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);

		return mapping.findForward("sbjgManage");
	}

	/**
	 * 评奖评优_奖学金(校内)申报(单个)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sbjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String pkValue = request.getParameter("pk");// 主键
		String lx = request.getParameter("doType");// 奖学金,荣誉称号
		String tableName = ("jxj".equalsIgnoreCase(lx)) ? "view_zjcm_jxjsq"
				: "view_zjcm_rychsq";
		String title = ("jxj".equalsIgnoreCase(lx)) ? "评奖评优 - 奖学金 - 申报结果"
				: "评奖评优 - 荣誉称号 - 申报结果";

		PjpyTyForm myForm = (PjpyTyForm) form;
		ZjcmPjpyService service = new ZjcmPjpyService();
		ZjcmPjpyModel model = new ZjcmPjpyModel();

		String[] colList = ("jxj".equalsIgnoreCase(lx)) ? new String[] { "xh",
				"xn", "xq", "jxjdm", "fdyyj", "xyyj", "xxyj", "bz" }
				: new String[] { "xh", "xn", "xq", "rychdm", "fdyyj", "xyyj",
						"xxyj", "bz" };

		HashMap<String, String> stuInfo = service.getPjpyInfo(tableName, "pk",
				pkValue, colList);

		model.setXh(stuInfo.get("xh"));
		model.setXn(stuInfo.get("xn"));
		model.setXq(stuInfo.get("xq"));
		model.setJxjdm(stuInfo.get("jxjdm"));
		model.setRychdm(stuInfo.get("rychdm"));

		// 获得待展现者相关信息
		HashMap<String, String> rs = service.getJxjsqInfo(model);

		// 初始化列表值
		service.setList(myForm, request, "sbjg");

		request.setAttribute("lx", lx);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("stuInfo", stuInfo);

		return mapping.findForward("sbjgUpdate");
	}
}
