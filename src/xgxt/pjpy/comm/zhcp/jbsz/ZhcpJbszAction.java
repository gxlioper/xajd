package xgxt.pjpy.comm.zhcp.jbsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 综合素质测评_基本设置_Action类
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

public class ZhcpJbszAction extends BasicAction {

	/**
	 * 综合测评_基本设置_综测基本设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm=(ZhcpJbszForm)form;
		ZhcpJbszInit init = new ZhcpJbszInit();
		ZhcpJbszService service=new ZhcpJbszService();
		User user = getUser(request);// 用户对象
		String message = "";// 提示信息
		RequestForm rForm = new RequestForm();
		String doType=request.getParameter("doType");
		
		// ==============综测周期========================
		myForm.setPjxn(ZhcpJbszForm.zcjbszModel.getPjxn());
		myForm.setPjxq(ZhcpJbszForm.zcjbszModel.getPjxq());
		myForm.setPjnd(ZhcpJbszForm.zcjbszModel.getPjnd());
		// ==============综测周期 end========================
		
		// ==============综测周期========================
		myForm.setUpXn(ZhcpJbszForm.zcjbszModel.getUpXn());
		myForm.setUpXq(ZhcpJbszForm.zcjbszModel.getUpXq());
		myForm.setUpNd(ZhcpJbszForm.zcjbszModel.getUpNd());
		// ==============综测周期 end========================
		
		
		//综测信息保存(综测名称保存、综测比例保存)
		if("save".equalsIgnoreCase(doType)){
			//综测名称保存
			boolean flag=service.upateZcxmmc(myForm, user);
			if(flag){
				//综测比例保存
				flag=service.saveZcbl(myForm, user);
			}
			message = flag ? "综测项目比例修改成功!"
					: "综测项目比例修改失败!";
			rForm.setMessage(message);
			request.setAttribute("updateResult", flag);
			myForm.setXmjb(null);
		}
		
		if("init".equalsIgnoreCase(doType)){
			boolean flag=service.zhcpIni(myForm, user);
			message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
					: MessageInfo.MESSAGE_INIT_FALSE;
			rForm.setMessage(message);
			request.setAttribute("initResult", flag);
			myForm.setXmjb(null);
		}
		
		if("copyXm".equalsIgnoreCase(doType)){
			//false说明不存在上一周期综测项目
			boolean flag=service.copyZcxm(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			myForm.setXmjb(null);
			
			request.setAttribute("copyResult", flag);
		}
		
		
		// ============= 初始化各变量的值 ============
		
		init.getDypfszRForm(rForm, myForm, request);
		// ==============初始化各变量的值end =============
		
		// 综测项目列表
		List<HashMap<String,String>>xmlist=service.getZctreeList(myForm);
		// 综测上级代码(distinct获得)
		request.setAttribute("sjdmList", service.getSjdmList(myForm));
		// 综测详细信息
		request.setAttribute("zcxmxxList", service.getZcxmxxList(myForm));
		
		
		// ============= 设置request的值 =============
		request.setAttribute("xmList",xmlist);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		return mapping.findForward("zcjbsz");
	}

	/**
	 * 综合测评_基本设置_德育评分设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dypfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm = (ZhcpJbszForm) form;
		ZhcpJbszService service = new ZhcpJbszService();
		ZhcpJbszInit init = new ZhcpJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getDypfszRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String userStatus = user.getUserStatus();// 用户身份
		String message = "";// 提示信息
		// =================== end ===================

		// ============= 非辅导员（班主任）用户不可操作 ============
		if (!"fdy".equalsIgnoreCase(userStatus)
				&& !"bzr".equalsIgnoreCase(userStatus)
				&& !"jd".equalsIgnoreCase(userStatus)) {
			message = "本模块只能由辅导员（班主任）级用户进行操作，请确认！";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================
		
		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveDypfKgzt(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		HashMap<String, Object> rs = service.getDypfInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		//service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("dypfsz");
	}
	
	/**
	 * 综合测评_基本设置_综测加分设置
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm = (ZhcpJbszForm) form;
		ZhcpJbszService service = new ZhcpJbszService();
		ZhcpJbszInit init = new ZhcpJbszInit();
		User user = getUser(request);// 用户对象

		// ============= 初始化各变量的值 ============
		RequestForm rForm = new RequestForm();
		init.getZcjfszRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // 操作类型（保存，删除等）
		String message = "";// 提示信息
		// =================== end ===================
		
		// ============= 执行保存操作 ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjfsz(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== 初始化页面显示值 ===========
		List<HashMap<String, Object>> rsList = service.getZcjfInfoList(myForm, user);
		request.setAttribute("rsList", rsList);
		// ================= end =====================

		// =================== 初始化列表值 ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= 设置request的值 =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfsz");
	}
	
	
	
	/**
	 * 清除当前周期综测项目
	 */
	public ActionForward removePjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhcpJbszService service = new ZhcpJbszService();
		
		boolean result = service.removeZcxm();
		request.setAttribute("removeResult", result ? "清除成功!" : "清除失败!");
		return zcjbsz(mapping, form, request, response);
	}
}

