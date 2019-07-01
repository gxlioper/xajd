package xgxt.pjpy.guizdx;

import java.util.ArrayList;
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
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 贵州大学评奖评优-action类
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

public class PjpyGuizdxAction extends BasicAction {
	
	/**
	 * 评奖评优_参数设置_人数设置_管理（学校）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszXxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
		PjpyTyForm myForm = (PjpyTyForm) form;
		
		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 模块类型(jxj:奖学金；rych：荣誉称号)
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jxj" : mklx;
		myForm.setQueryequals_szlx(mklx);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_gzdx_pjrs_xx";
		// 表名
		String realTable = "guizdx_pjpy_rssz";
		// 访问路径
		String path = "pjpy_rssz.do";
		// 评奖学年
		String xn = Base.getJxjsqxn();
		myForm.setQueryequals_xn(xn);
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			String[] outputColumn = { "xn", "lxmc", "jxjmc", "nj", "xymc",
					"szbl", "szrs" };
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
		rForm.setMklx(mklx);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszXxManage");
	}
	
	/**
	 * 评奖评优_参数设置_人数设置_维护（学校）
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward rsszXxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
		PjpyTyForm myForm = (PjpyTyForm) form;

		// ================= 赋初值 ==================
		HttpSession session = request.getSession();
		// 登陆用户类型
		String userType = (String) session.getAttribute("userType");
		// 登陆用户名
		String userName = (String) session.getAttribute("userName");
		// 登陆用户部门
		String userDep = (String) session.getAttribute("userDep");
		// 模块类型(jxj:奖学金；rych：荣誉称号)
		String mklx = request.getParameter("mklx");
		mklx = Base.isNull(mklx) ? "jxj" : mklx;
		myForm.setSzlx(mklx);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 视图名
		String tableName = "view_njxyzybj";
		// 表名
		String realTable = "guizdx_pjpy_rssz";
		// 访问路径
		String path = "pjpy_rssz.do";
		// 评奖学年
		String xn = Base.getJxjsqxn();
		// =================end==================

		// ===================执行保存操作 ======================
		if ("save".equalsIgnoreCase(doType)) {

			boolean result = service.savePjpyRssz(myForm, realTable);

			request.setAttribute("result", result);

		}
		// =================end ===================

		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "xn" };

		// 其他字段值
		String[] qtzdz = new String[] { xn };
		
		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setMklx(mklx);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================初始化列表值 ======================
		service.setList(myForm, request, "rssz");
		// =================end ===================
		

		return mapping.findForward("rsszXxUpdate");
	}

	/**
	 * 评奖评优_参数设置_人数设置_管理（学院）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rsszXyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGuizdxService service = new PjpyGuizdxService();
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
		String realTable = "guizdx_pjpy_rssz";
		// 访问路径
		String path = "pjpy_rssz.do";
		// 评奖学年
		String xn = Base.getJxjsqxn();
		// 年级
		String nj = myForm.getNj();
		// 奖学金代码
		String jxjdm = myForm.getJxjdm();
		// 奖学金名称
		String jxjmc = service.getOneValue("jxjdmb", "jxjmc", "jxjdm", myForm
				.getJxjdm());
		// 学院名称
		String xymc = service.getOneValue("view_njxyzybj", "xymc", "xydm",
				userDep);
		// 学院设置人数
		String xyrs = service.getOneValue("guizdx_pjpy_rssz", "szrs",
				"xn||szlx||bmlx||szbm||jxjdm||sznj", xn + "jxj" + "xy"
						+ userDep + jxjdm + nj);
		xyrs = Base.isNull(xyrs) ? "0" : xyrs;
		// 是否查询操作
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// =================end==================
		
		// =================权限控制 ==================
		if ("xy".equalsIgnoreCase(userType)) {
			// 学院
			myForm.setXydm(userDep);
			myForm.setXn(xn);
		}
		// =================end==================
		
		// =================执行保存操作 ==================
		if ("save".equalsIgnoreCase(doType)) {
			
			boolean result = service.savePjpyRssz_Zy(myForm, realTable);

			request.setAttribute("result", result);
		}
		// =================end==================
		
		// ==================执行查询操作 ==================
		if (search) {
			ArrayList<String[]> rs = service.getZyRsList(myForm);
			List<HashMap<String, String>> topTr = service.getTopTr("rssz_zy");
			FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
					topTr);
		}
		// =================end ===================
		
		// ==================执行导出操作 ==================
		if ("exp".equalsIgnoreCase(doType)) {

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expRssz_Zy(myForm, response.getOutputStream());

			return null;
		}
		// =================end ===================
		
		// =================初始化request的值 ====================
		RequestForm rForm = new RequestForm();

		// 其他字段
		String[] qtzd = new String[] { "jxjmc", "xymc", "nj", "xyrs" };

		// 其他字段值
		String[] qtzdz = new String[] { jxjmc, xymc, nj, xyrs };
		
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
		service.setList(myForm, request, "rssz");
		// =================end ===================

		return mapping.findForward("rsszXyManage");
	}
}

