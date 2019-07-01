/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-10 ����05:24:44 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
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
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: ��������ά��
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-10 ����05:24:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdclAction extends SuperAction {

	private static final String url = "daqdcl.do?method=daqdclList";
	
	/**
	 * 
	 * @����: ��������ά���б�
	 * @���ڣ�2014-4-23 ����02:45:08
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
	public ActionForward daqdclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		
		if (QUERY.equals(myForm.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			List<HashMap<String,String>> resultList = service.getPageList(myForm);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "daqdcl.do?method=daqdclList";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("daqdclList");
	}

	/**
	 * 
	 * @����: ���ӵ�������
	 * @���ڣ�2014-4-23 ����02:45:08
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
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥����ά��-����DAQDCL_MC:{daqdcl_mc}")
	public ActionForward addDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		if (SAVE.equalsIgnoreCase(myForm .getType())){
			String guid = UniqID.getInstance().getUniqIDHash();
			myForm.setDaqdcl_id(guid);
			boolean result = service.runInsert(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		return mapping.findForward("addDaqdcl");
	}
	/**
	 * 
	 * @����: �޸ĵ�������
	 * @���ڣ�2014-4-23 ����02:45:08
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
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥����ά��-�޸�DAQDCL_ID:{daqdcl_id},DAQDCL_MC:{daqdcl_mc}")
	public ActionForward updateDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		DaqdclForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		return mapping.findForward("updateDaqdcl");
	}
	
	/**
	 * ɾ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��������-�����嵥����ά��-ɾ��DAQDCLIDS:{daqdclIds}")
	public ActionForward delDaqdcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		
		String daqdclIds = request.getParameter("daqdclIds");
		
		if (!StringUtil.isNull(daqdclIds)){
			int num = service.runDelete(daqdclIds.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}

	/**
	 * �жϵ����嵥���������Ƿ��ظ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward resumeFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		DaqdclService service = new DaqdclService();
		DaqdclForm myForm = (DaqdclForm) form;
		String flag = "0";

		HashMap<String, String> daqdclInfo = service.getDaqdclByName(myForm);
		if(daqdclInfo!=null && daqdclInfo.size()>0){
			flag = "1";
		}
		
		response.getWriter().print(flag);
		return null;
	}
	
}
