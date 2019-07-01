package xgxt.shgz.zgdzdx.hdgl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class HdglService {

	HdglDAO dao = new HdglDAO();

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
	 * @throws Exception
	 * @describe 获得审核列表
	 */
	public ArrayList<String[]> getHdglList(String tableName, HdglModel model,
			String[] colList, String userType,String type) throws Exception {

		// 活动代码
		String hddm = model.getHddm();
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		// 学院代码
		String xydm = model.getXydm();
		// 专业代码
		String zydm = model.getZydm();
		// 班级代码
		String bjdm = model.getBjdm();

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");

		query.append(Base.isNull(hddm) ? " and 1=1" : " and hddm='" + hddm
				+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='" + xn + "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='" + xq + "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='" + xydm
				+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='" + zydm
				+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='" + bjdm
				+ "'");
		query.append(" and xh like ? and xm like ?");

		String pk = hddm + Base.currXn + Base.currXq;
		if ("yes".equalsIgnoreCase(needXy(pk,type))) {
			if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				query.append(" and xysh='已通过'");
			}
		}
		String[] inPutList = new String[] { xh, xm };

		return dao.commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * @author luo
	 * @describe 保存活动申请
	 */
	public boolean saveHdsq(HdglModel myModel, String[] zdyZd, String[] zdyZdz,
			HttpServletRequest request) throws Exception {
		return dao.saveHdsq(myModel, zdyZd, zdyZdz, request);
	}

	/**
	 * @author luo
	 * @describe 获得学生基本信息
	 */
	public HashMap<String, String> getXsjbxx(String tableName,
			String[] colList, String pk, String pkValue) throws Exception {
		return dao.commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 获取学相关信息
	 */
	public HashMap<String, String> getStuXxForXh(String xh) {
		return dao.commonQueryOne("view_xsxxb", new String[] { "xh", "xm" },
				"xh", xh);
	}

	/**
	 * 获取学年列表
	 */
	public List<HashMap<String, String>> getXnList() {
		DAO com_dao = new DAO();
		return com_dao.getXnndList();
	}

	/**
	 * @author luo
	 * @describe 批量处理审核
	 */
	public boolean saveHdsh(String[] key, String shlx, String type)
			throws Exception {
		return dao.saveHdsh(key, shlx, type);
	}

	/**
	 * @author luo
	 * @describe 单条记录审核
	 */
	public boolean saveHdsh(String pk, String userType, String shlx,
			HdglModel myModel, HttpServletRequest request) throws Exception {
		return dao.saveHdsh(pk, userType, shlx, myModel, request);
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

	/**
	 * @author luo
	 * @describe 获得活动列表
	 */
	public List<HashMap<String, String>> getHdList(String xn, String xq) {

		String tableName = "view_zgdd_hdfbb";
		String[] colList = new String[] { "hddm", "hdmc" };
		String[] inPutList = new String[] { xn, xq };
		String query = " where xn=? and xq=?";
		return dao.commonQueryforList(tableName, query, inPutList, colList, "");
	}

	/**
	 * @author luo
	 * @describe 获得自定义字段
	 */
	public List<HashMap<String, String>> getZdyZd(String hddm) {
		return dao.getZdyZd(hddm);
	}

	/**
	 * @author luo
	 * @describe 是否已经提交申请
	 */
	public boolean hadSq(HdglModel myModel) {
		return dao.hadSq(myModel);
	}

	/**
	 * @author luo
	 * @describe 是否需要学院审核
	 */
	public String needXy(String pk,String type) throws Exception {
		return dao.needXy(pk,type);
	}
}
