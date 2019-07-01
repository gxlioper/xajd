package xsgzgl.qgzx.cxtj;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.qgzx.QgCommUtil.QgCommUtilf;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.base.util.ZipUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
/**
 * 勤工助学-查询统计-酬金统计导出
 * @author yeyipin
 * @since 2012.9.19
 */
public class QgzxCxtjAjax extends BasicAction{
	
	/**
	 * 岗位信息查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gwxxCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		String qgzq = QgCommUtilf.getQgzq();
		model.getSearchModel().setPath("qgzx_cxtj_gwxx.do");
        if("xq".equals(qgzq)){
        	model.getSearchModel().setPath("qgzx_cxtj_gwxx_xq.do");
		}
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("gwxx");
		// 结果集
//		ArrayList<String[]> rsArrList = service.gwxxCx(model);
		// 构建结果集
//		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
//		rsModel.setRsArrList(rsArrList);
//		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 *岗位信息查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwxxcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getExportList(model,user);
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
	 * 学生岗位查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsgwCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_xsgw.do");
		String qgzq = QgCommUtilf.getQgzq();
		if("xq".equals(qgzq)){
			model.getSearchModel().setPath("qgzx_cxtj_xsgw_xq.do");
		}
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("xsgw");
		// 结果集
//		ArrayList<String[]> rsArrList = service.xsgwCx(model);
		// 构建结果集
//		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
//		// ==================构建前台页面========================
//		rsModel.setTopTr(topTr);
//		rsModel.setRsArrList(rsArrList);
//		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	
	/**
	 *学生岗位查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsgwcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;

		//生成高级查询对象
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		model.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList = service.xsgwCx(model,user);

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
	 * 经费划拨查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jfhbCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_jfhb.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("jfhb");
		// 结果集
		ArrayList<String[]> rsArrList = service.jfhbCx(model);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 经费划拨查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward jfhbCxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.jfhbCxExport(model,user);
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
	 * 酬金发放查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		model.getSearchModel().setPath("qgzx_cxtj_cjff.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr("cjff");
		// 结果集
		ArrayList<String[]> rsArrList = service.cjffCx(model);
		// 构建结果集
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 酬金发放查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward cjffCxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.cjffCxExport(model,user);
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
	 * @描述:北京林业大学下载申报表
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-19 上午09:30:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 */
	public ActionForward cjffCxExportData_10022(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+new String("申报表".getBytes("gb2312"),"iso-8859-1")+".xls");
		
