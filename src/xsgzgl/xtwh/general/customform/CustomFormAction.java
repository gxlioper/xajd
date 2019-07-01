package xsgzgl.xtwh.general.customform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_Action类
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

public class CustomFormAction extends BasicAction {

	// ===============頁面跳轉 begin=====================
	/**
	 * 自定义表单管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initManage(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormManage");
	}

	/**
	 * 自定义表单参数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormParameter(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initParameter(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormParameter");
	}

	/**
	 * 自定义表单顯示设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSetting(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormSetting");
	}

	/**
	 * 自定义表单查詢設置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customFormSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormInit init = new CustomFormInit();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initSearch(rForm, myForm, user, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customFormSearch");
	}
	
	// ===============頁面跳轉 end=======================

	// ===============表单管理 begin=====================
	/**
	 * 查询自定义表单
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

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

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
		ArrayList<String[]> rsArrList = service.getCustomFormList(myForm,
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

	/**
	 * 删除自定义表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.deleteCustomForm(model, user);
		String message = flag ? "刪除成功" : "刪除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	// ===============表单管理 end=====================

	// ===============表单参数 begin=====================
	/**
	 * 创建选择主键DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createChoosePkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		String source_table = request.getParameter("source_table");
		String source_table_pk = request.getParameter("source_table_pk");
		model.setSource_table(source_table);
		model.setSource_table_pk(source_table_pk);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createChoosePkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 创建选择关联DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createChooseRelateDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		String source_table = request.getParameter("source_table");
		String assistant_table = request.getParameter("assistant_table");
		String lx = request.getParameter("lx");
		model.setSource_table(source_table);
		if("one".equalsIgnoreCase(lx)){
			model.setAssistant_table_one(assistant_table);
		}else{
			model.setAssistant_table_two(assistant_table);
		}	
		// =================== end ===================

		// ==================构建前台页面====================
		service.createChooseRelateDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存自定义表单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCustomForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveCustomForm(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// ===============表单参数 end=====================

	// ===============表单查詢begin=====================
	/**
	 * 创建自定義表單查詢
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCustomSearch(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createCustomSearch(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建查詢操作字段TABLE
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSearchCzzdTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createSearchCzzdTable(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存查詢字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSearchZd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveSearchZd(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 刪除查詢字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSearchZd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.deleteSearchZd(model, user);
		String message = flag ? "刪除成功" : "刪除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存查询字段显示顺序
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSearchZdXssx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveSearchZdXssx(model, user);
		String message = flag ? "刪除成功" : "刪除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	// ===============表单查詢 end=====================
	
	// ===============表单设置 begin=====================
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
	public ActionForward saveTable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
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
	 * 创建自定義表單
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCustomForm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		String form_id = request.getParameter("form_id");
		model.setForm_id(form_id);
		
		String contextPath = request.getContextPath();
		model.setContextPath(contextPath);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createCustomForm(model, user, response);
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
	public ActionForward createClickedObj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		String td_id = request.getParameter("td_id");
		if (!Base.isNull(td_id)) {
			// model.setTd_id(td_id.split("!!luojw!!"));
		}
		// =================== end ===================

		// ==================构建前台页面====================
		service.createClickedObj(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 保存合并列
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalitionCol(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveCoalitionCol(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 取消合并列
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelCoalitionCol(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.cancelCoalitionCol(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存合并行
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCoalitionRow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveCoalitionRow(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 取消合并行
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelCoalitionRow(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.cancelCoalitionRow(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 获得合并信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getCoalitionInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		HashMap<String, String> map = service.getCoalitionInfo(model);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(JSONArray.fromObject(map));

		return null;
	}

	/**
	 * 创建操作字段TABLE
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCzzdTable(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createCzzdTable(model, user, response);
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
	public ActionForward saveZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		boolean flag = service.saveZd(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建国家地区DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createAreaDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createAreaDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建修改字段配置DIV
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createConfigureDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createConfigureDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 修改字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateZd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormService service = new CustomFormService();
		CustomFormModel model = new CustomFormModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================执行修改操作====================
		boolean flag = service.updateZd(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ==================执行修改操作 end================

		return null;
	}
	// ===============表单设置 end=====================
	
	// ===============其他方法 begin=====================
	/**
	 * 学生照片上传
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomFormForm myForm = (CustomFormForm) form;
		CustomFormService service = new CustomFormService();

		User user = getUser(request);// 用户对象

		// ============= 执行保存操作 ============
		boolean flag = service.saveStuPic(myForm, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		//request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	// ===============其他方法 end=====================
}
