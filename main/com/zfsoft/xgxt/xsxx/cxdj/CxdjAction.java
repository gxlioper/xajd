/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-7-30 ����06:08:49 
 */  
package com.zfsoft.xgxt.xsxx.cxdj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xszz.knsdc.KnsdcService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(���еȼ�ά��) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-7-30 ����06:08:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdjAction extends SuperAction {
	
	private static final String url = "xsxx_gygl_cxcxdj.do?method=cxdjwhList";
	
	@SystemAuth(url = url)
	public ActionForward cxdjwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CxdjForm model = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xsxx_gygl_cxcxdj.do?method=cxdjwhList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("cxdjwhList");
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-���еȼ�ά��-����CXDJDM:{cxdjdm}")
	public ActionForward addCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxdjForm model = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ȼ������Ƿ����
			boolean isExist=service.isExist(model);
			boolean isExists = service.checkSameNameIsNotExists(null, model.getCxdjmc());
			if(isExist){
				response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
				return null;
			}else if(!isExists){
				response.getWriter().print(getJsonMessage("�����Ѵ��ڣ�"));
				return null;
			}else{
				
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
		}
		
		return mapping.findForward("addCxdj");
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-31 ����10:01:23
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-���еȼ�ά��-�޸�CXDJDM:{cxdjdm}")
	public ActionForward updateCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CxdjForm myForm = (CxdjForm) form;
		CxdjService service = new CxdjService();
		CxdjForm model=service.getModel(myForm);
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			boolean isExists = service.checkSameNameIsNotExists(myForm.getCxdjdm(), myForm.getCxdjmc());
			if(!isExists){
				response.getWriter().print(getJsonMessage("�����Ѵ��ڣ�"));
				return null;
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateCxdj");
	}
	
	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�cmj
	 * @���ڣ�2013-7-31 ����04:17:51
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
	@SystemLog(description="����ѧ����Ϣ-ѧ��������������-���еȼ�ά��-ɾ��VALUES:{values}")
	public ActionForward delCxdj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CxdjForm myForm = (CxdjForm) form;
		CxdjService service = new CxdjService();
		
		String values = request.getParameter("values");
		if(Base.xxdm.equals("12684")){
			if(service.isNowUsing(values.split(","))){
				String message = "��������ʹ���У�������ɾ����";
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
		}
		int num = service.runDelete(values.split(","));
		boolean result =  num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
								: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
}
