/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����Ϣ 
 * @�๦������: �������
 * @���ߣ� ligl 
 * @ʱ�䣺 2013-12-11 ����01:35:21 
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class ShlcAction extends SuperAction {

	/**
	 * 
	 * @����:�����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-11 ����01:34:58
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveShlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		ShxxModel model = (ShxxModel) form;
		String gwid = request.getParameter("gwid");
		String ywid = request.getParameter("ywid");
		String xh = request.getParameter("xh");
		ShlcService service = new ShlcService();
		User user = getUser(request);
		model.setShr(user.getUserName());
		model.setGwid(gwid);
		model.setYwid(ywid);
		model.setSqrid(xh);
		boolean result = service.saveXgsh(model);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:���������������
	 * @���ߣ�ligl
	 * @���ڣ�2013-12-17 ����10:11:38
	 * @�޸ļ�¼: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward savePlshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		ShxxModel model = (ShxxModel) form;
		String dataJson = request.getParameter("dataJson");
//		DBEncrypt dbEncrypt = new DBEncrypt();
//		String dataJson = dbEncrypt.dCode(params.getBytes());
		ShlcService service = new ShlcService();
		User user = getUser(request);
		model.setShr(user.getUserName());
		boolean result = service.savePlxgsh(model,dataJson);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
