/**
 * @部门:学工产品事业部
 * @日期：2013-6-4 下午04:56:01 
 */  
package com.zfsoft.xgxt.szdw.fdyrz;

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
import com.zfsoft.xgxt.base.message.MessageKey;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块 
 * @类功能描述: TODO 辅导员任职管理 任职审核
 * @作者： zhangjw 
 * @时间： 2013-6-4 下午04:56:01 
 * @版本： V5.8.16
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyrzshAction  extends SuperAction{
	
	private static final String url = "szdw_fdyrz_sh.do?method=gjcxRzsh";

	/**
	 * @描述:辅导员任职审核高级模式查询
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
	public ActionForward gjcxRzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		
		if (QUERY.equals(myForm.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(myForm,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		String path = "szdw_fdyrz_sh.do?method=gjcxRzsh";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("gjcxFdyrzsh");
	}
	/**
	 * @描述:辅导员任职审核
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
	public ActionForward fdyrzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm myForm = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		FdyrzsqService sqservice = new FdyrzsqService();
		User user = getUser(request);
		FdyrzsqForm model = sqservice.getModel(myForm);
		if (SAVE.equalsIgnoreCase(myForm.getType())){
			myForm.setSplc(model.getSplc());
			boolean result = service.fdyrzsh(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		
		model.setShid(myForm.getShid());
		HashMap<String,String> map = sqservice.getFdyjbxx(model.getZgh());
		request.setAttribute("rs", StringUtils.formatData(map));
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("fdyrzsh");
	}
	
	/**
	 * 
	 * @描述:TODO(撤销流程)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-10 下午04:23:59
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
	public ActionForward cancelFdyrzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FdyrzsqForm model = (FdyrzsqForm) form;
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		FdyrzshService service = new FdyrzshService();
		//撤销日常行为审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 批量审核保存
	 * @作者：cq [工号：785]
	 * @日期：2014-4-29 下午02:17:49
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
	public ActionForward fdyrzPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FdyrzsqForm model = (FdyrzsqForm) form;
		FdyrzshService service = new FdyrzshService();
		
		User user = getUser(request);
		
		if("save".equalsIgnoreCase(model.getType())){
			
			String message = service.savePlsh(model,user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		
		return mapping.findForward("fdyrzPlsh");
	}
	
}
