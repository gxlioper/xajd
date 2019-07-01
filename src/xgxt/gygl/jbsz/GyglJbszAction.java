package xgxt.gygl.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 公寓管理_基本设置-action类
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

public class GyglJbszAction extends BasicAction {

	/**
	 * 学生信息_基本设置_公寓流程显示
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gylcDisplay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("gylcDisplay");
	}
	
	/**
	 * 学生信息_基本设置_公寓基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gyjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		GyglJbszForm myForm = (GyglJbszForm) form;
		GyglJbszService service = new GyglJbszService();
		GyglJbszInit init = new GyglJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getGyjbszRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveGyjbsz(myForm, user, request);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setGyjbszInfo(myForm);
		service.setGyglOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("gyjbsz");
	}
}
