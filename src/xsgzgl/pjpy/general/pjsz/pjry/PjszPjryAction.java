package xsgzgl.pjpy.general.pjsz.pjry;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszPjryInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖人员_通用_Action类
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

public class PjszPjryAction extends BasicAction {

	/**
	 * 查询评奖设置（评奖人员设置）结果
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward searchPjszPjry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);
		
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
		List<HashMap<String, String>> topTr = service.getPjszPjryTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service.getPjszPjryList(myForm, model,
				user);
		// 构建结果集
		String spHtml = service.createPjszPjryHTML(rsModel, model, rsArrList,
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
	 * 保存班级调整
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String bjdm = request.getParameter("bjdm");// 评奖班级代码
		model.setBjdm(bjdm);

		String bjmc = request.getParameter("bjmc");// 评奖班级名称
		if(!Base.isNull(bjmc)){
			model.setBjmc(myService.unicode2Gbk(bjmc));
		}

		String xh = request.getParameter("xh");// 学号
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存评奖班级调整 ============
		boolean flag = service.saveBjtz(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 撤销班级调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward disfrockBjtz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String xh = request.getParameter("xh");// 学号
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 撤销评奖班级调整 ============
		boolean flag = service.disfrockPjry(model, user);
		String message = flag ? "撤销成功" : "撤销失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存参评设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveCpsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszPjryInit init = new PjszPjryInit();
		PjszPjryModel model = new PjszPjryModel();
		
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initPjry(rForm, myForm, user, request);
		PjszPjryInterface service = myService.getPjszPjryService(myForm);

		String sfcp = request.getParameter("sfcp");// 评奖班级代码
		model.setSfcp(sfcp);

		String xh = request.getParameter("xh");// 学号
		if (xh != null && xh.length() > 0) {
			model.setXh(xh.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存参评设置 ============
		boolean flag = service.saveSfcp(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
