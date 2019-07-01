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
 * Title: ѧ����������ϵͳ
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
 * Time:2012-7-17 ����13:13:22
 * </p>
 */

public class CxqjAction extends BasicAction {

	/**
	 * ���������ҳ��ѯ
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
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("rcsw_qjgl_cxqj.do");
		// ----------------����PATH end-----------------------
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_rcsw_qjgl_qjcxb");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("cxqjCx");
	}
	
	/**
	 * ������ٵ�����¼�鿴
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
		// ������¼��Ϣ
		HashMap<String, String> rs = service.getCkqjMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "rcsw_qjgl_cxqj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxqjCk");
	}
	
	/**
	 * ���������Ϣ����
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
			message = flag ? "�����ɹ�" : "����ʧ��";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ���������Ϣȡ������
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
			message = flag ? "ȡ���ɹ�" : "ȡ��ʧ��";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}