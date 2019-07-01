package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

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
import com.zfsoft.xgxt.base.util.OptionUtil;

public class XjsqcsszAction extends SuperAction<XjsqCsszForm,XjsqcsszService> {
	
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		XjsqCsszForm myForm = (XjsqCsszForm) form;
		XjsqcsszService service = new XjsqcsszService();
		XjsqCsszForm model = service.getModel();
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "xg_qjgl_xjcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcsz");
	}
	
	
	
	
}
