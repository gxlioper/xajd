/*
 * 创建日期 2007-11-21 zhoumi
 *
 */
package xgxt.xszz.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;

public class XszzComNewDao {
	
	/**
	 * @author ZhouMi
	 * @describe 得到资助项目列表
	 */
	public synchronized List<HashMap<String, String>> getZzxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select xmdm,xmmc from XSZZ_COMMON_NEW_ZZXMDMB order by xmdm";
		return dao.getList(sql, new String[] {}, new String[] { "xmdm",
				"xmmc" });
	}
	
	public synchronized String[] getViewComm(String viewName, String notColName) throws SQLException {
		// 得到视图的字段注释语句
		DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||chr(39) com "
				+ "from user_col_comments where table_name=upper('"
				+ viewName
				+ "')  and COLUMN_NAME<>upper('"
				+ notColName + "')";
		arr = dao.getArray(sql, new String[] {}, "com");
		return arr;
	}
}