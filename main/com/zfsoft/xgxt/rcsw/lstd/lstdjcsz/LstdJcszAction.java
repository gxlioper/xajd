/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-21 ����05:28:57 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

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
 * @ģ������: ��ɫͨ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-11-21 ����05:28:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LstdJcszAction extends SuperAction {

	private static final String url = "rcsw_lstd_jcsz.do";
	
	/**
	 * 
	 * @����:��ɫͨ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-24 ����05:46:44
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
	public ActionForward lstdJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdJcszForm myForm = (LstdJcszForm) form;
		LstdJcszService service = new LstdJcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("lstd");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_lstd_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		LstdJcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("lstdJcsz");
	}

	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-11-24 ����05:47:12
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
	public ActionForward saveLstdJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		LstdJcszForm myForm = (LstdJcszForm) form;
		LstdJcszService service = new LstdJcszService();
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
