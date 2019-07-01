package xsgzgl.qgzx.tjcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-23 下午14:19:22
 * </p>
 */

public class QgzxTjcxAjax extends BasicAction{
	
	/**
	 * 月度酬金发放统计首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ydcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=ydcjfftjCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByYdcjfftjCx();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getYdcjfftjCx(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByYdcjfftjCx(myForm,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 年度酬金发放统计首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ndcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=ndcjfftjCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByNdcjfftjCx();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getNdcjfftjCx(myForm);
		ArrayList<String[]> rsArrListAll = (ArrayList<String[]>) service.getNdcjfftjCxAll(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByNdcjfftjCx(myForm, rsModel, rsArrList, rsArrListAll, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 部门酬金发放统计首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=bmcjfftjCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByBmcjfftjCx();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getBmcjfftjCx(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByBmcjfftjCx(myForm,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 个人酬金发放统计首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjfftjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("qgzx_cjtjcx.do?method=grcjfftjCx");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTrByGrcjfftjCx();
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getGrcjfftjCx(myForm);
		ArrayList<String[]> rsArrListAll = (ArrayList<String[]>) service.getGrcjfftjCxAll(myForm);
		// 构建结果集
		String spHtml = service.createSearchHTMLByGrcjfftjCx(myForm,rsModel, rsArrList,rsArrListAll, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 导出月度酬金发放统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expYdcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expYdcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * 导出部门酬金发放统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expBmcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expBmcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * 导出个人酬金发放统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expGrcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expGrcjfftj(response.getOutputStream(),myForm);
		return null;
	}
	
	/**
	 * 导出年度酬金发放统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expNdcjfftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxTjcxService service = new QgzxTjcxService();
		QgzxTjcxForm myForm = (QgzxTjcxForm) form;
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		myForm.setUserName(user.getUserName());
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.expNdcjfftj(response.getOutputStream(),myForm);
		return null;
	}
}