/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午08:36:58 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 基本设置 
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午08:36:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszAction extends SuperAction {
	
	private static final String url = "xljk_xlwygl_jcsz.do";

	/**
	 * @描述 审批流服务
	 */
	private XtwhShlcService shlcService = new XtwhShlcService();
	
	/**
	 * 
	 * @描述:查询参数设置配置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-3 下午03:34:13
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		User user = getUser(request);
		
		if("stu".equalsIgnoreCase(user.getUserType())){
			String msg = "该模块不允许学生用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		JcszService service = new JcszService();
		
		JcszForm model = (JcszForm)	form;

		JcszForm jcsz = service.getJcsz();
		
		if(null != jcsz){
			request.setAttribute("code", "1");
			BeanUtils.copyProperties(model, jcsz);
		}else{
			request.setAttribute("code", "0");
		}
		
		// 以下为公共配置项
		request.setAttribute("shlcList", shlcService.getShlcByDjlx("xlzx"));
		// 获取用户（是否可写）权限  和 title
		request.setAttribute("path", "xljk_xlwygl_jcsz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cxJcsz");
	}
	
	/**
	 * 
	 * @描述:保存参数设置
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-4 上午11:46:06
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward bcJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JcszService service = new JcszService();
		JcszForm model = (JcszForm)	form;
		boolean result = false;
		JSONObject message = null;

		result = service.saveJcsz(model);
		
		message = getJsonMessageByKey(result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL);

		response.getWriter().print(message);
		
		return null;
	}
}
