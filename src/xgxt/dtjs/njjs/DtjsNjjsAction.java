package xgxt.dtjs.njjs;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.DtjsForm;
import xgxt.form.RequestForm;
import xgxt.studentInfo.service.XsxxglService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 南京技师党团建设-action类
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

public class DtjsNjjsAction extends BasicAction {

	/**
	 * 党团建设_团员信息_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tyxxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsNjjsService service = new DtjsNjjsService();
		DtjsForm myForm = (DtjsForm) form;

		// ================== 赋初值 ==================
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
		String tableName = "view_njjs_tyxx";
		// 表名
		String realTable = "njjs_tyxxb";
		// 访问路径
		String path = "dtjs_tyxx.do";
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end ===================

		// ==================判断模块,权限======================

		// 登陆用户为学院
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}

		// =================end ===================

		// ==================执行删除操作 ==================
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, realTable);
		}
		// =================end ===================

		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "pk", "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "mzmc", "zzmmmc", "rtsj", "rdsj", };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		// =================end ===================

		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {
			expPageData(request, response, realTable, tableName, null);
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
		service.setList(myForm, request, "tyxx");
		// =================end ===================

		return mapping.findForward("tyxxManage");
	}

	/**
	 * 党团建设_团员信息_维护
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward tyxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsNjjsService service = new DtjsNjjsService();
		XsxxglService stuService = new XsxxglService();
		DtjsForm myForm = (DtjsForm) form;

		// ================= 赋初值 ==================
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// 视图名
		String tableName = "view_njjs_tyxx";
		// 表名
		String realTable = "njjs_tyxxb";
		// 访问路径
		String path = "dtjs_tyxx.do";
		// 学号
		String xh = request.getParameter("xh");
		// 主键值
		String pkValue = request.getParameter("pk");
		pkValue = Base.isNull(xh) ? pkValue : xh;
		// 提示信息
		String message = "";
		// 团员信息
		HashMap<String, String> rs = new HashMap<String, String>();
		// 保存数据成功标志
		boolean result = false;
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {
			result = service.saveTyxx(myForm, realTable, request);
			message = result ? "操作成功" : "操作失败";
		}
		// =================end ===================

		// ===================执行查看操作 ======================

		// 增加
		if ("add".equalsIgnoreCase(doType)) {

			if (!Base.isNull(xh)) {

				selectPageDataByOne(request, realTable, tableName, pkValue);

				rs = (HashMap<String, String>) request.getAttribute("rs");

				if (Base.isNull(rs.get("xh"))) {
					rs = stuService.selectStuinfo(xh);
				}
			}

			rs.put("xh", xh);
		}
		// 查看（修改）
		else if ("update".equalsIgnoreCase(doType)
				|| "view".equalsIgnoreCase(doType) || result) {

			HashMap<String, String> map = stuService.selectStuinfo(pkValue);

			selectPageDataByOne(request, realTable, tableName, pkValue);

			rs = (HashMap<String, String>) request.getAttribute("rs");
			rs.put("jg", map.get("jg"));

		}

		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "message" };
		String[] qtzdz = new String[] { message };

		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "tyxx");
		// =================end ===================

		return mapping.findForward("tyxxUpdate");
	}
}
