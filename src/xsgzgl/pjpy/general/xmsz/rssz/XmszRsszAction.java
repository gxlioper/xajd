package xsgzgl.pjpy.general.xmsz.rssz;

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
import xsgzgl.pjpy.general.inter.xmsz.XmszRsszInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_通用_Action类
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

public class XmszRsszAction extends BasicAction {

	/**
	 * 查询项目设置（人数设置）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchXmszRssz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============	
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// 项目代码
		String xmdm = otherValue[1];
		myForm.setXmdm(xmdm);
		
		// 排序
		String arrange = otherValue[2];
		myForm.getSearchModel().setArrange(arrange);
		// 排序方式
		String fashion = otherValue[3];
		myForm.getSearchModel().setFashion(fashion);
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
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
		List<HashMap<String, String>> topTr = service.getXmszRsszTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getXmszRsszList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createXmszRsszHTML(rsModel, model, rsArrList,
				user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setArrange("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 保存设置比例（未勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSzblNoChecked(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		// 设置比例
		String szbl = request.getParameter("szbl");
		model.setSzbl(szbl);
		
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
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
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= 保存设置设置（未勾选） ============
		boolean flag = service.saveSzbl(myForm,model, user, "no_checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存设置比例（勾选）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSzblChecked(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 设置比例
		String szbl = request.getParameter("szbl");
		model.setSzbl(szbl);
		
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		// 主键值
		String pkValue = request.getParameter("pkValue");
		if (!Base.isNull(pkValue)) {
			model.setPkValue(pkValue.split("!!@@!!"));
		}
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= 保存设置比例（勾选） ============
		boolean flag = service.saveSzbl(myForm, model, user, "checked");
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存确定人数
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveQdrs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszRsszInit init = new XmszRsszInit();
		XmszRsszModel model = new XmszRsszModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		// 确定人数
		String qdrs = request.getParameter("qdrs");
		if (!Base.isNull(qdrs)) {
			model.setQdrs(qdrs.split("!!@@!!"));
		}
		
		// 主键值
		String pkValue = request.getParameter("pkValue");
		if (!Base.isNull(pkValue)) {
			model.setPkValue(pkValue.split("!!@@!!"));
		}
		
		init.initRssz(rForm, myForm, user, request);
		XmszRsszInterface service = myService.getXmszRsszService(myForm);
		// ============= end ============

		// ============= 保存确定人数 ============
		boolean flag = service.saveQdrs(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
