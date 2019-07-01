package com.zfsoft.xgxt.rcsw.txhd.xmxxsh;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import xgxt.xtwh.comm.splc.XtwhShlcService;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.rcsw.txhd.dmwh.TxhdDmwhService;

public class XmxxshAction extends SuperAction {
	
	private static final String url = "rcsw_txhd_xmxxsh.do";
	
	/**
	 * @描述:项目审核
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
	public ActionForward xmxxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxshForm model = (XmxxshForm) form;
		XmxxshService service = new XmxxshService();
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询审核数据
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_txhd_xmxxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmxxshManage");
	}
	
	/**
	 * @描述:项目单个审核
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
	@SystemLog(description="访问日常事务-团学活动-项目审核-审核SQID:{sqid}")
	public ActionForward xmxxshDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxshForm model = (XmxxshForm) form;
		XmxxshService service = new XmxxshService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		XmxxshForm myModel = service.getModel(model);
		String sqrssx = myModel.getSqrssx();
		String shrssx = myModel.getShrssx();
		if(null!=sqrssx && !"".equals(sqrssx)){
			myModel.setSqrssx(sqrssx+" 人");
		}
		if(null!=shrssx && !"".equals(shrssx)){
			myModel.setShrssx(shrssx+" 人");
		}
		request.setAttribute("data", StringUtils.formatData(myModel));
		XtwhShlcService shlcService = new XtwhShlcService();
		List<HashMap<String, String>> shlc = shlcService.getShlcByDjlx("rcsw");// 基本设置中审核流程列表的取值通用方法
		request.setAttribute("shlcList", shlc);
		TxhdDmwhService txhdDmwhService = new TxhdDmwhService();
		List<HashMap<String, String>> lbList = txhdDmwhService.getLblist();// 项目类别
		String hdlbmc=null;
		for(HashMap<String, String> hm:lbList){
			if(myModel.getLbdm().equals(hm.get("lbdm"))){
				hdlbmc=hm.get("lbmc");
			}
		}
		request.setAttribute("hdlbmc", hdlbmc);
		BeanUtils.copyProperties(model, StringUtils.formatData(myModel));
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("spzt", "true");
		request.setAttribute("xxdm", Base.xxdm);
		return mapping.findForward("xmxxshDgsh");
	}
	
	/**
	 * @描述:撤销
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
	@SystemLog(description="访问日常事务-团学活动-项目审核-撤销SQID:{sqid}")
	public ActionForward cancelXmxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxshForm model = (XmxxshForm) form;
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		XmxxshService service = new XmxxshService();
		boolean isSuccess = service.newCancelSh(model, user);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * @描述:批量审核保存
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
	public ActionForward xmxxshPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XmxxshForm model = (XmxxshForm) form;
		XmxxshService service = new XmxxshService();
		
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		return mapping.findForward("xmxxshPlsh");
	}
	
}
