package xsgzgl.pjpy.general.tjcx.hjjehz;

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
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.tjcx.TjcxHjjehzInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖金额汇总_通用_Action类
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

public class TjcxHjjehzAction extends BasicAction {

	/**
	 * 查询获奖金额汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createHjjehzHTML(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxHjjehzInit init = new TjcxHjjehzInit();
		TjcxHjjehzModel model = new TjcxHjjehzModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxHjjehzInterface service = myService.getTjcxHjjehzService(myForm);

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
		List<HashMap<String, String>> topTr = service.getHjjehzTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getHjjehzList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createHjjehzHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setCheckBoxRs("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}

	/**
	 * 导出获奖金额汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expHjjehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxHjjehzInit init = new TjcxHjjehzInit();
		TjcxHjjehzModel model = new TjcxHjjehzModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxHjjehzInterface service = myService.getTjcxHjjehzService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expHjjehz(model, response.getOutputStream());
		// ============= end ============

		return null;
	}
	
	/**
	 * 传媒获奖名单汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createCMHJMDHZHTML(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxHjjehzInit init = new TjcxHjjehzInit();
		TjcxHjjehzModel model = new TjcxHjjehzModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		
		TjcxHjjehzInterface service = myService.getTjcxHjjehzService(myForm);

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
		List<HashMap<String, String>> topTr = service.getCMHJMDHZTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getCMHJMDHZList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createHjjehzHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setCheckBoxRs("no");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	
	/**
	 * 传媒导出获奖名单汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expCmhjmdhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxHjjehzInit init = new TjcxHjjehzInit();
		TjcxHjjehzModel model = new TjcxHjjehzModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.init(rForm, myForm, user, request);
		TjcxHjjehzInterface service = myService.getCmhjmdhzService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expCmhjmchz(model, response.getOutputStream());
		// ============= end ============

		return null;
	}
}
