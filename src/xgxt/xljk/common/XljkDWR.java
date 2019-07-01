package xgxt.xljk.common;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;
import xgxt.utils.sql.SQL_Util;

public class XljkDWR {

	private static final DAO dao = DAO.getInstance();

	private static final String[] nullArray = {};

	/**
	 * 判断咨询师编号已经存在
	 * @param pkValue
	 * @return
	 */
	public boolean isZxsbhExist(String pkValue) {
		String tableName = "xljk_zxsxxb2";
		String pkCol = "bh";
		String rs = dao.getOneRs(SQL_Util.getQuerySqlByPKValue(tableName,
				pkCol, pkValue), nullArray, pkCol);
		return StringUtils.isNull(rs) ? false : true;
	}
	
	/**
	 * 判断咨询资源是否已经存在
	 * @param pkValue
	 * @return
	 */
	public boolean isZxszyExist(String pkValue) {
		String tableName = "view_zxszy"; //心理健康咨询师资源视图
		String pkCol = "bh||xq||sjd";
		String rs = dao.getOneRs(SQL_Util.getQuerySqlByPKValue(tableName,
				pkCol, pkValue), nullArray, pkCol);
		return StringUtils.isNull(rs) ? false : true;
	}
	
	
}
