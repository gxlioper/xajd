package xgxt.pjpy.jhzy.zhf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;

public class ZhfService {

	ZhfDAO dao = new ZhfDAO();

	/**
	 * @author luo
	 * @describe 获得表头
	 */
	public List<HashMap<String, String>> getTopTr(String tableName,
			String[] colList) {
		DAO dao = DAO.getInstance();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,
				colListCN);
		return topTr;
	}

	/**
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @describe 获得奖惩分列表
	 */
	public ArrayList<String[]> getZhfList(String tableName, ZhfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		return dao.getZhfList(tableName, model, colList);
	}

	/**
	 * 保存综合素质分上限
	 * 
	 * @throws SQLException
	 */
	public boolean saveZhfsx(ZhfModel model, HttpServletRequest request)
			throws Exception {
		return dao.saveZhfsx(model, request);
	}

	/**
	 * 获得综合素质分上限
	 * 
	 * @throws Exception
	 */
	public HashMap<String, String> getZhfsx() {
		return dao.getZhfsx();
	}

	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return dao.getXqList();
	}

	/**
	 * 计算综合分
	 * 
	 * @throws Exception
	 */
	public boolean jsZhf(String xn, String xq) throws Exception {
		return dao.jsZhf(xn, xq);
	}
}
