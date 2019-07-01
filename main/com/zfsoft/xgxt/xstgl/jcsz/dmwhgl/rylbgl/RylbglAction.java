/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-9 ����04:37:05 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ź���-����ά��-��Ա���ά�� 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-9-9 ����04:37:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RylbglAction extends SuperAction<RylbglForm, RylbglService> {
	private RylbglService service = new RylbglService();
	private static final String url = "stgl_jcsz_dmwh.do";
	//��ѯ
	@SystemAuth(url = url)
	public ActionForward getRylblist(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			String rylbmc = request.getParameter("rylbmc"); 
			rylbmc = URLDecoder.decode(URLDecoder.decode(rylbmc,"UTF-8"),"UTF-8");
			model.setRylbmc(rylbmc);
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		return null;
	}
	
	//����
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		return map.findForward("addRylb");
	}
	
	//���ӱ���
	public ActionForward saveNewRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		//��֤���ӵ������Ƿ���������ݿ���
		model.setRylbmc(model.getRylbmc().trim());
		boolean isExist = service.isExistsSameRylbmc(model.getRylbmc(), null);
		//�������ֱ�ӷ��ش�����Ϣ
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.STGL_JCSZ_RYLB_REPEAT));
			return null;
		}
		boolean result = service.save(model.getRylbmc());
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//�޸�
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		RylbglForm myform = service.getModel(model);
		request.setAttribute("rylbdm", model.getRylbdm());
		request.setAttribute("rylbmc", myform.getRylbmc());
		return map.findForward("updateRylb");
	}
	
	//�޸ı���
	public ActionForward saveRylb_update(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		RylbglForm model = (RylbglForm)form;
		//��֤�޸ĵ������Ƿ���������ݿ���
		model.setRylbmc(model.getRylbmc().trim());
		boolean isExist = service.isExistsSameRylbmc(model.getRylbmc(), model.getRylbdm());
		//�������ֱ�ӷ��ش�����Ϣ
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.STGL_JCSZ_RYLB_REPEAT));
			return null;
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//ɾ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward delRylb(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String[] rylbdms = request.getParameter("values").split(",");
		boolean isUsing = false;
		if (!StringUtil.isNull(request.getParameter("values"))) {
			for(int i=0;i<rylbdms.length;i++){
				//�жϵ�ǰҪɾ������Ա����Ƿ���ҵ����б�ʹ��
				isUsing = service.isExistsRylbmc_user(rylbdms[i]);
				//���������״̬��ֱ�����������Ϣ
				if(isUsing){
					String rylbmc = service.getRylbmc(rylbdms[i]);
					String message = MessageUtil.getText(
							MessageKey.STGL_JCSZ_RYLB_USED, rylbmc);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}
			int num = service.runDelete(rylbdms);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		}else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		
		return null;
	}
}
