/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-29 ����08:58:13 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
 * @�๦������: �����Ŀ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-5-29 ����08:58:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcxmAction extends SuperAction<JcxmModel, JcxmService> {

	private static final String url = "gygl_wsjc_jcxm.do";
	
	/**�������--��Ŀ�б�**/
	@SystemAuth(url = url)
	public ActionForward jcxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setAttribute("path", "gygl_wsjc_jcxm.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("jcxmList");
	}
	
	
	/**�������--��Ŀ�б�**/
	@SystemAuth(url = url)
	public ActionForward getJcxmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JcxmService service = getService();
		JcxmModel model = (JcxmModel) form;
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	
	/**�������--����**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("add");
	}
	
	
	/**�������--�޸�**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JcxmService service = getService();
		JcxmModel myForm = (JcxmModel) form;
		
		JcxmModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("edit");
	}
	
	
	/**ɾ�������Ŀ**/
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "���ʹ�Ԣ����-�������-�����Ŀ-ɾ��XMDM:{ids}")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("ids");
		JcxmService service = getService();
		
		if (!StringUtil.isNull(ids)){
			int num = service.runDelete(ids.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.XYJD_DELETE_DMEXIST,"�����Ŀ");
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
	
	//����
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcxmModel myForm = (JcxmModel) form;
		JcxmService service = getService();
		if(service.isExist(myForm)){
			JSONObject message = getJsonMessageByKey(MessageKey.GYGL_WSJC_JCXM_REPEAT);
			response.getWriter().print(message);		
			return null;
		}else{			
			boolean isSuccess = service.runInsert(myForm);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
			return null;
		}
	}
	
	//�޸ı���
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcxmModel myForm = (JcxmModel) form;
		JcxmService service = getService();
		if(service.isExist(myForm)){
			JSONObject message = getJsonMessageByKey(MessageKey.GYGL_WSJC_JCXM_REPEAT);
			response.getWriter().print(message);		
			return null;
		}else{			
			boolean isSuccess = service.runUpdate(myForm);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			JSONObject message = getJsonMessageByKey(messageKey);
			response.getWriter().print(message);
			return null;
		}
	}
}
