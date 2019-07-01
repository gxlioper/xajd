package xgxt.dtjs.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.dao.DtjsDAO;
import xgxt.dtjs.model.DtjsModel;
import xgxt.dtjs.sjxy.SjxyDtjsDAO;
import xgxt.dtjs.sjxy.SjxyDtjsForm;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.SearchUtils;

public class DtjsService {
	DtjsDAO dao = new DtjsDAO();

	public ArrayList<String[]> getRtjjfzList(DtjsModel myModel) {
		// 入团积极份子查询
		String tableName = "view_rtjjfz";
		String xydm = DealString.toGBK(myModel.getXydm());
		String xm = DealString.toGBK(myModel.getXm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String xh = DealString.toGBK(myModel.getXh());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", xh, xm, nj, "", "", ""));
		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "hdsj" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}

	public List getRtjjfzTopTr() {
		// 入团积极份子表头
		DAO dao = DAO.getInstance();
		String tableName = "view_rtjjfz";
		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "hdsj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	public HashMap<String, String> getRtjjfzOne(String pk, String xh) {
		// 得到单个入团积极份子详细信息
		String tableName = "view_rtjjfz";
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "xydm",
				"xymc", "zydm", "zymc", "bjdm", "bjmc", "hdsj", "lrrq", "bz" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		colList = new String[] { "xh", "xm", "xb", "nj", "xydm", "xymc",
				"zydm", "zymc", "bjdm", "bjmc" };
		if (!xh.equalsIgnoreCase("")) {
			rs = dao.sxjyQueryOne3("view_xsjbxx", colList, "xh", xh, rs, "");
		}
		return rs;
	}

	public boolean updataRtjjfz(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// 保存单个入团积极份子
		String tableName = "rtjjfzb";
		String pkComment = "xh";
		String[] colList = new String[] { "xh", "hdsj", "bz" };
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
	 * @describe 判断是否团员
	 * @author luo
	 * @throws Exception
	 */
	public boolean isTy(String xh) throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "select count(*) from view_tyxxb where xh = '" + xh + "'";

		int count = dao.getOneRsint(sql);

		if (count == 0) {
			return true;
		}

		return false;
	}

	public boolean updataSzjylt(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// 保存单个 武汉理工 学院素质教育论坛
		String tableName = "sxjyszktb";
		String pkComment = "xydm||ltjh||lrrq";
		String[] colList = new String[] { "xydm", "jtkzqk", "bz", "zj", "ltjh",
				"xn", "nd" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}

	public boolean deleteRtjjfzOne(String pk, HttpServletRequest request)
			throws Exception {
		// 删除入党积极份子
		String tableName = "rtjjfzb";
		String pkComment = "xh";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean deleteSzjyltOne(String pk, HttpServletRequest request)
			throws Exception {
		// 删除武汉理工 学院素质教育论坛
		String tableName = "sxjyszktb";
		String pkComment = "xydm||ltjh||lrrq";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public ArrayList<String[]> getSzktList(DtjsModel myModel) {
		// 武汉理工 学院素质教育论坛
		String tableName = "view_sxjyszktb";
		String xydm = DealString.toGBK(myModel.getXydm());

		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, "", "", "", "", "", "", nd, "", xn));
		String[] colList = new String[] { "pk", "ltjh", "xymc", "lrrq", "nd" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}

	public ArrayList<String[]> getZtjyhzList(DtjsModel myModel) {
		// 武汉理工 学院主题教育活动
		String tableName = "view_sxjyztjyhdb";
		String xydm = DealString.toGBK(myModel.getXydm());

		String xn = DealString.toGBK(myModel.getXn());
		String nd = DealString.toGBK(myModel.getNd());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, "", "", "", "", "", "", nd, "", xn));
		String[] colList = new String[] { "pk", "hdmc", "xymc", "hdrq", "nd",
				"xn" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}

	public List getZtjyhzTopTr() {
		// 武汉理工 学院主题教育活动
		DAO dao = DAO.getInstance();
		String tableName = "view_sxjyztjyhdb";
		String[] colList = new String[] { "pk", "hdmc", "xymc", "hdrq", "nd",
				"xn" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	public List getSzktTopTr() {
		// 武汉理工 学院素质教育论坛表头
		DAO dao = DAO.getInstance();
		String tableName = "view_sxjyszktb";
		String[] colList = new String[] { "pk", "ltjh", "xymc", "lrrq", "nd" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	public HashMap<String, String> getSzjyltOne(String pk) {
		// 根据主键得到武汉理工 学院素质教育论坛
		String tableName = "view_sxjyszktb";
		String[] colList = new String[] { "pk", "ltjh", "xymc", "xydm", "lrrq",
				"zj", "jtkzqk", "bz", "xn", "nd", "lrrq" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public HashMap<String, String> getZtjyhzOne(String pk) {
		// 学院主题教育活动
		String tableName = "view_sxjyztjyhdb";
		String[] colList = new String[] { "pk", "hdmc", "xymc", "xydm", "hdrq",
				"hdnr", "zhdmc", "zhdrq", "xn", "nd", "hdxg", "bz" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public boolean updataZtjyhz(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// 保存单个 学院主题教育活动
		String tableName = "sxjyztjyhdb";
		String pkComment = "xydm||hdrq||hdmc";
		String[] colList = new String[] { "hdmc", "xydm", "hdrq", "hdnr",
				"zhdmc", "zhdrq", "xn", "nd", "hdxg", "bz" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}

	public ArrayList<String[]> getFhxxList(DtjsModel myModel) {
		// 武汉理工 马克思主义理论学习研究会分会基本信息
		String tableName = "mlzyyjhfhxxb";
		String fhmc = DealString.toGBK(myModel.getFhmc());
		String query = "";
		if (fhmc != null && !("".equalsIgnoreCase(fhmc.trim()))) {
			query += " where fhmc like '%";
			query += fhmc;
			query += "%' ";
		}
		String[] colList = new String[] { "fhmc", "fhmc", "zdls", "hzxm",
				"lxfs", "hyrs", "dyrs" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query,
				new String[] {}, colList, "");
		return rs;
	}

	public List getFhxxListTopTr() {
		// 武汉理工 马克思主义理论学习研究会分会基本信息表头
		DAO dao = DAO.getInstance();
		String tableName = "mlzyyjhfhxxb";
		String[] colList = new String[] { "fhmc", "fhmc", "zdls", "hzxm",
				"lxfs", "hyrs", "dyrs" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头

		return topTr;
	}

	public HashMap<String, String> getFhxxOne(String pk) {
		// 马克思主义理论学习研究会分会基本信息
		String tableName = "mlzyyjhfhxxb";
		String[] colList = new String[] { "fhmc", "zdls", "hzxm", "lxfs",
				"hyrs", "dyrs" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList,
				"fhmc", pk);
		return rs;
	}

	public boolean updataMlzyyjhfhxxb(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// 保存单个 马克思主义理论学习研究会分会基本信息
		String tableName = "mlzyyjhfhxxb";
		String pkComment = "fhmc";
		String[] colList = new String[] { "fhmc", "zdls", "hzxm", "lxfs",
				"hyrs", "dyrs" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}

	public boolean deleteFhxxOne(String pk, HttpServletRequest request)
			throws Exception {
		// 删除武汉理工 马克思主义理论学习研究会分会基本信息
		String tableName = "mlzyyjhfhxxb";
		String pkComment = "fhmc";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean deleteZtjyhzOne(String pk, HttpServletRequest request)
			throws Exception {
		// 删除武汉理工 学院主题教育活动
		String tableName = "sxjyztjyhdb";
		String pkComment = "xydm||hdrq||hdmc";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public ArrayList<String[]> getTkqkList(DtjsModel myModel) {
		// 上海工程 听课记录基本信息
		String tableName = "view_tkqkjl";
		String xydm = DealString.toGBK(myModel.getXydm());
		String zydm = DealString.toGBK(myModel.getZydm());
		String bjdm = DealString.toGBK(myModel.getBjdm());
		String nj = DealString.toGBK(myModel.getNj());
		String nd = DealString.toGBK(myModel.getNd());
		String xq = DealString.toGBK(myModel.getXq());
		String xn = DealString.toGBK(myModel.getXn());
		StringBuffer query = new StringBuffer(SearchUtils.makeQueryCondition(
				xydm, zydm, bjdm, "", "", "", nj, nd, xq, xn));
		String[] colList = new String[] { "pk", "kcmc", "xm", "tkrq", "tkr",
				"xymc", "nd" };
		ArrayList<String[]> rs = dao.sxjyQuery(tableName, query.toString(),
				new String[] {}, colList, "");
		return rs;
	}

	public List getTkqkListTopTr() {
		// 上海工程 听课记录基本信息表头
		DAO dao = DAO.getInstance();
		String tableName = "view_tkqkjl";
		String[] colList = new String[] { "pk", "kcmc", "xm", "tkrq", "tkr",
				"xymc", "nd" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// 表头
		return topTr;
	}

	public HashMap<String, String> getTkqkOne(String pk) {
		// 上海工程 听课记录单条
		String tableName = "view_tkqkjl";
		String[] colList = new String[] { "pk", "zgh", "bjdm", "zs", "kcmc",
				"js", "tkrq", "kssk", "jssk", "sklj", "xschb", "jrs", "crs",
				"wrs", "krs", "trs", "ktqf", "jsskqk", "xyxsbgsyj", "bz",
				"tkr", "ydrs", "sdrs", "cdrs", "kkrs", "qjrs", "xm", "bjmc",
				"zydm", "zymc", "xydm", "xymc", "xn", "nd", "xq", "nj" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		String tmp = rs.get("ktqf");
		char[] tmp2 = tmp.toCharArray();
		if (tmp2.length == 4) {
			rs.put("ktqf1", tmp2[0] + "");
			rs.put("ktqf2", tmp2[1] + "");
			rs.put("ktqf3", tmp2[2] + "");
			rs.put("ktqf4", tmp2[3] + "");
		}
		return rs;
	}

	public boolean updataTkqk(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		String tableName = "sxjy_tkqkjlb";
		String pkComment = "bjdm||tkrq||kssk||zgh||tkr";
		myModel.setKtqf(myModel.getKtqf1() + myModel.getKtqf2()
				+ myModel.getKtqf3() + myModel.getKtqf4());
		String[] colList = new String[] { "zgh", "bjdm", "zs", "kcmc", "js",
				"tkrq", "kssk", "jssk", "sklj", "xschb", "jrs", "crs", "wrs",
				"krs", "trs", "ktqf", "jsskqk", "xyxsbgsyj", "bz", "tkr",
				"ydrs", "sdrs", "cdrs", "kkrs", "qjrs", "xn", "nd", "xq", "xm" };
		String[] inputList = ModelToStrings.modelToStrings(colList, myModel);
		boolean inserted = StandardOperation.delete(tableName, pkComment, pk,
				request);
		if (inserted) {
			inserted = StandardOperation.insert(tableName, colList, inputList,
					request);
		}
		return inserted;
	}

	public boolean deleteTkqkOne(String pk, HttpServletRequest request)
			throws Exception {
		// 删除武汉理工 马克思主义理论学习研究会分会基本信息
		String tableName = "sxjy_tkqkjlb";
		String pkComment = "bjdm||tkrq||kssk||zgh||tkr";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}
	
	/**
	 * 查询党员相关数据信息
	 * @param String tableName
	 * @param DtjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> getDyxgsjxxList(String tableName, SjxyDtjsForm model, String[] colList) throws Exception{
		return dao.selectDyxgsjxxList(tableName,model,colList);
	}
	
	/**
	 * 查询党员相关数据统计信息（未分页）
	 * @param String tableName
	 * @param DtjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> queryDyxgsjtjForExport(String tableName, SjxyDtjsForm model, String[] colList) throws Exception{
		return dao.selectDyxgsjtjForExport(tableName,model,colList);
	}
	
	/**
	 * 查询学生培养层次列表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryDtjsXspyccList(){		
		return dao.getDtjsPyccSelectList();
	}
}
