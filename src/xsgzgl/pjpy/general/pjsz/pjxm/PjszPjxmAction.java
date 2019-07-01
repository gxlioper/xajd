package xsgzgl.pjpy.general.pjsz.pjxm;

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
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.pjsz.PjszPjxmInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖项目_通用_Action类
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

public class PjszPjxmAction extends BasicAction {
	
	/**
	 * 查询评奖设置（评奖项目设置）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszPjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszPjxmTop(model,
				user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getPjszPjxmList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createPjszPjxmHTML(rsModel, model, rsArrList,
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
	 * 初始化评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjxmSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//操作步骤
		String step = request.getParameter("step");
		model.setStep(step);
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultPjxmSetting(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 初始化审核流程岗位信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultShlcGwxx(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmService service = new PjszPjxmService();
		
		// 操作步骤
		String lcid = request.getParameter("lcid");
		model.setLcid(lcid);

		// 人数设置
		String rssz = request.getParameter("rssz");
		model.setRssz(rssz);
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultShlcGwxx(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 保存评奖项目
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.savePjxm(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存评奖项目
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updatePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存评奖项目 ============
		boolean flag = service.updatePjxm(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 初始化评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getPjxmInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);

		// 项目代码代码
		String xmdm = request.getParameter("xmdm");
		// =================== end ===================

		// ==================获得评奖项目信息====================
		HashMap<String, String> map = service.getPjxmMap(xmdm, user);
		// 人数设置
		String rssz = map.get("rssz");
		// 控制范围
		String kzfw = map.get("kzfw");

		String message = rssz + "!!luojw!!" + kzfw + "!!luojw!!";
		// ==================获得评奖项目信息 end================
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 检测项目名称
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkXmmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 检测项目名称 ============
		boolean flag = service.checkXmmc(model);
		String message = flag ? "" : "fail";
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 初始化评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultPjxmUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//操作步骤
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultPjxmUpdate(model, user, response);
		
		
		// ==================构建前台页面 end================

		return null;
	}
	

	/**
	 * 初始化评奖项目设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePjxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjxmInit init = new PjszPjxmInit();
		PjszPjxmModel model = new PjszPjxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initPjxm(rForm, myForm, user, request);
		PjszPjxmInterface service = myService.getPjszPjxmService(myForm);
		
		//操作步骤
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// =================== end ===================

		// ==================构建前台页面====================
		myService.getModelValue(model, request);
		
		String message=service.checkDelete(model, user);
		boolean flag=false;
		
		if(Base.isNull(message)){
			
			flag=service.deletePjxm(model, user);

			message = flag ? "删除成功" : "删除失败，请联系相关人员";
			
		}
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		// ==================构建前台页面 end================

		return null;
	}
}
