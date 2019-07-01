package xgxt.shgz.zgdzdx.hdfb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class HdfbService {

	HdfbDAO dao = new HdfbDAO();

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
	 * @describe 获得活动发布列表
	 */
	public ArrayList<String[]> getHdfbList(String tableName, HdfbModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 活动代码
		String hddm = model.getHddm();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(hddm) ? " and 1=1" : " and hddm='" + hddm
				+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(kssj) ? " and 1=1" : " and kssj>='" + kssj
				+ "'");
		query.append(Base.isNull(jssj) ? " and 1=1" : " and jssj<='" + jssj
				+ "'");

		String[] inPutList = new String[] {};

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe 保存活动发布
	 */
	public boolean saveHdfb(HdfbModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveHdfb(myModel, request);
	}

	/**
	 * @author luo
	 * @describe 获得活动发布
	 */
	public HashMap<String, String> getHdfb(String tableName, String[] colList,
			String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @author luo
	 * @describe 删除轰动发布
	 */
	public boolean delHdfb(String pk, HttpServletRequest request)
			throws Exception {
		return dao.delHdfb(pk, request);
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
