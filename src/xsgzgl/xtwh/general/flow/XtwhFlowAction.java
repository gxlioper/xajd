package xsgzgl.xtwh.general.flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�����_ͨ��_Action��
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

public class XtwhFlowAction extends BasicAction {

	/**
	 * �����û���Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhzDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// �û�����
		String yhszlx = request.getParameter("yhszlx");
		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================����ǰ̨ҳ��====================
		service.createYhzDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ������ѡ�û�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createKxyhDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// �û�����
		String yhszlx = request.getParameter("yhszlx");
		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================����ǰ̨ҳ��====================
		service.createKxyhDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ������ѡ�û�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYxyhDiv(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// �û�����
		String yhszlx = request.getParameter("yhszlx");
		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================����ǰ̨ҳ��====================
		service.createYxyhDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ������ѡ�û�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveYxyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// �û�����
		String yhszlx = request.getParameter("yhszlx");
		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================����ǰ̨ҳ��====================
		// ========== ���Ի� �û���Ȩ begin ============
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			service.saveYxyhRcxwwh(model, user);
		}else{
			// ϵͳά��-��������ά��-��������
			service.saveYxyh(model, user);
		}
		// ========== ���Ի� �û���Ȩ end ============
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * ɾ����ѡ�û�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteYxyh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhFlowService service = new XtwhFlowService();
		XtwhFlowModel model = new XtwhFlowModel();
		User user = getUser(request);// �û�����
		String yhszlx = request.getParameter("yhszlx");
		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================
		model.setYhszlx(yhszlx);
		// ==================����ǰ̨ҳ��====================
		// ========== ���Ի� �û���Ȩ begin ============
		if("rcxwwh".equals(yhszlx)){
			// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
			service.deleteYxyhRcxwwh(model, user);
		}else{
			// ϵͳά��-��������ά��-��������
			service.deleteYxyh(model, user);
		}
		// ========== ���Ի� �û���Ȩ end ============
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
}
