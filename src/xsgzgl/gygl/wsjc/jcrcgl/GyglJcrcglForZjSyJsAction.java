/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-5-31 ����10:59:40 
 */  
package xsgzgl.gygl.wsjc.jcrcgl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����ճ�action(�㽭��ҵ��ʦѧԺ)
 * @�๦������: �㽭��ҵ��ʦѧԺ���Ի� 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-5-31 ����10:59:40 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class GyglJcrcglForZjSyJsAction extends SuperAction<GyglJcrcglForm, GyglJcrcglForZjSyJsService>{
	/** 
	 * @����:����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-5-31 ����01:54:43
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
	public ActionForward saveForm (ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{		
		GyglJcrcglForm model = (GyglJcrcglForm) form;
		GyglJcrcglForZjSyJsService service = new GyglJcrcglForZjSyJsService();
		boolean result = service.insertJcrc(model);
		String messageKey = result?MessageKey.SYS_SAVE_SUCCESS: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}	
}
