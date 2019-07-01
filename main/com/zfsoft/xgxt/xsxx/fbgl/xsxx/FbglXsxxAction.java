/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-26 ����09:31:55 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx;

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
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ѧ����Ϣ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-26 ����09:31:55
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglXsxxAction extends SuperAction {
	
	private static final String url = "fbglxsxxbase.do";
	
	@SystemAuth(url = url)
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglXsxxService service = new FbglXsxxService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		User user = getUser(request);

		if (QUERY.equals(myForm.getType())) {
			// ==================�߼���ѯ���========================
			CommService cs = new CommService();
			SearchModel searchModel = cs.getSearchModel(request);
			searchModel.setPath("fbglxsxxbase.do");
			myForm.setSearchModel(searchModel);
			// ==================�߼���ѯ��� end========================
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "fbglxsxxbase.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fbglxsxxlb");
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
	@SystemLog(description="����ѧ����Ϣ-�ְ����-������Ϣά��-ɾ��VALUES:{values}")
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FbglXsxxService service = new FbglXsxxService();
		String values = request.getParameter("values");
		User user = getUser(request);
		int num = service.runDelete(values,user);
		boolean result = num > 0;
		String message = result ? MessageUtil.getText(MessageKey.SYS_DEL_NUM,
				num) : MessageUtil.getText(MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(getJsonMessage(message));
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
		FbglXsxxService service = new FbglXsxxService();
		FbglXsxxForm myForm = (FbglXsxxForm) form;
		FbglXsxxForm model = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, model);
		request.setAttribute("data", StringUtils.formatData(model));
		return mapping.findForward("fbglxsxxck");
	}
}
