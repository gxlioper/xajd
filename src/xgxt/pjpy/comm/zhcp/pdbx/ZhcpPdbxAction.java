package xgxt.pjpy.comm.zhcp.pdbx;

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
import xgxt.pjpy.comm.pjpy.jbsz.PjpyJbszService;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszService;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;

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

public class ZhcpPdbxAction extends BasicAction {
	
	/**
	 * �ۺϲ���_Ʒ�±��ַ�_ѧ������
	 * @method xspjpdf
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pdbxLr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyJbszService jbszService = new PjpyJbszService();
		ZhcpPdbxService service=new ZhcpPdbxService();
		ZhcpPdbxForm myForm=(ZhcpPdbxForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		
		String doType=request.getParameter("doType");
		
		HashMap<String, String> stuInfo = service.getPjrxx(myForm,user);
		//	==============�۲�����========================
		HashMap<String, String> jbszMap = jbszService.getPjpyJbsz();

		myForm.setPjxn(jbszMap.get("pjxn"));
		myForm.setPjxq(jbszMap.get("pjxq"));
		myForm.setPjnd(jbszMap.get("pjnd"));
		// ==============�۲����� end========================
		myForm.setBjdm(stuInfo.get("bjdm"));
		myForm.setPfr(stuInfo.get("xh"));
		
		if (!"stu".equalsIgnoreCase(user.getUserType())) {
			String msg = "��ģ��ֻ����ѧ���û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}else if(!service.getDypfsz(myForm,user)){
			String msg = "ѧ������δ���ţ�";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		
		String message = "";// ��ʾ��Ϣ
		//�޸�Ʒ������
		if("save".equalsIgnoreCase(doType)){
			boolean flag=service.updatePdbxf(myForm, user);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			rForm.setMessage(message);
		}
		
		//�ύ����
		if("tjpf".equalsIgnoreCase(doType)){
			boolean flag=service.updateXstjzt(myForm, user);
			message = flag ? MessageInfo.MESSAGE_REFER_SUCCESS
					: MessageInfo.MESSAGE_REFER_FALSE;
			rForm.setMessage(message);
			//��������-ѧ�������ύ���bug����Ҫ�������һ��
			stuInfo = service.getPjrxx(myForm,user);
		}
		
		
		List<String[]>rs=service.getXspjPdbx(myForm, user);
		
		// ============request==============
		request.setAttribute("path", "zhcp_pdbx_xspf.do");
		request.setAttribute("rs", rs);
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("pdbxszList", service.getPdbxSz(myForm, user));
		request.setAttribute("topTr", service.getTop(myForm, user));
		service.setRequestValue(rForm, request);
		FormModleCommon.commonRequestSet(request);
		// ==========request end==============
		return mapping.findForward("pdbxLr");
	}
	
	/**
	 * �ۺϲ���_Ʒ�±��ַ�_��ʦ�鿴Ʒ�·�
	 * @method lsckpdf
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pdbxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZhcpPdbxService service=new ZhcpPdbxService();
		ZhcpPdbxForm myForm=(ZhcpPdbxForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		PjpyJbszService jbszService = new PjpyJbszService();
		
		String doType=request.getParameter("doType");
		
		// ==============�۲�����========================
		HashMap<String, String> jbszMap = jbszService.getPjpyJbsz();

		myForm.setPjxn(jbszMap.get("pjxn"));
		myForm.setPjxq(jbszMap.get("pjxq"));
		myForm.setPjnd(jbszMap.get("pjnd"));
		// ==============�۲����� end========================
		
		
		
		String message = "";// ��ʾ��Ϣ
		if("jsqr".equalsIgnoreCase(doType)){
			
			boolean flag=service.updateZt(myForm, user);
			message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
					: MessageInfo.MESSAGE_WORK_FALSE;
			rForm.setMessage(message);
			doType="";
		}
		
		if("pdbxfjs".equalsIgnoreCase(doType)){
			boolean flag=service.pdfjs(myForm, user);
			message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
					: MessageInfo.MESSAGE_WORK_FALSE;
			rForm.setMessage(message);
			doType="";
		}
		
		//�����
		List<String[]>rs=service.getLsckpf(myForm, user);
		
		// ============request==============
		request.setAttribute("path", "zhcp_pdbx_lsck.do");
		request.setAttribute("rs", rs);
		request.setAttribute("searchTj", myForm.getSearchModel());
//		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("topTr", service.getLsckTop(myForm, user));
		request.setAttribute("pdbxfTj", service.pdbxfTj(myForm, user));
		service.setRequestValue(rForm, request);
		FormModleCommon.commonRequestSet(request);
		// ==========request end==============
		return mapping.findForward("pdbxCx");
	}
	
	/**
	 * �ۺϲ���_Ʒ�±��ַ�_Ʒ�·�����ȷ��
	 * @method lsckpdf
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward pdbxQd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsxxglService stuService = new XsxxglService();
		PjpyJbszService jbszService = new PjpyJbszService();
		ZhcpPdbxService service=new ZhcpPdbxService();
		ZhcpPdbxForm myForm=(ZhcpPdbxForm)form;
		RequestForm rForm = new RequestForm();
		User user=getUser(request);
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		String jsqr=request.getParameter("jsqr");
		
		myForm.setPfr(pkValue);
		myForm.setJsqr(jsqr);
		HashMap<String, String> stuInfo = stuService.selectStuinfo(myForm.getPfr());
		// ==============�۲�����========================
		HashMap<String, String> jbszMap = jbszService.getPjpyJbsz();

		myForm.setPjxn(jbszMap.get("pjxn"));
		myForm.setPjxq(jbszMap.get("pjxq"));
		myForm.setPjnd(jbszMap.get("pjnd"));
		// ==============�۲����� end========================
		
		String message = "";// ��ʾ��Ϣ
		if("jsqr".equalsIgnoreCase(doType)){
			boolean flag=service.updateQrzt(myForm, user);
			message = flag ? MessageInfo.MESSAGE_WORK_SUCCESS
					: MessageInfo.MESSAGE_WORK_FALSE;
			rForm.setMessage(message);
		}
		//�����
		List<String[]>rs=service.getLcckPdbx(myForm, user);
		
		// ============request==============
//		request.setAttribute("path", "zhcp_pdbx_lsck.do");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("stuInfo", stuInfo);
		request.setAttribute("topTr", service.getTop(myForm, user));
		service.setRequestValue(rForm, request);
//		FormModleCommon.commonRequestSet(request);
		// ==========request end==============
		return mapping.findForward("pdbxQd");
	}
}
