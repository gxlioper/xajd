/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:35:56 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-心理问题类型
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-23 下午03:35:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwtlxwhAction extends SuperAction{

	private static final String url = "xljk_jcsz_lxwh.do";
	
	/**
	 * 
	 * @描述:查询心理问题类型（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午04:46:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlwtlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_jcsz_lxwh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryXlwtlxwh");
	}
	
	/**
	 * 
	 * @描述:查询心理问题类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午04:50:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryXlwtlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XlwtlxwhForm model  = (XlwtlxwhForm) form;
		XlwtlxwhService service = new XlwtlxwhService();
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:添加心理问题类型（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24上午08:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlwtlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addXlwtlx");
	}
	
	/**
	 * 
	 * @描述:添加心理问题类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24上午09:10:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward addXlwtlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XlwtlxwhForm model  = (XlwtlxwhForm) form;
		XlwtlxwhService service = new XlwtlxwhService();
		
		//心理问题代码是否存在
		boolean isExist=service.xlwtlxIsExist(model);
		if(!isExist){
			boolean isSuccess = service.runInsert(model);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_SAVE_DM_REPEAT));
			return null;
		}
	}
	
	/**
	 * 
	 * @描述:修改心理问题类型（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24上午10:20:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXlwtlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwtlxwhForm model  = (XlwtlxwhForm) form;
		
		XlwtlxwhService service = new XlwtlxwhService();
		XlwtlxwhForm dataModel = service.getModel(model.getLxdm());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		return mapping.findForward("updateXlwtlx");
	}
	
	/**
	 * 
	 * @描述:修改心理问题类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24上午10:32:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXlwtlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwtlxwhForm model  = (XlwtlxwhForm) form;
		XlwtlxwhService service = new XlwtlxwhService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:删除心理问题类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午05:53:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteXlwtlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XlwtlxwhService service = new XlwtlxwhService();
		
		String lxdms = request.getParameter("lxdms"); //需删除的类型代码
		
		int isSuccess = service.runDelete(lxdms.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
	
}
