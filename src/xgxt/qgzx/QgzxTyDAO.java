package xgxt.qgzx;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class QgzxTyDAO extends CommonQueryDAO {
	
	/**
	 * 获得勤工助学相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getQgzxListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "lx", };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);
		
		String query = myQuery.getQueryString();
		
		other_query = Base.isNull(other_query) ? "" : other_query;
		
		if(!Base.isNull(query)){
			query += " "+other_query;
		}else{
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得勤工助学相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getQgzxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getQgzxList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {

		DAO dao = DAO.getInstance();

		String[] dm = null;
		String[] mc = null;

		if ("sflx".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否" };
			mc = new String[] { "是", "否" };
		} else if ("bblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "zhbnoyj", "zhbyj", "zhblxy", "jxjjetj",
					"jxjhj", "rychhj", "jxjjehz" };
			mc = new String[] { "综合测评表(不考虑英语、计算机等级)", "综合测评表(考虑英语、计算机等级)",
					Base.YXPZXY_KEY+"综合测评比例表", "奖学金金额统计", "奖学金获奖名单", "荣誉称号获奖名单", "奖学金金额汇总表" };
		} else if ("wlk".equalsIgnoreCase(lx)) {
			dm = new String[] { "文科", "理科", "艺术类" };
			mc = new String[] { "文科", "理科", "艺术类" };
		} else if ("tjlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", ">", ">=", "=", "<=", "<" };
			mc = new String[] { "-----请选择-----", ">", ">=", "=", "<=", "<" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "有", "无" };
			mc = new String[] { "-----请选择-----", "有", "无" };
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "开", "关" };
			mc = new String[] { "开", "关" };
		} else if ("jxjorrych".equalsIgnoreCase(lx)) {
			dm = new String[] { "jxj", "rych" };
			mc = new String[] { "奖学金", "荣誉称号" };
		}

		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk, String pkValue) {
		
		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * 获得班级人数
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getBjRs(String bjdm) {
		DAO dao = DAO.getInstance();
		String[] colList = new String[] { "bjdm", "bjmc", "num" };
		String sql = "select bjdm,bjmc,count(xh) num from view_xsjbxx where bjdm = ? group by bjdm,bjmc";
		HashMap<String, String> map = dao.getMap(sql, new String[] { bjdm },
				colList);
		return map;
	}
	
	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql)
			throws Exception {

		DAO dao = new DAO();
		boolean flag = true;
		int[] res = dao.runBatch(sql);

		for (int i = 0; i < res.length; i++) {
			flag = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * @param msg(显示信息)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getWhList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getWhList(tableName, dm, mc, msg, pk, pkValue);
	}
}
