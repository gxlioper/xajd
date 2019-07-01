package xsgzgl.pjpy.general.xmsz.xmjd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.xmsz.XmszXmjdInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目兼得_通用_Action类
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

public class XmszXmjdAction extends BasicAction {

	/**
	 * 初始化项目兼得设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultJdszSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmjdInit init = new XmszXmjdInit();
		XmszXmjdModel model = new XmszXmjdModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============	
		//项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		init.initXmjd(rForm, myForm, user, request);
		XmszXmjdInterface service = myService.getXmszXmjdService(myForm);
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultXmjdSetting(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}
	
	/**
	 * 保存项目兼得
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveXmjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmjdInit init = new XmszXmjdInit();
		XmszXmjdModel model = new XmszXmjdModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXmjd(rForm, myForm, user, request);
		XmszXmjdInterface service = myService.getXmszXmjdService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存项目兼得 ============
		boolean flag = service.saveXmjd(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除项目兼得
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteXmjd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszXmjdInit init = new XmszXmjdInit();
		XmszXmjdModel model = new XmszXmjdModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initXmjd(rForm, myForm, user, request);
		XmszXmjdInterface service = myService.getXmszXmjdService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= 保存确定人数 ============
		boolean flag = service.deleteXmjd(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
