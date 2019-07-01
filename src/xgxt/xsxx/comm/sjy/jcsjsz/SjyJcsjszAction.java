package xgxt.xsxx.comm.sjy.jcsjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_����Դ_��������ά��action��
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

public class SjyJcsjszAction extends BasicAction {

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

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
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

		// =============== ִ�д�����ͼ���� ===============
		if ("create".equalsIgnoreCase(doType)) {
			boolean flag = service.createNewProcedure(myForm);
			String message = flag ? MessageInfo.MESSAGE_CREATE_SUCCESS
					: MessageInfo.MESSAGE_CREATE_FALSE;
			message+="\n��ע��������plsql�ȹ��ߣ��в�\n���洢����pro_xg_jcsj_stuInfo_tj�Ľṹ��";
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

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszGuideRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		//���鲽��
		String step = request.getParameter("step");
		myForm.setStep(step);
		// ================= end =====================

		// =============== ������������ ===============
		List<HashMap<String, String>> stepList = service.getStepList(myForm);
		request.setAttribute("stepList", stepList);
		
		String[] ch_zd = myForm.getCh_zd();
		String[] ch_zdm = myForm.getCh_zdm();
		request.setAttribute("ch_zd", ch_zd);
		request.setAttribute("ch_zdm", ch_zdm);
		
		//�ɲ����ֶ��б�
		List<HashMap<String, String>> kczzdList = service.getKczzdList(myForm);
		request.setAttribute("kczzdList", kczzdList);
		
		if(kczzdList!=null && kczzdList.size()>0){
			int num = kczzdList.size();
			int rowNum = 14;
			if(num <rowNum){
				rowNum = rowNum-num;
			}
			request.setAttribute("rowNum", rowNum);
		}
		// ================= end =====================
		
		// =============== ����������� ===============
		if ("step1".equalsIgnoreCase(step)) {// ��һ��
			// �������ֶ��б�
			List<HashMap<String, Object>> dszZdList = service
					.getDszZdList(myForm);
			request.setAttribute("dszZdList", dszZdList);
		} else {
			// �������ֶ��б�
			List<HashMap<String, Object>> xszZdList = service.getXszZdList(
					myForm, kczzdList);
			request.setAttribute("xszZdList", xszZdList);
		} 

		// ================= end =====================
		
		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZdsz(myForm, user);

			// �����洢����
			if (flag) {
				service.createNewProcedure(myForm);
			}

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
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
	public ActionForward jcsjszUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJcsjszRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ֶ��б�
		String zd = request.getParameter("zd");
		// ================= end =====================
		
		// =============== ��ø��ֶεĸ�����Ϣ ===============
		HashMap<String, Object> rs = service.getZdszXgInfo(zd);
		request.setAttribute("rs", rs);
		// ================= end =====================
		
		// =============== ִ�б������ ===============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZdsz(myForm, user);
			
			//�����洢����
			if(flag){
				service.createNewProcedure(myForm);
			}
			
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setXsxxOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("jcsjszUpdate");
	}
	
	//==============================���·���by qlj=========================================
	/**
	 * ѧ����Ϣ_����Դ_ѧ����Ϣ��ϸҳ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xxypzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SjyJcsjszForm myForm = (SjyJcsjszForm) form;
		SjyJcsjszService service = new SjyJcsjszService();
		SjyJcsjszInit init = new SjyJcsjszInit();
		User user = getUser(request);// �û�����
		String doType=request.getParameter("doType");
		
		//������ϸҳ����
		if("save".equalsIgnoreCase(doType)){
			
			boolean flag=service.saveXxysz(myForm);
			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		
		//��ȡ��ʾ��Ŀ�б�
		request.setAttribute("xsxmList", service.getXsmkList(myForm));
		
		request.setAttribute("xxypzList", service.getXxypz(myForm));
		// =================== end ===================
		
		request.setAttribute("path", "xsxx_sjy_xxypz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xxypzManage");
	}
	//============================== end =========================================
}
