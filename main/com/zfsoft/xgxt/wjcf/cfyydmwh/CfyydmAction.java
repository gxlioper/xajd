/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-25 ����09:16:07 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����ԭ�����ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-25 ����09:07:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfyydmAction extends SuperAction {
	
	private static final String url = "wjcf_cfyydmwh.do?method=cxCfyydmList";
	
	@SystemAuth(url = url)
	public ActionForward cxCfyydmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfyydmForm myForm=(CfyydmForm)form;
		CfyydmService service=new CfyydmService();
		
		if (QUERY.equals(myForm.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "wjcf_cfyydmwh.do?method=cxCfyydmList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxCfyydmList");
	}
	/**
	 * 
	 * @����:(����ԭ������)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-25 ����09:56:00
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
	@SystemLog(description = "����Υ�ʹ���-��������-����ԭ�����ά��-����CFYYMC:{cfyymc}")
	public ActionForward cfyydmAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfyydmForm myForm=(CfyydmForm)form;
		CfyydmService service=new CfyydmService();
		if (SAVE.equals(myForm.getType())){
			if (!isTokenValid(request)){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
				return null;
			}

			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.WJCF_CFLBDM_CFYYCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			super.resetToken(request);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			myForm.setCjsj(sdf.format(new Date()));
			boolean result = service.runInsert(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		this.saveToken(request);
		return mapping.findForward("cfyydmAdd");
	}
	
	/**
	 * 
	 * @����:(����ԭ���޸�)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-25 ����09:56:00
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
	@SystemLog(description = "����Υ�ʹ���-��������-����ԭ�����ά��-�޸�CFYYDM:{cfyydm},CFYYMC:{cfyymc}")
	public ActionForward cfyydmUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfyydmForm myForm=(CfyydmForm)form;
		CfyydmService service=new CfyydmService();
		CfyydmForm model=new CfyydmForm();
		
		if (SAVE.equals(myForm.getType())){
			boolean flag=service.checkIsExist(myForm);
			if(flag){
				String messageKey=MessageKey.WJCF_CFLBDM_CFYYCZ;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey=result?MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		model=service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("cfyydmUpdate");
	}
	
	/**
	 * 
	 * @����:(����ԭ��ɾ��)
	 * @���ߣ�������[���ţ�913]
	 * @���ڣ�2013-10-25 ����09:56:00
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
	@SystemLog(description = "����Υ�ʹ���-��������-����ԭ�����ά��-ɾ��VALUES:{values}")
	public ActionForward cfyydmDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CfyydmService service=new CfyydmService();
		String values = request.getParameter("values");
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
				: MessageUtil.getText(MessageKey.WJCF_CFYYDM_CFYYYSY);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}
