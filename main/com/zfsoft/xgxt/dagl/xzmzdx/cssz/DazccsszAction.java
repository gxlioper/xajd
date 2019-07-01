/**
 * @部门:学工产品(1)部
 * @日期：2018-4-27 下午02:36:37 
 */  
package com.zfsoft.xgxt.dagl.xzmzdx.cssz;

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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.OptionUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理管理模块
 * @类功能描述: 档案转出参数设置
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-27 下午02:37:31 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DazccsszAction extends SuperAction{
	private static final String url = "xsxx_dagl_cssz.do";
	private DazccsszService service = new DazccsszService();
	
	/**
	 * @描述: 参数设置
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-27 下午05:31:22
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
	public ActionForward getDazccsszList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazccsszForm myForm = (DazccsszForm)form;
		
		/*开启关闭*/
		List<HashMap<String,String>> onoffList = new OptionUtil().getOptions(OptionUtil.ONOFF);
		request.setAttribute("onoffList", onoffList);
		
		/*审核流程列表*/
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("xsxx");
		request.setAttribute("shlcList", shlc);
		
		/*传回path*/
		String path = "xsxx_dagl_cssz.do";
		request.setAttribute("path", path);
		
		DazccsszForm model = service.getModel(myForm);
		if (model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}else{
			model = new DazccsszForm();
		}
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dazccsszList");
	}
	
	/**
	 * @描述: 参数设置保存
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-7 下午03:57:59
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
	public ActionForward dazccsszSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		DazccsszForm model = (DazccsszForm)form;
		boolean result = service.dazccsszSave(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
