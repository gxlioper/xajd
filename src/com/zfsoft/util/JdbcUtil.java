package com.zfsoft.util;

import java.util.HashMap;
import java.util.Map;

public class JdbcUtil {	
	
	/**
	 * 生成sql语句
	 * @param tableName
	 * @param paramMap
	 * @param opreationType
	 * @return Map<String,Object>
	 * */
	public Map<String,Object> loadSql(String tableName,
									   Map<String,String> paramMap,
									   String opreationType){
		CreateSqlStrategy createSqlStrategy = null;
		if("insert".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateInsertSqlStrategy();
		}else if("update".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateUpdateSqlStrategy();
		}else if("delete".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateDeleteSqlStrategy();
		}else if("queryOne".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateQuerySqlStrategy();
		}
		return createSqlStrategy.createSql(tableName, paramMap);
	}
	
	/**
	 * 生成查询语句
	 * @param tableName
	 * @param paramMap
	 * @param pagesMap
	 * @param pagination
	 * @return Map<String,Object>
	 * */
	public Map<String,Object> loadQuerySql(String tableName,
			                  Map<String,Object> paramMap,	
			                  Map<String,String> pagesMap,
			                  boolean pagination){
		CreateQuerySqlStrategy createSqlStrategy = new CreateQuerySqlStrategy();		
		return createSqlStrategy.createQuerySql(tableName, paramMap, pagesMap, pagination);
	}
	
	/**
	 * 生成条件语句
	 * @param primaryKeys
	 * @param paramMap
	 * @param opreationType
	 * @return Map<String,Object>
	 * */
	public Map<String,Object> loadSqlWhere(String[] primaryKeys,
			                          Map<String,String> paramMap,
			                          String opreationType){
		CreateSqlStrategy createSqlStrategy = null;
		if("insert".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateInsertSqlStrategy();
		}else if("update".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateUpdateSqlStrategy();
		}else if("delete".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateDeleteSqlStrategy();
		}		
		return createSqlStrategy.createSqlWhere(primaryKeys, paramMap);
	}
	
	/**
	 * 生成批量sql语句
	 * @param tableName
	 * @param paramMap
	 * @param opreationType
	 * @return String[]
	 * */
	public String[] loadSqlBatch(String tableName,
			   Map<String,String[]> paramMap,
			   String opreationType){
		CreateSqlStrategy createSqlStrategy = null;
		if("insert".equalsIgnoreCase(opreationType)){
		createSqlStrategy = new CreateInsertSqlStrategy();
		}else if("update".equalsIgnoreCase(opreationType)){
		createSqlStrategy = new CreateUpdateSqlStrategy();
		}else if("delete".equalsIgnoreCase(opreationType)){
		createSqlStrategy = new CreateDeleteSqlStrategy();
		}		
		return createSqlStrategy.createSqlBatch(tableName, paramMap);
	}
	
	/**
	 * 生成批量sql语句
	 * @param tableName
	 * @param paramMap
	 * @return String[]
	 * */
	public String[] loadSqlBatch(String tableName,
								 Map<String,String[]> paramMap,
								 String[] primaryKeys,
								 String opreationType){
		CreateSqlStrategy createSqlStrategy = null;
		if("insert".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateInsertSqlStrategy();
		}else if("update".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateUpdateSqlStrategy();
		}else if("delete".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateDeleteSqlStrategy();
		}else if("auditing".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateAuditingSqlStrategy();
		}
		return createSqlStrategy.createSqlBatch(tableName, paramMap, primaryKeys);
	}
	
	/**
	 * 生成批量sql语句
	 * @param tableName
	 * @param paramMap
	 * @param valueMap
	 * @param opreationType
	 * */
	public String[] loadSqlBatch(String tableName,
								 Map<String,String[]> paramMap,
								 Map<String,String> valueMap,
								 String opreationType){
		CreateSqlStrategy createSqlStrategy = null;
		if("insert".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateInsertSqlStrategy();
		}else if("update".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateUpdateSqlStrategy();
		}else if("delete".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateDeleteSqlStrategy();
		}else if("auditing".equalsIgnoreCase(opreationType)){
			createSqlStrategy = new CreateAuditingSqlStrategy();
		}
		return createSqlStrategy.createSqlBatch(tableName, paramMap, valueMap);
	}
	
