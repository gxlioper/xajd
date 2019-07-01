/**
 * @部门:学工产品事业部
 * @日期：2016-8-1 上午08:57:44 
 */  
package com.zfsoft.xgxt.xsztz.jdwhsz;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.newp.StringUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-8-1 上午08:57:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdwhSzAction extends SuperAction<JdwhSzForm, JdwhSzService> {
	JdwhSzService service = new JdwhSzService();
	/*以下是导入的提示！*/
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【error.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	public static final String EXCELREPEAT = "excel中存在重复数据(阶段名称和团队名称/成员学号主键重复)，请仔细核对！";
	/**
	 * 
	 * @描述:阶段维护设置查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 上午10:49:55
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
	public ActionForward getJdszList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm model = (JdwhSzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
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
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[]{Base.currXn});
		searchModel.setSearch_tj_xq(new String[]{Base.currXq});
		request.setAttribute("searchTj", searchModel);
		String path = "sztz_grttxm_jdsz.do";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("cx");
	}
	
	/**
	 * 
	 * @描述:个人项目阶段设置维护主界面查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:03:20
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
	public ActionForward getJdszGrList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm model = (JdwhSzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getGrxmYwh(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("jdid", model.getJdid());
		request.setAttribute("jdmc", request.getParameter("jdmc"));
		request.setAttribute("xmmc", request.getParameter("xmmc"));
		request.setAttribute("xmdm", request.getParameter("xmdm"));
		request.setAttribute("jdf", service.getXmJdwhxx(model.getJdid()).get("jdf"));
		if("view".equals(request.getParameter("flag"))){
			return mapping.findForward("jdwhszview");
		}else{
			return mapping.findForward("jdwhsz");
		}
		
	}
	
	/**
	 * 
	 * @描述: 个人项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 下午05:42:49
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
	public ActionForward getStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm myForm = (JdwhSzForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getGrxmcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "grttxm_jdsz.do?method=getStu";
		request.setAttribute("path", path);
		request.setAttribute("xhs", service.getXhs(myForm.getJdid()));
		request.setAttribute("xmdm", request.getParameter("xmdm"));
		request.setAttribute("jdid", myForm.getJdid());
		request.setAttribute("jdf", service.getXmJdwhxx(myForm.getJdid()).get("jdf"));
		return mapping.findForward("getStuSelect");
	}
	
	/**
	 * 
	 * @描述: 批量添加阶段设置成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 上午09:42:04
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
	public ActionForward saveJdszCy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm myForm = (JdwhSzForm) form;
		boolean result = service.saveJdszCy(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	/**
	 * 
	 * @描述:
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 上午09:59:41
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
	public ActionForward updateJdszCy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm myForm = (JdwhSzForm) form;
		boolean result = service.runUpdate(myForm);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @描述: 删除阶段维护成员
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午01:44:49
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//获得id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @描述:个人项目阶段设置维护主界面查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-1 下午04:03:20
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
	public ActionForward getJdszTtList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm model = (JdwhSzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getTtxmYwh(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		request.setAttribute("jdid", model.getJdid());
		request.setAttribute("jdmc", request.getParameter("jdmc"));
		request.setAttribute("xmmc", request.getParameter("xmmc"));
		request.setAttribute("xmdm", request.getParameter("xmdm"));
		request.setAttribute("jdf", service.getXmJdwhxx(model.getJdid()).get("jdf"));
		if("view".equals(request.getParameter("flag"))){
			return mapping.findForward("jdwhszttview");
		}else{
			return mapping.findForward("jdwhsztt");
		}
	}
	
	/**
	 * 
	 * @描述: 团体项目阶段维护选择成员页面的查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-25 下午05:42:49
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
	public ActionForward getTtcy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JdwhSzForm myForm = (JdwhSzForm) form;
		if (QUERY.equals(myForm.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			myForm.setSearchModel(searchModel);
			User user = getUser(request);
			List<HashMap<String, String>> resultList = service.getTtxmcyList(myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		String path = "grttxm_jdsz.do?method=getTtcy";
		request.setAttribute("path", path);
		request.setAttribute("xhs", service.getDzXhs(myForm.getJdid()));
		request.setAttribute("xmdm", request.getParameter("xmdm"));
		request.setAttribute("jdid", myForm.getJdid());
		request.setAttribute("jdf", service.getXmJdwhxx(myForm.getJdid()).get("jdf"));
		return mapping.findForward("getTtSelect");
	}
	
	/**
	 * 
	 * @描述:团体成员导入
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-2 下午03:08:17
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
	public ActionForward drPrepare(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = getUser(request);
		request.setAttribute("xmmclist", service.getXmmcList(user));
		return mapping.findForward("drprepare");
	}
	
	/**
	 * 
	 * @描述:导入模板下载
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午02:11:25
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
	public ActionForward downloadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//filesys.local.dir 配置于config/fileUploadConfig配置文件下
//		String path =  (String)request.getContextPath()+"/temp" +"/zhfdrmb.xls";
		String path = request.getSession().getServletContext().getRealPath(
				"/temp/mb/")+"/jdcywh.xls";
        logger.info("path = "+path);
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("jdcywh.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @描述:导入保存
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-22 上午11:15:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException{
		JdwhSzForm model = (JdwhSzForm) form;
		FormFile file = model.getFile();
		if(file != null){
			try {
				model.setFilepath(servlet.getServletContext().getRealPath(
				"/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else{
				    message = DRSBBZ;
				    Map<String,String> map = new HashMap<String, String>();
					map.put("message", message);
					map.put("gid", (String)resultMap.get("gid"));
					JSONObject json = JSONObject.fromObject(map); 
				    response.getWriter().print(json);
					return null;
				}
				
			} catch (FileNotFoundException e) {
				// TODO 自动生成 catch 块
				logger.info("导入文件未找到！");
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				logger.info("IO异常！");
				e.printStackTrace();
			}
		}else{
			  String message = KFILE;
			   Map<String,String> map = new HashMap<String, String>();
				map.put("message", message);
				JSONObject json = JSONObject.fromObject(map);
				response.getWriter().print(json);
				return null;
		}
		
		return null;
	}
	
	/**
	 * 
	 * @描述:下载错误数据
	 * @作者：yxy[工号：1206]
	 * @日期：2016-6-20 下午02:11:25
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
	public ActionForward downloadFileError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//得到tomcat/webapp/temp/importTemp下错误文件的路径
		String filename = request.getParameter("filename");
		String path = servlet.getServletContext().getRealPath(
		"/temp/importTemp/")+"/"+filename;
		if (StringUtils.isNotNull(path)){
			File file = new File(path);
				if (file.exists()){
					response.setHeader("Content-disposition", "attachment;filename="+URLEncoder.encode("错误数据.xls","utf-8")); 
					FileUtil.outputFile(response, file);
				}
		}
		return null;
	}
	

	/**
	 * @throws Exception 
	 * 
	 * @描述:学生个人项目查询（只给学生）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:46:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public ActionForward getXsGrxmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdwhSzForm model = (JdwhSzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getXsGrxmCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "grttxm_jdsz.do?method=getXsGrxmCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getxsgrxmcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:学生团体项目查询（只给学生）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 上午10:48:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public ActionForward getXsTtxmCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JdwhSzForm model = (JdwhSzForm) form;
		User user = getUser(request);
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			// 查询
			List<HashMap<String, String>> resultList = service.getXsTtxmCx(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		request.setAttribute("searchTj", searchModel);
		String path = "grttxm_jdsz.do?method=getXsTtxmCx";
		request.setAttribute("path", path);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("getxsttxmcx");
		
	}
	
	/**
	 * 
	 * @描述: 个人项目查询导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 下午02:36:17
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
	public ActionForward exportDataGr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdwhSzForm model = (JdwhSzForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);
		List<HashMap<String, String>> resultList = service.getXsGrxmCx(model, user);

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
	 * 
	 * @描述: 团体项目查询导出
	 * @作者：yxy[工号：1206]
	 * @日期：2016-8-3 下午02:36:44
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
	public ActionForward exportDataTt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JdwhSzForm model = (JdwhSzForm) form;

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// 查询
		Pages pages = model.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		model.setPages(pages);
		List<HashMap<String, String>> resultList = service.getXsTtxmCx(model, user);
		

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
