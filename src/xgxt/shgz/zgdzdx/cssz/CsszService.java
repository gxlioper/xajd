package xgxt.shgz.zgdzdx.cssz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class CsszService {

	CsszDAO dao = new CsszDAO();

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
	 * @describe 获得参数设置列表
	 */
	public ArrayList<String[]> getCsszList(String tableName, CsszModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 活动代码
		String hddm = model.getHddm();
		// 学年
		String xn = model.getXn();

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(hddm) ? " and 1=1" : " and hddm='" + hddm
				+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");

		String[] inPutList = new String[] {};

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe 保存参数设置
	 */
	public boolean saveCssz(CsszModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveCssz(myModel, request);
	}

	/**
	 * @author luo
	 * @describe 获得参数设置
	 */
	public HashMap<String, String> getCssz(String tableName, String[] colList,
			String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @author luo
	 * @describe 删除参数设置
	 */
	public boolean delCssz(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delCssz(pk, request);
	}

	/**
	 * @author luo
	 * @describe 获得活动列表
	 */
	public List<HashMap<String, String>> getHdList() {

		String tableName = "zgdd_shgz_hdb";
		String[] colList = new String[] { "hddm", "hdmc" };
		String[] inPutList = new String[] {};

		return dao.commonQueryforList(tableName, "", inPutList, colList, "");
	}
}
