package xgxt.sjdrdc;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Excel2Oracle;
import xgxt.form.CommanForm;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 通用数据导入Action
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-03-029</p>
 */
public class ExportDataAction extends BasicAction{
	
	/**
	 * 选择需要导出的字段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward choiceExportFields(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		ExportDataService service = new ExportDataService();
		
		// 表中所有可导出字段
		List<HashMap<String,String>> fields = service.getAllFields(tableName);
		// 配置好需要导出的字段
		List<HashMap<String,String>> dczds = service.getDczd(tableName);
		// 升级后有可能导致表结构变化，使原来存在的字段不存在。防止导出出错
		List<HashMap<String,String>> temp = getExportZd(fields, dczds);
	
		request.setAttribute("dczds", temp);
		request.setAttribute("fields", fields);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("choiceExportFields");
	}
	
	/**
	 * 保存需要导出的字段
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveExportFields(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ExportAndImportModel model = new ExportAndImportModel();

		model.setBm(request.getParameter("tableName"));    //导出表
		model.setCh_zdmc(request.getParameterValues("ch_zdmc"));		  //导出字段
		
		ExportDataService service = new ExportDataService();
		
		// 保存设置好的导出字段
		String message = service.saveDczd(model) ? "保存成功！" : "保存失败！";
		
		request.setAttribute("message", message);
		return choiceExportFields(mapping,form,request,response);
	}
	
	/**
	 * 防止表结构发生变化后导出字段出错
	 * @param fields
	 * @param dczds
	 * @return
	 */
	private List<HashMap<String,String>> getExportZd(List<HashMap<String,String>> fields, List<HashMap<String,String>> dczds){
		// 升级后有可能导致表结构变化，使原来存在的字段不存在。防止导出出错
		List<HashMap<String,String>> temp = new ArrayList<HashMap<String,String>>();
		
		for(int i=dczds.size()-1; i>=0; i--){
			HashMap<String, String> dczd = dczds.get(i);
			
			for(int j=0; j<fields.size(); j++){
				HashMap<String, String> field = fields.get(j);
				
				if(dczd.get("zd").equalsIgnoreCase(field.get("zdmc"))){
					fields.remove(j);
					field.put("checked", "checked");
					fields.add(0, field);
					temp.add(0,dczd);
					break;
				}
			}
		}
			
		return temp;
	}
	
