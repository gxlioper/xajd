package com.zfsoft.util;

import java.util.Map;

public abstract class CreateSqlStrategy {
	
	/**
	 * 根据传入的表名和参数生成相应的(insert/update/delete)sql语句
	 * @param tableName     要操作的表
	 * @param paramMap      从request中取出来的parameter map
	 * @return
	 */
	public abstract Map<String,Object> createSql(String tableName,
			   Map<String,String> paramMap);
	
	public abstract Map<String,Object> createSqlWhere(String[] primaryKeys,
			   Map<String,String> paramMap);
	
	public abstract String[] createSqlBatch(String tableName,
			   Map<String,String[]> paramMap);
	
	public abstract String[] createSqlBatch(String tableName, 			                                			
			                                Map<String,String[]> paramMap,
			                                String[] primaryKeys);
	
	public abstract String[] createSqlBatch(String tableName, 			                                			
            Map<String,String[]> paramMap,
            Map<String,String> valueMap);
	
	
}
