package xgxt.dtjs.zjlg.ybdy;

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

public class YbdyDAO extends ZjlgDtjsDAO {

	/**
	 * 保存预备党员
	 * 
	 * @throws Exception
	 */
	public boolean saveYbdy(YbdyModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();
		
		String sql = "";
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String pk = xn + xq + xh;

		String[] colList = new String[] { "nd", "xh", "xn", "xq", "kssj",
				"xsccdm", "sfkyzz", "jssj", "bz", "thcs", "cjzzxxqk", "lxr1",
				"dfjnqk", "kcqk", "rdlxr", "lxrdh" };
		String[] inputList = FormModleCommon.modelToStrings(colList, model);
		boolean flg = StandardOperation.delete("ybdyxxb", "xn||xq||xh", pk,
				request);

		if (flg) {

			sql = "select count(*) num from dyxxb where xh = ? and zzzt = 'yes'";
			String zsdy = dao.getOneRs(sql, new String[] { xh }, "num");

			if (!"0".equalsIgnoreCase(zsdy)) {
				model.setZzzt("no");
				colList = new String[] { "nd", "xh", "xn", "xq", "kssj",
						"xsccdm", "sfkyzz", "jssj", "bz", "thcs", "cjzzxxqk",
						"lxr1", "dfjnqk", "kcqk", "zzzt", "rdlxr", "lxrdh" };	
				inputList = FormModleCommon.modelToStrings(colList, model);
			}
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
	 * 预备党员转正
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(YbdyModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();

		boolean flg = false;

		String nd = model.getNd();
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String xsccdm = model.getXsccdm();
		String kssj = model.getKssj();
		String jssj = request.getParameter("gzkssj");
		String dfjnqk = DealString.toGBK(model.getDfjnqk());
		String zzxxqk = DealString.toGBK(model.getCjzzxxqk());
		String bz = DealString.toGBK(model.getBz());
		String rdlxr = model.getRdlxr();
		String lxrdh = model.getLxrdh();
		String pk = xn + xq + xh;

		String sql = "select count(*) num from dyxxb where xn||xq||xh = ? and zzzt = 'yes'";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = StandardOperation.update("dyxxb", new String[] { "xsccdm",
					"ybdykssj", "ybdyjssj", "rdsj", "zzsj", "bz", "rdlxr",
					"lxrdh" }, new String[] { xsccdm, kssj, jssj, jssj, jssj,
					bz, rdlxr, lxrdh }, "xn||xq||xh", pk, request);
		} else {
			flg = StandardOperation.delete("dyxxb", "xn||xq||xh", pk, request);
			if (flg) {
				flg = StandardOperation.insert("dyxxb", new String[] { "nd",
						"xn", "xq", "xh", "xsccdm", "ybdykssj", "ybdyjssj",
						"rdsj", "zzsj", "bz", "dfjnqk", "zzxxqk", "rdlxr",
						"lxrdh" }, new String[] { nd, xn, xq, xh, xsccdm, kssj,
						jssj, jssj, jssj, bz, dfjnqk, zzxxqk, rdlxr, lxrdh },
						request);
			}
		}

		if (flg) {
			flg = StandardOperation.update("ybdyxxb", new String[] { "jssj",
					"zzzt" }, new String[] { jssj, "no" }, "xh", xh, request);
			pk = xh + "zz" + jssj;

			if (flg) {
				flg = StandardOperation.delete("zjlg_zzgx", "xh||zjlx||zjsj",
						pk, request);
				if (flg) {
					StandardOperation.insert("zjlg_zzgx", new String[] { "xh",
							"zjlx", "zjsj", "zjdz", "bz", "zjmm" },
							new String[] { xh, "zz", jssj, getZbmc(xh), bz,
									"zsdy" }, request);
				}
			}
		}

		return flg;
	}
	
	/**
	 * 获得预备党员列表
	 */
	public ArrayList<String[]> getYbdyList(String tableName, YbdyModel model,
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
	 * 获得预备党员
	 * 
	 */
	public HashMap<String, String> getYbdyInfo(String pk) {
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xn",
				"sfkyzz", "xq", "xymc", "zymc", "bjmc", "xsccdm", "zbmc",
				"sxhbcs", "kssj", "jssj", "bz", "thcs", "cjzzxxqk", "lxr1",
				"dfjnqk", "kcqk", "rdlxr", "lxrdh","sfwj" };
		return commonQueryOne("view_zjlg_ybdyxx", colList, "xn||xq||xh", pk);
	}

	/**
	 * 删除预备党员
	 */
	public boolean delYbdy(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from ybdyxxb where xn||xq||xh ='" + pk + "'";
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
	 * 政治面貌转为正式党员
	 */
	public boolean saveZsdy(String[] key, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "select xh, xn, xq, nd, xsccdm,kssj,jssj, bz,dfjnqk,cjzzxxqk,rdlxr,lxrdh from view_zjlg_ybdyxx ";
		String rdsj = request.getParameter("gzkssj");
		String pk = "";
		String[] arrSql = new String[key.length * 5];
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
						"xn", "xq", "nd", "xsccdm", "kssj", "jssj", "bz",
						"dfjnqk", "cjzzxxqk","rdlxr","lxrdh" });
		if (list != null && list.size() > 0) {
			String xh = "";
			String xn = "";
			String xq = "";
			String nd = "";
			String xsccdm = "";
			String ybdykssj = "";
			String ybdyjssj = "";
			String bz = "";
			String dfjnqk = "";
			String cjzzxxqk = "";
			String num = "";
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
				ybdykssj = map.get("kssj");
				ybdyjssj = rdsj;//map.get("ybdyjssj");
				bz = map.get("bz");
				dfjnqk = map.get("dfjnqk");
				cjzzxxqk = map.get("cjzzxxqk");
				rdlxr = map.get("rdlxr");
				lxrdh = map.get("lxrdh");
				
				bz = Base.isNull(bz) ? "" : bz;
				xsccdm = Base.isNull(xsccdm) ? "" : xsccdm;
				ybdykssj = Base.isNull(ybdykssj) ? "" : ybdykssj;
				dfjnqk = Base.isNull(dfjnqk) ? "" : dfjnqk;
				cjzzxxqk = Base.isNull(cjzzxxqk) ? "" : cjzzxxqk;
				
				pk = xn + xq + xh;
				sql = "select count(*) num from dyxxb where xn||xq||xh = ?";
				num = dao.getOneRs(sql, new String[] { pk }, "num");
				if (!"0".equalsIgnoreCase(num)) {
					sql = "update dyxxb set zzzt='no' where xh='" + xh + "'";
					arrSql[n] = sql;
					n++;
					sql = "update dyxxb set xsccdm ='" + xsccdm + "',bz ='"
							+ bz + "',ybdyjssj ='" + ybdyjssj
							+ "', ybdykssj = '" + ybdykssj + "', rdsj = '"
							+ rdsj + "', zzsj = '" + rdsj
							+ "',zzzt='yes',rdlxr='" + rdlxr + "',lxrdh='"
							+ lxrdh + "' where xn||xq||xh='" + xn + xq + xh
							+ "'";
					arrSql[n] = sql;
					n++;
				} else {
					sql = "insert into dyxxb (nd,xn,xq,xh,xsccdm,bz,rdsj,zzsj,ybdykssj,ybdyjssj,dfjnqk,zzxxqk,rdlxr,lxrdh) values ('"
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
							+ rdsj
							+ "','"
							+ rdsj
							+ "','"
							+ ybdykssj
							+ "','"
							+ ybdyjssj
							+ "','"
							+ dfjnqk
							+ "','"
							+ cjzzxxqk
							+ "','" + rdlxr + "','" + lxrdh + "')";
					arrSql[n] = sql;
					n++;
				}
				sql = "update ybdyxxb set zzzt='no' where xh='" + xh + "'";
				arrSql[n] = sql;
				n++;
				sql = "delete from zjlg_zzgx where xh||zjlx||zjsj = '" + xh
						+ "zz" + rdsj + "'";
				arrSql[n] = sql;
				n++;
				sql = "insert into zjlg_zzgx(xh,zjlx, zjsj, zjdz,bz,zjmm) values('"
						+ xh
						+ "','zz','"
						+ rdsj
						+ "','"
						+ getZbmc(xh)
						+ "','"
						+ bz + "','zsdy')";
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
