package xsgzgl.rcsw.qjgl.cxqj;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-17 下午13:13:22
 * </p>
 */

public class CxqjAction extends BasicAction {

	/**
	 * 撤销请假首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxqjService service = new CxqjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("rcsw_qjgl_cxqj.do");
		// ----------------设置PATH end-----------------------
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_rcsw_qjgl_qjcxb");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("cxqjCx");
	}
	
	/**
	 * 撤销请假单条记录查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqjCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxqjService service = new CxqjService();
		CxqjForm myForm = (CxqjForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// 单条记录信息
		HashMap<String, String> rs = service.getCkqjMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "rcsw_qjgl_cxqj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxqjCk");
	}
	
	/**
	 * 撤销请假信息撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqjChx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxqjService service = new CxqjService();
		CxqjForm myForm = (CxqjForm) form;
		String str = request.getParameter("str");
		String cxyy = service.unicode2Gbk(request.getParameter("cxyy"));
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String username = user.getRealName();
		flag = service.cxqjChx(myForm, str, cxyy ,username);
		if (Base.isNull(message)) {
			message = flag ? "撤销成功" : "撤销失败";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 撤销请假信息取消撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cxqjQx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CxqjService service = new CxqjService();
		CxqjForm myForm = (CxqjForm) form;
		String str = request.getParameter("str");
		String message = "";
		boolean flag = false;
		flag = service.cxqjQx(myForm, str);
		if (Base.isNull(message)) {
			message = flag ? "取消成功" : "取消失败";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}