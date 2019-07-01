package com.zfsoft.util;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

public class CreateUpdateSqlStrategy extends CreateSqlStrategy{

	/**
	 * 拼接where条件之前的更新语句
	 * @param tableName
	 * @param paramMap
	 * @return Map<String, Object>
	 * */
	public Map<String, Object> createSql(String tableName, 
			                             Map<String, String> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] valueList = new String[paramMap.size()];
		StringBuilder mainSql = new StringBuilder("update ");
		mainSql.append(tableName);
		mainSql.append(" set ");
		
		int i=0;
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue = entry.getValue();
			
			mainSql.append(paramName.toLowerCase());
			if(i==paramMap.size()-1){
				mainSql.append("=? ");
			}else{
				mainSql.append("=?, ");
			}
			valueList[i++] = paramValue;
		}		
		
		
		map.put(mainSql.toString(), valueList);
		return map;
	}
	
	/**
	 * 根据表的主键拼接where条件
	 * @param primaryKeys
	 * @param paramMap
	 * @return Map<String, Object>
	 * */
	public Map<String, Object> createSqlWhere(String[] primaryKeys,
			                                  Map<String, String> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder whereSql = new StringBuilder(" where 1=1 ");
		String[] whereValue = new String[primaryKeys.length];
		
		int i=0;
		for(String pkey : primaryKeys){
			if(!StringUtil.isNull(pkey)){
				whereSql.append(" and ");
				whereSql.append(pkey);
				whereSql.append("=?");
				whereValue[i++] = paramMap.get(pkey);
			}
		}		
		
		map.put(whereSql.toString(), whereValue);
		return map;
	}

	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap) {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, String[] primaryKeys) {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, Map<String, String> valueMap) {
		// TODO 自动生成方法存根
		return null;
	}	

}
