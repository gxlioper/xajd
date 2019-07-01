/**
 * @部门:学工产品事业部
 * @日期：2013-12-30 下午03:31:40 
 */
package com.zfsoft.xgxt.rcsw.jqlx;

import java.util.Arrays;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 假期留校设置
 * @作者： 945
 * @时间： 2013-12-30 下午03:31:40
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class JqlxszAction extends SuperAction {
	
	private static final String url = "rcsw_jqlxsz.do";

	/**
	 * 
	 * @描述:假期留校基础设置
	 * @作者：945
	 * @日期：2013-12-30 下午04:19:17
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
	@SystemAuth(url = url)
	public ActionForward jqlxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxszModel myForm = (JqlxszModel) form;
		JqlxszService service = new JqlxszService();
		
		List<HashMap<String,String>> isnotList = new OptionUtil().getOptions(OptionUtil.ISNOT);//是否list
		request.setAttribute("kgList", isnotList);
		
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);//开启关闭
		request.setAttribute("onoffList", onoffList);
		//审核流程列表
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("rcsw");
		request.setAttribute("shlcList", shlc);
		String path = "rcsw_jqlxsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		JqlxszModel model = service.getModel(myForm);
		
		if (model != null){
			BeanUtils.copyProperties(myForm, model);
		}else{
			model = new JqlxszModel();
		}
		request.setAttribute("xxdm", Base.xxdm);
		request.setAttribute("jqlxList", new OptionUtil().getOptions(OptionUtil.JQLX));
		request.setAttribute("jqlxV", model.getJqlx());
		return mapping.findForward("jqlxsz");
	}

	/**
	 * 
	 * @描述:保存假期留校设置
	 * @作者：945
	 * @日期：2013-12-30 下午04:19:32
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
	@SystemLog(description="访问日常事务-假期留校-假期留校设置-保存")
	public ActionForward saveJqlxsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JqlxszModel myForm = (JqlxszModel) form;
		JqlxszService service = new JqlxszService();
		//2014-5-8 785 申请表当中已保存审批流程，不需要在进行控制 
//		JqlxszModel oldForm=service.getModel();
//		if(oldForm != null && Constants.OPEN.equals(myForm.getSqkg()) && !myForm.getSplc().equals(oldForm.getSplc())){
//			JqlxService  jqlxService = new JqlxService();
//			//判断是否有流程正在审核
//			boolean isUse=jqlxService.allowUpdateSetting();
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
