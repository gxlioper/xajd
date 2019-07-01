package com.zfsoft.xgxt.dtjs.tyzcgl.tyzc;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
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
import xgxt.utils.date.DateUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/** 
 * 团员注册
 */
public class TyzcAction extends SuperAction {
	
	private static final String url = "dtjs_tyzcgl.do";

	/**
	 * 团员注册
	 */
	@SystemAuth(url = url)
	public ActionForward tyzcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		if(QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		
		String path = "dtjs_tyzcgl.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("tyzcManage");
	}
	/**
	 * 单条团员注册
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问党团建设-团员注册管理-团员注册-保存PK:{pk}")
	public ActionForward tyzcDgzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		String[] xnXh = model.getPk().split("!!@@!!");
		String xn = xnXh[0];
		String xh = xnXh[1];
		//学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		request.setAttribute("jbxx", xsjbxx);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String zcr = getUser(request).getUserName();
			boolean isSuccess = service.tyzcInsert(new String[]{ xh }, new String[]{ xn }, model.getZcsj(), zcr);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		HashMap<String,String> tyzcxx = service.getTyzcxx(xn, xh);
		if(StringUtils.isNull(tyzcxx.get("zcsj"))){
			tyzcxx.put("zcsj", DateUtils.getCurrDate());
		}
		request.setAttribute("tyzcxx", StringUtils.formatData(tyzcxx));
		return mapping.findForward("tyzcDgzc");
	}
	
	/**
	 * 批量注册
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问党团建设-团员注册管理-团员注册-批量保存PK:{pk}")
	public ActionForward tyzcPlzc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String[] xhArr = null;
			String[] xnArr = null;
			if(StringUtil.isNull(model.getPks())){
				// 根据查询结果进行批量增加
				//生成高级查询对象		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("dtjs_tyzcgl.do");
				model.setSearchModel(searchModel);
				List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
				xhArr = new String[resultList.size()];
				xnArr = new String[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					xhArr[i] = resultList.get(i).get("xh");
					xnArr[i] = resultList.get(i).get("xn");
				}
			}else{
				// 根据勾选记录进行批量增加
				String[] arr = model.getPks().split(",");
				xhArr = new String[arr.length];
				xnArr = new String[arr.length];
				for (int i = 0; i < arr.length; i++) {
					String[] xnXh = arr[i].split("!!@@!!");
					xhArr[i] = xnXh[1];
					xnArr[i] = xnXh[0];
				}
			}
			String zcr = user.getUserName();
			boolean isSuccess = service.tyzcInsert(xhArr, xnArr, model.getZcsj(), zcr);
			String messageKey = isSuccess ? MessageKey.SYS_SAVE_SUCCESS : MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("zcsj", DateUtils.getCurrDate());
		request.setAttribute("len", request.getParameter("len"));
		return mapping.findForward("tyzcPlzc");
	}
	
	/**
	 * 查看
	 */
	@SystemAuth(url = url)
	public ActionForward tyzcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		String[] xnXh = model.getPk().split("!!@@!!");
		String xn = xnXh[0];
		String xh = xnXh[1];
		//学生基本信息
		XsxxService xsxxService = new XsxxService();
		HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
		request.setAttribute("jbxx", xsjbxx);
		HashMap<String,String> tyzcxx = service.getTyzcxx(xn, xh);
		request.setAttribute("tyzcxx", StringUtils.formatData(tyzcxx));
		return mapping.findForward("tyzcView");
	}
	
	/**
	 * 批量撤销
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问党团建设-团员注册管理-团员注册-批量撤销PK:{pk}")
	public ActionForward tyzcCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		User user = getUser(request);
		if (SAVE.equalsIgnoreCase(model.getType())) {
			String[] xhArr = null;
			String[] xnArr = null;
			if(StringUtil.isNull(model.getPks())){
				// 根据查询结果进行批量撤销
				//生成高级查询对象		
				CommService comService = new CommService();
				SearchModel searchModel = comService.getSearchModel(request);
				searchModel.setPath("dtjs_tyzcgl.do");
				model.setSearchModel(searchModel);
				List<HashMap<String,String>> resultList = service.getAllList(model, user);//查询出所有记录，不分页
				xhArr = new String[resultList.size()];
				xnArr = new String[resultList.size()];
				for (int i = 0; i < resultList.size(); i++) {
					xhArr[i] = resultList.get(i).get("xh");
					xnArr[i] = resultList.get(i).get("xn");
				}
			}else{
				// 根据勾选记录进行批量撤销
				String[] arr = model.getPks().split(",");
				xhArr = new String[arr.length];
				xnArr = new String[arr.length];
				for (int i = 0; i < arr.length; i++) {
					String[] xnXh = arr[i].split("!!@@!!");
					xhArr[i] = xnXh[1];
					xnArr[i] = xnXh[0];
				}
			}
			boolean isSuccess = service.tyzcDelete(xhArr, xnArr);
			String messageKey = isSuccess ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}
		request.setAttribute("len", request.getParameter("len"));
		return mapping.findForward("tyzcCancel");
	}
	
	/**
	 * 导出
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TyzcForm model = (TyzcForm) form;
		TyzcService service = new TyzcService();
		
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(model,user);// 查询出所有记录，不分页

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDataList(resultList);// 设置数据
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		File file = exportService.getExportFile(exportModel);// 生成导出文件
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
