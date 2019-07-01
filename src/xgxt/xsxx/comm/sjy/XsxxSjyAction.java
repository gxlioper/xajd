package xgxt.xsxx.comm.sjy;

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
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszInit;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源-action类
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

public class XsxxSjyAction extends BasicAction {

	/** 
	 * 功能模块选择
	 * Method xsxxwh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward jcsjszChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("jcsjszChoose");
	}
	
	/**
	 * 学生信息_数据源_基础数据设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxSjyForm myForm = (XsxxSjyForm) form;
		XsxxSjyService service = new XsxxSjyService();
		XsxxSjyInit init = new XsxxSjyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// 是否查询操作
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// 结果集显示字段
		String[] colList = rForm.getColList();
		// 结果列表
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {
			
//			//检测所保存的字段是否被允许（已分配字段不能设置为不启用）
//			String message = service.checkSaveZdsz(myForm);
//
//			if (Base.isNull(message)) {
//			
//				boolean flag = service.saveZdsz(myForm, user, request);
//				message = flag ? MessageInfo.MESSAGE_CONFIRM
//						: MessageInfo.MESSAGE_SAVE_FALSE;
//				
//				if(flag){
//					service.createNewView(myForm, user);
//				}
//			}
//			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 执行创建视图操作 ===============
		if ("create".equalsIgnoreCase(doType)) {
//			boolean flag = service.createNewView(myForm, user);
//			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
//					: MessageInfo.MESSAGE_CREATE_FALSE;
//			message+="\n（注：可以在plsql等工具，中查\n看视图xg_view_xsxxb的结构）";
//			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			//rsArrList = service.getZdszRsList(myForm, user, colList);
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszManage");
	}
	
	/**
	 * 学生信息_数据源_基础数据设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszGuide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxSjyForm myForm = (XsxxSjyForm) form;
		XsxxSjyService service = new XsxxSjyService();
		XsxxSjyInit init = new XsxxSjyInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// 操作类型（保存，删除等）
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {

		}
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszGuide");
	}
}
