package xsgzgl.pjpy.general.xmsz.pjtj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XmszPjtjDAO extends CommDAO {

	// ================执行查询操作===========================
	/**
	 * 获得评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjtjList(String xmdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjfw,a.gx,a.tjz,b.tjz pjtjz, ");
		sql.append("b.tjlx,b.tablename,b.zd,b.tsgs, ");
		sql.append("b.condition,b.xn,b.xq,b.nd,b.bzd, ");
		sql.append("'' tjms ");
		sql.append("from xg_pjpy_tjszb a,xg_pjpy_pjtjkb b ");
		sql.append("where a.tjdm = b.tjdm ");
		sql.append("and a.xmdm = ? ");
		sql.append("and b.sfqy = 'yes' ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "tjdm", "tjfw", "tjms",
						"gx", "tjz", "tjlx", "tablename", "zd", "tsgs",
						"condition", "xn", "xq", "nd", "bzd", "pjtjz" });

		return list;
	}
	
	/**
	 * 获得评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjtjList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjmc ");
		sql.append("from xg_pjpy_pjtjkb a ");
		sql.append("where 1=1 ");
		sql.append("and a.sfqy = 'yes' ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "tjdm", "tjmc" });

		return list;
	}
	
	/**
	 * 获得评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getPjtjInfo(String tjdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tsgs,a.tjz ");
		sql.append("from xg_pjpy_pjtjkb a ");
		sql.append("where 1=1 ");
		sql.append("and a.tjdm = ? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { tjdm });

		return map;
	}
	
	/**
	 * 获得比较值
	 * 
	 * @param xh
	 * @param map
	 * @return
	 */
	public List<HashMap<String, String>> getBjz(String xh[],
			Map<String, String> map, String lx) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 限制条件
		String condition = map.get("condition");
		// 学年限制
		String xn = map.get("xn");
		// 学期限制
		String xq = map.get("xq");
		// 年度限制
		String nd = map.get("nd");
		// 表字段
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 当前学年
		String dqxn = Base.currXn;
		// 当前学期
		String dqxq = Base.currXq;
		// 当前年度
		String dqnd = Base.currNd;

		// 过滤输入值
		ArrayList<String> inputV = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();

		for (int i = 0; i < xh.length; i++) {
			if (i != 0) {
				sql.append(" union all ");
			}
			sql.append(" select a.xh,nvl(b.bjz,0) bjz from ");
			sql.append(" (select '" + xh[i] + "' xh from dual) a left join ");
			sql.append(" (");
			sql.append(" select ");

			// 类型非空
			if (!Base.isNull(lx)) {
				sql.append(lx);
				sql.append("(");
				sql.append(zd);
				sql.append(")");
			} else {
				sql.append(zd);
			}
			sql.append(" bjz from ");
			sql.append(tablename);
			sql.append(" a ");
			sql.append(" where 1 = 1 ");
			sql.append(" and xh = '" + xh[i] + "' ");

			// 限制条件
			sql.append(Base.isNull(condition) ? "" : condition);

			// 需要控制学年
			if ("pjxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = '" + pjxn + "' ");
			} else if ("dqxn".equalsIgnoreCase(xn)) {
				sql.append(" and " + bzd + "xn = '" + dqxn + "' ");
			}

			// 需要控制学期
			if ("pjxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = '" + pjxq + "' ");
			} else if ("dqxq".equalsIgnoreCase(xq)) {
				sql.append(" and " + bzd + "xq = '" + dqxq + "' ");
			}

			// 需要控制年度
			if ("pjnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = '" + pjnd + "' ");
			} else if ("dqnd".equalsIgnoreCase(nd)) {
				sql.append(" and " + bzd + "nd = '" + dqnd + "' ");
			}

			sql.append(" ) b on 1=1");
		}

		DAO dao = DAO.getInstance();

		// 比较值
		List<HashMap<String, String>> list = dao.getList(sql.toString(), inputV
				.toArray(new String[] {}), new String[] { "xh", "bjz" });

		return list;
	}
	// ================执行查询操作 end===========================
	
	// ==================执行新增操作 =============================
	/**
	 * 新增数据（xg_pjpy_tjszb:条件设置表）
	 * 
	 * @table 班级大类表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertTjszb(String xmdm, String[] tjdm, String[] tjfw,
			String[] gx, String[] tjz, User user) throws Exception {

		boolean flag = false;
		// 表名
		String tableName = "xg_pjpy_tjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_tjszb ");
		sql.append("(xmdm,tjdm,tjfw,gx,tjz)");
		sql.append("values");
		sql.append("(?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (tjdm != null && tjdm.length > 0) {
			for (int i = 0; i < tjdm.length; i++) {
				String[] value = new String[] { xmdm, tjdm[i], tjfw[i], gx[i],
						tjz[i], };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================执行新增操作 end==============================
	
	// ==================执行删除操作 =============================
	/**
	 * 删除数据（xg_pjpy_tjszb:条件设置表）
	 * 
	 * @table 条件设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteTjszb(String xmdm, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_tjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_tjszb ");
		sql.append("where 1=1 ");
		sql.append("and xmdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xmdm };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end=============================
}
