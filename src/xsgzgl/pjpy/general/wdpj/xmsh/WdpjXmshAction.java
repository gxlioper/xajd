package xsgzgl.pjpy.general.wdpj.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;
import xsgzgl.xtwh.wdgz.Job;
import xsgzgl.xtwh.wdgz.MyJobsManager;
import xsgzgl.xtwh.wdgz.impl.MyJobsImpl;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_项目审核_通用_Action类
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

public class WdpjXmshAction extends BasicAction {
	
	/**
	 * 查询项目审核结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchWdpjXmsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXmshModel model = new WdpjXmshModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXmsh(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		String xmdm = otherValue[2];
		
		String spgw = otherValue[3];
		
		model.setXmdm(xmdm);
		model.setSpgw(spgw);
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
		List<HashMap<String, String>> topTr = service.getWdpjXmshTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWdpjXmshList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWdpjXmshHTML(rsModel, model, rsArrList,
				user);
		// 构建学校个性化信息隐藏域
		spHtml+=service.createKidneyDiv(rsModel,rForm, model, rsArrList, user);
		
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		
		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	//====================old=================================	
	
	/**
	 * 查询项目审核结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjpyXmsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyWdpjInit init = new PjpyWdpjInit();
		WdpjXmshModel model = new WdpjXmshModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXmsh(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		
		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);
		
		// 项目代码
		String xmdm = otherValue[1];
		model.setXmdm(xmdm);
		myForm.setXmdm(xmdm);
		
		// 项目名称
		String xmmc = otherValue[2];
		
		// stylePath
		String stylePath=otherValue[3];
		rsModel.setStylePath(stylePath);
		
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
		List<HashMap<String, String>> topTr = service.getWdpjXmshTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getWdpjXmshList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createWdpjXmshHTML(rsModel, model, rsArrList,
				user);
		// 构建学校个性化信息隐藏域
		spHtml+=service.createKidneyDiv(rsModel,rForm, model, rsArrList, user);
		
		
		// ==================显示内容 end========================

		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================构建前台页面 end========================

		return null;
	}
	
	/**
	 * 批量审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePlShzt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		WdpjXmshModel model=new WdpjXmshModel();
		PjpyWdpjModel pjtjModel=new PjpyWdpjModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);
		

		myForm.setSearchModel(searchModel);
		// ============= end ============

		String message="";
		
		commService.getModelValue(model, request);
		
		pjtjModel.setXmdm(model.getXmdm());
		pjtjModel.setSqr(model.getXh());
		pjtjModel.setGwid(model.getSpgw());
	
		if("tg".equalsIgnoreCase(model.getShzt())){
			message=pjtjService.checkShzg(pjtjModel, user);
		}
		
		boolean flag=false;
		
		if(Base.isNull(message)){
			flag = service.updateShzt(model,request, user);
			message = flag ? "保存成功" : "保存失败，请联系相关人员";
			
			if (flag){
				//===待办工作=====2013-1-15 qph==beign======
				PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
				String[] id = pjtjModel.getSqr();
				String[] spgw = pjxmService.getSpgwByXmdm(model.getXmdm());
				int index = StringUtils.getStrIndexInArray(model.getSpgw(), spgw);
				
				for (String str : id){
					Job job = null;
					String pkValue = str+"!!@@!!"+model.getXmdm()+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd();
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"评奖评优");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"评奖评优");
					} else {
						job = Job.getJobInstance(pkValue,"评奖评优");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				
				//===待办工作=====2013-1-15 qph==end=========
			}
			
			
		}
		
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 弹出审核岗位选择DIV
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showShgwDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		WdpjXmshModel model=new WdpjXmshModel();
		
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		//init.initSaveXssq(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		
		String xmdm = request.getParameter("xmdm");
		
		model.setXmdm(xmdm);

		//消息信息
		String message="";
		
		//保存数据方法
		
		if(Base.isNull(message)){
			
			service.showShgwDiv(model, user, response);
			
		}

		return null;
	}
	
	/**
	 * 批量审核保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveShzt(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommService commService=new CommService();
		WdpjXmshModel model=new WdpjXmshModel();
		PjpyWdpjModel pjtjModel=new PjpyWdpjModel();
		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmService pjxmService = new PjszPjxmService();
		PjpyZhcpInit init = new PjpyZhcpInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchModel searchModel = new SearchModel();
		
		// ============= 初始化各变量的值 ============
		init.initZhcpMaintain(rForm, myForm, user, request);
		WdpjXmshInterface service = myService.getWdpjXmshService(myForm);
		WdpjPjtjInterface pjtjService = myService.getWdpjPjtjService(myForm);

		myForm.setSearchModel(searchModel);
		// ============= end ============

		// ============= 保存参评小组设置（未勾选） ============
		String message="";
		
		commService.getModelValue(model, request);
		
		pjtjModel.setXmdm(model.getXmdm());
		pjtjModel.setSqr(model.getXh());
		pjtjModel.setGwid(model.getSpgw());
	
		boolean flag = false;
		if("tg".equalsIgnoreCase(model.getShzt())){
			message=pjtjService.checkShzg(pjtjModel, user);
		}
		
		if(Base.isNull(message)){
			flag = service.updateShzt(model,request, user);
			message = flag ? "审核成功" : "审核失败，请联系相关人员";
			
			if (flag){
				//===待办工作=====2013-1-15 qph==beign======
				PjpyGeneralForm jbszModel=PjpyGeneralForm.getJbszModel();
				String[] id = model.getXh();
				String[] spgw = pjxmService.getSpgwByXmdm(model.getXmdm());
				int index = StringUtils.getStrIndexInArray(model.getSpgw(), spgw);
				
				for (String str : id){
					Job job = null;
					String pkValue = str+"!!@@!!"+model.getXmdm()+"!!@@!!"+jbszModel.getPjxn()+"!!@@!!"+jbszModel.getPjxq()+"!!@@!!"+jbszModel.getPjnd();
					if ("tg".equals(model.getShzt())) {
						String nextShgw = index!=spgw.length-1 && spgw[index+1] != null ? spgw[index+1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"评奖评优");
					} else if ("th".equals(model.getShzt())){
						String nextShgw = index!=0 ? spgw[index-1] : null;
						job = Job.getJobInstance(pkValue,nextShgw,
								"general_pjpy.do?method=xmshManage&xmdm="+model.getXmdm()+"&spgw="+nextShgw,"评奖评优");
					} else {
						job = Job.getJobInstance(pkValue,"评奖评优");
					}
					MyJobsManager manager = new MyJobsImpl();
					manager.updateJob(job);
				}
				//===待办工作=====2013-1-15 qph==end=========
			}
			
			
		}
		// ============= end ============

		response.setContentType("text/html;charset=gbk");
//
		response.getWriter().print(message);

		return null;
	}
	
}
