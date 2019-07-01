/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 上午08:38:54 
 */  
package com.zfsoft.xgxt.xstgl.rtgl.rtsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqForm;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-8-14 上午08:38:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RtshAction extends SuperAction<RtshForm, RtshService> {
	private RtshService service = new RtshService();
	private final String RTSQ ="rtsq";
	RtsqService rtsqservice = new RtsqService();
	
	private static final String url = "stgl_rtgl_rtsh.do";
	
	@SystemAuth(url = url)
	public ActionForward getRtshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtshForm model = (RtshForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", "stgl_rtgl_rtsh.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getRtshList");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward Rtdgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtshForm model = (RtshForm) form;
		boolean result = false;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		if (SAVE.equalsIgnoreCase(model.getType())) {
			User user = getUser(request);
			// 保存单个审核
			result = service.saveSh(model, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		request.setAttribute("form", model);
		RtsqForm rtsq = rtsqservice.getModel(model.getRtid());
		HashMap<String, String> stxx = rtsqservice.getStxxMap(rtsq);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		RtsqForm rtsQ = new RtsqForm();
		rtsQ.setRtid(model.getRtid());
		User user= getUser(request);
		HashMap<String, String> stcyxx = rtsqservice.getStxxCyMap(rtsQ, user);
		request.setAttribute("stcyxx", stcyxx);
		return mapping.findForward("rtDgsh");
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward  Rtplsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtshForm model = (RtshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("rtPlsh");
	}
	
	/**
	 * @系统名称: 学生工作管理系统
	 * @模块名称: XXXX管理模块
	 * @类功能描述: TODO(这里用一句话描述这个类的作用)
	 * @作者： 喻鑫源[工号:1206]
	 * @时间： 2015-7-20 下午03:54:47
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtshForm model = (RtshForm) form;
		String rtid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model = service.getModel(rtid);
		model.setShzt(shzt);
		model.setRtid(rtid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * @系统名称: 学生工作管理系统
	 * @模块名称: XXXX管理模块
	 * @类功能描述: TODO(这里用一句话描述这个类的作用)
	 * @作者： 喻鑫源[工号:1206]
	 * @时间： 2015-7-20 下午03:54:47
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RtshForm model =new RtshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
		

		// 审核撤销成功
		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", MessageUtil.getText(messageKey));
		map.put("cancelFlg", cancelFlg);
		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}
	
	@SystemAuth(url = url)
	public ActionForward RtshView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RtshForm myForm = (RtshForm) form;
		RtshForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置gzkhKhjgXx
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(RTSQ);
		request.setAttribute("jbxxList", jbxxList);
		RtsqForm rtsq = rtsqservice.getModel(model.getRtid());
		HashMap<String, String> stxx = rtsqservice.getStxxMap(rtsq);
		request.setAttribute("stxx", StringUtils.formatData(stxx));
		request.setAttribute("form",model);
		RtsqForm rtsQ = new RtsqForm();
		rtsQ.setRtid(model.getRtid());
		User user= getUser(request);
		HashMap<String, String> stcyxx = rtsqservice.getStxxCyMap(rtsQ, user);
		request.setAttribute("stcyxx", stcyxx);
		return mapping.findForward("view");
	}
}
