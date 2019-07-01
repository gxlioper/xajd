package xgxt.studentInfo.zjlg.cxqk;

import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.studentInfo.zjlg.ZjlgXsxxDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CxqkDAO extends ZjlgXsxxDAO {

	/**
	 * 保存诚信情况
	 * 
	 * @throws Exception
	 */
	public boolean saveCxqk(CxqkModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String cxjl = DealString.toGBK(model.getCxjl());
		String jlsj = model.getJlsj();
		String bz = DealString.toGBK(model.getBz());
		String pk = xh + jlsj;

		boolean flg = StandardOperation.delete("zjlg_cxqk", "xh||jlsj", pk,
				request);

		if (flg) {
			flg = StandardOperation.insert("zjlg_cxqk", new String[] { "xh",
					"cxjl", "jlsj", "bz" },
					new String[] { xh, cxjl, jlsj, bz }, request);
		}

		return flg;
	}

	/**
	 * 获得诚信情况列表
	 */
	public ArrayList<String[]> getCxqkList(String tableName, CxqkModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "xydm", "zydm", "bjdm", "nj" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}

	/**
	 * 获得诚信情况
	 */
	public HashMap<String, String> getCxqk(String pk) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "cxjl", "jlsj", "bz" };
		return commonQueryOne("view_zjlg_cxqk", colList, "pk", pk);
	}

	/**
	 * 删除入伍信息
	 */
	public boolean delCxqk(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from zjlg_cxqk where xh||jlsj ='" + pk + "'";
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
