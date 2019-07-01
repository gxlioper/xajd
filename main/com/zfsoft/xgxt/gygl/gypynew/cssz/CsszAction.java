package com.zfsoft.xgxt.gygl.gypynew.cssz;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓评优系统
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2017-7-24 下午04:14:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CsszAction extends SuperAction<CsszForm,CsszService> {
	private CsszService service = new CsszService();
	private final String url = "gygl_gypynew_cssz.do";
	/**
	 * 
	 * @描述: 公寓
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-24 下午04:11:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward  cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("splcMap", service.getSplc());
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("splcList", shlcService.getShlcByDjlx("gygl"));
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-24 下午04:19:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm myForm = (CsszForm)form;
		boolean rs = true;
		if(StringUtils.isNotNull(myForm.getId())){
			rs = service.runUpdate(myForm);
		}else{
			rs = service.runInsert(myForm);
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
