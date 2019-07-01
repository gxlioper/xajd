package xgxt.dtjs.zjlg.jjfz;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zjlg.ZjlgDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;

public class JjfzDAO extends ZjlgDtjsDAO {

	/**
	 * 保存入党积极分子
	 * 
	 * @throws Exception
	 */
	public boolean saveJjfz(JjfzModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();
		
		String sql = "";
		String nd = model.getNd();
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String kssj = model.getKssj();
		String xsccdm = model.getXsccdm();
		String bz = DealString.toGBK(model.getBz());
		String rdlxr = model.getRdlxr();
		String lxrdh = model.getLxrdh();
		String djsqssj = model.getDjsqssj();
		String sxhbqk = model.getSxhbqk();
		String pk = xn + xq + xh;

		boolean flg = StandardOperation.delete("rdjjfzxxb", "xn||xq||xh", pk,
				request);

		if (flg) {
			sql = "select count(*) num from ybdyxxb where xh = ? and zzzt = 'yes'";
			String ybdy = dao.getOneRs(sql, new String[] { xh }, "num");

			sql = "select count(*) num from dyxxb where xh = ? and zzzt = 'yes'";
			String zsdy = dao.getOneRs(sql, new String[] { xh }, "num");

			if ("0".equalsIgnoreCase(ybdy) && "0".equalsIgnoreCase(zsdy)) {
				flg = StandardOperation.insert("rdjjfzxxb", new String[] {
						"nd", "xh", "xn", "xq", "kssj", "xsccdm", "bz",
						"rdlxr", "lxrdh", "djsqssj", "sxhbqk" }, new String[] {
						nd, xh, xn, xq, kssj, xsccdm, bz, rdlxr, lxrdh,
						djsqssj, sxhbqk }, request);
			} else {
				flg = StandardOperation.insert("rdjjfzxxb", new String[] {
						"nd", "xh", "xn", "xq", "kssj", "xsccdm", "bz", "zzzt",
						"rdlxr", "lxrdh", "djsqssj", "sxhbqk" }, new String[] {
						nd, xh, xn, xq, kssj, xsccdm, bz, "no", rdlxr, lxrdh,
						djsqssj, sxhbqk }, request);
			}
		}

		return flg;
	}

