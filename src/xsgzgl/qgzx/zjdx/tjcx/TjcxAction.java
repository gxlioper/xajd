/**
 * @部门:学工产品事业部
 * @日期：2016-12-26 上午09:41:11 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.date.DateUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.qgzx.jfgl.QgzxJfglService;
import xsgzgl.qgzx.zjdx.cjff.CjffForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 浙大勤工助学酬金发放统计查询
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-26 上午09:41:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxAction extends SuperAction<TjcxForm, TjcxService> {
	TjcxService service = new TjcxService();
	/**
	 * 
	 * @描述: 校区报酬发放统计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 上午11:35:26
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
	public ActionForward getXqbcFfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 查询
			List<HashMap<String, String>> resultList = service.getXqbcFfTj(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setNd(Base.currNd);
		String path = "cjff_tjcx_zjdx_xqbcfftj.do";
		request.setAttribute("path", path);
		request.setAttribute("ndList", Base.getXnndList2());
		
		//验证是否是勤工管理员
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xqbcfftj");
	}
	
	/**
	 * 
	 * @描述:校区报酬发放统计导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 上午11:55:58
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
	public ActionForward exportDataXqbcFfTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxForm model = (TjcxForm) form;

		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getXqbcFfTj(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xqcjfftjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwbXqDc(response.getOutputStream(), model, resultList);

		return null;
	}
	
	/**
	 * 
	 * @描述:用人单位发放统计查询（横向按月展开）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 下午05:10:08
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
	public ActionForward getYrdwFfTj(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 查询
			List<HashMap<String, String>> resultList = service.getYrdwFfTj(model);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		model.setNd(Base.currNd);
		String path = "cjff_tjcx_zjdx_yrdwcjfftj.do";
		request.setAttribute("path", path);
		request.setAttribute("ndList", Base.getXnndList2());
		request.setAttribute("yrbmList", service.getYrbmList(null));
		//验证是否是勤工管理员
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("yrdwtj");
	}
	
	/**
	 * 
	 * @描述: 用人单位合计加最后一列合计行
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-27 下午05:21:28
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
	public ActionForward getHjSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		HashMap<String, String> resultList = service.getYrdwFfTjSum(model);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述: 校区统计最后一列合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 上午11:35:12
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
	public ActionForward getXqSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		HashMap<String, String> resultList = service.getXqbcFfTjSum(model);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:部门类别下拉框切换联动
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 下午02:45:39
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
	public ActionForward bmlbChange(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		List<HashMap<String, String>> resultList = service.getYrbmList(model.getBmlb());
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:校区报酬发放统计导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 上午11:55:58
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
	public ActionForward exportDataYrdwFfTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxForm model = (TjcxForm) form;

		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = service.getYrdwFfTj(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("xqcjfftjdc.xls".getBytes(), "GBK") + "\"");
		service.createWwbYrdwDc(response.getOutputStream(), model, resultList);

		return null;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 学生勤工明细统计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 下午02:36:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getStudentQgDetailTjCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 查询
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getStudentQgDetailTjCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] { Base.currNd});
		request.setAttribute("searchTj", searchModel);
		String path = "cjff_tjcx_zjdx_stucjfftj.do";
		request.setAttribute("path", path);
		
		//验证是否是勤工管理员
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stucjfftj");
	}
	
	/**
	 * 
	 * @描述: 
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 下午02:55:48
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
	public ActionForward getStudentQgDetailTjCxSum(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String, String> resultList = service.getStudentQgDetailTjCxSum(model, user);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @描述:个人明细导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 下午05:03:04
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
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TjcxForm model = (TjcxForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String, String>> resultList = service.getStudentQgDetailTjCx(model, user);
		

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
	
	/**
	 * @描述: 个人报酬发放统计查询页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:02:03
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
	public ActionForward getStucjffgrtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		TjcxDAO dao = new TjcxDAO();
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 查询
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getStucjffgrtj(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		//默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_nd(new String[] { Base.currNd});
		//默认高级查询条件-月份
		String Yf = dao.getCsszYf();
		searchModel.setSearch_tj_yf(new String[]{Yf});
		
		request.setAttribute("searchTj", searchModel);
		String path = "cjff_tjcx_zjdx_stucjffgrtj.do";
		request.setAttribute("path", path);
		
		//验证是否是勤工管理员
		User user = getUser(request);
		QgzxJfglService qgzxJfglService = new QgzxJfglService();
		boolean sfqggly = qgzxJfglService.sfQggly(user.getUserName());
		request.setAttribute("sfqggly", sfqggly);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("stucjffgrtj");
	}
	
	/**
	 * @描述: 个人报酬发放统计根据高级查询条件计算报酬发放总数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:08:04
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
	public ActionForward getStucjffgrtjSum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		TjcxForm model = (TjcxForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		HashMap<String, String> resultList = service.getStucjffgrtjSum(model, user);
		JSONObject dataList = JSONObject.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 个人报酬发放统计导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:23:22
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
	public ActionForward exportDataStucjffgrtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		TjcxForm model = (TjcxForm) form;
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		//不分页
		model.getPages().setPageSize(Integer.MAX_VALUE);
		// 查询
		List<HashMap<String, String>> resultList = service.exportDataStucjffgrtj(model, user);
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
