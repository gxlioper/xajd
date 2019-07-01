package xgxt.xtwh.xtwhOther;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.sun.jmx.snmp.Timestamp;
import com.zfsoft.basic.BasicService;
import com.zfsoft.util.JdbcUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.Excel2Oracle;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.PictureUtil;
import xgxt.utils.ZipUtils;
import xgxt.xsxx.comm.ajax.XsxxAjaxService;

/**
 * 系统维护相关的新增加及修改的功能
 */
public class XtwhOtherAction extends SuperAction {
	/**
	 * @describe 照片批量上传功能
	 * @author luning
	 * @throws Exception
	 */
	public ActionForward picPlsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XtwhOtherService myService = new XtwhOtherService();
		try {
			HttpSession session = request.getSession();
			String act = request.getParameter("isFile");
			//导入方式
			String drfs = request.getParameter("drfs");
			
			String sUName = session.getAttribute("userName").toString();
			if("yes".equalsIgnoreCase(act)){
				String dir = servlet.getServletContext().getRealPath("/zip");
				File f = new File(dir);
				if (!f.exists()) {
					f.mkdir();
				}
				String fName = sUName;
				//文件上传
				Timestamp date = new Timestamp(System.currentTimeMillis());
				String czsj = date.toString();
				fName += date.toString().substring(0, 19);
				fName = fName.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", "");
				XtwhOtherForm dataImp = (XtwhOtherForm) form;
				FormFile file = dataImp.getFile();
				if (file == null) {
					request.setAttribute("errMsg", "文件读取失败,请重试！");
					return new ActionForward("/errMsg.do", false);
				}
				String fType1 = file.getFileName().substring(
						file.getFileName().length() - 4, file.getFileName().length());
				int size1 = file.getFileSize();
				InputStream in1 = file.getInputStream();
				String filePath1 = dir + "/" + fName + fType1;
				OutputStream out1 = new FileOutputStream(filePath1);
				int bytesRead1 = 0;
				byte[] buffer1 = new byte[size1];
				while ((bytesRead1 = in1.read(buffer1, 0, size1)) != -1) {
					out1.write(buffer1, 0, bytesRead1);
				}
				out1.close();
				in1.close();
				
				//文件进行解压
				ZipUtils zipUtils = new ZipUtils();
				zipUtils.unzip(filePath1,dir+"/"+fName);

				//解压后批量上传
				//String filepath = Base.chgNull(request.getParameter("filepath"), "", 1);
				List<String> rsMap = PictureUtil.uploadPhoto(dir+"/"+fName+"/");
				request.setAttribute("sb", rsMap.get(0));
				request.setAttribute("sb1", rsMap.get(1));
				request.setAttribute("sb2", rsMap.get(2));
				request.setAttribute("rsMap", rsMap);
				request.setAttribute("path", "xsxxpicpldr.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("picpldr");
			}else {
				request.setAttribute("path", "xsxxpicpldr.do");
				FormModleCommon.commonRequestSet(request);
				return mapping.findForward("picpldr");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("picpldr");
	}
	
	/**
	 * 新版代码维护
	 */
	public ActionForward xtDmwhNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		    XtwhOtherForm myForm = (XtwhOtherForm)form;
		    
			//得到模块名称,学校代码
			String ssmk = request.getParameter("ssmk");
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//表列表
			List<HashMap<String, String>> tableArray = myService.getDmwhTable(ssmk,xxdm);
			
			//判断如果tableArray的size为0，就不进行以后的操作，存入errMessage
			if(tableArray==null||tableArray.size()==0){
				request.setAttribute("errMessage", "还没有任何可维护的代码类型");
				return mapping.findForward("xtDmwhNew");
			}
			if(tableName==null||tableName.equalsIgnoreCase("")){
				tableName = tableArray.get(0).get("whdmb");
			}
			myService.getDmwhZdForCx(tableName,xxdm,ssmk,myForm);
			
			String[] zdList = myService.getWhzds();
			
			request.setAttribute("zdList", zdList);
			request.setAttribute("tName", tableName);
			request.setAttribute("ssmk", ssmk);
			request.setAttribute("rs", myService.getQueryRs());
			request.setAttribute("topTr", myService.getToptr());
			//根据字段和表名去拼sql语句
		    //存入标题和读写权
			request.setAttribute("tableArray", tableArray);
			FormModleCommon.commonRequestSet(request);
			
			//-----2011.1.9 qph--------------------------
			if ("jyweb".equals(ssmk)){
				request.setAttribute("title", "栏目设置");
				request.setAttribute("writeAble", "yes");
			}
			//-----------end --------------------------
			
			request.setAttribute("maxNum", myForm.getPages().getPageSize());
			return mapping.findForward("xtDmwhNew");
	}
	
	
	
	/**
	 * 新版代码维护
	 */
	@SystemLog(description="访问代码维护-删除SSMK：{ssmk} TNAME:{tName} PKVALUE:{pkValue}")
	public ActionForward xtDmwhDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		    
			//得到模块名称,学校代码
		    String xxdm = Base.xxdm;
		    String ssmk = request.getParameter("ssmk");
			String tableName = request.getParameter("tName");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			boolean del = myService.dmwhDel(tableName, pkValue,xxdm,ssmk,request);
			if(del){
				request.setAttribute("result", "OK");
			}else{
				request.setAttribute("result", "NO");
			}
			return xtDmwhNew(mapping,form,request,response);
	}
	
	
	/**
	 *新版代码维护自定义导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward xtwhdmwhExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XtwhOtherForm model = (XtwhOtherForm)form;	    
		//得到模块名称,学校代码
		String tablename = request.getParameter("tName");		
		XtwhOtherService service = new XtwhOtherService();
		
		//生成高级查询对象		
		//CommService comService = new CommService();
		//SearchModel searchModel = comService.getSearchModel(request);
		//model.setSearchModel(searchModel);
		User user = getUser(request);
		// 结果集
		List<HashMap<String,String>> resultList = service.getDmwhExport(tablename);
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
	 * 新版代码维护
	 */
	public ActionForward xtDmwhOneNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			XtwhOtherForm myFrom = (XtwhOtherForm)form;
			//得到模块名称,学校代码
			String type = request.getParameter("Type");
			String ssmk = request.getParameter("ssmk");
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//获得显示表结构和相关下拉框里的值
			myService.getDmwhZdForOneDis(tableName,xxdm,ssmk,pkValue);
			myService.getDmwhZd(tableName, xxdm, ssmk, request, myFrom);
			if(type!=null&&type.equalsIgnoreCase("ref")){
				String [] whzds = myService.getWhzds();
				for(int i = 0;i<whzds.length;i++){
					request.setAttribute(whzds[i], request.getParameter(whzds[i]));
				}
			}
			if(pkValue==null){
				request.setAttribute("newId", tableName);
			}
			request.setAttribute("opMap", myService.getZdOpMap());
			request.setAttribute("tName", tableName);
			request.setAttribute("pkValue", pkValue);
			request.setAttribute("ssmk", ssmk);
			request.setAttribute("opTwoAssociate", myService.getOpTwoAssociate());
			request.setAttribute("rs", myService.getOneRsAllAttribute());
			//根据字段和表名去拼sql语句
		    //存入标题和读写权
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("xtDmwhOne");
	}
	
	/**
	 * 新版代码保存
	 */
	@SystemLog(description="访问代码维护-增加或修改的保存SSMK:{ssmk} TNAME：{tName} PKVALUE:{DM} ")
	public ActionForward xtDmwhSaveNew(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			//得到模块名称,学校代码
			String xxdm = Base.xxdm;
			String tableName = request.getParameter("tName");
			String ssmk = request.getParameter("ssmk");
			String pkValue = request.getParameter("pkValue");
			//service
			XtwhOtherService myService = new XtwhOtherService();
			
			//获得显示表结构和相关下拉框里的值
			Boolean update = myService.dmwhZdForOneSave(tableName,xxdm,ssmk,pkValue,request);
			
			String message = update ? "保存成功！":"代码或名称已存在！";
			Map<String,String> map = new HashMap<String, String>();
			map.put("message", message);
			JSONObject json = JSONObject.fromObject(map); 
			response.getWriter().print(json);
			/*if(update){
				request.setAttribute("result", "OK");
			}else{
				request.setAttribute("result", "NO");
			}*/
			return null;
			//return mapping.findForward("xtDmwhOne");
			
	}
	
	/**
	 * 功能开关管理
	 * */
	public ActionForward gnkgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			XtwhOtherForm myForm = (XtwhOtherForm)form;
			HttpSession session = request.getSession();
			//得到模块名称,学校代码
			String xxdm = Base.xxdm;
			String ssmk = request.getParameter("ssmk");//所属模块
			String doType = request.getParameter("doType");
			//用户类型
			String userType = session.getAttribute("userType").toString();
			//辅导员
			boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx").toString());
			//班主任
			boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx").toString());
			//查询的表名
			String tableName = "xgxtgy_gnkgb";//学工公用模块开关控制表
			// 访问路径
			String path = "gnkgsz.do?ssmk="+ssmk;
			//service
			XtwhOtherService service = new XtwhOtherService();			
			
			// 是否查询操作
			boolean search = Base.isNull(request.getParameter("go")) ? false : true;
			
			// ==================登陆用户检测 ==================
			if (bzrQx || fdyQx || "stu".equalsIgnoreCase(userType) || "xy".equalsIgnoreCase(userType)) {
				String msg = "本模块只能由学校用户进行操作，请确认！";
				request.setAttribute("yhInfo", msg);
				return new ActionForward("/yhInfo.do", false);
			}
			// ==================执行保存操作 ==================
			if ("save".equalsIgnoreCase(doType)) {
				boolean result = service.updateKgzt(myForm);
				String msg = result ? "操作成功！" : "操作失败！";
				request.setAttribute("message", msg);
			}
			// =================end ===================
			// ==================执行导出操作 ==================
			if ("exp".equalsIgnoreCase(doType)) {
				String[] outputColumn = null;
				expPageData(request, response, tableName, tableName, outputColumn);
				return mapping.findForward("");
			}
			// =================end ===================
			// ==================执行查询操作 ==================
			if (search) {
				String[] outputColumn = { "gndm", "gnmc", "gnlb", "kgzt"};
				this.selectPageDataByPagination(request, myForm, "", tableName,
						outputColumn);
			}
			// =================end ===================
			//类别列表
			request.setAttribute("gnlbList", service.getList(tableName,"gnlb", "gnlb", "ssmk",ssmk));
			//开关状态
			request.setAttribute("kgztList", service.getDefaultList("kgzt"));
			//所属模块
			request.setAttribute("ssmk", ssmk);
			//模块读写权和路径
			request.setAttribute("path", path);
			
			FormModleCommon.commonRequestSet(request);
			return mapping.findForward("gnkgManage");
	}
	
	public ActionForward showExportPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JdbcUtil util = new JdbcUtil();
		BasicService service = new BasicService();
		String viewName = request.getParameter("viewName");
		String[] column =service.getTableColumn(viewName);
		
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		if(request.getParameterMap() != null){
			paramMap.putAll(request.getParameterMap());
		}
		//解决类型为multipart/form-data 获取不到数据的问题
		String content_type = request.getContentType();
		if (content_type != null && content_type.indexOf("multipart/form-data") != -1) {
			Enumeration<String> enu = request.getParameterNames();
			String str = "";
			while (enu.hasMoreElements()){
				str = enu.nextElement();
				paramMap.put(str, request.getParameter(str));
			}
		}
		
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
						paramMap, 
		                new HashMap<String, String>(), 
		                false);//分页查询
		String sql = "";//查询字段信息的sql
		String[] inputValue = {};//条件值
		for(Map.Entry<String, Object> entry : sqlMap.entrySet()){
			String key = entry.getKey();
			if("sql".equalsIgnoreCase(key)){
				sql = entry.getValue().toString();
			}
			if("value".equalsIgnoreCase(key)){
				inputValue = entry.getValue() != null 
				    ? (String[])entry.getValue() 
				    : new String[]{};
			}
		}
		
		request.setAttribute("list", service.getColumnList(viewName, column));
		request.setAttribute("tableName", request.getParameter("tableName"));
		request.setAttribute("viewName", viewName);
		request.setAttribute("sql", sql);
		request.setAttribute("value", Arrays.toString(inputValue).replace("[", "").replace("]", ""));
		return mapping.findForward("showexport");
	}
	
	
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BasicService basicService = new BasicService();
		XtwhOtherService service = new XtwhOtherService();	
		String[] outputColumn = request.getParameter("mappingItems").split("!!SplitOne!!");//所有用户选择的导出字段
		
		
		try {
			List<String[]> list = service.selectExportData(request.getParameter("viewName"),
					                                       request.getParameter("value").split(","),
					                                       outputColumn,
					                                       request.getParameter("sql"));
			String[] colListCN = basicService.getColumnComment(request.getParameter("viewName"), outputColumn);
			
			response.reset();
			response.setContentType("application/vnd.ms-excel");		
			Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			request.setAttribute("errMsg", "导出失败！");
			return mapping.findForward("false");
		}
		return mapping.findForward("");
	}
	
	public ActionForward getXxdm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String xxmc = request.getParameter("xxmc");
			//xxmc = new String(xxmc.getBytes("ISO8859-1"), "gbk");
			XtwhOtherService service = new XtwhOtherService();	
			
			String xxdm = service.getXxdm(xxmc);
		
			response.setContentType("text/html;charset=gbk");

			try {
				response.getWriter().print(xxdm);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
	}
	
	public ActionForward getXxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			String srz = request.getParameter("srz");
			
			XtwhOtherService service = new XtwhOtherService();	
			
			String xxdm = service.getXxmc(srz);
			if(xxdm==null){
				xxdm="";
			}
			response.setContentType("text/html;charset=gbk");

			try {
				response.getWriter().print(xxdm);
			} catch (IOException e) {
				// TODO 自动生成 catch 块
				e.printStackTrace();
			}
			return null;
	}

	/**
	 * @描述:智能模糊查询下拉
	 * @作者：qilm
	 * @日期：2013-8-15 上午11:18:47
	 * @throws
	 */
	public ActionForward getAutocomplete(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 表名
		String dataTable = request.getParameter("dataTable");

		// 字段名
		String dataField = request.getParameter("dataField");
		
		// 字段Key
		String dataFieldKey = request.getParameter("dataFieldKey");

		// 字段名作为参数
		String param = request.getParameter("param");
		// 个性化sql条件
		String sqlTj = request.getParameter("sqlTj");
		
		XtwhOtherService service = new XtwhOtherService();

		// 取得结果集
		List<HashMap<String, String>> lstAutocomplete = service
		.getAutocomplete(dataTable, dataField, dataFieldKey, param, sqlTj);

		try {
			response.setContentType("text/html;charset=gbk");
			JSONArray dataList = JSONArray.fromObject(lstAutocomplete);
			response.getWriter().print(dataList);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 
	 * @描述:火车站点列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-19 下午03:58:17
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
	public ActionForward getHczd(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
		XtwhOtherService service = new XtwhOtherService();
		// 取得结果集
		List<String> cityList = service.getHczd();
		try {
			response.setContentType("text/html;charset=gbk");
		
			JSONArray dataList = JSONArray.fromObject(cityList.toArray());
			response.getWriter().print(dataList);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @描述: 火车区间验证输入值是否有效
	 * @作者：yxy[工号：1206]
	 * @日期：2016-11-17 下午04:08:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param input
	 * @return
	 * ActionForward 返回类型 
	 * @throws
	 */
	public ActionForward getCheckYz(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String inputvalue = request.getParameter("inputValue");
		boolean result = new XtwhOtherService().isInputValueExist(inputvalue);
		response.getWriter().print(result);
		return null;
	}
	
}
