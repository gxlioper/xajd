package xsgzgl.gyjc.jcsd;

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
import xsgzgl.gyjc.ccjgcx.CcjgForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;

public class PfbzAction extends SuperAction<PfbzForm, PfbzService> {
	private PfbzService service = new  PfbzService();
	private static final String url = "xg_gyjc_jcsd.do";
	/**
	 * 
	 * @����: ��ѯ���ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-11 ����11:55:56
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
	public  ActionForward  getPfbzList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// ��ѯ
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//���û�����û�ҳ��
		request.setAttribute("userType", user.getUserStatus());
		request.setAttribute("jjlx", model.getJjlx());
		request.setAttribute("xydm",model.getXydm());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("search");
	}
	
	/**
	 * 
	 * @����:����ҳ����ת
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����08:59:19
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
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		request.setAttribute("xmList", service.getXmSelectList(model));
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����:�޸�ҳ����ת[���ӣ��޸�]
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����08:59:19
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
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzForm pfbz = service.getPfbzModel(model.getGuid());
		BeanUtils.copyProperties(model, pfbz);
		return mapping.findForward("update");
	}
	
	/**
	 * 
	 * @����: �������ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����10:08:40
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
	@SystemLog(description = "������������-�����趨-�����趨-���ֱ�׼����")
	public ActionForward savePfbz(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		boolean rs = service.savePfbz(model);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ�����ֱ�׼
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-12 ����10:21:47
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
	@SystemLog(description = "������������-�����趨-�����趨-ɾ�����ֱ�׼")
	public ActionForward delPfbz(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzService tanService = TransactionControlProxy.newProxy(new PfbzService());
		boolean rs = tanService.delRs(model.getGuids(), model.getFjids());
		String messageKey = rs ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_SUCCESS;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����:��ȡ���ֱ�׼Ajax
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2017-4-18 ����04:44:04
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
	
	public ActionForward getPfbzListAjax(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PfbzForm model = (PfbzForm) form;
		PfbzService service =  new PfbzService();
		List<HashMap<String,String>> pfbzList = service.getPfbzListAjax(model.getFjid());
		JSONArray dataList = JSONArray.fromObject(pfbzList);
		response.getWriter().print(dataList);
		return null;
	}
}
