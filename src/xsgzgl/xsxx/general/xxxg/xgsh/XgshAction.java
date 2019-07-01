package xsgzgl.xsxx.general.xxxg.xgsh;

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
import xsgzgl.xsxx.general.inter.xxxg.XxxgXgshInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XgshAction extends BasicAction {

	/**
	 * 查询修改審核
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ActionForward searchXgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		XgshInit init = new XgshInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXgshSearch(rForm, myForm, user, request);
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);

		// 结果集显示字段
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE版本
		String ie = otherValue[0];
		rsModel.setIe(ie);

		// 样式地址
		String stylePath = otherValue[1];
		rsModel.setStylePath(stylePath);
		
		// 審批流程
		String splc = otherValue[2];
		model.setSplc(splc);
		
		// 崗位ID
		String gwid = otherValue[3];
		model.setGwid(gwid);
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
		List<HashMap<String, String>> topTr = service.getXgshTop(model, user);
		// 结果集
		ArrayList<String[]> rsArrList = service
				.getXgshList(myForm, model, user);
		// 构建结果集
		String spHtml = service.createXgshHTML(rsModel, model, rsArrList, user);
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
	 * 保存審核狀態
	 * 
	 * @date 2013-01-10
	 * @author 伟大的骆
	 */
	public ActionForward saveShzt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService myService = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin =======
		XxxgXgshInterface service = myService.getXxxgXgshService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行删除操作 begin ============
		boolean flag = service.saveShzt(model, user);
		String message = flag ? "执行成功" : "执行失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
