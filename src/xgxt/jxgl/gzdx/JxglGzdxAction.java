package xgxt.jxgl.gzdx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 贵州大学学生信息管理军训管理-action类
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

public class JxglGzdxAction extends BasicAction {

	/**
	 * 军训管理_免缓训_申请
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_jxgl_mhxsq";
		// 表名
		String realTable = "jxgl_mhxsqb";
		// 访问路径
		String path = "jxgl_mhxsq.do";
		// 当前学年
		String xn = Base.currXn;
		// 学号
		String xh = request.getParameter("xh");
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// =================执行保存操作=================
		if ("save".equalsIgnoreCase(doType)) {
			this.insertOperation(request, realTable);
			xh = myForm.getSave_xh();
		}
		// ===================end====================
			
		
		// =================初始化学生信息=================
		HashMap<String, String> rs = new HashMap<String, String>();
		
		// 登陆用户为学生
		if ("stu".equalsIgnoreCase(userType)) {
			xh = userName;
		}
		
		// 设置参数
		myForm.setXh(xh);
		myForm.setXn(xn);
		String pkValue = xh + xn;
		
		// 显示查询单条数据
		selectPageDataByOne(request, realTable, tableName, pkValue);
		
		rs = (HashMap<String, String>) request.getAttribute("rs");
		
		if (Base.isNull(rs.get("xh"))) {
			rs = service.getMhxSqInfo(myForm);
		}
		
		//籍贯
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			String[] arrDq = jg.split("/");
			rs.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
			rs.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
			rs.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}

		
		// ===================end====================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName("view_jxmdxx");
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================
		
		// ==================初始化request的值====================
		service.setList(myForm, request, "mhx");
		//================== end ====================


		return mapping.findForward("mhxsq");
	}
	
	/**
	 * 军训管理_免缓训_审核
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ===================== 赋初值 ===================
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_gzdx_jxgl_mhxsq";
		String realTable = "jxgl_mhxsqb";
		String path = "jxgl_mhxsh.do";
		String mklx = "sh";

		JxglTyForm myForm = (JxglTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		// =================end ===================
		return mhxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 军训管理_免缓训_结果
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward mhxjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//================== 赋初值===================
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_gzdx_jxgl_mhxsq";
		String realTable = "jxgl_mhxsqb";
		String path = "jxgl_mhxjg.do";
		String mklx = "jg";

		JxglTyForm myForm = (JxglTyForm) form;

		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		// =================end ===================

		return mhxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 军训管理_免缓训_管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxManage(ActionMapping mapping, JxglTyForm myForm,
			RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================== 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 登陆者部门
		String userDep = rForm.getUserDep();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 访问路径
		String path = rForm.getDoType();
		// 模块类型
		String mklx = rForm.getMklx();
		//审核字段
		String shzd = "";
		//审核结果
		String shjg = request.getParameter("shjg");
		// 主键;
		String pk = request.getParameter("pk");
		//当前学年
		String xn = Base.currXn;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================
		
		//==================判断模块,权限======================
		
		// 审核
		if ("sh".equalsIgnoreCase(mklx)) {
			myForm.setQueryequals_xn(xn);
			// 登陆用户为学校
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xysh("通过");
				shzd = "xxsh";
			}
			// 登陆用户为学院
			else if ("xy".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xydm(userDep);
				shzd = "xysh";
			}

		}else{
			// 登陆用户为学院
			 if ("xy".equalsIgnoreCase(userType)) {
				myForm.setQueryequals_xydm(userDep);
			}
				// 登陆用户为学生
				else if ("stu".equalsIgnoreCase(userType)) {
					myForm.setQuerylike_xh(userName);
				}
		}
		
		// =================end ===================
		
		// ==================执行审核操作 ==================
		if ("sh".equalsIgnoreCase(doType)) {		
			request.setAttribute("shzd", shzd);
			request.setAttribute("shjg", shjg);
			auditingBatchOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "lxmc", "xysh", "xxsh" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = { "bjdm", "bjmc", "bz", "jg", "jsxm",
					"lddm", "ldmc", "lxmc", "nj", "pk", "sfzh", "sqlx", "sqly",
					"sqsj", "xb", "xh", "xm", "xn", "xxsh", "xxshsj", "xxshyj",
					"xydm", "xymc", "xysh", "xyshsj", "xyshyj", "zydm", "zymc" };
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		
		// ===============初始化request的值 =====================	
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "mhx");
		// =================end ===================

		return mapping.findForward("mhxManage");
	}

	/**
	 * 军训管理_免缓训_维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_jxgl_mhxsq";
		// 表名
		String realTable = "jxgl_mhxsqb";
		// 访问路径
		String path = "jxgl_mhxsq.do";
		// 标题
		String title = "军训管理 - 免缓训维护";
		// 当前学年
		String xn = Base.currXn;
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 主键值
		String pkValue = request.getParameter("pk");
		// 学号
		String xh = request.getParameter("xh");
		// 审核字段
		String shzd = "";
		// 免缓训申请者信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		// =================权限控制=================
		if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {
			shzd = "xxsh";
		}else{
			shzd = "xysh";
		}
		
		// ==================执行审核操作 ==================
		if ("sh".equalsIgnoreCase(doType)) {
			doType = "update";
			this.updateOperation(request, realTable);
		}
		// =================end==================
		
		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			doType = "add";
			this.insertOperation(request, realTable);
			xh = myForm.getSave_xh();
		}
		// =================end==================
		
		// ==================执行增加操作 ==================
		if ("add".equalsIgnoreCase(doType)) {
			
			pkValue = xh + xn;
			myForm.setXh(xh);
			myForm.setXn(xn);
			
			if(!Base.isNull(xh)){

				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");

				if (Base.isNull(rs.get("xh"))) {
					rs = service.getMhxSqInfo(myForm);
					rs.put("save_xysh", "未审核");
					rs.put("save_xxsh", "未审核");
				}
			}
			rs.put("xn", xn);
			request.setAttribute("rs", rs);
		}
		// =================end==================
		
		//==================执行查看操作 ==================
		if ("view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			// 显示查询单条数据
			selectPageDataByOne(request, realTable, tableName, pkValue);
			
			rs = (HashMap<String, String>) request.getAttribute("rs");
		}
		// =================end ===================

		// ==================设置籍贯 ==================
		// 籍贯
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			String[] arrDq = jg.split("/");
			rs.put("jgshen", (arrDq.length >= 1) ? arrDq[0] : "");
			rs.put("jgshi", (arrDq.length >= 2) ? arrDq[1] : "");
			rs.put("jgxian", (arrDq.length >= 3) ? arrDq[2] : "");
		}
		// =================end ===================
		
		// ===============初始化request的值 =====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {"shzd"};

		// 其他字段值
		String[] qtzdz = new String[] {shzd};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName("view_jxmdxx");
		rForm.setRealTable(realTable);
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "mhx");
		// =================end ===================

		return mapping.findForward("mhxUpdate");
	}
	
	/**
	 * 军训管理_免缓训_打印报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mhxPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {


		JxglTyForm myForm = (JxglTyForm) form;
		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_jxgl_mhxsq";
		// 表名
		String realTable = "jxgl_mhxsqb";
		// 访问路径
		String path = "jxgl_mhxsq.do";
		// 主键
		String pkValue = request.getParameter("pkValue");
		// =================end==================
		
		// ================= 赋初值 ==================
		// 显示查询单条数据
		selectPageDataByOne(request, realTable, tableName, pkValue);

		HashMap<String, String> rs = (HashMap<String, String>) request
				.getAttribute("rs");
		
		// 籍贯
		String jg = rs.get("jg");
		if (!Base.isNull(jg)) {
			rs.put("jg", service.getJgInfo(jg));
		}
		// =================end==================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================
		
		return mapping.findForward("mhxPrint");
	}

	/**
	 * 军训管理_信息维护_技能成绩管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jncjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();
		JxglTyForm myForm = (JxglTyForm) form;
		
		//	================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_jxgl_jncj";
		// 表名
		String realTable = "gzdx_jxgl_jncjb";
		// 访问路径
		String path = "jxgl_jxjncj.do";
		// 当前学年
		String xn = Base.currXn;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//学校代码
		String xxdm = Base.xxdm;
		// =================end==================
		
		// ==================判断模块,权限======================
		// 设置当前学年
		if (Base.isNull(myForm.getQueryequals_xn())) {
			myForm.setQueryequals_xn(xn);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end ===================
		

		// ==================执行保存操作======================
		if ("save".equalsIgnoreCase(doType)) {
			HashMap<String, String[]> primaryArrayMap = this.getValueArrayMap(request, "primarykey_");
			HashMap<String, String> primaryMap = new HashMap<String, String>();
			primaryMap.put("xn", myForm.getQueryequals_xn());
			HashMap<String, String> tableMap = new HashMap<String, String>();
			tableMap.put("tableName", realTable);
			tableMap.put("viewName", tableName);
			this.savePageDataBatch(request, primaryArrayMap, primaryMap, tableMap);
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc","ldmc","cj" };
			
			//南宁职业技术学院
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				outputColumn =new String[]{ "pk", "xn", "xh","sfzh", "xm", "xb", "nj",
						"xymc", "zymc", "bjmc","cj" };
			}
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jxcj");
		// =================end ===================

		return mapping.findForward("jncjManage");
	}
	
	/**
	 * 军训管理_信息维护_成绩查看
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjckManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglGzdxService service = new JxglGzdxService();
		JxglGzdxModel model = new JxglGzdxModel();
		JxglTyForm myForm = (JxglTyForm) form;
		
		//	================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) request.getSession().getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_jxgl_cjck";
		// 表名
		String realTable = "gzdx_jxgl_jncjb";
		// 访问路径
		String path = "jxgl_jxcjck.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//学校代码
		String xxdm=Base.xxdm;
		// =================end==================
		
		// ==================判断模块,权限======================
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end ===================
		
		//==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
			if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				outputColumn =new String[] { "pk", "xn", "xh", "xm","sfzh", "xb", "nj",
						"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
			}
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = { "pk", "xn", "xh", "xm", "xb", "nj",
					"xymc", "zymc", "bjmc", "ldmc", "llcj", "jncj" };
			expPageData(request, response, realTable, tableName, outputColumn);
			return mapping.findForward("");
		}
		// =================end ===================
		 
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jxcj");
		// =================end ===================

		return mapping.findForward("cjckManage");
	}
}
