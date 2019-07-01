package xgxt.wjdc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.utils.SearchUtils;

import com.zfsoft.basic.BasicAction;

public class WjdcRealizAtion extends BasicAction {

	/**
	 * 问卷调查_问卷信息_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward wjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================

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
					"zjxx", "sfkq", "kyxg", "dawk" };
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

		// =================初始化request的值 ===============
		// 其他字段
		String[] qtzd = new String[] { "mklx" };
		// 其他字段值
		String[] qtzdz = new String[] { mklx };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ==================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjManage");
	}

	/**
	 * 问卷调查_问卷信息_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward wjUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 主键值
		String pkValue = rForm.getPk();
		// ID
		String id = myForm.getId();
		// 当前学年
		String xn = Base.currXn;
		// 当前学期
		String xq = Base.currXq;
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", xq);
		// 当前年度
		String nd = Base.currNd;
		// 模块类型
		String mklx = rForm.getMklx();
		// 建立时间
		String jlsj = service.getNowTime("YYYYMMDD");
		String jlsjmc = service.getNowTime("YYYY年MM月DD日");
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 保存数据成功标志
		boolean result = false;
		// 是否可见试题
		boolean isSt = ("view".equalsIgnoreCase(doType)) ? true : false;
		// 提示信息
		String message = "";
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			myForm.setId(id);
			if (Base.isNull(id)) {// 增加
				result = service.saveWjInfo(myForm, realTable, request);
			} else {// 修改
				result = service.updateWjInfo(myForm, realTable, request);
			}
			message = result ? "操作成功" : "操作失败";
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
				|| "view".equalsIgnoreCase(doType) || result) {

			String pk = Base.isNull(pkValue) ? "xn||xq||nd||wjmc||jlsj" : "id";

			pkValue = Base.isNull(pkValue) ? myForm.getXn() + myForm.getXq()
					+ myForm.getNd() + myForm.getWjmc() + myForm.getJlsj()
					: pkValue;

			String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc",
					"nd", "wjmc", "xn", "xq", "xqmc", "sfkq", "kyxg", "dawk","jwy" };

			rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

			// 获得问卷组卷信息
			if (!Base.isNull(pkValue)) {
				myForm.setId(pkValue);
				service.setWjZjInfo(myForm, request);
			}
		}

		// =================end ===================

		// =================初始化request的值 ====================

		// 其他字段
		String[] qtzd = new String[] { "id", "isSt", "mklx", "message" };

		// 其他字段值
		String[] qtzdz = new String[] { pkValue, String.valueOf(isSt), mklx,
				message };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjUpdate");
	}

	/**
	 * 问卷调查_试题信息_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward stManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 模块类型
		String mklx = rForm.getMklx();
		// 提示信息
		String message = "";
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

			message = result ? "删除成功" : "删除失败";

		}
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "stbh", "lxmc", "ssmc", "xsmc",
					"jlsj", "haveda" };
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
		String[] qtzd = new String[] { "mklx", "message" };

		// 其他字段值
		String[] qtzdz = new String[] { mklx, message };

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("stManage");
	}

	/**
	 * 问卷调查_试题信息_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward stUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = rForm.getTableName();
		// 模块类型
		String mklx = rForm.getMklx();
		mklx = Base.isNull(mklx) ? myForm.getMklx() : mklx;
		// 访问路径
		String path = rForm.getPath();
		path = Base.isNull(path) ? request.getParameter("path") : path;
		// 主键值
		String pkValue = rForm.getPk() + mklx;
		// 试题编号
		String stbh = service.getStbh();
		// 试题详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		// 自动生成：试题编号
		rs.put("stbh", stbh);
		rs.put("mklx", mklx);
		// 查看（修改）
		if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType)) {

			String pk = "stbh||mklx";

			String[] colList = new String[] { "bz", "stbh", "stss", "stlx",
					"stmc", "jlsj", "mklx" };

			rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		}
		// =================end ===================

		// =================初始化request的值 ====================

		// 其他字段
		String[] qtzd = new String[] {};

		// 其他字段值
		String[] qtzdz = new String[] {};

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
	 * 问卷调查_组卷管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward zjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 表名
		String realTable = rForm.getRealTable();
		// =================end==================

		// ================= 执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {

			boolean result = service.saveZjInfo(myForm, realTable);

			String message = result ? "操作成功" : "操作失败";

			rForm.setMessage(message);
		}
		// =================end==================

		// =================初始化request的值 ====================

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("zjManage");
	}

	/**
	 * 问卷调查_问卷分配_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward wjfpManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户部门
		String userDep = rForm.getUserDep();
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 视图名
		String tableName = rForm.getTableName();
		// 表名
		String realTable = rForm.getRealTable();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
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
			String[] outputColumn = { "pk", "xn", "nd", "xqmc", "nj", "xymc",
					"zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpManage");
	}

	/**
	 * 问卷调查_问卷分配_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward wjfpUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		// 当前学年
		String xn = Base.currXn;
		myForm.setXn(xn);
		// 当前年度
		String nd = Base.currNd;
		myForm.setNd(nd);
		// 当前学期
		String xq = Base.currXq;
		myForm.setXq(xq);
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			boolean resault = service.saveWjfp(myForm);
			String message = resault ? "分配成功" : "分配失败";
			rForm.setMessage(message);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("wjfpUpdate");
	}

	/**
	 * 问卷调查_回答问卷_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdwjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户名
		String userName = rForm.getUserName();
		// 视图名
		String tableName = rForm.getTableName();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================权限控制 ==================
		if (!"stu".equalsIgnoreCase(userType)) {
			String msg = "问卷只能由学生进行回答";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
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
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdwjManage");
	}

	/**
	 * 问卷调查_回答问卷_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdwjUpdate(ActionMapping mapping, WjdcForm myForm,
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

		return mapping.findForward("hdwjUpdate");
	}

	/**
	 * 问卷调查_回答统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdtjManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 视图名
		String tableName = rForm.getTableName();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "id", "xn", "nd", "xqmc", "wjmc", "jlsj",
					"zjxx", "hdnum" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjManage");
	}

	/**
	 * 问卷调查_回答问卷_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdtjUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
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
		// 统计年级
		String nj = myForm.getNj();
		// 统计学院
		String xydm = myForm.getXydm();
		// 统计专业
		String zydm = myForm.getZydm();
		// 统计班级
		String bjdm = myForm.getBjdm();
		// 统计性别
		String xb = myForm.getXb();
		// 统计政治面貌
		String zzmm = myForm.getZzmm();
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "id";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

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
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdtjUpdate");
	}

	/**
	 * 问卷调查_回答结果_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward hdjgManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		String doType = request.getParameter("doType");
		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户部门
		String userDep = rForm.getUserDep();
		// 视图名
		String tableName = rForm.getTableName();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
			selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================
		//------------导出--------------------
		if(EXP.equals(doType)){
			String[] outputColumn = {"xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc", "isover" };
			expPageData(request, response, tableName, tableName, outputColumn);
			return null;
		}
		
		
		
		// =================初始化request的值 ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdjgManage");
	}

	/**
	 * 问卷调查_回答结果_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward hdjgUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = rForm.getTableName();
		// 主键值
		String pkValue = rForm.getPk();
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "pk";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "xh", "lx" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// 设置统计信息
		myForm.setId(rs.get("id"));
		myForm.setWjbh(rs.get("id"));
		myForm.setXhzgh(rs.get("xh"));
		myForm.setLx(rs.get("lx"));

		service.setWjZjInfo(myForm, request);

		// =================end ===================

		// =================初始化request的值 ====================
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("hdjgUpdate");
	}

	/**
	 * 问卷调查_分析结果_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ActionForward fxjgManage(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 登陆用户类型
		String userType = rForm.getUserType();
		// 登陆用户部门
		String userDep = rForm.getUserDep();
		// 视图名
		String tableName = rForm.getTableName();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================

		// ================= 权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
		}
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "xn", "nd",
					"xqmc", "nj", "xymc", "zymc", "bjmc", "wjmc" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("fxjgManage");
	}

	/**
	 * 问卷调查_分析结果_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	protected ActionForward fxjgUpdate(ActionMapping mapping, WjdcForm myForm,
			RequestForm rForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		WjdcService service = new WjdcService();

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = rForm.getDoType();
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = rForm.getTableName();
		// 主键值
		String pkValue = rForm.getPk();
		// 问卷详细信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// =================end==================

		// ===================执行查看操作 ======================

		String pk = "pk";

		String[] colList = new String[] { "bz", "id", "jlsj", "jlsjmc", "nd",
				"wjmc", "xn", "xq", "xqmc", "xh", "lx", "wzjg", "tbjg", "hadTb" };

		rs = service.getWjdcInfo(tableName, pk, pkValue, colList);

		// =================end ===================

		// =================初始化request的值 ====================
		rForm.setRs(rs);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "wjdc");
		// =================end ===================

		return mapping.findForward("fxjgUpdate");
	}
}
