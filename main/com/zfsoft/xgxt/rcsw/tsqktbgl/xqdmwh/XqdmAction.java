/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-15 ����03:09:05 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.xqdmwh;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-15 ����03:09:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqdmAction extends SuperAction<XqdmForm, XqdmService>{
	private static final String url = "rcsw_tsqktbgl_xqdmwh.do";
	private XqdmService service = new XqdmService();
	
	/** 
	 * @����:��ѯѧ�ڴ����б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����03:13:54
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
	public ActionForward getXqdmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqdmForm model = (XqdmForm) form;
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}		
		String path = "rcsw_tsqktbgl_xqdmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);		
		return mapping.findForward("getXqdmList");
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����03:36:58
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
	public ActionForward addXqdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqdmForm model = (XqdmForm) form;
		model.setXqmc(StringUtil.trim(model.getXqmc()));
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж����ʹ���������Ƿ����
			boolean isExist=service.checkExistForAdd(model); 
			if(!isExist){
				String xqdm = service.getMaxXqdm();
				model.setXqdm(xqdm);
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQDMWH_REPEATED));
				return null;
				
			}
		}		
		return mapping.findForward("addXqdm");
	}
	
	/** 
	 * @����:�޸�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����05:04:40
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
	public ActionForward updateXqdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
		XqdmForm model = (XqdmForm) form;
		XqdmForm myForm = service.getModel(model);
		if (UPDATE.equalsIgnoreCase(model.getType())){
			String oldxqdm = request.getParameter("oldxqdm");
			XqdmForm form2 = service.getModel(oldxqdm);
			model.setXqdm(StringUtil.trim(model.getXqdm()));
			model.setXqmc(StringUtil.trim(model.getXqmc()));	
			boolean isExist=service.checkExistForsqjg(form2);
			if(!isExist){				
				if(!service.checkExistForUpdate(model, oldxqdm)){
					boolean result = service.update(model, oldxqdm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQDMWH_REPEATED));
					return null;
				}
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQDMWH_SQJGEXIST));
				return null;
			}
		}		
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		request.setAttribute("oldxqdm", myForm.getXqdm());
		return mapping.findForward("updateXqdm");
	}
	
	/** 
	 * @����:ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-15 ����05:24:24
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
	public ActionForward delXqdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XqdmForm myForm = (XqdmForm) form;
		String values = request.getParameter("values");		
		if (!StringUtil.isNull(values)){
			String[] ids = values.split(",");
			for(String id : ids){
				myForm.setXqdm(StringUtil.trim(id));
				boolean isExist=service.checkExistForsqjg(myForm);
				if(isExist){
					response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQDMWH_SQJGEXIST));
					return null;
				}
			}
			int num = service.runDelete(ids);
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
	 * @����:�Ƿ���޸ĺ�ɾ��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-22 ����11:20:19
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
	public ActionForward isOperate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XqdmForm model = (XqdmForm) form;
		XqdmForm myForm = service.getModel(model);
		boolean isExist=service.checkExistForsqjg(myForm);
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XQDMWH_SQJGEXIST));
			return null;
		}
		return null;
	}
}
		

