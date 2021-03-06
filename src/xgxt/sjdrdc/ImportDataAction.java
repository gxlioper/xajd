/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.sjdrdc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import common.Globals;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.utils.ExcelMethods;
import xgxt.xsgygl.bjlh.sjwh.SjwhDAO;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 通用数据导入Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-08-04</p>
 */
public class ImportDataAction extends DispatchAction {
	/** 
	 * 转到数据检测和导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward importData(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
		String tableName = request.getParameter("tableName");//视图名
		String realTable = request.getParameter("realTable");//表名
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		return mapping.findForward("checkorimport");
	}
	
	/** 
	 * 转到数据检测和导入页面(山东科技辅导员考核)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward importDataSdkj(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response) {
		String tableName = request.getParameter("tableName");//视图名
		String realTable = request.getParameter("realTable");//表名
		String nf = request.getParameter("nf");//年份
		String yf = request.getParameter("yf");//月份
		
		request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("nf", nf);
		request.setAttribute("yf", yf);
		return mapping.findForward("checkorimport");
	}
	
	/** 
	 * 进入数据检测页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showCheckData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImportDataService service = new ImportDataService();
		uploadFile(mapping, form, request, response);
		String filePath = request.getAttribute("filepath") != null ?request.getAttribute("filepath").toString() : request.getParameter("filepath").toString();
		String realTable = request.getParameter("realTable");//导入数据对应的表
		String tableName = request.getParameter("tableName");//视图
		
		List<HashMap<String, String>> orcleItemList = service.getTableColumnList(realTable);//数据库表的列
		request.setAttribute("excelListCollection", service.getExcelCols(filePath));//Excel表中的列
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("tabName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("filepath", filePath);
		request.setAttribute("flag", "1");//用于检测的标记是 “1”
		return mapping.findForward("dataMatching");
	}
	
	/** 
	 * 进入数据导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showImportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImportDataService service = new ImportDataService();
		//先上传
		uploadFile(mapping, form, request, response);
		String filePath = request.getAttribute("filepath") != null ?request.getAttribute("filepath").toString() : request.getParameter("filepath").toString();
		String realTable = request.getParameter("realTable");//导入数据对应的表
		String tableName = request.getParameter("tableName");//视图
		String drms = request.getParameter("drms");//---1:导入新数据 ----0:数据更新
		
		String[] pkList = service.getTablePk(realTable);
		List<HashMap<String, String>> orcleItemList = null;
		
		if("xsxxb".equalsIgnoreCase(realTable) && "new".equalsIgnoreCase(Base.initProperties.get("edition"))){
			orcleItemList = service.getTableColumnList("xsxxb");
			orcleItemList.addAll(service.getTableColumnList("xsfzxxb"));
		}else{
			orcleItemList = service.getTableColumnList(realTable);//数据库表的列
		}
		request.setAttribute("excelListCollection", service.getExcelCols(filePath));//Excel表中的列
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("tabName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("filepath", filePath);
		request.setAttribute("nf", request.getParameter("nf"));
		request.setAttribute("yf", request.getParameter("yf"));
		request.setAttribute("drms", drms);
		request.setAttribute("pkList", pkList);
		request.setAttribute("flag", "2");//用于导入的标记是 “2”
		return mapping.findForward("dataMatching");
	}
	
	
	/** 
	 * 进入数据导入页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward showZdyImportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ImportDataService service = new ImportDataService();
		uploadFile(mapping, form, request, response);
		String filePath = request.getAttribute("filepath") != null ?request.getAttribute("filepath").toString() : request.getAttribute("filepath").toString();
		String realTable = request.getParameter("realTable");//导入数据对应的表
		String tableName = request.getParameter("tableName");//视图
		String drms = request.getParameter("drms");//---1:导入新数据 ----0:数据更新
		String[] pkList = service.getTablePk(realTable);
		
		//luning 自定义表单导入
		String dzyDdTab = request.getParameter("dzyDdTab");
		List<HashMap<String, String>> orcleItemList = null;
		
		if(dzyDdTab!=null&&!dzyDdTab.equalsIgnoreCase("")){
			orcleItemList = service.getZdyDdTableColumnList(realTable,dzyDdTab);//自定义表单数据库表的列
		}else{
			orcleItemList = service.getTableColumnList(realTable);//数据库表的列
		}
		request.setAttribute("excelListCollection", service.getExcelCols(filePath));//Excel表中的列
		request.setAttribute("orcleItemList", orcleItemList);
		request.setAttribute("tabName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("filepath", filePath);
		request.setAttribute("dzyDdTab", dzyDdTab);
		request.setAttribute("drms", drms);
		request.setAttribute("pkList", pkList);
		request.setAttribute("flag", "2");//用于导入的标记是 “2”
		return mapping.findForward("dataMatching");
	}
	
	/** 
	 * 数据检测和导入操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward checkAndImport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		String flag = request.getParameter("flag");
		String filePath =  DealString.toGBK(request.getParameter("filepath").toString()); 
		String realTable = request.getParameter("realTable");
		String mappingItems = request.getParameter("mappingItems");
		// 导入描述：0为数据更新，1为
		String drms = request.getParameter("drms");
		//excel表格中要与数据库数据匹配的字段
		String[] yzsjzd = request.getParameterValues("yzsjzd");
		
		ImportDataService service = new ImportDataService();
		List<String[]> list = new ArrayList<String[]>();
		
		ActionForward af = null;
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			String isFdy = session.getAttribute("isFdy").toString();
			userType = userType!=null && userType.equalsIgnoreCase("admin") ? "xx" : userType;
			userType = isFdy!=null && isFdy.equalsIgnoreCase("true") ? "fdy" : userType;
		}
		
		ExportAndImportModel model = new ExportAndImportModel();
		model.setMappingItems(mappingItems);
		model.setRealTable(realTable);
		model.setFilePath(filePath);
		if("zdlskhb".equalsIgnoreCase(realTable)){
			model.setNf(request.getParameter("nf"));
			model.setYf(request.getParameter("yf"));
		}
		
		if(flag.equals("1")){//数据检测
			list = service.checkExcelDataComm(model);//进行数据检测
			if(list.size()>0){//存在有问题的数据
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMethods.write2Sheet(filePath, response.getOutputStream(), "有问题数据", list);
				response.flushBuffer();
				af = mapping.findForward("showExcelData");
			}else{           //数据检测通过
				request.setAttribute("checkResult", "true");
				af = showCheckData(mapping,form,request,response);
			}
		}else if(flag.equals("2")){//数据导入
			if(drms!=null && drms.equalsIgnoreCase("1")){//导入新数据
//				if("xsxxb".equalsIgnoreCase(realTable) && "new".equalsIgnoreCase(Base.initProperties.get("edition"))){
//					service.saveExcel2Xsxx(model, response, yzsjzd);
//				}else {
					list = service.saveExcel2Db(model, response, yzsjzd);
//				}
			}else if(drms!=null && drms.equalsIgnoreCase("0")){//数据更新
				//TODO  通用导入更新
				list = service.updateExcel2Db(model, response, yzsjzd);				
			}
			if(list != null && list.size()>0){
				response.reset();
				response.setContentType("application/vnd.ms-excel");
				ExcelMethods.write2SheetImport(filePath, response.getOutputStream(), "有问题数据", list);
				response.flushBuffer();
			}else{
				//北京联合 房源库导入 同时生成床位信息
				if("bjlh_ssxxb".equalsIgnoreCase(realTable)){
					SjwhDAO dao = new SjwhDAO();
					dao.createCwxx();
				}
				request.setAttribute("importResult", true);
			}
			af = showImportData(mapping,form,request,response);				
		}
		
		request.setAttribute("filepath", filePath);
		return af;
	}
	
//	public ActionForward checkAndImport(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		HttpSession session = request.getSession(); 
//		String flag = request.getParameter("flag");
//		String filePath =  DealString.toGBK(request.getParameter("filepath").toString()); 
//		String realTable = request.getParameter("realTable");
//		String mappingItems = request.getParameter("mappingItems");
//		String drms = request.getParameter("drms");
//		//excel表格中要与数据库数据匹配的字段
//		String[] yzsjzd = request.getParameterValues("yzsjzd");
//		
//		ImportDataService service = new ImportDataService();
//		ArrayList<String[]> list = new ArrayList<String[]>();
//		ActionForward af = null;
//		String xxdm = StandardOperation.getXxdm();
//		String userType = session.getAttribute("userType").toString();
//		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
//			String isFdy = session.getAttribute("isFdy").toString();
//			userType = userType!=null && userType.equalsIgnoreCase("admin") ? "xx" : userType;
//			userType = isFdy!=null && isFdy.equalsIgnoreCase("true") ? "fdy" : userType;
//		}
//		
//		HashMap<String, String> model = new HashMap<String, String>();
//		model.put("filePath", filePath);
//		model.put("realTable", realTable);
//		model.put("drms", drms);
//		model.put("userType", userType);
//		model.put("xxdm", StandardOperation.getXxdm());
//		model.put("mappingItems", mappingItems);
//		
//		if(flag.equals("1")){//数据检测
//			list = service.checkExcelData(model, yzsjzd);//进行数据检测
//			if(list.size()>0){//存在有问题的数据
//				response.reset();
//				response.setContentType("application/vnd.ms-excel");
//				ExcelMethods.write2Sheet(filePath, response.getOutputStream(), "有问题数据", list);
//				response.flushBuffer();
//				af = mapping.findForward("showExcelData");
//			}else{           //数据检测通过
//				request.setAttribute("checkResult", "true");
//				af = showCheckData(mapping,form,request,response);
//			}
//		}else if(flag.equals("2")){//数据导入
//			if(drms!=null && drms.equalsIgnoreCase("1")){//导入新数据
//				if("xsxxb".equalsIgnoreCase(realTable) && "new".equalsIgnoreCase(Base.initProperties.get("edition"))){
//					service.saveExcel2Xsxx(model, response, yzsjzd);
//				}else {
//					list =(ArrayList<String[]>) service.saveExcel2Db(model, response, yzsjzd);
//				}
//			}else if(drms!=null && drms.equalsIgnoreCase("0")){//数据更新
//				//TODO  通用导入更新
//				list =(ArrayList<String[]>) service.updateExcel2Db(model, response, yzsjzd);				
//			}
//			if(list != null && list.size()>0){
//				response.reset();
//				response.setContentType("application/vnd.ms-excel");
//				ExcelMethods.write2SheetImport(filePath, response.getOutputStream(), "有问题数据", list);
//				response.flushBuffer();
//			}else{
//				//北京联合 房源库导入 同时生成床位信息
//				if("bjlh_ssxxb".equalsIgnoreCase(realTable)){
//					SjwhDAO dao = new SjwhDAO();
//					dao.createCwxx();
//				}
//				request.setAttribute("importResult", true);
//			}
//			af = showImportData(mapping,form,request,response);				
//		}
//		
//		request.setAttribute("filepath", filePath);
//		return af;
//	}
//	
	
	/** 
	 * 数据检测和导入操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws IOException 
	 */
	public ActionForward checkAndImportZdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(); 
		String flag = request.getParameter("flag");
		String filePath =  DealString.toGBK(request.getParameter("filepath").toString()); 
		String realTable = request.getParameter("realTable");
		String mappingItems = request.getParameter("mappingItems");
		String drms = request.getParameter("drms");
		ImportDataService service = new ImportDataService();
		ArrayList<String[]> list = new ArrayList<String[]>();
		ActionForward af = null;
		String xxdm = StandardOperation.getXxdm();
		String userType = session.getAttribute("userType").toString();
		
