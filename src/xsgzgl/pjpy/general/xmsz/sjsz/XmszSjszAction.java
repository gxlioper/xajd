package xsgzgl.pjpy.general.xmsz.sjsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.xmsz.XmszSjszInterface;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_ʱ������_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XmszSjszAction extends BasicAction {

	/**
	 * ��ʼ����Ŀʱ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward defaultSjszSetting(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszSjszInit init = new XmszSjszInit();
		XmszSjszModel model = new XmszSjszModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============	
		//��Ŀ����
		String xmdm = request.getParameter("xmdm");
		model.setXmdm(xmdm);
		
		init.initSjsz(rForm, myForm, user, request);
		XmszSjszInterface service = myService.getXmszSjszService(myForm);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.defaultSjszSetting(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ����ʱ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveSjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyGeneralForm myForm = (PjpyGeneralForm) form;
		PjpyGeneralService myService = new PjpyGeneralService();
		XmszSjszInit init = new XmszSjszInit();
		XmszSjszModel model = new XmszSjszModel();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSjsz(rForm, myForm, user, request);
		XmszSjszInterface service = myService.getXmszSjszService(myForm);
		myService.getModelValue(model, request);
		// ============= end ============

		// ============= ����ȷ������ ============
		boolean flag = service.saveSjsz(model, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		request.setAttribute("message", message);
		// ============= end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
