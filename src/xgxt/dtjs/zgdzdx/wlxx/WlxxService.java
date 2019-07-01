package xgxt.dtjs.zgdzdx.wlxx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class WlxxService {

	WlxxDAO dao = new WlxxDAO();

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
	 * @describe 获得网络信息列表
	 */
	public ArrayList<String[]> getWlxxList(String tableName, WlxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 值班时间
		String zbsj = DealString.toGBK(model.getZbsj());

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='" + nj + "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(Base.isNull(zbsj) ? " and 1=1" : " and kssj <'" + zbsj
				+ "' and jssj > '" + zbsj + "'");
		query.append(" and xh like ? and xm like ?");

		String[] inPutList = new String[] { xh, xm };

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe 保存网络信息
	 */
	public boolean saveWlxx(WlxxModel myModel, HttpServletRequest request)
			throws Exception {
		return dao.saveWlxx(myModel, request);
	}

	/**
	 * @author luo
	 * @describe 获得网络信息
	 */
	public HashMap<String, String> getWlxx(String tableName, String[] colList,
			String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * @author luo
	 * @describe 删除网络信息
	 */
	public boolean delWlxx(String xh, HttpServletRequest request)
			throws Exception {
		return dao.delWlxx(xh, request);
	}
}
