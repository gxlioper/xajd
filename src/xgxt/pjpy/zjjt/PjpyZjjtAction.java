package xgxt.pjpy.zjjt;

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

import com.zfsoft.basic.BasicAction;

import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.pjpy.PjpyTyForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 浙江交通技师评奖评优-action类
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

public class PjpyZjjtAction extends BasicAction {

	/**
	 * 评奖评优_操行分审核
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setMklx("sh");
		return cxfManage(mapping, myForm, request, response);
	}

	/**
	 * 评奖评优_操行分结果
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyTyForm myForm = (PjpyTyForm) form;
		myForm.setMklx("jg");
		return cxfManage(mapping, myForm, request, response);
	}
	
	/**
	 * 评奖评优_操行分管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfManage(ActionMapping mapping, PjpyTyForm myForm,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();

		String userName = (String) request.getSession().getAttribute("userName");
		String userType = (String) request.getSession().getAttribute("userType");
		String userDep = (String) request.getSession().getAttribute("userDep");
		String doType = request.getParameter("doType");
		String tableName = "view_zjjt_cxf";
		String realTable = "zjjt_cxfb";
		String path = "pjpy_cxfsh.do";
		String mklx = myForm.getMklx();
		String title = "sh".equalsIgnoreCase(mklx) ? "评奖评优 - 操行分 - 审核"
				: "评奖评优 - 操行分 - 结果";
		
		BeanUtils.copyProperties(model, myForm);
		
		if("stu".equalsIgnoreCase(userType)){
			//	初始化登陆者相关信息
			String pk = "xh";
			String pkValue = userName;
			String[] colList = new String[] { "xh", "nj", "xydm", "zydm",
					"bjdm" };
			
			HashMap<String,String> map = service.getPjpyInfo("view_xsjbxx", pk, pkValue, colList);
			
			myForm.setNj(map.get("nj"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
			myForm.setXh(map.get("xh"));
		}else if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		boolean result = false;
		
		// 批量删除操行分
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||pjxh";

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
			String[] colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
					"xymc", "zymc", "bjmc", "addValue", "subValue" };
			if ("jg".equalsIgnoreCase(mklx)) {
				colList = new String[] { "pk", "xn", "xqmc", "xh", "xm",
						"xymc", "zymc", "bjmc", "addValue", "addV", "subValue",
						"subV", "cxf" };
			}
			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getPjpyList(tableName, model, colList, "");
		}
		
		// 初始化列表值
		service.setList(myForm, request, "cxf");
		
		// 设置Request的值
		RequestForm rForm = new RequestForm();
		rForm.setPath(path);
		rForm.setMklx(mklx);
		service.setRequestValue(rForm, request);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		request.setAttribute("title", title);
		request.setAttribute("tableName", "view_zjjt_cxf_ex");

		return mapping.findForward("cxfManage");
	}
	
	/**
	 * 评奖评优_操行分维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();

		String doType = request.getParameter("doType");
		String type = request.getParameter("type");
		doType = Base.isNull(doType) ? "add" : doType;
		String userName = (String) request.getSession().getAttribute("userName");
		String userType = (String) request.getSession().getAttribute("userType");
		String tableName = "view_zjjt_cxf";
		String realTable = "zjjt_cxfb";
		String title = "评奖评优 - 操行分 - 维护";
		String xn = Base.getJxjsqxn();//评奖学年
		String xq = Base.getJxjsqxq();//评奖学期
		String xh = request.getParameter("xh");
		String pk = "xn||xq||xh";
		String key = request.getParameter("pk");
		
		BeanUtils.copyProperties(model, myForm);
	
		HashMap<String, String> rs = new HashMap<String, String>();
		
		// 判断登陆者是否是学生
		if ("stu".equalsIgnoreCase(userType)) {
			// 获得学生基本信息
			rs = service.getDetStuInfo(userName);
			rs.put("xn", xn);
			rs.put("xq", xq);
		} else {
			
			if ("add".equalsIgnoreCase(doType)&&!Base.isNull(xh)) {
				key = xn + xq + xh;
			}
			String[] colList = new String[] { "xn", "xq", "xh", "xm",
					"xymc", "zymc", "bjmc", "nj", "xb", "xm" };
			rs = service.getPjpyInfo(tableName, pk, key, colList);
			
			//该学生无本学年学期操行分
			if(Base.isNull(rs.get("xh"))){
				rs = service.getDetStuInfo(xh);
				rs.put("xn", xn);
				rs.put("xq", xq);
			}
		}

		//非学生用户添加数据
		if ("add".equalsIgnoreCase(doType) && !Base.isNull(xh)) {	
			key = service.getOneValue("zjjt_cxfb", "pjxh", "xn||xq||pjxh", key);
		}
		
		//保存操行加减分
		if ("save".equalsIgnoreCase(doType)) {
	
			String[] arrzd = new String[] { "pjxh", "jjf", "fz", "rq", "sy",
					"shjg" };
			String[] onezd = new String[] { "xn", "xq" };
			pk = "pjxh";
			
			// 构建主键
			String[] jjf = myForm.getJjf();
			String[] pjxh = null;
			String[] pkValue = null;
			
			if (jjf != null && jjf.length > 0) {
				pjxh = new String[jjf.length];
				pkValue = new String[jjf.length];
				for (int i = 0; i < jjf.length; i++) {
					pjxh[i] = myForm.getXh();
					pkValue[i] = myForm.getXh();
				}
				model.setPjxh(pjxh);
			}
				
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			
			//执行操作保存数据
			boolean result = service.savePjpy(saveForm, model);
			
			request.setAttribute("type", type);
			request.setAttribute("result", result);
		
		}
		
		// 初始化列表值
		service.setList(myForm, request, "cxf");
		
		// 设置Request的值
		RequestForm rForm = new RequestForm();
		rForm.setTitle(title);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		service.setRequestValue(rForm, request);
		
		return mapping.findForward("cxfUpdate");
	}

	/**
	 * 评奖评优_操行分_录入
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		HttpSession session = request.getSession();
		
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 模块类型
		String mklx = "lr";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_zjjt_cxfsbxs";
		// 表名
		String realTable = "zjjt_cxflrb";
		// 访问路径
		String path = "pjpy_cxflr.do";
		
		PjpyTyForm myForm = (PjpyTyForm) form;

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
		
		return cxfwhManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 评奖评优_操行分_查询
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ===================== 赋初值 ===================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) request.getSession().getAttribute("userDep");
		// 模块类型
		String mklx = "cx";
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_zjjt_cxfjg";
		// 表名
		String realTable = "zjjt_cxflrb";
		// 访问路径
		String path = "pjpy_cxfcx.do";

		PjpyTyForm myForm = (PjpyTyForm) form;

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
		
		return cxfcxManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * 评奖评优_操行分_管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhManage(ActionMapping mapping, PjpyTyForm form,RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
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
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] colList = null;
			if ("lr".equalsIgnoreCase(mklx)) {//录入
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh" };
			}else if("cx".equalsIgnoreCase(mklx)) {//查询
				colList = new String[] { "pk", "xh", "xm", "xb","xn","xqm", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "mrz",
						"jiaf", "jianf", "cxf" };
			}
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, colList, null);
			
			String otherQuery = CommonQueryDAO.getQuerySqlByUser(getUser(request), "a", "xydm", "bjdm");
			
			ArrayList<String[]> rs = service.getPjpyList(tableName,myForm,
					colList, otherQuery);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		// ===============初始化request的值 =====================
		// 其他字段
		// String[] qtzd = new String[] {"shzd"};

		// 其他字段值
		// String[] qtzdz = new String[] {shzd};

		// rForm.setQtzd(qtzd);
		// rForm.setQtzdz(qtzdz);

		if ("cx".equalsIgnoreCase(mklx)) {// 处理导出数据的视图
			tableName = "view_zjjt_cxfexp";
			rForm.setTableName(tableName);
		}
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhManage");
	}
	
	/**
	 * 评奖评优_操行分_管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfcxManage(ActionMapping mapping, PjpyTyForm form,RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
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
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] colList = null;
			if ("lr".equalsIgnoreCase(mklx)) {//录入
				colList = new String[] { "pk", "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh" };
			}else if("cx".equalsIgnoreCase(mklx)) {//查询
				colList = new String[] { "pk", "xh", "xm", "xb","xn","xqm", "xymc",
						"zymc", "bjmc", "xqmc", "ldmc", "cs", "qsh", "mrz",
						"jiaf", "jianf", "cxf" };
			}
			List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
					tableName, colList, null);
			
			String otherQuery = CommonQueryDAO.getQuerySqlByUser(getUser(request), "a", "xydm", "bjdm");
			
			ArrayList<String[]> rs = service.getPjpyList(myForm,
					colList, otherQuery);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		// ===============初始化request的值 =====================
		// 其他字段
		// String[] qtzd = new String[] {"shzd"};

		// 其他字段值
		// String[] qtzdz = new String[] {shzd};

		// rForm.setQtzd(qtzd);
		// rForm.setQtzdz(qtzdz);

		if ("cx".equalsIgnoreCase(mklx)) {// 处理导出数据的视图
			tableName = "view_zjjt_cxfexp";
			rForm.setTableName(tableName);
		}
		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhManage");
	}
	
	/**
	 * 评奖评优_操行分_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 标题
		String title = "操行分 - 维护";
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 视图名
		String tableName = ("lr".equalsIgnoreCase(mklx)) ? "view_zjjt_cxfsbxs"
				: "view_zjjt_cxfjg";
		// 表名
		String realTable = "zjjt_cxflrb";
		// 主键
		String pk = ("lr".equalsIgnoreCase(mklx))?"xh":"xh||xn||xq";
		// 主键值
		String pkValue = request.getParameter("pk");
		// 操行分申请者信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 操行分申请者信息
		List<HashMap<String, String>> rsList = new ArrayList<HashMap<String, String>>();
		//保存标记
		boolean result = false;
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ================= 执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			pkValue = model.getXh();
			result = service.saveCxf(model, realTable);
			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ================= 执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			result = service.delCxf(model, realTable);
			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ================= 执行查看操作 ==================
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {
			if("cx".equalsIgnoreCase(mklx)){
				String[] colList = new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "ldmc", "xqmc", "cs", "qsh", "nj","sfzh" ,"xn","xq"};
				rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
				
				myForm.setXh(rs.get("xh"));
				myForm.setXn(rs.get("xn"));
				myForm.setXq(rs.get("xq"));
				rsList = service.getCxfXxList(myForm);
			}else {
				String[] colList = new String[] { "xh", "xm", "xb", "xymc",
						"zymc", "bjmc", "ldmc", "xqmc", "cs", "qsh", "nj","sfzh" };
				rs = service.getPjpyInfo(tableName, pk, pkValue, colList);
				rs.put("xn", xn);
				rs.put("xq", xq);
				
				myForm.setXh(rs.get("xh"));
				myForm.setXn(rs.get("xn"));
				myForm.setXq(rs.get("xq"));
			}
		}
		// =================end==================
		
		// ===============初始化request的值 =====================
		RequestForm rForm = new RequestForm();
		//	其他字段
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
		rForm.setRsList(rsList);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhUpdate");
	}
	
	/**
	 * 评奖评优_操行分_设置
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward szManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = (String) request.getSession().getAttribute("userType");
		// 登陆用户名
		String userName = (String) request.getSession().getAttribute("userName");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 标题
		String title = "操行分 - 设置";
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 视图名
		String tableName = "zjjt_cxf_sz";
		// 表名
		String realTable = "zjjt_cxf_sz";
		// 主键
		String pk = "xn||xq";
		// 主键值
		String pkValue = xn + xq;
		// 操行分设置相关信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ================= 执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
			this.insertOperation(request, realTable);
		}
		// =================end==================
		
		// ================= 执行查看操作 ==================
		selectPageDataByOne(request, realTable, tableName, pkValue);
		rs = (HashMap<String, String>) request.getAttribute("rs");
		// 设置当前学年学期
		rs.put("xn", xn);
		rs.put("xq", xq);
		rs.put("save_xn", xn);
		rs.put("save_xq", xq);
		// =================end==================
		
		// ===============初始化request的值 =====================
		RequestForm rForm = new RequestForm();
		//	其他字段
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
		rForm.setPk(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("szManage");
	}
	
	/**
	 * 评奖评优_操行分_统计
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward cxfwhTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		PjpyZjjtModel model = new PjpyZjjtModel();
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户所在部门
		String userDep = (String) session.getAttribute("userDep");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		//路径
		String path = "pjpy_cxftj.do";
		// 模块类型
		String mklx = request.getParameter("mklx");
		// 视图名
		String tableName = "";
		// 表名
		String realTable = "";
		// 主键
		String pk = "";
		// 主键值
		String pkValue = "";
		String tjfs = myForm.getTjfs();
		if(Base.isNull(tjfs)){
			myForm.setTjfs("xq");
		}
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		BeanUtils.copyProperties(model, myForm);
		
		// ==================执行查询操作 ==================
		if (search) {
			List<HashMap<String, String>> topTr = null;
			
			if("xq".equalsIgnoreCase(model.getTjfs())){
				topTr = service.getTopTr("cxftj");
			}else{
				topTr = service.getTopTr("cxftjxn");
			}
			
			ArrayList<String[]> rs = (ArrayList<String[]>) service.getCxfTj(myForm,getUser(request));
			//ArrayList<String[]> rs = service.getCxfTjInfo(myForm);
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		}
		// =================end ===================
		
		//================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		// =================end==================
		
		
		// ==================执行打印报表操作 ==================
		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.printCxfTjbb(myForm, response.getOutputStream());

			return null;
		}
		// =================end ===================
		
		// ===============初始化request的值 =====================
		RequestForm rForm = new RequestForm();
		
		//	其他字段
		String[] qtzd = new String[] {};

		// 其他字段值
		String[] qtzdz = new String[] {};
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setPk(pkValue);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// =================end ===================

		// ===================初始化request的值 ======================
		service.setList(myForm, request, "cxf");
		// =================end ===================
		
		return mapping.findForward("cxfwhTj");
	}
	
	
	/**
	 * 初始化操行分
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initCxf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyTyForm myForm = (PjpyTyForm) form;
		PjpyZjjtService service = new PjpyZjjtService();
		
		request.setAttribute("result", service.initCxf(myForm));
		myForm.setXn(null);
		myForm.setXq(null);
		return cxfwhLr(mapping, form, request, response);
	}
}
