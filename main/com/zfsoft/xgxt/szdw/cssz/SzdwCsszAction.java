/**
 * @部门:学工产品事业部
 * @日期：2013-7-2 下午06:54:16 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.String.StringUtils;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:参数设置 Action
 * @作者： zhangjw
 * @时间： 2013-7-2 下午06:53:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class SzdwCsszAction  extends SuperAction{

	private SzdwCsszService service = new SzdwCsszService();
	
	private static final String url = "szdw_cssz.do?method=bbsjCssz";
	
	/**
	 * @描述:思政队伍 基本参数设置
	 * @作者：zhangjw
	 * @日期：2013-7-3 下午04:42:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	//此功能一分为二，分为辅导员设置、班干部设置
	/*public ActionForward cssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取辅导员任职申请参数
		SzdwCsszForm rzsqModel = service.getModel(CsszUtil.SZDW_FDYRZSQ);
		//获取辅导员培训申请参数
		SzdwCsszForm pxModel = service.getModel(CsszUtil.SZDW_FDYPXSQ);
		//获取学生干部职务申请参数
		SzdwCsszForm zwModel = service.getModel(CsszUtil.SZDW_XSGBZWSQ);
		request.setAttribute("splcList", ShlcUtil.getShlcByGnmk("szdw"));
		request.setAttribute("rzsqModel", rzsqModel);
		request.setAttribute("pxModel", pxModel);
		request.setAttribute("zwModel", zwModel);
		request.setAttribute("path", "szdw_cssz.do?method=cssz");
		return mapping.findForward("szdw_cssz");
	}*/
	/**
	 * @描述:思政队伍 -基础设置-辅导员参数设置
	 * @作者：wanghj
	 * @日期：2014-1-3 上午10:38:15
	 */
	@SystemAuth(url = "szdw_cssz.do?method=fdycssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward fdycssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取辅导员任职申请参数
		SzdwCsszForm rzsqModel = service.getModel(CsszUtil.SZDW_FDYRZSQ);

		XtwhShlcService shlcService = new XtwhShlcService();
		//获取思政队伍审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("rzsqModel", StringUtils.formatData(rzsqModel));
		request.setAttribute("path", "szdw_cssz.do?method=fdycssz");
		return mapping.findForward("fdycssz");
	}
	/**
	 * 编班时间参数设置
	 */
	@SystemAuth(url = "szdw_cssz.do?method=bbsjCssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bbsjCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			if("0".equals(myForm.getKg())){
				myForm.setKssj("");
				myForm.setJssj("");
			}
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取编班时间参数
		SzdwCsszForm bbsjModel = service.getModel(CsszUtil.SZDW_BBSJ);
		
		request.setAttribute("bbsjModel", bbsjModel);
		request.setAttribute("path", "szdw_cssz.do?method=bbsjCssz");
		return mapping.findForward("bbsjCssz");
	}
	
	/**
	 * @描述:思政队伍 -基础设置-班干部参数设置
	 * @作者：wanghj
	 * @日期：2014-1-3 上午10:42:17
	 */
	@SystemAuth(url = "szdw_cssz.do?method=bgbcssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward bgbcssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取学生干部职务申请参数
		SzdwCsszForm zwModel = service.getModel(CsszUtil.SZDW_XSGBZWSQ);
		XtwhShlcService shlcService = new XtwhShlcService();
		//获取思政队伍审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("zwModel", StringUtils.formatData(zwModel));
		request.setAttribute("path", "szdw_cssz.do?method=bgbcssz");
		return mapping.findForward("bgbcssz");
	}
	
	/**
	 * @描述:思政队伍 -基础设置-辅导员参数设置
	 * @作者：wanghj
	 * @日期：2014-1-3 上午10:38:15
	 */
	@SystemAuth(url = "szdw_cssz.do?method=fdypxCssz",rewritable=ReadWrite.WRITEABLE)
	public ActionForward fdypxCssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SzdwCsszForm myForm = (SzdwCsszForm) form;
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			boolean result = service.runUpdate(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		//获取辅导员培训申请参数
		SzdwCsszForm pxModel = service.getModel(CsszUtil.SZDW_FDYPXSQ);
		XtwhShlcService shlcService = new XtwhShlcService();
		//获取思政队伍审核流
		List<HashMap<String,String>> shlc = shlcService.getShlcByDjlx("szdw");
		request.setAttribute("shlcList", shlc);
		request.setAttribute("pxModel", StringUtils.formatData(pxModel));
		request.setAttribute("path", "szdw_cssz.do?method=fdycssz");
		
		return mapping.findForward("fdypxCssz");
	}
	
	
	
	
}
