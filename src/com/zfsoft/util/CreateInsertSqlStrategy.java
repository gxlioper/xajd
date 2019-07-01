package com.zfsoft.util;

import java.util.HashMap;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

import xgxt.utils.String.StringUtils;



public class CreateInsertSqlStrategy extends CreateSqlStrategy{
	
	/**
	 * 组装单句sql
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
			placeholder.append("?,");//占位符
			valueList[i++] = paramValue.toString();//字段值
		}
		
		mainSql.deleteCharAt(mainSql.length()-1);
		mainSql.append(") values (");
		if(!StringUtils.isNull(placeholder.toString())){
			placeholder.deleteCharAt(placeholder.length() - 1);
		}
		mainSql.append(placeholder);
		mainSql.append(")");
		
		if(paramMap.size()<1){//没有要插入到数据库的字段
			mainSql = new StringBuilder();
		}
		map.put(mainSql.toString(), valueList);
		return map;
	}

	/**
	 * 组装批量sql(insert)
	 * @param tableName
	 * @param paramMap
	 * @return String[]
	 * */
	public String[] createSqlBatch(String tableName,
			                       Map<String, String[]> paramMap) {
		String[] sqlArr = null;
		//需要插入的所有字段。插入时需要先进数据删除然后插入，所以在paramMap中必须包含
		//删除数据的主键和主键值，在插入数据操作获取字段时，要将主键和主键值两个字段剔除
		String[] columns = new String[paramMap.size()];		
		StringBuilder columnSql = new StringBuilder();//字段sql
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
			if(!(StringUtil.isNull(pkString) || StringUtil.isNull(pkValue))){//按传入的主键删除		
				sqlArr = new String[count+1];
				//删除sql
				sqlArr[0] = StringUtils.joinStr("delete from ", 
						                        tableName, 
						                        " where ", 
						                        pkString, 
						                        "='", 
						                        pkValue, 
						                        "'");
				//批量insert语句
				for(int j=0; j<count; j++){
					StringBuilder mainSql = new StringBuilder("insert into ");//主体sql
					mainSql.append(tableName);
					mainSql.append("(");
					mainSql.append(columnSql);
					mainSql.append(") values (");
					StringBuilder valueSql = new StringBuilder();//字段值sql
					for(String column : columns){
						if(!StringUtil.isNull(column)){
							valueSql.append("'");
							valueSql.append(StringUtils.cleanString(paramMap.get(column)[j]));//过滤特殊字符
							valueSql.append("',");
						}
					}
					valueSql.deleteCharAt(valueSql.length()-1);
					mainSql.append(valueSql);
					mainSql.append(")");
					sqlArr[j+1] = mainSql.toString();
				}
			} else {//按表的主键删除				
				sqlArr = new String[count*2];
				String[] primaryKeys = primarykey.split(",");
				int num = 0;
				for(int j=0; j<count; j++){
					//删除sql
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
					//插入sql
					StringBuilder mainSql = new StringBuilder("insert into ");//主体sql
					mainSql.append(tableName);
					mainSql.append("(");
					mainSql.append(columnSql);
					mainSql.append(") values (");
					StringBuilder valueSql = new StringBuilder();//字段值sql
					for(String column : columns){
						if(!StringUtil.isNull(column)){
							valueSql.append("'");
							valueSql.append(StringUtils.cleanString(paramMap.get(column)[j]));//过滤特殊字符
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
	 * 组装批量sql(update)
	 * @param tableName
	 * @param paramMap
	 * @param primaryKeys
	 * @return String[]
	 * */
	public String[] createSqlBatch(String tableName,
			                       Map<String, String[]> paramMap, 
			                       String[] primaryKeys) {
		String[] sqlArr = null;
		String[] columns = new String[paramMap.size()];//插入的所有字段		
		int i = 0;
		//获取要修改的字段
		for(Map.Entry<String, String[]> entry : paramMap.entrySet()){
			String paramName = entry.getKey();	
			columns[i++] = paramName;
		}
		//组合sql
		if(columns.length>0 && !StringUtils.isNull(columns[0])){
			int count = paramMap.get(columns[0]).length;
			sqlArr = new String[count];
			for(int j=0; j<count; j++){
				StringBuilder mainSql = new StringBuilder("update ");//主体sql
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
