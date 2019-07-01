/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:08 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqForm;
import com.zfsoft.xgxt.xstgl.stgl.stsq.StsqService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:38:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StshAction extends SuperAction<StshForm, StshService>{
	private StshService service = new StshService();
	private StsqService stsqService = new StsqService();
	
	private static final String url = "stgl_stgl_stsh.do";
	
	/**
	 * 
	 * @描述:获取审核列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4上午09:23:10
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
	public ActionForward getStshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StshForm model = (StshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		String path = "stgl_stgl_stsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getStshList");
	}
	/**
	 * 
	 * @描述:单个审核
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-8-4下午04:17:03
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
	public ActionForward stDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StshForm model = (StshForm) form;
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		StsqForm stsq = stsqService.getModel(model.getSqid());
		HashMap<String,String> sqxxMap = stsqService.getSqxx(stsq);
		//指导老师信息
		List<HashMap<String,String>> ZdlsInfoList=stsqService.getZdlsInfo(stsq);
		request.setAttribute("ZdlsInfoList",ZdlsInfoList);
		sqxxMap.put("shid", request.getParameter("shid"));
		request.setAttribute("rs", StringUtils.formatData(sqxxMap));
		return mapping.findForward("stDgsh");
	}
	/**
	 * 
	 * @描述:批量审核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4下午04:07:50
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
	public ActionForward stPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StshForm model = (StshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("stPlsh");
	}
	/**
	 * 
	 * @描述:最后一级审核撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4下午04:08:06
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StshForm model = (StshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	

}
