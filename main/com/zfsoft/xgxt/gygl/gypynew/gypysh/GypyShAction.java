package com.zfsoft.xgxt.gygl.gypynew.gypysh;

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
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqForm;
import com.zfsoft.xgxt.gygl.gypynew.gypysq.GypySqService;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsh.LxshService;

public class GypyShAction extends SuperAction<GypyShForm, GypyShService> {
	private GypyShService service = new GypyShService();
	private static final String url = "gygl_gypynew_gypysh.do";
	/**
	 * 
	 * @描述: 公寓评优审核查询跳转页面
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午09:50:19
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
	public ActionForward getGypyShlist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("shcx");
	}
	
	/**
	 * 
	 * @描述: 审核查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午09:51:37
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
	public ActionForward searchForShCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypyShForm model = (GypyShForm) form;
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
	
	/**
	 * 
	 * @描述: 公寓评优审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午09:56:57
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
	public ActionForward gypySh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypyShForm myForm = (GypyShForm) form;
		GypyShForm model = service.getModel(myForm);
		model.setShid(myForm.getShid());
		if(model != null){
			BeanUtils.copyProperties(myForm, StringUtils.formatDataView(model) );
		}
		GypySqForm sqForm = new GypySqForm();
		BeanUtils.copyProperties(sqForm, model);
		request.setAttribute("qsxx",new GypySqService().getQshxx(sqForm));
		request.setAttribute("wjxx",new GypySqService().getWjxx(sqForm));
		request.setAttribute("isLast", service.checkIsLastGw(request.getParameter("gwid"), model.getSplc())+"");
		request.setAttribute("gxsj", GetTime.getTimeByFormat("yyyy-mm-dd"));
		return mapping.findForward("gypysh");
	}
	
	/**
	 * 
	 * @描述: 保存公寓评优审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午10:15:31
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
	public ActionForward saveGypySh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypyShForm model = (GypyShForm)form;
		User user = getUser(request);
		// 保存单个审核
		GypyShService Transervice = TransactionControlProxy.newProxy(new GypyShService());
		boolean result = Transervice.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 保持批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-2 上午10:48:20
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
	public ActionForward savePlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypyShForm model = (GypyShForm)form;
		User user = getUser(request);
		GypyShService tranService = TransactionControlProxy.newProxy(new GypyShService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	/**
	 * 
	 * @描述: 公寓评优批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午10:18:10
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
	public ActionForward gypyPlsh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("gxsj", GetTime.getTimeByFormat("yyyy-mm-dd"));
		return mapping.findForward("gypyplsh");
	}
	
	/**
	 * 
	 * @描述: 最后一级撤销撤销审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午10:21:22
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
	public ActionForward cancelSh(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		GypyShForm model = (GypyShForm) form;
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
	 * @描述: 撤销审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-31 上午10:24:07
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
	public ActionForward cxshnew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		GypyShForm model =new GypyShForm();
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
}
