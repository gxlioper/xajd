package com.zfsoft.xgxt.xlzx.zxsgly;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglForm;
import com.zfsoft.xgxt.xlzx.zxswh.ZxsglService;
import common.newp.StringUtil;

public class ZxsglyAction extends SuperAction<ZxsglForm, ZxsglService> {
	private static final String URL = "xg_xlzx_zxsgly.do";
	private static final String URL_ADDGLY = "xlzx_zxsgly.do?method=addZxsgly";
	private ZxsglyService service = new  ZxsglyService();
	/**
	 * 
	 * @����: ��ѯ��תҳ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����10:22:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	@SystemAuth(url = URL)
	public ActionForward searchZxsgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URL);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @����:��ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����10:40:28
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
	@SystemAuth(url = URL)
	public ActionForward searchRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ������ѯʦ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����03:25:42
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
	@SystemAuth(url = URL,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������������ѯ-��ѯʦ����-��ѯʦ����Ա-����")
	public ActionForward addZxsgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		//�߼���ѯ����
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", URL_ADDGLY);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("add");
	}
	
	/**
	 * 
	 * @����: ��ѯ��ѯʦ����Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����03:43:01
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
	@SystemAuth(url = URL)
	public ActionForward searchRsAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getPageList(myForm);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����04:26:22
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
	@SystemAuth(url = URL,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "������������ѯ-��ѯʦ����-��ѯʦ����Ա-���ӱ���-zghs:{zghs}")
	public ActionForward saveAddGly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ZxsglyForm myForm = (ZxsglyForm)form;
		//Ϊ�˱�������һ����
		ZxsglyService transService = TransactionControlProxy.newProxy(new ZxsglyService());
		boolean rs = transService.saveForm(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ɾ������Ա
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����05:30:03
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
	public ActionForward delgly(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
}
