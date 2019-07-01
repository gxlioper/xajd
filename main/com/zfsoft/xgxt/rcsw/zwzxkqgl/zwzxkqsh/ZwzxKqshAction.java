/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午02:38:08 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsh;

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
import com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz.JcszService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqjg.ZwzxKqjgService;
import com.zfsoft.xgxt.rcsw.zwzxkqgl.zwzxkqsq.ZwzxKqsqService;

import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午02:38:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwzxKqshAction extends SuperAction<ZwzxKqshForm, ZwzxKqshService>{
	private ZwzxKqjgService kqjgService = new ZwzxKqjgService();
	private ZwzxKqshService service = new ZwzxKqshService();
	
	private static final String url = "rcsw_zwzxkq_kqsh.do";
	
	/**
	 * 
	 * @描述:获取审核列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-27 上午09:23:10
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
	public ActionForward getKqshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqshForm model = (ZwzxKqshForm) form;
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
		JcszService  jcszService = new JcszService();
		String[] sqshkg = jcszService.getSqShKg();
		request.setAttribute("shkg", sqshkg==null?"0":sqshkg[1]);
		String path = "rcsw_zwzxkq_kqsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		// =========== 根据菜单自动更新字段名称 begin =============
		String gnmkmc = (String) request.getAttribute("gnmkmc");
		String ccrqTitle = "填报日期";
		String cclxTitle = "填报事项";
		String jlrTitle = "填报人";
		if(gnmkmc.contains("考勤")){
			ccrqTitle = "抽查日期";
			cclxTitle = "抽查类型";
			jlrTitle = "填写人";
		}
		request.setAttribute("ccrqTitle", ccrqTitle);
		request.setAttribute("cclxTitle", cclxTitle);
		request.setAttribute("jlrTitle", jlrTitle);
		// =========== 根据菜单自动更新字段名称 end =============
		return mapping.findForward("kqshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward kqDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqshForm model = (ZwzxKqshForm) form;
		if (!StringUtil.isNull(model.getSqid())) {
			// 加载学生基本信息
			HashMap<String, String> infoList = service.getKqshInfo(model.getSqid());
			if("2297".equals(Base.xxdm)){
				ZwzxKqsqService sqService = new ZwzxKqsqService();
				infoList.put("ydrs", sqService.getYdrsSzly(infoList.get("bjdm")));
			}
			request.setAttribute("rs", infoList);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			boolean result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		//查询缺勤学生信息
		List<HashMap<String,String>> qqxsList = kqjgService.getQqxsList(model.getSqid());
		request.setAttribute("qqxsList", qqxsList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("kqDgsh");
	}
	/**
	 * 
	 * @描述:批量审核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 下午04:07:50
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
	public ActionForward kqPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ZwzxKqshForm model = (ZwzxKqshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("kqPlsh");
	}
	/**
	 * 
	 * @描述:最后一级审核撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 下午04:08:06
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
		ZwzxKqshForm model = (ZwzxKqshForm) form;
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
