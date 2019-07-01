package xsgzgl.gygl.rcjc.qszf;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
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
 * Time:2012-7-9 下午14:19:22
 * </p>
 */

public class QszfAction extends BasicAction {
	
	/**
	 * 寝室走访首页信息查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_rcjc_qszf.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- 导出表设置 ------------------------
		request.setAttribute("tableName", "VIEW_XG_GYGL_JHZY_BZRXQB");
		// ----------------- 导入表设置 ------------------------
		request.setAttribute("realTable", "XG_GYGL_JHZY_BZRXQB");
		return mapping.findForward("qszfCx");
	}

	/**
	 * 查询所有走访老师信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZfls(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_rcjc_qszf.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("zflsInfo");
	}

	/**
	 * 寝室走访-增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfZj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("gyglnew_rcjc_qszf.do");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("qszfZj");
	}

	/**
	 * 下寝次数-统计
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xqcsTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		rForm.setPath("gyglnew_rcjc_qszf.do");
		service.setRequestValue(rForm, user, request);
		FormModleCommon.setNdXnXqList(request);
		// 获取学院下拉列表框
		request.setAttribute("xyList", service.getXyList(request));
		return mapping.findForward("xqcsTj");
	}

	/**
	 * 寝室走访-删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfSc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		String message = "";
		boolean flag = false;
		QszfForm myForm = (QszfForm) form;
		User user = getUser(request);
		String username = user.getUserName();
		String[] valArr = service.unicode2Gbk(request.getParameter("str")).split("!!@@");
		flag = service.qszfSc(myForm, valArr, username);
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS : MessageInfo.MESSAGE_DEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 寝室走访-修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qszfXg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QszfService service = new QszfService();
		QszfForm qszfForm = (QszfForm) form;
		RequestForm rForm = new RequestForm();
		String pkValue = request.getParameter("pkValue");
		request.setAttribute("pkValue", pkValue);
		qszfForm.setGuid(pkValue);
		HashMap<String, String> qszfxx = service.getQszfMap(qszfForm);
		request.setAttribute("rs", qszfxx);
		User user = getUser(request);
		// ----------------设置PATH begin-----------------------
		// ----------------显示title，判断读写权----------------
		rForm.setPath("gyglnew_rcjc_qszf.do");
		// ----------------设置PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("qszfXg");
	}
}