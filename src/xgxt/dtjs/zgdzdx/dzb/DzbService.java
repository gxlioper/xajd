package xgxt.dtjs.zgdzdx.dzb;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class DzbService {

	DzbDAO dao = new DzbDAO();

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
	 * @describe 获得党支部信息列表
	 */
	public ArrayList<String[]> getDzbList(String tableName, DzbModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		String[] inPutList = new String[] {};

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe 保存党支部信息
	 */
	public boolean saveDzb(DzbModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveDzb(myModel, request);
	}

	/**
	 * @author luo
	 * @describe 获得党支部信息
	 */
	public HashMap<String, String> getDzb(String tableName, String[] colList,
			String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @author luo
	 * @describe 删除党支部信息
	 */
	public boolean delDzb(String xydm, HttpServletRequest request)
			throws Exception {
		return dao.delDzb(xydm, request);
	}
}
