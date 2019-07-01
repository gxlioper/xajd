package com.zfsoft.xgxt.gygl.jlkjfxgl.jqfxjcsz;


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
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ������ڷ�У����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ������[����:995]
 * @ʱ�䣺 2016-3-11 ����09:29:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
@SuppressWarnings("unchecked")
public class JqfxjcszAction extends SuperAction {

	/**
	 * 
	 * @����:TODO(���ڷ�У��������)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-25 ����04:29:07
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
	public ActionForward jqfxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxjcszForm myForm = (JqfxjcszForm) form;
		JqfxjcszService service = new JqfxjcszService();		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//�Ƿ�list
		request.setAttribute("kgList", isnotList);
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		
		//��У������Ƽ���
		JqfxjcszService jqfxjcszService = new JqfxjcszService();
		List<HashMap<String,String>> jqfxmcList = jqfxjcszService.getFxmcList();
		request.setAttribute("jqfxmcList", jqfxmcList);
			
		String path = "jlkjxyjqfxjcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JqfxjcszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("jlkjfxgljcsz");
		
	}
	
	/**
	 * 
	 * @����:TODO(������ڷ�У���)
	 * @���ߣ�������[���ţ�995]
	 * @���ڣ�2016-2-25 ����03:52:31
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
	public ActionForward saveJqfxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JqfxjcszForm myForm = (JqfxjcszForm) form;
		JqfxjcszService service = new JqfxjcszService();		
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}

}
