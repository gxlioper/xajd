package com.zfsoft.xgxt.gygl.gypynew.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����ϵͳ
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2017-7-24 ����04:14:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CsszAction extends SuperAction<CsszForm,CsszService> {
	private CsszService service = new CsszService();
	private final String url = "gygl_gypynew_cssz.do";
	/**
	 * 
	 * @����: ��Ԣ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����04:11:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward  cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("splcMap", service.getSplc());
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("splcList", shlcService.getShlcByDjlx("gygl"));
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����04:19:19
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm myForm = (CsszForm)form;
		boolean rs = true;
		if(StringUtils.isNotNull(myForm.getId())){
			rs = service.runUpdate(myForm);
		}else{
			rs = service.runInsert(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
