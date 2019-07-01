package com.zfsoft.xgxt.jskp.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;

public class CsszAction extends SuperAction<CsszForm, CsszService> {
	private CsszService service = new CsszService();
	private static final String url = "pjpy_jskp_cssz.do";//path路径
	/**
	 * 
	 * @描述: 参数设置查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:11:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description = "访问纪实考评-参数设置")
	@SystemAuth(url = url)
	public ActionForward getCsszCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("lxsplc", service.getSplc("lx"));
		request.setAttribute("sbsplc", service.getSplc("sb"));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xspj");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("csszcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-4 下午04:36:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	@SystemLog(description = "纪实考评-参数设置-保存")
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		CsszForm cssz = (CsszForm)form;
		String[] ids = request.getParameterValues("id");
		String[] lxs = request.getParameterValues("lx");
		String[] splcs = request.getParameterValues("splc");
		String sfsh = request.getParameter("sfsh");
		cssz.setIds(ids);
		cssz.setLxs(lxs);
		cssz.setSplcs(splcs);
		cssz.setSfsh(sfsh);
		boolean rs = service.saveData(cssz);
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
}
