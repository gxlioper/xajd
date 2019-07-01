package xgxt.comm.impexp;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;

import com.zfsoft.basic.BasicAction;
import common.Globals;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ͨ��-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class CommImpExpAction extends BasicAction {

	/**
	 * ���뵼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward commImpExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommImpExpService service = new CommImpExpService();
		CommForm myForm = (CommForm) form;

		// ================= ����ֵ ==================
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����
		String realTable = "";
		// ��ʾ��Ϣ
		String message = "";
		// ����·��
		String path = "xgxt_data_imp.do";
		// =================end==================

		// ==================ִ�е������ ==================
		if ("imp".equalsIgnoreCase(doType)) {

			String filePath = service.upLoadFile(request, myForm
					.getUploadFile(), "comm");

			message = (String) request.getAttribute("message");
			
			if (Base.isNull(message)) {
				if(Globals.XXDM_NTZYDX.equalsIgnoreCase(Base.xxdm)){
					message = service.impInfoForNtzy(myForm, filePath);
				}else {
					message = service.impInfo(myForm, filePath);
				}
				message = Base.isNull(message) ? "�������ݳɹ�" : message;
			}	
		}
		// =================end ===================

		// ==================ִ�е������� ==================
		if ("exp".equalsIgnoreCase(doType)) {

			HashMap<String, String> map = new HashMap<String, String>();

			response.reset();
			response.setContentType("application/vnd.ms-excel");

			service.expInfo(myForm, map, response.getOutputStream());

			return mapping.findForward("");
		}
		// =================end ===================

		// =================��ʼ��request��ֵ ====================
		RequestForm rForm = new RequestForm();

		rForm.setDoType(doType);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMessage(message);

		service.setRequestValue(rForm, request);
		// ===================end ====================

		// ===================��ʼ���б�ֵ ======================
		service.setList(myForm, request, "");
		// =================end ===================

		return mapping.findForward("commImp");
	}
}