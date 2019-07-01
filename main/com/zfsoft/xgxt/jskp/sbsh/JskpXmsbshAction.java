package com.zfsoft.xgxt.jskp.sbsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 每日工作考核管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-7 下午04:10:33 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JskpXmsbshAction extends SuperAction<JskpXmsbshForm,JskpXmsbshService>{
	private final String CJFF="cjff";
	private JskpXmsbshService service = new JskpXmsbshService();
	private static final String url = "jskp_xmsh.do";
	
	/**
	 * 
	 * @描述:审核信息列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-8 下午01:49:50
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
	public ActionForward getSbshList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbshForm model = (JskpXmsbshForm) form;
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
		request.setAttribute("path", url);
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("sbshList");
	}
	/**
	 * 
	 * @描述:申报审核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-7 下午06:07:24
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
	public ActionForward sbDgsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		if (!StringUtil.isNull(model.getSqid())) {
			HashMap<String, String> xmsbInfo = service.getSbshInfo(model);
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(xmsbInfo.get("xh"));
			request.setAttribute("jbxx", xsjbxx);
			
			request.setAttribute("rs", StringUtils.formatData(xmsbInfo));
		}
	
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(CJFF);
		request.setAttribute("jbxxList", jbxxList);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		/*取审核状态表中的最新一条记录的分数*/
		HashMap<String, String> shxxLevel = service.getLevelXxBySqid(model);
		request.setAttribute("shxxLevel", shxxLevel);
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("sbDgsh");
	}
	@SystemAuth(url = "jskp_xmsh.do",rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问纪实考评--申报审核-审核保存-GUID:{sqid}")
	public ActionForward saveSbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		JskpXmsbshService service = TransactionControlProxy.newProxy(new JskpXmsbshService());
		
		User user = getUser(request);
		// 保存单个审核
		boolean result = service.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 
	 * @描述:批量审核
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-27 下午03:59:11
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
	public ActionForward sbPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbshForm model = (JskpXmsbshForm) form;
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			JskpXmsbshService shService = TransactionControlProxy.newProxy(new JskpXmsbshService());
			String message = shService.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		HashMap<String, String> shxhList = service.getShxhForId(request.getParameter("id"));
		request.setAttribute("shxhList", shxhList);
		request.setAttribute("zdf", request.getParameter("zdf"));
		request.setAttribute("zxf", request.getParameter("zxf"));
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		return mapping.findForward("sbPlsh");
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
		JskpXmsbshForm model = (JskpXmsbshForm) form;
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
	/**
	 * 
	 * @描述:审核撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-1-20 下午06:50:51
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JskpXmsbshForm model =new JskpXmsbshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		
//		String cancelFlg = service.cxshnew(shxx.get("ywid"),model,user);
//		
//
//		// 审核撤销成功
//		String messageKey = Constants.CANCLE_FLG_SUCCESS.equals(cancelFlg)
//				|| Constants.CANCLE_FLG_SUCCESS_ZHYJ.equals(cancelFlg) ? MessageKey.SYS_CANCEL_SUCCESS
//				: MessageKey.SYS_CANCEL_FAIL;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("message", MessageUtil.getText(messageKey));
//		map.put("cancelFlg", cancelFlg);
//		response.getWriter().print(JSONObject.fromObject(map));
		return null;
	}


}
