/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-11-21 ����09:18:00 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.bjdldm;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: �༶����ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-11-21 ����09:18:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjdldmAction extends SuperAction{
	
	private static final String url = "pj_dmwh.do";
	
	/**
	 * 
	 * @����:�༶�������鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����09:37:46
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
	public ActionForward viewBjdldmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdldmForm model = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		
		if(QUERY.equals(model.getType())){
			
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewBjdldmList");
	}
	
	
	/**
	 * 
	 * @����:�༶������������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����11:27:30
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
	@SystemLog(description="������������-��������-����ά��-�����༶��������:LBMC:{lbmc}")
	public ActionForward addBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdldmForm model = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		
		if(SAVE.equalsIgnoreCase(model.getType())){
			//�ж��Ƿ�����ά��
			model.setLbmc(model.getLbmc().trim());
			boolean isExist=service.isExistByBjdldm(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_BJDM_DMEXIST));
				return null;
			}
		}
		
		return mapping.findForward("addBjlbdm");
	}
	
	/**
	 * 
	 * @����:�༶���������޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����04:08:00
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
	@SystemLog(description="������������-��������-����ά��-�����༶�����޸�-LBDM��{lbdm},LBMC:{lbmc}")
	public ActionForward updateBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		BjdldmForm myForm = (BjdldmForm) form;
		BjdldmService service = new BjdldmService();
		BjdldmForm model = service.getModel(myForm);

		if (UPDATE.equalsIgnoreCase(myForm.getType())) {
			// �ж��Ƿ����޸Ĵ�������
			if (!myForm.getLbmc().trim().equals(model.getLbmc().trim())) {
				// �����������Ƿ���ʹ��
				String checkBjldForBjlb = service.checkLbForBjdl(myForm
						.getLbdm());
				if (!checkBjldForBjlb.trim().equals("")) {
					String message = MessageUtil.getText(
							MessageKey.PJPY_BJDM_EXIST_BJDL_UPDATE,
							checkBjldForBjlb);
					response.getWriter().print(getJsonMessage(message));
					System.out.println(11);
					return null;
				}
			}else {
					// û���޸�ֱ�ӱ���
					myForm.setLbmc(myForm.getLbmc().trim());
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
							: MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					System.out.println(11);
					return null;
			}
			//�жϴ��������Ƿ����
			myForm.setLbmc(myForm.getLbmc().trim());
			boolean isExist = service.isExistByBjdldm(myForm);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_BJDM_DMEXIST));
				return null;
			}
			
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

		return mapping.findForward("updateBjlbdm");
	}
	
	/**
	 * 
	 * @����:�༶��������ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-11-21 ����04:11:52
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
	@SystemLog(description="������������-��������-����ά��-�����༶����ɾ��-VALUES��{values}")
	public ActionForward deleteBjlbdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjdldmService service = new BjdldmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//�������Ƿ���ʹ��
			String checkBjldForBjlb = service.checkLbForBjdl(values);
			if (!checkBjldForBjlb.trim().equals("")) {
				String message = MessageUtil.getText(
						MessageKey.PJPY_BJDM_EXIST_BJDL_DELETE,
						checkBjldForBjlb);
				response.getWriter().print(getJsonMessage(message));
				System.out.println(11);
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
}