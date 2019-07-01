/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-5 ����05:03:51 
 */  
package com.zfsoft.extend.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;

import com.zfsoft.extend.dao.ZDDataSourceDAO;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-5 ����05:03:51 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DBQueryDescriptor extends DataSourceQuery{
	
	private ZDDataSourceDAO zdDataSourceDAO = new ZDDataSourceDAO();
	
	@Deprecated
	private DAO dao = DAO.getInstance();
	
	/*��ѯ�ı����ͼ*/
	private String tableName;
	
	/*��ѯ���ֶ�*/
	private String[] fields;


	/*
	      ����: @see com.zfsoft.extend.service.DataSourceQuery#getData()
	 */
	
	@Override
	public List<HashMap<String, String>> getData() {
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		if(fields == null || fields.length == 0 ){
			return null;
		}
		return zdDataSourceDAO.obtainDataSource(this);
		/*String sqlStatement = getSQLStatement();
		List<HashMap<String, String>> listNotOut = null;
		if(StringUtils.isNotBlank(sqlStatement)){
			listNotOut = dao.getListNotOut(sqlStatement, new String[]{});
		}
		return listNotOut;*/
	}
	
	@Deprecated
	private String getSQLStatement(){
		if(StringUtils.isBlank(tableName)){
			return null;
		}
		if(fields == null || fields.length == 0 ){
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(fields[0]).append(" AS dm ,").append(fields[1]).append(" AS mc ");
		sb.append(" FROM ").append(tableName).append(" ORDER BY").append(fields[1]);
		return sb.toString();
	}
	
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public DBQueryDescriptor() {
		super();
	}


	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param tableName
	 * @param fields
	 */
	public DBQueryDescriptor(String tableName, String[] fields) {
		super();
		this.tableName = tableName;
		this.fields = fields;
	}


	/**
	 * @return the tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * @param tableNameҪ���õ� tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * @return the fields
	 */
	public String[] getFields() {
		return fields;
	}

	/**
	 * @param fieldsҪ���õ� fields
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	
}
