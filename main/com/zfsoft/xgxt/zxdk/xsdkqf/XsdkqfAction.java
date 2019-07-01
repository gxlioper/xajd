/**
 * @部门:学工产品事业部
 * @日期：2018-5-16 下午04:31:45 
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
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2018-5-16 下午04:31:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsdkqfAction extends SuperAction<XsdkqfForm, XsdkqfService>{
	private static final String url = "zxdk_hkcx_xsdkqf.do";
	private XsdkqfService service = new XsdkqfService();
	
	@SystemAuth(url = url)
	public ActionForward getXsdkqfList(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		XsdkqfForm model = (XsdkqfForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
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
