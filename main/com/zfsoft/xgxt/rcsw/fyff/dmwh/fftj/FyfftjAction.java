/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����09:39:19 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.fftj;

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
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����-���÷���-��������ά��-����;��
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����09:39:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyfftjAction extends SuperAction {
	
	private static final String url = "rcsw_fyff_dmwh_ffxm.do";
	
	/**
	 * 
	 * @����:����;��ҳ����ת
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:35:02
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
	public ActionForward viewFftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "rcsw_fyff_dmwh_ffxm.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewFftj");
	}
	
	
	/**
	 * 
	 * @����:��ѯ����;��list
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:42:14
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
	public ActionForward fftjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyfftjForm model = (FyfftjForm) form;
		FyfftjService service = new FyfftjService();
		
		//��ѯ�����
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:���ӷ���;������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����10:02:33
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-����;��-����FFTJ:{fftj}")
	public ActionForward addFftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyfftjForm model = (FyfftjForm) form;
		FyfftjService service = new FyfftjService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж����ʹ���������Ƿ����
			boolean isExist=service.isExistByFftjmc(model); 
			if(!isExist){
				//��÷���;������
				int nextFftjdm=service.getNextFftjdm();
				model.setFftjdm(nextFftjdm+"");
				//����һ���µķ���;������
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		
		model.setFfzh("0");
		return mapping.findForward("addFftj");
	}
	
	
	/**
	 * 
	 * @����:�޸ķ���;������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����10:53:07
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-����;��-�޸�FFTJDM:{fftjdm},FFTJ:{fftj}")
	public ActionForward updateFftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyfftjForm model = (FyfftjForm) form;
		FyfftjService service = new FyfftjService();
		FyfftjForm myForm = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//�ж���û�޸ķ���;������
			if(!model.getFftj().trim().equals(myForm.getFftj().trim())){
				//�жϷ��Ž�������Ƿ���ʹ��
				String checkFftjForFfjg = service.checkFftjForFfjg(model.getFftjdm());
				
				if(!checkFftjForFfjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFftjForFfjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
			}else{
				//û���޸�����ֱ�ӱ���
				model.setFftj(myForm.getFftj().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExistByFftjmc(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_MCEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateFftj");
		
	}
	
	
	/**
	 * 
	 * @����:ɾ������;������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����11:37:53
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-����;��-ɾ��VALUES:{values}")
	public ActionForward delFftj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyfftjService service = new FyfftjService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�жϷ��Ž�������Ƿ���ʹ��
			String checkFftjForFfjg = service.checkFftjForFfjg(values);
			
			if(!checkFftjForFfjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFftjForFfjg);
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
