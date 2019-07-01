/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-4-25 ����08:47:45 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ������2013��--��ͥ������� �������� 
 * @���ߣ� Penghui.Qu 
 * @ʱ�䣺 2013-4-25 ����08:47:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtqkdcJcszAction extends SuperAction {

	private static final String url = "xszz_jtqkdc_cssz.do";
	
	/**
	 * 
	 * @����:���������޸�
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����08:56:05
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
	public ActionForward updateJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszForm myForm = (JtqkdcJcszForm) form;
		JtqkdcJcszService service = new JtqkdcJcszService();
		
		JtqkdcJcszForm model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//����
		List<HashMap<String, String>> onOff = optionUtil.getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "xszz_jtqkdc_cssz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("updateJcsz");
	}
	
	
	/**
	 * 
	 * @����:�����������
	 * @���ߣ�Penghui.Qu
	 * @���ڣ�2013-4-25 ����09:11:13
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
	@SystemLog(description="����ѧ������-��ͥ���-��ͥ�����������-����-SQKG:{sqkg}")
	public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszForm myForm = (JtqkdcJcszForm) form;
		JtqkdcJcszService service = new JtqkdcJcszService();
		
		boolean isSuccess = service.runInsert(myForm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
}
