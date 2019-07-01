/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-2 ����09:27:59 
 */  
package com.zfsoft.xgxt.rcsw.fyff.dmwh.ffxm;

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
 * @ģ������: �ճ�����-���÷���-��������ά��-������Ŀ
 * @�๦������: 
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-4-2 ����09:27:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FyffxmAction extends SuperAction{

	private static final String url = "rcsw_fyff_dmwh_ffxm.do";
	
	/**
	 * 
	 * @����:������Ŀҳ����ת
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:35:02
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
	public ActionForward viewFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = "rcsw_fyff_dmwh_ffxm.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("viewFfxm");
	}
	
	
	/**
	 * 
	 * @����:��ѯ������Ŀlist
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����09:42:14
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
	public ActionForward ffxmQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		
		//��ѯ�����
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
		
	}
	
	
	/**
	 * 
	 * @����:���ӷ�����Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����10:02:33
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-������Ŀ-����FFXMMC:{ffxmmc}")
	public ActionForward addFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){
			//�ж���Ŀ����������Ƿ����
			boolean isExist = service.isExistByFfxmmc(model);
			if(!isExist){
				//��÷���;������
				int nextFfxmdm = service.getNextFfxmdm();
				model.setFfxmdm(nextFfxmdm+"");
				//����һ���µķ���;������
				boolean result = service.runInsert(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_EXIST));
				return null;
			}
		}
		
		//Ĭ��ѡ�а��η���
		model.setFffs("1");
		
		return mapping.findForward("addFfxm");
	}
	
	
	/**
	 * 
	 * @����:�޸ķ�����Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����10:53:07
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-������Ŀ-�޸�FFXMDM:{ffxmdm},FFXMMC:{ffxmmc}")
	public ActionForward updateFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmForm model = (FyffxmForm) form;
		FyffxmService service = new FyffxmService();
		FyffxmForm myForm = service.getModel(model);
		
		
		if (UPDATE.equalsIgnoreCase(model.getType())){
			//�ж���û�޸ķ�����Ŀ����
			if(!model.getFfxmmc().trim().equals(myForm.getFfxmmc().trim())){
				//�жϷ��Ž�������Ƿ���ʹ��
				String checkFfxmForFfjg = service.checkFfxmdmForFfjg(model.getFfxmdm());
				
				if(!checkFfxmForFfjg.trim().equals("")){
					String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFfxmForFfjg);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
				
			}else{
				//û���޸�����ֱ�ӱ���
				model.setFfxmmc(myForm.getFfxmmc().trim());
				
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}
			
			boolean isExist=service.isExistByFfxmmc(model);
			
			if(!isExist){
				boolean result = service.runUpdate(model);
				String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
				response.getWriter().print(getJsonMessageByKey(messageKey));
				return null;
			}else{
				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_FYFF_DMWH_MCEXIST));
				return null;
			  
			}
		}
		BeanUtils.copyProperties(model, StringUtils.formatData(myForm));
		
		return mapping.findForward("updateFfxm");
		
	}
	
	
	/**
	 * 
	 * @����:ɾ��������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-4-3 ����11:37:53
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
	@SystemLog(description="�����ճ�����-���÷���-����ά��-������Ŀ-ɾ��VALUES:{values}")
	public ActionForward delFfxm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FyffxmService service = new FyffxmService();
		String values = request.getParameter("values");
		
		if(!StringUtil.isNull(values)){
			//�жϷ��Ž�������Ƿ���ʹ��
			String checkFfxmForFfjg = service.checkFfxmdmForFfjg(values);
			
			if(!checkFfxmForFfjg.trim().equals("")){
				String message=MessageUtil.getText(MessageKey.RCSW_FYFF_DMWH_MCEXIST,checkFfxmForFfjg);
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
