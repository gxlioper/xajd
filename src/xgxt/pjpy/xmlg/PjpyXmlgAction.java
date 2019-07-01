package xgxt.pjpy.xmlg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyXmlgAction extends CommonAction {

	private static final String SAVE = "save";
	private static final String QUERY = "query";
//	private static final String DELETE = "delete";
	private static final String VIEW = "view";
	
	/**
	 * 综合素质测评维护默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhszcpwhDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryZhszcpTitle();
			rs = (ArrayList<String[]>)service.queryZhszcpResult(model);
		}
		
		request.setAttribute("path", "data_search.do?act=zhsz");
		FormModleCommon.commonRequestSet(request, "view_zhszcp", "zhszcp", rs, topTr);
		try {
			setNjXyZyBjList(request, xmlgForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("zhszcpwhDefault");
	}
	
	/**
	 * 综合素质测评删除操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		// 删除综合素质测评信息
		boolean result = service.deleteZhszcpxx(model);
		appendOperResult(request, result);
		zhszcpwhDefault(mapping, form, request, response);
		return mapping.findForward("zhszcpwhDefault");
	}
	
	/**
	 * 增加综合素质测评成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = CommonQueryDAO.getStuInfo(xh);
		}
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.addZhszcpxx(model);
			appendOperResult(request, result);
		}
		
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("rs", rs);
		return mapping.findForward("addZhszcpxx");
	}
	
	/**
	 * 修改综合素质测评成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			model.setPkValue(pkValue);
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.updateZhszcpxx(model);
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {
			request.setAttribute("writable", "no");
		}
		
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		HashMap<String, String> rs = service.queryZhszcpOnexx(queryModel);
		if (rs != null) {
			xmlgForm.setXn(rs.get("xn"));
			xmlgForm.setXq(rs.get("xq"));
			xmlgForm.setDcj(rs.get("dcj"));
			xmlgForm.setZcj(rs.get("zcj"));
			xmlgForm.setTcj(rs.get("tcj"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("modiZhszcpxx");
	}
	
	/**
	 * 设置综测排名方式
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szZhszcppm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userModel = getXybmxx(request);
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();
		
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydm(userModel.getXydm());
			boolean result = service.saveZhszcpPmfs(model);
			appendOperResult(request, result);
		} else {
			rs = service.getXyZhszcppmfs(userModel);
		}
		
		if (rs != null) {
			xmlgForm.setPmfs(rs.get("pmfs"));
		}
		return mapping.findForward("szZhszcppm");
	}
	
	/**
	 * 统一获取用户类型与部门信息
	 * @param request
	 * @return
	 */
	public PjpyXmlgModel getXybmxx(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ?session.getAttribute("userType").toString():null;
		String userDep = session.getAttribute("userDep") != null ?session.getAttribute("userDep").toString():null;
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXydm(userDep);
		model.setUserType(userType);
		return model;
	}
	
	/**
	 * 奖学金,荣誉称号比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward csblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyXmlgModel userDepModel = getXybmxx(request);
		//这个地方是学院用户进行奖学金,荣誉称号人数调整
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			return new ActionForward("/pjpy_xmlg_xyJxjrstz.do", false);
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "jxj";
		}
				
		String act = request.getParameter("act");
	
		if ("rych".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_rychCsblsz.do", false);
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("jxj");
			topTr = service.queryCsszTitle();
			rs = (ArrayList<String[]>)service.queryBlszxx(queryModel);
		}
		
		
		//加载下拉列表
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("是");
		request.setAttribute("jxjList", service.getJxjList(model));
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		return mapping.findForward("csblsz");
	}
	
	/**
	 * 初始化奖学金比例设置数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		model.setKey("jxj");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		boolean result = service.initData(model);
		appendOperResult(request, result);
		
		//初始化奖学金比例设置数据
		csblsz(mapping, form, request, response);
		return mapping.findForward("csblsz");
	}
	
	/**
	 * 初始化奖学金比例设置数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initDataByRych(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		model.setKey("rych");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		boolean result = service.initData(model);
		appendOperResult(request, result);
		
		//初始化荣誉称号比例设置数据
		rychCsblsz(mapping, form, request, response);
		return mapping.findForward("rychCsblsz");
	}
	
	/**
	 * 奖学金比例批量设置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String xn = Base.getJxjsqxn();
		PjpyXmlgService service = new PjpyXmlgService();
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			String[] xydm = request.getParameterValues("chkonexy");
			String[] njdm = request.getParameterValues("chkonenj");
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydmList(xydm);
			model.setNjdmList(njdm);
			model.setXn(xn);
			model.setKey("jxj");
			boolean result = service.modiJxjblszxx(model);
			appendOperResult(request, result);
		}
		
		//加载下拉列表
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		request.setAttribute("xn", xn);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxjBlsz");
	}
	
	/**
	 * 荣誉称号比例设置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String xn = Base.getJxjsqxn();
		PjpyXmlgService service = new PjpyXmlgService();
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			String[] xydm = request.getParameterValues("chkonexy");
			String[] njdm = request.getParameterValues("chkonenj");
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydmList(xydm);
			model.setNjdmList(njdm);
			model.setXn(xn);
			model.setKey("rych");
			boolean result = service.modiJxjblszxx(model);
			appendOperResult(request, result);
		}
		
		//加载下拉列表
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("xn", xn);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("rychBlsz");
	}
	
	/**
	 * 修改比例设置信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiBlszxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);

		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();

		// 修改数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.modiBlszxxOne(model);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryBlszxxOne(queryModel);
		if (rs != null) {
			xmlgForm.setBl(rs.get("bl"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiBlszxx");
	}
	
	/**
	 * 修改比例设置信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiRychBlszxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);

		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();

		// 修改数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.modiBlszxxOne(model);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryBlszxxOne(queryModel);
		if (rs != null) {
			xmlgForm.setBl(rs.get("bl"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiRychBlszxx");
	}
	
	/**
	 * 荣誉称号学校比例设置页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychCsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyXmlgService service = new PjpyXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "rych";
		}
		PjpyXmlgModel userDepModel = getXybmxx(request);
//		这个地方是学院用户进行荣誉称号人数调整
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			return new ActionForward("/xmlgpjpy.do?method=xyRychrstz", false);
		}
		String act = request.getParameter("act");
		if ("jxj".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_csblsz.do", false);
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("rych");
			topTr = service.queryCsszTitle();
			rs = (ArrayList<String[]>)service.queryBlszxx(queryModel);
		}
		
		//加载下拉列表
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		return mapping.findForward("rychCsblsz");
	}
	
	/**
	 * 奖学金申请默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);

		PjpyXmlgService service = new PjpyXmlgService();
				
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		
		//学生奖学金申请操作
		if ("stu".equalsIgnoreCase(userDepModel.getUserType())
				|| "student".equalsIgnoreCase(userDepModel.getUserType())) {
			xh = request.getSession().getAttribute("userName") != null ? request
					.getSession().getAttribute("userName").toString()
					: "";
			request.setAttribute("showstu", "yes");
		}
		
		//保存奖学金申请信息
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXh(xh);
			model.setXn(xn);
			model.setNd(nd);
			model.setXq(Base.getJxjsqxq());
			boolean result = service.addJxjsqxx(model);
			//这个主键是用来打印申请表的
			request.setAttribute("pkValue", model.getXh()+model.getXn()+model.getJxjdm());
			appendOperResult(request, result);
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			queryModel.setXn(xn);
			queryModel.setXh(xh);
			//查询学生信息
			rs = CommonQueryDAO.getStuInfo(xh);
			//查询学生班级人数
			String bjrs = CommonQueryDAO.getBjrs(xh);
			rs.put("bjrs", bjrs);
			//查询学生综测排名信息
			zhszcpList = service.getStuZhszcpList(queryModel);
		}
		rs.put("xn", xn);
		rs.put("nd", nd);
		request.setAttribute("rs", rs);
		request.setAttribute("zhcpList", zhszcpList);
		
		//加载下拉列表
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		//选择奖学金类别后刷新奖学金信息
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		return mapping.findForward("jxjsqDefault");
	}
	
	/**
	 * 奖学金调整人数方式限制
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzrsFssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.saveJxjrstzrs(model);
			appendOperResult(request, result);
		}
		
		//这里的限制方式定死了，
		// 1 是四舍五入限制方式。
		// 2 是金额限制方式.
		HashMap<String, String> rs = service.getJxjrstzxzrs();
		if (rs != null) {
			xmlgForm.setXzfs(rs.get("xzfs"));
		}
		
		request.setAttribute("xzfsList", service.getXzfsList());
		return mapping.findForward("tzrsFssz");
	}
	
	/**
	 * 奖学金申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//如果是学院用户
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryJxjsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.queryJxjsqResult(model, false);
		}
		
		//加载下拉列表
		request.setAttribute("path", "prise_result.do");
		FormModleCommon.commonRequestSet(request, "view_xsjxjb", "xsjxjb", rs, topTr);
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		//选择奖学金类别后刷新奖学金信息
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxjsqQuery");
	}
	
	/**
	 * 删除奖学金申请数据操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.deleteJxjsqxx(model);
		appendDeleteResult(request, result);
		
		jxjsqQuery(mapping, form, request, response);
		return mapping.findForward("jxjsqQuery");
	}
	
	/**
	 * 修改奖学金申请数据操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiJxjsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		
		//保存修改信息
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.updateJxjsqxx(model);
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {//这里是针对双击一行来控制的
			request.setAttribute("writable", "no");
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		
		queryModel.setPkValue(pkValue);
		//通过主键查询奖学金申请信息
		rs = service.viewJxjsqxx(queryModel);
		if (rs != null) {
			xmlgForm.setLbdm(rs.get("lbdm"));
			xmlgForm.setJxjdm(rs.get("jxjdm"));
			xmlgForm.setDrzw(rs.get("drzw"));
			xmlgForm.setWysp(rs.get("wysp"));
			xmlgForm.setJsjsp(rs.get("jsjsp"));
			xmlgForm.setJlqk(rs.get("jlqk"));
			xmlgForm.setSqly(rs.get("sqly"));
		}
		
		//查询学生综测排名信息
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		zhszcpList = service.getStuZhszcpList(queryModel);

		request.setAttribute("zhcpList", zhszcpList);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		
		PjpyXmlgModel dmqueryModel = new PjpyXmlgModel();
		dmqueryModel.setSfqy("是");
		//选择奖学金类别后刷新奖学金信息
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			dmqueryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(dmqueryModel));
		return mapping.findForward("modiJxjsqxx");
	}
	
	/**
	 * 学生查询奖学金申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryJxjsqxxBystu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString() : "";
				
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXh(xh);
		List<String[]> rs = service.queryStujxjsqxx(model);
		List<HashMap<String, String>> topTr = service.queryJxjsqByStuTitle();
			
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("num", rs != null ? rs.size() : 0);
		return mapping.findForward("queryJxjsqxxBystu");
	}
	
	/**
	 * 删除奖学金申请数据操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stujxjsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.deleteJxjsqxx(model);
		appendDeleteResult(request, result);
		
		queryJxjsqxxBystu(mapping, form, request, response);
		return mapping.findForward("queryJxjsqxxBystu");
	}
	
	/**
	 * 奖学金审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//如果是学院用户
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
			//这里处理的是加载各院系调整人数方式
			if (!StringUtils.isNull(xmlgForm.getXydm())) {
				String fpfs = service.getXyTzFs(Base.getJxjsqxn(), xmlgForm.getXydm());
				//调整人数方式
				request.setAttribute("fpfs", fpfs);
				if (!StringUtils.isNull(fpfs)) {
					request.setAttribute("fpfsxx", "奖学金审核将以奖学金，"
							+ ("zy".equalsIgnoreCase(fpfs) ? "专业" : ("bj"
									.equalsIgnoreCase(fpfs) ? "班级" : ""))
							+ "为单位进行审核;");
				}
				String szrs = "";
				PjpyXmlgModel model = new PjpyXmlgModel();
				model.setXn(Base.getJxjsqxn());
				model.setJxjdm(xmlgForm.getJxjdm());
				
				if ("zy".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getZydm());
					szrs = service.getXyJxtzrs(model, "jxj");
				} else if ("bj".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getBjdm());
					szrs = service.getXyJxtzrs(model, "jxj");
				}
				//调整的人数
				request.setAttribute("szrs", szrs);
				request.setAttribute("szrsxx", StringUtils.isNull(szrs)
						|| "0".equalsIgnoreCase(szrs) ? "" : "获奖名额为:" + szrs
						+ "人");
			}
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryJxjsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.queryJxjsqResult(model, true);
		}
		
		//加载下拉列表
		request.setAttribute("path", "prise_check.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		//选择奖学金类别后刷新奖学金信息
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("shList", service.getShList());
		
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金单个审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String pkValue = request.getParameter("pkValue");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setKeys(new String[]{pkValue});
			model.setUserType(userDepModel.getUserType());
			//这个地方单个审核与批量审核调用的是同一个方法,所以这里第二个参数为空
			boolean result = service.saveJxjshxx(model, "", "jxj", false);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		queryModel.setUserType(userDepModel.getUserType());
		rs = service.queryJxjshDgxx(queryModel);
		if (rs != null) {
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
			xmlgForm.setXyshyj(rs.get("xyshyj"));
		}
		
		//这里控制的是学校审核通过后,学院不能再审核哟
		if ("xy".equalsIgnoreCase(userDepModel.getUserType()) && "通过".equalsIgnoreCase(rs.get("xxsh"))) {
			request.setAttribute("writable", "no");
		}
		
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		
		//加载列表
		request.setAttribute("zhcpList", service.getStuZhszcpList(queryModel));//综测排名列表
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userDepModel.getUserType());
		return mapping.findForward("jxjDgsh");
	}
	
	/**
	 * 奖学金批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		String act = request.getParameter("act");
		if ("tg".equalsIgnoreCase(act)) {
			model.setSh("通过");
		} else if ("btg".equalsIgnoreCase(act)) {
			model.setSh("不通过");
		}
		model.setUserType(userDepModel.getUserType());
		
		//批量保存操作
		boolean result = service.saveJxjshxx(model, act, "jxj", true);
		appendOperResult(request, result);
		jxjsh(mapping, form, request, response);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * 在REQUEST中存放数据操作后的结果
	 * @param request
	 * @param result
	 */
	public void appendOperResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	}
	
	//删除操作提示
	public void appendDeleteResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
	}
	
	public void appendPjxnndxq(HttpServletRequest request) {
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
	}
	
	/**
	 * 加载学院，年级，专业，班级下拉列表值
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyXmlgActionForm myForm) throws Exception{
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
		if ("xy".equalsIgnoreCase(userType)) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//学期列表
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
						.toString())) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// 发送班级列表
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// 发送班级列表
		}
	}
	public ActionForward xyJxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXydm(userDep);
		xmlgForm.setXn(Base.getJxjsqxn());
		PjpyXmlgService service = new PjpyXmlgService();
		String userType = request.getSession().getAttribute("userType").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "jxj";
		}				
		String act = request.getParameter("act");
		
		if ("rych".equalsIgnoreCase(act)) {
			return new ActionForward("/xmlgpjpy.do?method=xyRychrstz", false);
		}
		String xydm = xmlgForm.getXydm();
		String xn   = xmlgForm.getXn();
		xmlgForm.setBmlb(service.getXyTzFs(xn, xydm));
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("jxj");
			topTr = service.queryXyJxjRsSzTitle();
			rs = (ArrayList<String[]>)service.queryXyJxjRsSz(queryModel);
		}
		//加载下拉列表
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("是");
		request.setAttribute("jxjList", service.getJxjList(model));
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		request.setAttribute("xydm",xmlgForm.getXydm());
		request.setAttribute("yxzje",service.getXyZje(xmlgForm.getXydm(),Base.getJxjsqxn()));//该学院该年总奖学金额
		request.setAttribute("xzfs",service.getJxjrstzxzrs().get("xzfs"));
		request.setAttribute("bmlbV",xmlgForm.getBmlb());//设置调整方式是按专业,还是班级
		
		//查询学院调整后的金额
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setXydm(xmlgForm.getXydm());
		queryModel.setXn(Base.getJxjsqxn());
		request.setAttribute("tzje", service.getXytzje(queryModel, xmlgForm
				.getBmlb()));
		
		return mapping.findForward("xyJxjrstz");
	}
	public ActionForward xyJxjrstzSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("jxj");
		service.XyJxjRsSzSave(queryModel);
		xmlgForm.setOperType("query");
		return xyJxjrstz(mapping, form, request, response);
	}
	public ActionForward  tzRsFsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException {//xyfpjxjfs
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();		
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("jxj");
		service.tzRsFsSzSave(queryModel);
		return new ActionForward("/xmlgpjpy.do?method=xyJxjrstz",false);
	}
	public ActionForward tzfsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();		
		BeanUtils.copyProperties(queryModel, xmlgForm);
	    //queryModel.setKey("jxj");
		service.tzfsUpdateSave(queryModel);
		return xyJxjrstz(mapping, form, request, response);
	}
	public ActionForward xyRychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXydm(userDep);
		xmlgForm.setXn(Base.getJxjsqxn());
		PjpyXmlgService service = new PjpyXmlgService();
		String userType = request.getSession().getAttribute("userType").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "rych";
		}				
		
		String act = request.getParameter("act");
		if ("jxj".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_csblsz.do", false);
		}
		
		String xydm = xmlgForm.getXydm();
		String xn   = xmlgForm.getXn();
		xmlgForm.setBmlb(service.getXyTzFs(xn, xydm));
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("rych");
			topTr = service.queryRychRsszTitle();
			rs = (ArrayList<String[]>)service.queryRychRssz(queryModel);
		}
		//加载下拉列表
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("是");
//		加载下拉列表
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		request.setAttribute("xydm",xmlgForm.getXydm());
		request.setAttribute("yxzje",service.getXyZje(xmlgForm.getXydm(),Base.getJxjsqxn()));//该学院该年总奖学金额
		request.setAttribute("xzfs",service.getJxjrstzxzrs().get("xzfs"));
		request.setAttribute("bmlb",xmlgForm.getBmlb());
		return mapping.findForward("xyRychrstz");
	}
	public ActionForward xyRychrstzSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("rych");
		service.XyRychRsSzSave(queryModel);
		xmlgForm.setOperType("query");
		return xyRychrstz(mapping, form, request, response);
	}
	/**荣誉称号奖学金申请默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);

		PjpyXmlgService service = new PjpyXmlgService();
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		
		//学生荣誉称号申请操作
		if ("stu".equalsIgnoreCase(userDepModel.getUserType())
				|| "student".equalsIgnoreCase(userDepModel.getUserType())) {
			xh = request.getSession().getAttribute("userName") != null ? 
					request.getSession().getAttribute("userName").toString(): "";
			request.setAttribute("showstu", "yes");
		}
		
		//保存荣誉称号申请信息
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXh(xh);
			model.setXn(xn);
			model.setNd(nd);
			model.setXq(Base.getJxjsqxq());
			boolean result = service.serv_addRychsqxx(model);
			appendOperResult(request, result);
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			queryModel.setXn(xn);
			queryModel.setXh(xh);
			//查询学生信息
			rs = CommonQueryDAO.getStuInfo(xh);
			//查询学生班级人数
			String bjrs = CommonQueryDAO.getBjrs(xh);
			rs.put("bjrs", bjrs);
			//查询学生综测排名信息
			zhszcpList = service.getStuZhszcpList(queryModel);
		}
		rs.put("xn", xn);
		rs.put("nd", nd);
		request.setAttribute("rs", rs);
		request.setAttribute("zhcpList", zhszcpList);		
		request.setAttribute("rychList", service.getRychList());
		return mapping.findForward("rychsqDefault");
	}
	/**
	 * 荣誉称号申请结果查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String userType = userDepModel.getUserType();
		//如果是学院用户
		
		if ("xy".equalsIgnoreCase(userType)) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryRychsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.serv_queryRychsqxx(model,userType);
		}
		//加载下拉列表
		request.setAttribute("path", "credit_result.do");
		FormModleCommon.commonRequestSet(request, "view_xsrychb", "xsrychb", rs, topTr);
		request.setAttribute("rychList", service.getRychList());
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("rychsqQuery");
	}
	/**
	 * 修改荣誉称号申请数据操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiRychsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		
		//保存修改信息
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.serv_modiRychsqxx(model,pkValue);
			appendOperResult(request, result);
			pkValue = model.getXh()+model.getXn()+model.getRychdm();
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {//这里是针对双击一行来控制的
			request.setAttribute("writable", "no");
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		queryModel.setPkValue(pkValue);
		//通过主键查询荣誉称号申请信息
		rs = service.viewRychsqxx(queryModel);
		
		//查询学生综测排名信息
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		rs.put("jlqk",rs.get("jcqk"));
		zhszcpList = service.getStuZhszcpList(queryModel);
		request.setAttribute("zhcpList", zhszcpList);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);		
		request.setAttribute("rychList", service.getRychList());
		return mapping.findForward("modiRychsqxx");
	}
	/**
	 * 删除荣誉称号申请数据操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.serv_deleteRychsqxx(model);
		appendDeleteResult(request, result);
		return rychsqQuery(mapping, form, request, response);
	}
		
	/**
	 * 奖学金申请表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.jxjPrint(pkValue, "jxj");
			
		} else {
			rs.put("xn", Base.getJxjsqxn());
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjPrint");
	}
	
	/**
	 * 荣誉称号审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// 学生无操作权限
			request.setAttribute("errMsg", "学生用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//如果是学院用户
		PjpyXmlgModel userDepModel = getXybmxx(request);
		
		String userType = userDepModel.getUserType();
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
			//这里处理的是加载各院系调整人数方式
			if (!StringUtils.isNull(xmlgForm.getXydm())) {
				String fpfs = service.getXyTzFs(Base.getJxjsqxn(), xmlgForm.getXydm());
				//调整人数方式
				request.setAttribute("fpfs", fpfs);
				if (!StringUtils.isNull(fpfs)) {
					request.setAttribute("fpfsxx", "荣誉称号审核将以荣誉称号，"
							+ ("zy".equalsIgnoreCase(fpfs) ? "专业" : ("bj"
									.equalsIgnoreCase(fpfs) ? "班级" : ""))
							+ "为单位进行审核;");
				}
				String szrs = "";
				PjpyXmlgModel model = new PjpyXmlgModel();
				model.setXn(Base.getJxjsqxn());
				model.setRychdm(xmlgForm.getRychdm());
				
				if ("zy".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getZydm());
					szrs = service.getXyJxtzrs(model, "rych");
				} else if ("bj".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getBjdm());
					szrs = service.getXyJxtzrs(model, "rych");
				}
				//调整的人数
				request.setAttribute("szrs", szrs);
				request.setAttribute("szrsxx", StringUtils.isNull(szrs)
						|| "0".equalsIgnoreCase(szrs) ? "" : "获奖名额为:" + szrs
						+ "人");
			}
		}
		
		//查询数据操作
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryRychShTitle();
			model.setUserType(userType);
			rs = (ArrayList<String[]>) service.serv_queryRychShxx(model,
					userType);
		}
		
		//加载下拉列表
		request.setAttribute("path", "credit_check.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("rychList", service.getRychList());		
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("shList", service.getShList());
		return mapping.findForward("rychsh");
	}
	/**
	 * 奖学金批量审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		String act = request.getParameter("act");
		if ("tg".equalsIgnoreCase(act)) {
			model.setSh("通过");
		} else if ("btg".equalsIgnoreCase(act)) {
			model.setSh("不通过");
		}
		model.setUserType(userDepModel.getUserType());
		
		//批量保存操作
		boolean result = service.saveJxjshxx(model, act, "rych", true);
		appendOperResult(request, result);
		return rychsh(mapping, form, request, response);
	}
	/**
	 * 荣誉称号单个审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String pkValue = request.getParameter("pkValue");
		String userType = userDepModel.getUserType();
		PjpyXmlgService service = new PjpyXmlgService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setKeys(new String[]{pkValue});
			model.setUserType(userDepModel.getUserType());
			//这个地方单个审核与批量审核调用的是同一个方法,所以这里第二个参数为空
			boolean result = service.saveJxjshxx(model, "", "rych", false);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		queryModel.setUserType(userDepModel.getUserType());
		rs = service.viewRychsqxx(queryModel);
		if (rs != null) {
			if("xy".equalsIgnoreCase(userType)){
				xmlgForm.setSh(rs.get("xysh"));
				xmlgForm.setYj(rs.get("xyyj"));
				//xmlgForm.setXyshyj(rs.get("xyyj"));			
			}else{
				xmlgForm.setSh(rs.get("xxsh"));
				xmlgForm.setYj(rs.get("xxyj"));
				xmlgForm.setXyshyj(rs.get("xyyj"));
			}
		}
		
		//这里控制的是学校审核通过后,学院不能再审核哟
		if ("xy".equalsIgnoreCase(userType) && "通过".equalsIgnoreCase(rs.get("xxsh"))) {
			request.setAttribute("writable", "no");
		}
		
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		rs.put("jlqk",rs.get("jcqk"));
		//加载列表
		request.setAttribute("zhcpList", service.getStuZhszcpList(queryModel));//综测排名列表
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userDepModel.getUserType());
		return mapping.findForward("rychdgsh");
	}
	
	/**
	 * 奖学金，荣誉称号院系推荐表，公示名单及金额汇总输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxhzsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		if (StringUtils.isNull(xmlgForm.getXn())) {
			xmlgForm.setXn(Base.getJxjsqxn());
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		//选择奖学金类别后刷新奖学金信息
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
			xmlgForm.setJxjmc("");
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxhzsc");
	}
	
	/**
	 * 奖学金金额汇总输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjehzsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		String[] jxjdmList = StringUtils.isNull(jxjdm) ? null : jxjdm.split("!@");
		String xydm = request.getParameter("xydm");
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(xn);
		model.setXydm(xydm);
		model.setKeys(jxjdmList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		
		//写出数据
		service.writeJxjjehz(wwb, model);
		return mapping.findForward("");
	}
	/**
	 * 名单输出
	 */
	public ActionForward mdscDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){		
		return mapping.findForward("mdscDefault");
	}
	/**
	 * 公示单输出
	 */
	public ActionForward gsdsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("是");
		String type = request.getParameter("type"); 
		//FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
