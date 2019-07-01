package xsgzgl.pjpy.general.pjsz.bjdl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
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
 * Description: 评奖评优_评奖设置_班级大类_通用_DAO类
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

public class PjszBjdlDAO extends CommDAO {

	// ==================执行查询操作==============================
	/**
	 * 获得结果集(班级大类)
	 * 
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		//query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select distinct a.bjdm pk,a.nj,a.xydm,a.xymc,a.zydm,");
		tableSql.append("a.zymc,a.bjmc,a.bjdlmc ");
		tableSql.append("from (");
		tableSql.append("select a.nj,a.xydm,a.xymc,a.zydm, ");
		tableSql.append("a.zymc,a.bjdm,a.bjmc, ");
		tableSql.append("nvl(c.bjdlmc,'未设置') bjdlmc, ");
		tableSql.append("case when bjdlmc is null then '否' else '是' end sfsz ");
		tableSql.append("from view_njxyzybj a ");
		tableSql.append("left join (select c.bjdm, ");
		tableSql.append("(select d.bjdlmc from xg_pjpy_bjdldmb d where c.bjdlmc = d.bjdldm) bjdlmc ");
		tableSql.append("from xg_pjpy_bjdlb c) c ");
		tableSql.append("on a.bjdm = c.bjdm ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" ) a ");
		tableSql.append(query);
		tableSql.append(" order by a.nj desc,a.xydm,a.zydm,a.bjdm ) a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc",
				"bjdlmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(model.getPages(), tableSql.toString(), inputV,
						colList);

		return list;
	}
	
	/**
	 * 查询班级大类列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBjdlList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select bjdldm dm ");
		sql.append(",bjdlmc mc ");
		sql.append("from xg_pjpy_bjdldmb ");
		sql.append("order by bjdldm ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}
	
	/**
	 * 查询某学生的班级大类
	 * 
	 * @author 伟大的骆
	 */
	public String getBjdl(String xh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select bjdlmc bjdl from xg_pjpy_bjdlb a ");
		sql.append("where exists (select 1 ");
		sql.append("from xg_pjpy_pjrykb b ");
		sql.append("where b.xh = ? ");
		sql.append("and a.bjdm = b.bjdm) ");

		String bjdl = dao.getOneRs(sql.toString(), new String[] { xh }, "bjdl");

		return bjdl;
	}
	// ==================执行查询操作 end =============================	

	// ==================执行新增操作 =============================
	/**
	 * 新增数据（xg_pjpy_bjdlb:班级大类表）
	 * 
	 * @table 班级大类表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertBjdlb(String[] bjdm, String bjdlmc, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_bjdlb ");
		sql.append("(bjdm,bjdlmc)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (bjdm != null && bjdm.length > 0) {
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { bjdm[i], bjdlmc };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * 新增数据（xg_pjpy_bjdlb:班级大类表）
	 * 
	 * @table 班级大类表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertBjdlb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, String bjdlmc, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_bjdlb ");
		sql.append("(bjdm,bjdlmc)");
		sql.append("select bjdm,'" + bjdlmc + "' bjdlmc ");
		sql.append("from view_njxyzybj b ");
		sql.append("where 1=? ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		
		return flag;
	}
	// ==================执行新增操作 end==============================
	
	// ==================执行删除操作 =============================
	/**
	 * 删除数据（xg_pjpy_bjdlb:班级大类表）
	 * 
	 * @table 班级大类表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteBjdlb(String[] bjdm, User user) throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_bjdlb ");
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
	 * 删除数据（xg_pjpy_bjdlb:班级大类表）
	 * 
	 * @table 参评组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteBjdlb(String[] nj, String[] xydm, String[] zydm,
			String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_bjdlb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_bjdlb a ");
		sql.append("where 1=? ");
		sql.append("and exists( ");
		sql.append("select 1 from view_njxyzybj b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append(getBmxxQuery(nj, xydm, zydm, bjdm, "b"));
		sql.append(") ");
		
		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { "1" });
		System.out.println(sql);
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end=============================
}
