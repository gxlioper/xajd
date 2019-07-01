/**
 * @部门:学工产品事业部
 * @日期：2015-6-5 下午05:03:51 
 */  
package com.zfsoft.extend.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;

import com.zfsoft.extend.dao.ZDDataSourceDAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-5 下午05:03:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DBQueryDescriptor extends DataSourceQuery{
	
	private ZDDataSourceDAO zdDataSourceDAO = new ZDDataSourceDAO();
	
	@Deprecated
	private DAO dao = DAO.getInstance();
	
	/*查询的表或视图*/
	private String tableName;
	
	/*查询的字段*/
	private String[] fields;


	/*
	      描述: @see com.zfsoft.extend.service.DataSourceQuery#getData()
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
	 * @描述 ：TODO描述下当前构造方法
	 */
	public DBQueryDescriptor() {
		super();
	}


	/**
	 * @描述 ：TODO描述下当前构造方法
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
	 * @param tableName要设置的 tableName
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
	 * @param fields要设置的 fields
	 */
	public void setFields(String[] fields) {
		this.fields = fields;
	}

	
}
