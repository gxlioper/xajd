package com.zfsoft.xgxt.xsxx.bycl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.DateTranCnDate;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.FreeMarkerUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.base.util.DateTranCnDate.FomartDateType;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.xpjpy.wdpj.pjjg.PjjgService;
import com.zfsoft.xgxt.xsxx.xsxxgl.xxgl.XsxxglService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息
 * @类功能描述:毕业处理
 * @作者： qilm
 * @时间： 2013-12-5
 * @版本： V1.0
 */
public class ByclAction extends SuperAction {

	private ByclService service = new ByclService();

	private static final String url = "xjyd_bycl.do";
	public static final String DRCGBZ = "导入成功！";
	public static final String DRSBBZ = "导入失败,请仔细核对【错误数据.xls】！";
	public static final String KBG = "空excel表格！";
	public static final String KFILE = "没有文件！";
	public static final String EXCELREPEAT = "Excel中存在重复数据(学号主键重复)，请仔细核对！";
	
	/**
	 * @描述:毕业处理列表
	 * @作者： qilm
	 * @时间： 2013-9-27
	 * @版本： V1.0
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 */
	@SystemAuth(url = url)
	public ActionForward byclList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		if (QUERY.equals(myForm.getType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);
			// 查询
			List<HashMap<String, String>> resultList = service.getPageList(
					myForm, user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}

		request.setAttribute("path", "xjyd_bycl.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("byclList");
	}

	/**
	 * 
	 * @描述: 毕业处理
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 下午04:26:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-毕业处理-保存XH:{xh}")
	public ActionForward bycl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		String selected = request.getParameter("selected");

		if (SAVE.equalsIgnoreCase(myForm.getType())) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);

			boolean result = service.runUpdate(myForm, user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			return null;
		}

		// 全选的情况
		if ("all".equals(selected)) {

			// 生成高级查询对象
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			searchModel.setPath("xjyd_bycl.do");
			myForm.setSearchModel(searchModel);

			// 查询取得所有人数
			int counts = service.getCounts(myForm, user);

			request.setAttribute("yxzxss", counts);
			myForm.setSelected("all");

		} else {
			// 设定VALUE和人数
			String values = myForm.getValues();
			request.setAttribute("values", values);
			if (StringUtils.isNotNull(values)) {
				request.setAttribute("yxzxss", values.split(",").length);
			} else {
				request.setAttribute("yxzxss", "0");
			}
		}

		return mapping.findForward("bycl");
	}

	/**
	 * 
	 * @描述: 取消毕业处理
	 * @作者：Qilm[工号：964]
	 * @日期：2013-12-5 下午04:26:05
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="访问学生信息-学籍异动-毕业处理-保存取消XH:{xh}")
	public ActionForward qxbycl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		myForm.setSearchModel(searchModel);

		boolean result = service.runDelete(myForm, user);
		String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
				: MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;

	}

	/**
	 * 
	 * @描述: 毕业处理导出
	 * @作者：qilm
	 * @日期：2013-9-29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm model = (ByclForm) form;
		ByclService service = new ByclService();

		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		model.setSearchModel(searchModel);
		User user = getUser(request);
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// 查询出所有记录，不分页

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
	 * @描述:江西师范大学毕业生奖惩信息打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-23 下午03:17:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printJcXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		File wordFile = getJcxxWord(myForm, request);
			FileUtil.outputWord(response, wordFile);
		return null;
	}

	/**
	 * 
	 * @描述:江西师范大学毕业生奖惩信息打印(多个)
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-23 下午05:06:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printJcXxZip(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		String value = request.getParameter("xh");
		if (StringUtils.isNotNull(value)) {
			String[] values = value.split(",");
			List<File> files = new ArrayList<File>();
			for (int i = 0, n = values.length; i < n; i++) {
				myForm.setXh(values[i]);
				File file = getJcxxWord(myForm, request);
				if(null!=file){
				files.add(file);
				}
			}
			File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
			FileUtil.outputZip(response, zipFile);
		}

		return null;
	}
	

	/**
	 * 
	 * @描述:江西师范大学毕业生奖惩信息批量打印
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-23 下午06:24:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward printPlJcXx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByclForm myForm = (ByclForm) form;
		User user = getUser(request);
		// 生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("xjyd_bycl.do");
		searchModel.setSearch_tj_yw(new String[]{"有"});
		myForm.setSearchModel(searchModel);
		// 查询
		List<HashMap<String, String>> resultList = service.getAllList(myForm, user);
		List<File> files = new ArrayList<File>();
		for (HashMap<String, String> map : resultList) {
			myForm.setXh(map.get("xh"));
			File file = getJcxxWord(myForm, request);
			if(null!=file){
			files.add(file);
			}
		}
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);

		return null;
	}

	private File getJcxxWord(ByclForm myForm, HttpServletRequest request) {
		Map<String, Object> data = new HashMap<String, Object>();
		String xh = myForm.getXh();
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		HashMap<String, String> xsxxMap = xsxxglService.getXsxxByXh(xh);// 学生信息
		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);// 评奖信息
		List<HashMap<String, String>> wjcfList = xsxxglService.getWjcfList(xh);// 违纪处分信息
		File file = null;
		if(0==pjList.size()&&0==wjcfList.size()){
			return null;
		}
		data.putAll(xsxxMap);
		int pjSize=(12 - pjList.size())<0?0:(12 - pjList.size());
		int wjcfSize=(4 - wjcfList.size())<=0?0:(4 - wjcfList.size());
		data.put("pjBlankList", service.getBlankList(pjSize));
		data.put("wjcfBlankList", service.getBlankList(wjcfSize));
		String nj = xsxxMap.get("nj");
		String xz = xsxxMap.get("xz");
		String bynd = null;
		if(null!=nj&&xz!=null){
			bynd = Integer.parseInt(nj) + Integer.parseInt(xz) + "";
		}
		data.put("pjList", pjList);
		data.put("wjcfList", wjcfList);
		data.put("bynd", bynd);
		data.put("xxmc",Base.xxmc);
		String dysj = GetTime.getTimeByFormat("yyyy-MM-dd hh24:mm:ss");
		data.put("dysj", DateTranCnDate.fomartDateToCn(dysj,FomartDateType.month));
		file = FreeMarkerUtil.getWordFile(data,
				"classpath://templates//xsxx//xjyd", "jcxxb_11318.xml", xh
						+ "-" + xsxxMap.get("xm"));
		return file;

	}
	/**
	 * 
	 * @描述:验证学生是否存在奖惩信息，若不存在则不打印
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-2-2 下午01:55:19
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
	public ActionForward checkJcxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xh = request.getParameter("xh");
		PjjgService pjjgService = new PjjgService();
		XsxxglService xsxxglService = new XsxxglService();
		List<HashMap<String, String>> pjList = pjjgService.getPjpyInfoMap(xh);// 评奖信息
		List<HashMap<String, String>> wjcfList = xsxxglService.getWjcfList(xh);// 违纪处分信息
		if(0==pjList.size()&&0==wjcfList.size()){
			response.getWriter().print(getJsonMessage("该学生无奖惩信息，无需打印！"));
		}else{
			response.getWriter().print(getJsonMessage("true"));
		}
		return null;

	}
	
	/**
	 * @描述: 北京中医药大学毕业学生信息导入
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-10 上午11:34:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward byxsImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		return mapping.findForward("byxsImport");
	}
	
	/**
	 * @描述: 毕业学生信息导入-模板下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-10 下午02:57:32
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
		response.setHeader("Content-Disposition", "attachment;filename=\""+ new String("xsxx_byxs.xls".getBytes(), "GBK") + "\"");
		/*创建工作簿*/
		service.createWwb(response.getOutputStream());
		return null;
	}
	
	/**
	 * @描述: 导入保存
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-10 下午04:50:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward SaveDrForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
		throws IOException{
		ByclForm model = (ByclForm) form;
		FormFile file = model.getDrmb();
		if(file != null){
			try{
				model.setFilepath(servlet.getServletContext().getRealPath("/temp/importTemp/")+"/");
				HashMap<String,Object> resultMap = service.saveDrExcelInfo(file.getInputStream(),model);
				/*导入成功*/
				String message = DRCGBZ;
				if(resultMap.get("result").equals("true")){
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if(resultMap.get("result").equals("null")){
					/*空Excel表格*/
					 message = KBG;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else if("excelrepeat".equals(resultMap.get("result"))){
					/*Excel中存在重复数据(学号主键重复)，请仔细核对！*/
					 message = EXCELREPEAT;
					 Map<String,String> map = new HashMap<String, String>();
						map.put("message", message);
						JSONObject json = JSONObject.fromObject(map);
						response.getWriter().print(json);
						return null;
				}else{
					/*导入失败,请仔细核对【错误数据.xls】！*/
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
		   /*没有文件！*/
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
	 * @描述: 错误文件下载
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-10-12 上午08:46:34
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
}
