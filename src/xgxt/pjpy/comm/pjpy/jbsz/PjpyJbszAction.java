package xgxt.pjpy.comm.pjpy.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyInit;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������-action��
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

public class PjpyJbszAction extends BasicAction {

	/**
	 * ��������_��������_������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pjjbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJbszForm myForm = (PjpyJbszForm) form;
		PjpyJbszService service = new PjpyJbszService();
		PjpyInit init = new PjpyInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getPjjbszRForm(rForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		// =================== end ===================

		// ============= ִ�б������ ============
		if ("save".equalsIgnoreCase(doType)) {

			boolean flag = service.savePjpyJbsz(myForm, rForm, user, request);

			String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
			
			if(flag){
				PjxtszModel.setPjxtszModel();
				service.updatePjzqSfsz("1");//���������Ƿ����ø�Ϊ���ǡ�;
			}
		}
		// =================== end ==============

		// =============== ��ʼ��ҳ����ʾֵ ===========
		HashMap<String, String> rs = service.getPjpyJbsz();
		rForm.setRs(rs);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("pjjbsz");
	}
	
	
	
	
	/**
	 * ��ʼ����
	 */
	public ActionForward startPjpy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		boolean result = service.startPjpy();
		
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(result); 
		return null;
	}
	
	
	
	
	/**
	 * �����ۺ�����
	 */
	public ActionForward pjpyZhsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		
		request.setAttribute("pjxtszModel", PjxtszModel.pjxtszModel);
		request.setAttribute("path", "pjpy_zhsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("pjpyZhsz");
	}
	
	
	
	/**
	 * ��������-�Ƿ�����
	 */
	public ActionForward getPjzqSfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyJbszService service = new PjpyJbszService();
		
		String flg = service.getPjpyPjzqSfsz();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(flg); 
		
		return null;
	}
	
	
	/**
	 * ��������-�۲��Ƿ�����
	 */
	public ActionForward getPjzcSfsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyJbszForm model = (PjpyJbszForm) form;
		PjpyJbszService service = new PjpyJbszService();
		String zczq = model.getZczq();
		
		String count = service.getZczqSfsz(zczq);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
	
	
	
	/**
	 * ������Ŀ�Ƿ�ά��
	 */
	public ActionForward getPjxmSfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		String count = service.getPjxmSfwh();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
	
	
	
	/**
	 * ������Ա�Ƿ�����
	 */
	public ActionForward getPjrySfwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjpyJbszService service = new PjpyJbszService();
		
		String count = service.getPjrySfsz();
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(count);
		
		return null;
	}
}