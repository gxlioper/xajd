package xsgzgl.pjpy.general.djbg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.PjpyDjbgInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_通用_Action类
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

public class PjpyDjbgAction extends BasicAction {

	/**
	 * 打印登记表格
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printDjbg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		PjpyGeneralService myService = new PjpyGeneralService();
		PjpyDjbgInterface service = myService.getPjpyDjbgService(myForm);
		RequestForm rForm = new RequestForm();
		PjpyDjbgModel model = new PjpyDjbgModel();
		PjpyDjbgInit init = new PjpyDjbgInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============

		myService.getModelValue(model, request);

		// ---------------------本次评奖打印表 begin---------------------

		String xn = request.getParameter("str_xn");
		String xq = request.getParameter("str_xq");

		if (Base.isNull(xn)) {
			model.setXn(jbszModel.getPjxn());
		}

		if (Base.isNull(xq)) {
			model.setXq(jbszModel.getPjxq());
		}
		// ---------------------本次评奖打印表 end-----------------------

		init.init(rForm, myForm, model, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 登记表格
		String djbg = service.getDjbg(model);
		// 跳转路径
		String url = service.getPrintJspForward(model);

		if (Base.isNull(url)) {
			url = "/xsgzgl/pjpy/" + xxpymc + "/djbg/" + djbg + ".jsp";
		}
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm commModel = new CommForm();
		myService.setRequestValue(rForm, request);
		myService.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
}
