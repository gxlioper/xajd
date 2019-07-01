package com.zfsoft.xgxt.pjpy.xzhcp;

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

public class ZhcpAction extends SuperAction<ZhcpForm,ZhcpService> {
	private static final String url = "pjpy_xzhcp_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZhcpForm myForm = (ZhcpForm) form;
		ZhcpService service = new ZhcpService();
		ZhcpForm model = service.getModel();
		if (model != null) {
			BeanUtils.copyProperties(myForm, model);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("pjpy");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
}
