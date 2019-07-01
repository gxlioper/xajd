package xsgzgl.rcsw.qjgl.bjfxdj;

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

public class BjfxdjAction extends BasicAction {

	/**
	 * 病假返校登记首页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjfxdjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjfxdjService service = new BjfxdjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("rcsw_qjgl_bjfxdj.do");
		// ----------------设置PATH end-----------------------
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "view_xg_rcsw_qjgl_fxdjb");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjfxdjCx");
	}
	
	/**
	 * 病假返校登记单条记录查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjfxdjCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjfxdjService service = new BjfxdjService();
		BjfxdjForm myForm = (BjfxdjForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// 单条记录信息
		HashMap<String, String> rs = service.getCkfxMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "rcsw_qjgl_bjfxdj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjfxdjCk");
	}
	
	/**
	 * 病假返校登记信息登记
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjfxdjDj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjfxdjService service = new BjfxdjService();
		BjfxdjForm myForm = (BjfxdjForm) form;
		String str = request.getParameter("str");
		String sffx = service.unicode2Gbk(request.getParameter("sffx"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String message = "";
		boolean flag = false;
		User user = getUser(request);
		String username = user.getRealName();
		flag = service.bjfxdjDj(myForm, str, sffx, bz ,username);
		if (Base.isNull(message)) {
			message = flag ? "登记成功" : "登记失败";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 病假返校登记信息取消
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjfxdjQx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BjfxdjService service = new BjfxdjService();
		BjfxdjForm myForm = (BjfxdjForm) form;
		String str = request.getParameter("str");
		String message = "";
		boolean flag = false;
		flag = service.bjfxdjQx(myForm, str);
		if (Base.isNull(message)) {
			message = flag ? "取消成功" : "取消失败";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}