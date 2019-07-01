/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-3-23 ����10:55:35 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

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
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ѧ����Ϣ����ע��
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-3-23 ����10:55:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<CsszForm, CsszService>{
	
	private static final String url = "xsxx_cssz.do";
	
	/**
	 * 
	 * @����:��������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-3-23 ����11:21:07
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CsszForm csszForm =(CsszForm)form;
		CsszService service = new CsszService();
		CsszForm model = service.getModel();
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "xsxx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

}
