/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-29 ����09:06:17 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.sq;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsbjg.XmsbjgService;
import com.zfsoft.xgxt.xsztz.xntzjg.cssz.JcftzCsszService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2016-3-29 ����09:06:17 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzSqAction extends SuperAction<JcftzSqForm, JcftzSqService>{
	private static final String url = "sztz_jcftz_sq.do";
	private JcftzSqService service = new JcftzSqService();
	private JcftzCsszService csszService = new JcftzCsszService();
	private XmsbService xmsbService = new XmsbService();
	private XmsbjgService xmsbjgService = new XmsbjgService();
	
	/** 
	 * @����:��ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����09:14:48
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
	public ActionForward getJcftzSqList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
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
		String[] sqshkg = csszService.getSqShKg();
		request.setAttribute("sqkg", sqshkg == null ? "0" : sqshkg[0]);
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getJcftzSqList");
	}
	
	/** 
	 * @����:�϶�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����02:30:07
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
	public ActionForward renDing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // ��ѯ
				List<HashMap<String, String>> resultList = service.getPageListForRenDing(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("rs", service.getXmxx(model.getXmdm()));
		JSONArray jxList = JSONArray.fromObject(xmsbService.getXmjxList(model.getXmdm()));
		request.setAttribute("jxList", jxList);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("renDing");
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����02:32:37
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
	public ActionForward saveJcftzSq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		String csms = request.getParameter("csms");
		boolean result = true;
		if("1".equals(csms)){
			 result = service.saveJcftzSq(model);	
		}else if("2".equals(csms)){
			result = service.saveJcftzTtSq(model);
		}else{
			//�������ģʽΪ�ջ���Ϊ����ֵ��ֱ�ӷ�����ʾʧ����Ϣ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
			
		if (!result){
			//���ʧ�ܣ�����ʾ
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_FAIL));
		}
		return null;
	}
	
	/** 
	 * @����:�ύ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����04:58:21
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
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		User user = getUser(request);
		//��û���������������֣�Ĭ��Ϊԭ�л�����
		service.checkIsCanSubmit(model);
		String message = service.tj(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/** 
	 * @����:�鿴
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-29 ����04:32:33
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
	public ActionForward viewRs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		request.setAttribute("xmdm", model.getXmdm());
		return mapping.findForward("viewRs");
	}
	
	
	/** 
	 * @����:��ȡ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-30 ����04:19:07
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
	public ActionForward getFs(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		String jxdm = request.getParameter("jxdm");
		String data = service.getFs(jxdm);
		response.getWriter().print(data);
		return null;
	}
	
	/**
	 * 
	 * @����: �����϶���ڲ�ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-4 ����10:36:42
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
	public ActionForward TtrenDing(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcftzSqForm model = (JcftzSqForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())){
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			 User user = getUser(request);
			 // ��ѯ
				List<HashMap<String, String>> resultList = service.getPageListForTtRenDing(
						model, user);
				JSONArray dataList = JSONArray.fromObject(resultList);
				response.getWriter().print(dataList);
				return null;
		}	   
		String path = "sztz_jcftz_sq.do";
		request.setAttribute("path", path);
		request.setAttribute("xmdm", model.getXmdm());
		request.setAttribute("rs", service.getXmxx(model.getXmdm()));
		JSONArray jxList = JSONArray.fromObject(xmsbService.getXmjxList(model.getXmdm()));
		request.setAttribute("jxList", jxList);
		FormModleCommon.commonRequestSet(request);
		if("view".equals(request.getParameter("flag"))){
			return mapping.findForward("viewTts");
		}else{
			return mapping.findForward("TtrenDing");
		}
		
	}
	
	
	
	
	
}
