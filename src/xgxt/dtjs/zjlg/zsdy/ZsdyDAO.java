package xgxt.dtjs.zjlg.zsdy;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.zjlg.ZjlgDtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.MakeQuery;

public class ZsdyDAO extends ZjlgDtjsDAO {

	/**
	 * 保存正式党员
	 * 
	 * @throws Exception
	 */
	public boolean saveZsdy(ZsdyModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String pk = xn + xq + xh;

		String[] colList = new String[] { "nd", "xh", "xn", "xq", "zzsj",
				"xsccdm", "cjhdqk", "zbshqk", "bz", "rdsj", "ybdykssj",
				"ybdyjssj", "zzxxqk", "dfjnqk", "rdlxr", "lxrdh" };
		String[] inputList = FormModleCommon.modelToStrings(colList, model);
		boolean flg = StandardOperation.delete("dyxxb", "xn||xq||xh", pk,
				request);

		if (flg) {
			flg = StandardOperation
					.insert("dyxxb", colList, inputList, request);
		}
		if (flg) {
			StandardOperation.update("ybdyxxb", new String[] { "zzzt" },
					new String[] { "no" }, "xh", xh, request);
			StandardOperation.update("rdjjfzxxb", new String[] { "zzzt" },
					new String[] { "no" }, "xh", xh, request);
		}
		return flg;
	}

	/**
	 * 组织关系转接
	 * 
	 * @throws Exception
	 */
	public boolean saveZzgx(ZsdyModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String zjlx = request.getParameter("zjlx");
		String zjsj = request.getParameter("zjsj");
		String zjdz = DealString.toGBK(request.getParameter("zjdz"));
		String bz = DealString.toGBK(request.getParameter("zjbz"));
		zjdz = "in".equalsIgnoreCase(zjlx) ? getZbmc(xh) : zjdz;
		String pk = xh + zjlx + zjsj;

		boolean flg = StandardOperation.delete("zjlg_zzgx", "xh||zjlx||zjsj",
				pk, request);
		if (flg) {
			StandardOperation.insert("zjlg_zzgx", new String[] { "xh", "zjlx",
					"zjsj", "zjdz", "bz","zjmm" }, new String[] { xh, zjlx, zjsj,
					zjdz, bz,"zsdy" }, request);
			StandardOperation.update("dyxxb", new String[] { "zzzt" },
					new String[] { "no" }, "xh", xh, request);
		}

		return flg;
	}
	
	/**
	 * 获得正式党员列表
	 */
	public ArrayList<String[]> getZsdyList(String tableName, ZsdyModel model,
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

		return CommonQueryDAO.commonQuery(tableName, query.toString(),
				myQuery.getInputList(), colList, "", model);
	}

	/**
	 * 获得正式党员
	 * 
	 */
	public HashMap<String, String> getZsdyInfo(String pk) {
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "xn", "xq",
				"xymc", "zymc", "bjmc", "zzsj", "xsccdm", "cjhdqk", "zbshqk",
				"bz", "rdsj", "ybdykssj", "ybdyjssj", "zzxxqk", "dfjnqk",
				"rdlxr", "lxrdh", "sfwj" };
		return commonQueryOne("view_zjlg_dyxx", colList, "xn||xq||xh", pk);
	}

	/**
	 * 删除正式党员
	 */
	public boolean delZsdy(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from dyxxb where xn||xq||xh ='" + pk + "'";
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
