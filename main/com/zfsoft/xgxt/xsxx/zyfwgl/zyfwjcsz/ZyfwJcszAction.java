/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 上午10:24:12 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.util.OptionUtil;

import common.newp.StringUtil;
import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务基础设置Action
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 上午10:24:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJcszAction extends SuperAction<ZyfwJcszForm,ZyfwJcszService>{
	
	private static final String KGZT_CLOSE = "0";
	private static final String url = "xsxx_zyfwgl_jcsz.do?method=zyfwJcsz";
	
	/**
	 * @描述:转到志愿服务管理-志愿服务基础设置页面
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月4日 上午11:03:50
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
	public ActionForward zyfwJcsz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZyfwJcszForm zyfwJcszForm = (ZyfwJcszForm) form;
		ZyfwJcszService zyfwJcszService = new ZyfwJcszService();
		
		ZyfwJcszForm model = zyfwJcszService.getModel();
		if (model != null) {
			if (StringUtil.isNull(model.getSqkg())) {
				model.setSqkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(zyfwJcszForm, model);
		} else {
			zyfwJcszForm.setSqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsxx");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
		request.setAttribute("onoffList", onoffList);
		request.setAttribute("path",url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zyfwJcsz");
	}
	
}
