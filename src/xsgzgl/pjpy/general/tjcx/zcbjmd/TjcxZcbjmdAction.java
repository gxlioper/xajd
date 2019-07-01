package xsgzgl.pjpy.general.tjcx.zcbjmd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.tjcx.TjcxZcbjmdInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_综测班级名单_通用_Action类
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

public class TjcxZcbjmdAction extends BasicAction {

	/**
	 * 创建综测班级名单HTMl【等级考试】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createZcbjmdDjksHTML(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxZcbjmdInit init = new TjcxZcbjmdInit();
		TjcxZcbjmdModel model = new TjcxZcbjmdModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxZcbjmdInterface service = myService.getTjcxZcbjmdService(myForm);

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

		// ==================显示内容 end========================

		// ==================构建前台页面========================
		service.createZcbjmdDjksHTML(myForm, model, user, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 创建综测班级名单HTMl【无等级考试】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createZcbjmdNoDjksHTML(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxZcbjmdInit init = new TjcxZcbjmdInit();
		TjcxZcbjmdModel model = new TjcxZcbjmdModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxZcbjmdInterface service = myService.getTjcxZcbjmdService(myForm);

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

		// ==================显示内容 end========================

		// ==================构建前台页面========================
		service.createZcbjmdNoDjksHTML(myForm, model, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 导出综测班级名单HTMl
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expZcbjmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxZcbjmdInit init = new TjcxZcbjmdInit();
		TjcxZcbjmdModel model = new TjcxZcbjmdModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxZcbjmdInterface service = myService.getTjcxZcbjmdService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expZcbjmd(model, response.getOutputStream());
		// ============= end ============

		return null;
	}
}
