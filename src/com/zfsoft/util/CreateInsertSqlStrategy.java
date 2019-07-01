package com.zfsoft.util;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

import xgxt.utils.String.StringUtils;



public class CreateInsertSqlStrategy extends CreateSqlStrategy{
	
	/**
	 * ��װ����sql
	 * @param tableName
	 * @param paramMap
	 * @return Map<String, Object>
	 * */
	public Map<String, Object> createSql(String tableName, 
			                             Map<String, String> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] valueList = new String[paramMap.size()];
		StringBuilder mainSql = new StringBuilder("insert into ");
		mainSql.append(tableName);
		mainSql.append("(");
		StringBuilder placeholder = new StringBuilder();
		int i=0;
		for(Map.Entry<String, String> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			String paramValue = entry.getValue().toString();			
			mainSql.append(paramName.toLowerCase());
			mainSql.append(",");
			placeholder.append("?,");//ռλ��
			valueList[i++] = paramValue.toString();//�ֶ�ֵ
		}
		
		mainSql.deleteCharAt(mainSql.length()-1);
		mainSql.append(") values (");
		if(!StringUtils.isNull(placeholder.toString())){
			placeholder.deleteCharAt(placeholder.length() - 1);
		}
		mainSql.append(placeholder);
		mainSql.append(")");
		
