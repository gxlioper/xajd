package xgxt.xsxx.comm.sjy;

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
import xgxt.xsxx.comm.jbsz.XsxxJbszForm;
import xgxt.xsxx.comm.jbsz.XsxxJbszInit;
import xgxt.xsxx.comm.jbsz.XsxxJbszService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_����Դ-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxSjyAction extends BasicAction {

	/** 
	 * ����ģ��ѡ��
	 * Method xsxxwh
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward jcsjszChoose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return mapping.findForward("jcsjszChoose");
	}
	
	/**
	 * ѧ����Ϣ_����Դ_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxSjyForm myForm = (XsxxSjyForm) form;
		XsxxSjyService service = new XsxxSjyService();
		XsxxSjyInit init = new XsxxSjyInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			
//			//�����������ֶ��Ƿ������ѷ����ֶβ�������Ϊ�����ã�
//			String message = service.checkSaveZdsz(myForm);
//
//			if (Base.isNull(message)) {
//			
//				boolean flag = service.saveZdsz(myForm, user, request);
//				message = flag ? MessageInfo.MESSAGE_CONFIRM
//						: MessageInfo.MESSAGE_SAVE_FALSE;
//				
//				if(flag){
//					service.createNewView(myForm, user);
//				}
//			}
//			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ִ�д�����ͼ���� ===============
		if ("create".equalsIgnoreCase(doType)) {
//			boolean flag = service.createNewView(myForm, user);
//			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
//					: MessageInfo.MESSAGE_CREATE_FALSE;
//			message+="\n��ע��������plsql�ȹ��ߣ��в�\n����ͼxg_view_xsxxb�Ľṹ��";
//			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			//rsArrList = service.getZdszRsList(myForm, user, colList);
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszManage");
	}
	
	/**
	 * ѧ����Ϣ_����Դ_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcsjszGuide(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxSjyForm myForm = (XsxxSjyForm) form;
		XsxxSjyService service = new XsxxSjyService();
		XsxxSjyInit init = new XsxxSjyInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {

		}
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszGuide");
	}
}
