/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-6 ����11:23:08 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxlb;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-�������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-6 ����11:23:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxlbAction extends SuperAction {
	
	private static final String url = "pjpy_hjgl_dmwh.do";
	
	/**
	 * 
	 * @����: �鿴�����LIST
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-7 ����04:21:00
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
	public ActionForward jxlbList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		
		if(QUERY.equals(model.getType())) {
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pjpy_hjgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jxlbList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����02:29:36
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
	public ActionForward addJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ظ��ж�
			boolean isExist=service.isExist(model, SAVE);
			if(!isExist){
				boolean result = service.save(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String messageKey = "�ý�����������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;			
			}	
		}
		return mapping.findForward("addJxlb");
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-11 ����06:11:46
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
	public ActionForward updateJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbForm model = (DmwhJxlbForm) form;
		DmwhJxlbService service = new DmwhJxlbService();
		DmwhJxlbForm myForm	= service.getModel(model);
		
		if(UPDATE.equalsIgnoreCase(model.getType())) {
			if(model.getJxlbmc().trim().equals(myForm.getJxlbmc().trim())){
				model.setJxlbmc(myForm.getJxlbmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExist(model, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String messageKey = "�ý�����������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			  
			}	
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateJxlb");
		
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-13 ����04:38:03
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
	public ActionForward delJxlb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxlbService service = new DmwhJxlbService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
			String checkSq = service.checkSq(values);
			String checkJg = service.checkJg(values);
			String checkJxdj = service.checkJxdj(values);
			String checkJxmc = service.checkJxmc(values);
			if(!checkSq.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_SQ,checkSq);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JG,checkJg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJxdj.trim().equals("")) {
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JXDJ,checkJxdj);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkJxmc.trim().equals("")) {
				String message=MessageUtil.getText(MessageKey.PJPY_XWHJ_DMWH_EXIST_JXMC,checkJxmc);
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
