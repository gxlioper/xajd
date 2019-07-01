package xgxt.studentInfo.zjlg.rwxx;

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

public class RwxxDAO extends ZjlgXsxxDAO {

	/**
	 * 保存入伍信息
	 * 
	 * @throws Exception
	 */
	public boolean saveRwxx(RwxxModel model, HttpServletRequest request)
			throws Exception {

		String xh = model.getXh();
		String rwsj = model.getRwsj();
		String twsj = model.getTwsj();
		String fyqjbx = DealString.toGBK(model.getFyqjbx());

		boolean flg = StandardOperation.delete("zjlg_rwxx", "xh", xh, request);

		if (flg) {
			flg = StandardOperation.insert("zjlg_rwxx", new String[] { "xh",
					"rwsj", "twsj", "fyqjbx" }, new String[] { xh, rwsj, twsj,
					fyqjbx }, request);
		}

		return flg;
	}

	/**
	 * 获得入伍信息列表
	 */
	public ArrayList<String[]> getRwxxList(String tableName, RwxxModel model,
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
	 * 获得入伍信息
	 * 
	 */
	public HashMap<String, String> getRwxx(String pk) {
		String[] colList = new String[] { "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "rwsj", "twsj", "fyqjbx" };
		return commonQueryOne("view_zjlg_rwxx", colList, "pk", pk);
	}

	/**
	 * 删除入伍信息
	 */
	public boolean delRwxx(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from zjlg_rwxx where xh ='" + pk + "'";
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
