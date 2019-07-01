package xsgzgl.pjpy.general.tjcx.shmddc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.tjcx.TjcxShmddcInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_获奖名单汇总_通用_Action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 程强
 * @version 1.0
 */

public class TjcxShmddcAction extends BasicAction {
	
	/**
	 * 导出获奖名单汇总
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expShmddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		TjcxShmddcModel model = new TjcxShmddcModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		TjcxShmddcInterface service = myService.getTjcxShmddcService(myForm);
		myService.getModelValue(model, request);
		// =================== end ===================

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.expShmddc(model, response.getOutputStream());
		// ============= end ============

		return null;
	}
}
