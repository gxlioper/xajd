package xsgzgl.xsxx.cssz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_个人信息_Action类
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

public class XsxxCsszAction extends BasicAction {

	/**
	 * 学生信息_参数设置_个人信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward csszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxCsszForm myForm = (XsxxCsszForm) form;
		XsxxCsszInit init = new XsxxCsszInit();
		XsxxCsszInterface service = init.getCsszService("grxx");//个人信息
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象
		SearchRsModel rsModel = new SearchRsModel();

		// ============= 初始化各变量的值 ============
		// 初始化
		init.initGrxx(rForm, myForm, request);
		HashMap<String, String> rs = service.getCsszInfo(myForm);
		// =================== end ===================

		// ============= 设置request的值 =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("csszManage");
	}
}
