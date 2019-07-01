package xgxt.dtjs.sjxy.zbgl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import xgxt.dtjs.sjxy.SjxyDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZbglDAO extends SjxyDtjsDAO {

	/**
	 * 获得支部管理相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getZbglList(String tableName, ZbglModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xydm" };
		String[] queryLikeList = new String[] {};
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得支部管理相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getZbglInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}
}
