/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:50:00 
 */  
package com.zfsoft.xgxt.zxdk.cssz;

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
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 助学贷款-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-9-24 上午09:50:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZxdkCsszAction extends SuperAction<ZxdkCssz, ZxdkCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	private static final String url = "zxdk_gjdk_cssz.do";
	
	/**
	 * 
	 * @描述: 助学贷款-国家助学贷款-参数设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 上午11:50:30
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
	@SystemAuth(url = "zxdk_gjdk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问国家助学贷款-参数设置")
	public ActionForward gjdkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			if (StringUtil.isNull(model.getXydkg())){
				model.setXydkg(KGZT_CLOSE);
			}
			
			if (StringUtil.isNull(model.getXdkg())){
				model.setXdkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setXydkg(KGZT_CLOSE);
			csszForm.setXdkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_gjdk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("gjdkCssz");
	}
	
	
	/**
	 * 
	 * @描述: 助学贷款-生源地贷款-参数设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014-9-25 上午11:50:30
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
	@SystemAuth(url = "zxdk_syddk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问生源地贷款-参数设置")
	public ActionForward syddkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		if (model == null || StringUtil.isNull(model.getSydkg())) {
			csszForm.setSydkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("xnlist", Base.getXnndList2());
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_syddk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("syddkCssz");
	}
	
	
	/**提前还款参数设置*/
	@SystemAuth(url = "zxdk_tqhk_cssz.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问提前还款-参数设置")
	public ActionForward tqhkCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZxdkCssz csszForm = (ZxdkCssz) form;
		
		ZxdkCsszService service = getService();
		ZxdkCssz model = service.getModel();
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		if (model == null || StringUtil.isNull(model.getSydkg())) {
			csszForm.setTqhkkg(KGZT_CLOSE);
		}
		
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_tqhk_cssz.do");
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("tqhkCssz");
	}
}
