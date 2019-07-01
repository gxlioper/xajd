package xgxt.pjpy.ghxy.rykf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.pjpy.ghxy.ryjf.PjpyRyjfForm;
import xgxt.pjpy.ghxy.ryjf.grryf.RyjfModel;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

public class RyjfGhxyAction extends BasicExtendAction {

	/**
	 * 硅湖学院荣誉减分录入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String xn = Base.currXn;
		String xq = Base.currXq;
		String go = request.getParameter("go");

		String user = getUserType(request);

		// 如果不是fdy 或者 gyfdy 返回错误页面，提示没有权限
		if (request.getAttribute("fdy") == null
				&& request.getAttribute("gyfdy") == null) {
			request.setAttribute("errMsg", "对不起,您没有该模块权限！");
			return mapping.findForward("error");
		}

		// 当前权限
		String dqqx = request.getParameter("dqqx");

		if (dqqx == null) {
			dqqx = "fdy".equalsIgnoreCase(user) ? "fdy" : "gyfdy";
		}

		PjpyRyjfForm myForm = (PjpyRyjfForm) form;

		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "xh", "xm", "xymc", "zymc",
					"bjmc", "xqmc", "ldmc", "qsh" };
			this.selectPageDataByPagination(request, myForm, "",
					"view_ghxy_xszsxx", outputColumn);
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		setWriteAbleAndTitle(request, "pjpyjflr.do");

		String ld = myForm.getQueryequals_lddm();
		String cs = myForm.getQueryequals_cs();
		String qsh = myForm.getQueryequals_qsh();

		request.setAttribute("xn", xn);
		request.setAttribute("xqmc", BasicExtendService.xqMap.get(xq));
		request.setAttribute("xqdm", xq);
		request.setAttribute("dqqx", dqqx);
		request.setAttribute("ld", ld);
		request.setAttribute("cs", cs);
		request.setAttribute("qsh", qsh);
		return mapping.findForward("ryjflr");
	}

	/**
	 * 硅湖学院单个学生荣誉减分录入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjflrone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 获得学年，学号，学期
		String xh = request.getParameter("xh");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String dqqx = request.getParameter("dqqx");

		String doType = request.getParameter("doType");
		PjpyRyjfForm myForm = (PjpyRyjfForm) form;
		RyjfGhxyService service = new RyjfGhxyService();

		// 批量保存录入信息
		if ("save".equalsIgnoreCase(doType)) {
			RyjfModel model = new RyjfModel();
			BeanUtils.copyProperties(model, myForm);

			String msg = service.saveXsRyjf(model,dqqx) ? "操作成功" : "操作失败";
			request.setAttribute("msg", msg);
		}

		String[] output = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc" };
		Map<String, String> stuInfo = service.getStuInfo(xh, output);

		List<HashMap<String, String>> xmList = new ArrayList<HashMap<String, String>>();

		if ("fdy".equalsIgnoreCase(dqqx)) {
			xmList = service.getXmList("1");
		} else if ("gyfdy".equalsIgnoreCase(dqqx)) {
			xmList = service.getXmList("2");
		}

		List<HashMap<String, String>> jftgxx = service.getXsjfxx(xh, xn, xq,
				new String[] { "通过" }, xmList);

		request.setAttribute("jftgxx", jftgxx);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xmList", xmList);
		request.setAttribute("xn", xn);
		request.setAttribute("xqmc", BasicExtendService.xqMap.get(xq));
		request.setAttribute("xqdm", xq);
		request.setAttribute("dqqx", dqqx);
		return mapping.findForward("ryjflrone");
	}

	/**
	 * 获得该用户的职能
	 * 
	 * @param request
	 * @return
	 */
	public String getUserType(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// 获得用户类型和用户名
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		// 辅导员依据字段
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy")
				.toString());
		RyjfGhxyService service = new RyjfGhxyService();
		GhxyNjzrwhService njzrService = new GhxyNjzrwhService();

		// 是否是年纪辅导员
		List<HashMap<String, String>> njList = njzrService.getFdyNj(userName);

		// 页签
		if (isFdy) {
			userType = "fdy";
			request.setAttribute("fdy", "yes");
		} else if (njList != null && njList.size() > 0) {
			userType = "njbzr";
			// 年级部主任可以查看的年级
			request.setAttribute("njListForBzr", njList);
			request.setAttribute("njbzr", "yes");
		} else if ("xy".equalsIgnoreCase(userType)) {
			userType = "xy";
		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			userType = "xx";
		}

