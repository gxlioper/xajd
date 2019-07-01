package xgxt.dtjs.sjxy.dyxx;

import java.lang.reflect.InvocationTargetException;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.sjxy.SjxyDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;


public class DyxxDAO extends SjxyDtjsDAO {

	/**
	 * 获得党员信息相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, DyxxModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj", "xn",
				"nd", "xq", "zbmc","zzzt" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String query = myQuery.getQueryString();

		return CommonQueryDAO.commonQuery(tableName, query, myQuery
				.getInputList(), colList, "", model);
	}

	/**
	 * 获得党员信息相关信息
	 * 
	 * @param tableName(表名)
	 * @param pk(主键)
	 * @param pkValue(主键值)
	 * @param colList(输出值)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDyxxInfo(String tableName, String pk,
			String pkValue, String[] colList) {
		return commonQueryOne(tableName, colList, pk, pkValue);
	}

	/**
	 * 保存支部名称
	 * 
	 * @throws Exception
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveZbmc(DyxxModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();

		boolean flg = true;

		String tableName = "sjxy_sszbb";
		String xn = model.getXn();
		String xq = model.getXq();
		String xh = model.getXh();
		String xydm = model.getXydm();
		String lx = model.getLx();
		String pk = xn + xq + xh + lx;
		String sql = "select zbmc from sjxy_sszbb where xn||xq||xh||lx =?";
		String zbmc = dao.getOneRs(sql, new String[] { pk }, "zbmc");

		if (!Base.isNull(zbmc)) {
			if ("积极分子".equalsIgnoreCase(lx)) {
				lx = "预备党员";
			} else if ("预备党员".equalsIgnoreCase(lx)) {
				lx = "正式党员";
			}

			flg = StandardOperation.delete(tableName, "xn||xq||xh||lx", xn+xq+xh+lx,
					request);
			if (flg) {
				flg = StandardOperation.insert("sjxy_sszbb", new String[] {
						"xn", "xq", "xh", "xydm", "zbmc", "lx" }, new String[] {
						xn, xq, xh, xydm, zbmc, lx }, request);
			}
		}
		return flg;
	}
	
	/**
	 * 保存支部名称
	 * 
	 * @throws Exception
	 * @author luojw
	 * @throws Exception 
	 */
	public boolean saveZbmc(String[] key, String lx,HttpServletRequest request)
			throws Exception {
		
		DAO dao = new DAO();
		
		boolean flg = false;
		
		String nextLx = "积极分子".equalsIgnoreCase(lx) ? "预备党员" : "正式党员";
		String[] arrSql = new String[key.length * 2];
		int n = 0;
		
		for (int i = 0; i < key.length; i++) {
			arrSql[n] = "delete from sjxy_sszbb where xn || xq || xh || lx = '"
					+ key[i] + nextLx + "'";
			n++;
			arrSql[n] = "insert into sjxy_sszbb select xn, xq, xh, xydm, zbmc, '"
					+ nextLx
					+ "' from sjxy_sszbb where xn || xq || xh || lx = '"
					+ key[i] + lx + "'";
			n++;
		}
		
		int[] res = dao.runBatch(arrSql);

		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}
		return flg;
	}
	
	/**
	 * 转为预备党员
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveYbdy(DyxxModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();

		boolean flg = false;

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String kssj = request.getParameter("gzkssj");
		model.setKssj(kssj);
		String pk = xn + xq + xh;

		String[] colList = new String[] { "xsccdm", "kssj", "bz" };
		String[] inputList = FormModleCommon.modelToStrings(colList, model);

		String sql = "select count(*) num from ybdyxxb where xn||xq||xh = ?";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");

		sql = "select count(*) num from dyxxb where xh = ? and zzzt = 'yes'";
		String zsdy = dao.getOneRs(sql, new String[] { xh }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			if (!"0".equalsIgnoreCase(zsdy)) {
				colList = new String[] { "xsccdm", "kssj", "bz", "zzzt" };
				model.setZzzt("no");
				inputList = FormModleCommon.modelToStrings(colList, model);
			}
			flg = StandardOperation.update("ybdyxxb", colList, inputList,
					"xn||xq||xh", pk, request);
		} else {
			if (!"0".equalsIgnoreCase(zsdy)) {
				colList = new String[] { "xh", "xn", "xq", "nd", "xsccdm",
						"kssj", "bz", "zzzt" };
				model.setZzzt("no");
			} else {
				colList = new String[] { "xh", "xn", "xq", "nd", "xsccdm",
						"kssj", "bz" };
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
	 * 预备党员转正
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveZsdy(DyxxModel model, HttpServletRequest request)
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
		String bz = model.getBz();
		String pk = xn + xq + xh;

		String sql = "select count(*) num from dyxxb where xn||xq||xh = ? and zzzt = 'yes'";
		String num = dao.getOneRs(sql, new String[] { pk }, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flg = StandardOperation.update("dyxxb", new String[] { "xsccdm",
					"ybdykssj", "ybdyjssj", "rdsj", "zzsj", "bz" },
					new String[] { xsccdm, kssj, jssj, jssj, jssj, bz },
					"xn||xq||xh", pk, request);
		} else {
			flg = StandardOperation.delete("dyxxb", "xn||xq||xh", pk, request);
			if (flg) {
				flg = StandardOperation.insert("dyxxb", new String[] { "nd",
						"xn", "xq", "xh", "xsccdm", "ybdykssj", "ybdyjssj",
						"rdsj", "zzsj", "bz" }, new String[] { nd, xn, xq, xh,
						xsccdm, kssj, jssj, jssj, jssj, bz }, request);
			}
		}

		if (flg) {
			flg = StandardOperation.update("ybdyxxb", new String[] { "jssj",
					"zzzt" }, new String[] { jssj, "no" }, "xh", xh, request);
		}

		return flg;
	}

	/**
	 * 转为预备党员
	 * 
	 * @throws Exception
	 * @author luojw
	 */
	public boolean saveYbdy(String[] key, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "select xh, xn, xq, nd, xsccdm, bz from rdjjfzxxb ";
		String kssj = request.getParameter("gzkssj");
		String pk = "";
		String[] arrSql = new String[key.length * 3];
		StringBuffer query = new StringBuffer("where ");
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			if (i == 0) {
				query.append(" xn||xq||xh='" + pk + "' ");
			} else {
				query.append(" or xn||xq||xh='" + pk + "' ");
			}
		}

		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, new String[] { "xh",
						"xn", "xq", "nd", "xsccdm", "bz" });
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
			int n = 0;
			for (int i = 0; i < list.size(); i++) {
				
				HashMap<String, String> map = list.get(i);
				
				xh = map.get("xh");
				xn = map.get("xn");
				xq = map.get("xq");
				nd = map.get("nd");
				xsccdm = map.get("xsccdm");
				bz = map.get("bz");
				
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
							+ "' where xn||xq||xh='" + xn + xq + xh + "'";
					arrSql[n] = sql;
					n++;
				} else {
					sql = "insert into ybdyxxb (nd,xn,xq,xh,xsccdm,bz,kssj,zzzt) values ('"
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
	
	/**
	 * 预备党员转正
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveZsdy(String[] key, HttpServletRequest request)
			throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "select xh, xn, xq, nd, xsccdm,kssj,jssj, bz from ybdyxxb ";
		String rdsj = request.getParameter("gzkssj");
		String pk = "";
		String[] arrSql = new String[key.length * 5];
		StringBuffer query = new StringBuffer("where ");
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			if (i == 0) {
				query.append(" xn||xq||xh='" + pk + "' ");
			} else {
				query.append(" or xn||xq||xh='" + pk + "' ");
			}
		}

		List<HashMap<String, String>> list = dao.getList(
				sql + query.toString(), new String[] {}, new String[] { "xh",
						"xn", "xq", "nd", "xsccdm", "kssj", "jssj", "bz"});
		if (list != null && list.size() > 0) {
			String xh = "";
			String xn = "";
			String xq = "";
			String nd = "";
			String xsccdm = "";
			String ybdykssj = "";
			String ybdyjssj = "";
			String bz = "";
			String num = "";

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
				
				bz = Base.isNull(bz) ? "" : bz;
				xsccdm = Base.isNull(xsccdm) ? "" : xsccdm;
				ybdykssj = Base.isNull(ybdykssj) ? "" : ybdykssj;
				
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
							+ "',zzzt='yes' where xn||xq||xh='" + xn + xq + xh
							+ "'";
					arrSql[n] = sql;
					n++;
				} else {
					sql = "insert into dyxxb (nd,xn,xq,xh,xsccdm,bz,rdsj,zzsj,ybdykssj,ybdyjssj) values ('"
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
							+ 
						 "')";
					arrSql[n] = sql;
					n++;
				}
				sql = "update ybdyxxb set zzzt='no' where xh='" + xh + "'";
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
