package xgxt.wjdc;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;

import common.GlobalsVariable;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 问卷调查-action类
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

public class WjdcAction extends WjdcRealizAtion {

	// ======================我是心里普查======================================

	/**
	 * 问卷信息_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_wjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷信息_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_wjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题信息_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题信息_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题组卷（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_zjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷分配（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_wjfpManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷分配设置（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "xlpc_wjfpManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答问卷_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答问卷_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "xlpc_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// ID
		String id = request.getParameter("id");
		// 类型
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答统计_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "xlpc_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答统计_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "xlpc_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答结果_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "xlpc_hdjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答结果_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjpj";
		// 表名
		String realTable = "";
		// 访问路径
		String path = "xlpc_hdjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 分析结果_管理（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcFxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_fxjg";
		// 表名
		String realTable = "wjdc_jgfxb";
		// 访问路径
		String path = "xlpc_fxjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return fxjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 分析结果_维护（心里普查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward xlpcFxjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_fxjg";
		// 表名
		String realTable = "";
		// 访问路径
		String path = "xlpc_fxjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_XLPC;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return fxjgUpdate(mapping, myForm, rForm, request, response);
	}

	// ======================我是心里普查 end==================================

	// ======================思想状况调查======================================

	/**
	 * 问卷信息_管理（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_wjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷信息_维护（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_wjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题信息_管理（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题信息_维护（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 试题组卷（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_zjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷分配（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_wjfpManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷分配设置（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "sxzk_wjfpManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答问卷_管理（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答问卷_维护（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "sxzk_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// ID
		String id = request.getParameter("id");
		// 类型
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答统计_管理（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "sxzk_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答统计_维护（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "sxzk_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答结果_管理（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxzk_hdjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * 回答结果_维护（思想状况调查）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward sxzkHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjpj";
		// 表名
		String realTable = "";
		// 访问路径
		String path = "sxzk_hdjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXZK;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}

	// ======================思想状况调查 end =================================

	// ======================通用======================================
	/**
	 * 问卷调查_试题信息_保存
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward stSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 表名
		String realTable = "wjdc_stxxb";
		// 建立时间
		String jlsj = myForm.getJlsj();
		// 是否添加

		if (Base.isNull(jlsj)) {
			jlsj = service.getNowTime("YYYYMMDD");
			myForm.setJlsj(jlsj);
		}
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
			rForm.setMessage(result ? "保存成功!" : "保存失败!");
		}
		// =================end ===================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷调查_答案_保存
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward daSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图
		String tableName = request.getParameter("tableName");
		// 表名
		String realTable = "wjdc_hdb";
		// 路径
		String path = request.getParameter("path");
		// 保存数据成功标志
		boolean result = false;
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		// ===================end ====================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			// 保存试题
			result = service.saveHdzDa(myForm, rForm);
			String message = result ? "操作成功" : "操作失败";
			rForm.setMessage(message);

		}
		// =================end ===================

		return hdwjUpdate(mapping, myForm, rForm, request, response);
	}

	/**
	 * 问卷调查_报表统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bbtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

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

		} else if ("jgtj".equalsIgnoreCase(lx)) {// 结果统计
			service.jgtjToExcel(myForm, response.getOutputStream(), request);
		}

		// =================end==================

		return null;

	}
	// ======================通用 end======================================
	
	
	
	
	
	
	//====================实习生状况调查========================
	/**
	 * 实习生状况调查-问卷维护 管理
	 */
	public ActionForward sxszkWjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_wjManage.do";
		// 模块类型(实习生状况)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * 实习生状况调查-问卷维护 维护
	 */
	public ActionForward sxszkWjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_wjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return wjUpdate(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * 实习生状况调查-题目维护 管理
	 */
	public ActionForward sxszkStManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stManage(mapping, myForm, rForm, request, response);
	}
	

	/**
	 * 实习生状况调查-题目维护
	 */
	public ActionForward sxszkStUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_stManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setMklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return stUpdate(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * 实习生状况调查-组卷维护
	 */
	public ActionForward sxszkZjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_zjManage.do";
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setPk(pk);

		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return zjManage(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * 实习生状况调查-问卷分配管理
	 */
	public ActionForward sxszkWjfpManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_wjfpManage.do";
		// 模块类型(实习生状况)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * 实习生状况调查-问卷分配设置
	 */
	public ActionForward sxszkWjfpUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "sxszk_wjfpManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return wjfpUpdate(mapping, myForm, rForm, request, response);
	}


	/**
	 * 实习生状况调查-回答问卷管理
	 */
	public ActionForward sxszkHdwjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdwjManage(mapping, myForm, rForm, request, response);
	}


