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
	 * �����Ṥ�������Ϣ(MAP)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getShgzInfoMap(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
	
	/**
	 * �����Ṥ�������Ϣ(����)
	 * 
	 * @param tableName(����)
	 * @param pk(����)
	 * @param pkValue(����ֵ)
	 * @param colList(���ֵ)
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
