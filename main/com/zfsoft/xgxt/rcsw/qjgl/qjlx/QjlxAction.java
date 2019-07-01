/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-9-9 ����12:03:38 
 */
package com.zfsoft.xgxt.rcsw.qjgl.qjlx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ٹ���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-9-9 ����12:03:38
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class QjlxAction extends SuperAction {
	
	private static final String url = "qjlxbase.do";

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
		QjlxService service = new QjlxService();
		CommService cs = new CommService();
		QjlxForm myForm = (QjlxForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {
			RequestForm rForm = new RequestForm();
			// ==================�߼���ѯ���========================
			SearchModel searchModel = cs.setSearchModel(rForm, request);
			searchModel.setPath("Qjlxwh.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "qjlxbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("qjlxlb");
	}

	/**
	 * 
	 * @����:����ɾ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������ά��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] mess = service.delete(values.split(","));
			if(null==mess||mess.length!=2){
				String message= MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
				response.getWriter().print(getJsonMessage(message));
				return null;
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("num",mess[0]);
			map.put("nodel",mess[1]);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}

	/**
	 * 
	 * @����: ��ٹ���ģ��
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:17:35
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
	@SystemLog(description="�����ճ�����-��ٹ���-�������ά��-�޸�QJLXID:{qjlxid},QJLXMC:{qjlxmc}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		QjlxForm model = service.getModel(myForm);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("qjlxxg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ٹ���-�������ά��-����QJLXMC:{qjlxmc}")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())) {
			boolean result = service.save(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		return mapping.findForward("qjlxzj");
	}

	/**
	 * 
	 * @����:�Ƿ��������
	 * @���ߣ��Ų�· [���ţ�982]
	 * @���ڣ�2013-6-17 ����05:24:41
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
	public ActionForward isCanAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QjlxService service = new QjlxService();
		QjlxForm myForm = (QjlxForm) form;
		// �Ƿ��������
		boolean result = service.isCanAdd(myForm);
		if (!result) {// ������
			response.getWriter().print(
					getJsonResult(MessageKey.ZYSZPJ_CANNOT_ADD, false));
		} else {
			Map<String, String> map = new HashMap<String, String>();
			map.put("success", "true");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().print(json);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����״̬
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-19 ����09:38:20
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
	public ActionForward openZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjlxForm myForm = (QjlxForm) form;
		QjlxForm model = new QjlxService().getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		return mapping.findForward("openZt");
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-6-19 ����10:31:19
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
	public ActionForward saveOpenZt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QjlxForm myForm = (QjlxForm) form;
		boolean rs = new QjlxService().runUpdate(myForm);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
