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

public class BjfxdjAction extends BasicAction {

	/**
	 * ���ٷ�У�Ǽ���ҳ��ѯ
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
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("rcsw_qjgl_bjfxdj.do");
		// ----------------����PATH end-----------------------
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_rcsw_qjgl_fxdjb");
		service.setRequestValue(rForm, user, request);
		return mapping.findForward("bjfxdjCx");
	}
	
	/**
	 * ���ٷ�У�Ǽǵ�����¼�鿴
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
		// ������¼��Ϣ
		HashMap<String, String> rs = service.getCkfxMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("path", "rcsw_qjgl_bjfxdj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bjfxdjCk");
	}
	
	/**
	 * ���ٷ�У�Ǽ���Ϣ�Ǽ�
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
			message = flag ? "�Ǽǳɹ�" : "�Ǽ�ʧ��";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * ���ٷ�У�Ǽ���Ϣȡ��
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
			message = flag ? "ȡ���ɹ�" : "ȡ��ʧ��";
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
}