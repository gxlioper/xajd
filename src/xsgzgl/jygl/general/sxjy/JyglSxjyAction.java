package xsgzgl.jygl.general.sxjy;

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
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.JyglGeneralService;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_实习就业_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class JyglSxjyAction extends BasicAction {
	
	
	/**
	 * 查询历史评奖结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSxjyManage(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getSxjyTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getSxjyList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createSxjyHTML(rsModel, model, rsArrList,
				user);
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
	 * 保存评奖历史信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSxjyUpdate(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.saveSxjy(model, user, request);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}	

	/**
	 * 删除评奖历史信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteSxjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService myService = new JyglGeneralService();
		JyglSxjyModel model = new JyglSxjyModel();
		JyglSxjyInit init = new JyglSxjyInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initSxjyUpdate(rForm, myForm, user, request);
		JyglSxjyInterface service = myService.getJyglSxjyService(myForm);
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.deleteSxjy(model, user, request);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}	
}
