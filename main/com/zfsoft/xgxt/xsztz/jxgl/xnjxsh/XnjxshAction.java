/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-27 ����05:03:47 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxsh;

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
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqForm;
import com.zfsoft.xgxt.xsztz.jxgl.xnjxsq.XnjxsqService;
import com.zfsoft.xgxt.xsztz.xmsbgl.xmsb.XmsbService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-27 ����05:03:47 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnjxshAction extends SuperAction<XnjxshForm, XnjxshService>{
	private  XmsbService xmsbService = new  XmsbService();
	private XnjxshService service = new XnjxshService();
	private XnjxsqService xnjxsqService = new XnjxsqService();
	private final String TZXMSQ ="tzxmsq";
	
	private static final String url = "sztz_jxgl_xnjxsh.do";
	
	/** 
	 * @����:��˲�ѯ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-27 ����05:06:02
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
		XnjxshForm model = (XnjxshForm) form;
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
		String path = "sztz_jxgl_xnjxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getSbshList");
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����08:49:00
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
		XnjxshForm model = (XnjxshForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if (!StringUtil.isNull(model.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(TZXMSQ);
		request.setAttribute("jbxxList", jbxxList);
		//��ѯ��Ŀ������Ϣ
		List<HashMap<String,String>> xmjxList = xmsbService.getXmjxList(model.getXmdm());
		request.setAttribute("xmjxList", xmjxList);
		HashMap<String,String> xmxxMap = xmsbService.getXmxx(model.getXmdm());
		model.setShid(request.getParameter("shid"));
		request.setAttribute("shid", model.getShid());
		XnjxsqForm viewform = xnjxsqService.getModel(request.getParameter("sqid"));
		if(null!=viewform.getSfqq() && !"".equals(viewform.getSfqq())){
			viewform.setSfqq("��");
		}else{
			viewform.setSfqq("��");
		}
		request.setAttribute("zf", request.getParameter("zf"));
		request.setAttribute("jxmc",service.getJxmc(request.getParameter("jxid")));
		request.setAttribute("viewform", viewform);
		request.setAttribute("rs", StringUtils.formatData(xmxxMap));
		return mapping.findForward("sbDgsh");
	}
	
	/** 
	 * @����:�������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����12:24:08
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
		XnjxshForm model = (XnjxshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("sbPlsh");
	}
	
	
	/** 
	 * @����:��˳���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-1-28 ����02:12:06
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XnjxshForm model = (XnjxshForm) form;
		String xmdm = request.getParameter("xmdm");
		String shzt = request.getParameter("shzt");
		String id = request.getParameter("id");
		model.setId(id);
		model.setShzt(shzt);
		model.setXmdm(xmdm);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
