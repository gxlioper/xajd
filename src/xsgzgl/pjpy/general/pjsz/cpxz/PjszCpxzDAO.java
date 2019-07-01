package xsgzgl.pjpy.general.pjsz.cpxz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_参评小组_通用_DAO类
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

public class PjszCpxzDAO extends CommDAO {

	// ==================执行查询操作==============================
	/**
	 * 获得结果集(参评小组)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// 用户类型
		String yhlx = model.getYhlx();

		// ====================过滤条件===================================
		user.setUserStatus(yhlx);
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.bjdm pk,a.nj,a.xymc,");
		tableSql.append("a.zymc,a.bjmc,a.cpzmc ");
		tableSql.append("from (");
		tableSql.append("select a.nj,a.xydm,a.xymc,a.zydm, ");
		tableSql.append("a.zymc,a.bjdm,a.bjmc, ");
		tableSql.append("nvl(c.cpzmc,'未设置') cpzmc, ");
		tableSql.append("case when c.cpzmc is null then '否' else '是' end sfsz ");
		tableSql.append("from view_njxyzybj a left join xg_pjpy_cpzb c ");
		tableSql.append("on a.bjdm = c.bjdm ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.nj desc,a.xydm,a.zydm,a.bjdm ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"cpzmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}

	/**
	 * 获得未维护参评组的班级数
	 * 
	 * @author 伟大的骆
	 */
	public String getNoCpzNum(PjszCpxzModel model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num ");
		sql.append("from view_njxyzybj a ");
		sql.append("where not exists (select 1 from ");
		sql.append("xg_pjpy_cpzb b where a.bjdm = b.bjdm) ");

		String num = dao.getOneRs(sql.toString(), new String[] {}, "num");

		return num;
	}
	// ==================执行查询操作 end==============================

	// ==================执行新增操作 begin=============================
	/**
	 * 新增数据（xg_pjpy_cpzb:参评组表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertCpzb(String[] bjdm, String cpzmc, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb ");
		sql.append("(bjdm,cpzmc)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i], cpzmc };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * 新增数据（xg_pjpy_cpzb:参评组表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertCpzb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String cpzmc, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb ");
		sql.append("(bjdm,cpzmc)");
		sql.append("select bjdm,'" + cpzmc + "' cpzmc ");
		sql.append("from view_njxyzybj b ");
		sql.append("where 1=? ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	

	/**
	 * 新增数据（xg_pjpy_cpzb:参评组表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean insertCpzb(String cpzmc, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_cpzb (cpzmc, bjdm) ");
		sql.append("select " + cpzmc + ", bjdm ");
		sql.append("from view_njxyzybj a ");
		sql.append("where not exists (select 1 from xg_pjpy_cpzb b ");
		sql.append("where a.bjdm = b.bjdm) ");
		sql.append("and 1=? ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行新增操作 end==============================
	
	// ==================执行删除操作 begin =============================
	/**
	 * 删除数据（xg_pjpy_cpzb:参评组表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteCpzb(String[] bjdm, User user) throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_cpzb ");
		sql.append("where 1=1 ");
		sql.append("and bjdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * 删除数据（xg_pjpy_cpzb:参评组表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteCpzb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_cpzb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_cpzb a ");
		sql.append("where 1=? ");
		sql.append("and exists( ");
		sql.append("select 1 from view_njxyzybj b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		sql.append(") ");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end =============================	
}
