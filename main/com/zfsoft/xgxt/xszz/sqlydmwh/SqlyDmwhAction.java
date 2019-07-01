/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-21 ����09:45:49 
 */  
package com.zfsoft.xgxt.xszz.sqlydmwh;

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
 * @ģ������: ѧ������-�������϶�
 * @�๦������: �������ɴ���ά��  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-6-21 ����09:45:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SqlyDmwhAction extends SuperAction<SqlyDmwhForm, SqlyDmwhService> {
	
	private static final String url = "xszz_knsrd_sqlywh.do";
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����10:45:03
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
	public ActionForward sqlywhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "xszz_knsrd_sqlywh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("sqlywhManage");
	}
	
	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����11:21:15
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
	public ActionForward sqlywhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		
		//��ѯ�����
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		
		return null;	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����01:30:43
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
	public ActionForward addSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж��Ƿ����
			boolean isExist = service.isExistBySqlywh(model);
			if(!isExist){
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSRD_SQLYWH_REPEAT));
				return null;
			}
		}
		
		return mapping.findForward("addSqlywh");	
	}
	
	
	/**
	 * 
	 * @����: �޸�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����02:52:41
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
	public ActionForward updateSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhForm model = (SqlyDmwhForm) form;
		SqlyDmwhService service = new SqlyDmwhService();
		SqlyDmwhForm myForm = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			
			//�ж���û�޸�
			if(model.getSqlymc().trim().equals(myForm.getSqlymc().trim())){
				
				model.setSqlymc(myForm.getSqlymc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
			
			//�ж��Ƿ����
			boolean isExist = service.isExistBySqlywh(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.XSZZ_KNSRD_SQLYWH_REPEAT));
				return null;
			  
			}
			
		}
		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateSqlywh");	
	}
	
	/**
	 * 
	 * @����: ɾ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-6-21 ����03:14:52
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
	public ActionForward delSqlywh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SqlyDmwhService service = new SqlyDmwhService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			
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
