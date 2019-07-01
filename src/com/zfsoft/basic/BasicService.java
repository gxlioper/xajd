package com.zfsoft.basic;

import com.zfsoft.database.imp.DatabaseTableManipulateImpl;
import com.zfsoft.database.imp.DatabaseTables;
import com.zfsoft.database.model.ColumnModel;
import com.zfsoft.database.model.TableModel;
import com.zfsoft.util.JdbcUtil;
import com.zfsoft.utils.StringUtil;
import xgxt.form.User;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class BasicService {
	//TODO ����ʱ��Ҫ
	BasicDAO dao;
	
	/**
	 * ��ѯ��ͷ��Ϣ
	 * @param tableName
	 * @param outputValue
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, 
			                                      String[] outputValue){
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		String[] colCN = dao.getColumnNameCN(outputValue, tableName);
		return dao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * ��ȡ���ֶ�ע����Ϣ
	 * @param  tableName
	 * @param  column
	 * @return String[]
	 * */
	public String[] getColumnComment(String tableName, String[] column){
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();		
		return dao.getColumnNameCN(column, tableName);
	}
	
	/**
	 * ��ȡ�������Ϣ
	 * @param tableName
	 * @param column
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getColumnList(String tableName, String[] column){
		BasicDAO dao = new BasicDAO();		
		String[] columnCN =  dao.getColumnNameCN(column,tableName);
		return dao.arrayToList(column, columnCN);
	}
	
	/**
	 * ��ȡ����Ϣ
	 * @param tableName
	 * @return TableModel
	 * */
	public TableModel getTable(String tableName){
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		TableModel tModel = new TableModel();
		tModel = DatabaseTables.getTable(tableName);
		if(tModel == null){
			DatabaseTableManipulateImpl dtmi = new DatabaseTableManipulateImpl(); 
			dtmi.getTableColumns(dao,tableName);
			tModel = DatabaseTables.getTable(tableName);
		}
		
		return tModel;
	}	
	
	/**
	 * ��ȡ��������ֶ�����
	 * @param  tableName
	 * @return String[]
	 * */
	public String[] getTableColumn(String tableName){
		TableModel tModel = getTable(tableName);
		List<ColumnModel> cList = tModel.getColumns();
		String[] columns = new String[cList.size()];
		
		int i=0;
		for(ColumnModel cModel : cList){
			columns[i++] = cModel.getName();
		}
		
		return columns;
	}
	
	/**
	 * �жϲ��������е�Ԫ���Ƿ��Ǳ���ֶ���
	 * @param  tableName
	 * @param  attributes
	 * @return boolean
	 * */
	public boolean checkColumnExists(String tableName, String[] attributes){
		TableModel table = getTable(tableName);
		List<ColumnModel> colList = table.getColumns();
		String[] columns = new String[colList.size()];
		
		int i=0;
		for(ColumnModel model : colList){
			columns[i++] = model.getName().toLowerCase();
		}
		List<String> fir = new ArrayList<String>(Arrays.asList(columns));
		List<String> sec = Arrays.asList(attributes);	
		boolean flag= fir.containsAll(sec);
		if(!flag){
			System.out.println("���ݿ��"+tableName+"�ֶΣ�"+fir);
			System.out.println("ҳ���ֶΣ�"+sec);
		}
		return flag;
	}

	
	/**
	 * �жϱ�ķǿ��ֶ��Ƿ���ֵ
	 * @param  tableName
	 * @param  map
	 * @return boolean
	 * */
	public boolean checkDataNull(String tableName, HashMap<String, String> map){
		boolean result = false;
		TableModel table = getTable(tableName);
		List<ColumnModel> colList = table.getColumns();		
		String[] notNullColumns = new String[colList.size()];//��������ֶ�
		
		int i=0;
		for(ColumnModel model : colList){			
			if(!model.isNullable() && StringUtils.isNull(model.getDefaultValue())){
				notNullColumns[i++] = model.getName().toLowerCase();
			}
		}
		
		for(String column : notNullColumns){
			if(!StringUtil.isNull(column)){
				if(StringUtil.isNull(map.get(column))){
					result = true;
					break;
				}
			}
		}		
		return result;
	}
	
	/**
	 * �жϱ�ķǿ��ֶ��Ƿ���ֵ���������ݣ�
	 * @param  tableName
	 * @param  map
	 * @return boolean
	 * */
	public boolean checkDataNullBatch(String tableName, HashMap<String, String[]> map){
		TableModel table = getTable(tableName);
		List<ColumnModel> colList = table.getColumns();		
		boolean result = false;
		
		for (ColumnModel model: colList){
			if (!StringUtil.isNull(model.getName()) && !model.isNullable()){
				String[] values = map.get(model.getName().toLowerCase());
				if(values != null){
					for(String str : values){
						if(StringUtil.isNull(str)){
							result = true;
							break;
						}
					}
				}else{
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * ���������ж����ݼ�¼�Ƿ����
	 * @param  tabName
	 * @param  valueMap
	 * @param  pkValue
	 * @return boolean
	 * */
	public boolean checkDataExists(String tabName, 
			                       HashMap<String, String> valueMap,
			                       String pkValue){
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);		
		String[] primaryKeys = table.getPrimaryKey().split(",");
		Map<String,Object> whereMap = util.loadSqlWhere(primaryKeys, 
						                valueMap, 
						                "update");
		String sql = "";
		String[] whereValue = null;
		for(Object str : whereMap.keySet()){
			sql = str.toString();
			whereValue = (String[])whereMap.get(str);			
		}
		sql = "select count(*) num from " + tabName + sql;
		if(!StringUtil.isNull(pkValue)){
			sql = StringUtils.joinStr("select count(*) num from ",
				  tabName,  
				  " where ",  
				  table.getPrimaryKey().replaceAll(",", "||"),
				  " =?");
			whereValue = new String[]{pkValue};
		}
		
		String num = dao.getOneRs(sql, whereValue, "num");
		
		return Integer.parseInt(StringUtil.isNull(num) ? "0" : num) >0 ? true : false;
	}
	
	/**
	 * ���map�е�ֵ�Ƿ��ǿ�
	 * @param paramMap
	 * @return boolean
	 * */
	public boolean checkParamNull(Map<String, String[]> paramMap){
		boolean result = false;
		for(Object str : paramMap.keySet()){
			String [] value = (String[])paramMap.get(str.toString());
			if(value.length < 1){
				result = true;
				break;
			}
		}
		if(paramMap.isEmpty()){
			result = true;
		}
		return result;
	}
	
	/**
	 * ���map��ֵ���鳤���Ƿ�һ��
	 * @param valueMap
	 * @return boolean
	 * */
	public boolean checkValueLeng(HashMap<String, String[]> valueMap){
		boolean result = true;
		int valueLeng = 0;
		for(Object key : valueMap.keySet()){
			String param = key.toString();
			if(!("pkString".equalsIgnoreCase(param) || "pkValue".equalsIgnoreCase(param))){
				String[] value = valueMap.get(key.toString());
				if(value.length <1){
					result = false;
					break;
				}else if(valueLeng == 0){
					valueLeng = value.length;
				}else{
					if(valueLeng != value.length){
						result = false;
						break;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * ���ݲ���
	 * @param tabName
	 * @param valueMap
	 * @param opreationType
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean insertPageData(String tabName, 
			                      HashMap<String, String> valueMap,
			                      String opreationType,
			                      User user) throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		boolean result = false;
		JdbcUtil util = new JdbcUtil();
		Map<String,Object> map = util.loadSql(tabName, valueMap,"insert");
		String sql = "";
		String[] colValue = null;
		
		for(Object str : map.keySet()){
			sql = str.toString();
			colValue = (String[])map.get(str);			
		}
		if(!StringUtil.isNull(sql)){//sql��Ϊ��ʱִ�в������
			result = dao.insert(sql, colValue, tabName, user);
		}
		return result;
	}
	
	/**
	 * �����޸ģ��������ݿ����������޸ģ�
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean updatePageData(String tabName, 
			                      HashMap<String, String> valueMap,
			                      User user) throws Exception{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		String[] primaryKeys = table.getPrimaryKey().split(",");
		Map<String,Object> map = util.loadSql(tabName, valueMap,"update");
		Map<String,Object> whereMap = util.loadSqlWhere(primaryKeys, 
				                                         valueMap, 
				                                         "update");
		String sql = "";		
		String[] colValue = null;
		//��where�����������ֶ�ֵ
		for(Map.Entry<String,Object> entry : map.entrySet()){
			sql = entry.getKey();
			colValue = (String[])entry.getValue();			
		}
		//where�������ֶ�ֵ
		String whereSql = "";
		String[] whereValue = null;
		String[] input = new String[colValue.length + primaryKeys.length];
		for(Object str : whereMap.keySet()){
			whereSql = str.toString();
			whereValue = (String[])whereMap.get(str);			
		}
		//���ֶ�ֵ�������ֶ�ֵ�ϲ���һ��������
		for(int i=0; i<colValue.length; i++){
			input[i] = colValue[i];
		}
		for(int i=0; i<whereValue.length; i++){
			input[colValue.length + i] = whereValue[i];
		}
		return dao.runUpdate(sql+whereSql, input, tabName, user);
	}
	
	/**
	 * �����޸ģ����ݴ��������ֵ�����޸ģ�
	 * @param tabName
	 * @param valueMap
	 * @param pkValue
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean updatePageData(String tabName, 
			                      HashMap<String, String> valueMap,
			                      String pkValue,
			                      User user) throws Exception{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		Map<String,Object> map = util.loadSql(tabName, valueMap,"update");
		
		String sql = "";		
		String[] colValue = null;
		//��where�����������ֶ�ֵ
		for(Map.Entry<String,Object> entry : map.entrySet()){
			sql = entry.getKey();
			colValue = (String[])entry.getValue();			
		}
		//where�������ֶ�ֵ
		String whereSql = " where " 
			              + table.getPrimaryKey().replaceAll(",", "||") 
			              + "=?";
		String[] whereValue = new String[]{pkValue};
		
		String[] input = new String[colValue.length + whereValue.length];		
		//���ֶ�ֵ�������ֶ�ֵ�ϲ���һ��������
		for(int i=0; i<colValue.length; i++){
			input[i] = colValue[i];
		}
		for(int i=0; i<whereValue.length; i++){
			input[colValue.length + i] = whereValue[i];
		}
		sql += whereSql;
		return dao.runUpdate(sql, input, tabName, user);
	}
	
	/**
	 * ������������
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean insertBatchPageData(String tabName, 
			                    HashMap<String, String[]> valueMap,
			                    User user) throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		valueMap.put("primarykey", new String[]{table.getPrimaryKey()});
		
		String[] sqlArr = util.loadSqlBatch(tabName,valueMap, "insert");
		int[] count = dao.runBatch(sqlArr, tabName, user);
		return dao.checkBatch(count);
	}
	
	/**
	 * ���������޸�
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean updateBatchPageData(String tabName, 
                                        HashMap<String, String[]> valueMap,
                                        User user) 
	                                    throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		String[] primaryKeys = table.getPrimaryKey().split(",");
		String[] sqlArr = util.loadSqlBatch(tabName, valueMap, primaryKeys, "update");
		int[] count = dao.runBatch(sqlArr, tabName, user);
		return dao.checkBatch(count);    
    }
	
	/**
	 * ��������ɾ��
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean deletePageData(String tabName, 
                                  HashMap<String, String[]> valueMap,
                                  User user) 
	                              throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		String pkString = table.getPrimaryKey();
		String[] primaryKeys = StringUtils.isNotNull(pkString) ? pkString.split(",") : new String[]{};
		String[] sqlArr = util.loadSqlBatch(tabName, valueMap, primaryKeys, "delete");
		int[] count = dao.runBatch(sqlArr);
		return dao.checkBatch(count);
	}
	
	/**
	 * �������
	 * @param tabName
	 * @param parimaryMap
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException
	 * */
	public boolean auditingPageData(String tabName,
			                        HashMap<String, String[]> parimaryMap,
			                        HashMap<String, String> valueMap,
			                        User user) throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		String primaryKey = table.getPrimaryKey().replaceAll(",", "||");
		
		for(Map.Entry<String, String[]> entry : parimaryMap.entrySet()){
			String[] value = (String[]) entry.getValue();
			parimaryMap = new HashMap<String, String[]>();			
			parimaryMap.put(primaryKey, value);
		}
		
		String[] value = new String[2];
		for(Map.Entry<String, String> entry : valueMap.entrySet()){
			value[0] = entry.getKey();
			value[1] = entry.getValue().getClass().isArray() 
			           ? (String)Array.get(entry.getValue(), 0) 
			           : entry.getValue();
		}
		
		String[] sqlArr = util.loadSqlBatch(tabName, parimaryMap, valueMap, "auditing");
		int[] count = dao.runBatch(sqlArr, tabName, user);
		return dao.checkBatch(count);		
	}	
	
	
	/**
	 * �����������ݡ�������ɾ������롣
	 * @param tableMap
	 * @param primaryMap
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException
	 * */
	public boolean saveDataBatch(HashMap<String, String> tableMap,
			                     HashMap<String, String[]>primaryMap, 
			                     HashMap<String, String[]>valueMap,
			                     User user) throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();		
		JdbcUtil util = new JdbcUtil();
		String tabName = tableMap.get("tableName");
		String viewName = tableMap.get("viewName");
		
		TableModel table = getTable(tabName);
		String[] pkValue = new String[primaryMap.size()];
		
		for(String key : primaryMap.keySet()){
			pkValue = primaryMap.get(key.toLowerCase());
		}
		
		String[] sqlArr = new String[pkValue.length*2];
		int j = 0;
		int f = 0;
		for(String pkV : pkValue){//ѭ������ֵ
			String[] inputArr = new String[valueMap.size()];//Ҫ������ֶ�
			String[] inputValue = new String[valueMap.size()];//Ҫ������ֶ�ֵ
			HashMap<String, String> map = selectDataByPk(tabName, viewName, pkV);//Ҫ����������Լ�����������Ϣ
			if(map == null || map.size()<1){				
				return false;				
			}
			int i=0;
			for(Map.Entry<String, String[]> entry : valueMap.entrySet()){
				inputArr[i] = entry.getKey();
				inputValue[i] = entry.getValue()[j];				
				i++;
			}
			
			//��ȡsql			
			HashMap<String, String> sqlMap = util.loadSaveSql(tabName,map,table.getPrimaryKey(),inputArr,inputValue);
			if(j==0){
				sqlArr[j] = sqlMap.get("deleteSql");
				sqlArr[j+1] = sqlMap.get("insertSql");
			}else{
				sqlArr[j+f] = sqlMap.get("deleteSql");
				sqlArr[j+f+1] = sqlMap.get("insertSql");
			}
			j++;
			f++;
		}
		int[] count = dao.runBatch(sqlArr, tabName, user);		
		return dao.checkBatch(count);
	}
	
	/**
	 * �����������ݡ�������ɾ������롣
	 * @param tableMap
	 * @param primaryArrayMap
	 * @param primaryMap
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean saveDataBatch(HashMap<String, String> tableMap, 
			                     HashMap<String, String[]>primaryArrayMap, 
                                 HashMap<String, String>primaryMap, 
                                 HashMap<String, String[]>valueMap,
                                 User user) throws SQLException{
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();		
		JdbcUtil util = new JdbcUtil();
		String[] pkValue = new String[primaryArrayMap.size()];
		String tabName = tableMap.get("tableName");

		for(String key : primaryArrayMap.keySet()){
			pkValue = primaryArrayMap.get(key.toLowerCase());
		}
		
		String[] sqlArr = new String[pkValue.length*2];
		int inputLength = valueMap.size() + primaryArrayMap.size() + primaryMap.size();
		int j = 0;
		int f = 0;
		for(String pkV : pkValue){
			StringBuilder pkString = new StringBuilder();
			StringBuilder pkStringV = new StringBuilder();
			String[] inputArr = new String[inputLength];//Ҫ������ֶ�
			String[] inputValue = new String[inputLength];//Ҫ������ֶ�ֵ
			int i=0;
			for(Map.Entry<String, String[]> entry : valueMap.entrySet()){
				inputArr[i] = entry.getKey();
				inputValue[i] = entry.getValue()[j];
				i++;
			}
			int m = 0;
			for(Map.Entry<String, String[]> entry : primaryArrayMap.entrySet()){
				inputArr[i+m] = entry.getKey();
				inputValue[i+m] = entry.getValue()[j];
				//�������
				pkString.append(inputArr[i+m]);
				pkString.append(",");
				pkStringV.append(inputValue[i+m]);
				m++;
			}
			int n = 0;
			for(Map.Entry<String, String> entry : primaryMap.entrySet()){
				inputArr[i+m+n] = entry.getKey();
				inputValue[i+m+n] = entry.getValue();
				//�������
				pkString.append(inputArr[i+m+n]);
				pkString.append(",");
				pkStringV.append(inputValue[i+m+n]);
				n++;
			}
			if(!StringUtil.isNull(pkString.toString())){
				pkString.deleteCharAt(pkString.length()-1);				
			}
			
			//��ȡsql
			HashMap<String, String> sqlMap = util.loadSaveSql(tabName,
					                                          pkString.toString(),
					                                          pkStringV.toString(),
					                                          inputArr,
					                                          inputValue);
			sqlArr[j+f] = sqlMap.get("deleteSql");
			sqlArr[j+f+1] = sqlMap.get("insertSql");
			f++;
			j++;
		}
		
		int[] count = dao.runBatch(sqlArr, tabName, user);		
		return dao.checkBatch(count);
	}
	
	/**
	 * ����������ѯ��Ϣ
	 * @param tableName
	 * @param viewName
	 * @param pkValue
	 * @return HashMap<String,String>
	 * */
	public HashMap<String,String> selectDataByPk(String tableName, 
			                                 String viewName, 
			                                 String pkValue){
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tableName);	
		TableModel view = getTable(viewName);	
		List<ColumnModel> columns = view.getColumns();
		
		HashMap<String,String> map = new HashMap<String, String>();
		String primarykey = table == null ? "" : table.getPrimaryKey();
		map.put("pkValue", pkValue);
		map.put("pkString", StringUtils.isNotNull(primarykey) ? primarykey.replaceAll(",", "||") : "");
		Map<String, Object> rsMap = util.loadSql(viewName, map, "queryOne");
		
		String sql = "";		
		for(Map.Entry<String,Object> entry : rsMap.entrySet()){
			sql = entry.getKey();
		}
		
		//Ҫ��ѯ���ֶ�
		String[] outputValue = new String[columns.size()+1];
		outputValue[0] = "pkValue";
		int i=1;
		for(ColumnModel model : columns){
			outputValue[i++] = model.getName().toLowerCase();
		}
		map = dao.getMap(sql, new String[]{}, outputValue);
		if(map != null && map.size()>1){
			for(String key : outputValue){
				map.put("save_"+key, map.get(key));
			}
		}
		
		return map;
	}
	
	/**
	 * �������ݷ�ҳ��ѯ
	 * @param tabName
	 * @param viewName
	 * @param paramMap
	 * @param pages
	 * @param outputValue
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> queryData(String tabName,
								  String viewName,
			                      Map<String, Object> paramMap,
			                      Pages pages,
			                      String[] outputValue){
		JdbcUtil util = new JdbcUtil();
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		TableModel table = getTable(tabName);
		HashMap<String, String> pageMap = new HashMap<String, String>();
		//��ҳ��Ϣ
		pageMap.put("start", pages.getStart() + "");
		pageMap.put("pagesize", pages.getPageSize() + "");
		pageMap.put("primarykey", table != null && null !=table.getPrimaryKey()  ? table.getPrimaryKey().replaceAll(",", "||") : "");//��������
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
                                                       paramMap, 
                                                       pageMap, 
                                                       true);//��ҳ��ѯ
		String sql = "";//��ѯ�ֶ���Ϣ��sql
		String countSql = "";//��ѯ�ܼ�¼����sql
		String[] inputValue = {};//����ֵ
		for(Map.Entry<String, Object> entry : sqlMap.entrySet()){
			String key = entry.getKey();
			if("sql".equalsIgnoreCase(key)){
				sql = entry.getValue().toString();				
			}else if("countSql".equalsIgnoreCase(key)){
				countSql = entry.getValue().toString();
			}else if("value".equalsIgnoreCase(key)){
				inputValue = (String[])entry.getValue();
			}
			
		}
		//�ܼ�¼��
		String count = dao.getOneRs(countSql, inputValue, "num");		
		pages.setMaxRecord(Integer.parseInt(StringUtil.isNull(count) ? "0" : count));
		return dao.rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * �������ݲ�ѯ
	 * @param tabName
	 * @param viewName
	 * @param paramMap
	 * @param outputValue
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> queryData(String tabName,
			                             String viewName,
                                         Map<String, Object> paramMap,
                                         String[] outputValue){
		JdbcUtil util = new JdbcUtil();
		//TODO ��������ʱ��Ҫ
		BasicDAO dao = new BasicDAO();
		TableModel table = getTable(tabName);	
		HashMap<String, String> pageMap = new HashMap<String, String>();			
		pageMap.put("primarykey", table != null && table.getPrimaryKey() != null ? table.getPrimaryKey().replaceAll(",", "||") : "");//��������
		
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
		                                 paramMap, 
		                                 pageMap, 
		                                 false);//��ҳ��ѯ
		String sql = "";//��ѯ�ֶ���Ϣ��sql
		String[] inputValue = {};//����ֵ
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
		
		return dao.rsToVator(sql, inputValue, outputValue);
	}

	/**
	 *  �����û�����ȡ�û����󣬽�������Ҫ��Ϣ..
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-12-01 16:46
	 * @param userName
	 * @return xgxt.form.User
	 * @throw Exception
	 */
    public User getUser(String userName) throws Exception {

    	User user = new User();
		BasicDAO dao = new BasicDAO();
		String sql_stu = "SELECT xh userName,xm realName,xydm userDep,xymc userDepName " +
						   		"FROM VIEW_XSJBXX WHERE xh = ?";
		HashMap<String,String> userMap = dao.getMapNotOut(sql_stu,new String[]{userName});

		if(userMap == null || userMap.isEmpty()){
			String sql_tea = "SELECT y.yhm userName,y.xm realName,y.szbm userDep,z.bmmc userDepName " +
									"FROM YHB y LEFT JOIN ZXBZ_XXBMDM z ON y.SZBM = z.BMDM WHERE y.yhm = ?" ;
			userMap = dao.getMapNotOut(sql_tea,new String[]{userName});
		}

		user.setUserName(userMap.get("username"));
		user.setRealName(userMap.get("realname"));
		user.setUserDep(userMap.get("userdep"));
		user.setUserDepName(userMap.get("userdepname"));
		return user;
    }
    //TODO ����ʱ��Ҫ
//	public BasicDAO getDao() {
//		return dao;
//	}
//
//	public void setDao(BasicDAO dao) {
//		this.dao = dao;
//	}	
}
