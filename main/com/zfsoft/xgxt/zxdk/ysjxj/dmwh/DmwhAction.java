/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-28 ����09:11:13 
 */  
package com.zfsoft.xgxt.zxdk.ysjxj.dmwh;

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
 * @ģ������: ��ѧ����-Ժ�轱ѧ��-����ά��
 * @�๦������: TODO(����ά��) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-28 ����09:11:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DmwhAction extends SuperAction {
	private static final String url = "zxdk_ysjxj_dmwh.do";
	
	/**
	 * @����:TODO(�ʽ���Դ��תҳ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:16:39
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
	public ActionForward getDmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String path = "zxdk_ysjxj_dmwh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dmwhList");
	}
	/**
	 * @����:TODO(��ѯ����·��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����02:19:20
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
	public ActionForward dmwhQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		//��ѯ�����
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:TODO(����)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����03:56:46
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-����;��-����ZJLYMC:{zjlymc}")
	public ActionForward dmwhAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			model.setZjlymc(StringUtil.trim(model.getZjlymc()));
			//�ж��ʽ���Դ�����Ƿ����
			boolean isExist=service.isExistByZjlymc(model); 
			if(!isExist){
				//����ʽ���Դ������������ ��һ������
				int nextZjlydm = service.getNextZjlydm();
				model.setZjlydm(nextZjlydm+"");
				//����һ���µ��ʽ���Դ����
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		return mapping.findForward("dmwhAdd");
	}
	
	/**
	 * @����:TODO(�޸�)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����04:50:19
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
	@SystemLog(description="������ѧ����-Ժ�轱ѧ��-����ά��-�ʽ���Դ����-�޸�ZJLYDM:{zjlydm},ZJLYMC:{zjlymc}")
	public ActionForward dmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhForm model = (DmwhForm) form;
		DmwhService service = new DmwhService();
		DmwhForm myForm = service.getModel(model);
		if (UPDATE.equalsIgnoreCase(model.getType())){
			model.setZjlymc(StringUtil.trim(model.getZjlymc()));
			//�ж���û�޸��ʽ���Դ����
			if(!model.getZjlymc().trim().equals(myForm.getZjlymc().trim())){
				//�ж�Ժ�轱ѧ���������Ƿ���ʹ��
				String checkZjlymcForYsjxjjg = service.checkZjlymcForYsjxjjg(model.getZjlydm());
				if(!checkZjlymcForYsjxjjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkZjlymcForYsjxjjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}else{
				//û���޸�����ֱ�ӱ���
				model.setZjlymc(myForm.getZjlymc().trim());
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			//�ж��ʽ���Դ�����Ƿ����
			boolean isExist=service.isExistByZjlymc(model);
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		return mapping.findForward("dmwhUpdate");
	}
	
	/**
	 * @����:TODO(ɾ�����ж��Ƿ���Ժ�轱ѧ������Ӧ��)
	 * @���ߣ�����[���ţ�1186]
	 * @���ڣ�2016-7-28 ����03:14:57
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
	@SystemLog(description="������ѧ����-Ժ�轱ѧ��-����ά��-ɾ��VALUES:{values}")
	public ActionForward dmwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DmwhService service = new DmwhService();
		String values = request.getParameter("values");
		if(!StringUtil.isNull(values)){
			//�жϷ��Ž�������Ƿ���ʹ��
			String checkZjlymcForYsjxjjg = service.checkZjlymcForYsjxjjg(values);
			if(!checkZjlymcForYsjxjjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkZjlymcForYsjxjjg);
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
