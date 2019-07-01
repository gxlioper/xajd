package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbjcsz;


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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 周报基础设置
 */
public class XsgzzbJcszAction extends SuperAction {
	
	private static final String url = "rcsw_xsgzzb_jcszgl.do";
	
	/**
	 * 周报基础设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsgzzbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbJcszForm myForm = (XsgzzbJcszForm) form;
		String gzzblx = myForm.getGzzblx();
		XsgzzbJcszService service = new XsgzzbJcszService();
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		
		String path = "rcsw_xsgzzb_jcsz.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_jcszgl.do?method=xsgzzbJcsz&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		XsgzzbJcszForm model = service.getModel(gzzblx);
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbJcsz");
		}
		return mapping.findForward("xsgzzbJcsz");
	}
	
	/**
	 * 保存周报基础设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward saveXsgzzbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbJcszForm myForm = (XsgzzbJcszForm) form;
		XsgzzbJcszService service = new XsgzzbJcszService();
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
