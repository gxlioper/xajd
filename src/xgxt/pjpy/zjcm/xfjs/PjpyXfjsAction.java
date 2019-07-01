package xgxt.pjpy.zjcm.xfjs;

import java.lang.reflect.InvocationTargetException;
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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.Fdypd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyXfjsAction extends CommonAction {
	PjpyXfjsService service = new PjpyXfjsService();

	/**
	 * 学生抽查情况维护首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		boolean xxOper = false;
		User user =  getUser(request);;
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		xxOper = "xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType) ? true : false;

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "评奖评优 - 学风建设 - 学生抽查情况";

		if ("stu".equalsIgnoreCase(userType)) {
			String msg = "该页面学生不能访问！";
			request.setAttribute("msg", msg);
		}

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			if (!myForm.isFdy()) {// 非辅导员
			//myForm.setCcyhlx("xy");
			}
			xxOper = true;
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "xymc",
					"bjmc", "jclxmc", "ydrs", "sdrs", "qqrs", "ccrq",
					"fdyclsj", "fdysjclsj", "xxsh", "ccyhlx" };// pk =
			// xn||xq||bjdm||ccrq||jclxdm
			String[] topList = new String[] { "xn", "xqmc", "xymc", "bjmc",
					"jclxmc", "ydrs", "sdrs", "qqrs", "ccrq", "fdyclsj",
					"fdysjclsj", "xxsh", "抽查用户" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXsccqkb(model, colList,user);
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "pjpy_xfjs_xsccqk.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("xxOper", xxOper);
		request.setAttribute("jclxList", service.queryJclxList());// 检查类型
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xsccqk");
	}

	/**
	 * 显示抽查情况信息增加页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String title = "评奖评优 - 学风建设 - 学生抽查情况 - 班级抽查情况增加";

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
			request.setAttribute("ccyhlx", "xy");
		} else {
			request.setAttribute("ccyhlx", "xx");
		}

		myForm.setXn(Base.currXn);
		myForm.setXq(Base.currXq);
		request.setAttribute("jclxList", service.queryJclxList());
		setNjXyZyBjList(request, myForm);
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkzj");
	}

	/**
	 * 班级抽查情况信息保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		model.setCcyhlx("xy".equalsIgnoreCase(userType) ? "xy" : "xx");
		model.setBjdm(request.getParameter("save_bjdm"));

		boolean result = service.bjccqkSave(model, request);

		xsccqkZj(mapping, form, request, response);
		request.setAttribute("result", result);
		model.setBjmc(DealString.toGBK(model.getBjmc()));
		return mapping.findForward("bjccqkzj");
	}

	/**
	 * 显示班级抽查情况信息修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward modiBjccqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		// String pk = "xn||xq||bjdm||ccrq||jclxdm||ccyhlx";
		String pkValue = request.getParameter("pk");
		String type = request.getParameter("type");

		if (!Base.isNull(type) && "save".equalsIgnoreCase(type)) {
			request.setAttribute("result", service.bjccqkSave(model, request));// 抽查情况保存
		}

		request.setAttribute("rs", service.queryBjccqkbByPk(pkValue));
		return mapping.findForward("modiBjccqk");
	}

	/**
	 * 显示学生抽查情况信息修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "评奖评优 - 学风建设 - 学生抽查情况 - 班级抽查情况修改";

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// 班级抽查信息
		request.setAttribute("flag", service.checkOther(pkValue));// 检测学院和学校是否录入了同一时间的记录且辅导员已经反馈过
		request.setAttribute("stuList", service.queryBjStuList(pkValue));// 查询检查班级的全部学生
		request.setAttribute("wjlxList", service.queryWjlxList());
		request.setAttribute("title", title);
		return mapping.findForward("xsccqkxg");
	}

	/**
	 * 显示班级抽查情况详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "评奖评优 - 学风建设 - 学生抽查情况 - 班级抽查情况详细信息";

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// 班级抽查信息
		request.setAttribute("xsList", service.queryXsccqkbByBj(pkValue));// 班级学生抽查信息
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkxx");
	}

	/**
	 * 保存学生抽查情况修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkXgSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		boolean result = service.saveXsccqk(model, request);

		xsccqkXg(mapping, form, request, response);
		request.setAttribute("result", result);
		return mapping.findForward("xsccqkxg");
	}

	/**
	 * 学生抽查情况信息删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String delPk = DealString.toGBK(request.getParameter("delPk"));

		model.setCbv(delPk.split("!!"));
		boolean result = service.deleteXsccqkb(model, request);

		request.setAttribute("result", result);

		xsccqkWh(mapping, form, request, response);
		return mapping.findForward("xsccqk");
	}

	/**
	 * 学生抽查情况审核查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward ccqksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = (String) session.getAttribute("userDep");

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "评奖评优 - 学风建设 - 学生抽查情况审核";

		if ("stu".equalsIgnoreCase(userType)) {
			String msg = "该页面学生不能访问！";
			request.setAttribute("msg", msg);
		}

		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		boolean result = false;

		BeanUtils.copyProperties(model, myForm);

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmc", "xymc",
					"bjmc", "jclxmc", "ydrs", "sdrs", "qqrs", "ccrq",
					"fdyclsj", "fdysjclsj", "xxsh", "ccyhlx" };// pk =
			// xn||xq||bjdm||ccrq||jclxdm||ccyhlx
			String[] topList = new String[] { "xn", "xqmc", "xymc", "bjmc",
					"jclxmc", "ydrs", "sdrs", "qqrs", "ccrq", "fdyclsj",
					"fdysjclsj", "xxsh", "ccyhlx" };
			User user =  getUser(request);
			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXsccqkb(model, colList,user);
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		// 存放访问路径求取读写权
		request.setAttribute("path", "pjpy_xfjs_ccqksh.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("jclxList", service.queryJclxList());// 检查类型
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("ccqksh");
	}

	/**
	 * 班级抽查信息单个审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward bjccqkshxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "评奖评优 - 学风建设 - 学生抽查情况 - 班级抽查情况审核";
		String pkValue = request.getParameter("pk");
		String type = request.getParameter("type");

		if (!Base.isNull(type) && "save".equalsIgnoreCase(type)) {
			PjpyXfjsForm model = (PjpyXfjsForm) form;
			model.setCbv(new String[] { request.getParameter("pk") });
			request.setAttribute("result", service.ccqkAuditing(model));
		}

		request.setAttribute("model", service.queryBjccqkbByPk(pkValue));// 班级抽查信息
		request.setAttribute("xsList", service.queryXsccqkbByBj(pkValue));// 班级学生抽查信息
		request.setAttribute("title", title);
		return mapping.findForward("bjccqkshxx");
	}

	/**
	 * 学生抽查情况信息审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward ccqkAudi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		String delPk = DealString.toGBK(request.getParameter("delPk"));

		model.setCbv(delPk.split("!!"));
		model.setXxsh(request.getParameter("shjg"));
		boolean result = service.ccqkAuditing(model);

		request.setAttribute("result", result);
		return ccqksh(mapping, form, request, response);
	}

	/**
	 * 学生考勤查询首页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xskqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String userDep = (String) session.getAttribute("userDep");
		String page = "xskqcx";

		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		PjpyXfjsForm model = new PjpyXfjsForm();

		// ==========2012.12.10 edit by luojw begin============
		if (Base.isNull(myForm.getXn())) {
			myForm.setXn(Base.currXn);
		}

		if (Base.isNull(myForm.getXq())) {
			myForm.setXq(Base.currXq);
		}
		// ==========2012.12.10 edit by luojw end============

		String tableName = "view_pjpy_xfjs_xsjljcb";
		String realTable = "pjpy_xfjs_xsjljcb";
		String title = "评奖评优 - 学风建设 - 学生考勤查询";

		if ("stu".equalsIgnoreCase(userType)) {
			myForm.setXh(session.getAttribute("userName").toString());
			page = "xskqxxcx";
		}

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}

		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		// if (((request.getParameter("go") != null) &&
		// request.getParameter("go")
		// .equalsIgnoreCase("go"))) {
		String[] colList = new String[] { "pk", "xh", "xm", "xn", "xqmc",
				"xymc", "bjmc", "jclxmc", "wjlxmc", "qjlxmc", "ccrq", "ccyhlx" };
		// pk = xh||xn||xq||bjdm||ccrq||jclxdm||ccyhlx
		String[] topList = new String[] { "xh", "xm", "xn", "xqmc", "xymc",
				"bjmc", "jclxmc", "wjlxmc", "qjlxmc", "ccrq", "ccyhlx" };

		topTr = service.getTopTr(tableName, topList);
		rs = service.queryXskqjljcb(model, colList);

		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		// }

		// 存放访问路径求取读写权
		request.setAttribute("path", "pjpy_xfjs_xsccqk.do");
		request.setAttribute("title", title);
		setUserInfo(request);
		request.setAttribute("jclxList", service.queryJclxList());// 检查类型
		request.setAttribute("wjlxList", service.queryWjlxList());// 违纪类型
		request.setAttribute("qjlxList", service.queryQjlxList());// 请假类型
		setNjXyZyBjList(request, myForm, false);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward(page);
	}

	/**
	 * 显示学生抽查情况详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward xsccqkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pkValue = DealString.toGBK(request.getParameter("pk"));
		String title = "评奖评优 - 学风建设 - 学生抽查情况 - 学生抽查情况详细信息";

		request.setAttribute("model", service.queryXsccqkbByPk(pkValue));// 班级学生抽查信息
		request.setAttribute("title", title);
		return mapping.findForward("xsccqkxx");
	}

	/**
	 * 导出班级抽查情况信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXsccqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setCcrq(request.getParameter("ccrq"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setSfyr(request.getParameter("sfyr"));
		model.setSfzgdsjcl(request.getParameter("sfzgdsjcl"));
		model.setSfcl(request.getParameter("sfcl"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = { "xn", "xq", "xqmc", "xydm", "xymc", "zydm",
				"zymc", "bjdm", "bjmc", "nj", "ccrq", "jclxdm", "jclxmc",
				"ydrs", "qqrs", "sdrs", "wjrs", "fdyclbz", "fdyclsj",
				"fdysjclsj", "xxsh", "ccyhlx", "bz" };
		String[] colListCN = service.getColumnNameCN(colList,
				"view_pjpy_xfjs_bjccqkb");
		List<String[]> rs = service.queryXsccqkForExport(model, colList);// 查询班级抽查情况信息

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * 导出学生考勤信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXskqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setCcrq(request.getParameter("ccrq"));
		model.setCcrqks(request.getParameter("ccrqks"));
		model.setCcrqjs(request.getParameter("ccrqjs"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setWjlxdm(request.getParameter("wjlxdm"));
		model.setQjlxdm(request.getParameter("qjlxdm"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setXh(request.getParameter("xh"));
		model.setXm(request.getParameter("xm"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = { "xn", "xq", "xqmc", "xh", "xm", "xb", "nj",
				"xydm", "xymc", "zydm", "zymc", "bjdm", "bjmc", "ccrq",
				"jclxdm", "jclxmc", "qjlxdm", "qjlxmc", "wjcs", "wjlxdm",
				"wjlxmc", "ccyhlx", "bz" };
		String[] colListCN = service.getColumnNameCN(colList,
				"view_pjpy_xfjs_xsjljcb");
		List<String[]> rs = service.queryXskqxxForExport(model, colList);// 查询学生考勤信息

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * 显示统计查询选择页面
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * */
	public ActionForward showXfjsTjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("showxfjstjcx");
	}

	/**
	 * 抽查情况统计查询
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward bjccqkSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String tableName = "view_pjpy_xfjs_bjccqkb";
		String realTable = "pjpy_xfjs_bjccqkb";
		String title = "评奖评优 - 学风建设 - 统计查询 - 抽查情况";

		List<HashMap<String, String>> topTr = null;
		List<String[]> rs = null;

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(session.getAttribute("userDep").toString());
		}
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "pkValue", "qualification",
					"xymc", "zymc", "bjmc", "nj", "wjcs", "cccs" };
			String[] topList = new String[] { "主键值", "条件", "学院名称", "专业名称",
					"班级名称", "年级", "违纪次数", "抽查次数" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryBjccqktjxx(myForm, colList);

			String pk = getBjccqkSearchPk(myForm);
			request.setAttribute("pk", !Base.isNull(pk) ? StringUtils.joinStr(
					pk, "||bjdm") : "bjdm");
		} else {
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}
		setNjXyZyBjList(request, myForm, false);
		request.setAttribute("title", title);
		request.setAttribute("jclxList", service.queryJclxList());// 检查类型
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("bjccqkSearch");
	}

	/**
	 * 显示抽查情况统计查询详细信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward bjccqktjDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pk = request.getParameter("pk");// 主键字段
		String pkValue = request.getParameter("pkValue");// 主键值

		request.setAttribute("rs", service.queryBjccqktjDetails(pk, pkValue));// 查询详细信息
		return mapping.findForward("bjccqktjDetails");
	}

	/**
	 * 获取查询的条件值
	 * 
	 * @param PjpyXfjsForm
	 *            model
	 * @return String
	 * */
	private String getBjccqkSearchPk(PjpyXfjsForm model) {
		String pk = "";
		StringBuffer qualification = new StringBuffer();
		qualification.append(Base.isNull(model.getXn()) ? "" : "xn||");
		qualification.append(Base.isNull(model.getXq()) ? "" : "xq||");
		qualification.append(Base.isNull(model.getNj()) ? "" : "nj||");
		qualification.append(Base.isNull(model.getXydm()) ? "" : "xydm||");
		qualification.append(Base.isNull(model.getZydm()) ? "" : "zydm||");
		qualification.append(Base.isNull(model.getBjdm()) ? "" : "bjdm||");
		qualification.append(Base.isNull(model.getJclxdm()) ? "" : "jclxdm||");
		qualification.append(Base.isNull(model.getWjlxdm()) ? "" : "wjlxdm||");
		qualification.append(Base.isNull(model.getQjlxdm()) ? "" : "qjlxdm||");
		qualification.append(Base.isNull(model.getXh()) ? "" : "xh||");
		pk = qualification.toString();
		if (!Base.isNull(pk)) {
			pk = pk.substring(0, pk.length() - 2);
		}
		return pk;
	}

	/**
	 * 抽查情况统计查询导出
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expBjccqktjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setXymc(request.getParameter("xymc"));
		model.setZymc(request.getParameter("zymc"));
		model.setBjmc(request.getParameter("bjmc"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setJclxmc(request.getParameter("jclxmc"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = new String[] { "qualification", "xymc", "zymc",
				"bjmc", "nj", "wjcs", "cccs" };
		String[] colListCN = new String[] { "条件", "学院名称", "专业名称", "班级名称", "年级",
				"违纪次数", "抽查次数" };
		List<String[]> rs = service.queryBjccqktjxx(model, colList);// 查询班级抽查情况信息

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * 学生考勤统计查询
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward xskqqkSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm myForm = (PjpyXfjsForm) form;
		String tableName = "view_pjpy_xskqb";
		String realTable = "pjpy_xskqb";
		String title = "评奖评优 - 学风建设 - 统计查询 - 学生考勤查询";

		List<HashMap<String, String>> topTr = null;
		List<String[]> rs = null;

		myForm
				.setFdy(session.getAttribute("fdyQx").equals(true) ? true
						: false);
		myForm.setUserName(userName);

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			String[] colList = new String[] { "qualification", "xh", "xm",
					"xymc", "bjmc", "nj", "wjcs", "qjcs", "kkjs" };
			String[] topList = new String[] { "条件", "xh", "xm", "xymc", "bjmc",
					"nj", "违纪次数", "请假次数", "旷课节数" };

			topTr = service.getTopTr(tableName, topList);
			rs = service.queryXskqqktjxx(myForm, colList);

			String pk = getBjccqkSearchPk(myForm);
			request.setAttribute("pk", !Base.isNull(pk) ? StringUtils.joinStr(
					pk, "||xh") : "xh");
		} else {
			myForm.setXn(Base.currXn);
			myForm.setXq(Base.currXq);
		}

		setNjXyZyBjList(request, myForm, false);
		request.setAttribute("title", title);
		request.setAttribute("jclxList", service.queryJclxList());// 检查类型
		request.setAttribute("wjlxList", service.queryWjlxList());// 违纪类型
		request.setAttribute("qjlxList", service.queryQjlxList());// 请假类型
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xskqqkSearch");
	}

	/**
	 * 显示学生考勤统计查询详细信息
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward xskqtjDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pk = request.getParameter("pk");// 主键字段
		String pkValue = request.getParameter("pkValue");// 主键值

		request.setAttribute("rs", service.queryXskqtjDetails(pk, pkValue));// 查询详细信息
		return mapping.findForward("xskqtjDetails");
	}

	/**
	 * 学生考勤统计查询导出
	 * 
	 * @param ActionMapping
	 *            mapping
	 * @param ActionForm
	 *            form
	 * @param HttpServletRequest
	 *            request
	 * @param HttpServletResponse
	 *            response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward expXskqtjxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		PjpyXfjsForm model = (PjpyXfjsForm) form;
		PjpyXfjsService service = new PjpyXfjsService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setNj(request.getParameter("nj"));
		model.setXydm(request.getParameter("xydm"));
		model.setZydm(request.getParameter("zydm"));
		model.setBjdm(request.getParameter("bjdm"));
		model.setJclxdm(request.getParameter("jclxdm"));
		model.setJclxmc(request.getParameter("jclxmc"));
		model.setWjlxdm(request.getParameter("wjlxdm"));
		model.setWjlxmc(request.getParameter("wjlxmc"));
		model.setQjlxdm(request.getParameter("qjlxdm"));
		model.setQjlxmc(request.getParameter("qjlxmc"));
		model.setXh(request.getParameter("xh"));

		model.setFdy(session.getAttribute("fdyQx").equals(true) ? true : false);
		model.setUserName(userName);

		String[] colList = new String[] { "qualification", "xh", "xm", "xymc",
				"bjmc", "nj", "wjcs", "qjcs", "kkjs" };
		String[] colListCN = new String[] { "条件", "学号", "姓名", "学院", "班级", "年级",
				"违纪次数", "请假次数", "旷课节数" };
		List<String[]> rs = service.queryXskqqktjxx(model, colList);// 查询班级抽查情况信息
		
		//
		List<String[]> rss= new ArrayList<String[]>(); 
		String[] tmp =null;
		for (int i = 0; i < rs.size(); i++) {
			tmp =new String[(rs.get(i).length - 1)];
			for(int j = 0; j < tmp.length ; j++){
				tmp[j]=rs.get(i)[(j+1)];
			}
			rss.add(tmp);
		}
		

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rss, colList, colListCN, response
				.getOutputStream());
		return mapping.findForward("");
	}

	/**
	 * 传递用户信息
	 * 
	 * @param HttpServletRequest
	 *            request
	 * */
	public void setUserInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();

		String userStatus = "";
		String userType = session.getAttribute("userType").toString();
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			userStatus = "xx";// 学校用户（管理员）
		}
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("userType", session.getAttribute("userType"));
		request.setAttribute("userName", session.getAttribute("userName"));
		request.setAttribute("userDep", session.getAttribute("userDep"));
	}

	public void setNjXyZyBjList(HttpServletRequest request, PjpyXfjsForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}
	}

	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyXfjsForm myForm, boolean bjFlag)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		// 在request保存年级学院专业班级List的方法
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")
				&& bjFlag) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		}
	}

	public void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, List<String[]> rs,
			List<HashMap<String, String>> topTr) {
		// Request存值的通用方法 区别是title从数据库里取
		String writeAble = request.getParameter("writeAble");
		if (Base.isNull(writeAble)) {
			String[] message = getWriteAbleAndTitle(request);
			writeAble = message[0];
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
}
