package xsgzgl.dtjs.dtxxgl;

import java.io.File;
import java.io.FileOutputStream;
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
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xsgzgl.comm.BasicInit;


public class DtxxglAction extends BasicExtendAction{

	private DtxxglService dtxxglService = new DtxxglService();
	
	public ActionForward dtxxglManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		DtxxglForm myForm = (DtxxglForm) form;
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = dtxxglService.dtxxDel(pk) ? "删除成功！" : "删除失败！";
			
			request.setAttribute("message", message);
		}else if("tbgxzzmm".equalsIgnoreCase(doType)){//同步更新政治面貌
				
			String message = dtxxglService.tbgxzzmm() ? "同步更新成功！" : "同步更新失败！";
			request.setAttribute("message", message);
			
		}
		User user = getUser(request);// 用户对象
		request.setAttribute("rs", dtxxglService.dtxxQuery(user,myForm,"manage",request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// write和titile
		setWriteAbleAndTitle(request, "dtjs_dtxxgl_dtxxgl.do");

		request.setAttribute("topTr", dtxxglService.getTopTr("dtxxgl"));
//		request.setAttribute("realTable", "xg_gygl_new_cwxxb");	// 导入表
//		request.setAttribute("tableName", "xg_gygl_new_cwxxb");	// 导出视图

		request.setAttribute("path", "dtjs_dtxxgl_dtxxgl.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("dtxxglManage");
	}
	
	/*
	 * 统计查询
	 */
	public ActionForward dtxxtj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		DtxxglForm myForm = (DtxxglForm) form;
		User user = getUser(request);
		setWriteAbleAndTitle(request, "dtjs_dtxxgl_dtxxtj.do");
		String tjlx=request.getParameter("tjlx");
		List<HashMap<String, String>> jdList=dtxxglService.getJdList();
		String jddmcs=new String();
		for (HashMap<String, String> hashMap : jdList) {
			jddmcs+=hashMap.get("jddmc")+",";
		}
		request.setAttribute("jddmcs", jddmcs.substring(0, jddmcs.length()-1));
		String[] jdArray={"共青团员","推优","预备党员","正式党员"};
		String[] rsbl={"人数","比例"};
		request.setAttribute("jdList", jdList);
		request.setAttribute("jdArray", jdArray);
		request.setAttribute("rsbl", rsbl);
		String[] output = new String[]{"xymc","zrs","ty","per_ty","tys","per_tys","yb","per_yb","dy","per_dy"};
		List<String[]> tjList=new ArrayList<String[]>(); 
//		tjlx=tjlx==null?"0":tjlx;
		//根据学院统计
		if("1".equalsIgnoreCase(tjlx)){
			tjList=dtxxglService.xytjQuery(user,myForm,"xytj",request);
			request.setAttribute("tjList", tjList);				
		}
		String dotype=request.getParameter("exportdate");
		if("dc".equalsIgnoreCase(dotype)){
			//response, bblx,nf,xmmc,xmshqk,xmhsxx
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			dtxxglService.exportData(response,tjlx,jdArray,tjList,output,response.getOutputStream());
			return null;
		}
		


		request.setAttribute("tjlx", tjlx);
		return mapping.findForward("dtxxtj");
	}
	
	public ActionForward dtxxAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {

		String doType = request.getParameter("doType");
		
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		DtxxglForm model=(DtxxglForm)form;
		if("save".equalsIgnoreCase(doType)){
			
			boolean b=dtxxglService.saveDtxx(model);
			if(b){
				request.setAttribute("message", "保存成功！");
			}else{
				request.setAttribute("message", "保存失败！");
			}
		}
		List<HashMap<String,String>> jdList=dtxxglService.getJdList();
		jdList.get(jdList.size()-1).put("isLast", "true");
		request.setAttribute("jdList", jdList);
		request.setAttribute("rs", stuInfo);
		init(request,model);
		// 学校拼音名称
		String xxpymc = model.getXxpymc();
		String path = "/xsgzgl/dtjs/"+xxpymc+"/dtxxgl/dtxxAdd.jsp";
		if(validateUrlIsExists(request,path)){
			return new ActionForward(path,false);
		}
		return mapping.findForward("dtxxAdd");
		
	}
	private DtxxglForm init(HttpServletRequest request,DtxxglForm form){
		// 学校代码
		String xxdm = (String) request.getSession().getAttribute("xxdm");
		form.setXxdm(xxdm);
		// 学校拼音名称
		String xxpymc = new BasicInit().getXxmc(xxdm, form.getXxpymc());
		form.setXxpymc(xxpymc);
		return form;
	}
	
	public ActionForward dtxxUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		DtxxglForm model=(DtxxglForm)form;
		if("update".equalsIgnoreCase(doType)){
			
			boolean b=dtxxglService.updateDtxx(model);
			if(b){
				request.setAttribute("message", "保存成功！");
			}else{
				request.setAttribute("message", "保存失败！");
			}
		}

		List<HashMap<String,String>> jdList=dtxxglService.getXsjdList(xh);
		jdList.get(jdList.size()-1).put("isLast", "true");
		request.setAttribute("jdList", jdList);
		request.setAttribute("other", dtxxglService.getXsjdOther(xh));
		request.setAttribute("dqjd", dtxxglService.getDqjd(xh));
		request.setAttribute("rs", stuInfo);

		init(request,model);
		// 学校拼音名称
		String xxpymc = model.getXxpymc();
		if("view".equalsIgnoreCase(act)){
			String path = "/xsgzgl/dtjs/"+xxpymc+"/dtxxgl/dtxxView.jsp";
			if(validateUrlIsExists(request,path)){
				return new ActionForward(path,false);
			}
			return mapping.findForward("dtxxView");
		}
		// 学校拼音名称
		String path = "/xsgzgl/dtjs/"+xxpymc+"/dtxxgl/dtxxUpdate.jsp";
		if(validateUrlIsExists(request,path)){
			return new ActionForward(path,false);
		}
		return mapping.findForward("dtxxUpdate");
	}
	@SuppressWarnings("deprecation")
	public boolean validateUrlIsExists(HttpServletRequest request, String jspUrl) {
		File tempFilePath  = new File(request.getRealPath(jspUrl));
		if (!tempFilePath.exists()) {
			return false;
		} else {
			return true;
		}
	}
	public ActionForward dtxxglQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String doType = request.getParameter("doType");
		DtxxglForm myForm = (DtxxglForm) form;
		
		// 删除操作
		if("del".equalsIgnoreCase(doType)){
			String[] pk = request.getParameterValues("primarykey_checkVal");
			String message = dtxxglService.delDtxx(pk) ? "删除成功！" : "删除失败！";
			
			request.setAttribute("message", message);
		}
		User user = getUser(request);// 用户对象
		request.setAttribute("rs", dtxxglService.dtxxQuery(user,myForm,"query",request));
		request.setAttribute("searchTj", myForm.getSearchModel());
		
		// write和titile
		setWriteAbleAndTitle(request, "dtjs_dtxxgl_dtxxcx.do");

		request.setAttribute("topTr", dtxxglService.getTopTr("dtxxcx"));
