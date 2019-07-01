package xgxt.xsgygl.zjcm;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcForm;
import xgxt.pjpy.cqdzgc.PjpyCqdzgcService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.xsgygl.GyglTyForm;

import com.zfsoft.basic.BasicAction;

public class ZjcmGyglAction extends BasicAction {

	/**
	 * 卫生检查管理
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_wsjc";
		String realTable = "zjcm_wsjcb";
		String xn = Base.currXn;
		String xq = Base.currXq;
		String csh = request.getParameter("csh");
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		}

		// 判断数据库中卫生检查等级是否维护
		if (!service.isWsjcdj()) {
			String msg = "卫生检查等级尚未维护，请确认";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		// 初始化数据，本学年，学期，周次
		if (Base.isNull(csh)) {
			myForm.setXn(xn);
			myForm.setXq(xq);
			csh = "no";
		}

		BeanUtils.copyProperties(model, myForm);

		// 批量删除卫生检查分
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = myForm.getCheckVal();
			if (checkVal != null && checkVal.length > 0) {
				String pk = "xn||xq||jcqs||jcsj";

				SaveForm saveForm = new SaveForm();
				saveForm.setTableName(realTable);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.delGygl(saveForm, model);
				request.setAttribute("result", result);
			}
		}

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xn", "xqmcc", "xqmc",
					"ldmc", "cs", "qsh", "jcsj", "fs" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
			rs = service.getWsjcQueryList(tableName, model, colList);

		}
		// 初始化列表值
		service.setList(myForm, request, "wsjc");
		request.setAttribute("path", "wsjc.do");
		request.setAttribute("csh", csh);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("wsjcManage");
	}

	/**
	 * 卫生检查维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		String doType = request.getParameter("doType");
		String tableName = "view_zjcm_ssxx";
		String realTable = "zjcm_wsjcb";
		String userName = session.getAttribute("userName").toString();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String bz = service.getWsdjMsg();
		boolean result = false;

		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;

		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		}	
		
		
		BeanUtils.copyProperties(model, myForm);

		// 保存卫生检查分
		if ("save".equalsIgnoreCase(doType)) {

			int n = 0;
			for (int i = 0; i < model.getFs().length; i++) {
				if (!Base.isNull(model.getFs()[i])) {
					n++;
				}
			}

			String[] arrzd = new String[] { "jcqs", "fs" };
			String[] onezd = new String[] { "xn", "xq", "jcsj", "lrr" };
			String pk = "xn||xq||jcqs||jcsj";
			String[] pkValue = new String[n];

			String jcsj = request.getParameter("jcsj");
			String[] jcqs = new String[n];
			String[] fs = new String[n];

			n = 0;

			for (int i = 0; i < model.getJcqs().length; i++) {
				if (!Base.isNull(model.getFs()[i])) {
					pkValue[n] = xn + xq + model.getJcqs()[i] + jcsj;
					jcqs[n] = model.getJcqs()[i];
					fs[n] = model.getFs()[i];
					n++;
				}
			}

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setArrzd(arrzd);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			model.setXn(xn);
			model.setXq(xq);
			model.setJcsj(jcsj);
			model.setLrr(userName);
			model.setJcqs(jcqs);
			model.setFs(fs);

			result = service.saveGygl(saveForm, model);

			request.setAttribute("result", result);
		}

		BeanUtils.copyProperties(model, myForm);

		// 点击查询按钮进行查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))
				|| result) {
			String[] colList = new String[] { "pk", "xqmc", "ldmc", "cs",
					"qsh", "cws", "xbxd", "sfbz", "qsdh" };

			topTr = SearchUtils.getTopTr(tableName, colList, null);
//			rs = service.getGyglList(tableName, model, colList, "");
			rs = service.getWsjcQueryList(tableName, model, colList);
		}
		// 初始化列表值
		service.setList(myForm, request, "wsjc");
		request.setAttribute("path", "wsjc.do");
		request.setAttribute("bz", bz);
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("wsjcUpdate");
	}

	/**
	 * 卫生检查信息查看
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "公寓管理 - 卫生检查信息 - 查看";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_xszsxx";
		String realTable = "zjcm_wsjcb";
		String pk = "";
		String pkValue = request.getParameter("pkValue");

		doType = Base.isNull(doType) ? "add" : doType;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看房源库信息
		if ("view".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			pk = "pk";
			pkValue = key;

			String[] colList = new String[] { "xqmc", "lddm", "ldmc", "cs",
					"qsh", "xn", "xq", "jcsj", "fs" };
			rs = service.getGyglInfo("view_zjcm_wsjc", pk, pkValue, colList);

			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "cwh" };

			model.setLddm(rs.get("lddm"));
			model.setCs(rs.get("cs"));
			model.setQsh(rs.get("qsh"));

			ArrayList<String[]> rsList = service.getGyglList(tableName, model,
					colList, "");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						"view_xszsxx", colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}

		// 保存卫生检查分修改
		if ("save".equalsIgnoreCase(doType)) {
			String fs = myForm.getFs()[0];
			pkValue = request.getParameter("pkValue");
			;
			boolean result = service.updateWsjcf(fs, pkValue, request);
			request.setAttribute("result", result);
		}
		// 初始化列表值
		service.setList(myForm, request, "wsjc");

		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		
		return mapping.findForward("wsjcView");
	}

	/**
	 * 学生住宿信息查看
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward fyxxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "公寓管理 - 房源信息 - 查看";
		String key = request.getParameter("pk");
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "view_xszsxx";
		String realTable = "xszsxxb";

		doType = Base.isNull(doType) ? "add" : doType;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(model, myForm);

		// 查看房源库信息
		if ("view".equalsIgnoreCase(doType)) {
			String pk = "pk";
			String pkValue = key;

			String[] colList = new String[] { "lddm", "ldmc", "cs", "qsh",
					"cws", "fpbj", "qsdh", "sfbz", "bz", "xqdm", "xqmc" };
			rs = service.getGyglInfo("view_zjcm_ssxx", pk, pkValue, colList);

			colList = new String[] { "xh", "xm", "xb", "nj", "xymc", "zymc",
					"bjmc", "cwh" };

			model.setLddm(rs.get("lddm"));
			model.setCs(rs.get("cs"));
			model.setQsh(rs.get("qsh"));

			ArrayList<String[]> rsList = service.getGyglList(tableName, model,
					colList, "");
			if (rsList != null && rsList.size() > 0) {
				List<HashMap<String, String>> topTr = SearchUtils.getTopTr(
						"view_xszsxx", colList, null);
				request.setAttribute("topTr", topTr);
				request.setAttribute("rsList", rsList);
				request.setAttribute("rsNum", rsList.size());
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("doType", doType);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", realTable);
		request.setAttribute("rs", rs);

		return mapping.findForward("fyxxView");
	}

	/**
	 * 卫生检查统计
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward wsjcTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "公寓管理 - 统计分析 - 卫生检查统计";
		String userType = session.getAttribute("userType").toString();
		String doType = request.getParameter("doType");
		String tableName = "";
		String realTable = "";
		String xn = Base.currXn;
		String xq = Base.currXq;

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		ZjcmGyglModel model = new ZjcmGyglModel();

		// 初始化数据，本学年，学期
		myForm.setXn(xn);
		myForm.setXq(xq);

		// 初始化列表值
		service.setList(myForm, request, "wsjc");

		BeanUtils.copyProperties(model, myForm);

		if (!Base.isNull(doType) && "print".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.printWsjcExcel(model, response.getOutputStream());

			return null;
		}

		request.setAttribute("title", title);

		return mapping.findForward("wsjcTj");
	}

	/**
	 * 公寓报修(申请)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		XsxxglService stuService = new XsxxglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户名
		String userName = (String) session.getAttribute("userName");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx =  "sq";
		// 是否结果查询
		String isjg = request.getParameter("isjg");
		// 学号
		String xh = request.getParameter("xh");
		xh = "stu".equalsIgnoreCase(userType) ? userName : xh;
		// 访问路径
		String path = "gygl_bxsqManage.do";
		// 报修时间
		String bxsj = service.getNowTime("YYYYMMDD");
		String bxsjmc = service.getNowTime("YYYY年MM月DD日");
		// 提示信息
		String message = "";
		// 是否申请成功
		boolean result = false;
		// 相关信息
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("xh", xh);
		// 学生基本信息
		HashMap<String, String> stuInfo = new HashMap<String, String>();
		// =================end==================

		// ================= 相关信息取得 ==================
		if (!Base.isNull(xh)) {
			// 学生基本信息
			stuInfo = stuService.selectStuinfo(xh);
			rs.putAll(stuInfo);
			rs.put("bxsj", bxsj);
			rs.put("bxsjmc", bxsjmc);
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			result = service.saveBxSq(myForm, request);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ===================保存成功后 ======================
		// 显示申请信息
		if (result) {
			rs = service.getBxInfo(myForm);

			// 报修时间
			bxsj = rs.get("bxsj");

			if (!Base.isNull(bxsj) && bxsj.length() >= 8) {
				bxsj = bxsj.substring(0, 8);
				bxsjmc = bxsj.substring(0, 4) + "年" + bxsj.substring(4, 6)
						+ "月" + bxsj.substring(6, 8) + "日";
			}

			rs.put("bxsj", bxsj);
			rs.put("bxsjmc", bxsjmc);
			rs.putAll(stuInfo);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isjg" };
		// 其他字段值
		String[] qtzdz = new String[] { isjg };

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxSq");
	}

	/**
	 * 公寓报修(审核管理)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户名
		String userName = (String) session.getAttribute("userName");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx = "sh";
		// 视图
		String tableName = "view_gygl_zjcm_gybx";
		// 访问路径
		String path = "gygl_bxshManage.do";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// 项目列表
		List<HashMap<String, String>> rsList = null;
		// =================end==================

		// ==================登陆用户检测 ==================

		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
			isxy = true;
		}
		// =================end==================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xqmc", "ldmc", "cs", "qsh", "bxsj", "shzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxshManage");
	}

	/**
	 * 公寓报修(审核)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx = "sh";
		// 主键
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// 访问路径
		String path = "gygl_bxshManage.do";
		// 提示信息
		String message = "";
		// 是否申请成功
		boolean result = false;
		// 相关信息
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("pk", pk);
		// =================end==================

		// ================= 相关信息取得 ==================
		if (!Base.isNull(pk)) {
			// 学生基本信息
			rs = service.getBxshInfo(myForm);
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {

			// 保存审核信息
			result = service.saveBxSh(myForm, request);

			if (result) {
				// 保存材料信息
				result = service.saveBxcl(myForm);
			}
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxSh");
	}

	/**
	 * 公寓报修(结果管理)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户名
		String userName = (String) session.getAttribute("userName");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx = "sh";
		// 视图
		String tableName = "view_gygl_zjcm_gybx";
		// 表
		String realTable = "gygl_gybxb";
		// 访问路径
		String path = "gygl_bxjgManage.do";
		// 提示信息
		String message = "";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// =================end==================

		// ==================登陆用户检测 ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setQueryequals_xydm(userDep);
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// 学生用户
			myForm.setQuerylike_xh(userName);
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
			String[] outputColumn = { "pk", "xh", "xm", "nj", "xymc", "zymc",
					"bjmc", "xqmc", "ldmc", "cs", "qsh", "bxsj", "sfsf",
					"sfwg", "shzt" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy) };

		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxjgManage");
	}

	/**
	 * 公寓报修(评价)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward gybxPj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx = "pj";
		// 主键
		String pk = request.getParameter("pk");
		myForm.setPkValue(pk);
		// 访问路径
		String path = "gygl_bxjgManage.do";
		// 提示信息
		String message = "";
		// 是否申请成功
		boolean result = false;
		// 相关信息
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("pk", pk);
		// =================end==================

		// ================= 相关信息取得 ==================
		if (!Base.isNull(pk)) {
			rs = service.getBxshInfo(myForm);
		}
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {

			// 保存评价信息
			result = service.saveBxPj(myForm, request);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setUserType(userType);
		rForm.setRs(rs);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("gybxPj");
	}

	/**
	 * 公寓报修(统计结果)
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bxtjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 用户名
		String userName = (String) session.getAttribute("userName");
		// 用户类型
		String userType = (String) session.getAttribute("userType");
		// 用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 辅导员权限
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 模块类型
		String mklx = "tj";
		// 访问路径
		String path = "gygl_bxtjManage.do";
		// 提示信息
		String message = "";
		// 统计范围
		String fw =myForm.getTjfw();
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// 是否学院
		boolean isxy = false;
		// 表头
		List<HashMap<String, String>> topTr = null;
		// 结果列表
		ArrayList<String[]> rsArrList = new ArrayList<String[]>();
		// =================end==================

		// ==================登陆用户检测 ==================
		if ("xy".equalsIgnoreCase(userType) && !fdyQx && !bzrQx) {
			// 学院用户
			myForm.setXydm(userDep);
			isxy = true;
		} else if ("stu".equalsIgnoreCase(userType)) {
			// 学生用户
			myForm.setXh(userName);
		}
		// =================end==================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expGybxInfo(myForm, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================
		
		// ==================执行查询操作 ==================
		if (search) {
			
			topTr = service.getBxTjTop(myForm);
			rsArrList = service.getBxTjList(myForm);
			//标题数
			if (topTr != null && topTr.size() > 0) {
				String tjfs = myForm.getTjfs();
				int num = "bxtjfw_ss".equalsIgnoreCase(tjfs) ? 5 : 2;
				request.setAttribute("topNum", topTr.size() - num);
			}
			//记录数
			if (rsArrList != null && rsArrList.size() > 0) {
				request.setAttribute("rsNum", rsArrList.size()-1);
			}
		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "isxy", "fw" };
		// 其他字段值
		String[] qtzdz = new String[] { String.valueOf(isxy), fw };

		rForm.setTopTr(topTr);
		rForm.setRsArrList(rsArrList);
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setMessage(message);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "gybx");
		// =================end ===================

		return mapping.findForward("bxtjManage");
	}
	
	/**
	 * 公寓卫生信息统计
	 * method wstjManage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wstjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglTyForm myForm = (GyglTyForm) form;
		ZjcmGyglService service = new ZjcmGyglService();
		myForm.setTjxn(request.getParameter("xn"));
		myForm.setTjxq(request.getParameter("xq"));
		String modelPath = servlet.getServletContext().getRealPath("")+"/print/gygl/gygl_jssp.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.wstjManage(myForm, request, wwb);
		return null;
	}
	
	
}
