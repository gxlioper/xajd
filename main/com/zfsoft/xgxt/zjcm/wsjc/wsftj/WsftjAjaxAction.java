/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-14 ����11:31:21 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-14 ����11:31:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsftjAjaxAction extends BasicAction{
	private WsftjAjaxService service = new WsftjAjaxService();
	private static final String url = "cjWsf_wsftj.do";
	@SystemAuth(url = url)
	public ActionForward getWsftjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		WsftjForm myForm = (WsftjForm) form;
		String[] otherValue = request.getParameter("otherValue")
		.split("!!@@!!");
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);
		service.commInit(rForm, myForm, request, user);
		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);	
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		service.createHTML(myForm, user, response);
		return null;
	}
}
