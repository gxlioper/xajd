/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-3 ����10:58:47 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ù���ģ��ֻ����ʦ����
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-3 ����10:58:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QjXySzAction extends SuperAction<QjXySzForm, QjXySzService> {
	QjXySzService service = new QjXySzService();
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-3 ����11:07:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward qjxySzCx(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap<String, String> xysz_data = service.getQjXySzDada();
		request.setAttribute("data", xysz_data);
		return mapping.findForward("qjxySzCx");
	}
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-3 ����11:27:59
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
	public ActionForward save_xyszData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		QjXySzForm model = (QjXySzForm)form;
		String content = DealString.toGBK(request.getParameter("editorid"));
		model.setContent(content);
		boolean result  = false;
		if(model.getContent() != null){
			model.setBjr(getUser(request).getUserName());
			result = service.save_xyszData(model);
		}
		String message = result ? MessageUtil.getText(
				MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SAVE_FAIL);
	    response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-3 ����11:07:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward qjxyCk(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap<String, String> xysz_data = service.getQjXySzDada();
		request.setAttribute("data", xysz_data);
		return mapping.findForward("qjxyCk");
	}
}