		if(paramMap.size()<1){//û��Ҫ���뵽���ݿ���ֶ�
			mainSql = new StringBuilder();
		}
		map.put(mainSql.toString(), valueList);
		return map;
	}

	/**
	 * ��װ����sql(insert)
	 * @param tableName
	 * @param paramMap
	 * @return String[]
	 * */
	public String[] createSqlBatch(String tableName,
			                       Map<String, String[]> paramMap) {
		String[] sqlArr = null;
		//��Ҫ����������ֶΡ�����ʱ��Ҫ�Ƚ�����ɾ��Ȼ����룬������paramMap�б������
		//ɾ�����ݵ�����������ֵ���ڲ������ݲ�����ȡ�ֶ�ʱ��Ҫ������������ֵ�����ֶ��޳�
		String[] columns = new String[paramMap.size()];		
		StringBuilder columnSql = new StringBuilder();//�ֶ�sql
		String pkValue = "";
		String pkString = "";
		String primarykey = "";
		int i = 0;
		for(Map.Entry<String, String[]> entry : paramMap.entrySet()){
			String paramName = entry.getKey();
			if("pkValue".equalsIgnoreCase(paramName)){
				pkValue = ((String[])paramMap.get(paramName))[0];				
			}else if("pkString".equalsIgnoreCase(paramName)){
				pkString = ((String[])paramMap.get(paramName))[0];
			}else if("primarykey".equalsIgnoreCase(paramName)){
				primarykey = ((String[])paramMap.get(paramName))[0];
			}else{
				columns[i] = paramName;
				columnSql.append(columns[i++]);
				columnSql.append(",");
			}
		}
		
		if(!StringUtil.isNull(columnSql.toString())){
			columnSql.deleteCharAt(columnSql.length()-1);
		}
		if(columns.length>0 && !StringUtils.isNull(columns[0])){
			int count = paramMap.get(columns[0]).length;
			if(!(StringUtil.isNull(pkString) || StringUtil.isNull(pkValue))){//�����������ɾ��		
				sqlArr = new String[count+1];
				//ɾ��sql
				sqlArr[0] = StringUtils.joinStr("delete from ", 
						                        tableName, 
						                        " where ", 
						                        pkString, 
						                        "='", 
						                        pkValue, 
						                        "'");
				//����insert���
				for(int j=0; j<count; j++){
					StringBuilder mainSql = new StringBuilder("insert into ");//����sql
					mainSql.append(tableName);
					mainSql.append("(");
					mainSql.append(columnSql);
					mainSql.append(") values (");
					StringBuilder valueSql = new StringBuilder();//�ֶ�ֵsql
					for(String column : columns){
						if(!StringUtil.isNull(column)){
							valueSql.append("'");
							valueSql.append(StringUtils.cleanString(paramMap.get(column)[j]));//���������ַ�
							valueSql.append("',");
						}
					}
					valueSql.deleteCharAt(valueSql.length()-1);
					mainSql.append(valueSql);
					mainSql.append(")");
					sqlArr[j+1] = mainSql.toString();
				}
			} else {//���������ɾ��				
				sqlArr = new String[count*2];
				String[] primaryKeys = primarykey.split(",");
				int num = 0;
				for(int j=0; j<count; j++){
					//ɾ��sql
					StringBuilder deleteSql = new StringBuilder();					
					deleteSql.append("delete from ");
					deleteSql.append(tableName);
					deleteSql.append(" where ");
					for(int k=0; k<primaryKeys.length; k++){
						if(k == primaryKeys.length-1){
							deleteSql.append(primaryKeys[k].toLowerCase());
							deleteSql.append(" = '");
							deleteSql.append(paramMap.get(primaryKeys[k].toLowerCase())[j]);
							deleteSql.append("'");
						}else{
							deleteSql.append(primaryKeys[k].toLowerCase());
							deleteSql.append(" = '");
							deleteSql.append(paramMap.get(primaryKeys[k].toLowerCase())[j]);
							deleteSql.append("' and ");
						}
					}
					//����sql
					StringBuilder mainSql = new StringBuilder("insert into ");//����sql
					mainSql.append(tableName);
					mainSql.append("(");
					mainSql.append(columnSql);
					mainSql.append(") values (");
					StringBuilder valueSql = new StringBuilder();//�ֶ�ֵsql
					for(String column : columns){
						if(!StringUtil.isNull(column)){
							valueSql.append("'");
							valueSql.append(StringUtils.cleanString(paramMap.get(column)[j]));//���������ַ�
							valueSql.append("',");
						}
					}
					valueSql.deleteCharAt(valueSql.length()-1);
					mainSql.append(valueSql);
					mainSql.append(")");
					
					sqlArr[j+num] =  deleteSql.toString();
					sqlArr[j+num+1] = mainSql.toString();
					num++;
				}
			}
			
		}
		return sqlArr;
	}
	
	/**
	 * ��װ����sql(update)
	 * @param tableName
	 * @param paramMap
	 * @param primaryKeys
	 * @return String[]
	 * */
	public String[] createSqlBatch(String tableName,
			                       Map<String, String[]> paramMap, 
			                       String[] primaryKeys) {
		String[] sqlArr = null;
		String[] columns = new String[paramMap.size()];//����������ֶ�		
		int i = 0;
		//��ȡҪ�޸ĵ��ֶ�
		for(Map.Entry<String, String[]> entry : paramMap.entrySet()){
			String paramName = entry.getKey();	
			columns[i++] = paramName;
		}
		//���sql
		if(columns.length>0 && !StringUtils.isNull(columns[0])){
			int count = paramMap.get(columns[0]).length;
			sqlArr = new String[count];
			for(int j=0; j<count; j++){
				StringBuilder mainSql = new StringBuilder("update ");//����sql
				mainSql.append(tableName);
				mainSql.append(" set ");
				for(String column : columns ){
					mainSql.append(column);
					mainSql.append(" = '");
					mainSql.append(StringUtils.cleanString(paramMap.get(column)[j]));
					mainSql.append("',");
				}
				mainSql.deleteCharAt(mainSql.length()-1);
				mainSql.append(" where 1=1 ");
				for(String column : primaryKeys ){
					mainSql.append(" and ");
					mainSql.append(column);
					mainSql.append(" = '");
					mainSql.append(StringUtils.cleanString(paramMap.get(column)[j]));
					mainSql.append("'");
				}
				sqlArr[j] = mainSql.toString();
			}			
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
