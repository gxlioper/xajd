/**
 * @����:ѧ����Ʒ(1)��
 * @���ڣ�2018-4-27 ����02:36:37 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ģ��
 * @�๦������: ����ת����������
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2018-4-27 ����02:37:31 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DazccsszAction extends SuperAction{
	private static final String url = "xsxx_dagl_cssz.do";
	private DazccsszService service = new DazccsszService();
	
	/**
	 * @����: ��������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-4-27 ����05:31:22
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
	public ActionForward getDazccsszList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazccsszForm myForm = (DazccsszForm)form;
		
		/*�����ر�*/
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onoffList", onoffList);
		
		/*��������б�*/
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		/*����path*/
		String path = "xsxx_dagl_cssz.do";
		request.setAttribute("path", path);
		
		DazccsszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}else{
			model = new DazccsszForm();
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazccsszList");
	}
	
	/**
	 * @����: �������ñ���
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ� 2018-5-7 ����03:57:59
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
	public ActionForward dazccsszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazccsszForm model = (DazccsszForm)form;
		boolean result = service.dazccsszSave(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
