/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.hdgl.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;



/**
 * @className	�� CsszAction
 * @description	�� cssz(��������������)
 * @author 		��������1282��
 * @date		�� 2018-1-16 ����11:07:19
 * @version 	V1.0 
 */

public class CsszAction extends SuperAction<CsszForm, CsszService>{
	private static final String url = "hdgl_hdbl_cssz.do";
	
	/**
	 * @description	�� ���������б�
	 * @author 		�� ������1282��
	 * @date 		��2018-1-16 ����11:17:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dekt");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		CsszForm CsszForm = service.getCsszForm();
		BeanUtils.copyProperties(model, CsszForm);
		request.setAttribute("path", "hdgl_hdbl_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * @description	�� ����
	 * @author 		��������1282��
	 * @date 		��2018-1-16 ����03:22:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		service.delete();
		boolean result = service.insert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;	
	}
}
