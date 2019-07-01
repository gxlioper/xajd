/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:30 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.cssz;

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
import common.newp.StringUtil;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-27 上午10:17:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class CsszAction extends SuperAction<CsszForm, CsszService> {
	
	private static final String url = "sztz_jxgl_cssz.do";
	
	/**
	 * 
	 * @描述:参数设置
	 * @作者：柳俊[工号：1282]
	 * @日期：2015-7-9 下午04:40:09
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
	public ActionForward cssz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm myForm = (CsszForm) form;
		CsszService service = new CsszService();
		CsszForm model = service.getModel();
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("xsztz");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		request.setAttribute("path", "sztz_jxgl_cssz.do");
		FormModleCommon.commonRequestSet(request);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
		}		
		return mapping.findForward("cssz");
	}
	/**
	 * 参数设置保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CsszForm model = (CsszForm) form;
		CsszService service = new CsszService();
		boolean result = false;
		service.deleteJcsz();
		result = service.runInsert(model);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

}
