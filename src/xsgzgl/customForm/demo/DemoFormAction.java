package xsgzgl.customForm.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DEMO_Action类
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

public class DemoFormAction extends BasicAction {

	// ===============頁面跳轉 begin=====================

	/**
	 * 自定义表单_功能模块_管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward demoFormManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initDemoFormManage(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("demoFormManage");
	}

	/**
	 * 自定义表单_功能模块_字段設置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xszdSetupDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initXszdSetup(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("xszdSetupDetail");
	}

	/**
	 * 自定义表单_功能模块_字段設置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jgcxSetupDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormInit init = new DemoFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initJgcxSetup(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jgcxSetupDetail");
	}

	// ===============頁面跳轉 end=======================

	// ===============查詢結果 begin=====================

	/**
	 * 查询项目申请结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		// 标题
		List<HashMap<String, String>> topTr = service.getCustomFormTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.ggetCustomFormList(myForm,
				model, user);
		// 构建结果集
		String spHtml = service.createCustomFormHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	// ===============查詢結果 end=====================

	// ===============表單操作 begin=====================
	
	/**
	 * 顯示自定義表單
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showCustomForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		DemoFormInit init = new DemoFormInit();

		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 begin =======
		// 初始化
		init.initXszdSetup(rForm, myForm, user, request);
		BeanUtils.copyProperties(myForm, model);
		// =================== end ===================

		// ==================构建前台页面====================
		service.showCustomForm(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 創建選中的對象
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createClickedObj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DemoFormForm myForm = (DemoFormForm) form;
		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		DemoFormInit init = new DemoFormInit();

		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 begin =======
		String td_id = request.getParameter("td_id");
		if (!Base.isNull(td_id)) {
			model.setTd_id(td_id.split("!!@@!!"));
		}
		// =================== end ===================

		// ==================构建前台页面====================
		service.createClickedObj(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存表單
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		boolean flag = service.saveTable(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存合并单元格
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalition(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DemoFormService service = new DemoFormService();
		DemoFormModel model = new DemoFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		boolean flag = service.saveTable(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ==================构建前台页面 end================

		return null;
	}
	// ===============表單操作 end=====================
}
