/**
 * @部门:学工产品事业部
 * @日期：2016-3-14 上午11:31:21 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-14 上午11:31:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
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
		// IE版本
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
