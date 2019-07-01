/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:50:00 
 */  
package com.zfsoft.xgxt.ybgzz.cssz;

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
 * @类功能描述: 易班工作站-参数设置 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-28 下午02:27:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbgzzCsszAction extends SuperAction<YbgzzCssz, YbgzzCsszService> {

	private static final String KGZT_CLOSE = "0";
	
	
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
	private static final String url = "ybgzz_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
	
		YbgzzCssz csszForm = (YbgzzCssz) form;
		
		YbgzzCsszService service = getService();
		YbgzzCssz model = service.getModel();
		
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
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("ybgzz");
		request.setAttribute("shlcList", shlc);
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		
		request.setAttribute("path", "ybgzz_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
}
