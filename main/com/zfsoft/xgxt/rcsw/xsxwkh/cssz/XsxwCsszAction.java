/**
 * @部门:学工产品事业部
 * @日期：2016-05-07 下午04:28:30 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.cssz;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生行为考核参数设置
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午02:40:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XsxwCsszAction extends SuperAction<XsxwCsszForm, XsxwCsszService> {
	private static final String url = "xsxwkh_cssz.do";
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午02:41:17
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxwCsszForm myForm = (XsxwCsszForm) form;
		XsxwCsszService service = new XsxwCsszService();
		XsxwCsszForm model = service.getModel();
		model.setXn(Base.currXn);
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	/**
	 * 参数设置保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问学生行为考核-参数设置")
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsxwCsszForm model = (XsxwCsszForm) form;
		XsxwCsszService service = new XsxwCsszService();
		boolean result = false;
		service.deleteJcsz();
		result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
