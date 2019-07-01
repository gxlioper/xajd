package xgxt.xszz.hndx;

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

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.dtjs.czxx.CzxxDtjsForm;
import xgxt.dtjs.czxx.dyxx.DyxxService;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.pjpy.zjjt.PjpyZjjtModel;
import xgxt.pjpy.zjjt.PjpyZjjtService;
import xgxt.studentInfo.service.StudentInfoService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 海南大学学生资助管理-action类
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

public class XszzHndxAction extends BasicAction {

	/**
	 * 学生资助_经济困难生认定_申请
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzTyForm myForm = (XszzTyForm) form;
		XszzHndxService service = new XszzHndxService();
		
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xsjbxx";
		// 表名
		String realTable = "hndx_xszz_jjknsrd";
		// 访问路径
		String path = "xszz_cssz.do";
		// 主键
		String pk = request.getParameter("pk");
		// 主键值
		String pkValue = request.getParameter("pkValue");
		//当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//保存成功与否
		boolean result = false;
		// =================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			ArrayList<String[]> rs = service.getXzXyRsList(myForm);
			List<HashMap<String, String>> topTr = service.getTopTr("cssz");
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		
		RequestForm rForm = new RequestForm();
		
		// 其他字段
		String[] qtzd = new String[] {};

		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("csszManage");
	}
	
	/**
	 * 评奖评优_参数设置_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward csszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzTyForm myForm = (XszzTyForm) form;
		XszzHndxService service = new XszzHndxService();
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 标题
		String title = "参数设置 - "+Base.YXPZXY_KEY+"比例设置";
		// 当前学年
		String xn = Base.currXn;
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 视图名
		String tableName = ("kg".equalsIgnoreCase(mklx)) ? "hndx_xszz_kgszb"
				: "hndx_xszz_xyrsb";
		// 表名
		String realTable = ("kg".equalsIgnoreCase(mklx)) ? "hndx_xszz_kgszb"
				: "hndx_xszz_xyrsb";
		// 提示信息
		String message = "";
		// 主键
		String pkValue = request.getParameter("pk");
		// 项目名称
		String xmmc =request.getParameter("xmmc");
		// 保存成功与否
		boolean result = false;
		// 操行分设置相关信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		// ================= 执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			// 批量保存
			if("all".equalsIgnoreCase(mklx)){
				result = service.saveJjknsSz(myForm, realTable);
				message = result ? "操作成功" : "操作失败";		
			}
			// 开关设置
			else if("kg".equalsIgnoreCase(mklx)){
				result = service.saveKg(myForm, realTable, request);
				message = result ? "操作成功" : "操作失败";
			}
			// 单条
			else if("dt".equalsIgnoreCase(mklx)){
				result = service.editXszzXmRs(myForm, realTable);
				message = result ? "操作成功" : "操作失败";
			}
			request.setAttribute("message", message);
		}
		// =================end==================
		
		// ================= 执行查看操作 ==================
		
		pkValue = Base.isNull(pkValue) ? xn + xmmc
				: pkValue;
		
		// 开关设置
		if ("kg".equalsIgnoreCase(mklx)) {
			selectPageDataByOne(request, realTable, tableName, pkValue);
			rs = (HashMap<String, String>) request.getAttribute("rs");
		}
		// 批量
		else if ("all".equalsIgnoreCase(mklx)) {
			myForm.setXn(xn);
			myForm.setXmmc(xmmc);
			List<HashMap<String,String>> blList = service.getXyRsBlList(myForm);
			request.setAttribute("blList", blList);
		}
		// 单条
		else if ("dt".equalsIgnoreCase(mklx)) {
			myForm.setXn(xn);
			myForm.setXmmc(xmmc);
			myForm.setXydm(pkValue);
			List<HashMap<String,String>> blList = service.getXySzRsList(myForm);
			request.setAttribute("blList", blList);
		}
		
		// 设置当前学年学期
		rs.put("xn", xn);
		// 学院
		rs.put("xydm", pkValue);
		
		// =================end==================
		
		// ===============初始化request的值 =====================
		RequestForm rForm = new RequestForm();
		// 其他字段
		String[] qtzd = new String[] {};

		// 其他字段值
		String[] qtzdz = new String[] {};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("csszUpdate");
	}
	
	/**
	 * 学生资助_经济困难生认定_申请
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_xsjbxx";
		// 表名
		String realTable = "hndx_xszz_jjknsrd";
		// 模块类型
		String mklx = "sq";
		// 标题
		String title = "学生资助 - 经济困难生认定 - 申请";
		// 访问路径
		String path = "xszz_xzcd_open.do?cdlb=sqcd&doType=open";
		// 主键
		String pk = request.getParameter("pk");
		// 主键值
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsUpdate(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 学生资助_经济困难生认定_审核
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_hddx_jjknssq";
		// 表名
		String realTable = "hndx_xszz_jjknsrd";
		// 模块类型
		String mklx = "sh";
		// 标题
		String title = "学生资助 - 经济困难生认定 - 审核";
		// 访问路径
		String path = "xszz_xzcd_open.do?cdlb=shcd&doType=open";
		// 主键
		String pk = request.getParameter("pk");
		// 主键值
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 学生资助_经济困难生认定_结果
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknsjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_hddx_jjknssq";
		// 表名
		String realTable = "hndx_xszz_jjknsrd";
		// 模块类型
		String mklx = "jg";
		// 标题
		String title = "学生资助 - 经济困难生认定 - 结果查询";
		// 访问路径
		String path = "xszz_xzcd_open.do?cdlb=shcd&doType=open";
		// 主键
		String pk = request.getParameter("pk");
		// 主键值
		String pkValue = request.getParameter("pkValue");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		
		request.setAttribute("rs", null);
		// =================end ===================
		
		return jjknsManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 学生资助_经济困难生认定_查看
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward jjknsView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep= (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_hddx_jjknssq";
		// 表名
		String realTable = "hndx_xszz_jjknsrd";
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 标题
		String title = "学生资助 - 经济困难生认定 - 维护";
		// 访问路径
		String path = "";
		// 主键值
		String pkValue = request.getParameter("pk");
		
		XszzTyForm myForm = (XszzTyForm) form;

		RequestForm rForm = new RequestForm();
		
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		rForm.setUserDep(userDep);
		rForm.setPkValue(pkValue);
		rForm.setTitle(title);
		// =================end ===================
		
		return jjknsUpdate(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 学生资助_经济困难生认定_维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jjknsUpdate(ActionMapping mapping, XszzTyForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzHndxService service = new XszzHndxService();
		
		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 登陆用户部门
		String userDep = rForm.getUserDep();
		// 登陆用户部门
		String bzrQx = rForm.getUserDep();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 模块类型
		String mklx = rForm.getMklx();
		// 当前学年
		String xn = Base.currXn;
		// 主键
		String pk = "xh||xn";
		// 主键值
		String pkValue = rForm.getPkValue();
		// 学号
		String xh = request.getParameter("xh");
		// 学生信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 提示信息
		String message = "";
		// 审核状态
		String shzt = request.getParameter("shzt");
		//TODO 目前写死
		//项目名称
		String xmmc = "经济困难生认定";
		// 班级审核权限
		boolean isBjsh = service.isBjsh(userName, request);
		// 项目申请开关
		String kg = service.getOneValue("hndx_xszz_kgszb", "kg", "xn||xmmc", xn
				+ xmmc);
		boolean canSq = (!Base.isNull(kg) && "true".equalsIgnoreCase(kg)) ? true
				: false;
		// 保存成功与否
		boolean result = false;
		// =================end==================

		// ==================访问权限控制 ==================
		if (("sq".equalsIgnoreCase(mklx) || // 申请开关关闭，不可申请
			("jg".equalsIgnoreCase(mklx) && "add".equalsIgnoreCase(doType)))
				&& !canSq) {
			request.setAttribute("errMsg", "目前不可进行该项目的申请，请确认！");
			return new ActionForward("/errMsg.do", false);
		}

		if ("stu".equalsIgnoreCase(userType)) {// 学生用户
			xh = userName;
		}
		// =================end ===================
		
		// ==================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {

			result = service.saveJjknssq(myForm, realTable, request);
			message = result ? "操作成功" : "操作失败";
			request.setAttribute("result", result);
			request.setAttribute("message", message);
		}
		// =================end ===================

		// ==================执行文件删除操作 ==================
		if ("fileDel".equalsIgnoreCase(doType)) {

			result = service.fileDel(realTable, "scdz", pk, pkValue);
			message = result ? "操作成功" : "操作失败";
			request.setAttribute("result", result);
			request.setAttribute("message", message);

		}
		// =================end ===================
		
		// ==================执行审核操作 ==================
		if ("sh".equalsIgnoreCase(doType)) {
			
			result = service.shJjknssq(myForm, realTable, userType, isBjsh, shzt);
			message = result ? "操作成功" : "操作失败";
			request.setAttribute("result", result);
			request.setAttribute("message", message);

		}
		// =================end ===================
		
		// ==================执行查看操作 ==================
		if ("sq".equalsIgnoreCase(mklx) || "update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {
			
			// 经济困难生申请
			if (!Base.isNull(xh) || !Base.isNull(pkValue)) {
				
				tableName = "view_hddx_jjknssq";

				pkValue = "sq".equalsIgnoreCase(mklx) ? xh + xn : pkValue;
					
				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");
				
				xh = Base.isNull(rs.get("xh")) ? xh : rs.get("xh");
				xn = Base.isNull(rs.get("xn")) ? xn : rs.get("xn");
			}
					
			// 学生基本信息
			String key = "xh";
			tableName = "view_xsxxb";	
			String[] colList = new String[] { "xh", "xm", "xb", "mzmc", "csrq",
					"sfzh", "zzmmmc", "yzbm", "sjhm", "hkxz", "hkshen",
					"hkshi", "hkxian", "nj", "xydm", "xymc", "zymc", "bjmc",
					"rxrq" };
			HashMap<String, String> stuInfo = service.getXszzInfo(tableName,
					key, xh, colList);
			rs.putAll(stuInfo);
			
			// 学生住宿信息
			tableName = "view_xszsxx";
			colList = new String[] { "xqmc", "ldmc", "cs", "qsh", "qsdh" };
			HashMap<String, String> zsInfo = service.getXszzInfo(tableName, key,
					xh, colList);

			rs.putAll(zsInfo);
		
			rs.put("xn", xn);//申请默认本学年
		}
		// =================end ===================
		
		// ==================执行增加操作 ==================
		if ("add".equalsIgnoreCase(doType)) {
			rs.put("xn", xn);//只能增加当前学年的数据
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		
		// 其他字段
		String[] qtzd = new String[] { "isBjsh" };

		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isBjsh) };
		
		rForm.setRs(rs);
		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================

		return mapping.findForward("jjknsUpdate");
	}
	
	/**
	 * 学生资助_经济困难生认定_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jjknsManage(ActionMapping mapping, XszzTyForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XszzHndxService service = new XszzHndxService();
		
		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 登陆用户部门
		String userDep = rForm.getUserDep();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 模块类型
		String mklx = rForm.getMklx();
		// 当前学年
		String xn = Base.currXn;
		// 主键
		String pk = "xh||xn";
		// 主键值
		String pkValue = rForm.getPkValue();
		// 学号
		String xh = request.getParameter("xh");
		// 提示信息
		String message = "";
		// 审核字段
		String shzd = "";
		// 审核状态
		String shzt = request.getParameter("shzt");
//		TODO 目前写死
		//项目名称
		String xmmc = "经济困难生认定";
		// 班级审核权限
		boolean isBjsh = service.isBjsh(userName, request);
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		//保存成功与否
		boolean result = false;
		// =================end==================
		
		// ==================访问权限控制 ==================
		
		if (isBjsh) {// 班级审核权限
			if ("stu".equalsIgnoreCase(userType)) {// 学生用户

				String key = "xh";
				xh = userName;
				String[] colList = new String[] { "xydm", "zydm", "bjdm", "nj" };
				HashMap<String, String> stuInfo = service.getXszzInfo(
						"view_xsxxb", key, xh, colList);
				myForm.setQueryequals_xydm(stuInfo.get("xydm"));
				myForm.setQueryequals_zydm(stuInfo.get("zydm"));
				myForm.setQueryequals_bjdm(stuInfo.get("bjdm"));
				myForm.setQueryequals_nj(stuInfo.get("nj"));

				shzd = "bjsh";
			} else {
				myForm.setQueryequals_xydm(userDep);
			}
		} else if ("xy".equalsIgnoreCase(userType)) {// 学院用户

			myForm.setQueryequals_xydm(userDep);

			if ("sh".equalsIgnoreCase(mklx)) {// 审核
				myForm.setQueryequals_bjsh("通过");
				shzd = "xysh";
			}

		} else if ("xx".equalsIgnoreCase(userType)
				|| "admin".equalsIgnoreCase(userType)) {// 学校（管理员）

			if ("sh".equalsIgnoreCase(mklx)) {// 审核
				myForm.setQueryequals_xysh("通过");
				myForm.setQueryequals_bjsh("通过");

				shzd = "xxsh";
			}
			
		} else {

			if ("sh".equalsIgnoreCase(mklx)) {// 审核
				String msg = "你没有操作权限，请确认";
				request.setAttribute("msg", msg);
			}

		}
		// =================end==================
		
		// ==================执行审核操作 ==================
		if ("sh".equalsIgnoreCase(doType)) {

			// 审核时间
			String nowTime = service.getNowTime("YYYYMMDD");
			// 审核状态
			shzt = "tg".equalsIgnoreCase(shzt) ? "通过" : "不通过";
			// 困难生级别
			String knsjb = myForm.getKnsjb();

			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			
			//判断是否超过最大人数
			
			if ("通过".equalsIgnoreCase(shzt)) {
				myForm.setXmmc(xmmc);
				myForm.setShzd(shzd);
				myForm.setXn(xn);
				message = service.isCgrs(myForm);
			}
			
			//没超过，顺利执行审核
			if (Base.isNull(message)) {
				HashMap<String, String> valueMap = new HashMap<String, String>();
				valueMap.put(shzd, shzt);
				valueMap.put(shzd + "sj", nowTime);
				valueMap.put("knsjb", knsjb);
				auditingBatchOperation(request, primaryMap, valueMap, realTable);
			}else{
				request.setAttribute("result", false);
			}
		}
		// =================end ===================
		
		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nj",
					"xymc", "zymc", "bjmc","jbmc", "bjsh", "xysh", "xxsh" };
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
		
		// 其他字段
		String[] qtzd = new String[] { "isBjsh", "message" };

		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isBjsh), message };

		rForm.setPk(pk);
		rForm.setPkValue(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "jjkns");
		// =================end ===================
		
		return mapping.findForward("jjknsManage");
	}
}
