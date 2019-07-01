package xgxt.dtjs.zjcm.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zjcm.dao.DtjszjcmDAO;
import xgxt.dtjs.zjcm.form.DtjszjcmForm;
import xgxt.dtjs.zjcm.model.DtjszjcmModel;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class DtjszjcmService {

	DtjszjcmDAO zjcmDao = new DtjszjcmDAO();

	/**
	 * @describe 获得事务处理表头
	 * @author luo
	 */
	public List getSwclTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_swcl";
		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "sssj", "shlx" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		return topTr;
	}

	/**
	 * @describe 事务处理查询
	 * @author luo
	 */
	public ArrayList<String[]> getSwclList(DtjszjcmModel myModel,
			DtjszjcmForm dataSearchForm) {

		// 事务处理查询
		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String sssj = DealString.toGBK(myModel.getSssj());
		String shlx = DealString.toGBK(myModel.getShlx());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));

		if (sssj != null && !("".equalsIgnoreCase(sssj.trim()))) {
			query.append(" and sssj='");
			query.append(sssj);
			query.append("' ");
		}
		if (shlx != null && !("".equalsIgnoreCase(shlx.trim()))) {
			query.append(" and shlx='");
			query.append(shlx);
			query.append("' ");
		}

		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "sssj", "shlx" };

		String sql = "select * from (select * from (select distinct pk,rownum r, xymc,zymc,bjmc,xh,xm,nj,sssj,"
				+ "(decode(shlx, 'wsh', '未审核') || decode(shlx, 'tg', '通过') ||decode(shlx, 'wtg', '未通过')) as shlx from view_swcl "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_swcl " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 将事务处理信息从数据库中删除
	 * @author luo
	 */
	public boolean delSwcl(String pk, HttpServletRequest request)
			throws Exception {

		String tableName = "swcl";
		String pkComment = "xh";

		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);

		return del;
	}

	/**
	 * @describe 添加申请记录
	 * @author luo
	 * @throws Exception
	 */
	public boolean updataSwcl(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		String tableName = "swcl";
		String pkComment = "xh";
		String[] colList = new String[] { "xh", "sssj", "cllx", "shlx", "bz" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}

		return inserted;
	}

	/**
	 * @describe 得到欲申请者详细信息
	 * @author luo
	 */
	public HashMap<String, String> getSwcl(String xh) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "xh", "nj", "xm", "xydm", "zydm",
				"bjdm", "xb" };

		String sql = "select xh,xydm,zydm,bjdm,xm,nj,xb from view_xsjbxx where xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, xh, map, sql);
		return rs;
	}

	/**
	 * @describe 获取材料类型列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getClList() {
		// 获取材料类型列表
		return zjcmDao.getClList();
	}

	/**
	 * @describe 得到欲审核者详细信息
	 * @author luo
	 */
	public HashMap<String, String> getSwclSh(String xh) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "pk", "xh", "nj", "xm", "xydm",
				"zydm", "bjdm", "xb", "cllx", "bz", "shlx", "sssj" };

		String sql = "select * from (select * from (select distinct pk, xh,xydm,zydm,bjdm,xm,nj,xb,cllx,bz,shlx,sssj"
				+ " from view_swcl where xh = ? ))";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, xh, map, sql);
		return rs;
	}

	/**
	 * @describe 得到欲组织关系接转者详细信息
	 * @author luo
	 */
	public HashMap<String, String> getZzgxxx(String xh) {

		// 得到欲组织关系接转者详细信息
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "xh", "nj", "xm", "xydm", "zydm",
				"bjdm", "xb" };

		String sql = "select xh,xydm,zydm,bjdm,xm,nj,xb from view_xsjbxx where xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, xh, map, sql);
		return rs;
	}

	/**
	 * @describe 得到组织关系接转信息
	 * @author luo
	 */
	public HashMap<String, String> getZzgxjz(String xh) {

		// 得到组织关系接转信息
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "xh", "nj", "xm", "xydm", "zydm",
				"bjdm", "xb", "jrlx", "jrsj", "gxzw", "zwsj" };

		String sql = "select xh,xydm,zydm,bjdm,xm,nj,xb,jrlx,jrsj,gxzw,zwsj from view_zzgx where xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, xh, map, sql);
		return rs;
	}

	/**
	 * @describe 添加申请记录
	 * @author luo
	 * @throws Exception
	 */
	public boolean updataZzgx(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		String tableName = "zzgx";
		String pkComment = "xh";
		String[] colList = new String[] { "xh", "jrlx", "jrsj", "gxzw", "zwsj" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}

		return inserted;
	}

	/**
	 * @describe 将信息添加到入党积极分子表
	 * @author luo
	 * @throws Exception
	 */
	public boolean addRdjjfz(String xn, String xq, String xh, String nd,
			String djsqsj, String pkValue, String pk, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		String realTable = "rdjjfzxxb";
		String sql = "select count(*) from rdjjfzxxb where xh = '" + xh + "'";
		String kssj = djsqsj;
		int count = dao.getOneRsint(sql);
		boolean del = false;

		if (count == 0) {
			sql = "insert into "
					+ realTable
					+ "(XN,XQ,XH,KSSJ,BZ,ND,TJDW,LXR1,LXR2,RZQK,PYSJ,PYCS,THCS,SFPYDY,ZBQDSJ,XSCCDM,SSBX1,SSBX2,SSBX3,SSBX4)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			del = dao.runUpdate(sql, new String[] { xn, xq, xh, kssj, "", nd,
					"", "", "", "", "", "", "", "", "", "", "", "", "", "" });
		} else {
			String[] columns = new String[] { "kssj" };
			String[] values = new String[] { kssj };
			String primaryKey = "xn||xq||xh";
			pk = xn + xq + xh;
			del = StandardOperation.update(realTable, columns, values,
					primaryKey, pk, request);
		}

		return del;
	}

	// /**
	// * @describe 查询是否党员
	// * @author luo
	// * @throws Exception
	// */
	// public boolean isDy(String xn, String xq, String xh)
	// throws Exception {
	//
	// DAO dao = DAO.getInstance();
	//
	// String sql = "select count(*) from dyxxb where xn||xq||xh = '" + xh +
	// "'";
	//
	// int count = dao.getOneRsint(sql);
	//
	// if (count == 0) {
	// return true;
	// }
	//
	// return false;
	// }

	/**
	 * @describe 将信息添加到发展对象表
	 * @author luo
	 * @throws Exception
	 */
	public boolean addFzdx(String nd, String xn, String xq, String xh,
			String pkValue, String bz, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();
		String realTable = "fzdx";
		String sql = "select count(*) from fzdx where xn||xq||xh = '" + xn + xq
				+ xh + "'";
		int count = dao.getOneRsint(sql);
		boolean inserted = false;

		if (count == 0) {
			if ((pkValue != null) || !"".equals(pkValue)) {
				sql = "insert into " + realTable + "(xn,xq,nd,xh,kssj,jssj,bz)"
						+ "values(?,?,?,?,?,?,?)";
				inserted = dao.runUpdate(sql, new String[] { xn, xq, nd, xh,
						pkValue, "", bz });
			}
		} else {
			String[] columns = new String[] { "kssj", "bz" };
			String[] values = new String[] { pkValue, bz };
			String primaryKey = "xn||xq||xh";
			String pk = xn + xq + xh;
			inserted = StandardOperation.update(realTable, columns, values,
					primaryKey, pk, request);
		}

		return inserted;
	}

	/**
	 * @describe 取得发展对象表头
	 * @author luo
	 */
	public List getFzdxTopTr() {

		DAO dao = DAO.getInstance();
		String tableName = "view_fzdx";
		String[] colList = new String[] { "pk", "nd", "xn", "xq", "xh", "xm",
				"nj", "bjmc", "kssj", "jssj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	/**
	 * @describe 取得发展对象列表
	 * @author luo
	 */
	public ArrayList<String[]> getFzdxList(DtjszjcmModel myModel,
			DtjszjcmForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());
		String kssj = DealString.toGBK(myModel.getKssj());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, nd, xq, xn));

		if (kssj != null && !"".equals(kssj)) {
			query.append("and kssj = " + kssj);
		}
		String[] colList = new String[] { "pk", "nd", "xn", "xq", "xh", "xm",
				"nj", "bjmc", "kssj", "jssj" };

		String sql = "select * from (select * from (select nd||'&'||xn||'&'||xq||'&'||xh||'&'||bz pk,rownum r,nd,xn,xq,xh,xm,nj,bjmc,kssj,jssj from view_fzdx "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_fzdx " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 得到发展对象详细信息
	 * @author luo
	 */
	public HashMap<String, String> getFzdxOne(String pk) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "pk", "xh", "nd", "xm", "xn", "xb",
				"xq", "nj", "kssj", "xymc", "jssj", "zymc", "bjmc", "bz",
				"xsccdm" };

		String sql = "select distinct pk, xh, nd, xm, xn, xb,xq, nj, kssj, xymc, "
				+ "jssj, zymc, bjmc, bz ,xsccdm from view_fzdx where xn||xq||xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, pk, map, sql);
		return rs;
	}

	/**
	 * @describe 得到学校层次列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getXxccList() {

		DAO dao = DAO.getInstance();

		String mySql = "select xsccdm, xsccmc from dtjs_xsccb";
		return dao.getList(mySql, new String[] {}, new String[] { "xsccdm",
				"xsccmc" });
	}

	/**
	 * @describe 将发展对象信息保存到数据库
	 * @author luo
	 */
	public boolean updataFzdx(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		String tableName = "fzdx";
		String pkComment = "xh";
		String[] colList = new String[] { "xn", "xq", "nd", "xh", "kssj",
				"jssj", "bz" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}

		return inserted;
	}

	/**
	 * @describe 将发展对象信息从数据库中删除
	 * @author luo
	 */
	public boolean delFzdx(String pk, HttpServletRequest request)
			throws Exception {

		String tableName = "fzdx";
		String pkComment = "xn||xq||xh";

		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);

		return del;
	}

	/**
	 * @describe 预备党员转正
	 * @author luo
	 */
	public boolean getYbdyOne(String pk, HttpServletRequest request)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DtjszjcmModel myModel = new DtjszjcmModel();
		String[] colList = new String[] { "kssj", "jssj", "nd", "xn", "xq",
				"xh" };

		String nd = "";
		String xn = "";
		String xq = "";
		String xh = "";
		boolean insert = false;

		String sql = "select nd,xn,xq,xh,kssj,jssj from view_ybdyxx where xn||xq||xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, pk, map, sql);

		nd = rs.get("nd");
		xn = rs.get("xn");
		xq = rs.get("xq");
		xh = rs.get("xh");

		String pkComment = "xn||xq||xh";
		String[] addList = new String[] { "nd", "xn", "xq", "xh", "rdsj", "bz",
				"cjhdqk", "zbshqk", "dfjnqk", "zzxxqk", "gxzcqx", "xsccdm",
				"zzsj", "ssbx1", "ssbx2", "ssbx3", "ssbx4" };

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		myModel.setNd(nd);
		myModel.setXn(xn);
		myModel.setXq(xq);
		myModel.setXh(xh);
		myModel.setRdsj(time);
		myModel.setZzsj(time);

		String[] inputList = ModelToStrings.modelToStrings(addList, myModel);
		boolean delDy = StandardOperation.delete("dyxxb", pkComment, pk,
				request);
		boolean delYb = StandardOperation.delete("ybdyxxb", pkComment, pk,
				request);
		if (delDy && delYb) {
			insert = StandardOperation.insert("dyxxb", addList, inputList,
					request);
		}
		// }
		return insert;
	}

	/**
	 * @describe 将党员信息保存到数据库
	 * @author luo
	 */
	public boolean updataDy(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		String tableName = "dyxxb";
		String pkComment = "xn||xq||xh";
		String[] colList = new String[] { "xh", "jssj" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}

		return inserted;
	}

	/**
	 * @describe 将预备党员的开始时间保存到数据库
	 * @author luo
	 * @throws Exception
	 */
	public boolean updataYbdy(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();

		String tableName = "ybdyxxb";
		String[] colList = new String[] { "xh" };
		boolean inserted = false;

		String sql = "select xh from ybdyxxb where xh = ?";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, pk, map, sql);

		String xh = rs.get("xh");

		if (xh == null || "".equals(xh)) {
			String[] ybList = new String[] { "xn", "xq", "xh", "kssj", "jssj",
					"bz", "nd", "rzqk", "lxr1", "lxr2", "dfjnqk", "thcs",
					"cjzzxxqk", "sfkyzz", "kcqk", "xsccdm", "ssbx1", "ssbx2",
					"ssbx3", "ssbx4" };
			String[] inputList = ModelToStrings.modelToStrings(ybList, myModel);
			inserted = StandardOperation.insert(tableName, ybList, inputList,
					request);
		} else {

			String[] columns = new String[] { "kssj", "jssj" };
			String[] values = new String[] { myModel.getKssj(),
					myModel.getJssj() };
			String primaryKey = "xh";
			String pkValue = xh;

			inserted = StandardOperation.update(tableName, columns, values,
					primaryKey, pkValue, request);
		}

		return inserted;
	}

	/**
	 * @describe 判断是否预备党员
	 * @author luo
	 * @throws Exception
	 */
	public boolean isYbdy(String pk) throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select count(*) as count from ybdyxxb where xn||xq||xh = ?";
		String count = dao.getOneRs(sql, new String[] { pk }, "count");

		if ("0".equals(count)) {
			return false;
		}
		return true;
	}

	/**
	 * @describe 取得预备党员转正表头
	 * @author luo
	 */
	public List getZzybTopTr() {

		DAO dao = DAO.getInstance();
		String tableName = "view_ybdyxx";
		String[] colList = new String[] { "pk", "nd", "xn", "xq", "xh", "xm",
				"nj", "bjmc", "kssj", "jssj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	/**
	 * @describe 取得入党积极分子表头
	 * @author luo
	 */
	public List getRdjjfzTopTr() {

		DAO dao = DAO.getInstance();
		String tableName = "view_rdjjfzxx";
		String[] colList = new String[] { "pk", "nd", "xn", "xq", "xh", "xm",
				"nj", "bjmc", "kssj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	/**
	 * @describe 取得入党积极分子列表
	 * @author luo
	 */
	public ArrayList<String[]> getRdjjfzList(DtjszjcmModel myModel,
			DtjszjcmForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, nd, xq, xn));

		String[] colList = new String[] {
				"nd||'&'||xn||'&'||xq||'&'||xh||'&'||bz", "nd", "xn", "xq",
				"xh", "xm", "nj", "bjmc", "kssj" };

		String sql = "select * from (select * from (select rownum r,nd||'&'||xn||'&'||xq||'&'||xh||'&'||bz"
				+ ",nd,xn,xq,xh,xm,nj,bjmc,kssj from view_rdjjfzxx "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_rdjjfzxx " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 发展对象设置
	 * @author luo
	 */
	public boolean setFzdx(String pk, String kssj, String jssj, String[] keys,
			HttpServletRequest request) throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		DtjszjcmModel myModel = new DtjszjcmModel();
		String[] colList = new String[] { "nd", "xn", "xq", "xh", "bz", "kssj" };
		String sql = "";
		boolean insert = false;

		if (jssj == null || "".equals(jssj)) {
			sql = "select nd,xn,xq,xh,bz from view_rdjjfzxx where xn||xq||xh = ? ";
		}
		if (kssj == null || "".equals(kssj)) {
			sql = "select nd,xn,xq,xh,bz,kssj from view_fzdx where xn||xq||xh = ? ";
		}
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, pk, map, sql);

		String nd = rs.get("nd");
		String xn = rs.get("xn");
		String xq = rs.get("xq");
		String xh = rs.get("xh");
		String bz = rs.get("bz");

		String pkComment = "xn||xq||xh";
		String[] addList = new String[] { "nd", "xn", "xq", "xh", "kssj", "bz",
				"jssj" };

		myModel.setNd(nd);
		myModel.setXn(xn);
		myModel.setXq(xq);
		myModel.setXh(xh);
		myModel.setBz(bz);
		if (jssj == null || "".equals(jssj)) {
			myModel.setKssj(kssj);
		}
		if (kssj == null || "".equals(kssj)) {
			myModel.setJssj(jssj);
			myModel.setKssj(rs.get("kssj"));
			if (Integer.parseInt(jssj) < Integer.parseInt(rs.get("kssj"))) {

			}
		}
		String[] inputList = ModelToStrings.modelToStrings(addList, myModel);

		boolean del =
		// zjcmDao.delFzdx(keys);
		StandardOperation.delete("fzdx", pkComment, pk, request);
		if (del) {
			insert = StandardOperation.insert("fzdx", addList, inputList,
					request);
		}

		if (kssj == null || "".equals(kssj)) {
			// 预备党员的预备期为一年
			int tempJssj = Integer.parseInt(jssj.substring(0, 4)) + 1;
			jssj = String.valueOf(tempJssj) + jssj.substring(4, 8);
			myModel.setJssj(jssj);
			updataYbdy(myModel, xh, request);
		}

		return insert;
	}

	/**
	 * 预备党员批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delYbdy(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from ybdyxxb where xn||xq||xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条预备党员数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 党员批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delDy(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from dyxxb where xn||xq||xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] == -1) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条党员数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 党员信息批量添加
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String addDy(String[] keys, String zzsj) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "kssj", "jssj", "nd", "xn", "xq",
				"xh", "bz" };

		String nd = "";
		String xn = "";
		String xq = "";
		String xh = "";
		String bz = "";
		String jssj = "";
		String rdsj = "";
		String sql = "";
		String pk = "";
		String whichpk = "";

		for (int i = 0; i < keys.length; i++) {
			pk = keys[i];
			sql = "select nd,xn,xq,xh,kssj,jssj,bz from view_ybdyxx where xn||xq||xh = ? ";
			HashMap<String, String> rs = zjcmDao.swclQueryOne(colList, pk, map,
					sql);

			// SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
			// String time = f.format(new Date());

			nd = rs.get("nd");
			xn = rs.get("xn");
			xq = rs.get("xq");
			xh = rs.get("xh");
			bz = rs.get("bz");
			jssj = rs.get("jssj");
			rdsj = zzsj;

			if (jssj != null && !"".equals(jssj)
					&& Integer.parseInt(jssj) > Integer.parseInt(zzsj)) {
				whichpk = whichpk + " 第" + String.valueOf(i + 1)
						+ "条数据转正小于预备党员结束时间,请重新设置;\n";
				return whichpk;
			}
			sql = "insert into dyxxb (xn,xq,nd,xh,rdsj,zzsj,bz,rzqk,cjhdqk,zbshqk,dfjnqk,zzxxqk,"
					+ "gxzcqx,xsccdm,ssbx1,ssbx2,ssbx3,ssbx4)"
					+ "values('"
					+ xn
					+ "','"
					+ xq
					+ "','"
					+ nd
					+ "','"
					+ xh
					+ "','"
					+ rdsj
					+ "','"
					+ zzsj
					+ "','"
					+ bz
					+ "','','','','','','','','','','','')";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		whichpk = whichpk + delDy(keys);
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);

		// 检查哪一条修改失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] != 1) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条党员添加失败;\n";
			}// end if
		}// end for
		whichpk = whichpk + delYbdy(keys);
		return whichpk;
	}

	/**
	 * 发展对象批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delFzdx(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from fzdx where xn||xq||xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 事务处理批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delSwcl(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from swcl where xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 发展对象批量修改
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String updateFzdx(String[] keys, String jssj) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> map = new HashMap<String, String>();
		String whichpk = "";
		String[] colList = new String[] { "kssj" };
		String sql = "";
		String kssj = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "select kssj from view_fzdx where xn||xq||xh = ?";
			rs = zjcmDao.swclQueryOne(colList, pk, map, sql);
			kssj = rs.get("kssj");
			if (kssj != null && !"".equals(kssj)
					&& Integer.parseInt(kssj) > Integer.parseInt(jssj)) {
				whichpk = whichpk + " 第" + String.valueOf(i + 1)
						+ "条数据结束时间小于开始时间,请重新设置;\n";
				return whichpk;
			}
			sql = "update fzdx set jssj = '" + jssj + "' where xn||xq||xh = '"
					+ pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);

		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] != 1) {
				whichpk = whichpk + " 第" + String.valueOf(i + 1)
						+ "条数据修改发展对象失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 发展对象批量添加
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String addFzdx(String[] keys, String kssj) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";

		for (int i = 0; i < keys.length; i++) {

			String[] pk = keys[i].split("&");
			String nd = pk[0];
			String xn = pk[1];
			String xq = pk[2];
			String xh = pk[3];
			String bz = "";
			String jssj = "";

			if (pk.length > 4) {
				bz = pk[4];
			}

			sql = "insert into fzdx (xn,xq,nd,xh,kssj,jssj,bz)" + "values('"
					+ xn + "','" + xq + "','" + nd + "','" + xh + "','" + kssj
					+ "','" + jssj + "','" + bz + "')";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条修改失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] != 1) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据修改失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 预备党员批量修改
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String updateYbdy(String[] keys, String jssj, int num)
			throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		String kssj = jssj;
		int tempSj = 0;
		// 预备党员的预备期为一年

		if (jssj != null || !"".equals(jssj)) {
			tempSj = Integer.parseInt(jssj.substring(0, 4)) + 1;
			jssj = (String.valueOf(tempSj) + jssj.substring(4, 8));
		}

		for (int i = 0; i < num; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "update ybdyxxb set kssj = '" + kssj + "',jssj = '" + jssj
					+ "' where xn||xq||xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条修改失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] != 1) {
				whichpk = whichpk + " 第" + String.valueOf(i + 1)
						+ "条数据修改预备党员失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * 预备党员批量添加
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String addYbdy(String[] keys, String jssj, int num) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		String kssj = jssj;

		int tempSj = 0;
		// 预备党员的预备期为一年

		if (jssj != null || !"".equals(jssj)) {
			tempSj = Integer.parseInt(jssj.substring(0, 4)) + 1;
			jssj = (String.valueOf(tempSj) + jssj.substring(4, 8));
		}

		for (int i = 0; i < num; i++) {

			String[] pk = keys[i].split("&");
			String nd = pk[0];
			String xn = pk[1];
			String xq = pk[2];
			String xh = pk[3];
			String bz = "";

			if (pk.length > 4) {
				bz = pk[4];
			}

			sql = "insert into ybdyxxb (xn,xq,nd,xh,kssj,jssj,bz)" + "values('"
					+ xn + "','" + xq + "','" + nd + "','" + xh + "','" + kssj
					+ "','" + jssj + "','" + bz + "')";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条修改失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] != 1) {
				whichpk = whichpk + " 第" + String.valueOf(i + 1)
						+ "条数据添加预备党员失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * @describe 取得预备党员转正列表
	 * @author luo
	 */
	public ArrayList<String[]> getZzybList(DtjszjcmModel myModel,
			DtjszjcmForm dataSearchForm) {

		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());
		// String jssj = DealString.toGBK(myModel.getJssj());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, nd, xq, xn));

		query.append("and jssj > kssj and (jssj - kssj) >= '10000'");
		// if (jssj != null && !"".equals(jssj)) {
		// query.append("and jssj = '" + jssj + "'");
		// }
		String[] colList = new String[] { "xn||xq||xh", "nd", "xn", "xq", "xh",
				"xm", "nj", "bjmc", "kssj", "jssj" };

		String sql = "select * from (select * from (select rownum r,xn||xq||xh,nd,xn,xq,xh,xm,nj,bjmc,kssj,jssj from view_ybdyxx "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_ybdyxx " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 获得党内奖惩表头
	 * @author luo
	 */
	public List getDnjcTopTr() {
		DAO dao = DAO.getInstance();
		String tableName = "view_dnjc";
		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "shlx" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);

		return topTr;
	}

	/**
	 * @describe 党内奖惩查询
	 * @author luo
	 */
	public ArrayList<String[]> getDnjcList(DtjszjcmModel myModel,
			DtjszjcmForm dataSearchForm) {

		// 事务处理查询
		DAO dao = DAO.getInstance();

		ArrayList<String[]> rs = new ArrayList<String[]>();
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		String shlx = DealString.toGBK(myModel.getShlx());
		String jcsj = DealString.toGBK(myModel.getJcsj());

		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));

		if (shlx != null && !("".equalsIgnoreCase(shlx.trim()))) {
			query.append(" and shlx='");
			query.append(shlx);
			query.append("' ");
		}
		if (jcsj != null && !("".equalsIgnoreCase(jcsj.trim()))) {
			query.append(" and jcsj='");
			query.append(jcsj);
			query.append("' ");
		}

		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "shlx" };

		String sql = "select * from (select * from (select distinct pk,rownum r, xymc,zymc,bjmc,xh,xm,nj,"
				+ "(decode(shlx, 'wsh', '未审核') || decode(shlx, 'tg', '通过') ||decode(shlx, 'wtg', '未通过')) as shlx"
				+ " from view_dnjc "
				+ query
				+ " ) where r<="
				+ (dataSearchForm.getPages().getStart() + dataSearchForm
						.getPages().getPageSize())
				+ ") where r>"
				+ dataSearchForm.getPages().getStart();

		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));

		// 分页
		sql = "select count(*) count from view_dnjc " + query;
		dataSearchForm.getPages().setMaxRecord(
				Integer.parseInt(dao.getOneRs(sql, new String[] {}, "count")));

		return rs;
	}

	/**
	 * @describe 获取奖惩理由列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getJclyList() {
		return zjcmDao.getJclyList();
	}

	/**
	 * @describe 获取奖惩类型列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getJclxList() {
		return zjcmDao.getJclxList();
	}

	/**
	 * @describe 添加党内奖惩申请记录
	 * @author luo
	 * @throws Exception
	 */
	public boolean updataDnjc(DtjszjcmModel myModel, String pk,
			HttpServletRequest request) throws Exception {

		String tableName = "zjcm_dnjc";
		String pkComment = "xh";
		String[] colList = new String[] { "xh", "jcsj", "jclx", "jcly", "bz",
				"shlx" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}

		return inserted;
	}

	/**
	 * 党内奖惩批量删除
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String delDnjc(String[] keys) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "delete from zjcm_dnjc where xh = '" + pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
			}// end if
		}// end for
		return whichpk;
	}

	/**
	 * @describe 得到奖惩者详细信息
	 * @author luo
	 */
	public HashMap<String, String> getDnjc(String xh) {

		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[] { "pk", "xh", "nj", "xm", "xydm",
				"zydm", "bjdm", "xb", "jclx", "jcly", "bz", "shlx", "jcsj" };

		String sql = "select distinct pk, xh,xydm,zydm,bjdm,xm,nj,xb,jclx,jcly,bz,shlx,jcsj"
				+ " from view_dnjc where xh = ? ";
		HashMap<String, String> rs = zjcmDao
				.swclQueryOne(colList, xh, map, sql);
		return rs;
	}

	/**
	 * 党内奖惩批量审核
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String dnjcShhAll(String[] keys, String shlx) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "update zjcm_dnjc set shlx = '" + shlx + "' where xh = '"
					+ pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据审核失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
	
	/**
	 * 党内奖惩批量审核
	 * 
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String swclShhAll(String[] keys, String shlx) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sb = new StringBuffer();
		String[] pksql = new String[] {};
		String sql = "";
		for (int i = 0; i < keys.length; i++) {
			String pk = DealString.toGBK(keys[i]);// 得到主键
			sql = "update swcl set shlx = '" + shlx + "' where xh = '"
					+ pk + "'";
			// 把主键组合成sql语句
			sb.append(sql);
			sb.append("!!#!!");
		}// end for
		// sql语句拆分成数组
		pksql = sb.toString().split("!!#!!");
		int[] judge = dao.runBatch(pksql);
		String whichpk = "";
		// 检查哪一条删除失败
		for (int i = 0; i < judge.length; i++) {
			if (judge[i] < 0) {
				whichpk = whichpk + " 第" + String.valueOf(i) + "条数据审核失败;\n";
			}// end if
		}// end for
		return whichpk;
	}
}
