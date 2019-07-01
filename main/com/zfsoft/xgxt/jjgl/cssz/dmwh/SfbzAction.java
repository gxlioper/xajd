/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����04:44:31 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����04:44:31 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SfbzAction extends SuperAction<SfbzForm, SfbzService> {
	
	
	private JjxkService jjxkService = new JjxkService();
	
	private JjnjService jjnjService = new JjnjService();
	
	/**
	 * 
	 * @����:���
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����04:46:51
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
	
	public  ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		SfbzForm model = (SfbzForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().add(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 
	 * @����:�޸�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����04:46:51
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
	
	public  ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		SfbzForm model = (SfbzForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().runUpdate(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-8-25 ����04:46:51
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
	
	public  ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		boolean result = false;
		JSONObject message = null;
		result = getService().runDelete(request.getParameter("delIds").split(",")) > 0;
		message = getJsonMessageByKey(result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @return the jjxkService
	 */
	public JjxkService getJjxkService() {
		return jjxkService;
	}

	/**
	 * @param jjxkServiceҪ���õ� jjxkService
	 */
	public void setJjxkService(JjxkService jjxkService) {
		this.jjxkService = jjxkService;
	}

	/**
	 * @return the jjnjService
	 */
	public JjnjService getJjnjService() {
		return jjnjService;
	}

	/**
	 * @param jjnjServiceҪ���õ� jjnjService
	 */
	public void setJjnjService(JjnjService jjnjService) {
		this.jjnjService = jjnjService;
	}
	
	
}
