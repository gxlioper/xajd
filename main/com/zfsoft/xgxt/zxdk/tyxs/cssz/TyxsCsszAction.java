/**
 * @部门:学工产品事业部
 * @日期：2015-4-8 上午10:23:06 
 */  
package com.zfsoft.xgxt.zxdk.tyxs.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCssz;
import com.zfsoft.xgxt.zxdk.cssz.ZxdkCsszService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 冯兰英[工号：1177]
 * @时间： 2015-4-8 上午10:23:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TyxsCsszAction extends SuperAction<TyxsCssz, TyxsCsszService> {
	/**退役学生入学学费资助参数设置*/
	private static final String KGZT_CLOSE = "0";
	private Log logger = LogFactory.getLog(TyxsCsszAction.class);
	
	private static final String url = "zxdk_tyxs_cssz.do";
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = " 退役学生入学学费资助-参数设置")
	public ActionForward tyxsCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("start");
		TyxsCssz csszForm = (TyxsCssz) form;
		
		TyxsCsszService service = getService();
		TyxsCssz model = service.getModel();
		
		if (model != null){
			if (StringUtil.isNull(model.getXfzzsqkg())){
				
				model.setXfzzsqkg(KGZT_CLOSE);
			}
			
			if (StringUtil.isNull(model.getXfzzshkg())){
				model.setXfzzshkg(KGZT_CLOSE);
			}
			BeanUtils.copyProperties(csszForm, model);
		} else {
			csszForm.setXfzzshkg(KGZT_CLOSE);
			csszForm.setXfzzsqkg(KGZT_CLOSE);
		}
		
		XtwhShlcService shlcService = new XtwhShlcService();
		// 基本设置中审核流程列表的取值通用方法
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("zxdk");
		request.setAttribute("shlcList", shlc);
		
		
		List<HashMap<String,String>> onOff = new OptionUtil().getOptions(OptionUtil.ONOFF);
		logger.info(onOff);
		logger.info("model"+model.toString());
		request.setAttribute("onOff", onOff);
		request.setAttribute("path", "zxdk_tyxs_cssz.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tyxsCssz");
	}
	
}
