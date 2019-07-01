package xgxt.shgz;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ShgzTyDAO extends CommonQueryDAO {

	/**
	 * 获得社会工作相关信息(MAP)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getShgzInfoMap(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * 获得社会工作相关信息(数组)
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public String[] getShgzInfoArr(String tableName, String pk,
			String pkValue, String[] colList) {
		
		DAO dao = DAO.getInstance();
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" select * from ");
		sql.append(tableName);
		sql.append(" where ");
		sql.append(pk);
		sql.append(" = ?");
		
		String[] rs = dao.getOneRs(sql.toString(), new String[]{pkValue}, colList);
		
		return rs;
	}
}
