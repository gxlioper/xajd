package com.zfsoft.xgxt.dtjs.dxbmgl.shlcsz;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.utils.FormModleCommon;
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;

/** 
 * @功能描述：党校报名审核流程设置action
 * @author：杨珩 【1346】
 * @date：2017-11-1 上午09:28:08 
 */
public class DxbmshlcszAction extends SuperAction<DxbmshlcszForm, DxbmshlcszService> {
	private DxbmshlcszService service = new DxbmshlcszService();
	private XtwhShlcService shlcService = new XtwhShlcService();
	/** 
	 * @功能描述：党校报名审核流程设置跳转
	 * @author：杨珩 【1346】
	 * @date：2017-11-2 下午04:33:48 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshlcsz(ActionMapping mapping,ActionForm form,HttpServletRequest request, HttpServletResponse response)throws Exception {
		String rs=service.dxbmshlcszCx();
		DxbmshlcszForm model = (DxbmshlcszForm) form;
		model.setShl(rs);
		request.setAttribute("shl", rs);
		List<HashMap<String, String>> shlcList = shlcService.getShlcByDjlx("dtjs");//获取党团建设类型的审核 流程列表
		request.setAttribute("shlcList", shlcList);
		// 获取用户（是否可写）权限 和title
		request.setAttribute("path", "dxbmgl_dxbmshlcsz?method=dxbmshlcsz");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("dxbmshlcsz");
	}
	/** 
	 * @功能描述：保存审批流
	 * @author：杨珩 【1346】
	 * @date：2017-11-3 上午09:53:50 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws 
	 */
	public ActionForward dxbmshlcszBc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DxbmshlcszForm model = (DxbmshlcszForm) form;
		boolean result=service.dxbmshlcszBc(model);
		//以下为公共配置项
		request.setAttribute("result", result);
		dxbmshlcsz(mapping, form, request, response);
		return mapping.findForward("dxbmshlcsz");
	}
}
