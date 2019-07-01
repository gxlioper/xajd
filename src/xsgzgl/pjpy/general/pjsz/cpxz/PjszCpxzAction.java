package xsgzgl.pjpy.general.pjsz.cpxz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszCpxzInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_参评小组_通用_Action类
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

public class PjszCpxzAction extends BasicAction {
	
	/**
	 * 查询评奖设置（参评小组设置）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszCpxz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszCpxzTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getPjszCpxzList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createPjszCpxzHTML(rsModel, model, rsArrList,
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
	 * 保存参评组设置（未勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzNoChecked(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);

		// 参评组名称
		String cpzmc = request.getParameter("cpzmc");
		if (!Base.isNull(cpzmc)) {
			model.setCpzmc(myService.unicode2Gbk(cpzmc));
		}
		// 年级
		String nj = request.getParameter("nj");
		if (!Base.isNull(nj)) {
			searchModel.setSearch_tj_nj(nj.split("!!@@!!"));
		}
		// 学院
		String xy = request.getParameter("xy");
		if (!Base.isNull(xy)) {
			searchModel.setSearch_tj_xy(xy.split("!!@@!!"));
		}
		// 专业
		String zy = request.getParameter("zy");
		if (!Base.isNull(zy)) {
			searchModel.setSearch_tj_zy(zy.split("!!@@!!"));
		}
		// 班级
		String bj = request.getParameter("bj");
		if (!Base.isNull(bj)) {
			searchModel.setSearch_tj_bj(bj.split("!!@@!!"));
		}
		
		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		boolean flag = service.saveCpxz(myForm,model, user, "no_checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存参评组设置（勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);

		// 参评组名称
		String cpzmc = request.getParameter("cpzmc");
		if (!Base.isNull(cpzmc)) {
			model.setCpzmc(myService.unicode2Gbk(cpzmc));
		}
		
		// 班级代码
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存参评小组设置（勾选，新的） ============
		boolean flag = service.saveCpxz(myForm, model, user, "checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除参评组设置（未勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCpxzNoChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// 年级
		String nj = request.getParameter("nj");
		if (!Base.isNull(nj)) {
			searchModel.setSearch_tj_nj(nj.split("!!@@!!"));
		}
		// 学院
		String xy = request.getParameter("xy");
		if (!Base.isNull(xy)) {
			searchModel.setSearch_tj_xy(xy.split("!!@@!!"));
		}
		// 专业
		String zy = request.getParameter("zy");
		if (!Base.isNull(zy)) {
			searchModel.setSearch_tj_zy(zy.split("!!@@!!"));
		}
		// 班级
		String bj = request.getParameter("bj");
		if (!Base.isNull(bj)) {
			searchModel.setSearch_tj_bj(bj.split("!!@@!!"));
		}
		
		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		boolean flag = service.deleteCpxz(myForm, model, user, "no_checked");
		String message = flag ? "取消成功" : "取消失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除参评组设置（勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteCpxzChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// 班级代码
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存参评小组设置（勾选） ============
		boolean flag = service.deleteCpxz(myForm, model, user, "checked");
		String message = flag ? "取消成功" : "取消失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存参评小组自动设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpxzZdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// 参评组规则
		String cpzgz = request.getParameter("cpzgz");
		model.setCpzgz(cpzgz);
		// ============= end ============

		// ============= 保存参评小组自动设置 ============
		boolean flag = service.saveCpxzZdsz(model, user);
		String message = flag ? "设置成功" : "设置失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 检测参评小组提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkCpxzSubmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszCpxzInit init = new PjszCpxzInit();
		PjszCpxzModel model = new PjszCpxzModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initCpxz(rForm, myForm, user, request);
		PjszCpxzInterface service = myService.getPjszCpxzService(myForm);
		
		// 参评组规则
		String cpzgz = request.getParameter("cpzgz");
		model.setCpzgz(cpzgz);
		// ============= end ============

		// ============= 保存参评小组自动设置 ============
		String message = service.checkCpxzSubmit(model, user);
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
