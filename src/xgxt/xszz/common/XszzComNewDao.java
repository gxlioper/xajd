/*
 * �������� 2007-11-21 zhoumi
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
	 * @describe �õ�������Ŀ�б�
	 */
	public synchronized List<HashMap<String, String>> getZzxmList() throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "select xmdm,xmmc from XSZZ_COMMON_NEW_ZZXMDMB order by xmdm";
		return dao.getList(sql, new String[] {}, new String[] { "xmdm",
				"xmmc" });
	}
	
	public synchronized String[] getViewComm(String viewName, String notColName) throws SQLException {
		// �õ���ͼ���ֶ�ע�����
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