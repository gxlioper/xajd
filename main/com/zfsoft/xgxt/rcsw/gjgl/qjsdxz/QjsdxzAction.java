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
	 * @描述: 时段限制
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-11 下午02:20:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
	 * @描述: 保存时段限制
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-11 下午02:23:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
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
