/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;

import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称:学生信息 
 * @类功能描述: 审核流程
 * @作者： ligl 
 * @时间： 2013-12-11 下午01:35:21 
 * @版本： V1.0
 * @修改记录:
 */
public class ShlcAction extends SuperAction {

	/**
	 * 
	 * @描述:保存审核流程
	 * @作者：ligl
	 * @日期：2013-12-11 下午01:34:58
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveShlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		ShxxModel model = (ShxxModel) form;
		String gwid = request.getParameter("gwid");
		String ywid = request.getParameter("ywid");
		String xh = request.getParameter("xh");
		ShlcService service = new ShlcService();
		User user = getUser(request);
		model.setShr(user.getUserName());
		model.setGwid(gwid);
		model.setYwid(ywid);
		model.setSqrid(xh);
		boolean result = service.saveXgsh(model);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:保存批量审核流程
	 * @作者：ligl
	 * @日期：2013-12-17 上午10:11:38
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward savePlshlc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		if (!isTokenValid(request)){
//			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
//			return null;
//		} else {
//			super.resetToken(request);
//		}
		ShxxModel model = (ShxxModel) form;
		String dataJson = request.getParameter("dataJson");
//		DBEncrypt dbEncrypt = new DBEncrypt();
//		String dataJson = dbEncrypt.dCode(params.getBytes());
		ShlcService service = new ShlcService();
		User user = getUser(request);
		model.setShr(user.getUserName());
		boolean result = service.savePlxgsh(model,dataJson);
		String messageKey = result ? MessageKey.SYS_AUD_SUCCESS
				: MessageKey.SYS_AUD_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
