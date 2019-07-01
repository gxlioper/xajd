/**
 * @部门:学工产品事业部
 * @日期：2013-4-25 上午08:47:45 
 */  
package com.zfsoft.xgxt.xszz.jtqkdcjcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生资助2013版--家庭情况调查 基础设置 
 * @作者： Penghui.Qu 
 * @时间： 2013-4-25 上午08:47:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtqkdcJcszAction extends SuperAction {

	private static final String url = "xszz_jtqkdc_cssz.do";
	
	/**
	 * 
	 * @描述:基础设置修改
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 上午08:56:05
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
	public ActionForward updateJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszForm myForm = (JtqkdcJcszForm) form;
		JtqkdcJcszService service = new JtqkdcJcszService();
		
		JtqkdcJcszForm model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		
		OptionUtil optionUtil = new OptionUtil();
		//开关
		List<HashMap<String, String>> onOff = optionUtil.getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "xszz_jtqkdc_cssz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("updateJcsz");
	}
	
	
	/**
	 * 
	 * @描述:保存基础设置
	 * @作者：Penghui.Qu
	 * @日期：2013-4-25 上午09:11:13
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
	@SystemLog(description="访问学生资助-家庭情况-家庭情况调查设置-保存-SQKG:{sqkg}")
	public ActionForward saveJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JtqkdcJcszForm myForm = (JtqkdcJcszForm) form;
		JtqkdcJcszService service = new JtqkdcJcszService();
		
		boolean isSuccess = service.runInsert(myForm);
		String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		
		return null;
	}
}
