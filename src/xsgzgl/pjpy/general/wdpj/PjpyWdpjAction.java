package xsgzgl.pjpy.general.wdpj;

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
import xsgzgl.pjpy.general.inter.PjpyWdpjInterface;
import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_通用_Action类
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

public class PjpyWdpjAction extends BasicAction {

	/**
	 * 查询评奖设置（我的评奖统计）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjpyWdpj(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		PjpyWdpjModel model = new PjpyWdpjModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initWdpj(rForm, myForm, user, request);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjpyWdpjTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getPjpyWdpjList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createPjpyWdpjHTML(rsModel, myForm, model,
				rsArrList, user);
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
	 * 显示学生评奖申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdpjView(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象
		
		String ie =request.getParameter("ie");
		rsModel.setIe(ie);
		
		String stylePath =request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);
		
		// ============= 初始化各变量的值 ============
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
		String xmdm = request.getParameter("xmdm");
	
		service.showWdpjView(rsModel,xmdm, user, response);
		
		return null;
	}
	
	/**
	 * 显示学生评奖申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showWdpjLssb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		String[] otherValue = request.getParameter("otherValue")
		.split("!!@@!!");
		
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		// ============= 初始化各变量的值 ============
		//init.initSaveXssq(rForm, myForm, user, request);
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		// 操作类型
		String xh=request.getParameter("xh");
		
		String xmdm = request.getParameter("xmdm");

		//消息信息
		String message="";
		
		//保存数据方法
			
		service.showWdpjView( rsModel,xmdm, user, response);
		
		return null;
	}
	
	/**
	 * 显示学生评奖申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showLnzcInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SearchRsModel rsModel = new SearchRsModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象
		
		String ie =request.getParameter("ie");
		rsModel.setIe(ie);
		
		String stylePath =request.getParameter("stylePath");
		rsModel.setStylePath(stylePath);
		
		String pkValue=request.getParameter("pkValue");
		
		// ============= 初始化各变量的值 ============
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		
		String xmdm = request.getParameter("xmdm");
	
		service.showLnzcInfo(rsModel,pkValue,user, response);
		
		return null;
	}
	
	/**
	 * 创建我的审核DIV
	 * 
	 * @date 2013-02-04
	 * @author 伟大的骆
	 */
	public ActionForward createWdshDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjModel model = new PjpyWdpjModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		PjpyWdpjInterface service = myService.getPjpyWdpjService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createWdshDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
}
