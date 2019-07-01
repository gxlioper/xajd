package xsgzgl.xsxx.cssz.grxx;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.cssz.XsxxCsszInit;
import xsgzgl.xsxx.cssz.XsxxCsszInterface;
import xsgzgl.xsxx.model.ZdqxModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxCsszAction extends BasicAction {

	// =================以下頁面跳轉=======================

	/**
	 * 学生信息_参数设置_个人信息_学生修改字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszService service = new XsxxCsszService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initGrxx(rForm, myForm, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("xszdManage");
	}

	/**
	 * 学生信息_参数设置_个人信息_老师修改字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lszdManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszInterface service = init.getCsszService("grxx");// 个人信息
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initGrxx(rForm, myForm, request);
		HashMap<String, String> rs = service.getCsszInfo(myForm);
		// =================== end ===================

		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("lszdManage");
	}

	// =================以下方法=======================

	/**
	 * 保存参数设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszInterface service = init.getCsszService("grxx");// 个人信息
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		String sfsh = request.getParameter("sfsh");// '是否审核';
		myForm.setSfsh(sfsh);

		String lcid = request.getParameter("lcid");// '流程ID';
		myForm.setLcid(lcid);

		String sqkssj = request.getParameter("sqkssj");// '申请开始时间';
		myForm.setSqkssj(sqkssj);

		String sqjssj = request.getParameter("sqjssj");// '申请结束时间';
		myForm.setSqjssj(sqjssj);

		String shkssj = request.getParameter("shkssj");// '审核开始时间';
		myForm.setShkssj(shkssj);

		String shjssj = request.getParameter("shjssj");// '审核结束时间';
		myForm.setShjssj(shjssj);
		
		String over = "no";// '设置结束';
		myForm.setOver(over);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.saveCssz(myForm, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 显示字段设置Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszService service = new XsxxCsszService();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 用户类型
		String yhlx = request.getParameter("yhlx");
		// =================== end ===================

		// ==================构建前台页面========================
		service.showZdxzDiv(yhlx, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存字段权限
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdqx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszService service = new XsxxCsszService();
		User user = getUser(request);// 用户对象
		ZdqxModel zdqxModel = new ZdqxModel();

		// ============= 初始化各变量的值 ============
		String[] zd = request.getParameter("zd").split("!!@@!!");// '字段';
		zdqxModel.setZd(zd);
		
		String yhlx = request.getParameter("yhlx");//用户类型
		myForm.setYhlx(yhlx);
		
		String over = request.getParameter("over");//用户类型
		myForm.setOver(over);
		
		myForm.setZdqxModel(zdqxModel);
		// ============= 初始化各变量的值 end============

		// ==================执行保存操作========================
		boolean flag = service.saveZdqx(myForm, user);
		String message = flag ? "设置成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
