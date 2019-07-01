/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午01:56:05 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;




/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 北京中医药大学_学情月报管理模块
 * @类功能描述: TODO(北京中医药大学_学情月报_班级学情审核) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午01:56:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */
public class BjxqybshAction extends SuperAction<BjxqybshForm, BjxqybshService> {

	private static final String url = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午02:06:51
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
	public ActionForward bjxqybshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		BjxqybshForm model = (BjxqybshForm) form;
		BjxqybshService service = new BjxqybshService();
		User user = getUser(request);
		if(QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询审核数据
			List<HashMap<String,String>> resultList = null;
			resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
			
		}
		String path = "rcsw_xqybgl_bjxqybgl_bjxqybsh.do";
		request.setAttribute("path",path);
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});		
		request.setAttribute("searchTj", searchModel);		
		FormModleCommon.commonRequestSet(request);						
		return mapping.findForward("bjxqybshManage");
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午02:07:03
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
	public ActionForward bjxqybDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		BjxqybshForm model =  (BjxqybshForm)form;
		BjxqybshService service = new BjxqybshService();
		
		if (SAVE.equalsIgnoreCase(model.getType())){	
			User user = getUser(request);
			//保存单个审核
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		User user = getUser(request);
		model.setSqid(request.getParameter("sqid"));
		//审核流程信息
		HashMap<String,String> infoList = service.getBjxqybshInfo(user,model);
		request.setAttribute("infoList", infoList);
		
		//BjxqybshForm updateForm = service.getModel(model);
		model = service.getModel(model);
		model.setShid(request.getParameter("shid"));
		//BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		
		
		request.setAttribute("model", StringUtils.formatData(model));
		return mapping.findForward("bjxqybDgsh");
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—撤销)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午02:05:01
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
	@SystemLog(description="学情月报_班级学情月报管理_班级学情审核—撤销")
	public ActionForward cancelBjxqybsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybshForm model = (BjxqybshForm) form;
		String sqid = request.getParameter("sqid");
		String shzt = request.getParameter("shzt");
		model.setShzt(shzt);
		model.setSqid(sqid);
		
		BjxqybshService service = new BjxqybshService();
		//撤销火车乘车区间审核，最后一级。
		boolean isSuccess = service.newCancelSh(model);
		String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
		
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—批量审核)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-11 下午04:56:49
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
	public ActionForward bjxqybPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BjxqybshForm model = (BjxqybshForm) form;
		BjxqybshService service = new BjxqybshService();
		User user = getUser(request);
		
		if("SAVE".equalsIgnoreCase(model.getType())){		
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		
		return mapping.findForward("bjxqybPlsh");
	}
}