		// 是否是公寓辅导员
		List<HashMap<String, String>> xqldList = service.getXqList(userName);

		if (xqldList != null && xqldList.size() > 0) {
			request.setAttribute("xqldList", xqldList);
			request.setAttribute("gyfdy", "yes");
		}

		request.setAttribute("userType", userType);

		return userType;
	}

	/**
	 * 硅湖学院学生减分审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "ghxy_ryjfb";
		String viewName = "view_ghxy_ryjf";
		// 需要进行的操作
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(request);
		String xq = Base.currXq;

		if (!("njbzr".equalsIgnoreCase(user) || "xx".equalsIgnoreCase(user))) {
			request.setAttribute("errMsg", "对不起,您没有该模块权限！");
			return mapping.findForward("error");
		}

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

		// 审核操作
		if ("sh".equalsIgnoreCase(doType)) {
			// 审核字段
			String shzd = request.getParameter("shzd");

			// 审核结果
			String shjg = request.getParameter("shjg");

			if (!StringUtils.isNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "通过" : "不通过";
			}

			// 获取页面中以primarykey_为开始的数据
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);

			// 通用审核方法
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
					"xn", "xqmc", "xm", "nj", "xymc", "zymc", "bjmc", "xmmc",
					"jf", "njbzrsh", "xxsh" };
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "pjpyjfsh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", BasicExtendService.xqMap.get(xq));
		request.setAttribute("xqdm", xq);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("userType", user);

		return mapping.findForward("ryjfsh");
	}

	/**
	 * 硅湖学院学生减分审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();

		String tableName = "ghxy_ryjfb";
		String viewName = "view_ghxy_ryjf";
		// 需要进行的操作
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// 获取userType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

		if ("stu".equalsIgnoreCase(user)) {
			gForm.setQuerylike_xh(userName);
		} else if ("xy".equalsIgnoreCase(user)) {
			gForm.setQueryequals_xydm(userDep);
		}

		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tableName);
		}

		// 根据页面中的条件获取数据
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
					"xn", "xqmc", "xm", "nj", "xymc", "zymc", "bjmc", "xmmc",
					"jf", "njbzrsh", "xxsh" };
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "pjpyjfcx.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);

		return mapping.findForward("ryjfcx");
	}

	/**
	 * 硅湖学院个人加分单个审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// 表ghxy_ryjfb的主键
		String pkValue = request.getParameter("pkValue");

		RyjfGhxyService service = new RyjfGhxyService();

		if ("save".equalsIgnoreCase(doType)) {
			this.updateOperation(request, "ghxy_ryjfb");
		}

		Map<String, String> jfxx = service.getXsjfxxone(pkValue);
		request.setAttribute("rs", jfxx);
		request.setAttribute("userType", user);
		return mapping.findForward("ryjfshone");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfViewAndModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String tableName = "ghxy_ryjfb";
		String viewName = "view_ghxy_ryjf";

		String user = getUserType(session);

		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		// 区分页面属于什么操作
		String operation = "modi";
		RyjfGhxyService service = new RyjfGhxyService();

		Map<String, String> map = service.getXsjfxxone(pkValue);

		if ("view".equalsIgnoreCase(doType)) {
			operation = "view";
		}

		if ("save".equalsIgnoreCase(doType)) {
			this.updateOperation(request, tableName);
		}

		request.setAttribute("operation", operation);
		request.setAttribute("rs", map);
		request.setAttribute("userType", user);
		return mapping.findForward("ryjfview");
	}

	/**
	 * 显示需要获取数据库的字段包括特殊字段,
	 * 
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField(HttpServletRequest request, String user) {
		// 获取上级是否审核通过信息，返回个页面
		if ("njbzr".equalsIgnoreCase(user)) {
			request
					.setAttribute("clientColumns",
							"(case xxsh when '通过' then 'disabled' else '' end)disabled,");
		} else if ("xx".equalsIgnoreCase(user)) {
			request.setAttribute("clientColumns", "'' disabled,");
		} else {
			request
					.setAttribute("clientColumns",
							"(case njbzrsh when '通过' then 'disabled' else '' end)disabled,");
		}
	}

	/**
	 * 通用导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] output = new String[] { "xh", "xn", "xqmc", "xm", "nj",
				"xymc", "zymc", "bjmc", "xmmc", "jf", "njbzrsh", "xxsh" };

		expPageData(request, response, "ghxy_ryjfb", "view_ghxy_ryjf", output);
		return mapping.findForward("");
	}
}
