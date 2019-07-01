package xsgzgl.pjpy.general.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyZhcpInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_Action类
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

public class PjpyZhcpAction extends BasicAction {
	
	// ---------------------综测信息维护 begin--------------------------
	/**
	 * 查询综合测评（综测信息）维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZhcpZcxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcp(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// 项目代码
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		
		// 项目名称
		String xmmc = otherValue[2];
		
		// 来源表
		String lyb =otherValue[3];
		model.setLyb(lyb);
		
		//项目级别
		String xmjb =otherValue[4];
		model.setXmjb(xmjb);
		
		if(!Base.isNull(xmmc)){
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
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
		List<HashMap<String, String>> topTr = service.getZhcpZcxxTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZhcpZcxxList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createZhcpZcxxHTML(rsModel, model, rsArrList,
				user);
		
		// 构建学校个性化信息隐藏域
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
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
	 * 保存综合测评信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZhcpInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);

		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		boolean flag = service.saveZhcpInfo(myForm, request, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	
	/**
	 * 显示字段修改Div(扩展字段修改)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showZdxgDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		
		PjpyGeneralService myService = new PjpyGeneralService();
		
		PjpyZhcpInit init = new PjpyZhcpInit();
		
		RequestForm rForm = new RequestForm();
		
		User user = getUser(request);// 用户对象
		
		init.initZhcpMaintain(rForm, myForm, user, request);
		
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		// ============= 初始化各变量的值 ============
		// 用户类型
		String zd = request.getParameter("zd");

		// 隐藏域字段ID
		String zdid = request.getParameter("zdid");
		// =================== end ===================

		// ==================构建前台页面========================
		service.showZdxgDiv(zd,zdid, user, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 同步来源表数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateLybInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
		
		//同步来源表数据
		boolean flag=false;
		String message="";
		
		BeanUtils.copyProperties(myForm, model);
		
		flag=service.updateLybInfo(myForm, user);
			
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
	
		response.getWriter().print(message);
		
		return null;
	}
	
	// ---------------------综测信息维护 end--------------------------	
	
	// ---------------------综测结果查询 begin-------------------------
	
	/**
	 * 查询综合测评结果(本次评奖)	  
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchZhcpResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
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
		List<HashMap<String, String>> topTr = service.getZhcpResultTop(
				myForm,user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getZhcpResultList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createZhcpResultHTML(rsModel, model, rsArrList,
				user);
		
		// 构建学校个性化信息隐藏域
		spHtml+=service.createKidneyDiv(rsModel, model, rsArrList, user);
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setArrange("no");
		
		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kindChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
//		 标题
		boolean flag= service.saveKindChoose(model, user);
		
		String message="";
		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		
		response.setContentType("text/html;charset=gbk");
	
		response.getWriter().print(message);
		
		return null;
	}
	
	/**
	 * 综合测评_综测总分计算
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhcpAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
 
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();
		PjpyZhcpModel model = new PjpyZhcpModel();
		
		RequestForm rForm = new RequestForm();

		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initZhcpResult(rForm, myForm, user, request);
		PjpyZhcpInterface service = myService.getPjpyZhcpService(myForm);
		
		CommService commService=new CommService();
		
		commService.getModelValue(model, request);
		
		//综测总分计算
		boolean flag=false;
		String message="";
		
		BeanUtils.copyProperties(myForm, model);
		
		flag = service.account(myForm, user);

		message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	
	// ---------------------综测结果查询 end-------------------------
	
}
