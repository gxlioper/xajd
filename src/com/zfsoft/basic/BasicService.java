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
	//TODO 测试时需要
	BasicDAO dao;
	
	/**
	 * 查询表头信息
	 * @param tableName
	 * @param outputValue
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName, 
			                                      String[] outputValue){
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		String[] colCN = dao.getColumnNameCN(outputValue, tableName);
		return dao.arrayToList(outputValue, colCN);
	}
	
	/**
	 * 获取表字段注释信息
	 * @param  tableName
	 * @param  column
	 * @return String[]
	 * */
	public String[] getColumnComment(String tableName, String[] column){
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();		
		return dao.getColumnNameCN(column, tableName);
	}
	
	/**
	 * 获取表的列信息
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
	 * 获取表信息
	 * @param tableName
	 * @return TableModel
	 * */
	public TableModel getTable(String tableName){
		//TODO 程序运行时需要
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
	 * 获取表的所有字段名称
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
	 * 判断参数数组中的元素是否是表的字段名
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
			System.out.println("数据库表"+tableName+"字段："+fir);
			System.out.println("页面字段："+sec);
		}
		return flag;
	}

	
	/**
	 * 判断表的非空字段是否有值
	 * @param  tableName
	 * @param  map
	 * @return boolean
	 * */
	public boolean checkDataNull(String tableName, HashMap<String, String> map){
		boolean result = false;
		TableModel table = getTable(tableName);
		List<ColumnModel> colList = table.getColumns();		
		String[] notNullColumns = new String[colList.size()];//表的主键字段
		
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
	 * 判断表的非空字段是否有值（批量数据）
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
	 * 根据主键判断数据记录是否存在
	 * @param  tabName
	 * @param  valueMap
	 * @param  pkValue
	 * @return boolean
	 * */
	public boolean checkDataExists(String tabName, 
			                       HashMap<String, String> valueMap,
			                       String pkValue){
		//TODO 程序运行时需要
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
	 * 检测map中的值是否是空
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
	 * 检测map中值数组长度是否一致
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
	 * 数据插入
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
		//TODO 程序运行时需要
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
		if(!StringUtil.isNull(sql)){//sql不为空时执行插入操作
			result = dao.insert(sql, colValue, tabName, user);
		}
		return result;
	}
	
	/**
	 * 数据修改（根据数据库主键进行修改）
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean updatePageData(String tabName, 
			                      HashMap<String, String> valueMap,
			                      User user) throws Exception{
		//TODO 程序运行时需要
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
		//除where条件的语句和字段值
		for(Map.Entry<String,Object> entry : map.entrySet()){
			sql = entry.getKey();
			colValue = (String[])entry.getValue();			
		}
		//where条件和字段值
		String whereSql = "";
		String[] whereValue = null;
		String[] input = new String[colValue.length + primaryKeys.length];
		for(Object str : whereMap.keySet()){
			whereSql = str.toString();
			whereValue = (String[])whereMap.get(str);			
		}
		//将字段值和条件字段值合并到一个数组中
		for(int i=0; i<colValue.length; i++){
			input[i] = colValue[i];
		}
		for(int i=0; i<whereValue.length; i++){
			input[colValue.length + i] = whereValue[i];
		}
		return dao.runUpdate(sql+whereSql, input, tabName, user);
	}
	
	/**
	 * 数据修改（根据传入的主键值进行修改）
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
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		Map<String,Object> map = util.loadSql(tabName, valueMap,"update");
		
		String sql = "";		
		String[] colValue = null;
		//除where条件的语句和字段值
		for(Map.Entry<String,Object> entry : map.entrySet()){
			sql = entry.getKey();
			colValue = (String[])entry.getValue();			
		}
		//where条件和字段值
		String whereSql = " where " 
			              + table.getPrimaryKey().replaceAll(",", "||") 
			              + "=?";
		String[] whereValue = new String[]{pkValue};
		
		String[] input = new String[colValue.length + whereValue.length];		
		//将字段值和条件字段值合并到一个数组中
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
	 * 数据批量插入
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean insertBatchPageData(String tabName, 
			                    HashMap<String, String[]> valueMap,
			                    User user) throws SQLException{
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		valueMap.put("primarykey", new String[]{table.getPrimaryKey()});
		
		String[] sqlArr = util.loadSqlBatch(tabName,valueMap, "insert");
		int[] count = dao.runBatch(sqlArr, tabName, user);
		return dao.checkBatch(count);
	}
	
	/**
	 * 数据批量修改
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean updateBatchPageData(String tabName, 
                                        HashMap<String, String[]> valueMap,
                                        User user) 
	                                    throws SQLException{
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		JdbcUtil util = new JdbcUtil();
		TableModel table = getTable(tabName);
		String[] primaryKeys = table.getPrimaryKey().split(",");
		String[] sqlArr = util.loadSqlBatch(tabName, valueMap, primaryKeys, "update");
		int[] count = dao.runBatch(sqlArr, tabName, user);
		return dao.checkBatch(count);    
    }
	
	/**
	 * 数据批量删除
	 * @param tabName
	 * @param valueMap
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean deletePageData(String tabName, 
                                  HashMap<String, String[]> valueMap,
                                  User user) 
	                              throws SQLException{
		//TODO 程序运行时需要
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
	 * 批量审核
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
		//TODO 程序运行时需要
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
	 * 批量保存数据。数据先删除后插入。
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
		//TODO 程序运行时需要
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
		for(String pkV : pkValue){//循环主键值
			String[] inputArr = new String[valueMap.size()];//要保存的字段
			String[] inputValue = new String[valueMap.size()];//要保存的字段值
			HashMap<String, String> map = selectDataByPk(tabName, viewName, pkV);//要保存的主键以及其它已有信息
			if(map == null || map.size()<1){				
				return false;				
			}
			int i=0;
			for(Map.Entry<String, String[]> entry : valueMap.entrySet()){
				inputArr[i] = entry.getKey();
				inputValue[i] = entry.getValue()[j];				
				i++;
			}
			
			//获取sql			
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
	 * 批量保存数据。数据先删除后插入。
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
		//TODO 程序运行时需要
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
			String[] inputArr = new String[inputLength];//要保存的字段
			String[] inputValue = new String[inputLength];//要保存的字段值
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
				//组合主键
				pkString.append(inputArr[i+m]);
				pkString.append(",");
				pkStringV.append(inputValue[i+m]);
				m++;
			}
			int n = 0;
			for(Map.Entry<String, String> entry : primaryMap.entrySet()){
				inputArr[i+m+n] = entry.getKey();
				inputValue[i+m+n] = entry.getValue();
				//组合主键
				pkString.append(inputArr[i+m+n]);
				pkString.append(",");
				pkStringV.append(inputValue[i+m+n]);
				n++;
			}
			if(!StringUtil.isNull(pkString.toString())){
				pkString.deleteCharAt(pkString.length()-1);				
			}
			
			//获取sql
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
	 * 根据主键查询信息
	 * @param tableName
	 * @param viewName
	 * @param pkValue
	 * @return HashMap<String,String>
	 * */
	public HashMap<String,String> selectDataByPk(String tableName, 
			                                 String viewName, 
			                                 String pkValue){
		//TODO 程序运行时需要
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
		
		//要查询的字段
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
	 * 多条数据分页查询
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
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		TableModel table = getTable(tabName);
		HashMap<String, String> pageMap = new HashMap<String, String>();
		//分页信息
		pageMap.put("start", pages.getStart() + "");
		pageMap.put("pagesize", pages.getPageSize() + "");
		pageMap.put("primarykey", table != null && null !=table.getPrimaryKey()  ? table.getPrimaryKey().replaceAll(",", "||") : "");//联合主键
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
                                                       paramMap, 
                                                       pageMap, 
                                                       true);//分页查询
		String sql = "";//查询字段信息的sql
		String countSql = "";//查询总记录数的sql
		String[] inputValue = {};//条件值
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
		//总记录数
		String count = dao.getOneRs(countSql, inputValue, "num");		
		pages.setMaxRecord(Integer.parseInt(StringUtil.isNull(count) ? "0" : count));
		return dao.rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * 多条数据查询
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
		//TODO 程序运行时需要
		BasicDAO dao = new BasicDAO();
		TableModel table = getTable(tabName);	
		HashMap<String, String> pageMap = new HashMap<String, String>();			
		pageMap.put("primarykey", table != null && table.getPrimaryKey() != null ? table.getPrimaryKey().replaceAll(",", "||") : "");//联合主键
		
		Map<String, Object> sqlMap = util.loadQuerySql(viewName, 
		                                 paramMap, 
		                                 pageMap, 
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
		
		return dao.rsToVator(sql, inputValue, outputValue);
	}

	/**
	 *  根据用户名获取用户对象，仅包含简要信息..
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
    //TODO 测试时需要
//	public BasicDAO getDao() {
//		return dao;
//	}
//
//	public void setDao(BasicDAO dao) {
//		this.dao = dao;
//	}	
}
