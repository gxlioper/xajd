/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-21 ����10:46:26 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm.pjxzdm;

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
 * @ģ������: �������Ź���ģ��
 * @�๦������: �������ʴ���ά��
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-21 ����10:46:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxzdmAction extends SuperAction{
	
	private static final String url = "pj_dmwh.do";
	
	/**
	 * ������Ŀ���ʴ���ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward viewPjxzdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjxzdmForm model = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewPjxzdmList");
	}
	
	/**
	 * ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="������������-��������-����ά��-������Ŀ��������XMXZMC:{xmxzmc}")
	public ActionForward addPjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmForm model = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж�������������Ƿ����
			model.setXmxzmc(model.getXmxzmc().trim());
			boolean isExist=service.isExistByXmxzdm(model, SAVE);
			if(!isExist){
				int nextXzdm=service.getNextXmxzdm();
				model.setXmxzdm(nextXzdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
				return null;
			}
		}
		
		return mapping.findForward("addPjxzdm");
	}
	
	/**
	 * 
	 * @����:�޸��������ʴ���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����02:06:22
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ�����޸�-XMXZDM��{xmxzdm},XMXZMC:{xmxzmc}")
	public ActionForward updatePjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmForm myForm = (PjxzdmForm) form;
		PjxzdmService service = new PjxzdmService();
		PjxzdmForm model = service.getModel(myForm);
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//�ж���ô���޸���������
			if(!myForm.getXmxzmc().trim().equals(model.getXmxzmc().trim())){
				//����������������Ƿ���ʹ��
				String checkPjxzdmForPjjg = service.checkXzForPjjg(myForm.getXmxzdm());
				//����������Ŀ�����Ƿ���ʹ��
				String checkPjxzdmForPjxm = service.checkXzForPjxm(myForm.getXmxzdm());
				if(!checkPjxzdmForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkPjxzdmForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				if(!checkPjxzdmForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkPjxzdmForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�ֱ�ӱ���
				myForm.setXmxzmc(myForm.getXmxzmc().trim());
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//�ж����������Ƿ����
			myForm.setXmxzmc(myForm.getXmxzmc().trim());
			boolean isExist = service.isExistByXmxzdm(myForm, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
				return null;
			}
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("updatePjxzdm");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-21 ����02:19:16
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ����ɾ��-VALUES��{values}")
	public ActionForward delPjxzdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjxzdmService service = new PjxzdmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//����������������Ƿ���ʹ��
			String checkPjxzdmForPjjg = service.checkXzForPjjg(values);
			//����������Ŀ�����Ƿ���ʹ��
			String checkPjxzdmForPjxm = service.checkXzForPjxm(values);
			if(!checkPjxzdmForPjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkPjxzdmForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			if(!checkPjxzdmForPjxm.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkPjxzdmForPjjg);
				response.getWriter().print(getJsonMessage(message));
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
