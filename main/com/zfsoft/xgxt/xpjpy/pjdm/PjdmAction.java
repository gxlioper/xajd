/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-16 ����09:07:19 
 */  
package com.zfsoft.xgxt.xpjpy.pjdm;

import java.util.ArrayList;
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
 * @ģ������: ��������_����ά������Ŀ���ͺ����ʣ�
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-16 ����09:07:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjdmAction extends SuperAction{
	
	private static final String urlJxj = "xpj_pjdm.do?method=xmlxList&xmxz=1&sindex=0";
	private static final String urlBz = "xpj_pjdm.do?method=xmlxList&xmxz=2&sindex=1";
	private static final String url="pj_dmwh.do";
	
	/**
	 * 
	 * ��Ŀ���ʹ���鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-16 ����11:08:51
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
	@SystemAuth(url = {urlJxj,urlBz})
	public ActionForward xmlxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		String xmxz = request.getParameter("xmxz");
		request.setAttribute("xmxz",xmxz);
		
		if (QUERY.equals(model.getType())){
			 xmxz = request.getParameter("xmxz");
			 model.setXmxz(xmxz);
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "pj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xmlxList");
	}
	
	/**
	 * 
	 * @����:��Ŀ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-16 ����11:23:35
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ��������XMLXMC:{xmlxmc} ")
	public ActionForward addXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж����ʹ���������Ƿ����
			model.setXmlxmc(model.getXmlxmc().trim());
			boolean isExist=service.isExistByXmlxmc(model, SAVE); 
			if(!isExist){
				int nextXmlxdm=service.getNextXmlxdm();
				model.setXmlxdm(nextXmlxdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
				return null;
				
			}
		}
		
		return mapping.findForward("addXmlx");
	}
	
	/**
	 * 
	 * @����:��Ŀ�����޸�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-16 ����05:33:34
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ�����޸�-XMLXDM��{xmlxdm},XMLXMC:{xmlxmc}")
	public ActionForward updateXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmModel model = (PjdmModel) form;
		PjdmService service = new PjdmService();
		PjdmModel myForm = service.getModel(model);
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//�ж���û�޸���Ŀ��������
			if(!model.getXmlxmc().trim().equals(myForm.getXmlxmc().trim())){
				//�ж���������⵱���Ƿ���ʹ��
				String checkXmlxForPjjg=service.checkLxForPjjg(model.getXmlxdm());
				//�ж�������Ŀ�����Ƿ���ʹ��
				String checkXmlxForPjxm=service.checkLxForPjxm(model.getXmlxdm());
				
				if(!checkXmlxForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkXmlxForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
				if(!checkXmlxForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkXmlxForPjxm);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�����ֱ�ӱ���
				model.setXmlxmc(model.getXmlxmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			model.setXmlxmc(model.getXmlxmc().trim());
			boolean isExist=service.isExistByXmlxmc(model, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateXmlx");
		
	}
	
	
	/**
	 * 
	 * @����:��Ŀ����ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-19 ����07:53:26
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
	public ActionForward delXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PjdmService service = new PjdmService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�ж���������⵱���Ƿ���ʹ��
			String checkXmlxForPjjg=service.checkLxForPjjg(values);
			//�ж�������Ŀ�����Ƿ���ʹ��
			String checkXmlxForPjxm=service.checkLxForPjxm(values);
			if(!checkXmlxForPjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkXmlxForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkXmlxForPjxm.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkXmlxForPjxm);
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
