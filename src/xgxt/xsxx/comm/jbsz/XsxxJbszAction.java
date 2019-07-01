package xgxt.xsxx.comm.jbsz;

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

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������-action��
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

public class XsxxJbszAction extends BasicAction {

	/**
	 * ѧ����Ϣ_��������_ϵͳ�ֶ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xtzdsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXtzdszRForm(rForm, myForm, request);

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
			
			//�����������ֶ��Ƿ������ѷ����ֶβ�������Ϊ�����ã�
			String message = service.checkSaveZdsz(myForm);

			if (Base.isNull(message)) {
			
				boolean flag = service.saveZdsz(myForm, user, request);
				message = flag ? MessageInfo.MESSAGE_CONFIRM
						: MessageInfo.MESSAGE_SAVE_FALSE;
				
				if(flag){
					service.createNewView(myForm, user);
				}
			}
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ִ�д�����ͼ���� ===============
		if ("create".equalsIgnoreCase(doType)) {
			boolean flag = service.createNewView(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			message+="\n��ע��������plsql�ȹ��ߣ��в�\n����ͼxg_view_xsxxb�Ľṹ��";
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			rsArrList = service.getZdszRsList(myForm, user, colList);
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

		return mapping.findForward("xtzdsz");
	}

	/**
	 * ѧ����Ϣ_��������_ҳ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ymjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getYmjbszRForm(rForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// ================= end =====================

		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.saveYmsz(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;

			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============�����������ɵ�ֵ ============
		// �����ֶ��б�
		List<HashMap<String, String>> qyzdList = service.getQyzdList(myForm);
		request.setAttribute("qyzdList", qyzdList);

		// ������ʾ������
		String maxXsqdm = service.getMaxXsqdm();
		request.setAttribute("maxXsqdm", maxXsqdm);

		// �����úõ���ʾ��
		List<HashMap<String, Object>> xsqList = service.getXsqInfoList(myForm);
		request.setAttribute("xsqList", xsqList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("ymjbsz");
	}

	/**
	 * ѧ����Ϣ_��������_��ʾ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxJbszForm myForm = (XsxxJbszForm) form;
		XsxxJbszService service = new XsxxJbszService();
		XsxxJbszInit init = new XsxxJbszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getXsqszRForm(rForm, myForm, request);

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
			boolean flag = service.saveXsqsz(myForm, user);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			rsArrList = service.getXsqRsList(myForm, user, colList);
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

		return mapping.findForward("xsqsz");
	}
}
