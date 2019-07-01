/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-4 ����10:34:08 
 */  
package com.zfsoft.xgxt.xszz.jtqkxgzd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-7-4 ����10:34:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XgzdAction extends SuperAction<XgzdForm, XgzdService> {
	//����service���󣬷�����÷���
	XgzdService service = new XgzdService();
	/**
	 * 
	 * @����: �޸��ֶβ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-4 ����02:00:56
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
	public ActionForward xgzdCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XgzdForm model = (XgzdForm)form;
		//��ѯ
		if("search".equals(model.getType())){
			JSONObject JsonObj = service.getJsonData();
			response.getWriter().print(JsonObj);
			return null;
		}
		String path = "xszz_jtqkdc_xgzdsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgzdCx");
	}
	
	/**
	 * 
	 * @����: �����ֶα���Ǳ�����Ϣ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-7-5 ����01:47:01
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
	public ActionForward saveData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XgzdForm model = (XgzdForm)form;
		//����
	    boolean result = service.saveData(model);
	    String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
