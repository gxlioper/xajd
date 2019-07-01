package xgxt.dtjs.zjlg.zzgx;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zjlg.ZjlgDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZzgxDAO extends ZjlgDtjsDAO {

	/**
	 * 保存组织关系转接
	 * 
	 * @throws Exception
	 */
	public boolean saveZzgx(ZzgxModel model, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		String xh = model.getXh()[0];
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String zjlx = model.getZjlx();
		String zjsj = model.getZjsj();
		String zjmm = model.getZjmm();
		String ydw = model.getYdw();
		String dfjzyf = model.getDfjzyf();
		String jsxbh = model.getJsxbh();
		String lxdh = model.getLxdh();
		String yxq = model.getYxq();
		String zjdz = model.getZjdz();
		String bz = DealString.toGBK(model.getBz());
		String pk = xh + zjlx + zjsj;

		boolean flg = StandardOperation.delete("zjlg_zzgx", "xh||zjlx||zjsj",
				pk, request);

		String[] colList = new String[] { "xh", "zjsj", "ydw", "dfjzyf",
				"jsxbh", "lxdh", "zjlx", "yxq", "zjmm", "bz", "zjdz" };
		//String[] inputList = FormModleCommon.modelToStrings(colList, model);
		
		if (flg) {
			flg = StandardOperation.insert("zjlg_zzgx", colList, new String[] {
					xh, zjsj, ydw, dfjzyf, jsxbh, lxdh, zjlx, yxq, zjmm, bz,
					zjdz }, request);
		}

		if (flg) {
			String sql = "select count(*) num from dyxxb where xh = ? and zzzt='yes'";
			String zsdy = dao.getOneRs(sql, new String[] { xh }, "num");

			sql = "select count(*) num from ybdyxxb where xh = ? and zzzt='yes'";
			String ybdy = dao.getOneRs(sql, new String[] { xh }, "num");

			if ("in".equalsIgnoreCase(zjlx)) {
				if ("ybdy".equalsIgnoreCase(zjmm)) {
					if ("0".equalsIgnoreCase(ybdy)) {
						flg = StandardOperation.delete("ybdyxxb", "xn||xq||xh",
								xn + xq + xh, request);
						if (flg) {
							String zzzt = "yes";
							if (!"0".equalsIgnoreCase(zsdy)) {
								zzzt = "no";
							}
							flg = StandardOperation.insert("ybdyxxb",
									new String[] { "xh", "xn", "xq", "nd",
											"kssj", "bz", "zzzt" },
									new String[] { xh, xn, xq, nd, zjsj, bz,
											zzzt }, request);
							if (flg) {
								flg = StandardOperation.update("rdjjfzxxb",
										new String[] { "zzzt" },
										new String[] { "no" }, "xh", xh,
										request);
							}
						}
					}
				} else {
					if ("0".equalsIgnoreCase(zsdy)) {
						flg = StandardOperation.delete("dyxxb", "xn||xq||xh",
								xn + xq + xh, request);
						if (flg) {
							flg = StandardOperation.insert("dyxxb",
									new String[] { "xh", "xn", "xq", "nd",
											"rdsj", "bz" }, new String[] { xh,
											xn, xq, nd, zjsj, bz }, request);
							if (flg) {
								flg = StandardOperation.update("ybdyxxb",
										new String[] { "zzzt" },
										new String[] { "no" }, "xh", xh,
										request);
								if (flg) {
									flg = StandardOperation.update("rdjjfzxxb",
											new String[] { "zzzt" },
											new String[] { "no" }, "xh", xh,
											request);
								}
							}
						}
					}
				}
			}
		}

		return flg;
	}

	/**
	 * 获得组织关系转接列表
	 */
	public ArrayList<String[]> getZzgxList(String tableName, ZzgxModel model,
			String[] colList,HashMap<String, String> map) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj",
				"zjlx" };
		String[] queryLikeList = new String[] { "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		String xh = "";
		if (model.getXh() != null && model.getXh().length > 0) {
			xh = "%" + model.getXh()[0] + "%";
		} else {
			xh = "%";
		}
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select * from (select rownum r,");
		sqlBuffer.append(" a.* from " + tableName +" a ");
		sqlBuffer.append(myQuery.getQueryString().toString());
		sqlBuffer.append(" and xh like '" + xh + "'");
		sqlBuffer.append(getUserTypeQuery(map));
		sqlBuffer.append(" ) where r > ");
		sqlBuffer.append(model.getPages().getStart());
		sqlBuffer.append(" and r <= ");
		sqlBuffer.append((model.getPages().getStart() + model.getPages().getPageSize()));
		
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, sqlBuffer.toString(), model);
	}

	/**
	 * 获得组织关系转接信息
	 * 
	 */
	public HashMap<String, String> getZzgxInfo(String pk) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc","csrq","sfzh","mzmc", "zjsj", "ydw", "dfjzyf", "jsxbh",
				"lxdh", "xh", "zjlx", "yxq", "zjmm", "bz", "zjdz" };
		return commonQueryOne("view_zjlg_zzgx", colList, "pk", pk);
	}

	/**
	 * 删除组织关系
	 */
	public boolean delZzgx(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from zjlg_zzgx where xh||zjlx||zjsj ='" + pk + "'";
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
}
