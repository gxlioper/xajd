package xsgzgl.xsxx.general.dljc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommForm;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxDljcInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生信息_登录检测_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class XsxxDljcAction extends BasicAction {

	// ===================页面跳转 begin=============================

	/**
	 * 检测信息配置【页面跳转】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initDljcSetting(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcSetting.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// ===================页面跳转 end=============================

	// ===================查询页面 begin=============================

	/**
	 * 查询登录检测
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchDljc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getXsxxDljclTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getXsxxDljcList(myForm, model,
				user);
		// 构建结果集
		String spHtml = "";
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 重置信息完善
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resetDljc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 执行重置操作 ============
		boolean flag = service.resetDljc(myForm, model, user);
		String message = flag ? "操作成功" : "操作失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================查询页面 end=============================

	// ===================设置页面 begin=============================

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
	public ActionForward createZdszDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSetting(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// =================== end ===================

		// ==================构建前台页面========================
		service.createZdszDiv(model, user, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存字段设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);

		myService.getModelValue(model, request);
		// ============= end ============

		// ==================执行保存操作========================
		boolean flag = service.saveZdsz(model, user);
		String message = flag ? "设置成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================设置页面 end=============================

	// ===================信息完善 begin=============================

	/**
	 * 创建信息完善Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createXxwsDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();

		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSetting(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// =================== end ===================

		// ==================构建前台页面========================
		service.createXxwsDiv(model, user, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 保存字段设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXxws(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		XsxxDljcModel model = new XsxxDljcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initDljcSearch(rForm, myForm, user, request);
		XsxxDljcInterface service = myService.getXsxxDljcService(myForm);
		// ============= end ============

		// ==================执行保存操作========================
		boolean flag = service.saveXxws(user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		// ==================执行保存操作 end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===================信息完善 end=============================
}
