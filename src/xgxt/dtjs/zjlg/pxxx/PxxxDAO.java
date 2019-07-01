package xgxt.dtjs.zjlg.pxxx;

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
import xgxt.utils.MakeQuery;

public class PxxxDAO extends ZjlgDtjsDAO {

	/**
	 * 保存对外交流转接
	 * 
	 * @throws Exception
	 */
	public boolean savePxxx(PxxxModel model, HttpServletRequest request)
			throws Exception {

		String nd = model.getNd();
		String xh = model.getXh();
		String xn = model.getXn();
		String xq = model.getXq();
		String pxxmdm = model.getPxxmdm();
		String pxkssj = model.getPxkssj();
		String pxjssj = model.getPxjssj();
		String pxjg = DealString.toGBK(model.getPxjg());
		String bz = DealString.toGBK(model.getBz());
		String pk = xn + xq + xh + pxxmdm;

		boolean flg = StandardOperation.delete("xspxxxb", "xn||xq||xh||pxxmdm",
				pk, request);

		if (flg) {
			flg = StandardOperation.insert("xspxxxb", new String[] { "nd",
					"xh", "xn", "xq", "pxxmdm", "pxkssj", "pxjssj", "pxjg",
					"bz" }, new String[] { nd, xh, xn, xq, pxxmdm, pxkssj,
					pxjssj, pxjg, bz }, request);
		}

		return flg;
	}

	/**
	 * 获得培训信息列表
	 */
	public ArrayList<String[]> getPxxxList(String tableName, PxxxModel model,
			String[] colList, HashMap<String, String> map)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String[] queryList = new String[] { "xn", "xq", "xydm", "zydm", "bjdm",
				"nj", "pxxmdm", "zbdm" };
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
	 * 获得培训信息
	 * 
	 */
	public HashMap<String, String> getPxxxInfo(String pk) {
		String[] colList = new String[] { "nd", "xh", "xm", "xb", "nj", "xn",
				"xq", "xymc", "zymc", "bjmc", "pxxmdm", "pxkssj", "pxjssj",
				"pxjg", "bz", "zbmc" };
		return commonQueryOne("view_xspxxx", colList, "xn||xq||xh||pxxmdm", pk);
	}

	/**
	 * 删除培训信息
	 */
	public boolean delPxxx(String[] key) throws Exception {

		DAO dao = new DAO();
		boolean flg = false;

		String sql = "";
		String pk = "";
		String[] delsql = new String[key.length];
		for (int i = 0; i < key.length; i++) {
			pk = key[i];
			sql = "delete from xspxxxb where xn||xq||xh||pxxmdm ='" + pk + "'";
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
	 * 获得培训项目
	 */
	public List<HashMap<String, String>> getPxxm() {
		return commonQueryforList("pxxmdmb", "", new String[] {},
				new String[] { "pxxmdm", "pxxmmc" }, "");
	}
}
