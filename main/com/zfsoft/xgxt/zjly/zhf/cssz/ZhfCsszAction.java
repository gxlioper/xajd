/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����01:32:23 
 */  
package com.zfsoft.xgxt.zjly.zhf.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����01:32:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfCsszAction extends SuperAction<ZhfCsszForm, ZhfCsszService>{
	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "xg_zjly_zhfcssz.do";
	
	/** 
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-17 ����02:12:10
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
		ZhfCsszForm myForm = (ZhfCsszForm) form;
		ZhfCsszService service = new ZhfCsszService();
		ZhfCsszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(myForm, model);
		} else {
			myForm.setSqkg(KGZT_CLOSE);
		}
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// �����ر�
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "xg_zjly_zhfcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhfCsszForm myForm = (ZhfCsszForm) form;
		ZhfCsszService service = new ZhfCsszService();
		boolean result = false;
		service.deleteJcsz();
		result = service.runInsert(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;	
	}
}
