package com.zfsoft.xgxt.jskp.sbjg;
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

import org.apache.commons.beanutils.BeanUtils;
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

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;
import common.newp.StringUtil;

public class JskpXmsbjgAction extends SuperAction<JskpXmsbjgForm, JskpXmsbjgService> {
	private final String XSXX="xsxxgl";
	private JskpXmsbjgService service = new JskpXmsbjgService();
	
	private static final String url = "jskp_xmjg.do";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "未上传文件！";
	public static final String EXCELREPEAT = "Excle中存在重复数据(参与人、参与时间、项目名称重复)，请仔细核对！";

	/**
	 * 
	 * @描述:项目申报结果
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-10 上午09:35:35
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
	public ActionForward getXmsbjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		if (QUERY.equalsIgnoreCase(model.getType())) {
			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			User user = getUser(request);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(model, user);
			
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		// 默认高级查询条件
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		String path = "jskp_xmjg.do";
		request.setAttribute("path", path);
		/*返回用户类型*/
		User user = getUser(request);
		request.setAttribute("userType", user.getUserType());
		/*取参数设置表中的是否审核制字段*/
		request.setAttribute("sfsh", new CsszService().getSfsh());
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xmsbjgList");
	}
	
	/**
	 * 
	 * @描述:申报结果增加
	 * @作者：xiaxia[工号：1104]
	 * @日期：2017-7-10 上午09:47:47
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
	public ActionForward addXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		if (!StringUtil.isNull(model.getXh())) {
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSXX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		String path = "jskpXmjg.do?method=addXmsbjg";
		request.setAttribute("path", path);
		this.saveToken(request);
		return mapping.findForward("addXmsbjg");
	}
	/**
	 * 结果查看
	 */
	@SystemAuth(url = url)
	public ActionForward viewXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbjgForm myForm = (JskpXmsbjgForm) form;
		JskpXmsbjgForm model = service.getModel(myForm);
		if(null!=model){
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
			// 学生学年分数汇总
			List<HashMap<String, String>> singleSummary = service.getSingleSummary(model.getXh());
			request.setAttribute("singleSummary", singleSummary);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSXX);
		request.setAttribute("jbxxList", jbxxList);
		/*取参数设置表中的是否审核制字段*/
		String sfsh = new CsszService().getSfsh();
		request.setAttribute("sfsh", sfsh);
		HashMap<String,String> xxckList = service.getXxckForJgid(model.getJgid());
		if("0".equals(sfsh)){
			request.setAttribute("rs", xxckList);
		}else{
			request.setAttribute("rs", StringUtils.formatData(model));
		}
		/*把用户类型传出去*/
		User user = getUser(request);
		request.setAttribute("userType", user.getUserType());
		return mapping.findForward("viewXmsbjg");
	}
	/**
	 * 结果保存
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问纪实考评-项目申报结果-增加XH:{xh},xmid:{xmid},sbsj:{sbsj}")
	public ActionForward saveXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (!isTokenValid(request)){
			response.getWriter().print(getJsonMessageByKey(MessageKey.SYS_TOKEN_VALID));
			return null;
		} else {
			super.resetToken(request);
		}
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		boolean result = false;
		String message=null;

		result = service.saveXmsbjg(model, getUser(request));
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	/**
	 * 申报结果修改
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward editXmsbjg(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JskpXmsbjgForm myForm = (JskpXmsbjgForm) form;
		JskpXmsbjgForm model = service.getModel(myForm);
		if(null!=model){
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));
			// 加载学生基本信息
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(model.getXh());
			request.setAttribute("jbxx", xsjbxx);
		}
		// 学生基本信息显示配置
		BdpzService bdpzService = new BdpzService();
		List<HashMap<String, String>> jbxxList = bdpzService.getJbxxpz(XSXX);
		request.setAttribute("jbxxList", jbxxList);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xmsbjgModel", StringUtils.formatData(model));
		this.saveToken(request);
		return mapping.findForward("editXmsbjg");
	}
	/**
	 * 申报结果删除
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description = "访问纪实考评-项目申报结果-删除VALUES:{values}")
	public ActionForward delXmsbjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JskpXmsbjgService service = new JskpXmsbjgService();
		
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
	 * 申报结果导出
	 */
	@SystemAuth(url = url)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		JskpXmsbjgService service = new JskpXmsbjgService();

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
	
	/**
	 * @描述: 纪实考评分统计
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-11-24 上午09:48:19
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
	public ActionForward dataStatistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JskpXmsbjgForm model = new JskpXmsbjgForm();
		JskpXmsbjgService service = new JskpXmsbjgService();
		
		/*生成高级查询对象	*/
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		String xn = searchModel.getSearch_tj_xn()[0];
		User user = getUser(request);
		/*查询出所有记录，不分页*/
		List<HashMap<String,String>> resultList = service.getDateForSearchXn(model,user, xn);
		/*生成导出文件*/
		File file = service.getDataStatisticsFile(resultList,xn);
		FileUtil.outputExcel(response, file);
		return null;
		
	}
	
	/**
	 * @描述: 思政素质结果列表
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2018-3-12 下午04:56:26
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
	public ActionForward getSzszList (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);

		/**查询*/
		List<HashMap<String, String>> resultList = service.getSzszList(model, user);
		
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * @描述: 思政素质结果导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-13 下午03:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward szszDataImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("szszDataImport");
	}
	
	/**
	 * @描述: 项目结果(思政素质)结果导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-14 上午10:08:39
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
		throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("jskp_xmjg.xls".getBytes(), "GBK") + "\"");
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述: 项目结果(思政素质)结果导入保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-14 下午04:01:33
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
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws Exception{
		
		JskpXmsbjgForm model = (JskpXmsbjgForm)form;
		/**request获取用户*/
		User user = getUser(request);
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setExclePath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model,user);
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
				
			}catch (FileNotFoundException e) {
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
	 * @描述: 下载错误数据
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-15 下午05:43:02
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
		String path = servlet.getServletContext().getRealPath("/temp/importTemp/")+"/"+filename;
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
	 * @描述: 思政素质结果导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-3-16 下午02:19:22
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
	public ActionForward szszExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		JskpXmsbjgForm model = (JskpXmsbjgForm) form;
		JskpXmsbjgService service = new JskpXmsbjgService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		/*不分页*/
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		// 查询
		List<HashMap<String, String>> resultList = service.getSzszList(model,user);// 查询出所有记录，不分页

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
