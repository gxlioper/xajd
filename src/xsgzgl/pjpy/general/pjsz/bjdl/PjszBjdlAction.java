package xsgzgl.pjpy.general.pjsz.bjdl;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszBjdlInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_班级大类_通用_Action类
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

public class PjszBjdlAction extends BasicAction {
	
	/**
	 * 查询评奖设置（班级大类设置）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszBjdl(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInit init = new PjszBjdlInit();
		PjszBjdlModel model = new PjszBjdlModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initBjdl(rForm, myForm, user, request);
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszBjdlTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getPjszBjdlList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createPjszBjdlHTML(rsModel, model, rsArrList,
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
	 * 保存班级大类设置（未勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjdlNoChecked(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInit init = new PjszBjdlInit();
		PjszBjdlModel model = new PjszBjdlModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initBjdl(rForm, myForm, user, request);
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);

		// 班级大类名称
		String bjdlmc = request.getParameter("bjdlmc");
		if (!Base.isNull(bjdlmc)) {
			model.setBjdlmc(myService.unicode2Gbk(bjdlmc));
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

		// ============= 保存班级大类设置（未勾选） ============
		boolean flag = service.saveBjdl(myForm,model, user, "no_checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存班级大类设置（勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjdlChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInit init = new PjszBjdlInit();
		PjszBjdlModel model = new PjszBjdlModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initBjdl(rForm, myForm, user, request);
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);

		// 班级大类名称
		String bjdlmc = request.getParameter("bjdlmc");
		if (!Base.isNull(bjdlmc)) {
			model.setBjdlmc(myService.unicode2Gbk(bjdlmc));
		}
		
		// 班级代码
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存参评小组设置（勾选，新的） ============
		boolean flag = service.saveBjdl(myForm, model, user, "checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 删除班级大类设置（未勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBjdlNoChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInit init = new PjszBjdlInit();
		PjszBjdlModel model = new PjszBjdlModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initBjdl(rForm, myForm, user, request);
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);
		
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

		// ============= 取消班级大类设置（未勾选） ============
		boolean flag = service.deleteBjdl(myForm, model, user, "no_checked");
		String message = flag ? "取消成功" : "取消失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除班级大类设置（勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteBjdlChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszBjdlInit init = new PjszBjdlInit();
		PjszBjdlModel model = new PjszBjdlModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initBjdl(rForm, myForm, user, request);
		PjszBjdlInterface service = myService.getPjszBjdlService(myForm);
		
		// 班级代码
		String bjdm = request.getParameter("bjdm");
		if (!Base.isNull(bjdm)) {
			model.setBjdm(bjdm.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 取消班级大类设置（勾选） ============
		boolean flag = service.deleteBjdl(myForm, model, user, "checked");
		String message = flag ? "取消成功" : "取消失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
