package xgxt.pjpy.zjcm.cpz;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.pjpy.zjcm.ZhszcpDAO;

public class CpzDAO extends ZhszcpDAO {

	/**
	 * @describe 获得学生职务列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getZwList(String xn, String xq,
			String xydm) {
		DAO dao = new DAO();
		StringBuffer sql = new StringBuffer("select a.bjgbdm, a.bjgbmc from sxjy_bjgbzlb a ");
		if (!Base.isNull(xn)&&!Base.isNull(xq) && !Base.isNull(xydm)) {
			sql.append("where not exists (select 1 from zjcm_cpz b where a.bjgbdm = b.zwdm ");
			sql.append("and xn='" + xn +"' and xq='"+xq+ "' and xydm='" + xydm + "') ");
		}
		sql.append("order by bjgbdm");
		List<HashMap<String, String>> zwList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "bjgbdm", "bjgbmc" });
		return zwList;
	}

	/**
	 * @describe 获得学生职务列表
	 * @author luo
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCpZwList(String xn, String xq,
			String xydm) {
		DAO dao = new DAO();
		String sql = "select b.bjgbdm,b.bjgbmc from zjcm_cpz a, sxjy_bjgbzlb b where a.zwdm = b.bjgbdm and a.xn=? and a.xq=? and a.xydm=?";
		List<HashMap<String, String>> zwList = dao.getList(sql, new String[] {
				xn, xq, xydm }, new String[] { "bjgbdm", "bjgbmc" });
		return zwList;
	}

	/**
	 * @describe 获得学生职务列表
	 * @author luo
	 * @throws Exception
	 */
	public HashMap<String, String> getCpzSj(String xn, String xq, String xydm) {
		DAO dao = new DAO();
		String sql = "select distinct(lrkssj),lrjssj,sbkssj,sbjssj from zjcm_cpz where xn=? and xq=? and xydm=?";
		HashMap<String, String> map = dao
				.getMap(sql, new String[] { xn, xq, xydm }, new String[] {
						"lrkssj", "lrjssj", "sbkssj", "sbjssj" });
		return map.size() > 0 ? map : null;
	}

	/**
	 * @author luo
	 * @throws SQLException
	 * @describe 保存测评组
	 */
	public boolean saveCpz(CpzModel model) throws SQLException {

		DAO dao = new DAO();

		String xn = model.getXn();
		String xq = model.getXq();
		String xydm = model.getXydm();
		String lrkssj = model.getLrkssj();
		String lrjssj = model.getLrjssj();
		String sbkssj = model.getSbkssj();
		String sbjssj = model.getSbjssj();
		String[] zw = model.getZw();

		boolean flg = false;
		String sql = "";
		String pk = xn + xq + xydm;
		StringBuffer sb = new StringBuffer();

		if (zw != null && zw.length > 0) {
			sql = "delete from zjcm_cpz where xn||xq||xydm='" + pk + "'";
			sb.append(sql);
			sb.append("!!#!!");

			for (int i = 0; i < zw.length; i++) {

				sql = "insert into zjcm_cpz(xn,xq,xydm,zwdm,lrkssj,lrjssj,sbkssj,sbjssj) values('"
						+ xn
						+ "','"
						+ xq
						+ "','"
						+ xydm
						+ "','"
						+ zw[i]
						+ "','"
						+ lrkssj
						+ "','"
						+ lrjssj
						+ "','"
						+ sbkssj
						+ "','" + sbjssj + "')";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		String[] actsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}

	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}
}
