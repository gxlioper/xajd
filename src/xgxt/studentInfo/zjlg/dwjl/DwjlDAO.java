package xgxt.studentInfo.zjlg.dwjl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.zjlg.ZjlgXsxxDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class DwjlDAO extends ZjlgXsxxDAO {

	/**
	 * 保存对外交流转接
	 * 
	 * @throws Exception
	 */
	public boolean saveDwjl(DwjlModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String jlxm = model.getJlxm();
		String cgsj = model.getCgsj();
		String hgsj = model.getHgsj();
		String bz = DealString.toGBK(model.getBz());
		String pk = xn + xq + xh + jlxm;

		boolean flg = StandardOperation.delete("zjlg_dwjl", "xn||xq||xh||jlxm",
				pk, request);

		if (flg) {
			flg = StandardOperation.insert("zjlg_dwjl", new String[] { "xh",
					"xn", "xq", "jlxm", "cgsj", "hgsj", "bz" }, new String[] {
					xh, xn, xq, jlxm, cgsj, hgsj, bz }, request);
		}

		return flg;
	}

	/**
	 * 获得对外交流列表
	 */
	public ArrayList<String[]> getDwjlList(String tableName, DwjlModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "xydm", "zydm", "bjdm",
				"nj", "jlxm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}

	/**
	 * 获得对外交流信息
	 * 
	 */
	public HashMap<String, String> getDwjlInfo(String pk) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xn", "xq",
				"xymc", "zymc", "bjmc", "jlxm", "cgsj", "hgsj", "bz" };
		return commonQueryOne("view_zjlg_dwjl", colList, "pk", pk);
	}

	/**
	 * 删除对外关系
	 */
	public boolean delDwgx(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from zjlg_dwjl where xn||xq||xh||jlxm ='" + pk + "'";
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
	 * 获得交流项目
	 */
	public List<HashMap<String, String>> getJlxm() {
		return commonQueryforList("zjlg_dwjlxm", "", new String[] {},
				new String[] { "xmdm", "xmmc" }, "");
	}
}