		service.cjffCxExportData_10022(model, response.getOutputStream(), user);
		return null;
	}
	
	/**
	 * 部门酬金发放统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.bmcjffTj(model);
		// 构建结果集
		String spHtml = service.createSearchHTMLByBm(model,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	/**
	 * @描述:岗位酬金发放统计
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午10:05:29
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		model.setGwmc(URLDecoder.decode(request.getParameter("gwmc"),"UTF-8"));

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.gwcjffTj(model);
		// 构建结果集
		String spHtml = service.createSearchHTMLByGw(model,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 个人酬金发放统计
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjffTj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		model.setGwmc(URLDecoder.decode(request.getParameter("gwmc"),"UTF-8"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_cjtj.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.grcjffTj(model);
		// 构建结果集
		String spHtml = service.createSearchHTMLByGr(model,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	

	/**
	 * 部门酬金发放导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bmcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= 初始化各变量的值 ============
		response.setContentType("application/vnd.ms-excel");
		//加入文件名，防止因为api操作系统兼容性不好而引起的excel后缀名改成.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_bmcjtj.xls".getBytes(), "GBK") + "\"");
		service.bmcjffDc(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * 个人酬金发放导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= 初始化各变量的值 ============
		response.setContentType("application/vnd.ms-excel");
		//加入文件名，防止因为api操作系统兼容性不好而引起的excel后缀名改成.do
		 response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_grcjtj.xls".getBytes(), "GBK") + "\"");
		service.grcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	/**
	 * @描述:岗位酬金发放导出
	 * @作者：CP[工号：1352]
	 * @日期：2017-8-18 上午10:00:30
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward gwcjffDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= 初始化各变量的值 ============
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzx_gwcjtj.xls".getBytes(), "GBK") + "\"");
		service.gwcjffDc(response.getOutputStream(),model);
		
		return null;
	}
	/**
	 * @描述：个人酬金发放月份导出
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月23日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * ActionForward 返回类型
	 */
	public ActionForward grcjffDcyf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		File wordFile = service.getjffTjyf(model);
		FileUtil.outputWord(response, wordFile);
		return null;
	}
	
	/**
	 * 
	 * @描述:传媒个性化导出
	 * @作者：cq
	 * @日期：2013-12-26 下午04:30:54
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
	public ActionForward cjffCxExportDataCm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = new QgzxCxtjForm();
		CommService comService = new CommService();
		
		comService.getModelValue(exporModel, request);
		
		User user = getUser(request);
		

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.cjffExpCm(exporModel, response.getOutputStream(),user);
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @描述:济南工程职业技术学院勤工助学考核个性化导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-30 下午05:34:57
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
	public ActionForward cjffCxExportDataJn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = new QgzxCxtjForm();
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("qgzx_cxtj_cjff.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		
		comService.getModelValue(exporModel, request);
		
		

		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		service.cjffExpJn(exporModel, response.getOutputStream(),user);
		// ============= end ============

		return null;
	}
	/**
	 * 
	 * @描述:嘉兴职业技术学院勤工助学工作统计及补贴发放清单
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-23 下午07:08:28
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
	public ActionForward cjffCxExportDataJzy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm exporModel = (QgzxCxtjForm)form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		searchModel.setPath("qgzx_cxtj_cjff.do");
		exporModel.setSearchModel(searchModel);
		User user = getUser(request);
		// ============= 执行打印操作 ============
		response.reset();
		response.setContentType("application/vnd.ms-excel");

		List<File> files=service.cjffExpJzy(exporModel,user);
		File zipFile = ZipUtil.zip(files.toArray(new File[] {}));
		FileUtil.outputZip(response, zipFile);
		// ============= end ============

		return null;
	}
	
	/**
	 * 部门酬金发放查询
	 */
	public ActionForward bmcjffCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		model.setNd(request.getParameter("nd"));
		model.setYf(request.getParameter("yf"));
		model.setBmdm(request.getParameter("bmdm"));
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// 用户对象
		// ============= 初始化各变量的值 ============
		service.commInit(rForm, model, request, user);
		request.setAttribute("path", "qgzx_cxtj_bmcjffcx.do");
		// IE版本
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// 结果集
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.bmcjffCx(model);
		// 构建结果集
		String spHtml = service.createBmcjffcxSearchHTML(model,rsModel, rsArrList, user);
		// ==================构建前台页面========================
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, model.getPages(), response);
		// ==================构建前台页面 end========================
		return null;
	}
	
	/**
	 * 部门酬金发放查询导出
	 */
	public ActionForward bmcjffCxDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		// ============= 初始化各变量的值 ============
		response.setContentType("application/vnd.ms-excel");
		service.bmcjffCxDc(response.getOutputStream(),model);
		return null;
	}
	
	/**
	 * 
	 * @描述: 个人酬金发放月份导出(浙江交通职业技术学院)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-4-19 下午02:46:49
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
	public ActionForward grcjffyfdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		QgzxCxtjService service = new QgzxCxtjService();
		QgzxCxtjForm model = (QgzxCxtjForm) form;
		File file = service.getExcelFile(model);
		response.setHeader("Content-Disposition", "attachment;filename=\""
	               + new String("qgzxcjffdcqc.xls".getBytes(), "GBK") + "\"");
		response.setContentType("application/vnd.ms-excel");
		FileUtil.outputExcel(response, file);
		return null;
	}
	
}
