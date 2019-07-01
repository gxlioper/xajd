package xgxt.pjpy.comm.zhcp.jbsz;

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

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_��������_Action��
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

public class ZhcpJbszAction extends BasicAction {

	/**
	 * �ۺϲ���_��������_�۲��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm=(ZhcpJbszForm)form;
		ZhcpJbszInit init = new ZhcpJbszInit();
		ZhcpJbszService service=new ZhcpJbszService();
		User user = getUser(request);// �û�����
		String message = "";// ��ʾ��Ϣ
		RequestForm rForm = new RequestForm();
		String doType=request.getParameter("doType");
		
		// ==============�۲�����========================
		myForm.setPjxn(ZhcpJbszForm.zcjbszModel.getPjxn());
		myForm.setPjxq(ZhcpJbszForm.zcjbszModel.getPjxq());
		myForm.setPjnd(ZhcpJbszForm.zcjbszModel.getPjnd());
		// ==============�۲����� end========================
		
		// ==============�۲�����========================
		myForm.setUpXn(ZhcpJbszForm.zcjbszModel.getUpXn());
		myForm.setUpXq(ZhcpJbszForm.zcjbszModel.getUpXq());
		myForm.setUpNd(ZhcpJbszForm.zcjbszModel.getUpNd());
		// ==============�۲����� end========================
		
		
		//�۲���Ϣ����(�۲����Ʊ��桢�۲��������)
		if("save".equalsIgnoreCase(doType)){
			//�۲����Ʊ���
			boolean flag=service.upateZcxmmc(myForm, user);
			if(flag){
				//�۲��������
				flag=service.saveZcbl(myForm, user);
			}
			message = flag ? "�۲���Ŀ�����޸ĳɹ�!"
					: "�۲���Ŀ�����޸�ʧ��!";
			rForm.setMessage(message);
			request.setAttribute("updateResult", flag);
			myForm.setXmjb(null);
		}
		
		if("init".equalsIgnoreCase(doType)){
			boolean flag=service.zhcpIni(myForm, user);
			message = flag ? MessageInfo.MESSAGE_INIT_SUCCESS
					: MessageInfo.MESSAGE_INIT_FALSE;
			rForm.setMessage(message);
			request.setAttribute("initResult", flag);
			myForm.setXmjb(null);
		}
		
		if("copyXm".equalsIgnoreCase(doType)){
			//false˵����������һ�����۲���Ŀ
			boolean flag=service.copyZcxm(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			myForm.setXmjb(null);
			
			request.setAttribute("copyResult", flag);
		}
		
		
		// ============= ��ʼ����������ֵ ============
		
		init.getDypfszRForm(rForm, myForm, request);
		// ==============��ʼ����������ֵend =============
		
		// �۲���Ŀ�б�
		List<HashMap<String,String>>xmlist=service.getZctreeList(myForm);
		// �۲��ϼ�����(distinct���)
		request.setAttribute("sjdmList", service.getSjdmList(myForm));
		// �۲���ϸ��Ϣ
		request.setAttribute("zcxmxxList", service.getZcxmxxList(myForm));
		
		
		// ============= ����request��ֵ =============
		request.setAttribute("xmList",xmlist);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		return mapping.findForward("zcjbsz");
	}

	/**
	 * �ۺϲ���_��������_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dypfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm = (ZhcpJbszForm) form;
		ZhcpJbszService service = new ZhcpJbszService();
		ZhcpJbszInit init = new ZhcpJbszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getDypfszRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		// =================== end ===================

		// ============= �Ǹ���Ա�������Σ��û����ɲ��� ============
		if (!"fdy".equalsIgnoreCase(userStatus)
				&& !"bzr".equalsIgnoreCase(userStatus)
				&& !"jd".equalsIgnoreCase(userStatus)) {
			message = "��ģ��ֻ���ɸ���Ա�������Σ����û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================
		
		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveDypfKgzt(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, Object> rs = service.getDypfInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		//service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("dypfsz");
	}
	
	/**
	 * �ۺϲ���_��������_�۲�ӷ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpJbszForm myForm = (ZhcpJbszForm) form;
		ZhcpJbszService service = new ZhcpJbszService();
		ZhcpJbszInit init = new ZhcpJbszInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getZcjfszRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String message = "";// ��ʾ��Ϣ
		// =================== end ===================
		
		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjfsz(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		List<HashMap<String, Object>> rsList = service.getZcjfInfoList(myForm, user);
		request.setAttribute("rsList", rsList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfsz");
	}
	
	
	
	/**
	 * �����ǰ�����۲���Ŀ
	 */
	public ActionForward removePjxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZhcpJbszService service = new ZhcpJbszService();
		
		boolean result = service.removeZcxm();
		request.setAttribute("removeResult", result ? "����ɹ�!" : "���ʧ��!");
		return zcjbsz(mapping, form, request, response);
	}
}

