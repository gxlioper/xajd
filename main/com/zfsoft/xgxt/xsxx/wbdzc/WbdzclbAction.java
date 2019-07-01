/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-8 ����10:56:35 
 */  
package com.zfsoft.xgxt.xsxx.wbdzc;
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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;


		/**
		 * @ϵͳ����: ѧ����������ϵͳ
		 * @ģ������: ѧ����Ϣ--����ע�ᣨ��ʦ��--δ����ע�����
		 * @�๦������:  
		 * @���ߣ� ����[����:1186]
		 * @ʱ�䣺 2016-3-16 ����09:28:46 
		 * @�汾�� V1.0
		 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
		 */
		public class WbdzclbAction extends SuperAction {
			private static final String url = "xsxx_wbdlb.do";
		
		
		/**
		 * @����:	δ����ע�����Ͳ�ѯҳ��
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:29:42
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
		public ActionForward wbdzclbManage (ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response)throws Exception {
			WbdzclbForm model = ( WbdzclbForm ) form;
			WbdzclbService service = new WbdzclbService();
			if (QUERY.equals(model.getType())){
				//��ѯ��¼
				List<HashMap<String,String>> resultList = service.getPageList(model);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
			}
			String path = "xsxx_wbdlb.do";
			request.setAttribute("path", path);
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("WbdzclbManage");
		}

		
		/**
		 * @����:	����
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:35:11
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
		@SystemLog(description = "����ѧ����Ϣ-����ע��-δ����ע�����-����Wbdlbmc:{wbdlbmc}")
		public ActionForward addWbdzclb(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			WbdzclbForm model = (WbdzclbForm) form;
			WbdzclbService service = new WbdzclbService();		
			if (SAVE.equalsIgnoreCase(model.getType())){
				model.setWbdlbmc(StringUtil.trim(model.getWbdlbmc()));
				boolean result = service.saveLxInfo(model, "add");
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				
				return null;
			}
			String wbdlbdm = service.changeWbdlbdm();
			model.setWbdlbdm(wbdlbdm);
			
			request.setAttribute("model", StringUtils.formatData(model));
			return mapping.findForward("addWbdzclb");
		}
		
		
		/**
		 * @����:	�޸�
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:36:00
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
		@SystemLog(description = "����ѧ����Ϣ-����ע��-δ����ע�����-�޸�Wbdlbdm:{wbdlbdm},Wbdlbmc:{wbdlbmc}")
		public ActionForward updateWbdzclb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			WbdzclbForm myForm = (WbdzclbForm) form;
			WbdzclbService service = new WbdzclbService();		
			WbdzclbForm model = service.getModel(myForm);
			if (UPDATE.equalsIgnoreCase(myForm.getType())){	
				//�ж���û��δ������������
				if(!myForm.getWbdlbmc().trim().equals(model.getWbdlbmc().trim())){
					String wbdlbmcpd = service.pdsfsy(myForm.getWbdlbdm());
					if(!wbdlbmcpd.trim().equals("")){
						String message=MessageUtil.getText(MessageKey.XSXX_WBDZC_UPDATE,wbdlbmcpd);
						response.getWriter().print(getJsonMessage(message));
						return null;
					}
				}else{
					//û��δ�����������ֱ�ӱ���
					myForm.setWbdlbmc(model.getWbdlbmc().trim());
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}
				//�ж�δ������������Ƿ����
				boolean isExist = service.isExistByWbdlbdm(myForm, UPDATE);
				if(!isExist){
					boolean result = service.runUpdate(myForm);
					String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
					response.getWriter().print(getJsonMessageByKey(messageKey));
					return null;
				}else{
					response.getWriter().print(getJsonMessageByKey(MessageKey.XSXX_WBDZC_WBDLBMC));
					return null;
				}
			}
			request.setAttribute("model", model);
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));			
			return mapping.findForward("updateWbdzclb");
		}
		
		
		/**
		 * @����:	ɾ��
		 * @���ߣ�	����[���ţ�1186]
		 * @���ڣ�	2016-3-16 ����09:37:48
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
		@SystemLog(description = "����ѧ����Ϣ-����ע��-δ����ע�����-ɾ��VALUES:{values}")
		public ActionForward delWbdzclb (ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
		throws Exception {
			WbdzclbService service = new WbdzclbService();
			String values = request.getParameter("values");
			if(!StringUtil.isNull(values)){
				//�жϷ��Ž�������Ƿ���ʹ��
				String pdsfsy = service.pdsfsy(values);
				if(!pdsfsy.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,pdsfsy);
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
