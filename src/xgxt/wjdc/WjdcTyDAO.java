package xgxt.wjdc;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class WjdcTyDAO extends CommonQueryDAO {

	/**
	 * 获得问卷调查相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getWjdcListInfo(String tableName, Object model,
			String[] colList, String other_query)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", };

		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();

		myQuery.makeQuery(queryList, queryLikeList, model);

		String query = myQuery.getQueryString();

		other_query = Base.isNull(other_query) ? "" : other_query;

		if (!Base.isNull(query)) {
			query += " " + other_query;
		} else {
			query = other_query;
		}

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得问卷调查相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getWjdcInfo(String tableName, String pk,
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
	public List<HashMap<String, String>> getWjdcList(String tableName,
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
		} else if ("kglx".equalsIgnoreCase(lx)) {
			dm = new String[] { "开", "关" };
			mc = new String[] { "开", "关" };
		} else if ("over".equalsIgnoreCase(lx)) {
			dm = new String[] { "未做", "已做" };
			mc = new String[] { "未做", "已做" };
		} else if ("zz".equalsIgnoreCase(lx)) {
			dm = new String[] { "已组卷", "未组卷" };
			mc = new String[] { "已组卷", "未组卷" };
		} else if ("xblx".equalsIgnoreCase(lx)) {
			dm = new String[] { "男", "女" };
			mc = new String[] { "男", "女" };
		} else if ("fplx".equalsIgnoreCase(lx)) {
			dm = new String[] { "未分配", "已分配" };
			mc = new String[] { "未分配", "已分配" };
		} else if ("ywlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "", "有", "无" };
			mc = new String[] { "-----请选择-----", "有", "无" };
		} else if ("mklx".equalsIgnoreCase(lx)) {
			dm = new String[] { GlobalsVariable.WJDC_XLPC,
					GlobalsVariable.WJDC_SXZK ,GlobalsVariable.WJDC_SXSZK};
			mc = new String[] { "心理普查", "学生思想状况调查","实习生状况调查" };
		} 

		return dao.arrayToList(dm, mc);
	}

	/**
	 * 获得指定表的指定字段
	 * 
	 * @author luojw
	 */
	public String getOneValue(String tableName, String dm, String pk,
			String pkValue) {

		DAO dao = DAO.getInstance();

		return dao.getOneValue(tableName, dm, pk, pkValue);
	}

	/**
	 * 批量提交
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveArrDate(String[] sql) throws Exception {

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
	 * 获得问卷调查条件列表
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getTjList() {

		DAO dao = DAO.getInstance();

		String sql = "select zdmc dm, zdsm mc from jxjtjzdb order by lsh";

		return dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

}
