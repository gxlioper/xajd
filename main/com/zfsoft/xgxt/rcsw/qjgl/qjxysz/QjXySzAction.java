/**
 * @部门:学工产品事业部
 * @日期：2015-11-3 上午10:58:47 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.qjxysz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 该功能模块只对老师开放
 * @作者： yxy[工号:1206]
 * @时间： 2015-11-3 上午10:58:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QjXySzAction extends SuperAction<QjXySzForm, QjXySzService> {
	QjXySzService service = new QjXySzService();
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-3 上午11:07:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward qjxySzCx(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap<String, String> xysz_data = service.getQjXySzDada();
		request.setAttribute("data", xysz_data);
		return mapping.findForward("qjxySzCx");
	}
	
	/**
	 * 
	 * @描述:保存数据
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-3 上午11:27:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward save_xyszData(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		QjXySzForm model = (QjXySzForm)form;
		String content = DealString.toGBK(request.getParameter("editorid"));
		model.setContent(content);
		boolean result  = false;
		if(model.getContent() != null){
			model.setBjr(getUser(request).getUserName());
			result = service.save_xyszData(model);
		}
		String message = result ? MessageUtil.getText(
				MessageKey.SYS_SAVE_SUCCESS) : MessageUtil
				.getText(MessageKey.SYS_SAVE_FAIL);
	    response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2015-11-3 上午11:07:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward qjxyCk(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) throws Exception{
		HashMap<String, String> xysz_data = service.getQjXySzDada();
		request.setAttribute("data", xysz_data);
		return mapping.findForward("qjxyCk");
	}
}
