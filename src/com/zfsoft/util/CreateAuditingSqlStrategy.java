package com.zfsoft.util;

import java.util.Map;

public class CreateAuditingSqlStrategy extends CreateSqlStrategy{

	@Override
	public Map<String,Object> createSql(String tableName, Map<String, String> paramMap) {		
		
		return null;
	}

	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap) {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, String[] primaryKeys) {
		String[] sqlArr = null;
		String shzd = primaryKeys[0];
		String shzdValue = primaryKeys[1];
		String primarykey = "";
		
		String[] primaryValue = new String[paramMap.size()];
		for(Map.Entry<String,String[]> entry : paramMap.entrySet()){
			primarykey = entry.getKey();
			primaryValue = (String[])entry.getValue();			
		}
		
		sqlArr = new String[primaryValue.length];
		int i=0;
		for(String value : primaryValue){
			StringBuilder mainSql = new StringBuilder("update ");
			mainSql.append(tableName);
			mainSql.append(" set ");
			mainSql.append(shzd);
			mainSql.append("='");
			mainSql.append(shzdValue);
			mainSql.append("' where ");
			mainSql.append(primarykey);
			mainSql.append("='");
			mainSql.append(value);
			mainSql.append("'");
			sqlArr[i++] = mainSql.toString();
		}
		
		return sqlArr;
	}
	
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, Map<String, String> valueMap) {
		String[] sqlArr = null;
		String primarykey = "";
		
		String[] primaryValue = new String[paramMap.size()];
		for(Map.Entry<String,String[]> entry : paramMap.entrySet()){
			primarykey = entry.getKey();
			primaryValue = (String[])entry.getValue();			
		}
		
		sqlArr = new String[primaryValue.length];
		int i=0;
		for(String value : primaryValue){
			StringBuilder mainSql = new StringBuilder("update ");
			mainSql.append(tableName);
			mainSql.append(" set ");
			int j=0;
			for(Map.Entry<String, String> entry : valueMap.entrySet()){
				String paramName = entry.getKey();
				String paramValue = entry.getValue();
				
				mainSql.append(paramName.toLowerCase());
				if(j==valueMap.size()-1){
					mainSql.append("='" + paramValue+ "' ");
				}else{
					mainSql.append("='" + paramValue+ "', ");
				}
				j++;
			}
			mainSql.append("where ");
			mainSql.append(primarykey);
			mainSql.append("='");
			mainSql.append(value);
			mainSql.append("'");
			sqlArr[i++] = mainSql.toString();
		}
		
		return sqlArr;
	}

	@Override
	public Map<String, Object> createSqlWhere(String[] primaryKeys,
			Map<String, String> paramMap) {
		// TODO 自动生成方法存根
		return null;
	}

	

}
