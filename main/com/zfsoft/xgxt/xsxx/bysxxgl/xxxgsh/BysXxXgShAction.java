/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-7-10 ����11:13:00 
 */
package com.zfsoft.xgxt.xsxx.bysxxgl.xxxgsh;

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

import xgxt.base.DBEncrypt;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xsgzgl.xsxx.general.xsxxgl.XsxxtyglService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.xsxx.bysxxgl.bysxx.BysXxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2014-7-10 ����11:13:00
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BysXxXgShAction extends SuperAction {
	
	private static final String url = "xsxx_new_bysxx_xxxgsh.do";
	
	/**
	 * 
	 * @����:��Ϣ�޸���������б�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����11:28:50
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url)
	public ActionForward xgSqShList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShService service = new BysXxXgShService();
		CommService cs = new CommService();
		BysXxXgShForm myForm = (BysXxXgShForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("xsxx_new_bysxx_xxxgsh.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "xsxx_new_bysxx_xxxgsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xgSqShList");
	}

	/**
	 * 
	 * @����:���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-10 ����02:58:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SuppressWarnings("unchecked")
	public ActionForward xgSqSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShForm model = (BysXxXgShForm) form;
		BysXxService bysxxService = new BysXxService();
		HashMap xsxxMap = bysxxService.getBysXx(model.getXh());
		if (QUERY.equalsIgnoreCase(model.getType())) {
			response.setContentType("text/html;charset=gbk");
			response.getWriter().print(JSONObject.fromMap(xsxxMap));
			return null;
		}
		String zpsfcz = new XsxxtyglService().checkxszpSfcz(model.getXh());
		request.setAttribute("zpsfcz", zpsfcz);
		request.setAttribute("rs", StringUtils.formatData(xsxxMap));
		xgxt.studentInfo.service.XsxxglService xsxxglService = new xgxt.studentInfo.service.XsxxglService();
		request.setAttribute("jtgxList", xsxxglService.getJtgxList());
		request.setAttribute("xh", model.getXh());
		request.setAttribute("gwid", request.getParameter("gwid"));
		request.setAttribute("ywid", request.getParameter("ywid"));
		request.setAttribute("lcid", request.getParameter("lcid"));
		request.setAttribute("shid", request.getParameter("shid"));
		this.saveToken(request);
		return mapping.findForward("xgSqSh");
	}

	/**
	 * 
	 * @����:��˱���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-28 ����11:01:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸����-���GWID:{gwid}")
	public ActionForward xgSqShBc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		String gwid = request.getParameter("gwid");
		String ywid = request.getParameter("ywid");
		String xh = request.getParameter("xh");
		String lcid = request.getParameter("lcid");
		BysXxXgShForm model = (BysXxXgShForm) form;
		BysXxXgShService service = new BysXxXgShService();
		User user = getUser(request);
		model.setGwid(gwid);
		model.setSqid(ywid);
		model.setSqr(xh);
		model.setSplc(lcid);
		// ���浥�����
		boolean result = service.saveSqSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:TODO�������
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����10:33:33
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xgSqPlSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String dataJson = request.getParameter("params");
		DBEncrypt p = new DBEncrypt();
		String afterE = p.eCode(dataJson);
		request.setAttribute("dataJson", afterE);
		this.saveToken(request);
		return mapping.findForward("xgSqPlSh");
	}

	/**
	 * 
	 * @����:������˱���
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-17 ����06:44:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸����-�������DATAJSON:{dataJson}")
	public ActionForward savePlSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		BysXxXgShForm model = (BysXxXgShForm) form;
		String params = request.getParameter("dataJson");
		DBEncrypt dbEncrypt = new DBEncrypt();
		String dataJson = dbEncrypt.dCode(params.getBytes());
		BysXxXgShService service = new BysXxXgShService();
		User user = getUser(request);
		boolean result = service.savePlXgSh(model, dataJson, user);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS : MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	/**
	 * 
	 * @����:TODO��˳����˻�
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-7-11 ����09:55:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="����ѧ����Ϣ-��ҵ����Ϣ����-��Ϣ�޸����-����SQID:{sqid}")
	public ActionForward xgShCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BysXxXgShService service = new BysXxXgShService();

		String sqid = request.getParameter("sqid");
		boolean result = service.CxXgsq(sqid);
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		response.getWriter().print(JSONObject.fromObject(map));
		return null;

	}
}
