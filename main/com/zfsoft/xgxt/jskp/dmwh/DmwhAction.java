package com.zfsoft.xgxt.jskp.dmwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import common.newp.StringUtil;

public class DmwhAction extends SuperAction<DmwhForm, DmwhService> {
	private DmwhService service = new DmwhService();
	private static final String url = "pjpy_jskp_dmwh.do";//path·��
	/**
	 * 
	 * @����: ����ά����ҳ��ѯ��ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:02:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "���ʼ�ʵ����-����ά��")
	@SystemAuth(url = url)
	public ActionForward getDmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getdmwhlist");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:04:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward searchForDmwhList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DmwhForm model = (DmwhForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(model);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ���Ӵ���ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:07:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "���ʼ�ʵ����-����ά��-����")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("adddmwh");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �޸Ĵ���ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:09:56
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "���ʼ�ʵ����-����ά��-�޸�")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DmwhForm dmwh = (DmwhForm)form;
		DmwhForm model = service.getModel(dmwh);
		BeanUtils.copyProperties(dmwh, model);
		return mapping.findForward("editdmwh");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �������ά��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:12:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "��ʵ����-����ά��-����")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveDmwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DmwhForm dmwh = (DmwhForm)form;
		boolean rs = true;
		if(!service.checkMcIsNotUserd(dmwh.getXmlbmc(), dmwh.getXmlbdm())){
			throw new SystemException(MessageKey.GYGL_WSJC_JCXM_REPEAT);
		}
		if(StringUtils.isNotNull(dmwh.getXmlbdm())){
			rs = service.runUpdate(dmwh);
		}else{
			rs = service.runInsert(dmwh);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ɾ������(��������֤����)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����02:45:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemLog(description = "��ʵ����-����ά��-ɾ��")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward  delXm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			if(!service.checkIsDmIsNotUserd(ids)){
				response.getWriter().print(getJsonMessage(MessageUtil.getText(MessageKey.DTJS_ZZGXZC_DZBWH_USED,"����Ŀ������")));
				return null;
			}
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
}
