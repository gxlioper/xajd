/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-26 ����01:56:46 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-6-26 ����01:56:46
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class TxhdXmShAction extends SuperAction {
	// �����ճ��������ճ���Ϊ�������Դӻ�����Ϣ���л�ȡѧ����Ϣ
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	private List<HashMap<String, String>> jbxxList = null;
	
	private static final String url = "rcsw_txhd_xmsh.do";

	/**
	 * 
	 * @����:��ٹ����б��ѯ��ʾ
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
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
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmShService service = new TxhdXmShService();
		CommService cs = new CommService();
		TxhdXmShForm myForm = (TxhdXmShForm)form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("rcsw_txhd_xmsh.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_txhd_xmsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("txhdshlist");
	}
	/**
	 * 
	 * @����:���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����03:35:14
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
	@SystemLog(description="�����ճ�����-��ѧ�-����-���SQID:{sqid}")
	public ActionForward txhdXmSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmShService service = TransactionControlProxy.newProxy(new TxhdXmShService());
		TxhdXmShForm myForm = (TxhdXmShForm) form;
		User user = getUser(request);
		if (SAVE.equals(myForm.getType())) {
			// ���浥�����
			boolean result = service.saveSh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		TxhdXmShForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(myForm.getXh())) {
			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		//��ѧ��Ŀ��Ϣ
		request.setAttribute("xmxx", service.getTxXmxx(model).get(0));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("txhdshsh");
	}
	/**
	 * 
	 * @����:�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-9-11 ����03:35:03
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
	@SystemLog(description="�����ճ�����-��ѧ�-����-����SHID:{shid}")
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmShService service = new TxhdXmShService();
		TxhdXmShForm myForm = (TxhdXmShForm) form;
		HashMap<String,String> shxx = ShlcUtil.getShxx(myForm.getShid());	
		// ҵ��ع�
		boolean result = service.cancel(myForm.getSplc(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey,result));
		return null;
	}

	/**
	 * @����:���һ���������
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmShForm model = (TxhdXmShForm) form;
		TxhdXmShService service = new TxhdXmShService();

		HashMap<String, String> shxx = ShlcUtil.getShxx(model.getShid());
		// ҵ��ع�
		boolean result = service.cancel(model.getSqid(), shxx.get("ywid"));
		// ҵ��ع��ɹ�
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonResult(messageKey, result));
		return null;
	}
	/**
	 * 
	 * @����:��ʾ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-6-17 ����05:23:05
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
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TxhdXmShForm model = (TxhdXmShForm) form;
		TxhdXmShService service = new TxhdXmShService();
		model = service.getModel(model);
		// ѧ����Ϣ
		if (!StringUtil.isNull(model.getXh())){
			//����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("data", StringUtils.formatData(model));
		//��ѧ��Ŀ��Ϣ
		request.setAttribute("xmxx", service.getTxXmxx(model).get(0));
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("txhdshshow");
	}
}
