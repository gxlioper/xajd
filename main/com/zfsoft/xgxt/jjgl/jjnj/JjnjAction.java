/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����04:22:56 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

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
import com.zfsoft.xgxt.jjgl.cssz.CsszForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����04:22:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JjnjAction extends SuperAction<JjnjForm, JjnjService> {
	
	

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
		JjnjForm model = (JjnjForm)	form;
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
		JjnjForm model = (JjnjForm)	form;
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
		result = getService().delete(request.getParameter("delIds"));
		message = getJsonMessageByKey(result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(message);
		return null;
	}
}
