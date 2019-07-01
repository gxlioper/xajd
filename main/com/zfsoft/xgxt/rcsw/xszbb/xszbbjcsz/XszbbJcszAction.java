package com.zfsoft.xgxt.rcsw.xszbb.xszbbjcsz;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办模块
 * @类功能描述: TODO(学生证补办基础设置) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-16 下午02:47:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbJcszAction extends SuperAction {
	
	private static final String url = "rcsw_xszbb_jcsz.do";
	
	/**
	 * 
	 * @描述:TODO(学生证补办基础设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:19:40
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
	public ActionForward xszbbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbJcszForm myForm = (XszbbJcszForm) form;
		XszbbJcszService service = new XszbbJcszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_xszbb_jcsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		XszbbJcszForm model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}
		return mapping.findForward("xszbbJcsz");
	}

	
	
	/**
	 * 
	 * @描述:TODO(保存基础设置)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:19:52
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
	@SystemLog(description="访问日常事务-证件补办-证件补办基础设置-保存SPLC:{splc}")
	public ActionForward saveXszbbJcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszbbJcszForm myForm = (XszbbJcszForm) form;
		XszbbJcszService service = new XszbbJcszService();
//		XszbbJcszForm oldForm=service.getModel();
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			XszbbsqService  xszbbsqService = new XszbbsqService();
//			//判断是否有流程正在审核
//			boolean isUse=xszbbsqService.allowUpdateSetting();
//			if(!isUse){
//				response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_XSZBB_SHLC_EXIST));
//				return null;
//			}
//		}
		boolean result = service.saveJcsz(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
