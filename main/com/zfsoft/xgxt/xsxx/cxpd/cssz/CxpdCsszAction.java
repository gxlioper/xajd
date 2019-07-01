/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:50:00 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.cssz;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定 --参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-11 下午04:16:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CxpdCsszAction extends SuperAction<CxpdCssz, CxpdCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "cxpd_cssz.do";
	
	/**
	 * 
	 * @系统名称: 学生工作管理系统
	 * @模块名称: XXXX管理模块
	 * @类功能描述: 操行评定--参数设置 
	 * @作者： 屈朋辉[工号:445]
	 * @时间： 2015-1-14 下午01:52:28 
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		CxpdCssz csszForm = (CxpdCssz) form;
		
		CxpdCsszService service = getService();
		CxpdCssz model = service.getModel();
		
		if (model != null){
			
			if (StringUtil.isNull(csszForm.getSqkg())){
				csszForm.setSqkg(KGZT_CLOSE);
			}
			
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "cxpd_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
