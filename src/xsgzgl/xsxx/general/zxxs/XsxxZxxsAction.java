package xsgzgl.xsxx.general.zxxs;

import java.io.InputStream;
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
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.XsxxGeneralService;
import xsgzgl.xsxx.general.inter.XsxxZxxsInterface;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_通用_Action类
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

public class XsxxZxxsAction extends BasicAction{
	
	/**
	 * 查询在校学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXsxxZxxs(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getXsxxZxxsTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getXsxxZxxsList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createXsxxZxxsHTML(rsModel, model, rsArrList,
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
	 * 保存学生信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXsxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		boolean flag = service.saveXsxx(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存毕业处理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBycl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XsxxZxxsInit init = new XsxxZxxsInit();
		XsxxZxxsModel model = new XsxxZxxsModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initZxxs(rForm, myForm, user, request);
		XsxxZxxsInterface service = myService.getXsxxZxxsService(myForm);
		
		myService.getModelValue(model, request);		
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		boolean flag = service.saveBycl(model, user);
		String message = flag ? "操作成功" : "操作失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 照片上传
	 */
	public ActionForward uploadStuPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();
		String type=(String) request.getAttribute("type");

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 执行保存操作 ============
		String flag = service.saveStuPic(myForm, user);
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
	
	/**
	 * 华中师范大学高考照片上传
	 */
	public ActionForward uploadStuGkPic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxZxxsService service = new XsxxZxxsService();
		User user = getUser(request);// 用户对象

		// ============= 执行保存操作 ============
		String flag = service.saveStuGkPic(myForm, user);
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(flag);
	
		return null;
	}
}
