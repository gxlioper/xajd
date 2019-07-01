package xsgzgl.pjpy.general.index;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyIndexInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_首页_通用_Action类
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

public class PjpyIndexAction extends BasicAction {

	/**
	 * 初始化已定制评奖流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultCustomPjlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();
		PjpyIndexModel model = new PjpyIndexModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		//样式地址
		model.setStylePath(rForm.getStylePath());
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultCustomPjlc(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 初始化自由流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultFreePjlc(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);
		// =================== end ===================

		// ==================构建前台页面====================
		service.defaultFreePjlc(model, user, response);
		// ==================构建前台页面 end================

		return null;
	}

	/**
	 * 保存评奖流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward savePjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		// 最大评奖流程
		String maxPjlc = request.getParameter("maxPjlc");
		model.setMaxPjlc(maxPjlc);

		// 流程代码
		String lcdm = request.getParameter("lcdm");
		if (!Base.isNull(lcdm)) {
			model.setLcdm(lcdm.split("!!@@!!"));
		}

		// 流程等级
		String lcdj = request.getParameter("lcdj");
		if (!Base.isNull(lcdj)) {
			model.setLcdj(lcdj.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.savePjlc(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 保存开始新评奖
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexModel model = new PjpyIndexModel();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		String pjzq = request.getParameter("pjzq");// 评奖周期
		model.setPjzq(pjzq);

		String pjxn = request.getParameter("pjxn");// 评奖学年
		model.setPjxn(pjxn);

		String pjxq = request.getParameter("pjxq");// 评奖学期
		pjxq = Base.isNull(pjxq) ? "no" : pjxq;
		model.setPjxq(pjxq);

		String cpz = request.getParameter("cpz");// 参评组
		model.setCpz(cpz);

		String zcpm = request.getParameter("zcpm");// 综测排名
		model.setZcpm(zcpm);

		String zypm = request.getParameter("zypm");// 智育排名
		model.setZypm(zypm);
		
		String start = request.getParameter("start");// 是否开始新评奖
		model.setStart(start);
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.saveStart(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * 提交评奖流程
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submitPjlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		String lcdj = request.getParameter("lcdj");// 流程等级
		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag = service.submitPjlc(lcdj, user);
		String message = flag ? "提交成功" : "提交失败，请联系相关人员";
		if ("3".equalsIgnoreCase(lcdj) && flag) {// 第四步
			StringBuilder msg = new StringBuilder();
			msg.append("提交成功!<br/>");
			msg.append("注：如果您调整了<font color='blue'>评奖人员库</font>或者");
			msg.append("<font color='blue'>综测项目</font>的话，");
			msg.append("请在<font color='blue'>综合分结果</font>处重新执行计算。");
			message = msg.toString();
		} else if ("4".equalsIgnoreCase(lcdj) && flag) {// 第五步
			StringBuilder msg = new StringBuilder();
			msg.append("提交成功!<br/>");
			msg.append("注：如果评奖人员的综合分未进行计算的话，");
			msg.append("系统已经自动帮您进行了计算操作，");
			msg.append("请在<font color='blue'>综合分结果</font>中确认相关分数。");
			message = msg.toString();
		}
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 将评奖数据转入历史库
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dataToHis(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyIndexInit init = new PjpyIndexInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		init.initIndex(rForm, myForm, user, request);
		PjpyIndexInterface service = myService.getPjpyIndexService(myForm);

		// ============= end ============

		// ============= 保存功能模块 ============
		boolean flag=true;
		service.theEnd(user);
		String message = flag ? "提交成功" : "提交失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

}
