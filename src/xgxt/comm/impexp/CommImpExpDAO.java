package xgxt.comm.impexp;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.CommForm;

public class CommImpExpDAO extends CommDAO {

	/**
	 * 获得评奖评优导出模板表头
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getPjpyExpTrList(CommForm model, HashMap<String, String> map) {

		DAO dao = new DAO();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.zd en,t.mc cn from pjpy_zctjszb t ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.dmjb = '4'");
		sql.append(" and t.sfwh = '1'");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "en", "cn" });
		
		return list;

	}
	
	/**
	 * 获得导入表等信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getImpInfoList(String[] bt) {

		DAO dao = new DAO();

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.mc,t.bm,t.zd,t.zj from pjpy_zctjszb t ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.dmjb = '4'");
		sql.append(" and t.sfwh = '1'");
		if (bt != null && bt.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < bt.length; i++) {
				if (i == 0) {
					sql.append(" t.mc = ? ");
				} else {
					sql.append(" or t.mc = ? ");
				}
			}
			sql.append(" )");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(), bt,
				new String[] { "mc","bm", "zd", "zj" });

		return list;
	}
	
	/**
	 * 获得导入表等信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getImpInfoListForNtzy(String[] bt) {

		DAO dao = new DAO();

		StringBuilder sql = new StringBuilder();
		sql.append(" select t.mc,'pjpy_zhszcpb' bm,t.zd,t.dmjb jb,t.dm from pjpy_zctjszb t ");
		sql.append(" where 1 = 1 ");
		sql.append(" and t.dmjb = '4'");
		sql.append(" and t.sfwh = '1'");
		if (bt != null && bt.length > 0) {
			sql.append(" and (");
			for (int i = 0; i < bt.length; i++) {
				if (i == 0) {
					sql.append(" t.mc = ? ");
				} else {
					sql.append(" or t.mc = ? ");
				}
			}
			sql.append(" )");
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(), bt,
				new String[] { "mc","bm", "zd","jb", "dm" });

		return list;
	}
	
	
	/**
	 * 获得存在的学生序号
	 * 
	 * @author luojw
	 * @throws SQLException
	 */
	public List<String> getExistXhList(List<HashMap<String, String>> list)
			throws SQLException {

		DAO dao = DAO.getInstance();

		List<String> xhList = null;

		if (list != null && list.size() > 0) {
			StringBuilder sql = new StringBuilder();
			sql.append(" select xh from view_xsjbxx where ");
			for (int i = 0; i < list.size(); i++) {
				String xh = list.get(i).get("学号");
				if (i == 0) {
					sql.append(" xh = '" + xh + "' ");
				} else {
					sql.append(" or xh = '" + xh + "' ");
				}
			}
			xhList = dao.getList(sql.toString(), new String[] {}, "xh");
		}

		return xhList;
	}
}
