package xgxt.szdw.utils;

import java.util.HashMap;

public class GetColCommentForTableName {
	public static HashMap<String, String> GetColCommentForTableNameT(String tableName){
		//	    �������������һ����ϣmap���ֶ�����Ϊ�����ֶγ���Ϊ��ֵ�����ڹ���ҳ��
			GetHashMapDAO dao = new GetHashMapDAO();
			String sql = "select column_name,data_length from user_tab_cols where table_name = ? ";
			return dao.getHashMap(sql,tableName,"column_name","data_length");
	}
}
