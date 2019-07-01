package xsgzgl.gygl.wsjc.jcrcgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xsgzgl.comm.BasicModel;
import xsgzgl.gygl.comm.GyglNewInit;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.log.SystemLog;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: qlj
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-14 ����12:36:30
 * </p>
 */
public class GyglJcrcglAction extends BasicAction {

	// -----------------------��Ԣ���� ����ճ̹��� begin---------------------------

	/**
	 * ��������ճ̲�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);
		// ----------------����PATH begin-----------------------
		// ----------------��ʾtitle���ж϶�дȨ----------------
		rForm.setPath("gyglnew_wsjc_jcrcgl.do");
		// ----------------����PATH end-----------------------
		service.setRequestValue(rForm, user, request);
		// ----------------- ���������� ------------------------
		request.setAttribute("tableName", "view_xg_gygl_new_wsjc_jcrcb");
		// ----------------- ��������� ------------------------
		request.setAttribute("realTable", "xg_gygl_new_wsjc_jcrcb");
		return mapping.findForward("jcrcglCx");
	}

	/**
	 * ��������ճ̹�������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglZj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		
		String doType = "add";
		request.setAttribute("doType", doType);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", Base.getDqxqmc());
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		request.setAttribute("xxdm", Base.xxdm);
		myForm.setJclx(GyglNewInit.JFFS);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglWh");
	}

	/**
	 * ��������ճ̹����޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglXg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		String doType = "modi";
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		HashMap<String, String> rs = service.getJcrcglMap(myForm);
		request.setAttribute("doType", doType);
		// ������¼��Ϣ
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		
		if (StringUtil.isNull(myForm.getJclx())){
			myForm.setJclx(GyglNewInit.JFFS);
		}
		
		// ����ѧ�ꡢѧ�ڡ�����б�
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglWh");
	}

	/**
	 * ��������ճ̹�����ϸ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jcrcglCk(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GyglJcrcglService service = new GyglJcrcglService();
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		String pkValue = request.getParameter("pkValue");
		myForm.setGuid(pkValue);
		// ������¼��Ϣ
		HashMap<String, String> rs = service.getJcrcglMap(myForm);
		request.setAttribute("rs", rs);
		request.setAttribute("xjqs", GyglNewInit.WSJC_XJQS);
		request.setAttribute("path", "gyglnew_wsjc_jcrcgl.do");
		request.setAttribute("xxdm", Base.xxdm);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcrcglCk");
	}
	

	/**
	 * 
	 * @����:�ύ����ճ̹���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����02:39:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-�ύ����ճ̹���PK:{pkValue}")
	public ActionForward tjJcrcgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//�޸ļ���ճ̹���Ϊ�ύ״̬
		boolean result = service.tjJcrcgl(pkStr);
		if(result && "10279".equals(Base.xxdm)) {
			service.saveSubmit(pkStr);
		}
		//�㽭��ý���Ի����ճ��ύ���֮�����������ճ���ص������ּ�¼�е��Ƿ��ҵ�����ֶ�
		if(result && "11647".equals(Base.xxdm)){
			service.updateByqsForZjcm(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_REFER_SUCCESS : MessageInfo.MESSAGE_REFER_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:ȡ���ύ����ճ̹���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-29 ����02:40:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-ȡ������ճ̹���PK:{pkValue}")
	public ActionForward qxtjJcrcgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//�޸ļ���ճ̹���Ϊ�ύ״̬
		boolean result = service.qxtjJcrcgl(pkStr);
		if(result) {
			service.delCancel(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_CANCEL_SUCCESS : MessageInfo.MESSAGE_CANCEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	/** 
	 * @����:�������(�㽭��ҵ��ʦѧԺר��)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-31 ����10:46:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws 
	 */
	@SystemLog(description="���ʹ�Ԣ����-�������-������������ճ�PK:{pkValue}")
	public ActionForward saveForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		GyglJcrcglForm myForm = (GyglJcrcglForm) form;
		GyglJcrcglService service = new GyglJcrcglService();
		BasicModel model = new BasicModel();
		model.setPk(new String[] { "guid" });
		
		String pkStr = service.unicode2Gbk(request.getParameter("pkValue"));
		
		//�޸ļ���ճ̹���Ϊ�ύ״̬
		boolean result = service.qxtjJcrcgl(pkStr);
		if(result) {
			service.delCancel(pkStr);
		}
		String message = "";
		if (Base.isNull(message)) {
			message = result ? MessageInfo.MESSAGE_CANCEL_SUCCESS : MessageInfo.MESSAGE_CANCEL_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		
		return null;
	}
	
	
}