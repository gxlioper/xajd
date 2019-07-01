/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-9 ����09:43:00 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ����ά��_��Ŀ����
 * @���ߣ�����[����:1186]
 * @ʱ�䣺 2017-3-9 ����09:39:56 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmxzAction extends SuperAction{
	//private static final String url = "xpjpy_pjxzdm.do";
	
	/**
	 * @����: ��ѯ���ݲ�����ҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
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
	public ActionForward listXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmxzForm model = (XmxzForm) form;
		XmxzService service = new XmxzService();
		
		if (QUERY.equals(model.getType())){
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xpjpy_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("listXmxz");
	}
	
	/**
	 * @����: ����ҳ��
	 * @���ߣ�  Meng.Wei[���ţ�1186]
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
	public ActionForward addXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addXmxz");
	}
	
	/** 
	 * @����: ���ӱ��淽��
	 * @���ߣ�  Meng.Wei[���ţ�1186]
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
	@SystemLog(description="��������������-��������-����ά��-����������������XZMC:{xzmc} ")
	public ActionForward saveForAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxzForm model = (XmxzForm) form;
		XmxzService service = new XmxzService();
		
		model.setXzmc(model.getXzmc().trim());
		boolean isExist = service.isExistXzmc(model);
		
		if(!isExist){
			boolean result = service.runInsert(model);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
		}
		return null;
	}
	
	/**
	 * @����: �޸ķ���ҳ��
	 * @���ߣ� Meng.Wei[���ţ�1186]
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
	public ActionForward updateXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxzForm model = (XmxzForm) form;
		XmxzService service = new XmxzService();
		XmxzForm myForm = service.getModel(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("updateXmxz");
	}
	
	/**
	 * @����: �޸ı��淽��
	 * @���ߣ� Meng.Wei[���ţ�1186]
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
	@SystemLog(description="������������-��������-����ά��-������Ŀ�����޸�-XZDM��{xzdm},XZMC:{xzmc}")
	public ActionForward saveForUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmxzForm model = (XmxzForm) form;
		XmxzService service = new XmxzService();
		XmxzForm myForm = service.getModel(model);
			//�ж���û�޸���Ŀ��������
			if(!model.getXzmc().trim().equals(myForm.getXzmc().trim())){
				//�ж���������⵱���Ƿ���ʹ��
				String checkXzmcForPjjg = service.checkXzForPjjg(model.getXzdm());
				//�ж�������Ŀ�����Ƿ���ʹ��
				String checkXzmcForPjxm = service.checkXzForPjxm(model.getXzdm());
				if(!checkXzmcForPjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_UPDATE,checkXzmcForPjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				if(!checkXzmcForPjxm.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_UPDATE,checkXzmcForPjxm);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�����ֱ�ӱ���
				model.setXzmc(model.getXzmc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			model.setXzmc(model.getXzmc().trim());
			boolean isExist = service.isExistXzmc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_XMXZ_XZEXIST));
			}
		return null;
	}
	
	/**
	 * @����: ɾ������
	 * @���ߣ� Meng.Wei[���ţ�1186]
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
	@SystemLog(description="������������-��������-����ά��-��Ŀ����ɾ��-VALUES��{values}")
	public ActionForward delXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XmxzService service = new XmxzService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�ж���������⵱���Ƿ���ʹ��
			String checkXzmcForPjjg = service.checkXzForPjjg(values);
			//�ж�������Ŀ�����Ƿ���ʹ��
			String checkXzmcForPjxm = service.checkXzForPjxm(values);
			if(!checkXzmcForPjjg.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJJG_DELETE,checkXzmcForPjjg);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			if(!checkXzmcForPjxm.trim().equals("")){
				String message = MessageUtil.getText(MessageKey.PJPY_DMWH_EXIST_PJXM_DELETE,checkXzmcForPjxm);
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
