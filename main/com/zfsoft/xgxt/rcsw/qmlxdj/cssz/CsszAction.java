/**
 * @部门:学工产品事业部
 * @日期：2017-1-10 下午04:49:07 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.cssz;

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

import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszForm;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.OptionUtil;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-10 下午04:49:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszAction extends SuperAction<CsszForm,CsszService> {
	 private static final String KGZT_CLOSE = "0";
		private CsszService service = new CsszService();
		/**
		 * 
		 * @描述: 参数设置
		 * @作者：yxy[工号：1206]
		 * @日期：2017-1-4 上午10:02:10
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
			CsszForm myForm = (CsszForm) form;
			CsszForm model = service.getModel();
			if (model != null) {
				if (StringUtil.isNull(model.getSqkg())) {
					model.setSqkg(KGZT_CLOSE);
				}
//				if (StringUtil.isNull(model.getSqkg())) {
//					model.setShkg(KGZT_CLOSE);
//				}
				BeanUtils.copyProperties(myForm, model);
			} else {
				myForm.setSqkg(KGZT_CLOSE);
//				myForm.setShkg(KGZT_CLOSE);
			}
			XtwhShlcService shlcService = new XtwhShlcService();
			List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
			request.setAttribute("shlcList", shlc);
			List<HashMap<String, String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);// 开启关闭
			request.setAttribute("onoffList", onoffList);
			request.setAttribute("path", "rcsw_qmlxcssz.do");
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("jcsz");
		}
}
