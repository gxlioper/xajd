package xsgzgl.dtjs.general.tyjf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.DtjsGeneralService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_团员缴费_通用_Action类
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

public class DtjsTyjfAction extends BasicAction {

	/**
	 * 查询团员缴费结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchTyjfResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		
		

		// ============= 初始化各变量的值 ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String edit =  otherValue[1];//是否点击编辑隐藏值
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
		List<HashMap<String, String>> topTr = service.getDtjsTyjfTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getDtjsTyjfList(myForm, model,
				user);
		// 构建结果集
		String spHtml = "";
		if(null!=edit&&"yes".equals(edit)){
			spHtml= service.createDtjsTyjfHTMLofEdit(rsModel, model, rsArrList,
					user);
		}else{
			spHtml = service.createDtjsTyjfHTML(rsModel, model, rsArrList,
					user);
		}
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
	 * 保存团员缴费
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveTyjf(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存团员缴费
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInit init = new DtjsTyjfInit();
		DtjsTyjfModel model = new DtjsTyjfModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initTyjf(rForm, myForm, user, request);
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.saveBjTyjf(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * 结果导出
	 */
	public ActionForward expTyjf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsGeneralForm myForm = (DtjsGeneralForm) form;
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);
		User user = getUser(request);// 用户对象
		DtjsTyjfModel model = new DtjsTyjfModel();
		model.setXn(Base.currXn);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		String[] title = new String[]{"PK","学年","学号","姓名","年级","班级","应缴团费","应缴团费","欠费"};
		List<String[]> rs = service.getDtjsTyjfList(myForm, model,
				user);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(rs, title, title, response.getOutputStream());
		return mapping.findForward("");
	}
	
}
