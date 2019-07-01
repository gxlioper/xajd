/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��10�� ����8:40:46 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.xstgl.sthdgl.sthdsh.SthdshForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqService;

import common.newp.StringUtil;
import net.sf.json.JSONArray;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը�������Action
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��10�� ����8:40:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwShAction extends SuperAction<ZyfwShForm,ZyfwShService>{

	private final String ZYFW="zyfw";
	private ZyfwShService zyfwShService = new ZyfwShService();
	private static final String url = "xsxx_zyfwgl_sh.do?method=zyfwShList";
	
	/**
	 * @����:ת��־Ը��������б�ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����10:03:03
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
	public ActionForward zyfwShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path",url);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwShList");
	}
	
	/**
	 * @����:��ȡ־Ը��������б�JSON����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����10:03:03
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
	public ActionForward getZyfwShListData(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		zyfwShForm.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = zyfwShService.getPageList(zyfwShForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @����:��ת��־Ը���񵥸����ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����3:04:51
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
	public ActionForward zyfwShDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		ZyfwSqForm model = new ZyfwSqService().getModel(zyfwShForm.getFwid());
		if (!StringUtil.isNull(zyfwShForm.getXh())) {
			BeanUtils.copyProperties(zyfwShForm, StringUtils.formatData(model));
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(zyfwShForm.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(ZYFW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("zyfwShDgsh");
	}
	
	/**
	 * @����:����־Ը���񵥸����
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����3:11:11
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
	public ActionForward saveForDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		User user = getUser(request);
		// ���浥�����
		boolean result = zyfwShService.saveForDgsh(zyfwShForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @����:��ת��־Ը�����������ҳ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����3:04:51
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
	public ActionForward zyfwShPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		return mapping.findForward("zyfwShPlsh");
	}
	
	/**
	 * @����:����־Ը�����������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����3:12:22
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
	public ActionForward saveForPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		User user = getUser(request);
		String message = zyfwShService.saveForPlsh(zyfwShForm, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * @����:���һ���������
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��10�� ����3:04:51
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
	public ActionForward cancelShLast(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		ZyfwShForm zyfwShForm = (ZyfwShForm) form;
		// ���һ������
		boolean isSuccess = zyfwShService.cancelShLast(zyfwShForm);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
