/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-20 ����09:29:00 
 */  
package com.zfsoft.xgxt.xpjpy.tsxsdm;

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
 * @ģ������: XXXX����ģ��
 * @�๦������: ����ѧ������ά�� 
 * @���ߣ�CQ [���ţ�785]
 * @ʱ�䣺 2013-8-20 ����09:29:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TsxsDmAction extends SuperAction{

	private static final String url = "xpj_tsxsdm.do?method=tsxsDmList&mklx=pjpy";
	
	/**
	 * ����ѧ������ά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsxsDmList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TsxsDmForm model = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		String mklx = request.getParameter("mklx");
		
		if (QUERY.equals(model.getType())){
			
			List<HashMap<String,String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "xpj_tsxsdm.do?method=tsxsDmList&mklx="+mklx;
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tsxsDmList");
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
	@SystemLog(description="������������-����ѧ��-����ά��-����-LXMC:{lxmc},LXSX:{lxsx}")
	public ActionForward addTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmForm model = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�жϵ��������Ƿ����
			boolean isExist=service.isExistByTsxsDm(model,SAVE);
			if(!isExist){
				int nextLxdm=service.getNextTsxsDm();
				model.setLxdm(nextLxdm+"");
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_TSXSDM_NAME_REPEAT));
				return null;
				
			}
		}
		
		return mapping.findForward("addTsxsDm");
	}
	
	/**
	 * 
	 * @����:�޸���ѧ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����02:03:10
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
	@SystemLog(description="������������-����ѧ��-����ά��-�޸�-LXDM��{lxdm},LXMC:{lxmc},LXSX:{lxsx}")
	public ActionForward updateTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmForm myForm = (TsxsDmForm) form;
		TsxsDmService service = new TsxsDmService();
		TsxsDmForm model = service.getModel(myForm);
		
		if (UPDATE.equalsIgnoreCase(myForm.getType())){
			//�ж���û�޸ĵ�������
			if(!myForm.getLxmc().trim().equals(model.getLxmc().trim())||!myForm.getLxsx().trim().equals(model.getLxsx().trim())){
				//��������ѧ�������Ƿ��б��޸ĵ�����
				String checkLxmcForTsxsb = service.checkDcForTsxsb(myForm.getLxdm());
				if(!checkLxmcForTsxsb.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.PJPY_TSXSDM_EXIST_TSXSB_UPDATE,checkLxmcForTsxsb);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸ĵ�������ֱ�ӱ���
				myForm.setLxmc(model.getLxmc().trim());
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
				
			}
			//�жϵ��������Ƿ����
			boolean isExist = service.isExistByTsxsDm(myForm, UPDATE);
			if(!isExist){
				boolean result = service.runUpdate(myForm);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.PJPY_TSXSDM_NAME_REPEAT));
				return null;
			  
			}
		}
		
		BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		
		return mapping.findForward("updateTsxsDm");
	}

	/**
	 * 
	 * @����:ɾ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2013-8-20 ����02:19:32
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
	@SystemLog(description="������������-����ѧ��-����ά��-ɾ��-VALUES��{values}")
	public ActionForward delTsxsDm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TsxsDmService service = new TsxsDmService();
		String values = request.getParameter("values");
		
		if (!StringUtil.isNull(values)){
			//��������ѧ��������Ƿ��б�ɾ��������
			String checkLxmcForTsxsb = service.checkDcForTsxsb(values);
			if(!checkLxmcForTsxsb.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.PJPY_TSXSDM_EXIST_TSXSB_DELETE,checkLxmcForTsxsb);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			
			int num = service.runDelete(values.split(","));
			boolean result =  num > 0;
			String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,num) 
									: MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
		
	}
	
	
	
}