	/**
	 * excel导出：若是采用李蓉的流程，请在页面中加入isComm为true的隐藏域
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		ExportDataService service = new ExportDataService();
		String tableName = request.getParameter("tableName");
		// 是否采用李蓉通用流程
		String isComm = request.getParameter("isComm");
		
		// 表中所有可导出字段
		List<HashMap<String,String>> fields = service.getAllFields(tableName);
		// 配置好需要导出的字段
		List<HashMap<String,String>> dczds = service.getDczd(tableName);
		
		List<HashMap<String,String>> exportZd = getExportZd(fields, dczds);
		
		String[] outputColumn = new String[exportZd.size()];
		String[] colListCN = new String[exportZd.size()];
		List<String[]> list = new ArrayList<String[]>();
		
		for(int i=0; i<exportZd.size(); i++){
			HashMap<String, String> map = exportZd.get(i);
			outputColumn[i] = map.get("zd");
			colListCN[i] = map.get("zdsm");
		}
		
		if(StringUtils.isNotNull(isComm) && "true".equalsIgnoreCase(isComm)){ // 采用李蓉通用流程，直接获取数据
			list = getData(request, outputColumn);
		}else{																  // 非通用，个性化数据
			list = getDataNotCommon(request, form, outputColumn);
		}
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
		
		return mapping.findForward("");
	}
	
	/**
	 * 自定义导出数据，用自己的sql导出不再使用视图
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public ActionForward exportDataZdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException, Exception{
		String id = request.getParameter("exportId");
		String sheetName = request.getParameter("sheetName");
		sheetName = StringUtils.isNull(sheetName) ? "数据导出" : sheetName;
		
		CommanForm commForm = (CommanForm)form;
		ExportAndImportModel model = new ExportAndImportModel();
		BeanUtils.copyProperties(model, commForm);
		
		model.setExportId(id);
		
		ExportDataService service = new ExportDataService();
		if("gyglnew_zsxxgl_zsxxgl.do".equals(id) && "12727".equals(Base.xxdm)){
			model.setExportId("gyglnew_zsxxgl_zsxxgl_hljnk.do");
		}
		if("gyglnew_xszstj_xszstj.do".equals(id) && "11799".equals(Base.xxdm)){
			model.setExportId("gyglnew_xszstj_xszstj_cqgs.do");
		}
		if("gyglnew_xszstj_detail.do".equals(id) && "11799".equals(Base.xxdm)){
			model.setExportId("gyglnew_xszstj_detail_cqgs.do");
		}		
		request.setAttribute("path", id);
		Map<String, Object> map = service.getDataZdyList(request, model);
		String[] colListCN = (String[])map.get("tit");
		String[] inputs = new String[]{};
		if(!"gyglnew_zsxxgl_zsxxgl_zjgmzy.do".equalsIgnoreCase(id))
			inputs = (String[])map.get("inputs");
		String[] outputs = (String[])map.get("outputs");
		String sql = (String)map.get("sql");
		
		//GyglNewService gyglNewService = new GyglNewService();
		
		
		//String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//公寓辅导员
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(sql,inputs,outputs, colListCN, response.getOutputStream(), sheetName);
		
		return mapping.findForward("");
	}
	
	
	/**
	 * 获取导出数据(通用流程)
	 * @param request
	 * @param outputColumn
	 * @return
	 */
	private List<String[]> getData(HttpServletRequest request,String[] outputColumn){
		String tableName = request.getParameter("realTable");
		String viewName = request.getParameter("tableName");
		selectPageData(request, tableName, viewName, outputColumn);		
		List<String[]> list = (List<String[]>)request.getAttribute("rs");
		return list;
	}
	
	/**
	 * 获取导出数据(非通用流程)
	 * @param request
	 * @param outputColumn
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	private List<String[]> getDataNotCommon(HttpServletRequest request, ActionForm form, String[] outputColumn) throws IllegalAccessException, InvocationTargetException{
		String tableName = request.getParameter("tableName");
		
		CommanForm commForm = (CommanForm)form;
		ExportAndImportModel model = new ExportAndImportModel();
		BeanUtils.copyProperties(model, commForm);
		
		// 页面jsp名称，唯一确认
		model.setPath(request.getParameter("path"));
		List<String[]> list = new ArrayList<String[]>();
		
		ExportDataService service = new ExportDataService();
		
		// 获得查询条件，并确定条件字段
		Map<String, Object> map = service.getQuery(request, model);
		
		String query = (String)map.get("query");
		String[] inputValue = (String[])map.get("inputValue");
		//山东科技辅导员考核个性化导出
		if("zdlskhb".equalsIgnoreCase(tableName)&&tableName!=null){
			query="";
			List<String> input=new ArrayList<String>();
			if(!"".equalsIgnoreCase(model.getNf())&&model.getNf()!=null){
				input.add(model.getNf());
				query+="  and nf like '%'||?||'%'";
			}
			if(!"".equalsIgnoreCase(model.getYf())&&model.getYf()!=null){
				input.add(model.getYf());
				query+="  and yf like '%'||?||'%'";
			}
			if(!"".equalsIgnoreCase(request.getParameter("xb"))&&request.getParameter("xb")!=null){
				input.add(request.getParameter("xb"));
				query+="  and xb like '%'||?||'%'";
			}
			if(!"".equalsIgnoreCase(request.getParameter("zdls"))&&request.getParameter("zdls")!=null){
				input.add(request.getParameter("zdls"));
				query+="  and zdls like '%'||?||'%'";
			}
			if(!"".equalsIgnoreCase(request.getParameter("zgh"))&&request.getParameter("zgh")!=null){
				input.add(request.getParameter("zgh"));
				query+="  and zgh like '%'||?||'%'";
			}
			inputValue=input.toArray(new String[]{});
		}
		// 获取数据List
		list = service.getDataList(tableName, query, inputValue, outputColumn);
		return list;
	}
}
