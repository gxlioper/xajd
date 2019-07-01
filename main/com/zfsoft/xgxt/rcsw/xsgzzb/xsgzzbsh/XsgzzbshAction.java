package com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbsh;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.rcsw.xsgzzb.xsgzzbcssz.CsszService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class XsgzzbshAction extends SuperAction {
	
	private static final String url = "rcsw_xsgzzb_xsgzzbsh.do";
	
	/**
	 * @描述:周报审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsgzzbshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		User user = getUser(request);
		String userStatus = user.getUserStatus();
		if(!"xy".equalsIgnoreCase(userStatus) && !"xx".equalsIgnoreCase(userStatus)){
			String msg = "该模块仅允许"+Base.YXPZXY_KEY+"、学校用户访问，请确认！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		if (QUERY.equalsIgnoreCase(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//查询审核数据
			List<HashMap<String,String>> resultList = null;
			if("bj".equals(gzzblx)){
				resultList = service.getPageListBj(model,user);
			}else{
				resultList = service.getPageList(model,user);
			}
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		searchModel.setSearch_tj_xq(new String[] {Base.currXq});
		request.setAttribute("searchTj", searchModel);
		
		String path = "rcsw_xsgzzb_xsgzzbsh.do";
		if("bj".equals(gzzblx)){
			path = "rcsw_xsgzzb_xsgzzbshgl.do?method=xsgzzbshManage&gzzblx=bj";
		}
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbshManage");
		}
		return mapping.findForward("xsgzzbshManage");
	}
	
	/**
	 * @描述:周报单个审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsgzzbDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		if (SAVE.equalsIgnoreCase(model.getType())){
			User user = getUser(request);
			boolean result = service.saveSh(model,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		if("bj".equals(gzzblx)){
			model=service.getModelBj(model);
		}else{
			model=service.getModel(model);
		}
		model.setShid(request.getParameter("shid"));
		request.setAttribute("model", StringUtils.formatData(model));
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbDgsh");
		}
		if(Base.xxdm.equalsIgnoreCase("13815")){
			CsszService cssz = new CsszService();
			List<HashMap<String, String>> yscfjlist = cssz.getYscfjList(model.getSqid());
			request.setAttribute("yscfjlist", yscfjlist);
		}
		return mapping.findForward("xsgzzbDgsh");
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
	public ActionForward cancelXsgzzbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		User user = getUser(request);
		String sqid = request.getParameter("sqid");
		model.setSqid(sqid);
		XsgzzbshService service = new XsgzzbshService();
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
	public ActionForward xsgzzbPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		String gzzblx = model.getGzzblx();
		XsgzzbshService service = new XsgzzbshService();
		
		User user = getUser(request);
		if("SAVE".equalsIgnoreCase(model.getType())){
			String message = service.savePlsh(model, user);
			response.getWriter().print(getJsonMessage(message));
			return null;
		}
		if("bj".equals(gzzblx)){
			return mapping.findForward("bjgzzbPlsh");
		}
		return mapping.findForward("xsgzzbPlsh");
	}
	
	/**
	 * @描述:自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XsgzzbshForm model = (XsgzzbshForm) form;
		//根据不同的审核类型 去自定义导出
		String shlx = request.getParameter("shlx");
		XsgzzbshService service = new XsgzzbshService();
		model.setShzt(shlx);
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model,user);//查询出所有记录，不分页
		
		//导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());//当前操作员
		exportModel.setDataList(resultList);//设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));//设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);//生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
