package com.zfsoft.xgxt.jskp.lxsh;

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

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.dmwh.DmwhService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqService;

public class LxshAction extends SuperAction<LxshForm, LxshService> {
	private LxshService service = new LxshService();
	private final String url = "pjpy_jskp_lxsh.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * 
	 * @描述: 立项审核查询跳转
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 上午11:58:39
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
	public ActionForward getLxshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("lxshcx");
	}
	
	/**
	 * 
	 * @描述: 立项审核查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 下午01:48:34
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
	public ActionForward searchForLxshCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		// 查询
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getPageList(model,user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	/**
	 * 
	 * @描述: 立项审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 上午11:59:28
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
	public ActionForward lxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		LxshForm lxsq = service.getModel(model);
		BeanUtils.copyProperties(model, lxsq);
		request.setAttribute("xmlbList", new DmwhService().getXmlbList());
		request.setAttribute("xmlbmc",new DmwhService().getModel(lxsq.getXmlb()).getXmlbmc());
		//立项时间最大值，取系统当前日期
		request.setAttribute("maxtime", GetTime.getTimeByFormat(DATE_FORMAT));
		User user = getUser(request);
		request.setAttribute("fzrxm", user.getRealName());
		request.setAttribute("fzr", user.getUserName());
		List<HashMap<String, String>> xhList = new LxsqService().getXmcyryXhs(lxsq.getSqid());
		request.setAttribute("xhList", xhList);
	    request.setAttribute("bmmc", new LxsqService().getBmmc(lxsq.getZdbm()).get("bmmc"));
	    //获取审核字段
	    HashMap<String,String> shzdxx = service.getLastshzd(lxsq.getSqid());
	    if(  shzdxx == null || shzdxx.isEmpty()){
	    	shzdxx = new HashMap<String, String>();
	    	shzdxx.put("zxf", lxsq.getZxf());
	    	shzdxx.put("zdf", lxsq.getZdf());
	    }else{
	    	shzdxx.put("zxf", shzdxx.get("zd3").split("-")[0]);
	    	shzdxx.put("zdf", shzdxx.get("zd3").split("-")[1]);
	    }
	    request.setAttribute("shzdxx",shzdxx);
		return mapping.findForward("lxsh");
	}
	
	/**
	 * 
	 * @描述: 保存立项审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-18 下午05:08:06
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
	public ActionForward saveLxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		User user = getUser(request);
		// 保存单个审核
		LxshService Transervice = TransactionControlProxy.newProxy(new LxshService());
		boolean result = Transervice.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述:批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午10:23:40
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
	public ActionForward lxPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		return mapping.findForward("zsplsh");
	}
	
	/**
	 * 
	 * @描述: 保存批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-19 上午10:44:21
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		LxshForm model = (LxshForm)form;
		User user = getUser(request);
		LxshService tranService = TransactionControlProxy.newProxy(new LxshService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
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
		LxshForm model = (LxshForm) form;
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
		LxshForm model =new LxshForm();
		String shid = request.getParameter("shid");
		String splc = request.getParameter("shlc");
		model.setShlc(splc);
		model.setShid(shid);
		User user = getUser(request);
		HashMap<String,String> shxx = ShlcUtil.getShxx(shid);
		//验证是否有学生提交上报数据，有的话，返回，提示无法撤销
		if(service.isStuSbTj(shxx.get("ywid"))){
			throw new SystemException(MessageKey.XG_ZJDX_JSKP_XMCX_CANCEL_CX);
		}
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
	
}
