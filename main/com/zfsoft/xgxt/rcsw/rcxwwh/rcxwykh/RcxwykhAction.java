package com.zfsoft.xgxt.rcsw.rcxwwh.rcxwykh;

import java.io.File;
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
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/**
 * 日常行为月考核
 */
public class RcxwykhAction extends SuperAction {

	private static final String url = "rcsw_rcxwwh_rcxwykh.do";
	
	/**
	 * 查询日常行为月考核
	 */
	@SystemAuth(url = url)
	public ActionForward rcxwykhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwykhForm model = (RcxwykhForm) form;
		RcxwykhService service = new RcxwykhService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_nd(new String[] {Base.currNd});
		searchModel.setSearch_tj_yf(new String[] {Base.currYf});
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_rcxwykh.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("rcxwykhManage");
	}
	/**
	 * 查看日常行为月考核
	 */
	@SystemAuth(url = url)
	public ActionForward viewRcxwykh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwykhForm model = (RcxwykhForm) form;
		RcxwykhService service = new RcxwykhService();
		User user = getUser(request);
		if (QUERY.equals(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageListView(model, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "rcsw_rcxwwh_rcxwykh_view.do";
		request.setAttribute("path", path);
		request.setAttribute("rs", StringUtils.formatData(model));
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewRcxwykh");
	}
	
	/**
	 * 自定义导出设置
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwykhForm model = (RcxwykhForm) form;
		RcxwykhService service = new RcxwykhService();

		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
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
	
	/**
	 * 太原旅游职业学院导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward rcxwykhDc_13696(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcxwykhForm exporModel = (RcxwykhForm) form;
		RcxwykhService service = new RcxwykhService();	
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.rcxwykhDc_13696(exporModel,response.getOutputStream(),user);
		return null;
	}
	
}