	/**
	 * 转为预备党员
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(JjfzModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();

		boolean flg = false;

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String kssj = request.getParameter("gzkssj");
		model.setKssj(kssj);
		String pk = xn + xq + xh;

		String[] colList = new String[] { "xsccdm", "kssj", "bz", "rdlxr","lxrdh" };
		String[] inputList = FormModleCommon.modelToStrings(colList, model);

		String sql = "select count(*) num from ybdyxxb where xn||xq||xh = ?";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");

		sql = "select count(*) num from dyxxb where xh = ? and zzzt = 'yes'";
		String zsdy = dao.getOneRs(sql, new String[] { xh }, "num");
		
		if (!"0".equalsIgnoreCase(num)) {
			if (!"0".equalsIgnoreCase(zsdy)) {
				colList = new String[] { "xsccdm", "kssj", "bz","zzzt", "rdlxr","lxrdh" };
				model.setZzzt("no");
				inputList = FormModleCommon.modelToStrings(colList, model);
			}
			flg = StandardOperation.update("ybdyxxb", colList, inputList,
					"xn||xq||xh", pk, request);
		} else {
			if (!"0".equalsIgnoreCase(zsdy)) {
				colList = new String[] { "xh", "xn", "xq", "nd", "xsccdm",
						"kssj", "bz", "zzzt", "rdlxr","lxrdh" };
				model.setZzzt("no");
			} else {
				colList = new String[] { "xh", "xn", "xq", "nd", "xsccdm",
						"kssj", "bz", "rdlxr","lxrdh" };
			}
			inputList = FormModleCommon.modelToStrings(colList, model);

			flg = StandardOperation.insert("ybdyxxb", colList, inputList,
					request);
		}

		if (flg) {
			flg = StandardOperation.update("rdjjfzxxb",
					new String[] { "zzzt" }, new String[] { "no" }, "xh", xh,
					request);
		}

		return flg;
	}
	
	/**
	 * 获得入党积极分子列表
	 */
	public ArrayList<String[]> getJjfzList(String tableName, JjfzModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "xydm", "zydm", "bjdm",
				"nd", "nj", "xsccdm", "zbdm", "zzzt" };
		String[] queryLikeList = new String[] { "xh", "xm" };

		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);

		StringBuilder query = new StringBuilder();
		query.append(myQuery.getQueryString());
		query.append(getUserTypeQuery(map));

		return CommonQueryDAO.commonQuery(tableName, query.toString(), myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得入党积极分子
	 * 
	 */
	public HashMap<String, String> getJjfzInfo(String pk) {
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xn",
				"xq", "xymc", "zymc", "bjmc", "kssj", "xsccdm", "bz", "zbmc",
				"sfwj", "sxhbqk", "sxhbqk", "djsqssj","rdlxr","lxrdh","sfwj" };
		return commonQueryOne("view_zjlg_rdjjfzxx", colList, "xn||xq||xh", pk);
	}

	/**
	 * 删除入党积极分子
	 */
	public boolean delJjfz(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from rdjjfzxxb where xn||xq||xh ='" + pk + "'";
			delsql[i] = sql;
		}

		int[] res = dao.runBatch(delsql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * 政治面貌转为预备党员
	 */
	public boolean saveYbdy(String[] key, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "select xh, xn, xq, nd, xsccdm, bz,rdlxr,lxrdh from view_rdjjfzxx ";
		String kssj = request.getParameter("gzkssj");
		String pk = "";
		String[] arrSql = new String[key.length * 3];
		StringBuffer query = new StringBuffer("where ");
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			if (i == 0) {
				query.append(" pk='" + pk + "' ");
			} else {
				query.append(" or pk='" + pk + "' ");
			}
		}

		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, new String[] { "xh",
						"xn", "xq", "nd", "xsccdm", "bz","rdlxr","lxrdh" });
		if (list != null && list.size() > 0) {
			String xh = "";
			String xn = "";
			String xq = "";
			String nd = "";
			String xsccdm = "";
			String bz = "";
			String zzzt = "";
			String num = "";
			String zsdy = "";
			String rdlxr = "";
			String lxrdh = "";
			int n = 0;
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				
				xh = map.get("xh");
				xn = map.get("xn");
				xq = map.get("xq");
				nd = map.get("nd");
				xsccdm = map.get("xsccdm");
				bz = map.get("bz");
				rdlxr = map.get("rdlxr");
				lxrdh = map.get("lxrdh");
				
				xsccdm = Base.isNull(xsccdm) ? "" : xsccdm;
				bz = Base.isNull(bz) ? "" : bz;
				pk = xn + xq + xh;
				
				sql = "select count(*) num from ybdyxxb where xn||xq||xh = ?";
				num = dao.getOneRs(sql, new String[] { pk }, "num");
				
				sql = "select count(*) num from dyxxb where xh = ? and zzzt = 'yes'";
				zsdy = dao.getOneRs(sql, new String[] { xh }, "num");
				zzzt = "0".equalsIgnoreCase(zsdy) ? "yes" : "no";

				if (!"0".equalsIgnoreCase(num)) {
					sql = "update ybdyxxb set zzzt='no' where xh='" + xh + "'";
					arrSql[n] = sql;
					n++;
					sql = "update ybdyxxb set xsccdm ='" + xsccdm + "',bz ='"
							+ bz + "',kssj ='" + kssj + "',zzzt='" + zzzt
							+ "' rdlxr='" + rdlxr + "' lxrdh='" + lxrdh
							+ "' where xn||xq||xh='" + xn + xq + xh + "'";
					arrSql[n] = sql;
					n++;
				} else {
					sql = "insert into ybdyxxb (nd,xn,xq,xh,xsccdm,bz,kssj,zzzt,rdlxr,lxrdh) values ('"
							+ nd
							+ "','"
							+ xn
							+ "', '"
							+ xq
							+ "','"
							+ xh
							+ "','"
							+ xsccdm
							+ "','"
							+ bz
							+ "','"
							+ kssj
							+ "','"
							+ zzzt
							+ "','"
							+ rdlxr
							+ "','"
							+ lxrdh
							+ "')";
					arrSql[n] = sql;
					n++;
				}
				sql = "update rdjjfzxxb set zzzt='no' where xh='" + xh + "'";
				arrSql[n] = sql;
				n++;
			}
		}
		int[] res = dao.runBatch(arrSql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
}
