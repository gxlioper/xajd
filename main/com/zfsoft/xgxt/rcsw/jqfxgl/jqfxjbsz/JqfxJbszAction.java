/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-4-20 ����03:52:10 
 */  
package com.zfsoft.xgxt.rcsw.jqfxgl.jqfxjbsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-4-20 ����03:52:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@SuppressWarnings("unchecked")
public class JqfxJbszAction extends SuperAction {

	/**
	 * 
	 * @����:TODO(���ڷ�У��������)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:52:10 
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
	public ActionForward jqfxJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxJbszForm myForm = (JqfxJbszForm) form;
		JqfxJbszService service = new JqfxJbszService();		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		
		//��У������Ƽ���
		JqfxJbszService JqfxJbszService = new JqfxJbszService();
		List<HashMap<String,String>> jqfxmcList = JqfxJbszService.getFxmcList();
		request.setAttribute("jqfxmcList", jqfxmcList);
			
		String path = "rcsw_jqfxgl_jbsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JqfxJbszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jqfxjbsz");
		
	}
	
	/**
	 * 
	 * @����:TODO(������ڷ�У���)
	 * @���ߣ� lgx[����:1553]
	 * @ʱ�䣺 2018-4-20 ����03:52:10 
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
	public ActionForward saveJqfxJbsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxJbszForm myForm = (JqfxJbszForm) form;
		JqfxJbszService service = new JqfxJbszService();		
		boolean result = service.saveJbsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}

}
