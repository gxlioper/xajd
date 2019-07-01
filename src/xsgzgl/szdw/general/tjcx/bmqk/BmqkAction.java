package xsgzgl.szdw.general.tjcx.bmqk;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.tjcx.TjcxBmqkInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_思政人员_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class BmqkAction extends BasicAction {

	/**
	 * 统计部门情况
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward statBmqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		BmqkModel model = new BmqkModel();
		BmqkInit init = new BmqkInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initBmqkSearch(rForm, myForm, user, request);
		TjcxBmqkInterface service = myService.getTjcxBmqkService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================高级查询相关========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		service.createBmqkStatDiv(myForm, model, user, response);
		// ==================显示内容 end========================

		return null;
	}
	/**
	 * 統計數據導出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		BmqkModel model = new BmqkModel();
		BmqkInit init = new BmqkInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initBmqkSearch(rForm, myForm, user, request);
		TjcxBmqkInterface service = myService.getTjcxBmqkService(myForm);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportData(response.getOutputStream(),myForm,user);
		return null;
	}
	
}
