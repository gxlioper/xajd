package xsgzgl.szdw.general.tjcx.szry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;
import xsgzgl.szdw.general.inter.tjcx.TjcxSzryInterface;

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

public class SzryAction extends BasicAction {

	/**
	 * 统计思政人员
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward statSzry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzryModel model = new SzryModel();
		SzryInit init = new SzryInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSzrySearch(rForm, myForm, user, request);
		TjcxSzryInterface service = myService.getTjcxSzryService(myForm);

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
		service.createSzryStatDiv(myForm, model, user, response);
		// ==================显示内容 end========================

		return null;
	}
	/**
	 * 统计数据导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward export(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SzryInit init = new SzryInit();
		
		init.initSzrySearch(rForm, myForm, user, request);
		TjcxSzryInterface service = myService.getTjcxSzryService(myForm);
		
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.exportData(response.getOutputStream(),myForm,user);
		return null;
	}
	
	
}
