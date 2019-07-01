/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:38:08 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.xmsbsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:38:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XmsbshAction extends SuperAction<XmsbshForm, XmsbshService>{
	private  XmsbService xmsbService = new  XmsbService();
	private XmsbshService service = new XmsbshService();
	
	private static final String url = "sztz_xmsbgl_xmsh.do";
	
	/**
	 * 
	 * @����:��ȡ����б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����09:23:10
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
	public ActionForward getSbshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbshForm model = (XmsbshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_xmsbgl_xmsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSbshList");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:17:03
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
	public ActionForward sbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbshForm model = (XmsbshForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//��ѯ��Ŀ������Ϣ
		List<HashMap<String,String>> xmjxList = xmsbService.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		HashMap<String,String> xmxxMap = xmsbService.getXmxx(model.getXmdm());
		// ������Ŀ����ѧԺ��Ϣ
		List<HashMap<String, String>> xyList = xmsbService.getCyxyListForView(model.getXmdm());
		request.setAttribute("xyList", xyList);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("rs", StringUtils.formatData(xmxxMap));
		return mapping.findForward("sbDgsh");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:07:50
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
	public ActionForward sbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbshForm model = (XmsbshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("sbPlsh");
	}
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-7-13 ����04:08:06
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XmsbshForm model = (XmsbshForm) form;
		String xmdm = request.getParameter("xmdm");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setXmdm(xmdm);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	

}
