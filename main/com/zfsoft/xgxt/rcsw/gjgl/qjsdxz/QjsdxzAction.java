package com.zfsoft.xgxt.rcsw.gjgl.qjsdxz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

public class QjsdxzAction extends SuperAction<QjsdxzForm,QjsdxzService> {
	private QjsdxzService service = new QjsdxzService();
	/**
	 * 
	 * @����: ʱ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-11 ����02:20:59
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
	public ActionForward sdxzCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjsdxzForm qjsdxzform = (QjsdxzForm)form;
		QjsdxzForm qjsdxzmodel = service.getModel(qjsdxzform);
		if(qjsdxzmodel != null){
			BeanUtils.copyProperties(qjsdxzform, qjsdxzmodel);
		}
		request.setAttribute("path", "xg_qjgl_sdcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sdxzcssz");
	}
	
	/**
	 * 
	 * @����: ����ʱ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-11 ����02:23:49
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
	public ActionForward saveSdxzcssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjsdxzForm qjsdxzform = (QjsdxzForm)form;
		QjsdxzService tranService = TransactionControlProxy.newProxy(new QjsdxzService());
		boolean rs = tranService.saveQjsdxz(qjsdxzform);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
