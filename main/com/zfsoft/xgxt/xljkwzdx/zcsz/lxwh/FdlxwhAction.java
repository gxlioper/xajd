/**
 * @部门:学工产品事业部
 * @日期：2014-4-23 下午03:35:56 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康-基础设置-类型维护-辅导类型
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-23 下午03:35:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdlxwhAction extends SuperAction{

	private static final String url = "xljk_jcsz_lxwh.do";
	
	/**
	 * 
	 * @描述:查询辅导类型（跳转）
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午04:49:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//获取操作权限
		request.setAttribute("path", "xljk_jcsz_lxwh.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("queryFdlxwh");
	}
	
	/**
	 * 
	 * @描述:查询辅导类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-23 下午04:49:39 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward queryFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
		
		//查询
		List<HashMap<String,String>> resultList = service.getPageList(model);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:添加辅导类型（跳转）
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
	public ActionForward addFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addFdlx");
	}
	
	/**
	 * 
	 * @描述:添加辅导类型
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
	public ActionForward addFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
			
		//辅导类型代码是否存在
		boolean isExist=service.fdlxIsExist(model);
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
	 * @描述:修改辅导类型（跳转）
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
	public ActionForward updateFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhForm model  = (FdlxwhForm) form;
		
		FdlxwhService service = new FdlxwhService();
		FdlxwhForm dataModel = service.getModel(model.getFdlxdm());
		BeanUtils.copyProperties(model, StringUtils.formatData(dataModel));
		
		return mapping.findForward("updateFdlx");
	}
	
	/**
	 * 
	 * @描述:修改辅导类型
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
	public ActionForward updateFdlxAction(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhForm model  = (FdlxwhForm) form;
		FdlxwhService service = new FdlxwhService();
		
		boolean isSuccess = service.runUpdate(model);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:删除辅导类型
	 * @作者：王志刚[工号:1060]
	 * @日期：2014-4-24 上午08:53:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward deleteFdlx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdlxwhService service = new FdlxwhService();
		
		String fdlxdms = request.getParameter("fdlxdms"); //需删除的类型代码
		
		int isSuccess = service.runDelete(fdlxdms.split(","));
		
		String message = isSuccess > 0 ? MessageUtil.getText(
				MessageKey.SYS_DEL_NUM, isSuccess) : MessageUtil
				.getText(MessageKey.SYS_DEL_FAIL);
				
		response.getWriter().print(getJsonMessage(message));
		
		return null;
	}
}
