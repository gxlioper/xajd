/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午04:22:56 
 */  
package com.zfsoft.xgxt.jjgl.jjnj;

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
import com.zfsoft.xgxt.jjgl.cssz.CsszForm;
import com.zfsoft.xgxt.jjgl.jjxk.JjxkForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午04:22:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjnjAction extends SuperAction<JjnjForm, JjnjService> {
	
	

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
		JjnjForm model = (JjnjForm)	form;
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
		JjnjForm model = (JjnjForm)	form;
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
		result = getService().delete(request.getParameter("delIds"));
		message = getJsonMessageByKey(result ? MessageKey.SYS_DEL_SUCCESS : MessageKey.SYS_DEL_FAIL);
		response.getWriter().print(message);
		return null;
	}
}
