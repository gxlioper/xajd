package com.zfsoft.xgxt.jskp.xmsb;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.xmjg.XmjgService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ʵ����
 * @�๦������: ��Ŀ�걨 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-7-5 ����04:46:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JskpXmsbAction extends SuperAction<JskpXmsbForm,JskpXmsbService>{
	private JskpXmsbService service = new JskpXmsbService();
	private final String XSXX="xsxxgl";
	private  XmjgService xmjgService = new XmjgService();
	private static final String url = "jskp_xmsb.do";
	private static final String JGZQ_FLG="1";
	
	/**
	 * 
	 * @����:��Ŀ�걨�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-5 ����04:48:23
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
	public ActionForward getXmsbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
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
		String path = "jskp_xmsb.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsbList");
	}
	
	/**
	 * 
	 * @����:��Ŀ�걨
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-6 ����05:36:46
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
	@SystemLog(description = "��ʵ����-��Ŀ�걨-xmid:{xmid}")
	public ActionForward xmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
		HashMap<String,String> xmxxMap = xmjgService.getXmxx(model.getXmid());
		request.setAttribute("xmxx", xmxxMap);
		this.saveToken(request);
		return mapping.findForward("xmsb");
	}
	/**
	 * 
	 * @����:��֤�û��Ƿ���걨
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-24 ����02:52:21
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
	public ActionForward xmsbCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm model = (JskpXmsbForm) form;
		User user = getUser(request);
		String jgzqFlg = service.xmsbCheck(model,user);
		String message = jgzqFlg.equals(JGZQ_FLG) ? MessageUtil.getText(MessageKey.XG_JSKP_JGZQ_REPEAT)
				: null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", message);
		map.put("jgzqFlg", jgzqFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}

	/**
	 * 
	 * @����:�걨����
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����09:28:37
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
	@SystemLog(description = "��ʵ����-��Ŀ�걨-�걨����-xmid:{xmid}")
	public ActionForward saveXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		JskpXmsbForm model = (JskpXmsbForm) form;
		User user = getUser(request);
		model.setXh(user.getUserName());
		boolean result = service.saveXmsb(model);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 * 
	 * @����:ɾ���걨
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-27 ����08:41:44
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
	@SystemLog(description = "��ʵ����-��Ŀ�걨-�걨ɾ��-xmid:{xmid}")
	public ActionForward delXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿���ɾ��
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			result=service.runDelete(sqid.split(","))>0;
		}
		String messageKey = result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * �걨�鿴
	 */
	public ActionForward viewXmsb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbForm myForm = (JskpXmsbForm) form;
		JskpXmsbForm model = service.getModel(myForm);
		if(null!=model){
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSXX);
		request.setAttribute("jbxxList", jbxxList);
		/*����������Ϊ0ʱ������б�ѧ�Ų鿴��xhLink��*/
		String sfsh = new CsszService().getSfsh();
		request.setAttribute("sfsh", sfsh);
		HashMap<String,String> ckxxMap = service.getXxck(model.getSqid());
		if("0".equals(sfsh)){
			request.setAttribute("rs", ckxxMap);
		}else{
			request.setAttribute("rs", StringUtils.formatData(model));
		}
		
		return mapping.findForward("viewXmsb");
	}

}
