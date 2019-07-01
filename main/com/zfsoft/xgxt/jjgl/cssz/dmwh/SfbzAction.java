/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午04:44:31 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.jjgl.jjnj.JjnjService;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午04:44:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SfbzAction extends SuperAction<SfbzForm, SfbzService> {
	
	
	private JjxkService jjxkService = new JjxkService();
	
	private JjnjService jjnjService = new JjnjService();
	
	/**
	 * 
	 * @描述:添加
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午04:46:51
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
	
	public  ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		SfbzForm model = (SfbzForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().add(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}

	/**
	 * 
	 * @描述:修改
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午04:46:51
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
	
	public  ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		SfbzForm model = (SfbzForm)	form;
		boolean result = false;
		JSONObject message = null;
		result = getService().runUpdate(model);
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	
	/**
	 * 
	 * @描述:删除
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-25 下午04:46:51
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
	
	public  ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		boolean result = false;
		JSONObject message = null;
		result = getService().runDelete(request.getParameter("delIds").split(",")) > 0;
		message = getJsonMessageByKey(result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * @return the jjxkService
	 */
	public JjxkService getJjxkService() {
		return jjxkService;
	}

	/**
	 * @param jjxkService要设置的 jjxkService
	 */
	public void setJjxkService(JjxkService jjxkService) {
		this.jjxkService = jjxkService;
	}

	/**
	 * @return the jjnjService
	 */
	public JjnjService getJjnjService() {
		return jjnjService;
	}

	/**
	 * @param jjnjService要设置的 jjnjService
	 */
	public void setJjnjService(JjnjService jjnjService) {
		this.jjnjService = jjnjService;
	}
	
	
}
