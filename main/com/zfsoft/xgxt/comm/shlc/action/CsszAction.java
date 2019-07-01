/**
 * @部门:学工产品事业部
 * @日期：2014年6月9日 下午1:54:53 
 */  
package com.zfsoft.xgxt.comm.shlc.action;

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
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;
import com.zfsoft.xgxt.comm.shlc.service.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 通用申请审核-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月9日 下午1:54:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction<CsszModel, CsszService> {

	
	/**
	 * 
	 * @描述: 申请审核-参数设置
	 * @作者：屈朋辉[工号：445]
	 * @日期：2014年6月9日 下午1:55:59
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		CsszModel csszForm = (CsszModel) form;
		CsszService service = new CsszService();
		
		CsszModel model = service.getModel(csszForm);
		
		if (model != null){
			BeanUtils.copyProperties(csszForm, model);
		}
		
		//开关列表
		OptionUtil optionUtil = new OptionUtil();
		List<HashMap<String , String>> kgList = optionUtil.getOptions(OptionUtil.ONOFF);
		request.setAttribute("kgList", kgList);
		
		//审核流程
		XtwhShlcService shlcService = new XtwhShlcService();
		request.setAttribute("shlcList", shlcService.getShlcByDjlx(csszForm.getSsmk()));
		
		request.setAttribute("path", "sqsh_cssz.do?id="+csszForm.getId()+"&ssmk="+csszForm.getSsmk());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cssz");
	}
	
	
}
