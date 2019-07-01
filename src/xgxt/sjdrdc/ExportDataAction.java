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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ͨ�����ݵ���Action
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-03-029</p>
 */
public class ExportDataAction extends BasicAction{
	
	/**
	 * ѡ����Ҫ�������ֶ�
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
		
		// �������пɵ����ֶ�
		List<HashMap<String,String>> fields = service.getAllFields(tableName);
		// ���ú���Ҫ�������ֶ�
		List<HashMap<String,String>> dczds = service.getDczd(tableName);
		// �������п��ܵ��±�ṹ�仯��ʹԭ�����ڵ��ֶβ����ڡ���ֹ��������
		List<HashMap<String,String>> temp = getExportZd(fields, dczds);
	
		request.setAttribute("dczds", temp);
		request.setAttribute("fields", fields);
		request.setAttribute("tableName", tableName);
		return mapping.findForward("choiceExportFields");
	}
	
	/**
	 * ������Ҫ�������ֶ�
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

		model.setBm(request.getParameter("tableName"));    //������
		model.setCh_zdmc(request.getParameterValues("ch_zdmc"));		  //�����ֶ�
		
		ExportDataService service = new ExportDataService();
		
		// �������úõĵ����ֶ�
		String message = service.saveDczd(model) ? "����ɹ���" : "����ʧ�ܣ�";
		
		request.setAttribute("message", message);
		return choiceExportFields(mapping,form,request,response);
	}
	
	/**
	 * ��ֹ��ṹ�����仯�󵼳��ֶγ���
	 * @param fields
	 * @param dczds
	 * @return
	 */
	private List<HashMap<String,String>> getExportZd(List<HashMap<String,String>> fields, List<HashMap<String,String>> dczds){
		// �������п��ܵ��±�ṹ�仯��ʹԭ�����ڵ��ֶβ����ڡ���ֹ��������
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
	 * excel���������ǲ������ص����̣�����ҳ���м���isCommΪtrue��������
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
		// �Ƿ��������ͨ������
		String isComm = request.getParameter("isComm");
		
		// �������пɵ����ֶ�
		List<HashMap<String,String>> fields = service.getAllFields(tableName);
		// ���ú���Ҫ�������ֶ�
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
		
		if(StringUtils.isNotNull(isComm) && "true".equalsIgnoreCase(isComm)){ // ��������ͨ�����̣�ֱ�ӻ�ȡ����
			list = getData(request, outputColumn);
		}else{																  // ��ͨ�ã����Ի�����
			list = getDataNotCommon(request, form, outputColumn);
		}
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(list,outputColumn,colListCN, response.getOutputStream());
		
		return mapping.findForward("");
	}
	
	/**
	 * �Զ��嵼�����ݣ����Լ���sql��������ʹ����ͼ
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
		sheetName = StringUtils.isNull(sheetName) ? "���ݵ���" : sheetName;
		
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
		
		
		//String searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
		
		response.reset();
		response.setHeader("Content-Disposition", "attachment; filename=exportData.xls"); 
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportData(sql,inputs,outputs, colListCN, response.getOutputStream(), sheetName);
		
		return mapping.findForward("");
	}
	
	
	/**
	 * ��ȡ��������(ͨ������)
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
	 * ��ȡ��������(��ͨ������)
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
		
		// ҳ��jsp���ƣ�Ψһȷ��
		model.setPath(request.getParameter("path"));
		List<String[]> list = new ArrayList<String[]>();
		
		ExportDataService service = new ExportDataService();
		
		// ��ò�ѯ��������ȷ�������ֶ�
		Map<String, Object> map = service.getQuery(request, model);
		
		String query = (String)map.get("query");
		String[] inputValue = (String[])map.get("inputValue");
		//ɽ���Ƽ�����Ա���˸��Ի�����
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
		// ��ȡ����List
		list = service.getDataList(tableName, query, inputValue, outputColumn);
		return list;
	}
}
