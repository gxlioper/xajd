package xsgzgl.jygl.general;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.FormModleCommon;
import xsgzgl.jygl.general.sxjy.JyglSxjyInit;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.index.PjpyIndexInit;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlInit;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzInit;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryInit;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmInit;
import xsgzgl.pjpy.general.pjsz.zcxm.PjszZcxmInit;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjInit;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjInit;
import xsgzgl.pjpy.general.xmsz.rssz.XmszRsszInit;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpInit;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_通用_Action类
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

public class JyglGeneralAction extends BasicAction {

	/**
	 * 【实习就业 - 管理】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sxjyManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService service = new JyglGeneralService();
		RequestForm rForm = new RequestForm();
		JyglSxjyInit init = new JyglSxjyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSxjyManage(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/jygl/" + xxpymc + "/sxjy/sxjyManage.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	/**
	 * 【实习就业 - 维护】
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward sxjyUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JyglGeneralForm myForm = (JyglGeneralForm) form;
		JyglGeneralService service = new JyglGeneralService();
		RequestForm rForm = new RequestForm();
		JyglSxjyInit init = new JyglSxjyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 begin ============
		init.initSxjyUpdate(rForm, myForm, user, request);

		// 学校拼音名称
		String xxpymc = myForm.getXxpymc();
		// 跳转路径
		String url = "/xsgzgl/jygl/" + xxpymc + "/sxjy/sxjyUpdate.jsp";
		// ============= 初始化各变量的值 end ==============

		// ============= 设置request的值 =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
}
