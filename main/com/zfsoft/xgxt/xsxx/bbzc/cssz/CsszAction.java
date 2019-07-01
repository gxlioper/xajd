/**
 * @部门:学工产品事业部
 * @日期：2015-3-23 上午10:55:35 
 */  
package com.zfsoft.xgxt.xsxx.bbzc.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生信息报到注册
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-23 上午10:55:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction<CsszForm, CsszService>{
	
	private static final String url = "xsxx_cssz.do";
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-23 上午11:21:07
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		CsszForm csszForm =(CsszForm)form;
		CsszService service = new CsszService();
		CsszForm model = service.getModel();
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "xsxx_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}

}
