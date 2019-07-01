package xsgzgl.pjpy.general.pjsz.zcxm;

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
import xsgzgl.pjpy.general.inter.pjsz.PjszZcxmInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_综测项目_通用_Action类
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

public class PjszZcxmAction extends BasicAction {
	
	/**
	 * 保存综测项目(下一级别)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveNextZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// 上级代码
		String sjdm = request.getParameter("sjdm");
		model.setSjdm(sjdm);

		//项目名称
		String xmmc = request.getParameter("xmmc");
		if (!Base.isNull(xmmc)) {
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		
		// 加减分
		String jjf = request.getParameter("jjf");
		model.setJjf(jjf);

		// 录入理由
		String lrly = request.getParameter("lrly");
		model.setLrly(lrly);
		
		// 比例代码
		String bldm = request.getParameter("bldm");
		if (!Base.isNull(bldm)) {
			model.setBldm(bldm.split("!!@@!!"));
		}
		
		// 比例
		String bl = request.getParameter("bl");
		if (!Base.isNull(bl)) {
			model.setBl(bl.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存综测项目 ============
		boolean flag = service.saveZcxm(model, "next", user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 保存综测项目(修改本级别)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveEditZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// 项目代码
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);

		// 项目名称
		String xmmc = request.getParameter("xmmc");
		if (!Base.isNull(xmmc)) {
			model.setXmmc(myService.unicode2Gbk(xmmc));
		}
		
		// 加减分
		String jjf = request.getParameter("jjf");
		model.setJjf(jjf);

		// 录入理由
		String lrly = request.getParameter("lrly");
		model.setLrly(lrly);
		
		// 比例代码
		String bldm = request.getParameter("bldm");
		if (!Base.isNull(bldm)) {
			model.setBldm(bldm.split("!!@@!!"));
		}
		
		// 比例
		String bl = request.getParameter("bl");
		if (!Base.isNull(bl)) {
			model.setBl(bl.split("!!@@!!"));
		}
		// ============= end ============

		// ============= 保存综测项目 ============
		boolean flag = service.updateZcxm(model, user);
		String message = flag ? "保存成功" : "保存失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * 删除综测项目
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteZcxm(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		PjszZcxmInit init = new PjszZcxmInit();
		PjszZcxmModel model = new PjszZcxmModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		
		// ============= 初始化各变量的值 ============
		init.initZcxm(rForm, myForm, user, request);
		PjszZcxmInterface service = myService.getPjszZcxmService(myForm);

		// 综测项目
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		// ============= end ============

		// ============= 删除综测项目 ============
		boolean flag = service.deleteZcxm(model, user);
		String message = flag ? "删除成功" : "删除失败，请联系相关人员";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