		//通用自定义字段导入
		String dzyDdTab = request.getParameter("dzyDdTab");
		
 		if(xxdm.equalsIgnoreCase(Globals.XXDM_WHLGDX)){
			String isFdy = session.getAttribute("isFdy").toString();
			userType = userType!=null && userType.equalsIgnoreCase("admin") ? "xx" : userType;
			userType = isFdy!=null && isFdy.equalsIgnoreCase("true") ? "fdy" : userType;
		}
		
		
//		if(list.size()>0){//存在有问题的数据
			
//		}else{//数据检测通过
			if(flag.equals("1")){//数据检测
				list = service.checkExcelData(filePath, realTable, mappingItems,drms,userType);//进行数据检测
				if(list.size()>0){//存在有问题的数据
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					ExcelMethods.write2Sheet(filePath, response.getOutputStream(), "有问题数据", list);
					response.flushBuffer();
					af = mapping.findForward("showExcelData");
				}else{           //数据检测通过
					request.setAttribute("checkResult", "true");
					af = showCheckData(mapping,form,request,response);
				}
			}else if(flag.equals("2")){//数据导入
				if(drms!=null && drms.equalsIgnoreCase("1")){//导入新数据
					if(dzyDdTab.equalsIgnoreCase("")){
						list =(ArrayList<String[]>) service.saveExcel2Db(filePath, realTable, mappingItems,userType, response);				
					}else{
						list =(ArrayList<String[]>) service.saveExcel2DbForZdy(filePath, realTable, mappingItems,userType, response,dzyDdTab);				
					}
				}else if(drms!=null && drms.equalsIgnoreCase("0")){//数据更新
					
					if(dzyDdTab.equalsIgnoreCase("")){
						list =(ArrayList<String[]>) service.updateExcel2Db(filePath, realTable, mappingItems,userType, response);		
					}else{
						throw new Exception("此功能即将开放!");					
					}
				}
				if(list != null && list.size()>0){
					response.reset();
					response.setContentType("application/vnd.ms-excel");
					ExcelMethods.write2SheetImport(filePath, response.getOutputStream(), "有问题数据", list);
					response.flushBuffer();
				}else{
					request.setAttribute("importResult", true);
				}
				af = showImportData(mapping,form,request,response);				
			}
//		}
		request.setAttribute("filepath", filePath);
		return af;
	}
	
	
	/**
	 * 文件上传 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	private ActionForward uploadFile(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String dir = servlet.getServletContext().getRealPath("/upload");
		File f = new File(dir);
		if (!f.exists()) {
			f.mkdir();
		}
		HttpSession session = request.getSession();
		String fName = (String) session.getAttribute("userName");
		String tabName = request.getParameter("tabName");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		fName += date.toString().substring(0, 19);
		fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
		CommanForm hff = (CommanForm) form;
		FormFile file = hff.getImpFilePath();	
		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
		}
		int size = file.getFileSize();
		InputStream in = file.getInputStream();
		String filePath = dir + "/" + fName + ".xls";
		OutputStream out = new FileOutputStream(filePath);
		int bytesRead = 0;
		byte[] buffer = new byte[size];
		while ((bytesRead = in.read(buffer, 0, size)) != -1) {
			out.write(buffer, 0, bytesRead);
		}
		out.close();
		in.close();
		request.setAttribute("tabName", tabName);
		request.setAttribute("filepath", filePath);
		request.setAttribute("moditag", request.getParameter("moditag"));
		return mapping.findForward("success");
	}
}