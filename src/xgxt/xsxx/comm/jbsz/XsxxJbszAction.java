package xgxt.xsxx.comm.jbsz;

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
 * Description: 学生信息_基本设置-action类
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

public class XsxxJbszAction extends BasicAction {

	/**
	 * 学生信息_基本设置_系统字段设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xtzdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXtzdszRForm(rForm, myForm, request);

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
			
			//检测所保存的字段是否被允许（已分配字段不能设置为不启用）
			String message = service.checkSaveZdsz(myForm);

			if (Base.isNull(message)) {
			
				boolean flag = service.saveZdsz(myForm, user, request);
				message = flag ? MessageInfo.MESSAGE_CONFIRM
						: MessageInfo.MESSAGE_SAVE_FALSE;
				
				if(flag){
					service.createNewView(myForm, user);
				}
			}
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 执行创建视图操作 ===============
		if ("create".equalsIgnoreCase(doType)) {
			boolean flag = service.createNewView(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			message+="\n（注：可以在plsql等工具，中查\n看视图xg_view_xsxxb的结构）";
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getZdszRsList(myForm, user, colList);
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

		return mapping.findForward("xtzdsz");
	}

	/**
	 * 学生信息_基本设置_页面基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ymjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getYmjbszRForm(rForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		// ================= end =====================

		// =============== 执行保存操作 ===============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveYmsz(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============获得已设置完成的值 ============
		// 启用字段列表
		List<HashMap<String, String>> qyzdList = service.getQyzdList(myForm);
		request.setAttribute("qyzdList", qyzdList);

		// 最大的显示区代码
		String maxXsqdm = service.getMaxXsqdm();
		request.setAttribute("maxXsqdm", maxXsqdm);

		// 已配置好的显示区
		List<HashMap<String, Object>> xsqList = service.getXsqInfoList(myForm);
		request.setAttribute("xsqList", xsqList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("ymjbsz");
	}

	/**
	 * 学生信息_基本设置_显示区设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getXsqszRForm(rForm, myForm, request);

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
			boolean flag = service.saveXsqsz(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== 执行查询操作 ===========
		if (search) {
			// 结果集
			rsArrList = service.getXsqRsList(myForm, user, colList);
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

		return mapping.findForward("xsqsz");
	}
}
