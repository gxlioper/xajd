/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-26 ����04:59:47 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.pfz;

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
import xgxt.form.User;
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
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ý������_������
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-2-26 ����04:59:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PfzAction extends SuperAction<PfzForm, PfzService> {
	
	
	private PfzService service = new PfzService(); 
	private static final String url = "cjWsf_pfz.do";  //��ý������
	
	
	/**
	 * 
	 * @����:��ѯ������List
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-26 ����03:22:45
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
	public ActionForward getPfzlList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		PfzForm model = (PfzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(
					model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "cjWsf_pfz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getPfzlList");
	}
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-29 ����06:46:02
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
	public ActionForward addPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("ssxqList", new OptionUtil().getOptions("ssxq"));
		return mapping.findForward("addPfz");
	}
	
	
	/**
	 * 
	 * @����:����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-29 ����07:26:35
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
	@SystemLog(description = "����������顪������-���ӻ��޸ı���PFZMC:{pfzmc},SSXQ:{ssxq},pfzdm:{pfzdm}")
	public ActionForward savePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm myForm = (PfzForm) form;
		myForm.setPfzmc(StringUtil.trim(myForm.getPfzmc()));
		if (service.isHave(myForm)) {
			String message = MessageUtil
					.getText(MessageKey.ZJCM_WSJC_PFZ_REPEAT);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		boolean result = service.editPfz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:�޸�������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����04:16:31
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
	public ActionForward updatePfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm myForm = (PfzForm) form;
		PfzForm pfzForm = service.getModel(myForm);
		if (null != pfzForm) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(pfzForm));
			request.setAttribute("pfzForm", pfzForm);
		}
		request.setAttribute("pfzid", myForm.getPfzid());
		request.setAttribute("ssxqList", new OptionUtil().getOptions("ssxq"));
		return mapping.findForward("updatePfz");
	}
	
	
	/**
	 * 
	 * @����:ɾ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-2-29 ����07:37:52
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
	@SystemLog(description = "����������顪������-ɾ��VALUES:{values}")
	public ActionForward delPfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			//�ж��������Ƿ�ʹ��
			if (service.isUsed(values)) {
				String message = MessageUtil.getText(MessageKey.ZJCM_WSJC_PFZ_DFGZYSY);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			//ɾ����Ӧ�����ֳ�Ա(�ݲ����Ƿ�����Ϣ)��
			service.delPfcy(ids);
			
			String message = num>0 ? MessageUtil.getText(
					MessageKey.SYS_DEL_SUCCESS) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @����:���ֳ�Ա�б�
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:19:40
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
	public ActionForward showPfcyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm myForm = (PfzForm) form;
		if (QUERY.equals(myForm.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPfcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		request.setAttribute("path", "cjWsfPfz.do?method=showPfcyList");
		request.setAttribute("zxxMap", service.getZxx(myForm.getPfzid()));
		request.setAttribute("pfzid", myForm.getPfzid());
		return mapping.findForward("showPfcyList");
	}
	
	
	/**
	 * 
	 * @����:�������ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:30:28
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
	public ActionForward savePfcy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm model = (PfzForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfcy(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}

	/**
	 * 
	 * @����:ȡ�����ֳ�Ա
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����10:31:26
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
	public ActionForward savePfcyQx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PfzForm model = (PfzForm) form;
		boolean result=true;
		String value = request.getParameter("values");
		result=service.savePfcyQxFp(model,value);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:���ֶ���鿴
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-3-1 ����03:04:29
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
	public ActionForward viewPfzList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfzForm model = (PfzForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPfzList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path =null;
		
		path = "cjWsfPfz.do?method=showPfcyList";
		
		request.setAttribute("path", path);
		request.setAttribute("pfzid", model.getPfzid());
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPfzList");
	}


}
