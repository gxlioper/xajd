/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:35:56 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-��������
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-23 ����03:35:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdlxwhAction extends SuperAction{

	private static final String url = "xljk_jcsz_lxwh.do";
	
	/**
	 * 
	 * @����:��ѯ�������ͣ���ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-23 ����04:49:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//��ȡ����Ȩ��
		request.setAttribute("path", "xljk_jcsz_lxwh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryFdlxwh");
	}
	
	/**
	 * 
	 * @����:��ѯ��������
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-23 ����04:49:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
		
		//��ѯ
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����:��Ӹ������ͣ���ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����08:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addFdlx");
	}
	
	/**
	 * 
	 * @����:��Ӹ�������
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����09:10:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
			
		//�������ʹ����Ƿ����
		boolean isExist=service.fdlxIsExist(model);
		if(!isExist){
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
			return null;
		}
	}
	
	/**
	 * 
	 * @����:�޸ĸ������ͣ���ת��
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����10:20:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhForm model  = (FdlxwhForm) form;
		
		FdlxwhService service = new FdlxwhService();
		FdlxwhForm dataModel = service.getModel(model.getFdlxdm());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		return mapping.findForward("updateFdlx");
	}
	
	/**
	 * 
	 * @����:�޸ĸ�������
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24����10:32:39 
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:ɾ����������
	 * @���ߣ���־��[����:1060]
	 * @���ڣ�2014-4-24 ����08:53:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhService service = new FdlxwhService();
		
		String fdlxdms = request.getParameter("fdlxdms"); //��ɾ�������ʹ���
		
		int isSuccess = service.runDelete(fdlxdms.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
}
