package com.zfsoft.util;

import java.util.Map;

public class CreateDeleteSqlStrategy extends CreateSqlStrategy{

	/**
	 * ����ɾ��sql
	 * @param tableName
	 * @param paramMap
	 * @return Map<String,Object>
	 * */
	@Override
	public Map<String,Object> createSql(String tableName, 
			                            Map<String, String> paramMap) {		
		StringBuilder mainSql = new StringBuilder("delete from ");
		mainSql.append(tableName);
		mainSql.append("where ");
		StringBuilder placeholder = new StringBuilder();
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue = entry.getValue().toString();
			if(paramValue != null){
				mainSql.append(paramName.toLowerCase());
				mainSql.append(",");
				placeholder.append("?,");
			}
		}
		mainSql.deleteCharAt(mainSql.length()-1);
		mainSql.append(") values (");
		placeholder.deleteCharAt(placeholder.length() - 1);
		mainSql.append(placeholder);
		mainSql.append(")");
		mainSql.toString();
		return null;
	}

	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap) {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/**
	 * ��������ɾ��sql
	 * @param tableName
	 * @param paramMap
	 * @param primaryKeys
	 * @return String[]
	 * */
	@Override
	public String[] createSqlBatch(String tableName,
			                       Map<String, String[]> paramMap, 
			                       String[] primaryKeys) {
		String[] sqlArr = new String[paramMap.size()];	
		//��ȡ����ֵ
		String[] value = {};		
		for(Object key : paramMap.keySet()){
			value = paramMap.get(key); 
		}
		
		//��ȡ����
		StringBuilder pk = new StringBuilder();	
		int j=0;
		for(String key : primaryKeys){
			if(j++ == primaryKeys.length-1){
				pk.append(key.toLowerCase());
			}else{
				pk.append(key);
				pk.append("||");
			}
		}
		
		sqlArr = new String[value.length];
		int i=0;
		for(String pkValue : value){
			StringBuilder deleteSql = new StringBuilder("delete from ");
			deleteSql.append(tableName);
			deleteSql.append(" where ");
			deleteSql.append(pk);
			deleteSql.append(" = '");
			deleteSql.append(pkValue);
			deleteSql.append("'");
			sqlArr[i++] = deleteSql.toString();
		}
		return sqlArr;
	}

	@Override
	public Map<String, Object> createSqlWhere(String[] primaryKeys,
			Map<String, String> paramMap) {
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, Map<String, String> valueMap) {
		// TODO �Զ����ɷ������
		return null;
	}

	

}
