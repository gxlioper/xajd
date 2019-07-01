/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.hdgl.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;



/**
 * @className	： CsszAction
 * @description	： cssz(描述这个类的作用)
 * @author 		：柳俊（1282）
 * @date		： 2018-1-16 上午11:07:19
 * @version 	V1.0 
 */

public class CsszAction extends SuperAction<CsszForm, CsszService>{
	private static final String url = "hdgl_hdbl_cssz.do";
	
	/**
	 * @description	： 参数设置列表
	 * @author 		： 柳俊（1282）
	 * @date 		：2018-1-16 上午11:17:10
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dekt");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		CsszForm CsszForm = service.getCsszForm();
		BeanUtils.copyProperties(model, CsszForm);
		request.setAttribute("path", "hdgl_hdbl_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	/**
	 * @description	： 保存
	 * @author 		：柳俊（1282）
	 * @date 		：2018-1-16 下午03:22:51
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm model = (CsszForm)form;
		CsszService service = new CsszService();
		service.delete();
		boolean result = service.insert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;	
	}
}