//		request.setAttribute("realTable", "xg_gygl_new_cwxxb");	// 导入表
//		request.setAttribute("tableName", "xg_gygl_new_cwxxb");	// 导出视图

		request.setAttribute("path", "dtjs_dtxxgl_dtxxcx.do");
		request.setAttribute("pageSize", myForm.getPages().getPageSize());
		
		return mapping.findForward("dtxxglQuery");
	}
	
	/**
	 * 导入数据更新 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward importData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
			String tableName = request.getParameter("tableName");//视图名
			String realTable = request.getParameter("realTable");//表名
			
			request.setAttribute("dzyDdTab", request.getParameter("dzyDdTab"));
			request.setAttribute("tableName", tableName);
			request.setAttribute("realTable", realTable);
			
			String act=request.getParameter("act");
			//导入数据
			if("import".equals(act)){
				uploadFile(mapping, form, request, response);
				
				String back= dtxxglService.importData(request,response);//导入数据
				String sfdcExcel=(String)request.getAttribute("sfdcExcel");
				if("yes".equals(sfdcExcel)){
					return mapping.findForward("");
				}
				request.setAttribute("back", back);
				
			}else if("mbxz".equals(act)){//模板下载
				dtxxglService.mbxz(response);
				return mapping.findForward("");
			}
			return mapping.findForward("importData");
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
		//该处需要验证超级管理员权限
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
		DtxxglForm hff = (DtxxglForm) form;
		FormFile file = hff.getImpFilePath();	
//		if (file == null || (file.getFileName() == null || file.getFileName().trim().equals(""))) {
//			file = hff.getCheckFilePath();
			if(file == null){
				return mapping.findForward("false");
			}
//		}
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
	
	/**
	 * 导出数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		
		DtxxglForm myForm = (DtxxglForm) form;
		User user=getUser(request);
		
		request.setAttribute("rs", dtxxglService.exportData(myForm,"manage",response,user));
		return mapping.findForward("");
	}
	/**
	 * 导出数据2
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward exportData2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		User user=getUser(request);
		DtxxglForm myForm = (DtxxglForm) form;
		request.setAttribute("rs", dtxxglService.exportData(myForm,"query",response,user));
		return mapping.findForward("");
	}
	
	/**
	 * 学生党团信息管理字定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsdtxxglExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		DtxxglForm model = (DtxxglForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);// 用户对象
		
		
		List<HashMap<String,String>> resultList =  dtxxglService.dtxxglExportData(model,"manage",user);
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
	 * 学生党团信息查询自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xsdtxxcxExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) 
		throws Exception {
		
		DtxxglForm model = (DtxxglForm) form;
		//生成高级查询对象		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);
		User user = getUser(request);// 用户对象
		
		
		List<HashMap<String,String>> resultList =  dtxxglService.dtxxglExportData(model,"query",user);
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