//		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		request.setAttribute("rychList", service.getRychList());
		xmlgForm.setXn(Base.getJxjsqxn());
		if(type.equalsIgnoreCase("jxjgsd")){
			return mapping.findForward("jxjgsd");
		}else if(type.equalsIgnoreCase("rychgsd")){
			return mapping.findForward("rychgsd");
		}else{
			return mapping.findForward("pytjb");
		}		
	}
	/**
	 * 奖学金、荣誉称号公示单导出
	 * @throws Exception 
	 */
	public ActionForward jxjRychGsddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		String type = request.getParameter("type");
		xmlgForm.setXn(Base.getJxjsqxn());
		BeanUtils.copyProperties(model, xmlgForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		//写数据
		service.serv_jxjRychGsddc(wwb, model,type);
		return mapping.findForward("");
	}
	/**
	 * 评优推荐表导出
	 * @throws Exception 
	 */
	public ActionForward pytjbdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();		
		xmlgForm.setXn(Base.getJxjsqxn());
		BeanUtils.copyProperties(model, xmlgForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		//写数据
		service.serv_pytjb(wwb,model);
		return mapping.findForward("");
	}
		
	/**
	 * 奖学金金额汇总输出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjehzOutPut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		//查询出来未解析的奖学金金额汇总数据
		List<String[]> rs = service.queryJxjjehzData(model);
		
		//解析查询出来的结果与表头
		List<String[]> title = service.queryJxjjehzTitle(rs);
		List<String[]> result = service.parseQueryJxjjeResult(rs);
		
		request.setAttribute("rs", result);
		request.setAttribute("title", title);
		request.setAttribute("num", rs != null ? rs.size() : 1);
		request.setAttribute("xn", Base.getJxjsqxn());
		return mapping.findForward("jxjjehzOutPut");
	}
	
}
