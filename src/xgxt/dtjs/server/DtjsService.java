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
		// ���Ż������Ӳ�ѯ
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
		// ���Ż������ӱ�ͷ
		DAO dao = DAO.getInstance();
		String tableName = "view_rtjjfz";
		String[] colList = new String[] { "pk", "xh", "xm", "xymc", "zymc",
				"bjmc", "hdsj" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ

		return topTr;
	}

	public HashMap<String, String> getRtjjfzOne(String pk, String xh) {
		// �õ��������Ż���������ϸ��Ϣ
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
		// ���浥�����Ż�������
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
	 * @describe �ж��Ƿ���Ա
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
		// ���浥�� �人�� ѧԺ���ʽ�����̳
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
		// ɾ���뵳��������
		String tableName = "rtjjfzb";
		String pkComment = "xh";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean deleteSzjyltOne(String pk, HttpServletRequest request)
			throws Exception {
		// ɾ���人�� ѧԺ���ʽ�����̳
		String tableName = "sxjyszktb";
		String pkComment = "xydm||ltjh||lrrq";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public ArrayList<String[]> getSzktList(DtjsModel myModel) {
		// �人�� ѧԺ���ʽ�����̳
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
		// �人�� ѧԺ��������
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
		// �人�� ѧԺ��������
		DAO dao = DAO.getInstance();
		String tableName = "view_sxjyztjyhdb";
		String[] colList = new String[] { "pk", "hdmc", "xymc", "hdrq", "nd",
				"xn" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ

		return topTr;
	}

	public List getSzktTopTr() {
		// �人�� ѧԺ���ʽ�����̳��ͷ
		DAO dao = DAO.getInstance();
		String tableName = "view_sxjyszktb";
		String[] colList = new String[] { "pk", "ltjh", "xymc", "lrrq", "nd" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ

		return topTr;
	}

	public HashMap<String, String> getSzjyltOne(String pk) {
		// ���������õ��人�� ѧԺ���ʽ�����̳
		String tableName = "view_sxjyszktb";
		String[] colList = new String[] { "pk", "ltjh", "xymc", "xydm", "lrrq",
				"zj", "jtkzqk", "bz", "xn", "nd", "lrrq" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public HashMap<String, String> getZtjyhzOne(String pk) {
		// ѧԺ��������
		String tableName = "view_sxjyztjyhdb";
		String[] colList = new String[] { "pk", "hdmc", "xymc", "xydm", "hdrq",
				"hdnr", "zhdmc", "zhdrq", "xn", "nd", "hdxg", "bz" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList, "pk",
				pk);
		return rs;
	}

	public boolean updataZtjyhz(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// ���浥�� ѧԺ��������
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
		// �人�� ���˼��������ѧϰ�о���ֻ������Ϣ
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
		// �人�� ���˼��������ѧϰ�о���ֻ������Ϣ��ͷ
		DAO dao = DAO.getInstance();
		String tableName = "mlzyyjhfhxxb";
		String[] colList = new String[] { "fhmc", "fhmc", "zdls", "hzxm",
				"lxfs", "hyrs", "dyrs" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ

		return topTr;
	}

	public HashMap<String, String> getFhxxOne(String pk) {
		// ���˼��������ѧϰ�о���ֻ������Ϣ
		String tableName = "mlzyyjhfhxxb";
		String[] colList = new String[] { "fhmc", "zdls", "hzxm", "lxfs",
				"hyrs", "dyrs" };
		HashMap<String, String> rs = dao.sxjyQueryOne(tableName, colList,
				"fhmc", pk);
		return rs;
	}

	public boolean updataMlzyyjhfhxxb(DtjsModel myModel, String pk,
			HttpServletRequest request) throws Exception {
		// ���浥�� ���˼��������ѧϰ�о���ֻ������Ϣ
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
		// ɾ���人�� ���˼��������ѧϰ�о���ֻ������Ϣ
		String tableName = "mlzyyjhfhxxb";
		String pkComment = "fhmc";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public boolean deleteZtjyhzOne(String pk, HttpServletRequest request)
			throws Exception {
		// ɾ���人�� ѧԺ��������
		String tableName = "sxjyztjyhdb";
		String pkComment = "xydm||hdrq||hdmc";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}

	public ArrayList<String[]> getTkqkList(DtjsModel myModel) {
		// �Ϻ����� ���μ�¼������Ϣ
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
		// �Ϻ����� ���μ�¼������Ϣ��ͷ
		DAO dao = DAO.getInstance();
		String tableName = "view_tkqkjl";
		String[] colList = new String[] { "pk", "kcmc", "xm", "tkrq", "tkr",
				"xymc", "nd" };
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);// ��ͷ
		return topTr;
	}

	public HashMap<String, String> getTkqkOne(String pk) {
		// �Ϻ����� ���μ�¼����
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
		// ɾ���人�� ���˼��������ѧϰ�о���ֻ������Ϣ
		String tableName = "sxjy_tkqkjlb";
		String pkComment = "bjdm||tkrq||kssk||zgh||tkr";
		boolean del = StandardOperation.delete(tableName, pkComment, pk,
				request);
		return del;
	}
	
	/**
	 * ��ѯ��Ա���������Ϣ
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
	 * ��ѯ��Ա�������ͳ����Ϣ��δ��ҳ��
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
	 * ��ѯѧ����������б�
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryDtjsXspyccList(){		
		return dao.getDtjsPyccSelectList();
	}
}
