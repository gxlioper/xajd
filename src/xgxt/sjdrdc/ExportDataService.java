package xgxt.sjdrdc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.comm.xml.Dom4jForXmlHandle;
import xgxt.comm.xml.XMLReader;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;
import xsgzgl.qgzx.glygl.QgzxGlyglService;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ݵ���Service
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: zfsoft</p>
 * <p>Author: sjf</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2011-03-29</p>
 */
public class ExportDataService {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * ��ȡ���п��Ե����ֶ�
	 * @return
	 */
	public List<HashMap<String, String>> getAllFields(String tableName){
		String xxdm = Base.xxdm;
		
		String[] keys = new String[]{tableName+xxdm, tableName+"public"};
		String[] colList = new String[]{"zdmc", "zdsm"};
		String pk = "zdssb||xxdm";
		String query = " where zdssb||xxdm=?";
		
		// ����Ƿ��ڵ��������й�����
		boolean[] exists =  dao.checkExists("dcb", pk, keys);
		
		List<HashMap<String, String>> zds = new ArrayList<HashMap<String, String>>();
		
		if(exists[0]){ 		 //������ѧУ���Ի�
			zds = CommonQueryDAO.commonQueryforList("dcb", query, new String[]{tableName + xxdm}, colList, "");
		}else if(exists[1]){ //������ͨ��
			zds = CommonQueryDAO.commonQueryforList("dcb", query, new String[]{tableName + "public"}, colList, "");
		}else { 			 //���϶������ڵ��������ֶ�
			String[] en = dao.getColumnName("select * from "+tableName+" where 1=2");
			String[] cn = dao.getColumnNameCN(en, tableName);
			
			if(en.length == cn.length){
				for(int i=0;i<cn.length;i++){
					HashMap<String, String> map = new HashMap<String, String>();
					map.put("zdmc", en[i]);
					map.put("zdsm", cn[i]);
					zds.add(map);
				}
			}
		}
		return zds;
	}
	
	public List<HashMap<String,String>> getDczd(String tableName){
		String query = " where bm=? order by sx";
		String[] colList = new String[]{"zd", "zdsm"};
		return CommonQueryDAO.commonQueryforList("xg_xtwh_dczdpzb", query, new String[]{tableName}, colList, "");
		
	}
	
	/**
	 * �������úõĵ����ֶ�
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public boolean saveDczd(ExportAndImportModel model) throws Exception{
		String tableName = "xg_xtwh_dczdpzb";
		String pk = "bm";
		String[] pkValue = new String[]{model.getBm()};
		String[] arrzd = new String[]{"zd","zdsm","sx"};
		String[] onezd = new String[]{"bm","mk"};
		
		SaveForm saveForm = new SaveForm();
		
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setTableName(tableName);
		
		return dao.saveData(saveForm, model);
	}
	
	/**
	 * ��ȡ�������ݣ������ڷǲ���ͨ�����̵�ģ��
	 * @param tableName
	 * @param query
	 * @param inputValue
	 * @param outputColumn
	 * @return
	 */
	public List<String[]> getDataList(String tableName, String query, String[] inputValue, String[] outputColumn){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select *");
		
		sql.append(" from ");
		sql.append(tableName);
		
		sql.append(" a where 1=1 ");
		sql.append(query);
		
		return dao.rsToVator(sql.toString(), inputValue, outputColumn);
	}
	
	/**
	 * ��ȡquery��䲢ͬʱȷ��������query������"and ..." ��ʼ
	 * @param path
	 * @param conditions
	 * @return
	 */
	public Map<String, Object> getQuery(HttpServletRequest request, ExportAndImportModel model){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder query = new StringBuilder();
		String[] inputValue = new String[]{};
		String path = model.getPath();                      	// ȷ��ҳ���Ψһpath
		String superPath = model.getSearchModel().getPath();    // ȷ���߼���ѯ��path
		String dcqx = request.getParameter("dcqx");
		
		// ��ȡҵ���ж�����
		Map<String, Object> personalMap = getPersonalQuery(request, path);
		
		query.append(personalMap.get("query").toString());
		inputValue = (String[]) personalMap.get("inputValue");
		
		if(StringUtils.isNotNull(superPath)){
		// ����path��ȷ���Լ���query��ͬʱ�г������ֶ�conditions
			try {
				query.append(SearchService.getSearchTj(model.getSearchModel()));		// �Լ�����ģ���query���
				
				String[] superSearchTj = SearchService.getTjInput(model
						.getSearchModel());
				inputValue = dao.unionArray(inputValue, superSearchTj); // query�������ֶ�
				
				SearchService searchService = new SearchService();	
				
				//�ڹ���ѧ �����ڹ�����ԱȨ��by yyp
				String qgglyTj = "qgzx_cxtj_gwxx.do,qgzx_cxtj_xsgw.do,qgzx_cxtj_jfhb.do,qgzx_cxtj_cjff.do" +
						",qgzx_gwgl_gwsq.do";
				//����·������Ȩ�޿��� by yyp 
				String wTj = "gygl_gypygl_gypywh.do,rwgl_mbxxgl_mbxxgl.do,rwgl_rwtwgl_rwdjgl.do" +
						",rwgl_rwtwgl_twdjgl.do";
				if(qgglyTj.indexOf(superPath)!=-1){
			    	String searchTjByQgzx = searchService.getSearchTjByQgzx(request);	
					query.append(searchTjByQgzx);
					dcqx = "no";
			    }else if(wTj.indexOf(superPath)!=-1){
			    	dcqx = "no";
			    }
				
				// ����ѧԺԼ��,�༶Լ��
				if("stu_info.jsp".equalsIgnoreCase(superPath)){
					String searchTjByUser = searchService.getSearchTjByUser(request, "a", "ydqxydm", "ydqbjdm");	
					
					query.append(searchTjByUser);
				}else if(!"no".equalsIgnoreCase(dcqx)){
					String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");	
					
					query.append(searchTjByUser);
			    }
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} 		
			
		}
		map.put("query", query.toString());
		map.put("inputValue", inputValue);
		
		return map ;
	}
	
