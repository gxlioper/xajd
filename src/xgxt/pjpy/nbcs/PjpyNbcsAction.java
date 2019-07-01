package xgxt.pjpy.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import common.Globals;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 宁波城市评奖评优-action类
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

public class PjpyNbcsAction extends BasicAction {
	
	/**
	 * 评奖评优_问卷调查_问卷信息_管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_wjManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			
			this.deleteOperation(request, realTable);
			
			// 删除组卷信息
			boolean result = service.delZjxx(myForm, "id");

			if (result) {
				// 删除回答信息
				result = service.delHdxx(myForm, "wjbh");
			}
			
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "sfkq" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjManage");
	}
	
	/**
	 * 评奖评优_问卷调查_问卷信息_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_wjManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		// 当前年度
		String nd = Base.currNd;
		// 建立时间
		String jlsj = service.getNowTime("YYYYMMDD");
		String jlsjmc = service.getNowTime("YYYY年MM月DD日");
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		//保存数据成功标志
		boolean result = false;
		// 是否可见试题
		boolean isSt = ("view".equalsIgnoreCase(doType)) ? true : false;
		// =================end==================
	
		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			if (Base.isNull(id)) {// 增加
				result = service.saveWjInfo(myForm, realTable, request);
			} else {// 修改
				result = service.updateWjInfo(myForm, realTable, request);
			}
			request.setAttribute("result", result);
		}
		// =================end ===================
		
		// ===================执行查看操作 ======================
				
		// 增加
		if ("add".equalsIgnoreCase(doType)) {
			// 显示项
			rs.put("xn", xn);
			rs.put("xq", xq);
			rs.put("xqmc", xqmc);
			rs.put("nd", nd);
			rs.put("jlsj", jlsj);
			rs.put("jlsjmc", jlsjmc);
		}
		// 查看（修改）
		else if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) 
				|| result) {
			
			String pk = Base.isNull(pkValue) ? "xn||xq||nd||wjmc||jlsj" : "id";
			
			pkValue = Base.isNull(pkValue) ? myForm.getXn() + myForm.getXq()
					+ myForm.getNd() + myForm.getWjmc() + myForm.getJlsj()
					: pkValue;
					
			String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc",
					"nd", "wjmc", "xn", "xq", "xqmc", "sfkq" };

			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
			
			//获得问卷组卷信息
			if(!Base.isNull(pkValue) ){
				myForm.setId(pkValue);
				service.setWjZjInfo(myForm, request);			
			}
		}
		
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {"id","isSt"};

		// 其他字段值
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjUpdate");
	}
	
	/**
	 * 评奖评优_问卷调查_试题信息_管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_stxx";
		// 表名
		String realTable = "wjdc_stxxb";
		// 访问路径
		String path = "pjpy_stManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= 执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {

			// 删除试题
			boolean result = service.delStInfo(myForm);

			if (result) {
				// 删除答案
				result = service.delDaInfo(myForm);
			}
			
			if (result) {
				// 删除组卷信息
				result = service.delZjxx(myForm, "fpbh");
			}

			if (result) {
				// 删除回答信息
				result = service.delHdxx(myForm, "fpbh");
			}
			
			request.setAttribute("result", result);
			String message = result ? "删除成功" : "删除失败";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "stbh", "lxmc", "ssmc", "xsmc",
					"haveda" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			String[] outputColumn = null;
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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stManage");
	}
	
	/**
	 * 评奖评优_问卷调查_试题信息_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_stxx";
		// 表名
		String realTable = "wjdc_stxxb";
		// 访问路径
		String path = "pjpy_stManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		// 试题编号
		String stbh = service.getStbh();
		// 试题详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		//保存数据成功标志
		boolean result = false;
		// =================end==================
		
		// ===================执行查看操作 ======================
			
		//自动生成：试题编号
		rs.put("stbh", stbh);
		// 查看（修改）
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) 
				|| result) {
			
			String pk =  "stbh";

			String[] colList = new String[] { "bz", "stbh", "stss", "stlx",
					"stmc" };

			rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};

		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stUpdate");
	}
	
	/**
	 * 评奖评优_问卷调查_试题信息_保存
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 表名
		String realTable = "wjdc_stxxb";
		// 保存数据成功标志
		boolean result = false;
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			// 保存试题
			result = service.saveStInfo(myForm, realTable, request);
			// 保存试题答案
			if (result) {
				result = service.saveDaInfo(myForm);
			}
		}
		// =================end ===================

		return stUpdate(mapping, form, request, response);
	}

	/**
	 * 评奖评优_问卷调查_组卷管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "";
		// 表名
		String realTable = "wjdc_zjb";
		// 访问路径
		String path = "pjpy_zjManage.do";
		// =================end==================
		
		// ================= 执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean result =service.saveZjInfo(myForm,realTable);
			
			String message = result ? "操作成功" : "操作失败";
			
			request.setAttribute("result", result);
			request.setAttribute("message", message);
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

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("zjManage");
	}

	/**
	 * 评奖评优_问卷调查_回答问卷_管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_hdwjManage.do";
		//学年
		String xn = Base.currXn;
		// 学期
		String xq = Base.currXq;
		// 年度
		String nd = Base.currNd;
		// 学校代码
		String xxdm = Base.xxdm;
		// 宁波城市
		if (Globals.XXDM_NBCSZYJSXY.equalsIgnoreCase(xxdm)) {
			myForm.setQueryequals_xn(xn);
			myForm.setQueryequals_xq(xq);
			myForm.setQueryequals_nd(nd);
		}
		//错误信息提示
		String msg = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================权限控制 ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			msg = "问卷只能由学生进行回答";
		} 
		// =================end ===================
		
		// ==================执行查询操作 ==================
		if (search) {

			myForm.setXh(userName);

			String[] outputValue = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "hdnum" };
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, outputValue, null);

			ArrayList<String[]> rs = service.getWjhdList(myForm);
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			if (rs != null && rs.size() > 0) {
				request.setAttribute("rsNum", rs.size());
			}
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		
		String[] qtzd = new String[] { "msg" };
		String[] qtzdz = new String[] { msg };
		
		RequestForm rForm = new RequestForm();

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
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjManage");
	}

	/**
	 * 评奖评优_问卷调查_回答问卷_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_hdwjManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		// id
		String id = request.getParameter("id");
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 是否可见试题
		boolean isSt = true;
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		// 获得问卷组卷信息

		// 构建参数
		myForm.setId(pkValue);
		myForm.setXhzgh(userName);
		myForm.setLx(userType);
		myForm.setWjbh(pkValue);

		// 试题情况
		service.setWjZjInfo(myForm, request);


		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "id", "isSt" };

		// 其他字段值
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt) };

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjUpdate");
	}
	
	/**
	 * 评奖评优_问卷调查_答案_保存
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward daSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 表名
		String realTable = "wjdc_hdb";
		// 保存数据成功标志
		boolean result = false;
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setRealTable(realTable);

		// ===================end ====================
		
		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			// 保存试题
			result = service.saveHdzDa(myForm, rForm);
			request.setAttribute("result", result);
		}
		// =================end ===================

		return hdwjUpdate(mapping, form, request, response);
	}

	/**
	 * 评奖评优_问卷调查_回答统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_hdtjManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx","hdnum" };
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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjManage");
	}
	
	/**
	 * 评奖评优_问卷调查_回答问卷_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_wjxx";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_wjManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		// id
		String id = request.getParameter("id");
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// 统计年级
		String nj = request.getParameter("nj");
		// 统计学院
		String xydm = request.getParameter("xy");
		// 统计专业
		String zydm = request.getParameter("zy");
		// 统计班级
		String bjdm = request.getParameter("bj");
		// 统计性别
		String xb = request.getParameter("xb");
		// 统计政治面貌
		String zzmm = request.getParameter("zzmm");
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "id";
		
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		// 设置统计信息
		myForm.setId(pkValue);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// 执行统计
		service.setWjZjInfo(myForm, request);
		
		// =================end ===================

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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjUpdate");
	}
	
	/**
	 * 评奖评优_问卷调查_回答结果_管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward hdpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjjg";
		// 表名
		String realTable = "wjdc_wjxxb";
		// 访问路径
		String path = "pjpy_hdpjManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
		}
		//	=================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdpjManage");
	}
	
	/**
	 * 评奖评优_问卷调查_回答结果_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward hdpjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_wjdc_wjpj";
		// 访问路径
		String path = "pjpy_hdpjManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "pk";
		
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc","xh","lx" };

		rs = service.getPjpyInfo(tableName, pk, pkValue, colList);

		//设置统计信息
		myForm.setId(rs.get("id"));
		myForm.setWjbh(rs.get("id"));
		myForm.setXhzgh(rs.get("xh"));
		myForm.setLx(rs.get("lx"));
	
		service.setWjZjInfo(myForm, request);
		
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdpjUpdate");
	}
	
	/**
	 * 评奖评优_问卷调查_问卷分配_管理
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

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjfp";
		// 表名
		String realTable = "wjdc_wjfpb";
		// 访问路径
		String path = "pjpy_wjfpManage.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
		}
		//	=================end==================
		
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
			String[] outputColumn = { "pk", "xn", "nd", "xqmc", "nj", "xymc",
					"zymc", "bjmc", "wjmc" };
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

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}
	
	/**
	 * 评奖评优_问卷调查_问卷分配_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "pjpy_wjfpManage.do";
		// 主键值
		String pkValue = request.getParameter("pk");
		//当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		//当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		//当前学期
		String xq=Base.currXq;
		myForm.setXq(xq);
		//提示信息
		String message = "";
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			message = resault ? "分配成功" : "分配失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		String[] qtzd = new String[]{"message"};
		String[] qtzdz = new String[]{message};
		
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
	 * 评奖评优_问卷调查_报表统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyNbcsService service = new PjpyNbcsService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		// 导出类型
		String lx = request.getParameter("lx");
		// 主键值
		String pkValue = request.getParameter("pk");
		// 问卷ID
		String id = request.getParameter("id");
		id = Base.isNull(id) ? pkValue : id;
		myForm.setId(id);
		// 统计年级
		String nj = request.getParameter("nj");
		myForm.setNj(nj);
		// 统计学院
		String xydm = request.getParameter("xy");
		myForm.setXydm(xydm);
		// 统计专业
		String zydm = request.getParameter("zy");
		myForm.setZydm(zydm);
		// 统计班级
		String bjdm = request.getParameter("bj");
		myForm.setBjdm(bjdm);
		// 统计性别
		String xb = request.getParameter("xb");
		myForm.setXb(xb);
		// 统计政治面貌
		String zzmm = request.getParameter("zzmm");
		myForm.setZzmm(zzmm);
		// =================end==================

		// =================执行导出Excel操作==================

		response.reset();
		response.setContentType("application/vnd.ms-excel");

		if ("hsqk".equalsIgnoreCase(lx)) {// 回收情况
			
			service.hsqkToExcel(myForm, response.getOutputStream());
			
		}else if("jgtj".equalsIgnoreCase(lx)){// 结果统计
			service.jgtjToExcel(myForm, response.getOutputStream(),request);
		}
		
		// =================end==================

		return null;

	}

}