package com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdxxgl;

import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.ZzyrxmglActionForm;
import com.zfsoft.xgxt.zzyrxmgl.zzyrxmgl.fdgl.FdglService;

/** 
 * @功能描述：资助育人项目管理-辅导信息管理action
 * @author：Lu.Yao 【1271】
 * @date：2017-10-16 下午03:55:26 
 */
public class FdxxglAction extends SuperAction {
	
	private static final String url = "zzyrxmgl_fdxxgl.do";
	
	private FdxxglService service = new FdxxglService();

	
	/** 
	 * @description：加载首页
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:24:43 
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
	public ActionForward fdxxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_fdxxgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdxxglManage");
	}
	
	/** 
	 * @description：评价
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:24:57 
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
	@SystemLog(description="访问就业管理-就业管理-毕业去向-修改XH:{xh}")
	public ActionForward addFdxxglpj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		if (SAVE.equalsIgnoreCase(model.getType())) {
			boolean result = service.addFdxxpj(model);//保存评价
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));				
			return null;
		}
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		return mapping.findForward("addFdxxglpj");
	}
	
	/** 
	 * @description：查看
	 * @dept:学工2部
	 * @author：Lu.Yao 【1271】
	 * @date：2017-10-20 上午11:25:04 
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
	public ActionForward viewFdxxgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		FdglService fservice = new FdglService();
		model.setFdlx("0");//辅导人填写记录
		List<HashMap<String,String>> fdjlList = fservice.getFdjlList(model);
		model.setFdlx("1");//被辅导人填写记录
		List<HashMap<String,String>> bfdjlList = fservice.getFdjlList(model);
		HashMap<String, String> updateForm = fservice.getModelMap2(model);
		BeanUtils.copyProperties(model, StringUtils.formatData(updateForm));
		request.setAttribute("rs",StringUtils.formatData(model));
		request.setAttribute("fdjlList", fdjlList);
		request.setAttribute("bfdjlList", bfdjlList);
		return mapping.findForward("viewFdxxgl");
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2017-12-19 下午02:04:41
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fdxxshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		
		User user = getUser(request);
		
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询
			List<HashMap<String, String>> resultList = service.getShPageList(model, user);
			
			
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "zzyrxmgl_fdxxsh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("fdxxshManage");
	}
	
	/**
	 * @description	： TODO
	 * @author 		： CP（1352）
	 * @date 		：2017-12-20 下午08:02:28
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ZzyrxmglActionForm model = (ZzyrxmglActionForm) form;
		User user = getUser(request);
		boolean canSave = true;
		boolean canCancel = true;
		FdglService fdglService = new FdglService();
		if("1".equals(model.getShzt())){//同意时判断
			canSave = fdglService.checkYtyfdrs(model);//判断同意辅导人数是否超出限定人数			
		}
		if("0".equals(model.getShzt())){//不同意时判断
			canCancel = fdglService.checkCancancel(model);
		}
		if(canSave&&canCancel){
			boolean result = service.updateShzt(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));	
		}else if(!canSave){
			response.getWriter().print(getJsonMessage("已超出限定辅导人数，无法继续同意辅导！"));
		}else if(!(canCancel)){
			response.getWriter().print(getJsonMessage("已有辅导记录，无法取消同意辅导！"));
		}
		return null;
	}
	
	
	
	
}
