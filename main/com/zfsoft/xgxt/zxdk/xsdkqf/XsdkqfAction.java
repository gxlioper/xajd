/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-16 ����04:31:45 
 */  
package com.zfsoft.xgxt.zxdk.xsdkqf;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2018-5-16 ����04:31:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsdkqfAction extends SuperAction<XsdkqfForm, XsdkqfService>{
	private static final String url = "zxdk_hkcx_xsdkqf.do";
	private XsdkqfService service = new XsdkqfService();
	
	@SystemAuth(url = url)
	public ActionForward getXsdkqfList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		XsdkqfForm model = (XsdkqfForm) form;
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
		User user = getUser(request);
		String usersf = user.getUserStatus();
		request.setAttribute("usersf", usersf);
		String path = "zxdk_hkcx_xsdkqf.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getXsdkqfList");
		
	}
	
	public ActionForward jcsjcshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsdkqfForm model = (XsdkqfForm) form;
		boolean flag = service.Fsznx();
		String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
				: MessageInfo.MESSAGE_DO_FALSE;
		model.setMessage(message);
		return null;
	}
}