	/**
	 * 实习生状况调查-回答问卷维护
	 */
	public ActionForward sxszkHdwjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();
		// ================= 赋初值 ==================
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
		// 主键
		String pk = request.getParameter("pk");
		// 访问路径
		String path = "sxszk_hdwjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// ID
		String id = request.getParameter("id");
		// 类型
		String lx = request.getParameter("lx");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setLx(lx);
		myForm.setId(id);
		// ================= end ==================

		//学生基本信息
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> stuInfo = xsxxglService.selectStuinfo(userName);
		request.setAttribute("stuInfo", stuInfo);
		
		return hdwj(mapping, myForm, rForm, request, response);
	}

	
	
	/**
	 * 实习生状况调查-回答问卷
	 */
	private ActionForward hdwj(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = rForm.getTableName();
		// 主键值
		String pkValue = rForm.getPk();
		// id
		String id = myForm.getId();
		pkValue = Base.isNull(pkValue) ? id : pkValue;
		// 类型
		String lx = myForm.getLx();
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 是否可见试题
		boolean isSt = true;
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "id";
		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "kyxg", "dawk","jwy" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);
		
		//学生基本情况
		rs.putAll(service.getWjdcInfo("xg_wjdc_xsjbqkb", "xh||wjid", 
				rForm.getUserName()+pkValue, new String[]{"sxdwmc","gzdd","gzgw","lxfs"}));
		
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

		// 其他字段
		String[] qtzd = new String[] { "id", "isSt", "bclx" };

		// 其他字段值
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt), lx };

		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwj");
	}

	
	
	/**
	 * 实习生状况调查-回答问卷保存
	 */
	public ActionForward hdwjSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		WjdcService service = new WjdcService();
		WjdcForm myForm = (WjdcForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图
		String tableName = request.getParameter("tableName");
		// 表名
		String realTable = "wjdc_hdb";
		// 路径
		String path = request.getParameter("path");
		// 保存数据成功标志
		boolean result = false;
		// =================end==================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		// ===================end ====================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			//保存实习生基本情况
			myForm.setXh(userName);
			myForm.setWjid(myForm.getId());
			result = service.saveSxsjbqk(myForm,request);
			// 保存试题
			result = result ? service.saveHdzDa(myForm, rForm) : result;
			String message = result ? "操作成功" : "操作失败";
			rForm.setMessage(message);

		}
		// =================end ===================

		return hdwj(mapping, myForm, rForm, request, response);
	}
	
	
	
	/**
	 * 实习生状况调查-回答统计
	 */
	public ActionForward sxszkHdtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "sxszk_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdtjManage(mapping, myForm, rForm, request, response);
	}

	
	/**
	 * 实习生状况调查-回答统计
	 */
	public ActionForward sxszkHdtjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String realTable = "";
		// 访问路径
		String path = "sxszk_hdtjManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");
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

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		myForm.setNj(nj);
		myForm.setXydm(xydm);
		myForm.setZydm(zydm);
		myForm.setBjdm(bjdm);
		myForm.setXb(xb);
		myForm.setZzmm(zzmm);
		// ================= end ==================

		return hdtjUpdate(mapping, myForm, rForm, request, response);
	}
	
	

	/**
	 * 实习生状况调查-回答结果
	 */
	public ActionForward sxszkHdjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
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
		String path = "sxszk_hdjgManage.do";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);

		myForm.setQueryequals_mklx(mklx);
		// ================= end ==================

		return hdjgManage(mapping, myForm, rForm, request, response);
	}
	
	
	/**
	 * 实习生状况调查-回答结果
	 */
	public ActionForward sxszkHdjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		WjdcForm myForm = (WjdcForm) form;
		RequestForm rForm = new RequestForm();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_wjdc_wjpj";
		// 表名
		String realTable = "";
		// 模块类型(心理普查)
		String mklx = GlobalsVariable.WJDC_SXSZK;
		// 主键
		String pk = request.getParameter("pk");
		// ID
		String id = request.getParameter("id");

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPk(pk);

		myForm.setQueryequals_mklx(mklx);
		myForm.setId(id);
		// ================= end ==================

		return hdjgUpdate(mapping, myForm, rForm, request, response);
	}
}
