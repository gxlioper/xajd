package com.zfsoft.util;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

public class CreateUpdateSqlStrategy extends CreateSqlStrategy{

	/**
	 * ƴ��where����֮ǰ�ĸ������
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
	 * ���ݱ������ƴ��where����
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
		// TODO �Զ����ɷ������
		return null;
	}

	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, String[] primaryKeys) {
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
