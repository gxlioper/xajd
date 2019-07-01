package xgxt.pjpy.nbcs.pxpj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 宁波城市学生品行评价-action类
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

public class PxpjNbcsAction extends BasicAction {

	/**
	 * 学生品行评价_答卷人_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward djrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 是否辅导员
		String isFdy = session.getAttribute("isFdy").toString();
		// 是否班主任
		String isBzr = session.getAttribute("isBzr").toString();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_pxpj_djr";
		// 表名
		String realTable = "pxpj_djrb";
		// 访问路径
		String path = "pjpy_pxpj_djrManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrManage");
	}

	/**
	 * 学生品行评价_答卷人_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward djrUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 是否辅导员
		String isFdy = session.getAttribute("isFdy").toString();
		// 是否班主任
		String isBzr = session.getAttribute("isBzr").toString();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_pxpj_djrManage.do";
		// 表名
		String realTable = "pxpj_djrb";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 提示信息
		String message = "";
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveDjr(myForm, realTable);
			message = resault ? "分配成功" : "分配失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn", "isxy" };
		String[] qtzdz = new String[] { message, xn, String.valueOf(isxy) };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrUpdate");
	}
	
	/**
	 * 学生品行评价_答卷人_比例设置
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward djrBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_pxpj_djrManage.do";
		// 表名
		String realTable = "pxpj_djrblb";
		// 当前学年
		String xn = Base.currXn;
		// 提示信息
		String message = "";
		// 比例信息
		String[] colList = new String[] { "bl", "bz" };
		HashMap<String, String> rs = service.getPjpyInfo(realTable, "xn", xn,
				colList);
		rs.put("xn", xn);
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveDjrbl(myForm, realTable, request);
			message = resault ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn" };
		String[] qtzdz = new String[] { message, xn };
		
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("djrBlsz");
	}
	
	/**
	 * 学生品行评价_问卷分配_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 是否辅导员
		String isFdy = session.getAttribute("isFdy").toString();
		// 是否班主任
		String isBzr = session.getAttribute("isBzr").toString();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_pxpj_fpjg";
		// 表名
		String realTable = "pxpj_wjfpb";
		// 访问路径
		String path = "pjpy_pxpj_wjfp.do";
		//当前学年
		String xn = Base.currXn;
		myForm.setQueryequals_xn(xn);
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc", "wjmc", "fpzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}

	/**
	 * 学生品行评价_问卷分配_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 是否辅导员
		String isFdy = session.getAttribute("isFdy").toString();
		// 是否班主任
		String isBzr = session.getAttribute("isBzr").toString();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_pxpj_djrManage.do";
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 提示信息
		String message = "";
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			message = resault ? "分配成功" : "分配失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "message", "xn", "isxy" };
		String[] qtzdz = new String[] { message, xn, String.valueOf(isxy) };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpUpdate");
	}
	
	/**
	 * 学生品行评价_品行评价_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pxpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxglService stuService = new XsxxglService();
		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_pxpj_wjfp";
		// 表名
		String realTable = "pxpj_wjfpb";
		// 访问路径
		String path = "pjpy_pxpj_pxpj.do";
		// 当前学年
		String xn = Base.currXn;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 错误信息提示
		String msg = "";
		// 是否答卷人
		boolean isDjr = false;
		// =================end==================

		// ================= 权限控制 ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "问卷只能由学生进行回答";
		}else {
			// 学生基本信息
			HashMap<String, String> stuInfo = stuService
					.selectStuinfo(userName);
			stuInfo.put("xn", xn);
			request.setAttribute("stuInfo", stuInfo);
			
			myForm.setXh(userName);
			myForm.setXn(xn);
			isDjr = service.isDjr(myForm);
		}
		// =================end==================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "msg", "isDjr" };
		String[] qtzdz = new String[] { msg, String.valueOf(isDjr) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("pxpjManage");
	}
	
	/**
	 * 学生品行评价_品行评价_回答
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pxpjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		myForm.setLx(userType);
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		myForm.setXhzgh(userName);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "pxpj_hdb";
		// 访问路径
		String path = "pjpy_pxpj_pxpj.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		
		// 问卷编号与被评价人
		HashMap<String, String> map = service.getPjpyInfo("pxpj_wjfpb",
				"fpxh||id", pkValue, new String[] { "id", "fpxh" });

		String id = map.get("id");
		id = Base.isNull(id) ? request.getParameter("id") : id;
		
		String bpjr = map.get("fpxh");
		bpjr = Base.isNull(bpjr) ? request.getParameter("bpjr") : bpjr;
		
		myForm.setBpjr(bpjr);
		myForm.setWjbh(id);
		myForm.setId(id);
		
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, id, colList);

		// 试题情况
		service.setWjZjInfo(myForm, request);

		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "id", "bpjr" };

		// 其他字段值
		String[] qtzdz = new String[] { id, bpjr };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			
			// 保存试题
			boolean result = service.savePxpj(myForm, rForm);
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("pxpjUpdate");
	}

	/**
	 * 学生品行评价_评价结果_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 是否辅导员
		String isFdy = session.getAttribute("isFdy").toString();
		// 是否班主任
		String isBzr = session.getAttribute("isBzr").toString();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_pxpj_fpjg";
		// 表名
		String realTable = "pxpj_wjfpb";
		// 访问路径
		String path = "pjpy_pxpj_pjjg.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType) && !(Boolean.parseBoolean(isFdy))
				&& !(Boolean.parseBoolean(isBzr))) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
			expPageData(request, response, "", tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputValue = { "pk", "nj", "xymc", "zymc", "bjmc","xn",
					"wjmc" };
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, outputValue, null);

			List<HashMap<String, String>> rs = service.getTjjgList(myForm);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			if (rs != null && rs.size() > 0) {
				request.setAttribute("rsNum", rs.size());
			}
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "isxy" };
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "pxpj");
		// =================end ===================

		return mapping.findForward("pjjgManage");
	}

	/**
	 * 学生品行评价_评价结果_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward pjjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PxpjNbcsService service = new PxpjNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 主键
		String pkValue = request.getParameter("pk");
		// 操作表
		String realTable = "pxpj_wjpjfb";
		// 访问路径
		String path = "pjpy_pxpj_pjjg.do";	
		//提示信息
		String message = "";
		// =================end==================

		// =================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.savePjf(myForm, realTable);
			message = resault ? "操作成功" : "操作失败";
		}
		// =================end==================
		

		// =================执行查看操作 ==================
		// 问卷相关信息
		HashMap<String, String> map = service.getPjpyInfo("view_pxpj_bjqk",
				"pk", pkValue, new String[] { "bjdm", "bjmc", "id", "wjmc",
						"xn" });
		// 学年
		String xn = map.get("xn");
		// 问卷编号
		String wjbh = map.get("id");
		wjbh = Base.isNull(wjbh) ? myForm.getWjbh() : wjbh;
		// 班级代码
		String bjdm = map.get("bjdm");
		// 班级名称
		String bjmc = map.get("bjmc");
		// /问卷名称
		String wjmc = map.get("wjmc");
		
		// 学生评价列表
		myForm.setBjdm(bjdm);
		myForm.setWjbh(wjbh);
		myForm.setXn(xn);
		List<HashMap<String, String>> rsList = service.getXsxxList(myForm);
		List<HashMap<String, String>> cfList = service.getXscfList(myForm);
		// =================end==================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[] { "wjbh", "bjmc", "wjmc", "xn", "message" };
		String[] qtzdz = new String[] { wjbh, bjmc, wjmc, xn, message };

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRsList(rsList);

		request.setAttribute("cfList", cfList);
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("pjjgUpdate");
	}
}