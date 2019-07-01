package com.zfsoft.util;

import java.util.Map;

public abstract class CreateSqlStrategy {
	
	/**
	 * ���ݴ���ı����Ͳ���������Ӧ��(insert/update/delete)sql���
	 * @param tableName     Ҫ�����ı�
	 * @param paramMap      ��request��ȡ������parameter map
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
