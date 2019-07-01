package xsgzgl.xtwh.general.flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系統維護_审核流_通用_Action类
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

public class XtwhFlowAction extends BasicAction {

	/**
	 * 创建用户组Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// 用户对象
		String yhszlx = request.getParameter("yhszlx");
		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================构建前台页面====================
		service.createYhzDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建可选用户Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createKxyhDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// 用户对象
		String yhszlx = request.getParameter("yhszlx");
		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================构建前台页面====================
		service.createKxyhDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建已选用户Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYxyhDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// 用户对象
		String yhszlx = request.getParameter("yhszlx");
		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================构建前台页面====================
		service.createYxyhDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存已选用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveYxyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// 用户对象
		String yhszlx = request.getParameter("yhszlx");
		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================构建前台页面====================
		// ========== 个性化 用户授权 begin ============
		if("rcxwwh".equals(yhszlx)){
			// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
			service.saveYxyhRcxwwh(model, user);
		}else{
			// 系统维护-审批流程维护-审批流程
			service.saveYxyh(model, user);
		}
		// ========== 个性化 用户授权 end ============
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 删除已选用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteYxyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// 用户对象
		String yhszlx = request.getParameter("yhszlx");
		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================构建前台页面====================
		// ========== 个性化 用户授权 begin ============
		if("rcxwwh".equals(yhszlx)){
			// 日常事务NEW-日常行为维护NEW-日常行为代码维护-日常行为类别
			service.deleteYxyhRcxwwh(model, user);
		}else{
			// 系统维护-审批流程维护-审批流程
			service.deleteYxyh(model, user);
		}
		// ========== 个性化 用户授权 end ============
		// ==================构建前台页面 end================

		return null;
	}
}
