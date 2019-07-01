package xgxt.szdw.utils;

import java.util.HashMap;

public class GetColCommentForTableName {
	public static HashMap<String, String> GetColCommentForTableNameT(String tableName){
		//	    传入表名，返回一个哈希map以字段名称为键，字段长度为键值，用于构建页面
			GetHashMapDAO dao = new GetHashMapDAO();
			String sql = "select column_name,data_length from user_tab_cols where table_name = ? ";
			return dao.getHashMap(sql,tableName,"column_name","data_length");
	}
}
