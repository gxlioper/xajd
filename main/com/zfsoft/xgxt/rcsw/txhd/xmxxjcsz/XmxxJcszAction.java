package com.zfsoft.xgxt.rcsw.txhd.xmxxjcsz;

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
 * ��������
 */
public class XmxxJcszAction extends SuperAction {
	
	private static final String url = "rcsw_txhd_jcsz.do";
	
	/**
	 * ��������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward xmxxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxJcszForm myForm = (XmxxJcszForm) form;
		XmxxJcszService service = new XmxxJcszService();
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//�����ر�
		request.setAttribute("onoffList", onoffList);
		
		//��������б�
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_txhd_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		XmxxJcszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("xmxxJcsz");
	}
	
	/**
	 * �����������
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward saveXmxxJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxJcszForm myForm = (XmxxJcszForm) form;
		XmxxJcszService service = new XmxxJcszService();
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
