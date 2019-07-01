/**
 * @部门:学工产品事业部
 * @日期：2017-2-22 下午04:59:52 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.OptionUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.QueryDataService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-统计管理-评奖项目汇总
 * @类功能描述: 统计每学年、每学期、每个项目的获奖人数
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-2-22 下午04:59:52 
 * @版本： Ver 5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PjxmhzAction extends SuperAction {
	private static final String url = "xg_pjpy_tjgl_pjxmhz.do";
	
	/**
	 * @描述: 查询
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-2-22 下午05:35:09
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
	public ActionForward viewPjxmhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		if (QUERY.equals(model.getType())){
			//生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			//得到用户
			User user = getUser(request);
			//查询
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		SearchModel searchModel=new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		request.setAttribute("searchTj", searchModel);
		String path = "xg_pjpy_tjgl_pjxmhz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewPjxmhz");
	}
	
	/**
	 * @描述: 评奖项目汇总查询列表自定义导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-2-23 上午11:18:23
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
	public ActionForward pjxmhzExportData(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		exportData(model, request, response);
		return null;
	}
	
	/**
	 * @描述: 实现自定义导出
	 * @作者：Meng.Wei[工号：1186]
	 * @日期：2017-2-23 上午11:19:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param request
	 * @param response
	 * @throws Exception
	 * void 返回类型 
	 * @throws
	 */
	private void exportData(PjxmhzForm model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		// 导出功能代码
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// 当前操作员
		exportModel.setDcclbh(request.getParameter("dcclbh"));// 设置当前导出功能编号
		exportModel.setRowConut(model.getRowConut());
		model.getPages().setPageSize(RAM_MAX_SIZE);
		File file = exportService.getExportExcelFile(exportModel,new QueryDataService(model,user){
			@Override
			public List queryData(Object model, User user) throws Exception {
				PjxmhzForm fmtModel = (PjxmhzForm)model;
				fmtModel.getPages().setCurrentPage(OptionUtil.page);
				PjxmhzService service = new PjxmhzService();
				return service.getPageList(fmtModel, user);	
			}});
		FileUtil.outputExcel(response, file);
	}
	
	/**
	 * @描述: 点击总人数的超链接，跳转到每个奖项获得的具体名单和学生详细信息
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-2-23 下午02:14:30
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
	public ActionForward viewRs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		if (QUERY.equals(model.getType())){
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.viewRs(model, user, true);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("model", model);
		return mapping.findForward("viewRs");
	}
	
	/**
	 * @描述: 点击超链接进入详细名单查询自定义导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-2-23 上午11:18:23
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
	public ActionForward pjxmhzRsExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjxmhzForm model = (PjxmhzForm) form;
		PjxmhzService service = new PjxmhzService();
		User user = getUser(request);
		// 查询
		List<HashMap<String, String>> resultList = service.viewRs(model, user, false);// 查询出所有记录，不分页

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
