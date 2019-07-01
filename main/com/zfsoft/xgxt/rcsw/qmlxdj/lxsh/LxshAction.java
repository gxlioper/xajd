/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-11 ����09:03:37 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

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
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.rcsw.qmlxdj.lxdj.LxdjService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

import common.newp.StringUtil;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2017-1-11 ����09:03:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxshAction extends SuperAction<LxshForm, LxshService> {
	LxshService service = new LxshService();
	private final String QMLXDJ="qmlxdj";

	/**
	 * 
	 * @����: ��ѯ
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����10:27:53
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
	public ActionForward getLxdjshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxshForm lxshform = (LxshForm)form;
		if (QUERY.equalsIgnoreCase(lxshform.getType())) {
			// ���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			lxshform.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(lxshform, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// Ĭ�ϸ߼���ѯ����
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "rcsw_qmlxsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����10:34:19
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
	public ActionForward Dgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxshForm lxshform = (LxshForm)form;
		if (!StringUtil.isNull(lxshform.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(lxshform.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(lxshform.getType())) {
			User user = getUser(request);
			// ���浥�����
			boolean result = service.saveSh(lxshform, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(QMLXDJ);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("shid", request.getParameter("shid"));
		LxdjService sqService = new LxdjService();
		request.setAttribute("rs", sqService.getCkxx(lxshform.getSqid()));
		return mapping.findForward("dgsh");
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:41:18
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
	public ActionForward zsPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LxshForm model = (LxshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("plsh");
	}
	
	/**
	 * 
	 * @����: �������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:50:35
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
		LxshForm model = (LxshForm) form;
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
	 * @����: ����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2017-1-12 ����11:54:07
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LxshForm model = (LxshForm) form;
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// ��˳����ɹ�
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
}
