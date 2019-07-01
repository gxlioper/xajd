package xsgzgl.xszz.jhzy.gjzxj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.xszz.jhzy.cssz.XszzCsszActionForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 学生资助_国家助学金_金华职业_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */
public class GjzxjDAO extends CommDAO {

	// ==================执行查询操作begin =============================

	/**
	 * 获得国家助学金申请结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZxjsqList(GjzxjForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// 学年
		String xn = csszModel.getXn();

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.xn||'luojw'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm,a.shzt, ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm ");
		tableSql.append("from xg_xszz_jhzy_gjzxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where a.xn = '" + xn + "') a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xh ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shztmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 获得国家助学金审核结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZxjshList(GjzxjForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		XszzCsszActionForm csszModel = XszzCsszActionForm.getCsszForm();

		// 学年
		String xn = csszModel.getXn();

		// 审核
		String[] sh = myForm.getSearchModel().getSearch_tj_shzt1();
		myForm.getSearchModel().setSearch_tj_shzt1(null);

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql
				.append("select rownum r,a.xn||'luojw'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt,a.tjdc,a.knstjdc, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm, ");
		tableSql
				.append("(select d.xxtjdc from xg_xszz_jhzy_knssqb d where a.xh=d.xh and a.xn=d.xn) knstjdc,  ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm, ");
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.bzrsh shzt,a.bzrtjdc tjdc ");
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.fdysh shzt,a.fdytjdc tjdc ");
		} else if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.xysh shzt,a.xytjdc tjdc ");
		} else if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.xxsh shzt,a.xxtjdc tjdc ");
		}
		tableSql.append("from xg_xszz_jhzy_gjzxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where a.xn = '" + xn + "' ");
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='wsh' ");
			if (sh != null && sh.length > 0) {
				tableSql.append(" and a.bzrsh in( ");
				for (int i = 0; i < sh.length; i++) {
					if (i != 0) {
						tableSql.append(",");
					}
					tableSql.append("未审核".equalsIgnoreCase(sh[i]) ? "'wsh'"
							: "");
					tableSql.append("通过".equalsIgnoreCase(sh[i]) ? "'tg'" : "");
					tableSql.append("不通过".equalsIgnoreCase(sh[i]) ? "'btg'"
							: "");
				}
				tableSql.append(" ) ");
			}
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.bzrsh='tg' ");
			tableSql.append(" and a.xysh='wsh' ");
			if (sh != null && sh.length > 0) {
				tableSql.append(" and a.fdysh in( ");
				for (int i = 0; i < sh.length; i++) {
					if (i != 0) {
						tableSql.append(",");
					}
					tableSql.append("未审核".equalsIgnoreCase(sh[i]) ? "'wsh'"
							: "");
					tableSql.append("通过".equalsIgnoreCase(sh[i]) ? "'tg'" : "");
					tableSql.append("不通过".equalsIgnoreCase(sh[i]) ? "'btg'"
							: "");
				}
				tableSql.append(" ) ");
			}
		} else if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='tg' ");
			tableSql.append(" and a.xxsh='wsh' ");
			if (sh != null && sh.length > 0) {
				tableSql.append(" and a.xysh in( ");
				for (int i = 0; i < sh.length; i++) {
					if (i != 0) {
						tableSql.append(",");
					}
					tableSql.append("未审核".equalsIgnoreCase(sh[i]) ? "'wsh'"
							: "");
					tableSql.append("通过".equalsIgnoreCase(sh[i]) ? "'tg'" : "");
					tableSql.append("不通过".equalsIgnoreCase(sh[i]) ? "'btg'"
							: "");
				}
				tableSql.append(" ) ");
			}
		} else if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.xysh='tg' ");
			if (sh != null && sh.length > 0) {
				tableSql.append(" and a.xxsh in( ");
				for (int i = 0; i < sh.length; i++) {
					if (i != 0) {
						tableSql.append(",");
					}
					tableSql.append("未审核".equalsIgnoreCase(sh[i]) ? "'wsh'"
							: "");
					tableSql.append("通过".equalsIgnoreCase(sh[i]) ? "'tg'" : "");
					tableSql.append("不通过".equalsIgnoreCase(sh[i]) ? "'btg'"
							: "");
				}
				tableSql.append(" ) ");
			}
		}
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xh ) a ");
		tableSql.append(query);

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knstjdc", "sqsj", "shztmc", "tjdc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 获得国家助学金结果结果集
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getZxjjgList(GjzxjForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// query += searchTjByUser;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();

		tableSql.append("select rownum r,a.* from(");
		tableSql.append("select a.xn||'luojw'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt,a.tjdc,a.knstjdc, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm, ");
		tableSql
				.append("(select d.xxtjdc from xg_xszz_jhzy_knssqb d where a.xh=d.xh and a.xn=d.xn) knstjdc,  ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm, ");
		tableSql.append("a.xxsh shzt,a.xxtjdc tjdc ");
		tableSql.append("from xg_xszz_jhzy_gjzxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where 1=1 ");
		tableSql.append(" and a.shzt='tg' ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xh ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "knstjdc", "sqsj", "tjdc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 获得国家助学金申请学生基本信息
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getGjzxjInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh,a.xm,a.xb,a.xz,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.csrq, ");
		sql.append("b.sqly,b.sqsj,b.shzt, ");
		sql
				.append("(select c.xxtjdc from xg_xszz_jhzy_knssqb c where c.xn=? and a.xh=c.xh)knstjdc, ");
		sql.append("b.bzrshsj,b.bzrshyj,b.bzrshr,b.bzrtjdc, ");
		sql
				.append("case when length(b.bzrshyj)>=20 then substr(b.bzrshyj,0,20)||'...' ");
		sql.append("else b.bzrshyj end bzrshyjxs, ");
		sql.append("(select c.xm from yhb c where b.bzrshr = c.yhm) bzrxm,");
		sql.append("decode(b.bzrsh,'wsh','未审核','tg','通过','btg','不通过') bzrsh,");

		sql.append("b.fdyshsj,b.fdyshyj,b.fdyshr,b.fdytjdc, ");
		sql
				.append("case when length(b.fdyshyj)>=20 then substr(b.fdyshyj,0,20)||'...' ");
		sql.append("else b.fdyshyj end fdyshyjxs, ");
		sql.append("(select c.xm from yhb c where b.fdyshr = c.yhm) fdyxm,");
		sql.append("decode(b.fdysh,'wsh','未审核','tg','通过','btg','不通过') fdysh,");

		sql.append("b.xyshsj,b.xyshyj,b.xyshr,b.xytjdc, ");
		sql
				.append("case when length(b.xyshyj)>=20 then substr(b.xyshyj,0,20)||'...' ");
		sql.append("else b.xyshyj end xyshyjxs, ");
		sql.append("(select c.xm from yhb c where b.xyshr = c.yhm) xyxm,");
		sql.append("decode(b.xysh,'wsh','未审核','tg','通过','btg','不通过') xysh,");

		sql.append("b.xxshsj,b.xxshyj,b.xxshr,b.xxtjdc, ");
		sql
				.append("case when length(b.xxshyj)>=20 then substr(b.xxshyj,0,20)||'...' ");
		sql.append("else b.xxshyj end xxshyjxs, ");
		sql.append("(select c.xm from yhb c where b.xxshr = c.yhm) xxxm,");
		sql.append("decode(b.xxsh,'wsh','未审核','tg','通过','btg','不通过') xxsh ");

		sql.append("from view_xsxxb a ");
		sql.append("left join (");
		sql.append("select b.xh,b.sqly,b.sqsj,b.shzt, ");
		sql.append("b.bzrsh,b.bzrshsj,b.bzrshyj,b.bzrshr,b.bzrtjdc, ");
		sql.append("b.fdysh,b.fdyshsj,b.fdyshyj,b.fdyshr,b.fdytjdc, ");
		sql.append("b.xysh,b.xyshsj,b.xyshyj,b.xyshr,b.xytjdc, ");
		sql.append("b.xxsh,b.xxshsj,b.xxshyj,b.xxshr,b.xxtjdc ");
		sql.append("from xg_xszz_jhzy_gjzxjsqb b ");
		sql.append("where b.xn = ? ");
		sql.append(") b on a.xh = b.xh ");
		sql.append("where a.xh=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { xn, xn, xh });

		return map;
	}

	// ==================执行查询操作 end=============================

	// ==================执行增加操作 begin=============================
	/**
	 * 增加数据（xg_xszz_jhzy_gjzxjsqb:国家助学金申请表）
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertZxjsqb(String xh, String xn, String sqly, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjzxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xszz_jhzy_gjzxjsqb ");
		sql.append("(");
		sql.append("xh,xn,sqly");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { xh, xn, sqly };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行增加操作 end=============================

	// ==================执行修改操作 begin=============================
	/**
	 * 修改数据（xg_xszz_jhzy_gjzxjsqb:国家助学金申请表）
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZxjsqb(String xh, String xn, String sqly, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjzxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_jhzy_gjzxjsqb ");
		sql.append(" set sqly=? ");
		sql.append(" where xn=? and xh=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { sqly, xn, xh };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 修改数据（xg_xszz_jhzy_gjzxjsqb:国家助学金申请表）
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZxjsqb(String xh, String xn, String shzt, String shsj,
			String shyj, String tjdc, String userStatus, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjzxjsqb";

		String result = "";

		if ("btg".equalsIgnoreCase(shzt)) {
			result = "btg";
		} else if ("tg".equalsIgnoreCase(shzt)
				&& "xx".equalsIgnoreCase(userStatus)) {
			result = "tg";
		} else {
			result = "shz";
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_jhzy_gjzxjsqb ");
		sql.append(" set ");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" bzrsh=?, ");
			sql.append(" bzrshsj=?, ");
			sql.append(" bzrshyj=?, ");
			sql.append(" bzrshr=?, ");
			sql.append(" bzrtjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" fdysh=?, ");
			sql.append(" fdyshsj=?, ");
			sql.append(" fdyshyj=?, ");
			sql.append(" fdyshr=?, ");
			sql.append(" fdytjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" xysh=?, ");
			sql.append(" xyshsj=?, ");
			sql.append(" xyshyj=?, ");
			sql.append(" xyshr=?, ");
			sql.append(" xytjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("xx".equalsIgnoreCase(userStatus)) {
			sql.append(" xxsh=?, ");
			sql.append(" xxshsj=?, ");
			sql.append(" xxshyj=?, ");
			sql.append(" xxshr=?, ");
			sql.append(" xxtjdc=?, ");
			sql.append(" shzt=? ");
		}
		sql.append(" where xn=? and xh=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { shzt, shsj, shyj, user.getUserName(),
				tjdc, result, xn, xh };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 修改数据（xg_xszz_jhzy_gjzxjsqb:国家助学金申请表）
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updateZxjsqb(String[] pkValue, String shzt, String shsj,
			String shyj, String tjdc, String userStatus, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjzxjsqb";

		String result = "";

		if ("btg".equalsIgnoreCase(shzt)) {
			result = "btg";
		} else if ("tg".equalsIgnoreCase(shzt)
				&& "xx".equalsIgnoreCase(userStatus)) {
			result = "tg";
		} else {
			result = "shz";
		}

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_jhzy_gjzxjsqb ");
		sql.append(" set ");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" bzrsh=?, ");
			sql.append(" bzrshsj=?, ");
			sql.append(" bzrshyj=?, ");
			sql.append(" bzrshr=?, ");
			sql.append(" bzrtjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" fdysh=?, ");
			sql.append(" fdyshsj=?, ");
			sql.append(" fdyshyj=?, ");
			sql.append(" fdyshr=?, ");
			sql.append(" fdytjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" xysh=?, ");
			sql.append(" xyshsj=?, ");
			sql.append(" xyshyj=?, ");
			sql.append(" xyshr=?, ");
			sql.append(" xytjdc=?, ");
			sql.append(" shzt=? ");
		} else if ("xx".equalsIgnoreCase(userStatus)) {
			sql.append(" xxsh=?, ");
			sql.append(" xxshsj=?, ");
			sql.append(" xxshyj=?, ");
			sql.append(" xxshr=?, ");
			sql.append(" xxtjdc=?, ");
			sql.append(" shzt=? ");
		}

		sql.append(" where xn||'luojw'||xh = ?");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] value = new String[] { shzt, shsj, shyj,
					user.getUserName(), tjdc, result, pkValue[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行修改操作 end=============================

	// ==================执行删除操作 begin=============================
	/**
	 * 删除数据（xg_xszz_jhzy_gjzxjsqb:国家助学金申请表）
	 * 
	 * @date 2012-12-05
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteZxjsqb(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		// 表名
		String tableName = "xg_xszz_jhzy_gjzxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete xg_xszz_jhzy_gjzxjsqb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xn||'luojw'||xh=? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();

		params.add(inputV.toArray(new String[] {}));

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end=============================
}
