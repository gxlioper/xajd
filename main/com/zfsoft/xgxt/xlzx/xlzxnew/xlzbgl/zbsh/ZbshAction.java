package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsh;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.transaction.TransactionControlProxy;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.util.ShlcUtil;
import com.zfsoft.xgxt.jskp.lxsh.LxshForm;
import com.zfsoft.xgxt.jskp.lxsh.LxshService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbjg.ZbjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb.ZbsbForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb.ZbsbService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.zqsz.ZqszService;

public class ZbshAction extends SuperAction<ZbshForm,ZbshService> {
	private final String url = "xg_xlzxnew_zbsh.do";
	private ZbshService service = new ZbshService();
	public ActionForward getZbshListCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		//审批流程有无啥子控制增删改按钮
		request.setAttribute("path", url);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		searchModel.setSearch_tj_xq(new String[] { Base.currXq });
		request.setAttribute("searchTj", searchModel);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zbshcx");
	}
	
	/**
	 * 
	 * @描述: 周报审核查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午02:07:43
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
	public ActionForward searchForzbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbshForm myForm = (ZbshForm)form;
		User user = getUser(request);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 单个审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午03:36:44
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
	public ActionForward zbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbshForm myForm = (ZbshForm)form;
		ZbshForm model = service.getModel(myForm.getSbsqid());
		BeanUtils.copyProperties(myForm, model);
		myForm.setShid(request.getParameter("shid"));
		ZqszForm zbrc = new ZqszService().getModel(model.getSbzbid());
		request.setAttribute("zbrc",zbrc);
		request.setAttribute("xqmc",Base.getXqmcForXqdm(zbrc.getXq()));
		request.setAttribute("bjxx", new ZbjgService().getTeaBjxx(model.getBjdm()));
		request.setAttribute("wtryInfo", new ZbsbService().getZbWtryInfo(myForm.getSbsqid()));
		return mapping.findForward("zbsh");
	}
	
	/**
	 * 
	 * @描述: 周报批量审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午03:43:03
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
	public ActionForward zbPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		return mapping.findForward("zbplsh");
	}
	
	/**
	 * 
	 * @描述: 单个审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午03:57:42
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
	public ActionForward saveZbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbshForm model = (ZbshForm)form;
		User user = getUser(request);
		// 保存单个审核
		ZbshService Transervice = TransactionControlProxy.newProxy(new ZbshService());
		boolean result = Transervice.saveSh(model, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午03:58:35
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
	public ActionForward saveZbplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		ZbshForm model = (ZbshForm)form;
		User user = getUser(request);
		ZbshService tranService = TransactionControlProxy.newProxy(new ZbshService());
		String message = tranService.savePlsh(model, user);
		response.getWriter().print(getJsonMessage(message));
		return null;
	}
	
	/**
	 * 
	 * @描述: 最后一级审核
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:05:38
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
		ZbshForm model = (ZbshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSbsqid(sqid);
		// 最后一级撤销
		boolean isSuccess = service.cancel(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 审核撤销
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-4 下午04:05:11
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
		ZbshForm model =new ZbshForm();
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
