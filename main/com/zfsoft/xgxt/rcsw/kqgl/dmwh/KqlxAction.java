/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����09:30:14 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.dmwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: �������ʹ���ά��
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����09:32:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KqlxAction extends SuperAction {

	private static final String url = "rcsw_kqgl_kqlxdmwh.do";
	
	/**
	 * 
	 * @����:�������ʹ���ά��
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����01:28:11
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
	@SystemAuth(url = url)
	public ActionForward viewKqlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "rcsw_kqgl_kqlxdmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewKqlxList");
	}
	
	
	/**
	 * 
	 * @����:��ѯ���������б�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����01:31:56
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
	@SystemAuth(url = url)
	public ActionForward kqlxQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		
		//��ѯ�����
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:���ӿ������ʹ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����01:32:24
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
	@SystemLog(description="�����ճ�����-���ڹ���-����ά��-����KQLXMC:{kqlxmc}")
	public ActionForward addKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�жϿ������ʹ���������Ƿ����
			boolean isExist = service.isExistByKqlxmc(model);
			if(!isExist){
				//��ÿ������ʹ���
				int nextKqlxdm = service.getNextKqlxdm();
				model.setKqlxdm(common.newp.StringUtil.toLengthStr(nextKqlxdm+"", 3, 0, "0"));
				//����һ���µĿ������ʹ���
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KQGL_DMWH_EXIST));
				return null;
			}
		}
		
		return mapping.findForward("addKqlx");
	}
	
	
	/**
	 * 
	 * @����:�޸Ŀ������ʹ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����01:44:56
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
	@SystemLog(description="�����ճ�����-���ڹ���-����ά��-�޸�KQLXDM:{kqlxdm},KQLXMC:{kqlxmc}")
	public ActionForward updateKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxForm model = (KqlxForm) form;
		KqlxService service = new KqlxService();
		KqlxForm myForm = service.getModel(model);
		
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//�ж���û�޸Ŀ�����������
			if(!model.getKqlxmc().trim().equals(myForm.getKqlxmc().trim())){
				//�жϿ��ڵǼǱ����Ƿ���ʹ��
				String checkKqlxdmForKqdj = service.checkKqlxdmForKqdj(model.getKqlxdm());
				
				if(!checkKqlxdmForKqdj.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_KQGL_DMWH_MCEXIST, checkKqlxdmForKqdj);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
			}else{
				//û���޸�����ֱ�ӱ���
				model.setKqlxmc(myForm.getKqlxmc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			//�жϿ������ʹ���������Ƿ����
			boolean isExist=service.isExistByKqlxmc(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_KQGL_DMWH_EXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateKqlx");
		
	}
	
	
	/**
	 * 
	 * @����:ɾ���������ʹ���
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-6 ����01:54:18
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
	@SystemLog(description="�����ճ�����-���ڹ���-����ά��-ɾ��VALUES:{values}")
	public ActionForward delKqlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		KqlxService service = new KqlxService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�жϿ��ڵǼǱ����Ƿ���ʹ��
			String checkKqlxdmForKqdj = service.checkKqlxdmForKqdj(values);
			
			if(!checkKqlxdmForKqdj.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_KQGL_DMWH_MCEXIST, checkKqlxdmForKqdj);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
