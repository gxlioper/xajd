package com.zfsoft.xgxt.jskp.sbsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ÿ�չ������˹���ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-7 ����04:10:33 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JskpXmsbshAction extends SuperAction<JskpXmsbshForm,JskpXmsbshService>{
	private final String CJFF="cjff";
	private JskpXmsbshService service = new JskpXmsbshService();
	private static final String url = "jskp_xmsh.do";
	
	/**
	 * 
	 * @����:�����Ϣ�б�
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����01:49:50
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
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("path", url);
		/*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sbshList");
	}
	/**
	 * 
	 * @����:�걨���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-7 ����06:07:24
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
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		if (!StringUtil.isNull(model.getSqid())) {
			HashMap<String, String> xmsbInfo = service.getSbshInfo(model);
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xmsbInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			
			request.setAttribute("rs", StringUtils.formatData(xmsbInfo));
		}
	
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		/*ȡ���״̬���е�����һ����¼�ķ���*/
		HashMap<String, String> shxxLevel = service.getLevelXxBySqid(model);
		request.setAttribute("shxxLevel", shxxLevel);
		/*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("sbDgsh");
	}
	@SystemAuth(url = "jskp_xmsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="���ʼ�ʵ����--�걨���-��˱���-GUID:{sqid}")
	public ActionForward saveSbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		JskpXmsbshService service = TransactionControlProxy.newProxy(new JskpXmsbshService());
		
		User user = getUser(request);
		// ���浥�����
		boolean result = service.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-7-27 ����03:59:11
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
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			JskpXmsbshService shService = TransactionControlProxy.newProxy(new JskpXmsbshService());
			String message = shService.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		HashMap<String, String> shxhList = service.getShxhForId(request.getParameter("id"));
		request.setAttribute("shxhList", shxhList);
		request.setAttribute("zdf", request.getParameter("zdf"));
		request.setAttribute("zxf", request.getParameter("zxf"));
		/*ȡ�������ñ��е��Ƿ�������ֶ�*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("sbPlsh");
	}
	/**
	 * 
	 * @����:���һ����˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-8 ����04:08:06
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
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// ���һ������
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @����:��˳���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-20 ����06:50:51
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JskpXmsbshForm model =new JskpXmsbshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
//		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
//		
//
//		// ��˳����ɹ�
//		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
//				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
//				: MessageKey.SYS_CANCEL_FAIL;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("message", MessageUtil.getText(messageKey));
//		map.put("cancelFlg", cancelFlg);
//		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}


}
