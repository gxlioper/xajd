package xgxt.pjpy.comm.zhcp.zcjf;

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
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_综测加分_Action类
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

public class ZhcpZcjfAction extends BasicAction {

	/**
	 * 综合测评_基本设置_综测加分申请
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getZcjfsqRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		String xh = request.getParameter("xh");// 学号
		myForm.setXh(xh);
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "sq", request);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, Object> rs = service.getZcjfSqInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfsq");
	}

	/**
	 * 综合测评_基本设置_综测加分审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		List<HashMap<String, String>> xmList = service.getXmList();// 项目列表
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getZcjfshRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		String[] colList = rForm.getColList();// 结果集显示字段
		// =================== end ===================

		// ============= 非辅导员（班主任）用户不可操作 ============
		if (!"fdy".equalsIgnoreCase(userStatus)
				&& !"bzr".equalsIgnoreCase(userStatus)
				&& !"jd".equalsIgnoreCase(userStatus)) {
			message = "本功能只能由辅导员（班主任）级用户进行操作，请确认！";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================

		// ============= 执行提交操作 ============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "pltj", request);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		ArrayList<String[]> rsArrList = service.getJfshList(myForm, user,
				xmList);
		rForm.setRsArrList(rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// ================= end =====================

		// =================== 初始化列表值 ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zcjfsh");
	}

	/**
	 * 综合测评_基本设置_综测加分审核(详细)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getZcjfsqRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		String xh = request.getParameter("pk");// 学号
		if (Base.isNull(myForm.getXh())) {
			myForm.setXh(xh);
		}
		// =================== end ===================

		// ============= 学生用户监督的情况 ============
		if ("stu".equalsIgnoreCase(userType) && "jg".equalsIgnoreCase(doType)) {
			request.setAttribute("mklx", "jg");
		}
		// =================== end ===================

		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "sh", request);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// ============= 执行提交操作 ============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "tj", request);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, Object> rs = service.getZcjfSqInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfshDetail");
	}

	/**
	 * 综合测评_基本设置_综测加分查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		List<HashMap<String, String>> xmList = service.getXmList();// 项目列表
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getZcjfcxRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userType = user.getUserType();// 用户类型
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		String[] colList = rForm.getColList();// 结果集显示字段
		// =================== end ===================

		// =============== 导出Excel ===========
		if ("print".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.printZcjfsq(myForm, rForm, user, xmList, response
					.getOutputStream());
		}
		// ================= end =====================

		// =============== 初始化页面显示值 ===========
		ArrayList<String[]> rsArrList = service.getJfshList(myForm, user,
				xmList);
		rForm.setRsArrList(rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zcjfcx");
	}
	
	/**
	 * 判断审核分是否为空
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfService service = new ZhcpZcjfService();
		
		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		
		String xh=request.getParameter("xh");

		String message="";
		
		boolean blog=service.checkShfs(myForm, xh);
		
		message = blog ? "true"
				: "该学生综测加分尚未录入审核分，如进行提交操作审核分将以零分处理，是否确定操作?";
	
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	

	/**
	 * 判断审核分是否修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShfIsModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfService service = new ZhcpZcjfService();
		
		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		
		String xh=request.getParameter("xh");
		
		HashMap<String,Object>map=service.getValueMapByObj(request, null);
		String message="";
		
		map.put("xh", xh);
		
		boolean blog=service.checkShfIsModi(myForm,map);
		
		message = blog ? "true"
				: "该学生综测加分已做修改，但尚未保存，如进行提交操作修改记录将已原有信息为准，是否确定继续操作?";
	
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
}
