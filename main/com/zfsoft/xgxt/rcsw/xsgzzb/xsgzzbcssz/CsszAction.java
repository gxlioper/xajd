/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-10 ����04:56:15 
 */  
package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl.RylbglForm;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-11-10 ����04:56:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszAction extends SuperAction<CsszForm, CsszService> {
	private CsszService service = new CsszService();
	private static final String url = "rcsw_xsgzzb_cssz.do";
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-11 ����09:55:03
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
	public ActionForward getCsszList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		if(QUERY.equalsIgnoreCase(model.getType())){
			String wjlxmc = request.getParameter("wjlxmc"); 
			wjlxmc = URLDecoder.decode(URLDecoder.decode(wjlxmc,"UTF-8"),"UTF-8");
			model.setWjlxmc(wjlxmc);
			List<HashMap<String, String>> resultList = service.getPageList(model);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", "rcsw_xsgzzb_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszlist");
	}
	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-11 ����09:58:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2015-11-11 ����09:54:07
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
	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		CsszForm myform = service.getModel(model);
		request.setAttribute("wjlxdm", model.getWjlxdm());
		request.setAttribute("wjlxmc", myform.getWjlxmc());
		return mapping.findForward("edit");
	}
	
	//ɾ��
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-��������-ɾ��VALUES:{values}")
	public ActionForward delWjlx(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String[] wjlxdms = request.getParameter("values").split(",");
		boolean isUsing = false;
		if (!StringUtil.isNull(request.getParameter("values"))) {
			for(int i=0;i<wjlxdms.length;i++){
				//�жϵ�ǰҪɾ������Ա����Ƿ���ҵ����б�ʹ��
				isUsing = service.isExistsWjlxmc_user(wjlxdms[i]);
				//���������״̬��ֱ�����������Ϣ
				if(isUsing){
					String rylbmc = service.getWjlxmc(wjlxdms[i]);
					String message = MessageUtil.getText(
							MessageKey.RCSW_XSGZZB_WJLX_USED, rylbmc);
					response.getWriter().print(getJsonMessage(message));
					return null;
				}
			}
			int num = service.runDelete(wjlxdms);
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
	
	//���ӱ���
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-��������-����WJLXMC:{wjlxmc}")
	public ActionForward saveNewWjlx(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		//��֤���ӵ������Ƿ���������ݿ���
		model.setWjlxmc(model.getWjlxmc().trim());
		boolean isExist = service.isExistsSameWjlxmc(model.getWjlxmc(), null);
		//�������ֱ�ӷ��ش�����Ϣ
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSGZZB_WJLX_REPEAT));
			return null;
		}
		boolean result = service.saveWjlx(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	//�޸ı���
	@SystemLog(description = "�����ճ�����-ѧ�������ܱ���-��������-�޸�WJLXDM:{wjlxdm},WJLXMC:{wjlxmc}")
	public ActionForward saveWjlx_update(ActionMapping map,ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		CsszForm model = (CsszForm)form;
		//��֤�޸ĵ������Ƿ���������ݿ���
		model.setWjlxmc(model.getWjlxmc().trim());
		boolean isExist = service.isExistsSameWjlxmc(model.getWjlxmc(), model.getWjlxdm());
		//�������ֱ�ӷ��ش�����Ϣ
		if(isExist){
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSGZZB_WJLX_REPEAT));
			return null;
		}
		boolean result = service.runUpdate(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
