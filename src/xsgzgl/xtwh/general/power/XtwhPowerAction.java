package xsgzgl.xtwh.general.power;

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
 * Description: ϵ�y�S�o_Ȩ��_ͨ��_Action��
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

public class XtwhPowerAction extends BasicAction {

	/**
	 * ������ϵͳһ���˵��б�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createFirstGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createFirstGnmkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * ������ϵͳ�����˵��б�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createSecondGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createSecondGnmkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * �����û���˵��б�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhzGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createYhzGnmkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

	/**
	 * �����û��˵��б�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createYhGnmkDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createYhGnmkDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
	
	/**
	 * �����û�Div
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createUserDiv(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		XtwhPowerService service = new XtwhPowerService();
		XtwhPowerModel model = new XtwhPowerModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		service.getModelValue(model, request);
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createUserDiv(model, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}
}
