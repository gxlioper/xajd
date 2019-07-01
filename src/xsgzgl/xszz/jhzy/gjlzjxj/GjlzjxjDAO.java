package xsgzgl.xszz.jhzy.gjlzjxj;

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
 * Description: 学生资助_国家励志奖学金_金华职业_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class GjlzjxjDAO extends CommDAO {

	// ==================执行查询操作begin =============================
	/**
	 * 获得国家励志奖学金申请结果集
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getGjlzjxjsq(GjlzjxjForm myForm, User user)
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
		tableSql.append("select a.xn||'!!luojw!!'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc," +
						"(select xxtjdc from xg_xszz_jhzy_knssqb b where a.xh=b.xh and a.xn=b.xn and b.shzt='tg') rddc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm,a.shzt, ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm ");
		tableSql.append("from xg_xszz_jhzy_gjlzjxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where a.xn = '" + xn + "') a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xn,a.xydm,a.zydm,a.bjdm,a.sqsj ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc","rddc", "sqsj", "shztmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 获得国家励志奖学金审核结果集
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getGjlzjxjshList(GjlzjxjForm myForm, User user)
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
		tableSql.append("select a.xn||'!!luojw!!'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt, ");
		tableSql.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc," +
				"(select xxtjdc from xg_xszz_jhzy_knssqb b where a.xh=b.xh and a.xn=b.xn and b.shzt='tg') rddc  ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm, ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm, ");
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.bzrsh shzt ");
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.fdysh shzt ");
		} else if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.xysh shzt ");
		} else if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append("a.xxsh shzt ");
		}
		tableSql.append("from xg_xszz_jhzy_gjlzjxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where a.xn = '" + xn + "' ");
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='wsh' ");
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.bzrsh='tg' ");
			tableSql.append(" and a.xysh='wsh' ");
		} else if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='tg' ");
			tableSql.append(" and a.xxsh='wsh' ");
		}else if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.xysh='tg' ");
		}
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xn,a.xydm,a.zydm,a.bjdm,a.sqsj ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "rddc","sqsj", "shztmc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	/**
	 * 获得国家励志奖学金类别列表
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public List<HashMap<String, String>> getKnslbList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.dm,a.mc ");
		sql.append("from xg_xszz_jhzt_knslbb a ");
		sql.append("where 1=1 ");
		sql.append("order by to_number(a.dm)");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "dm", "mc" });

		return list;
	}

	/**
	 * 获得国家励志奖学金申请学生基本信息
	 * 
	 * @date 2012-12-04
	 * @author lt
	 */
	public HashMap<String, String> getGjlzjxjInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select (select count(*) from view_xsjbxx b where a.bjdm=b.bjdm) bjzrs,a.xh,a.xm,a.xb,a.xz,a.nj,a.xymc,a.zymc,");
		sql.append("a.bjmc,a.mzmc,a.zzmmmc,a.sfzh,a.csrq, ");
		sql.append("b.sqly,b.sqsj,b.xn,b.bxkms,b.jgms,b.cjpm,b.sxzhkppm,b.zhkppm,b.sqly," +
				"b.hjsj1,b.hjmc1,b.bjdw1,b.hjsj2,b.hjmc2,b.bjdw2,b.hjsj3,b.hjmc3,b.bjdw3,b.hjsj4,b.hjmc4,b.bjdw4,b.shzt, ");
		sql.append("b.bzrshsj,b.bzrshyj,b.bzrshr, ");
		sql.append("(select c.xm from yhb c where b.bzrshr = c.yhm) bzrxm,");
		sql.append("decode(b.bzrsh,'wsh','未审核','tg','通过','btg','不通过') bzrsh,");

		sql.append("b.fdyshsj,b.fdyshyj,b.fdyshr, ");
		sql.append("(select c.xm from yhb c where b.fdyshr = c.yhm) fdyxm,");
		sql.append("decode(b.fdysh,'wsh','未审核','tg','通过','btg','不通过') fdysh,");

		sql.append("b.xyshsj,b.xyshyj,b.xyshr, ");
		sql.append("(select c.xm from yhb c where b.xyshr = c.yhm) xyxm,");
		sql.append("decode(b.xysh,'wsh','未审核','tg','通过','btg','不通过') xysh,");

		sql.append("b.xxshsj,b.xxshyj,b.xxshr, ");
		sql.append("(select c.xm from yhb c where b.xxshr = c.yhm) xxxm,");
		sql.append("decode(b.xxsh,'wsh','未审核','tg','通过','btg','不通过') xxsh ");

		sql.append("from view_xsxxb a ");
		sql.append("left join (");
		sql.append("select b.* from xg_xszz_jhzy_gjlzjxjsqb b ");
		sql.append("where b.xn = ? ");
		sql.append(") b on a.xh = b.xh ");
		sql.append("where a.xh=? ");

		HashMap<String, String> map = dao.getMapNotOut(sql.toString(),
				new String[] { xn, xh });

		return map;
	}

	// ==================执行查询操作 end=============================

	// ==================执行增加操作 begin=============================
	/**
	 * 增加数据（xg_xszz_jhzy_knssqb:国家励志奖学金申请表）
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean insertGjlzjxjsqb(List<String[]> params,
			User user) throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjlzjxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_xszz_jhzy_gjlzjxjsqb ");
		sql.append("(");
		sql.append("sqsj,bxkms,jgms,cjpm,sxzhkppm,zhkppm,sqly,hjsj1,hjmc1,bjdw1,hjsj2,hjmc2,bjdw2,hjsj3,hjmc3,bjdw3,hjsj4,hjmc4,bjdw4,xh,xn");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");


		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行增加操作 end=============================

	// ==================执行修改操作 begin=============================
	/**
	 * 修改数据（xg_xszz_jhzy_knssqb:国家励志奖学金申请表）
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean updateGjlzjxjsqb(List<String[]> params,
			User user) throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjlzjxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_xszz_jhzy_gjlzjxjsqb ");
		sql.append(" set sqsj=?,bxkms=?,jgms=?,cjpm=?,sxzhkppm=?,zhkppm=?,sqly=?,hjsj1=?,hjmc1=?,bjdw1=?,hjsj2=?,hjmc2=?,bjdw2=?,hjsj3=?,hjmc3=?,bjdw3=?,hjsj4=?,hjmc4=?,bjdw4=? ");
		sql.append(" where xh=? and xn=? ");

		
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行修改操作 end=============================

	// ==================执行删除操作 begin=============================
	/**
	 * 删除数据（xg_xszz_jhzy_knssqb:国家励志奖学金申请表）
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean deleteGjlzjxjsqb(String[] pkValue, User user) throws Exception {

		List<String> inputV = new ArrayList<String>();

		// 表名
		String tableName = "xg_xszz_jhzy_gjlzjxjsqb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete xg_xszz_jhzy_gjlzjxjsqb ");
		sql.append(" where  1=1 and(  ");

		for (int i = 0; i < pkValue.length; i++) {
			if (i != 0) {
				sql.append(" or ");
			}
			sql.append(" xn||'!!luojw!!'||xh=? ");
			inputV.add(pkValue[i]);
		}
		sql.append(")");

		List<String[]> params = new ArrayList<String[]>();

		params.add(inputV.toArray(new String[] {}));

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end=============================
	
	public String cxBjrs(String xh) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(1) cnt from view_xsjbxx where bjdm = (select bjdm from view_xsjbxx where xh=?)", new String[]{xh}, "cnt");
	}
	
	/**
	 * 判断学生是否国家励志奖学金
	 * @param xh
	 * @param xn
	 * @return
	 */
	public String isKns(String xh, String xn) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select count(1) cnt from xg_xszz_jhzy_knssqb where shzt='tg' and xh=? and xn=?", 
				new String[]{xh,xn}, "cnt");
	}
	
	/**
	 * 获得国家励志奖学金审核结果集
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getKnsshList(GjlzjxjForm myForm, User user)
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
		tableSql.append("select a.xn||'!!luojw!!'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt,a.tjdc, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm, ");
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
		tableSql.append("from xg_xszz_jhzy_knssqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where a.xn = '" + xn + "' ");
		if ("bzr".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='wsh' ");
		} else if ("fdy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.bzrsh='tg' ");
			tableSql.append(" and a.xysh='wsh' ");
		} else if ("xy".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.fdysh='tg' ");
			tableSql.append(" and a.xxsh='wsh' ");
		} else if ("xx".equalsIgnoreCase(user.getUserStatus())) {
			tableSql.append(" and a.xysh='tg' ");
		}
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xh ) a ");
		tableSql.append(query);
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc", "sqsj", "shztmc", "tjdc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), tableSql.toString(),
						inputV, colList);

		return list;
	}

	
	
	/**
	 * 修改数据（xg_xszz_jhzy_knssqb:国家励志奖学金申请表）
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean updateGjlzjxjsh(String xh, String xn, String shzt, String shsj,
			String shyj, String userStatus, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjlzjxjsqb";

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
		sql.append(" update xg_xszz_jhzy_gjlzjxjsqb ");
		sql.append(" set ");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" bzrsh=?, ");
			sql.append(" bzrshsj=?, ");
			sql.append(" bzrshyj=?, ");
			sql.append(" bzrshr=?, ");
			sql.append(" shzt=? ");
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" fdysh=?, ");
			sql.append(" fdyshsj=?, ");
			sql.append(" fdyshyj=?, ");
			sql.append(" fdyshr=?, ");
			sql.append(" shzt=? ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" xysh=?, ");
			sql.append(" xyshsj=?, ");
			sql.append(" xyshyj=?, ");
			sql.append(" xyshr=?, ");
			sql.append(" shzt=? ");
		} else if ("xx".equalsIgnoreCase(userStatus)) {
			sql.append(" xxsh=?, ");
			sql.append(" xxshsj=?, ");
			sql.append(" xxshyj=?, ");
			sql.append(" xxshr=?, ");
			sql.append(" shzt=? ");
		}
		sql.append(" where xn=? and xh=? ");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { shzt, shsj, shyj, user.getUserName(),
				 result, xn, xh };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 修改数据（xg_xszz_jhzy_knssqb:国家励志奖学金申请表）
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws Exception
	 */
	public Boolean updateGjlzjxjsh(String[] pkValue, String shzt, String shsj,
			String shyj, String userStatus, User user)
			throws Exception {

		// 表名
		String tableName = "xg_xszz_jhzy_gjlzjxjsqb";

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
		sql.append(" update xg_xszz_jhzy_gjlzjxjsqb ");
		sql.append(" set ");
		if ("bzr".equalsIgnoreCase(userStatus)) {
			sql.append(" bzrsh=?, ");
			sql.append(" bzrshsj=?, ");
			sql.append(" bzrshyj=?, ");
			sql.append(" bzrshr=?, ");
			sql.append(" shzt=? ");
		} else if ("fdy".equalsIgnoreCase(userStatus)) {
			sql.append(" fdysh=?, ");
			sql.append(" fdyshsj=?, ");
			sql.append(" fdyshyj=?, ");
			sql.append(" fdyshr=?, ");
			sql.append(" shzt=? ");
		} else if ("xy".equalsIgnoreCase(userStatus)) {
			sql.append(" xysh=?, ");
			sql.append(" xyshsj=?, ");
			sql.append(" xyshyj=?, ");
			sql.append(" xyshr=?, ");
			sql.append(" shzt=? ");
		} else if ("xx".equalsIgnoreCase(userStatus)) {
			sql.append(" xxsh=?, ");
			sql.append(" xxshsj=?, ");
			sql.append(" xxshyj=?, ");
			sql.append(" xxshr=?, ");
			sql.append(" shzt=? ");
		}

		sql.append(" where xn||'!!luojw!!'||xh = ?");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < pkValue.length; i++) {
			String[] value = new String[] { shzt, shsj, shyj,
					user.getUserName(), result, pkValue[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	
	/**
	 * 获得国家励志奖学金结果结果集
	 * 
	 * @date 2012-12-04
	 * @author lt
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getGjlzjxjjgList(GjlzjxjForm myForm, User user)
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

		tableSql.append("select rownum r,a.xn||'luojw'||a.xh pk,a.xn,a.xh,a.xm,");
		tableSql.append("a.nj,a.xymc,a.bjmc,a.sqsj,a.shzt,a.tjdc,a.jxmc, ");
		tableSql
				.append("decode(a.shzt,'wsh','未审核','tg','通过','btg','不通过','审核中') shztmc ");
		tableSql.append("from (");
		tableSql.append("select * from (select a.xn,a.xh,b.xm, ");
		tableSql.append("b.nj,b.bjmc,a.sqsj,b.xydm,b.xymc,b.zydm,b.bjdm, ");
		tableSql.append("a.xxsh shzt,(select xxtjdc from xg_xszz_jhzy_knssqb c where a.xh=c.xh and a.xn=c.xn) tjdc,'国家励志奖学金' jxmc ");
		tableSql.append("from xg_xszz_jhzy_gjlzjxjsqb a ");
		tableSql.append("left join view_xsjbxx b ");
		tableSql.append("on a.xh=b.xh ");
		tableSql.append("where 1=1 ");
		tableSql.append(" and a.shzt='tg' ");
		tableSql.append(") a ");
		tableSql.append("where 1=1 ");
		tableSql.append(searchTjByUser);
		tableSql.append(" order by a.xn,a.xydm,a.zydm,a.bjdm,a.sqsj ) a ");
		tableSql.append(query);

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "xn", "xh", "xm", "nj", "xymc",
				"bjmc",  "tjdc" ,"sqsj","jxmc"};

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
				inputV, colList);

		return list;
	}
}
