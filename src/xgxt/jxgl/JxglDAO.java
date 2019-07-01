package xgxt.jxgl;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.jxglxxwh.JxglwhForm;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息军训管理通用DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 鲁宁
 * @version 1.0
 */

public class JxglDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();

	public ArrayList<String[]> sxjyQuery(String tableName, String query,
			String[] inPutList, String[] colList, String sql) {
		// DAO dao = DAO.getInstance();
		// 查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		// 取到colList的长度
		int size = colList.length - 1;
		if (sql.equalsIgnoreCase("")) {
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for (int i = 0; i < (size); i++) {
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(query);
			rs = rsToVator(sqlBuffer.toString(), inPutList, colList);
		} else {
			rs = rsToVator(sql + query, inPutList, colList);
		}
		return rs;
	}

	public HashMap<String, String[]> sxjyQueryForHashMap(String tableName,
			String query, String[] inPutList, String[] colList, String sql) {
		// DAO dao = DAO.getInstance();
		// 查询用 获得HashMap<String, String[]>形式的通用方法
		HashMap<String, String[]> rs = new HashMap<String, String[]>();
		// 取到colList的长度
		int size = colList.length - 1;
		if (sql.equalsIgnoreCase("")) {
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for (int i = 0; i < (size); i++) {
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(query);
			rs = getHashMapList(sqlBuffer.toString(), inPutList, colList);
		} else {
			rs = getHashMapList(sql, inPutList, colList);
		}
		return rs;
	}

	public List<HashMap<String, String>> sxjyQueryforList(String tableName,
			String query, String[] inPutList, String[] colList, String sql) {
		// DAO dao = DAO.getInstance();
		// 查询用 获得List<HashMap<String, String>>形式的通用方法
		List<HashMap<String, String>> rs;
		int size = colList.length - 1;
		// 取到colList的长度
		if (sql.equalsIgnoreCase("")) {
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for (int i = 0; i < (size); i++) {
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(query);
			rs = getList(sqlBuffer.toString(), inPutList, colList);
		} else {
			rs = getList(sql, inPutList, colList);
		}
		return rs;
	}

	public HashMap<String, String> sxjyQueryOne(String tableName,
			String[] colList, String pk, String pkValue) {
		// 通过主键查询单个数据用 获得HashMap<String, String>形式的通用方法
		// DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		int size = colList.length - 1;
		if (pkValue.equalsIgnoreCase("")) {
			for (int i = 0; i < size; i++) {
				map.put(colList[i], "");
			}
		} else {
			StringBuffer sqlBuffer = new StringBuffer("select ");
			for (int i = 0; i < size; i++) {
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(tableName);
			sqlBuffer.append(" where ");
			sqlBuffer.append(pk);
			sqlBuffer.append("=?");
			String[] rsTmp = getOneRs(sqlBuffer.toString(),
					new String[] { pkValue }, colList);
			for (int i = 0; i <= size; i++) {
				map.put(colList[i], (rsTmp != null) ? rsTmp[i] : "");
			}
		}
		return map;
	}

	public HashMap<String, String> sxjyQueryOne3(String tableName,
			String[] colList, String pk, String pkValue,
			HashMap<String, String> map, String sql) {
		// DAO dao = DAO.getInstance();
		// 通过主键查询单个数据用 获得HashMap<String, String>形式,并覆盖之前传入的HashMap里的值的通用方法
		int size = colList.length - 1;
		if (sql.equalsIgnoreCase("")) {
			if (pkValue.equalsIgnoreCase("")) {
				for (int i = 0; i < size; i++) {
					map.put(colList[i], "");
				}
			} else {
				StringBuffer sqlBuffer = new StringBuffer("select ");
				for (int i = 0; i < size; i++) {
					sqlBuffer.append(colList[i]);
					sqlBuffer.append(", ");
				}
				sqlBuffer.append(colList[size]);
				sqlBuffer.append(" from ");
				sqlBuffer.append(tableName);
				sqlBuffer.append(" where ");
				sqlBuffer.append(pk);
				sqlBuffer.append("=?");
				String[] rsTmp = getOneRs(sqlBuffer.toString(),
						new String[] { pkValue }, colList);
				for (int i = 0; i <= size; i++) {
					map.put(colList[i], (rsTmp != null) ? rsTmp[i] : "");
				}
			}
		} else {
			String[] rsTmp = getOneRs(sql, new String[] { pkValue }, colList);
			for (int i = 0; i <= size; i++) {
				map.put(colList[i], (rsTmp != null) ? rsTmp[i] : "");
			}
		}
		return map;
	}

	public List<HashMap<String, String>> getBjrsList(String userDep,
			String xydm, String zydm, String nj) {
		// 获取班级及班级人数信息
		// DAO dao = DAO.getInstance();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		if (xydm != null && !("".equalsIgnoreCase(xydm.trim()))) {
			query.append(" and a.xydm='");
			query.append(xydm);
			query.append("' ");
		}
		if (zydm != null && !("".equalsIgnoreCase(zydm.trim()))) {
			query.append(" and a.zydm='");
			query.append(zydm);
			query.append("' ");
		}
		if (nj != null && !("".equalsIgnoreCase(nj.trim()))) {
			query.append(" and a.nj='");
			query.append(nj);
			query.append("' ");
		}
		String sql = "select a.bjdm,a.bjmc,b.bjrs from (select distinct bjdm,bjmc from view_njxyzybj) a left join (select count(*) bjrs,bjdm from view_xsjbxx group by bjdm) b on a.bjdm = b.bjdm";
		return getList(sql + query.toString(), new String[] {}, new String[] {
				"bjdm", "bjmc", "bjrs" });
	}

	public String[] sxjyQueryOne2(String tableName, String[] colList,
			String pk, String pkValue) {
		// 通过主键查询单个数据用 获得HashMapString[]形式的通用方法
		// DAO dao = DAO.getInstance();
		int size = colList.length - 1;
		StringBuffer sqlBuffer = new StringBuffer("select ");
		for (int i = 0; i < (size); i++) {
			sqlBuffer.append(colList[i]);
			sqlBuffer.append(", ");
		}
		sqlBuffer.append(colList[size]);
		sqlBuffer.append(" from ");
		sqlBuffer.append(tableName);
		sqlBuffer.append(" where ");
		sqlBuffer.append(pk);
		sqlBuffer.append("=?");
		String[] rsTmp = getOneRs(sqlBuffer.toString(),
				new String[] { pkValue }, colList);
		return rsTmp;
	}

	public synchronized String[] getViewComm(String viewName)
			throws SQLException {
		// 得到视图的字段注释语句
		// DAO dao = DAO.getInstance();
		String[] arr = null;
		String sql = "select 'comment on column '||table_name||'.'||column_name||' is '||chr(39)||comments||"
				+ "chr(39) com from user_col_comments where table_name=upper('"
				+ viewName + "')";
		arr = getArray(sql, new String[] {}, "com");
		return arr;
	}

	public List<HashMap<String, String>> getJxqkList() {
		// 获得部门代码，名称列表
		String sql = "select dm,mc from jxkqztb";
		return getList(sql, new String[] {}, new String[] { "dm", "mc" });
	}

	public List<HashMap<String, String>> getLdList(String nd, String xb) {
		// 获取军训连队列表
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		if (!nd.equalsIgnoreCase("")) {
			query.append(" and nd='");
			query.append(nd);
			query.append("' ");
		}
		if (!xb.equalsIgnoreCase("")) {
			query.append(" and xb='");
			query.append(xb);
			query.append("' ");
		}
		String sql = "select 'ldbh' dm,'--请选择--' mc from jxlddmwhb where rownum='1' union all select ldbh dm,ldmc mc from jxlddmwhb";
		List<HashMap<String, String>> ldList = getList(sql + query.toString(),
				new String[] {}, new String[] { "dm", "mc" });
		return ldList;
	}

	/**
	 * 取得军训成绩比例
	 * @return
	 * @throws Exception
	 * @author luo
	 */
	public String[] getCjbl() throws Exception {
		DAO  dao = DAO.getInstance();
		String[] getPara = { "jxsj", "jxll" };
		String sql = "select jxsj,jxll from zjcm_cjbl";
		String[] reVal = dao.getOneRs(sql, new String[] {  }, getPara);
		return reVal;
	}
	
	public List<HashMap<String, String>> getLdList2(String nd) {
		// 获取军训连队列表
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		if (!nd.equalsIgnoreCase("")) {
			query.append(" and nd='");
			query.append(nd);
			query.append("' ");
		}
		String sql = "select ldmc bz,ldmc bz from jxlddmwhb";
		List<HashMap<String, String>> ldList = getList(sql + query.toString(),
				new String[] {}, new String[] { "bz", "bz" });
		return ldList;
	}
	
	/**
	 * 判断军训名单数据是否取共享数据
	 * 
	 * @return boolean
	 */
	public String checkJxmdsfqgx() {
		String sql = "select jxmdsfqgx from jxglxtszb";
		String result = getOneRs(sql, new String[] {}, "jxmdsfqgx");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : "1";

		return result;
	}

	/**
	 * 获取取军训名单的视图
	 * 
	 * @return String
	 */
	public String getJxmdTableName() {
		String tableName = "view_jxmdxx";
		String sfqgx = getOneRs("select jxmdsfqgx from jxglxtszb",
				new String[] {}, "jxmdsfqgx");
		sfqgx = sfqgx == null || "".equalsIgnoreCase(sfqgx) ? "0" : sfqgx;
		boolean flag = Integer.parseInt(sfqgx) > 0 ? true : false;
		if (flag) {// 取学工和共享数据
			tableName = "view_bks_jxmdxx";
		}
		return tableName;
	}

	public List searchJsghInfo(JxglwhForm model) {
		String tableName = "view_fdyxx";
		String sql = "select zgh,xm,xb,bmmc xymc,xl,sfmc from " + tableName
				+ " ";
		String[] outputValue = { "zgh", "xm", "xb", "xymc", "xl", "sfmc" };
		String whereSql = getWhereSql(model).toString();

		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	public StringBuffer getWhereSql(JxglwhForm model) {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = DealString.toGBK(model.getXq());
		String xb = DealString.toGBK(model.getXb());
		String lddm = DealString.toGBK(model.getLddm());
		String jxdm = DealString.toGBK(model.getJxdm());
		String fzlddm = DealString.toGBK(model.getFzlddm());
		String jxnd = DealString.toGBK(model.getJxnd());

		StringBuffer sb = new StringBuffer("where 1=1 ");
		if (xydm != null && !xydm.equalsIgnoreCase("")) {
			sb.append(" and bmdm=?");
			value.add(xydm);
		}
		if (zydm != null && !zydm.equalsIgnoreCase("")) {
			sb.append(" and zydm=?");
			value.add(zydm);
		}
		if (bjdm != null && !bjdm.equalsIgnoreCase("")) {
			sb.append(" and bjdm=?");
			value.add(bjdm);
		}
		if (nj != null && !nj.equalsIgnoreCase("")) {
			sb.append(" and nj=?");
			value.add(nj);
		}
		if (xh != null && !xh.equalsIgnoreCase("")) {
			sb.append(" and xh=?");
			value.add(xh);
		}
		if (xm != null && !xm.equalsIgnoreCase("")) {
			sb.append(" and xm=?");
			value.add(xm);
		}
		if (xn != null && !xn.equalsIgnoreCase("")) {
			sb.append(" and xn=?");
			value.add(xn);
		}
		if (nd != null && !nd.equalsIgnoreCase("")) {
			sb.append(" and nd=?");
			value.add(nd);
		}
		if (xq != null && !xq.equalsIgnoreCase("")) {
			sb.append(" and xq=?");
			value.add(xq);
		}
		if (xb != null && !xb.equalsIgnoreCase("")) {
			sb.append(" and xb=?");
			value.add(xb);
		}
		if (lddm != null && !lddm.equalsIgnoreCase("")) {
			sb.append(" and lddm=?");
			value.add(lddm);
		}
		if (jxdm != null && !jxdm.equalsIgnoreCase("")) {
			sb.append(" and jxdm=?");
			value.add(jxdm);
		}
		if (fzlddm != null && !fzlddm.equalsIgnoreCase("")) {
			sb.append(" and fzlddm=?");
			value.add(fzlddm);
		}
		if (jxnd != null && !jxnd.equalsIgnoreCase("")) {
			sb.append(" and jxnd=?");
			value.add(jxnd);
		}
		return sb;
	}

	/**
	 * 检测记录是否存在
	 * 
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 */
	public boolean checkExists(String tableName, String pk, String pkValue) {
		String sql = "select count(*) num from " + tableName + " where " + pk
				+ "=?";
		String result = getOneRs(sql, new String[] { pkValue }, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		return Integer.parseInt(result) > 0 ? true : false;
	}

	/**
	 * 根据职工号查询教师个人信息
	 * 
	 * @param jsid
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getJsxx(String jsid) {
		String sql = "select zgh jsdm, xm, xb,bmdm xydm, mz mzdm from view_fdyxx where zgh=?";

		return getMap(sql, new String[] { jsid }, new String[] { "jsdm", "xm",
				"xb", "xydm", "mzdm" });
	}

	/**
	 * 获取军训获奖类别列表
	 * 
	 * @return List
	 */
	public List getJxhjlbList() {
		String sql = "select distinct jxdm, jxmc from jxhjlbb ";
		return getList(sql, new String[] {}, new String[] { "jxdm", "jxmc" });
	}

	/**
	 * 查询军训团队获奖信息
	 * 
	 * @param model
	 * @return List
	 */
	public List searchJxtdhjInfo(JxglwhForm model) {
		String tableName = "VIEW_NBLG_JXTDHJ";
		String sql = "select xn||nd||xq||lddm||jxdm 主键, xn,nd,xqmc,xq,lddm,ldmc,jxdm,jxmc,zdy,hjsj from "
				+ tableName + " ";
		String[] outputValue = { "主键", "xn", "nd", "xqmc", "ldmc", "zdy", "jxmc",
				"hjsj" };
		String whereSql = getWhereSql(model).toString();

		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	/**
	 * 获取军训奖项列表
	 * 
	 * @return List
	 */
	public List selectJxtdjxList() {
		String sql = "select distinct jxdm,jxmc from jxjxdmb ";
		return getList(sql, new String[] {}, new String[] { "jxdm", "jxmc" });
	}

	/**
	 * 根据主键查询军训团队获奖信息
	 * 
	 * @param pkValue
	 * @return HashMap<String, String>
	 */
	public HashMap<String, String> getJxtdhjByPk(String pkValue) {
		String tableName = "view_jxtdhj";
		String sql = "select xn, nd, xq, lddm, jxdm,zdy,hjsj from " + tableName
				+ " where xn||nd||xq||lddm||jxdm=?";

		return getMap(sql, new String[] { pkValue }, new String[] { "xn", "nd",
				"xq", "lddm", "jxdm", "zdy", "hjsj" });
	}

	/**
	 * 查询军训学生参训干部信息
	 * 
	 * @param model
	 * @return List
	 */
	public List getJxxscxgbInfo(JxglwhForm model) {
		String tableName = "view_jxxscxgb";
		String[] outputValue = { "主键", "xh", "xm", "xymc", "bjmc", "jxnd",
				"fzldmc", "zw" };
		String sql = "select xh||jxnd||fzlddm 主键, xh, xm, xb, xymc, bjmc, jxnd, fzldmc, zw from "
				+ tableName + " ";
		String whereSql = getWhereSql(model).toString();

		return rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}
	
	/**
	 * 根据主键查询军训学生参训干部信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxxscxgb(String pkValue){
		String tableName = "view_jxxscxgb";
		String[] outputValue = {"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "fzlddm", "fzldmc", "zw", "bz","jxnd", "zzdh", "sjhm"};
		String sql = "select xh,xm,xymc,zymc,bjmc,xb,nj,fzlddm,fzldmc,jxnd,zw,bz,jxnd,zzdh,sjhm from " + tableName + " where xh||jxnd||fzlddm=?";
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	public String getNblgJxjz(String nj) {
		String sql = "select outJxbz(?) menuTxt from dual";
		return getOneRs(sql, new String[]{nj}, "menuTxt");
	}
	
	/**
	 * 获得编制菜单
	 * 
	 * @return String
	 * @author LuoJw
	 * */
	public String getXbmzJxjz(String nj) {
		String sql = "select outJxbzXbmz(?) menuTxt from dual";
		return getOneRs(sql, new String[]{nj}, "menuTxt");
	}
	
	public HashMap<String, String> getNblgJxjzxx(String bzdm,String nj) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nj,bzdm,bzmc,bzdj,(case bzdj when '1' then '营级' when '2' then '连级' when '3' then '排级' when '4' then '班级' else bzdj end) bzdjmc,zdy,jgmc,sjdm,(select getJxbzss(?,?) sT from dual) ssjz,bz from nblg_jxbzdmb a where bzdm=? and nj=?";
		String[] colList = new String[] { "nj", "bzdm", "bzmc", "bzdj", "bzdjmc", "zdy", "jgmc", "sjdm", "ssjz", "bz" };
		String[] sLen = getOneRs(sql, new String[] { bzdm,nj,bzdm,nj }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	public HashMap<String, String> getXbmzJxjzxx(String bzdm,String nj) throws Exception {
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select nj,bzdm,bzmc,bzdj,(case bzdj when '1' then '营级' when '2' then '连级' when '3' then '排级' when '4' then '班级' else bzdj end) bzdjmc,zdy,jgmc,sjdm,(select getJxbzss(?,?) sT from dual) ssjz,bz from XBMZ_JXBZDMB a where bzdm=? and nj=?";
		String[] colList = new String[] { "nj", "bzdm", "bzmc", "bzdj", "bzdjmc", "zdy", "jgmc", "sjdm", "ssjz", "bz" };
		String[] sLen = getOneRs(sql, new String[] { bzdm,nj,bzdm,nj }, colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}// end for
		}// end if
		return stuList;
	}
	
	public List<HashMap<String, String>> getLdList() {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		String sql="";
		// 获得带队老师信息
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			sql = "select a.bzdm, b.bzmc || a.bzmc bzmc from nblg_jxbzdmb a, nblg_jxbzdmb b "
					+ "where a.sjdm = b.bzdm and a.bzdj = '2'";
			return dao.getList(sql, new String[] {}, new String[] { "bzdm", "bzmc" });
		}else{
			sql = "select a.bzdm dm, b.bzmc || a.bzmc mc from XBMZ_JXBZDMB a, XBMZ_JXBZDMB b "
				+ "where a.sjdm = b.bzdm and a.bzdj = '2'";
			List<HashMap<String, String>> list =  dao.getList(sql, new String[] {}, new String[] { "dm", "mc" });
			return list;
		}	
	}
	
	public List<HashMap<String, String>> getLsList(String nj) {
		DAO dao = DAO.getInstance();
		// 获得带队老师信息
		String sql = "select distinct jsdm,xm from jxgl_ddjsxxb where jxnd=? order by jsdm";
		return dao.getList(sql, new String[] {nj},
				new String[] { "jsdm", "xm" });
	}

	public String getLsXm(String jsdm) {
		DAO dao = DAO.getInstance();
		// 获得带队老师姓名
		String sql = "select xm from jxgl_ddjsxxb where jsdm = ?";
		return dao.getOneRs(sql, new String[] {jsdm}, "xm");
	}
	
	public List<HashMap<String, String>> getJgList(String nj) {
		DAO dao = DAO.getInstance();
		// 获得带队教官信息
		String sql = "select distinct jgbh,xm from jxgl_jgxxb where jxnd = ? order by jgbh";
		return dao.getList(sql, new String[] { nj }, new String[] { "jgbh",
				"xm" });
	}
	
	public String getJgXm(String jgbh) {
		DAO dao = DAO.getInstance();
		// 获得教官姓名
		String sql = "select xm from jxgl_jgxxb where jgbh = ?";
		return dao.getOneRs(sql, new String[] {jgbh}, "xm");
	}
	
	public String getLsOne(String sjdm) {
		DAO dao = DAO.getInstance();
		// 获得老师代码
		String sql = "select a.jsdm from nblg_jxbzdmb t, nblg_lsxx b, jxgl_ddjsxxb a"
				+ " where b.sfzld = t.bzdm and a.xm = t.zdy and b.sfzld = ?";
		return dao.getOneRs(sql, new String[] {sjdm}, "jsdm");
	}
	
	public String getXbjsOne(String sjdm) {
		DAO dao = DAO.getInstance();
		// 获得老师代码
		String sql = "select distinct(t.zdy) zdy from XBMZ_JXBZDMB t where t.sjdm=?";
		return dao.getOneRs(sql, new String[] {sjdm}, "zdy");
	}
	
	public String getXbjgOne(String sjdm) {
		DAO dao = DAO.getInstance();
		// 获得教官代码
		String sql = "select distinct(t.jgmc) jgmc from XBMZ_JXBZDMB t where t.sjdm=?";
		return dao.getOneRs(sql, new String[] {sjdm}, "jgmc");
	}
	
	public String getJgOne(String jgmc) {
		DAO dao = DAO.getInstance();
		// 获得教官代码
		String sql = "select distinct(b.jgbh) from nblg_jxbzdmb t, nblg_jgxx b where b.sfzld = t.bzdm and t.jgmc =?";
		return dao.getOneRs(sql, new String[] {jgmc}, "jgbh");
	}
	
	public List<String> getBzList(String jgbh) throws SQLException {
		DAO dao = DAO.getInstance();
		// 获得带队教官信息
		String sql = "select b.bzmc from nblg_jgxx a, nblg_jxbzdmb b where a.sfzld = b.bzdm and a.jgbh = ?";
		return dao.getList(sql, new String[] { jgbh }, "bzmc");
	}
	
	public List<String> getJsBzList(String jsbh) throws SQLException {
		DAO dao = DAO.getInstance();
		// 获得指导员信息
		String sql = "select b.bzmc from nblg_lsxx a, nblg_jxbzdmb b where a.sfzld = b.bzdm and a.jsdm = ?";
		return dao.getList(sql, new String[] { jsbh }, "bzmc");
	}
	public boolean saveNblgJxjz(NblgJxjzModel model, String doS, HttpServletRequest request)
			throws Exception {
		boolean bFlag = false;
		String nj = Base.chgNull(model.getNj(), "", 1);
		String bzdm = Base.chgNull(model.getBzdm(), "", 1);
		String bzmc = Base.chgNull(model.getBzmc(), "", 1);
		String bzdj = Base.chgNull(model.getBzdj(), "", 1);
		String zdy = Base.chgNull(model.getZdy(), "", 1);
		String jgmc = Base.chgNull(model.getJgmc(), "", 1);
		String sjdm = Base.chgNull(model.getSjdm(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		zdy = getLsXm(zdy);
		jgmc = getJgXm(jgmc);

		String sHave = isNblgJxJzCf(bzdm, nj);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("nblg_jxbzdmb",
					new String[] { "nj", "bzdm", "bzmc", "bzdj", "zdy", "jgmc",
							"sjdm", "bz" }, new String[] { nj, bzdm, bzmc,
							bzdj, zdy, jgmc, sjdm, bz }, request);
		} else if (!"add".equalsIgnoreCase(doS)) {
			bFlag = StandardOperation.update("nblg_jxbzdmb", new String[] {
					"bzmc", "bzdj", "zdy", "jgmc", "sjdm", "bz" },
					new String[] { bzmc, bzdj, zdy, jgmc, sjdm, bz },
					"bzdm||nj", bzdm + nj, request);
		} else {
			request.setAttribute("have", "have");
		}
		return bFlag;
	}
	
	public boolean saveXbmzJxjz(NblgJxjzModel model, String doS,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String nj = Base.chgNull(model.getNj(), "", 1);
		String bzdm = Base.chgNull(model.getBzdm(), "", 1);
		String bzmc = Base.chgNull(model.getBzmc(), "", 1);
		String bzdj = Base.chgNull(model.getBzdj(), "", 1);
		String zdy = Base.chgNull(model.getZdy(), "", 1);
		String jgmc = Base.chgNull(model.getJgmc(), "", 1);
		String sjdm = Base.chgNull(model.getSjdm(), "", 1);
		String bz = Base.chgNull(model.getBz(), "", 1);
		String xb = Base.chgNull(model.getXb(), "", 1);
		xb = ("".equals(xb)) ? "-" : xb;
		
		String sHave = isXbmzJxJzCf(bzdm, nj);
		if ("-1".equalsIgnoreCase(sHave)) {
			bFlag = StandardOperation.insert("XBMZ_JXBZDMB",
					new String[] { "nj", "bzdm", "bzmc", "bzdj", "zdy", "jgmc",
							"sjdm", "bz","xb" }, new String[] { nj, bzdm, bzmc,
							bzdj, zdy, jgmc, sjdm, bz,xb }, request);
		} else if (!"add".equalsIgnoreCase(doS)) {
			bFlag = StandardOperation.update("XBMZ_JXBZDMB", new String[] {
					"bzmc", "bzdj", "zdy", "jgmc", "sjdm", "bz","xb" },
					new String[] { bzmc, bzdj, zdy, jgmc, sjdm, bz,xb },
					"bzdm||nj", bzdm + nj, request);
		} else {
			request.setAttribute("have", "have");
		}
		return bFlag;
	}

	public String isNblgJxJzCf(String bzdm, String nj) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from nblg_jxbzdmb where bzdm = ? and nj = ?";
		String num = getOneRs(sql, new String[] { bzdm, nj }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		} else {
			sql = "select count(*) num from view_njxyzybj where bjdm = ? and nj = ?";
			num = getOneRs(sql, new String[] { bzdm, nj }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	public String isXbmzJxJzCf(String bzdm, String nj) throws Exception {
		String sFlag = "-1";
		String sql = "select count(*) num from XBMZ_JXBZDMB where bzdm = ? and nj = ?";
		String num = getOneRs(sql, new String[] { bzdm, nj }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		} else {
			sql = "select count(*) num from view_njxyzybj where bjdm = ? and nj = ?";
			num = getOneRs(sql, new String[] { bzdm, nj }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}
	
	public List<HashMap<String, String>> getNblgYjList(String nj) {
		return getList("select bzdm yjdm,bzmc yjmc from nblg_jxbzdmb where bzdj='1' and nj=?", new String[]{nj}, new String[]{"yjdm", "yjmc"});
	}
	
	public List<HashMap<String, String>> getNblgLjList(String yjdm,String nj) {
		return getList("select bzdm ljdm,bzmc ljmc from nblg_jxbzdmb where bzdj='2' and nj=? and sjdm=?", new String[]{nj,yjdm}, new String[]{"ljdm", "ljmc"});
	}
	
	public List<HashMap<String, String>> getXbmzYjList(String nj) {
		return getList("select bzdm yjdm,bzmc yjmc from XBMZ_JXBZDMB where bzdj='1' and nj=?", new String[]{nj}, new String[]{"yjdm", "yjmc"});
	}
	
	public List<HashMap<String, String>> getXbmzLjList(String yjdm,String nj) {
		return getList("select bzdm ljdm,bzmc ljmc from XBMZ_JXBZDMB where bzdj='2' and nj=? and sjdm=?", new String[]{nj,yjdm}, new String[]{"ljdm", "ljmc"});
	}
	
	public List<HashMap<String, String>> getNblgPjList(String ljdm,String nj) {
		return getList("select bzdm pjdm,bzmc pjmc from nblg_jxbzdmb where bzdj='2' and nj=? and sjdm=?", new String[]{nj,ljdm}, new String[]{"pjdm", "pjmc"});
	}
	
	public boolean delNblgJxbz(String bzdm,String nj) throws Exception {
		boolean b = false;
		String sT = Base.chgNull(getOneRs("select getJxbzXsxx(?,?) sT from dual", new String[]{bzdm,nj}, "sT"), "123!!##sptilOne##!!", 1);
		String[] stL = sT.split("!!##sptilOne##!!");
		String[] sqlT = new String[stL.length];
		int i = 1;
		StringBuffer delSQL = new StringBuffer("delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm in (''");
		for (; i < stL.length; i++){
			delSQL.append(",'");
			delSQL.append(stL[i]);
			delSQL.append("'");
			
			sqlT[i-1] = "delete nblg_jxbzdmb where bzdm='"+stL[i]+"' and nj='"+nj+"'";
		}
		
		delSQL.append("))");
		runUpdate(delSQL.toString(), new String[] { nj });
		
		sqlT[i-1] = "delete nblg_jxbzdmb where bzdm='"+bzdm+"' and nj='"+nj+"'";
		
		int[] res = runBatch(sqlT);
		for(i=0;i<res.length;i++){
			b = (res[i]==Statement.EXECUTE_FAILED)?false:true;
			if(!b) break;
		}
		return b;
	}
	
	public boolean delXbmzJxbz(String bzdm, String nj, String xb)
			throws Exception {
		boolean b = false;
		String sT = Base.chgNull(getOneRs(
				"select getJxbzXbmzXsxx(?,?) sT from dual", new String[] {
						bzdm, nj }, "sT"), "123!!##sptilOne##!!", 1);
		String[] stL = sT.split("!!##sptilOne##!!");
		String[] sqlT = new String[stL.length];
		int i = 1;
		StringBuffer delSQL = new StringBuffer(
				"delete jxgl_jxmdb a where a.nd=? and exists (select 1 from view_xsjbxx b where a.xh=b.xh and b.bjdm in (''");
		for (; i < stL.length; i++) {
			delSQL.append(",'");
			delSQL.append(stL[i]);
			delSQL.append("'");
			if ("-".equals(xb)) {
				sqlT[i - 1] = "delete XBMZ_JXBZDMB where bzdm='" + stL[i]
						+ "' and nj='" + nj + "'";
			} else {
				sqlT[i - 1] = "delete XBMZ_JXBZDMB where bzdm='" + stL[i]
						+ "' and nj='" + nj + "' and xb='" + xb + "'";
			}
		}

		delSQL.append("))");
		runUpdate(delSQL.toString(), new String[] { nj });
		if ("-".equals(xb)) {
			sqlT[i - 1] = "delete XBMZ_JXBZDMB where bzdm='" + bzdm
					+ "' and nj='" + nj + "'";
		} else {
		sqlT[i - 1] = "delete XBMZ_JXBZDMB where bzdm='" + bzdm + "' and nj='"
				+ nj + "' and xb='" + xb + "'";
		}

		int[] res = runBatch(sqlT);
		for (i = 0; i < res.length; i++) {
			b = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!b)
				break;
		}
		return b;
	}
	
	// 得到次小编制列表
	public List<HashMap<String, String>> getNextMinJz() {
		DAO dao = DAO.getInstance();
		String sql = "select a.bzdm, a.bzmc from jxbzdmb a, (select * from (select * from (select jzdm, jzmc"
				+ " , rownum num from jxjzdj order by jzdm desc)) where num = 2) b where a.bzdj = b.jzdm";
		return dao.getList(sql, new String[] {},
				new String[] { "bzdm", "bzmc" });
	}
}