	/**
	 * ��ȡ˽��ҵ���ѯ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getPersonalQuery(HttpServletRequest request, String path){
		Map<String, Object> map = new HashMap<String, Object>();
		String query = "";
		String[] inputValue = new String[]{};
		
		if(StringUtils.isNotNull(path)){
			// ��ȡ����xml
			Document doc = Dom4jForXmlHandle.parserXml("exportData.xml");
			// ����ҵ���ѯ�����ڵ�
			List<Element> querys = doc.selectNodes("//query");
			
			// ѭ���ڵ���sql������ֵ
			for(int i=0; i<querys.size(); i++){
				Element ele = querys.get(i);
				String xmlPath = ele.valueOf("@path");
				String xmlSql = ele.elementText("sql");
				String xmlInput = ele.element("sql").valueOf("@value");
				
				if(xmlPath != null && xmlPath.equalsIgnoreCase(path)){
					query = xmlSql;
					
					if(StringUtils.isNotNull(xmlInput)){
						String[] conditions = xmlInput.split("-");
						inputValue = new String[conditions.length];
						
						for(int j=0; j<conditions.length; j++){
							inputValue[j] = request.getParameter(conditions[j]);
						}
					}
				}
			}
		}
		
		map.put("query", query);
		map.put("inputValue", inputValue);
		return map;
	}
	
	/**
	 * �Ƿ�������
	 * @param tableName
	 * @return
	 */
	public boolean isConfigure(String tableName){
		String sql = "select count(*) num from xg_xtwh_dczdpzb where bm=?";
		
		Map<String, String> map = dao.getMap(sql, new String[]{tableName}, new String[]{"num"});
		int num = Integer.parseInt(map.get("num"));
		
		return num>0 ? true : false;
	}
	
	/**
	 * �����Զ��嵼��xml
	 * @param path
	 * @return
	 */
	public Map<String, Object> getSqlZdy(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(StringUtils.isNotNull(id)){
			Document doc = Dom4jForXmlHandle.parserXml("exportDataZdy.xml");
			
			String xPath = "/exports/entry[@id='" + id + "']";
			Element titNode = (Element)doc.selectSingleNode(xPath + "/title");
			Element sqlNode = (Element)doc.selectSingleNode(xPath + "/sql");
			
			String sql = sqlNode.getTextTrim();
			String tit = titNode.getTextTrim();
			String values = sqlNode.attributeValue("parameter");
			String en = titNode.attributeValue("en");
			
			map.put("sql", sql);
			map.put("tit", tit.split("-"));
			map.put("parameter", StringUtils.isNull(values)?new String[]{}:values.split("-"));
			map.put("outputs", StringUtils.isNull(en)?new String[]{}:en.split("-"));
			
		}
		
		return map;
	}
	
	public Map<String, Object> getDataZdyList(HttpServletRequest request, ExportAndImportModel model){
		Map<String, Object> map = getSqlZdy(model.getExportId());
		String[] inputValue = null;

		StringBuilder sql = new StringBuilder((String)map.get("sql"));
		String[] parameters = (String[])map.get("parameter");
		
		String[] values = getRequestValue(request, parameters);

		//����Ȩ�޿���:��Ԣ����-ס����Ϣ�����߼���ѯ������ģ�������ƹ�Ԣ����Ա��Ȩ�ޣ�����һ�Ų�����
		//���Գ����롰ס����Ϣ�����Ĳ˵��������˵��Ĺ�Ԣ����ԱȨ�޾���Ϊno
		
		try {
			if(!"gyglnew_zsxxgl_zsxxgl_zjgmzy.do".equalsIgnoreCase(model.getExportId()))
				sql.append(SearchService.getSearchTj(model.getSearchModel()));			// �߼���ѯ��query���
			
			SearchService searchService=new SearchService();
			String searchTjByUser = searchService.getSearchTjByUser(request, "a","xydm", "bjdm"); 	//ѧԺ�û�
			
			String searchTjQx = null;
			
			String searchTjByGyfdy="";
			
			String gygly_path = XMLReader.getFlowControl("gygl", "gygly_path");
			if(gygly_path.contains(model.getExportId())){
				GyglNewService gyglNewService = new GyglNewService();
				searchTjByGyfdy = gyglNewService.getSearchTjByGyfdy(request);				//��Ԣ����Ա
			}
			
			if(searchTjByGyfdy !=null && !"".equalsIgnoreCase(searchTjByGyfdy)){//�û�Ϊ��Ԣ����Ա
				searchTjQx = searchTjByGyfdy;
			}else{//�û��ǹ�Ԣ����Ա��������ѧУ����Ա��������ѧԺ����Ա
				searchTjQx = searchTjByUser;
			}
			
			sql.append(searchTjQx);
			inputValue = SearchService.getTjInput(model.getSearchModel());	// query�������ֶ�
			
			inputValue = DAO.getInstance().uniteArrays(values,inputValue);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		map.put("sql", sql.toString());
		map.put("inputs", inputValue);
		return map;
	}
	
	private String[] getRequestValue(HttpServletRequest request, String[] parameters){
		String[] values = new String[]{};
		if(parameters != null && parameters.length>0){
			values = new String[parameters.length];
			for(int i=0;i<parameters.length;i++){
				values[i] = request.getParameter(parameters[i]);
			}
		}
		
		return values;
	}
}
