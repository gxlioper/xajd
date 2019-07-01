package xgxt.pjpy.comm.zhcp.zcjf;

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
import xgxt.xljk.hzny.HznyXljkZxzxInit;
import xsgzgl.comm.BasicModel;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_�۲�ӷ�_Action��
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

public class ZhcpZcjfAction extends BasicAction {

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
	public ActionForward zcjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getZcjfsqRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		String xh = request.getParameter("xh");// ѧ��
		myForm.setXh(xh);
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "sq", request);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, Object> rs = service.getZcjfSqInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfsq");
	}

	/**
	 * �ۺϲ���_��������_�۲�ӷ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		List<HashMap<String, String>> xmList = service.getXmList();// ��Ŀ�б�
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getZcjfshRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		String[] colList = rForm.getColList();// �������ʾ�ֶ�
		// =================== end ===================

		// ============= �Ǹ���Ա�������Σ��û����ɲ��� ============
		if (!"fdy".equalsIgnoreCase(userStatus)
				&& !"bzr".equalsIgnoreCase(userStatus)
				&& !"jd".equalsIgnoreCase(userStatus)) {
			message = "������ֻ���ɸ���Ա�������Σ����û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================

		// ============= ִ���ύ���� ============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "pltj", request);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		ArrayList<String[]> rsArrList = service.getJfshList(myForm, user,
				xmList);
		rForm.setRsArrList(rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zcjfsh");
	}

	/**
	 * �ۺϲ���_��������_�۲�ӷ����(��ϸ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getZcjfsqRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		String xh = request.getParameter("pk");// ѧ��
		if (Base.isNull(myForm.getXh())) {
			myForm.setXh(xh);
		}
		// =================== end ===================

		// ============= ѧ���û��ල����� ============
		if ("stu".equalsIgnoreCase(userType) && "jg".equalsIgnoreCase(doType)) {
			request.setAttribute("mklx", "jg");
		}
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "sh", request);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// ============= ִ���ύ���� ============
		if ("submit".equalsIgnoreCase(doType)) {
			boolean flag = service.saveZcjf(myForm, user, "tj", request);
			message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
					: MessageInfo.MESSAGE_DO_FALSE;
			rForm.setMessage(message);
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, Object> rs = service.getZcjfSqInfo(myForm, user);
		request.setAttribute("rs", rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		// service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("zcjfshDetail");
	}

	/**
	 * �ۺϲ���_��������_�۲�ӷֲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zcjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		ZhcpZcjfService service = new ZhcpZcjfService();
		ZhcpZcjfInit init = new ZhcpZcjfInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		List<HashMap<String, String>> xmList = service.getXmList();// ��Ŀ�б�
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getZcjfcxRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		String[] colList = rForm.getColList();// �������ʾ�ֶ�
		// =================== end ===================

		// =============== ����Excel ===========
		if ("print".equalsIgnoreCase(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.printZcjfsq(myForm, rForm, user, xmList, response
					.getOutputStream());
		}
		// ================= end =====================

		// =============== ��ʼ��ҳ����ʾֵ ===========
		ArrayList<String[]> rsArrList = service.getJfshList(myForm, user,
				xmList);
		rForm.setRsArrList(rsArrList);
		request.setAttribute("searchTj", myForm.getSearchModel());
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("zcjfcx");
	}
	
	/**
	 * �ж���˷��Ƿ�Ϊ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShfs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfService service = new ZhcpZcjfService();
		
		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		
		String xh=request.getParameter("xh");

		String message="";
		
		boolean blog=service.checkShfs(myForm, xh);
		
		message = blog ? "true"
				: "��ѧ���۲�ӷ���δ¼����˷֣�������ύ������˷ֽ�����ִ����Ƿ�ȷ������?";
	
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
	

	/**
	 * �ж���˷��Ƿ��޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkShfIsModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpZcjfService service = new ZhcpZcjfService();
		
		ZhcpZcjfForm myForm = (ZhcpZcjfForm) form;
		
		String xh=request.getParameter("xh");
		
		HashMap<String,Object>map=service.getValueMapByObj(request, null);
		String message="";
		
		map.put("xh", xh);
		
		boolean blog=service.checkShfIsModi(myForm,map);
		
		message = blog ? "true"
				: "��ѧ���۲�ӷ������޸ģ�����δ���棬������ύ�����޸ļ�¼����ԭ����ϢΪ׼���Ƿ�ȷ����������?";
	
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);
		
		return null;
	}
}
