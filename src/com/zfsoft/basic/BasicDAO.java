package com.zfsoft.basic;

import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import xgxt.DAO.DAO;

public class BasicDAO extends DAO {


	public BasicDAO(){
	}
	
	public BasicDAO(DataSource db){
		super(db);
	}
	
	/**
	 * ��ѯ����ֶ���Ϣ
	 * @param tableName
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectTableInfo(String tableName){
		String sql = "SELECT a.TABLE_NAME tableName,"
                     + "a.TABLECOMMENTS tableComments,"
                     + "a.COLUMN_NAME name,"
                     + "a.COMMENTS comments,"
                     + "a.ISUNIQUEINDEX uniqueIndex,"
                     + "a.INDEX_COLUMNS,"
                     + "a.DATA_TYPE type,"
                     + "a.DATA_LENGTH length,"
                     + "a.NULLABLE nullable,"
                     + "a.ISPRIMARYKEY primaryKey,"
                     + "a.DATA_DEFAULT defaultValue " 
                     + " FROM " 
                     + " view_tableinfo a"
                     + " WHERE a.table_name=upper(?)";
		return getList(sql, 
				       new String[]{tableName}, 
				       new String[]{"name","comments","defaultValue","nullable",
				                    "primaryKey","tableName","tableComments",
				                    "uniqueIndex","length"});
	}
	
	/**
	 * ��������Ƿ����
	 * @param tableName
	 * @param pk 
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		return Integer.parseInt(getOneRs(sql, new String[]{pkValue}, "num"))>0 ? true : false;
	}
}
