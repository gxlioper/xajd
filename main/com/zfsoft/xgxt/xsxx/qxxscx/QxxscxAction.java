/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-23 ����03:50:02 
 */  
package com.zfsoft.xgxt.xsxx.qxxscx;

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

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ȫУѧ����Ϣ��ѯ�����������ʦ��ѯ����ѧԺѧ��
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-8-23 ����03:50:02 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QxxscxAction extends SuperAction<QxxscxForm, QxxscxService>{
	private static final String url = "xsxx_zjdx_qxxscx.do";
	private QxxscxService service = new QxxscxService();

	/**
	 * @����: ȫУѧ����Ϣ��ѯ
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ�2016-8-23 ����04:18:02
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
	public ActionForward getQxxscxList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		QxxscxForm model = (QxxscxForm) form;
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
		String path = "xsxx_zjdx_qxxscx.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getQxxscxList");
	}
}
