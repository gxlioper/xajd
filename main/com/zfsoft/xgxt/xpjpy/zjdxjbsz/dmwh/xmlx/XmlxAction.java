/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:39:56 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

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
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ�Meng.Wei[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:39:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmlxAction extends SuperAction{
	private static final String url = "xpjpy_dmwh.do";
	
	/**
	 * @����: ��ѯ���ݲ�����ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����07:28:14
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
	public ActionForward listXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xpjpy_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("listXmlx");
	}
	
	/**
	 * @����: ����ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����07:27:21
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
	public ActionForward addXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		/*����path*/
		String path = "xpjpy_dmwh.do?method=addXmlx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if (model != null) {
			BeanUtils.copyProperties(model, StringUtils.formatData(model));
		}
		return mapping.findForward("addXmlx");
	}
	
	/** 
	 * @����: ���ӱ��淽��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����07:31:36
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
	@SystemLog(description="��������������-��������-����ά��-������Ŀ��������LXMC:{lxmc} ")
	public ActionForward saveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		
		boolean isExist = service.isExistLxmc(model);
		
		if(!isExist){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
		}
		return null;
	}
	
	/**
	 * @����: �޸ķ���ҳ��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����11:02:50
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
	public ActionForward updateXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		XmlxForm myForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("updateXmlx");
	}
	
	/**
	 * @����: �޸ı��淽��
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-10 ����11:19:36
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ�����޸�-LXDM��{lxdm},LXMC:{lxmc}")
	public ActionForward saveForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmlxForm model = (XmlxForm) form;
		XmlxService service = new XmlxService();
		XmlxForm myForm = service.getModel(model);
			//�ж���û�޸���Ŀ��������
			if(!model.getLxmc().trim().equals(myForm.getLxmc().trim())){
				//�ж���������⵱���Ƿ���ʹ��
				String checkLxmcForPjjg=service.checkLxForPjjg(model.getLxdm());
				//�ж�������Ŀ�����Ƿ���ʹ��
				String checkLxmcForPjxm=service.checkLxForPjxm(model.getLxdm());
				if(!checkLxmcForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkLxmcForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				if(!checkLxmcForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkLxmcForPjxm);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�����ֱ�ӱ���
				model.setLxmc(model.getLxmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			model.setLxmc(model.getLxmc().trim());
			boolean isExist=service.isExistLxmc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMLX_LXEXIST));
			}
		return null;
	}
	
	/**
	 * @����: ɾ������
	 * @���ߣ� Meng.Wei[����:1186]
	 * @���ڣ�2017-3-9 ����08:10:03
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
	@SystemLog(description="������������-��������-����ά��-��Ŀ����ɾ��-VALUES��{values}")
	public ActionForward delXmlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmlxService service = new XmlxService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�ж���������⵱���Ƿ���ʹ��
			String checkLxmcForPjjg = service.checkLxForPjjg(values);
			//�ж�������Ŀ�����Ƿ���ʹ��
			String checkLxmcForPjxm = service.checkLxForPjxm(values);
			if(!checkLxmcForPjjg.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkLxmcForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkLxmcForPjxm.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkLxmcForPjxm);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num)
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else{
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
