/**
 * @部门:学工产品事业部
 * @日期：2017年1月25日 上午9:04:47 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.OptionUtil;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设组织关系转出管理模块
 * @类功能描述: 基础设置Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年1月25日 上午9:04:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszAction extends SuperAction<JcszForm,JcszService>{
	private static final String KGZT_CLOSE = "0";
	private JcszService service = new JcszService();
	
	/**
	 * @描述:组织关系转出基础设置
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年1月25日 上午9:27:27
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JcszForm jcszForm = (JcszForm) form;
		JcszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(jcszForm, model);
		} else {
			jcszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("dtjs");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "dtjs_jcsz.do?method=jcsz");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcsz");
	}
}
