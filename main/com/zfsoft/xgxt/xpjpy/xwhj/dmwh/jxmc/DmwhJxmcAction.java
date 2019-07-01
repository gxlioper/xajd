/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-20 ����04:49:57 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxmc;

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
import com.zfsoft.xgxt.xpjpy.xwhj.dmwh.jxdj.DmwhJxdjService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: ����ά��-��������
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-20 ����04:49:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhJxmcAction extends SuperAction {
	
	private static final String url = "pjpy_hjgl_dmwh.do";
	
	/**
	 * 
	 * @����: �鿴�����LIST
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-20 ����05:38:09
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
	public ActionForward jxmcList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxmcForm model = (DmwhJxmcForm) form;
		DmwhJxmcService service = new DmwhJxmcService();
		
		if(QUERY.equals(model.getType())) {
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pjpy_hjgl_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jxmcList");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����10:23:30
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
	public ActionForward addJxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxmcForm model = (DmwhJxmcForm) form;
		DmwhJxmcService service = new DmwhJxmcService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ظ��ж�
			boolean isExist=service.isExist(model, SAVE);
			if(!isExist){
				boolean result = service.save(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				String messageKey = "�ý��������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;			
			}	
		}
		
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		
		return mapping.findForward("addJxmc");
	}
	
	/**
	 * 
	 * @����: ��̬ȡֵ(����ȼ�)
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����01:22:08
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
	public ActionForward getJxdjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhJxmcForm model = (DmwhJxmcForm) form;
		DmwhJxmcService service = new DmwhJxmcService();
		JSONArray json = JSONArray.fromObject(service.getJxdjList(model.getJxlbdm(), model.getJsfs())); 
		response.getWriter().print(json);
		return null;	
	}
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����01:59:06
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
	public ActionForward updateJxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxmcForm model = (DmwhJxmcForm) form;
		DmwhJxmcService service = new DmwhJxmcService();
		DmwhJxmcForm myForm = service.getModel(model);
		if(UPDATE.equalsIgnoreCase(model.getType())) {
			if(model.getJxmcmc().trim().equals(myForm.getJxmcmc().trim()) && model.getJxlbdm().trim().equals(myForm.getJxlbdm().trim()) && model.getJxdjdm().trim().equals(myForm.getJxdjdm().trim()) && model.getJsfs().trim().equals(myForm.getJsfs().trim())) {
				model.setJxmcmc(myForm.getJxmcmc().trim());
				model.setJxlbdm(myForm.getJxlbdm().trim());
				model.setJxdjdm(myForm.getJxdjdm().trim());
				model.setJsfs(myForm.getJsfs().trim());
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
				String messageKey = "�ý��������Ѵ��ڣ�";
 				response.getWriter().print(getJsonMessage(messageKey));
 				return null;
			  
			}			
		}
		DmwhJxdjService djService = new DmwhJxdjService();
		request.setAttribute("jxlbList", djService.getJxlbList());
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("rs", StringUtils.formatData(model));
		
		return mapping.findForward("updateJxmc");
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-21 ����02:29:56
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
	public ActionForward delJxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DmwhJxmcService service = new DmwhJxmcService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
			String checkSq = service.checkSq(values);
			String checkJg = service.checkJg(values);
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
