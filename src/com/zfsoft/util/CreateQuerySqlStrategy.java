package com.zfsoft.util;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import com.zfsoft.utils.StringUtil;

public class CreateQuerySqlStrategy extends CreateSqlStrategy{

	
	/**
	 * 根据主键值从视图中查询单条记录信息,返回的map中包含的pkValue,为主键值。
	 * @param tableName
	 * @param paramMap
	 * @return Map<String,Object>
	 * */
	@Override
	public Map<String,Object> createSql(String tableName, 
			                            Map<String, String> paramMap) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		StringBuilder querySql = new StringBuilder(" select a.*,");
		querySql.append(paramMap.get("pkString"));
		querySql.append(" pkValue from ");
		querySql.append(tableName);
		querySql.append(" a where ");
		querySql.append(paramMap.get("pkString"));
		querySql.append(" ='");
		querySql.append(paramMap.get("pkValue"));
		querySql.append("'");
		rsMap.put(querySql.toString(), null);
		return rsMap;
	}
	
	/**
	 * 根据paramMap中传输的条件查询
	 * @param tableName
	 * @param paramMap
	 * @param pagesMap
	 * @param pagination
	 * @return Map<String,Object>
	 * */	
	public Map<String,Object> createQuerySql(String tableName,
			                                Map<String, Object> paramMap, 
			                                Map<String, String> pagesMap,
			                                boolean pagination){
		Map<String, Object> rsMap = new HashMap<String, Object>();
		StringBuilder whereSql = new StringBuilder(" where 1=1 ");
		String[] paramValue = new String[paramMap.size()];
		int i=0;
		for(Object entry : paramMap.keySet()){
			String param = entry.toString();			
			String value = paramMap.get(param).getClass().isArray() 
			               ? (String) Array.get(paramMap.get(param), 0) 
			               : paramMap.get(param).toString();
			if(param.length()>12 
			   && "queryequals_".equalsIgnoreCase(param.substring(0,12))){//equals
				if(!StringUtil.isNull(value)){
					whereSql.append(" and ");
					whereSql.append(param.substring(12,param.length()));
					whereSql.append("=?");
					paramValue[i++] = value;
				}
			}else if(param.length()>15
					&& "querynotequals_".equalsIgnoreCase(param.substring(0,15))){//notequals
				if(!StringUtil.isNull(value)){
					whereSql.append(" and ");
					whereSql.append(param.substring(15,param.length()));
					whereSql.append("<>?");
					paramValue[i++] = value;
				}
			}else if(param.length()>10 
					 && "querylike_".equalsIgnoreCase(param.substring(0,10))){//like
				if(!StringUtil.isNull(value)){
					whereSql.append(" and ");
					whereSql.append(param.substring(10,param.length()));
					whereSql.append(" like '%'||?||'%'");
					paramValue[i++] = value;
				}
			}else if(param.length()>18 
					 && "querygreaterequal_".equalsIgnoreCase(param.substring(0,18))){//greaterEqual
				if(!StringUtil.isNull(value)){
					whereSql.append(" and to_number(");
					whereSql.append(param.substring(18,param.length()));
					whereSql.append(") >= ? ");
					paramValue[i++] = value;
				}
			}else if(param.length()>17 
					 && "querygreaterthan_".equalsIgnoreCase(param.substring(0,17))){//greaterThan
				if(!StringUtil.isNull(value)){
					whereSql.append(" and to_number(");
					whereSql.append(param.substring(17,param.length()));
					whereSql.append(") > ? ");
					paramValue[i++] = value;
				}
			}else if(param.length()>15 
					 && "querylessequal_".equalsIgnoreCase(param.substring(0,15))){//lessEqual
				if(!StringUtil.isNull(value)){
					whereSql.append(" and to_number(");
					whereSql.append(param.substring(15,param.length()));
					whereSql.append(") <= ? ");
					paramValue[i++] = value;
				}
			}else if(param.length()>14 
					 && "querylessthan_".equalsIgnoreCase(param.substring(0,14))){//lessThan
				if(!StringUtil.isNull(value)){
					whereSql.append(" and to_number(");
					whereSql.append(param.substring(14,param.length()));
					whereSql.append(") < ? ");
					paramValue[i++] = value;
				}
			}else if(param.length()>4 && "isFdy".equalsIgnoreCase(param)){
				if(!StringUtil.isNull(value) 
						&& "true".equalsIgnoreCase(value)){
					whereSql.append(" and exists(select 1 from fdybjb b where a.bjdm=b.bjdm and b.zgh=?) ");
					paramValue[i++] = paramMap.get("userName").getClass().isArray() 
		               ? (String) Array.get(paramMap.get("userName"), 0) 
				               : paramMap.get("userName").toString();
				}
			}
			
		}
		StringBuilder querySql = new StringBuilder("");
		//客户端查询的特殊字段
		String clientColumns = paramMap.get("clientColumns") != null ? paramMap.get("clientColumns").toString() : "";
		//客户端查询的附加条件
		String annexTerm = paramMap.get("annexTerm") != null ? paramMap.get("annexTerm").toString() : "";
	    whereSql.append(annexTerm);//自定义的查询条件
	    
		if(pagination){
			querySql.append("select * from (select rownum r,rownum 行号,");
			querySql.append(clientColumns);
			querySql.append(" a.* ");
			if(!StringUtil.isNull(pagesMap.get("primarykey"))){
				querySql.append(",");
				querySql.append(pagesMap.get("primarykey"));
				querySql.append(" pkValue");
			}
			querySql.append(" from ");
			querySql.append(tableName);
			querySql.append(" a ");
			querySql.append(whereSql);
			querySql.append(") where r>");
			querySql.append(Integer.parseInt(pagesMap.get("start")));
			querySql.append(" and r<=");
			querySql.append(Integer.parseInt(pagesMap.get("start")) 
					        + Integer.parseInt(pagesMap.get("pagesize")));
		}else{
			querySql.append("select rownum r,rownum 行号,");
			querySql.append(clientColumns);
			querySql.append(" a.* ");
			if(!StringUtil.isNull(pagesMap.get("primarykey"))){
				querySql.append(",");
				querySql.append(pagesMap.get("primarykey"));
				querySql.append(" pkValue");
			}
			querySql.append(" from ");
			querySql.append(tableName);
			querySql.append(" a ");
			querySql.append(whereSql);
		}
		
		
		String[] value = new String[i];
		for(int j=0; j<i; j++){
			value[j] = paramValue[j];
		}
		rsMap.put("sql", querySql.toString());
		rsMap.put("countSql", "select count(*) num from " + tableName + " a " + whereSql);
		rsMap.put("value", value);
		return rsMap;		
	}

	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap) {
		// TODO 自动生成方法存根
		return null;
	}
	
	@Override
	public String[] createSqlBatch(String tableName,
			Map<String, String[]> paramMap, String[] primaryKeys) {
		return null;
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
