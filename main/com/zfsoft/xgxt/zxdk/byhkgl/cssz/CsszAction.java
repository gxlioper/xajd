/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����09:49:41 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����09:49:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<ByhkglCsszForm, CsszService>{
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_byhkgl_cssz.do";
	
	/**
	 * 
	 * @����: ��������ҳ��
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-6 ����10:33:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ByhkglCsszForm myForm = (ByhkglCsszForm) form;
		CsszService service = new CsszService();
		ByhkglCsszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(myForm, model);
		} else {
			myForm.setSqkg(KGZT_CLOSE);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");// ������������������б��ȡֵͨ�÷���
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "zxdk_byhkgl_cssz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cssz");	
		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-6 ����10:33:16
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ByhkglCsszForm myForm = (ByhkglCsszForm) form;
		CsszService service = new CsszService();
		boolean result = false;
		service.deleteJcsz();
		result = service.runInsert(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;	
	}
		
}
