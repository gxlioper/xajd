package xsgzgl.xtwh.general.power;

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
 * Description: 系統維護_权限_通用_Action类
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

public class XtwhPowerAction extends BasicAction {

	/**
	 * 创建子系统一级菜单列表Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createFirstGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createFirstGnmkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 创建子系统二级菜单列表Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSecondGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createSecondGnmkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 创建用户组菜单列表Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhzGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createYhzGnmkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 创建用户菜单列表Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createYhGnmkDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 创建用户Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createUserDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================构建前台页面====================
		service.createUserDiv(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
}