	/**
	 * 生成保存sql语句(先删除后插入)
	 * @param tabName
	 * @param map
	 * @param primaryKeys
	 * @param inputArr
	 * @param inputValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> loadSaveSql(String tabName,
			                                   HashMap<String, String>map,
			                                   String primaryKeys,
			                                   String[] inputArr,
			                                   String[] inputValue){
		String pk = primaryKeys.replaceAll(",", "||");
		String[] primary = primaryKeys.split(",");
		String pkValue = "";
		
		for(String key : primary){
			pkValue += map.get(key.toLowerCase());
		}
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		StringBuilder deleteSql = new StringBuilder();
		StringBuilder insertSql = new StringBuilder();
		//删除sql
		deleteSql.append("delete from ");
		deleteSql.append(tabName);
		deleteSql.append(" where ");
		deleteSql.append(pk);
		deleteSql.append(" = '");
		deleteSql.append(pkValue);
		deleteSql.append("'");
		
		//插入sql
		StringBuilder tmp = new StringBuilder();
		insertSql.append("insert into ");
		insertSql.append(tabName);
		insertSql.append("(");
		int i=0;
		for(String col : inputArr){
			if(i == inputArr.length-1){
				insertSql.append(col);
				tmp.append("'");
				tmp.append(inputValue[i]);
				tmp.append("'");
			}else{
				insertSql.append(col);
				insertSql.append(",");
				tmp.append("'");
				tmp.append(inputValue[i]);
				tmp.append("',");
			}
			i++;
		}
		
		int m = 0;		
		for(String primaryKey : primary){
			if(m == 0 && i==0){
				insertSql.append(primaryKey.toLowerCase());
				tmp.append("'");
				tmp.append(map.get(primaryKey.toLowerCase()));
				tmp.append("'");
			}else {
				insertSql.append(",");
				insertSql.append(primaryKey.toLowerCase());
				tmp.append(",'");
				tmp.append(map.get(primaryKey.toLowerCase()));
				tmp.append("'");
			}
			m++;
		}
		insertSql.append(") values(");
		insertSql.append(tmp.toString());
		insertSql.append(")");
		sqlMap.put("deleteSql", deleteSql.toString());
		sqlMap.put("insertSql", insertSql.toString());
		return sqlMap;
	}
	
	/**
	 * 生成保存sql语句(先删除后插入)
	 * @param tabName
	 * @param primarykey
	 * @param primarykeyV
	 * @param inputArr
	 * @param inputValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> loadSaveSql(String tabName,
                                               String primarykey,
                                               String primarykeyV,
									           String[] inputArr,
									           String[] inputValue){
		HashMap<String, String> sqlMap = new HashMap<String, String>();
		StringBuilder deleteSql = new StringBuilder();
		StringBuilder insertSql = new StringBuilder();
		
		primarykey = primarykey.replaceAll(",", "||");
		//删除sql
		deleteSql.append("delete from ");
		deleteSql.append(tabName);
		deleteSql.append(" where ");
		deleteSql.append(primarykey);
		deleteSql.append(" = '");
		deleteSql.append(primarykeyV);
		deleteSql.append("'");
		
		//插入sql
		StringBuilder tmp = new StringBuilder();
		insertSql.append("insert into ");
		insertSql.append(tabName);
		insertSql.append("(");
		
		int i=0;
		for(String col : inputArr){
			if(i == inputArr.length-1){
				insertSql.append(col);
				tmp.append("'");
				tmp.append(inputValue[i]);
				tmp.append("'");
			}else{
				insertSql.append(col);
				insertSql.append(",");
				tmp.append("'");
				tmp.append(inputValue[i]);
				tmp.append("',");
			}
			i++;
		}
		insertSql.append(") values(");
		insertSql.append(tmp.toString());
		insertSql.append(")");
		
		sqlMap.put("deleteSql", deleteSql.toString());
		sqlMap.put("insertSql", insertSql.toString());
		return sqlMap;
	}
	
}
