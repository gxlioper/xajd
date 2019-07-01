/**
 * @部门:学工产品事业部
 * @日期：2016-10-26 上午09:31:11 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.cssz;

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
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务_考情管理_管理模块
 * @类功能描述: 考情管理参数设置Action
 * @作者： cq [工号:785]
 * @时间： 2016-10-26 上午09:31:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KqCsszAction extends SuperAction<KqCsszForm,KqCsszService> {
	
	
	private static final String KGZT_CLOSE = "0";

	private static final String url = "rcsw_zjsy_kqcssz.do";
	
	KqCsszService service = new KqCsszService();
	
	/**
	 * 
	 * @描述:基本参数设置
	 * @作者：cq [工号：785]
	 * @日期：2016-10-26 上午09:43:33
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
	public ActionForward jcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		KqCsszForm myForm = (KqCsszForm) form;
		KqCsszForm model = service.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			if (StringUtil.isNull(model.getSqkg())) {
				model.setShkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(myForm, model);
		} else {
			myForm.setSqkg(KGZT_CLOSE);
			myForm.setShkg(KGZT_CLOSE);
		}
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path", "rcsw_zjsy_kqcssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("jcsz");
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关状态
	 * @作者：cq [工号：785]
	 * @日期：2016-10-27 上午09:26:26
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
	public ActionForward checkSqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		KqCsszForm myForm = (KqCsszForm) form;
		String[] sqshkg = service.getSqShKg();
		String kg = "0";
		if(myForm.getType().equals("sq")){
			kg = sqshkg==null?"0":sqshkg[0];
		}else{
			kg = sqshkg==null?"0":sqshkg[1];
		}
		response.getWriter().print(kg);
		return null;
	} 

}
