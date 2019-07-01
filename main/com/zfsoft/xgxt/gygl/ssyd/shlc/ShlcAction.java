/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:03:38 
 */
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 宿舍异动模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-9-9 下午12:03:38
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ShlcAction extends SuperAction {
	
	private static final String url = "shlcbase.do";
	
	/**
	 * 
	 * @描述: 宿舍异动模块
	 * @作者：张昌路 [工号：982]
	 * @日期：2013-6-17 下午05:17:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-审核流程配置-修改ID:{id}")
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcService service = new ShlcService();
		ShlcForm myForm = (ShlcForm) form;
		ShlcForm model = service.getShlcForm();
		//request.setAttribute("zsfxs", model.getZsfxs());
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		//获取审核流
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("gygl");
		request.setAttribute("path", "shlcbase.do");
		request.setAttribute("shlcList", shlc);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shlcxg");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-审核流程配置-增加")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcService service = new ShlcService();
		ShlcForm myForm = (ShlcForm) form;
		boolean result = service.save(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问公寓管理-宿舍异动-审核流程配置-保存")
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ShlcService service = new ShlcService();
		ShlcForm myForm = (ShlcForm) form;
		boolean result = service.delete();
		if(result){
			result = service.insert(myForm);
		}
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	public ActionForward getSplcid(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ShlcService service = new ShlcService();
		ShlcForm myForm = (ShlcForm) form;
		String splcid = service.getSplcid(myForm.getSsydlx());
		response.getWriter().print(splcid);
		return null;
	}
	
}
