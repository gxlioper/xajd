/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午04:56:01 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
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
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块 
 * @类功能描述: TODO 辅导员任职管理 任职申请
 * @作者： zhangjw 
 * @时间： 2013-6-4 下午04:56:01 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyrzsqAction  extends SuperAction{
	
	private static final String url = "szdw_fdyrz_sq.do?method=gjcxWdsq";

	/**
	 * @描述:辅导员任职申请列表查询
	 * @作者：zhangjw
	 * @日期：2013-4-18 下午04:22:47
	 * @修改记录: 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward 返回类型 
	 * @throws Exception
	 */
	@SystemAuth(url = url)
	public ActionForward gjcxWdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzsqService service = new FdyrzsqService();
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		FormModleCommon.commonRequestSet(request);
		String path = "szdw_fdyrz_sq.do?method=gjcxWdsq";
		request.setAttribute("path", path);
		return mapping.findForward("gjcxFdyrz_wdsq");
		
	}
	
	/**
	 * @描述:辅导员任职申请
	 * @作者：zhangjw
	 * @日期：2013-6-5 上午10:39:42
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
	public ActionForward fdyrzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzsqService service = new FdyrzsqService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(myForm.getType()) || SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setZgh(user.getUserName());
			boolean result = service.fdyrzsq(myForm,user);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		HashMap<String,String> map = service.getFdyjbxx(user.getUserName());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", StringUtils.formatData(myForm));
		return mapping.findForward("fdyrzsq");
		
	}
	
	/**
	 * 
	 * @描述:TODO(辅导员任职申请更新)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-31 下午03:15:16
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
	public ActionForward fdyrzsqxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzsqService service = new FdyrzsqService();
		
		User user = getUser(request);
		if (UPDATE.equalsIgnoreCase(myForm.getType()) || SUBMIT.equalsIgnoreCase(myForm.getType())){
			myForm.setZgh(user.getUserName());
			boolean result = service.updateFdyrzsq(myForm,user);
			String messageKey = "";
			if(SUBMIT.equalsIgnoreCase(myForm.getType())){
				messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
			}else{
				messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			}
			response.getWriter().print(getJsonResult(messageKey,result));
			return null;
		}
		
		HashMap<String,String> map = service.getFdyjbxx(user.getUserName());
		request.setAttribute("rs", StringUtils.formatData(map));
		FdyrzsqForm updatemyForm = service.getModel(myForm);
		BeanUtils.copyProperties(myForm, StringUtils.formatData(updatemyForm));
		request.setAttribute("model", StringUtils.formatData(updatemyForm));
		return mapping.findForward("fdyrzsqxg");
		
	}
	
	/**
	 * @描述:辅导员任职列表
	 * @作者：zhangjw
	 * @日期：2013-6-6 上午10:42:36
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
	/*public ActionForward fdyrzsqbj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String bjdm = request.getParameter("bjdm");
		String type = request.getParameter("type");
		FdyrzsqService service = new FdyrzsqService();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("bjList");
		List<HashMap<String,String>> bjlist =  new ArrayList<HashMap<String,String>>();
		if(obj!=null && obj instanceof List){
			bjlist =(List<HashMap<String,String>>) obj;
			
		}
		bjlist =service.getBjxxBybjdm(bjdm,bjlist,type);
		String path = "szdw_fdyrz_sq.do?method=fdyrzsqbj";
		request.setAttribute("path", path);
		session.setAttribute("bjList", bjlist);
		return mapping.findForward("fdyrzbj");
	}*/
	
	/**
	 * @描述:辅导员任职 取消申请
	 * @作者：zhangjw
	 * @日期：2013-6-8 下午05:07:20
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
	public ActionForward fdyrzqxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		/*String sqids = request.getParameter("sqids");
		String[]sqid =null;
		if(sqids!=null && !"".equals(sqids)){
			sqid = sqids.split(",");
		}*/
		String sqid = request.getParameter("values");
		String lcid = request.getParameter("splcid");
		FdyrzsqService service = new FdyrzsqService();
		
		//只有刚提交并且第一级未审核的前提下，申请人可以撤销
		boolean result = service.cancelRecord(sqid,lcid);
		//boolean result = service.fdyrzqxsq(sqid);
		if(result){
			//更新业务状态为'未提交'
			FdyrzsqForm model = new FdyrzsqForm();
			model.setSqid(sqid);
			model.setSplc(lcid);
			ShlcDao shlcDao = new ShlcDao();
			if(Integer.valueOf(shlcDao.getExistTh(sqid))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			
			service.updateFdyrzsq(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}   
	/**
	 * @描述:验证辅导员任职申请
	 * @作者：zhangjw
	 * @日期：2013-7-4 下午02:46:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型
	 */
	public ActionForward yzFdyrzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		User user = getUser(request);
		myForm.setZgh(user.getUserName());
		FdyrzsqService service = new FdyrzsqService();
		response.getWriter().print(service.yzcssz(myForm));
		return null;
	}
	
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward submitFdyrzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyrzsqForm model = (FdyrzsqForm) form;
		String sqid = request.getParameter("values");
		String zgh = request.getParameter("zgh");
		model.setSqid(sqid);
		model.setZgh(zgh);
		FdyrzsqService service = new FdyrzsqService();
		boolean result = service.submitFdyrzsq(model);
    	String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS : MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}

	
	/**
	 * 
	 * @描述:验证申请是否在时间内
	 * @作者：cq [工号：785]
	 * @日期：2014-7-8 下午04:26:30
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
	public ActionForward yzFdyrzsqTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyrzsqService service = new FdyrzsqService();
		String shzt = request.getParameter("shzt");
		
		Map<String,String> resultmap = new HashMap<String,String>();
		resultmap.put("message", "true");
		
		if(null!=shzt&&Constants.YW_YTH.equals(shzt)){
			response.getWriter().print(resultmap);
		}else{
			response.getWriter().print(service.yzsqTime());
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述: 申请开关验证
	 * @作者：cq [工号：785]
	 * @日期：2014-5-27 上午11:02:05
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
	public ActionForward timeSwitch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		User user = getUser(request);
		myForm.setZgh(user.getUserName());
		FdyrzsqService service = new FdyrzsqService();
		response.getWriter().print(service.timeSwitch(myForm));
		return null;
	}
}
