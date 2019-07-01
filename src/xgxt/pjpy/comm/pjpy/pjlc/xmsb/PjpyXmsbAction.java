package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;
import xgxt.utils.Pages;
import xgxt.xsxx.comm.ajax.XsxxAjaxModel;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目上报-action类
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

public class PjpyXmsbAction extends BasicAction {

	/**
	 * 评奖评优_评奖流程_项目上报
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xmsbManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("xmsbManage");
	}	
	
	/**
	 * 获得项目上报学生列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXmsbXsList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= 初始化各变量的值 ============		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// 项目代码
		String xmdm = otherValue[0];
		myForm.setXmdm(xmdm);
		// 班级代码
		String bjdm = otherValue[1];
		myForm.setBjdm(bjdm);
		// 排序
		String arrange = otherValue[2];
		myForm.setArrange(arrange);
		// 排序方式
		String fashion = otherValue[3];
		myForm.setFashion(fashion);
		// 查询条件
		String search_condition = otherValue[4];
		myForm.setSearch_condition(search_condition);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================

		// ==================分页相关========================
		Pages pages = service.setPages("",request);
		myForm.setPages(pages);
		// ==================分页相关 end========================

		// ==================高级查询相关========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================高级查询相关 end========================

		// ==================显示内容========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// 标题
		String spHtml = service.getLssbHtml(rsModel, myForm, user);
		// ==================显示内容 end========================
		
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");
		rsModel.setLeft("no");
		rsModel.setNoSpace("no");
		rsModel.setArrange("yes");
		
		service.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================
		
		return null;
	}
	
	/**
	 * 上报范围确定
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sbfwChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		RequestForm rForm = new RequestForm();

		// ============= 初始化各变量的值 ============
		init.getPjxmsbRForm(rForm, myForm, request);
		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String showMessage = "";// 提示信息
		// =================== end ===================		

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		List<HashMap<String, String>> xmdmList = service.getXmOption(myForm);
		request.setAttribute("xmdmList", xmdmList);
		// 初始化列表
		CommForm commForm = new CommForm();
		service.setList(commForm, rForm, request);
		// =================== end ===================
		
		return mapping.findForward("sbfwChoose");
	}
	
	/**
	 * 加载项目下拉列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setXmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		User user = getUser(request);// 用户对象
		
		//项目名称
		String xmmc = request.getParameter("xmdm");
		
		if (!Base.isNull(xmmc)){
			xmmc = URLDecoder.decode(xmmc, "UTF-8");
		}
			
		myForm.setXmmc(xmmc);
		
		List<HashMap<String, String>> map = service.getXmOption(myForm);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * 保存项目上报
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		User user = getUser(request);// 用户对象

		// 评奖学号
		String[] pjxh = request.getParameter("xh").split("!!@@!!");
		myForm.setPjxh(pjxh);
		// 项目代码
		String xmdm = request.getParameter("xmdm");
		myForm.setXmdm(xmdm);

		// ============= 保存项目上报 ============
		// 提示信息
		String message = service.judgeSbzg(myForm);

		if (Base.isNull(message)) {
			boolean flag = service.saveXmsb(myForm, user);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
		}
		// ============= 保存项目上报 end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 初始化学生成绩
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultXscj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmsbForm myForm = (PjpyXmsbForm) form;
		PjpyXmsbService service = new PjpyXmsbService();
		PjpyXmsbInit init = new PjpyXmsbInit();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= 初始化各变量的值 ============
		// 学号
		String xh = request.getParameter("xh");
		myForm.setXh(xh);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmsbRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ==================构建前台页面========================		
		service.createXscjHTML(myForm, response);
		// ==================构建前台页面 end========================
		
		return null;
	}
}
