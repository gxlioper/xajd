package xgxt.jxgl.gt;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.JxglForm;

public class JxglDAO {

	ArrayList<String> value = new ArrayList<String>();

	// 获取团队获奖信息List
	public Vector<Object> getTdhjList(JxglForm jxglform, String[] colList) {
		DAO dao = DAO.getInstance();

		String nextMaxJz = getnextMaxJz().get("jzdm");
		StringBuffer query = new StringBuffer();
		query.append(" where bzdj ='" + nextMaxJz + "'");
		query.append(Base.isNull(jxglform.getXn()) ? " and 1=1" : " and xn='"
				+ jxglform.getXn() + "'");
		query.append(Base.isNull(jxglform.getJxnd()) ? " and 1=1"
				: " and nd = '" + jxglform.getJxnd() + "'");
		query.append(Base.isNull(jxglform.getXq()) ? " and 1=1" : " and xq= '"
				+ jxglform.getXq() + "'");
		query.append(Base.isNull(jxglform.getLddm()) ? " and 1=1"
				: " and lddm= '" + jxglform.getLddm() + "'");
		query.append(Base.isNull(jxglform.getJxdm()) ? " and 1=1"
				: " and jxdm= '" + jxglform.getJxdm() + "'");

		Vector<Object> vector = new Vector<Object>();

		String sql = "select XN||ND||XQ||LDDM||JXDM 主键,xn,nd,xqmc,ldmc,zdy,jxmc,hjsj"
				+ " from view_jxgl_jxtdhj" + query.toString();
		//System.out.println(sql);
		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);
		vector.addAll(rslist);
		return vector;
	}
	// 获取团队获奖信息List信息数
	public int getTdhjListCount(JxglForm jxglform, String colList) {
		DAO dao = DAO.getInstance();

		String nextMaxJz = getnextMaxJz().get("jzdm");
		StringBuffer query = new StringBuffer();
		query.append(" where bzdj ='" + nextMaxJz + "'");
		query.append(Base.isNull(jxglform.getXn()) ? " and 1=1" : " and xn='"
				+ jxglform.getXn() + "'");
		query.append(Base.isNull(jxglform.getJxnd()) ? " and 1=1"
				: " and nd = '" + jxglform.getJxnd() + "'");
		query.append(Base.isNull(jxglform.getXq()) ? " and 1=1" : " and xq= '"
				+ jxglform.getXq() + "'");
		query.append(Base.isNull(jxglform.getLddm()) ? " and 1=1"
				: " and lddm= '" + jxglform.getLddm() + "'");
		query.append(Base.isNull(jxglform.getJxdm()) ? " and 1=1"
				: " and jxdm= '" + jxglform.getJxdm() + "'");


		String sql = "select count(1) count"
				+ " from view_jxgl_jxtdhj" + query.toString();
		//System.out.println(sql);
		  List<HashMap<String, String>> rsList = dao.getList(sql, new String[]{}, new String[]{"count"});
		  HashMap<String, String> map = rsList.get(0);
		  int count = Integer.parseInt(map.get("count"));
		return count;
	}

	// 获取教官信息List
	public Vector<Object> getJgxxList(JxglForm jxglform, String[] colList) {
		DAO dao = DAO.getInstance();

		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(jxglform.getJgbh()) ? " and 1=1"
				: " and jgbh='" + jxglform.getJgbh() + "'");
		query
				.append("".equalsIgnoreCase(jxglform.getXm()) ? " and 1=1"
						: " and xm like '%"
								+ DealString.toGBK(jxglform.getXm()) + "%'");
		query.append("".equalsIgnoreCase(jxglform.getJxnd()) ? " and 1=1"
				: " and jxnd= '" + DealString.toGBK(jxglform.getJxnd()) + "'");

		String lddm = jxglform.getLddm();
		String jxnd = jxglform.getJxnd();
		String sT = Base.chgNull(dao.getOneRs(
				"select getJxglbz(?,?) sT from dual",
				new String[] { lddm, jxnd }, "sT"), "!!##sptilOne##!!", 1);
		sT += "!!##sptilOne##!!" + lddm;
		String[] xsJz = sT.split("!!##sptilOne##!!");

		if (!"0000".equalsIgnoreCase(lddm) && lddm != null) {
			if (xsJz != null && xsJz.length > 0) {
				query.append(" and bzdm in ('',");
				for (int i = 0; i < xsJz.length; i++) {
					query.append("'");
					query.append(xsJz[i]);
					query.append("',");
				}
				query.append("'')");
			}
		}

		Vector<Object> vector = new Vector<Object>();

		String sql = "select rownum 行号, b.xm,b.主键,b.jgbh,b.xb,b.mzmc,b.lxdh,b.jxnd,b.bzdm "
				+ " from (select distinct (a.xm),a.jgbh 主键,a.jgbh,b.bzdj,"
				+ " a.xb,a.mzmc,a.lxdh,a.jxnd,b.bzdm from view_jxgl_jgxx a"
				+ " left join jxbzdmb b on a.jgbh = b.jgbh order by b.bzdj) b "
				+ query.toString();

		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);

		for (int i = 0; i < rslist.size(); i++) {
			String rs[] = rslist.get(i);
			String bzdm = rs[rs.length - 1];
			String nj = rs[rs.length - 2];
			sql = "select  NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
					+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
			String bzmc = dao.getOneRs(sql,
					new String[] { bzdm, nj, bzdm, nj }, "bzmc");
			rs[rs.length - 1] = bzmc.replace("-", "");
		}
		vector.addAll(rslist);
		return vector;
	}

	// 获取教师信息List
	public Vector<Object> getJsxxList(JxglForm jxglform, String[] colList) {
		DAO dao = DAO.getInstance();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(jxglform.getJsdm()) ? " and 1=1"
				: " and jsdm='" + jxglform.getJsdm() + "'");
		query
				.append("".equalsIgnoreCase(jxglform.getXm()) ? " and 1=1"
						: " and xm like '%"
								+ DealString.toGBK(jxglform.getXm()) + "%'");
		query.append("".equalsIgnoreCase(jxglform.getJxnd()) ? " and 1=1"
				: " and jxnd= '" + DealString.toGBK(jxglform.getJxnd()) + "'");

		String lddm = jxglform.getLddm();
		String jxnd = jxglform.getJxnd();
		String sT = Base.chgNull(dao.getOneRs(
				"select getJxglbz(?,?) sT from dual",
				new String[] { lddm, jxnd }, "sT"), "!!##sptilOne##!!", 1);
		sT += "!!##sptilOne##!!" + lddm;
		String[] xsJz = sT.split("!!##sptilOne##!!");

		if (!"0000".equalsIgnoreCase(lddm) && lddm != null) {
			if (xsJz != null && xsJz.length > 0) {
				query.append(" and sfzld in ('',");
				for (int i = 0; i < xsJz.length; i++) {
					query.append("'");
					query.append(xsJz[i]);
					query.append("',");
				}
				query.append("'')");
			}
		}

		Vector<Object> vector = new Vector<Object>();
		String sql = "select rownum 行号,t.* from (select b.xm,b.主键,b.jsdm,b.xb,b.mzmc,b.lxdh,b.jxnd,b.sfzld"
				+ " from (select distinct (a.jsdm),a.xm,a.jsdm 主键,b.bzdj,a.xb,a.mzmc,a.lxdh,a.jxnd,"
				+ " b.bzdm sfzld from view_jxgl_jsxx a left join jxbzdmb b on b.jsdm = a.jsdm) b "
				+ query.toString() + " order by b.bzdj) t";
		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);
		for (int i = 0; i < rslist.size(); i++) {
			String rs[] = rslist.get(i);
			String bzdm = rs[rs.length - 1];
			String nj = rs[rs.length - 2];
			sql = "select NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
					+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
			String bzmc = dao.getOneRs(sql,
					new String[] { bzdm, nj, bzdm, nj }, "bzmc");
			rs[rs.length - 1] = bzmc.replace("-", "");
		}
		vector.addAll(rslist);
		return vector;
	}

	// 获取军训名单信息List
	public ArrayList<String[]>  getArmyStuList(JxglForm jxglform, String[] colList,
			String isFdy, String userName) throws SQLException {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();

		String nj = DealString.toGBK(jxglform.getNj());
		String xh = DealString.toGBK(jxglform.getXh());
		String xm = jxglform.getXm();
		String xydm = DealString.toGBK(jxglform.getXydm());
		String zydm = DealString.toGBK(jxglform.getZydm());
		String bjdm = DealString.toGBK(jxglform.getBjdm());
		String lddm = DealString.toGBK(jxglform.getLddm());
		String xb = DealString.toGBK(jxglform.getXb());
		String xn = DealString.toGBK(jxglform.getXn());

		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj = '" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and xn = '" + xn
				+ "'");
		query.append("".equalsIgnoreCase(xh) ? " and 1=1" : " and xh = '" + xh
				+ "'");
		query.append("".equalsIgnoreCase(xm) ? " and 1=1" : " and xm like '%"
				+ xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm = '"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm = '"
				+ zydm + "'");

		query.append("".equalsIgnoreCase(xb) ? " and 1=1" : " and xb = '" + xb
				+ "'");
		// 江苏信息，辅导员限制
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				List<HashMap<String, String>> bjList = getFdyBjList(userName);
				if (bjList != null && bjList.size() > 0) {
					query.append(" and bjdm in ('',");
					for (int i = 0; i < bjList.size(); i++) {
						HashMap<String, String> map = bjList.get(i);
						query.append("'" + map.get("bjdm") + "',");
					}
					query.append("'')");
				}
			} else {
				query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
						: " and bjdm = '" + bjdm + "'");
			}
		} else {
			query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
					: " and bjdm = '" + bjdm + "'");
		}
		String sT = Base.chgNull(dao.getOneRs(
				"select getJxglbz(?,?) sT from dual",
				new String[] { lddm, nj }, "sT"), "!!##sptilOne##!!", 1);
		String[] xsJz = sT.split("!!##sptilOne##!!");
		if (!"0000".equalsIgnoreCase(lddm) && lddm != null) {
			if (xsJz != null && xsJz.length > 0) {
				query.append(" and ldbh in ('" + lddm + "',");
				for (int i = 0; i < xsJz.length; i++) {
					query.append("'");
					query.append(xsJz[i]);
					query.append("',");
				}
				query.append("'')");
			}
		}

		String sql = "";

		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			sql = "select t.*, rownum 行号 from (select xh || nd || '0' 主键, a.*, '否' xs from view_jxmdxx a"
					+ " union select b.ksh || b.nj || '1' 主键, b.ksh, b.nj nd, b.xm, b.xb, b.xydm, b.xymc, b.nj,"
					+ " b.zydm, b.zymc, b.bjdm, b.bjmc, b.mz mzdm, c.ldbh, c.sfbx, c.jxbx, c.jxbz, c.jxcf,"
					+ " b.mz mzmc, '' ldmc, '是' xs from view_jxgl_xsbjb b, jxgl_jxmdb c where b.ksh = c.xh) t"
					+ query;
		} else {
			sql = "select t.*, rownum 行号 from (select xh || nd || '0' 主键, a.*, '否' xs from view_jxmdxx a) t"
					+ query;
		}
		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);

		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			for (int i = 0; i < rslist.size(); i++) {
				String rs[] = rslist.get(i);
				String bzdm = rs[rs.length - 2];
				String nd = rs[rs.length - 3];
				sql = "select  NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
						+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
				String bzmc = dao.getOneRs(sql, new String[] { bzdm, nd, bzdm,
						nd }, "bzmc");
				rs[rs.length - 2] = bzmc.replace("-", "");
			}
		} else {
			for (int i = 0; i < rslist.size(); i++) {
				String rs[] = rslist.get(i);
				String bzdm = rs[rs.length - 1];
				String nd = rs[rs.length - 4];
				sql = "select  NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
						+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
				String bzmc = dao.getOneRs(sql, new String[] { bzdm, nd, bzdm,
						nd }, "bzmc");
				rs[rs.length - 1] = bzmc.replace("-", "");
			}
		}
		
		return rslist;
	}

	// 获取军训干部信息List
	public Vector<Object> getGbxxList(JxglForm jxglform, String[] colList) {
		DAO dao = DAO.getInstance();
		String xxdm = StandardOperation.getXxdm();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(jxglform.getZgh()) ? " and 1=1"
				: " and zgh='" + jxglform.getZgh() + "'");
		query
				.append("".equalsIgnoreCase(jxglform.getXm()) ? " and 1=1"
						: " and xm like '%"
								+ DealString.toGBK(jxglform.getXm()) + "%'");
		query.append("".equalsIgnoreCase(jxglform.getJxnd()) ? " and 1=1"
				: " and jxnd= '" + DealString.toGBK(jxglform.getJxnd()) + "'");

		String lddm = jxglform.getLddm();
		String jxnd = jxglform.getJxnd();
		String jxzw = jxglform.getJxzw();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String gblx = jxglform.getGblx();
			query.append(" and dwlx = '" + gblx + "'");
		}
		if (!"0000".equalsIgnoreCase(lddm) && lddm != null) {
			String sT = Base.chgNull(dao.getOneRs(
					"select getJxglbz(?,?) sT from dual", new String[] { lddm,
							jxnd }, "sT"), "!!##sptilOne##!!", 1);
			sT += "!!##sptilOne##!!" + lddm;
			String[] xsJz = sT.split("!!##sptilOne##!!");

			if (xsJz != null && xsJz.length > 0) {
				query.append(" and jxdw in ('',");
				for (int i = 0; i < xsJz.length; i++) {
					query.append("'");
					query.append(xsJz[i]);
					query.append("',");
				}
				query.append("'')");
			}
		}

		if (!"0000".equalsIgnoreCase(jxzw) && jxzw != null) {
			query.append("".equalsIgnoreCase(jxzw) ? " and 1=1"
					: " and jxzw= '" + DealString.toGBK(jxzw) + "'");
		}

		Vector<Object> vector = new Vector<Object>();

		String sql = "select 主键,rownum 行号 ,zgh, xm, xb, dwmc, zwmc, jxnd,lxmc from view_nblg_jxgbxx t"
				+ query.toString();

		ArrayList<String[]> rslist = dao.rsToVator(sql, new String[] {},
				colList);

		vector.addAll(rslist);
		return vector;
	}

	// 获取军训学生详细
	public HashMap<String, String> getArmyStuDetail(String doType, String pk,
			String pkValue, String[] colList, String xh, String realTable) {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from " + realTable + " where " + pk + "=?";

		if ("add".equalsIgnoreCase(doType)) {
			if (!"".equalsIgnoreCase(xh)) {
				map = dao
						.getMap(
								"select xh,xb,xm,(select dqnd from xtszb) nd,nj,xymc,zymc,bjmc,'' ldbh,'' sfbx,'' jxbx,'' jxbz,'' jxcf from view_xsjbxx where xh=?",
								new String[] { xh }, colList);
			}
		} else if ("modi".equalsIgnoreCase(doType)) {
			map = dao.getMap(sql, new String[] { pkValue }, colList);
		}
		return map;
	}

	// 得到军训成绩录入列表
	public List<HashMap<String, String>> getCjlrList(JxglForm jxglform,
			String[] colList, String isFdy, String userName) {
		DAO dao = DAO.getInstance();

		String xxdm = StandardOperation.getXxdm();

		String nj = DealString.toGBK(jxglform.getNj());
		String jxnd = DealString.toGBK(jxglform.getNd());
		String xh = DealString.toGBK(jxglform.getXh());
		String xm = DealString.toGBK(jxglform.getXm());
		String xydm = DealString.toGBK(jxglform.getXydm());
		String zydm = DealString.toGBK(jxglform.getZydm());
		String bjdm = DealString.toGBK(jxglform.getBjdm());
		String lddm = DealString.toGBK(jxglform.getLddm());
		String xb = DealString.toGBK(jxglform.getXb());
		String xn = DealString.toGBK(jxglform.getXn());
		String sfzh = DealString.toGBK(jxglform.getSfzh());
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append("".equalsIgnoreCase(nj) ? " and 1=1" : " and nj = '" + nj
				+ "'");
		query.append("".equalsIgnoreCase(xn) ? " and 1=1" : " and ndArray = '" + xn
				+ "'");
		query.append("".equalsIgnoreCase(jxnd) ? " and 1=1"
				: " and ndArray = '" + jxnd + "'");
		query.append("".equalsIgnoreCase(xh) ? " and 1=1" : " and xhArray = '"
				+ xh + "'");
		query.append("".equalsIgnoreCase(xm) ? " and 1=1"
				: " and xmArray like '%" + xm + "%'");
		query.append("".equalsIgnoreCase(xydm) ? " and 1=1" : " and xydm = '"
				+ xydm + "'");
		query.append("".equalsIgnoreCase(zydm) ? " and 1=1" : " and zydm = '"
				+ zydm + "'");
		query.append("".equalsIgnoreCase(lddm) ? " and 1=1" : " and ldbh = '"
			+ lddm + "'");
		query.append("".equalsIgnoreCase(sfzh) ? " and 1=1" : " and sfzh like '%"
			+ sfzh + "%'");
		
		// 江苏信息，辅导员限制
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				List<HashMap<String, String>> bjList = getFdyBjList(userName);
				if (bjList != null && bjList.size() > 0) {
					query.append(" and bjdm in ('',");
					for (int i = 0; i < bjList.size(); i++) {
						HashMap<String, String> map = bjList.get(i);
						query.append("'" + map.get("bjdm") + "',");
					}
					query.append("'')");
				}
			} else {
				query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
						: " and bjdm = '" + bjdm + "'");
			}
		} else {
			query.append("".equalsIgnoreCase(bjdm) ? " and 1=1"
					: " and bjdm = '" + bjdm + "'");
		}

		query.append("".equalsIgnoreCase(xb) ? " and 1=1" : " and xb = '" + xb
				+ "'");
//		String sT = Base.chgNull(dao.getOneRs(
//				"select getJxglbz(?,?) sT from dual",
//				new String[] { lddm, nj }, "sT"), "!!##sptilOne##!!", 1);
//		String[] xsJz = sT.split("!!##sptilOne##!!");
//
//		if (xsJz != null && xsJz.length > 0) {
//			query.append(" and ldbh in ('" + lddm + "',");
//			for (int i = 0; i < xsJz.length; i++) {
//				query.append("'");
//				query.append(xsJz[i]);
//				query.append("',");
//			}
//			query.append("'')");
//		}

		String sql = "";
		//南宁职业技术学院
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)) {
			sql = "select * from (select a.xh xhArray, a.xm xmArray, a.xn ndArray, a.xb, a.nj,a.bjmc,'否' xs, a.cj cjArray"
				+ " ,a.ldbh,a.xydm,a.zydm,a.bjdm,b.sfzh,b.zymc from view_jxcjxx a,view_xsjbxx b where a.xh=b.xh )" + query;
		} else if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			sql = "select * from (select xh xhArray, xm xmArray, nd ndArray, xb, nj, bjmc,"
				+ " cj cjArray, ldbh, xydm, zydm, bjdm, '否' xs from view_jxcjxx union select ksh xhArray,"
				+ " xm xmArray, nd ndArray, xb, nj, bjmc, cj cjArray, ldbh, xydm, zydm, bjdm, '是' xs"
				+ " from view_xsjxcjxx)" + query;
		} else {
			sql = "select * from (select xh xhArray, xm xmArray, xn ndArray, xb, nj, bjmc,'否' xs, cj cjArray"
					+ " ,ldbh,xydm,zydm,bjdm from view_jxcjxx )" + query;
		}

		System.out.println(sql);
		return dao.getList(sql, new String[] {}, colList);
	}

	// 获取教官信息
	public HashMap<String, String> getJgxx(String jgbh) {
		DAO dao = DAO.getInstance();

		String sql = "select * from jxgl_jgxxb where jgbh = ?";
		String[] colList = new String[] { "jgbh", "xm", "xb", "mzdm", "lxdh",
				"bz", "jxnd", "zw" };
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> rs = queryOne(colList, jgbh, map, sql);

		sql = "select a.jxnd, b.bzdm from jxgl_jgxxb a, jxbzdmb b where a.jgbh = b.jgbh and a.jgbh = ? order by b.bzdj";
		List<HashMap<String, String>> rslist = dao.getList(sql,
				new String[] { jgbh }, new String[] { "jxnd", "bzdm" });
		String sfzld = "";
		for (int i = 0; i < rslist.size(); i++) {
			HashMap<String, String> bz = rslist.get(i);
			String bzdm = bz.get("bzdm");
			String nj = bz.get("jxnd");
			sql = "select NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
					+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
			String bzmc = dao.getOneRs(sql,
					new String[] { bzdm, nj, bzdm, nj }, "bzmc");
			sfzld += bzmc.replace("-", "") + ",";
		}
		rs.put("sfzld", sfzld);
		return rs;
	}

	// 获取团队获奖信息
	public HashMap<String, String> getTdhjxx(String pk) {
		DAO dao = DAO.getInstance();
		String sql = "select * from jxtdhjb where xn||nd||xq||lddm||jxdm = ?";
		String[] colList = new String[] { "xn", "nd", "xq", "lddm", "jxdm",
				"hjsj" };
		HashMap<String, String> rs = dao.getMap(sql, new String[] { pk },
				colList);
		return rs;
	}

	// 获取干部信息
	public HashMap<String, String> getGbxx(String zgh, String dwlx) {
		DAO dao = DAO.getInstance();

		String sql = "select zgh jsdm,dwlx,xm,bmmc,xb,mz mzdm,bgdh,zzdh,sjdh,xnh,jxnd,gblx,jxdw,jxzw,"
				+ " bz from view_nblg_jxgbxx t where t.zgh=? and t.dwlx =?";
		String[] colList = new String[] { "jsdm", "dwlx", "xm", "bmmc", "xb",
				"mzdm", "bgdh", "zzdh", "sjdh", "xnh", "jxnd", "gblx", "jxdw",
				"jxzw", "bz" };
		HashMap<String, String> rs = dao.getMap(sql,
				new String[] { zgh, dwlx }, colList);
		return rs;
	}

	// 获取带队教师信息
	public HashMap<String, String> getJsxxOne(String jsdm) {
		DAO dao = DAO.getInstance();
		String sql = "select * from jxgl_ddjsxxb where jsdm = ?";
		String[] colList = new String[] { "jsdm", "xm", "xb", "mzdm", "xydm",
				"lxdh", "zzdh", "sjhm", "bz", "jxnd", "zw" };
		HashMap<String, String> map = new HashMap<String, String>();
		HashMap<String, String> rs = queryOne(colList, jsdm, map, sql);

		sql = "select a.jxnd, b.bzdm from jxgl_ddjsxxb a, jxbzdmb b where a.jsdm = b.jsdm and a.jsdm = ? order by b.bzdj";
		List<HashMap<String, String>> rslist = dao.getList(sql,
				new String[] { jsdm }, new String[] { "jxnd", "bzdm" });
		String sfzld = "";
		for (int i = 0; i < rslist.size(); i++) {
			HashMap<String, String> bz = rslist.get(i);
			String bzdm = bz.get("bzdm");
			String nj = bz.get("jxnd");
			sql = "select NVL((select getJxglbzss(?, ?) sT from dual), '-') || a.bzmc bzmc"
					+ " from jxbzdmb a where a.bzdm = ? and nj = ?";
			String bzmc = dao.getOneRs(sql,
					new String[] { bzdm, nj, bzdm, nj }, "bzmc");
			sfzld += bzmc.replace("-", "") + ",";
		}
		rs.put("sfzld", sfzld);
		return rs;
	}

	// 保存教官信息
	public boolean saveJgxx(JxglForm jxglform, HttpServletRequest request)
			throws Exception {

		String jgbh = DealString.toGBK(jxglform.getJgbh());
		String xm = DealString.toGBK(jxglform.getXm());
		String xb = DealString.toGBK(jxglform.getXb());
		String mzdm = DealString.toGBK(jxglform.getMzdm());
		String lxdh = DealString.toGBK(jxglform.getLxdh());
		String bz = DealString.toGBK(jxglform.getBz());
		String jxnd = DealString.toGBK(jxglform.getJxnd());
		String zw = DealString.toGBK(jxglform.getZw());

		boolean flg = StandardOperation.delete("jxgl_jgxxb", "jgbh", jgbh,
				request);

		if (flg) {
			flg = StandardOperation.insert("jxgl_jgxxb", new String[] { "jgbh",
					"xm", "xb", "mzdm", "lxdh", "bz", "jxnd", "zw" },
					new String[] { jgbh, xm, xb, mzdm, lxdh, bz, jxnd, zw },
					request);
		}
		return flg;
	}

	// 是否存在该教官
	public boolean isJg(JxglForm jxglform) throws Exception {
		boolean flg = false;
		DAO dao = DAO.getInstance();
		String jgbh = DealString.toGBK(jxglform.getJgbh());
		String sql = "select count(*) num from jxgl_jgxxb where jgbh = ?";
		String num = dao.getOneRs(sql, new String[] { jgbh }, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}
	
	// 保存团队获奖信息
	public boolean saveTdhjxx(JxglForm jxglform, HttpServletRequest request)
			throws Exception {

		String xn = DealString.toGBK(jxglform.getXn());
		String nd = DealString.toGBK(jxglform.getNd());
		String xq = DealString.toGBK(jxglform.getXq());
		String lddm = DealString.toGBK(jxglform.getLddm());
		String jxdm = DealString.toGBK(jxglform.getJxdm());
		String hjsj = DealString.toGBK(jxglform.getHjsj());

		boolean flg = StandardOperation.delete("jxtdhjb",
				"xn||nd||xq||lddm||jxdm", xn + nd + xq + lddm + jxdm, request);

		if (flg) {
			flg = StandardOperation.insert("jxtdhjb", new String[] { "xn",
					"nd", "xq", "lddm", "jxdm", "hjsj" }, new String[] { xn,
					nd, xq, lddm, jxdm, hjsj }, request);
		}
		return flg;
	}

	// 保存军训干部信息
	public boolean saveGbxx(JxglForm jxglform, HttpServletRequest request)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		String zgh = DealString.toGBK("".equalsIgnoreCase(jxglform.getJsdm())
				|| jxglform.getJsdm() == null ? jxglform.getXh() : jxglform
				.getJsdm());
		String jxdw = DealString.toGBK(jxglform.getJxdw());
		String jxzw = DealString.toGBK(jxglform.getJxzw());
		String bgdh = DealString.toGBK(jxglform.getBgdh());
		String zzdh = DealString.toGBK(jxglform.getZzdh());
		String sjdh = DealString.toGBK(jxglform.getSjdh());
		String xnh = DealString.toGBK(jxglform.getXnh());
		String jxnd = DealString.toGBK(jxglform.getJxnd());
		String bz = DealString.toGBK(jxglform.getBz());
		String gblx = DealString.toGBK(jxglform.getDwlx());
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if ("cx".equalsIgnoreCase(gblx)) {
				jxdw = DealString.toGBK(jxglform.getLddm());
				jxzw = DealString.toGBK(jxglform.getCxgb());
			}
		} else {
			gblx = "cx";
			jxdw = DealString.toGBK(jxglform.getLddm());
			jxzw = DealString.toGBK(jxglform.getCxgb());
		}

		boolean flg = StandardOperation.delete("nblg_jxgl_jxcxgb", "zgh", zgh,
				request);

		if (flg) {
			flg = StandardOperation.insert("nblg_jxgl_jxcxgb", new String[] {
					"zgh", "jxdw", "jxzw", "bgdh", "zzdh", "sjdh", "xnh",
					"jxnd", "bz", "gblx" }, new String[] { zgh, jxdw, jxzw,
					bgdh, zzdh, sjdh, xnh, jxnd, bz, gblx }, request);
		}
		return flg;
	}

	// 保存带队教师信息
	public boolean saveJsxx(JxglForm jxglform, HttpServletRequest request)
			throws Exception {

		String jsdm = DealString.toGBK(jxglform.getJsdm());
		String xm = DealString.toGBK(jxglform.getXm());
		String xb = request.getParameter("xbV");
		String mzdm = DealString.toGBK(request.getParameter("mzdmV"));
		String lxdh = DealString.toGBK(jxglform.getLxdh());
		String bz = DealString.toGBK(jxglform.getBz());
		String jxnd = DealString.toGBK(jxglform.getJxnd());
		String zzdh = DealString.toGBK(jxglform.getZzdh());
		String sjhm = DealString.toGBK(jxglform.getSjhm());
		String zw = DealString.toGBK(jxglform.getZw());
		String xydm = request.getParameter("xydmV");

		boolean flg = StandardOperation.delete("jxgl_ddjsxxb", "jsdm", jsdm,
				request);

		if (flg) {
			flg = StandardOperation.insert("jxgl_ddjsxxb", new String[] {
					"jsdm", "xm", "xb", "mzdm", "lxdh", "bz", "zzdh", "sjhm",
					"jxnd", "xydm", "zw" }, new String[] { jsdm, xm, xb, mzdm,
					lxdh, bz, zzdh, sjhm, jxnd, xydm, zw }, request);
		}
		return flg;
	}

	// 保存学生成绩
	public boolean saveJxcj(String[] xhArray, String[] ndArray,
			String[] cjArray, String lrr, HttpServletRequest request)
			throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "";
		boolean flg = false;
		StringBuffer sb = new StringBuffer();
		if(xhArray!=null){
			for (int i = 0; i < xhArray.length; i++) {
				sql = "select * from jxgl_cjb where xh=? and xn=?";
				String tag = dao.returntag(sql, new String[] { xhArray[i],
						ndArray[i] });
				if ("notempty".equalsIgnoreCase(tag)) {
					sql = "update jxgl_cjb set cj = '"
							+ DealString.toGBK(cjArray[i]) + "' where xh||xn = '"
							+ xhArray[i] + ndArray[i] + "'";
					sb.append(sql);
					sb.append("!!#!!");
				} else {
					sql = "insert into jxgl_cjb(xh,xn,cj,lrr) values('"
							+ xhArray[i] + "','" + ndArray[i] + "','"
							+ DealString.toGBK(cjArray[i]) + "','" + lrr + "')";
					sb.append(sql);
					sb.append("!!#!!");
				}
			}
		}
		
		String[] pksql = sb.toString().split("!!#!!");
		dao.runBatch(pksql);

		return flg;
	}

	// 删除教官信息
	public boolean delJgxx(String jgbh, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jxgl_jgxxb", "jgbh", jgbh,
				request);

		return flg;
	}

	// 删除团队获奖信息
	public boolean delTdxx(String pk, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jxtdhjb",
				"xn||nd||xq||lddm||jxdm", pk, request);

		return flg;
	}

	// 删除干部信息
	public boolean delGbxx(String zgh, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("nblg_jxgl_jxcxgb", "zgh", zgh,
				request);

		return flg;
	}

	// 删除带队教师信息
	public boolean delJsxx(String jsdm, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jxgl_ddjsxxb", "jsdm", jsdm,
				request);

		return flg;
	}

	public String getJxjz(String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select outJxglbz(?) menuTxt from dual";
		return dao.getOneRs(sql, new String[] { nj }, "menuTxt");
	}

	// 获得建制等级数量
	public String getJzNum() {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from jxjzdj ";
		return dao.getOneRs(sql, new String[] {}, "num");
	}

	// 得到最大编制名称
	public HashMap<String, String> getMaxJz() {
		DAO dao = DAO.getInstance();
		String sql = "select jzdm,jzmc from (select jzdm,jzmc from jxjzdj order by jzdm) where rownum = '1'";
		return dao
				.getMap(sql, new String[] {}, new String[] { "jzdm", "jzmc" });
	}

	// 得到次大编制名称
	public HashMap<String, String> getnextMaxJz() {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select a.*, rownum r from (select jzdm, jzmc from jxjzdj order by jzdm) a) where r = 2";
		return dao
				.getMap(sql, new String[] {}, new String[] { "jzdm", "jzmc" });
	}

	// 得到最小编制名称
	public HashMap<String, String> getMinJz() {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select jzdm, jzmc from jxjzdj order by jzdm desc) where rownum = 1";
		return dao
				.getMap(sql, new String[] {}, new String[] { "jzdm", "jzmc" });
	}

	// 得到次小编制名称
	public HashMap<String, String> getnextMinJz() {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select * from (select jzdm, jzmc, rownum num from jxjzdj order by jzdm desc)) where num = 2";
		return dao
				.getMap(sql, new String[] {}, new String[] { "jzdm", "jzmc" });
	}

	// 得到次小编制列表
	public List<HashMap<String, String>> getNextMinJz(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select a.bzdm, NVL((select getJxglbzss(a.bzdm, a.nj) sT from dual), '-') || a.bzmc bzmc from"
				+ " jxbzdmb a, (select * from (select t.*,rownum num from (select jzdm, jzmc"
				+ " from jxjzdj order by jzdm desc) t) where num = 2) b where a.bzdj = b.jzdm and a.nj = ?";
		return dao.getList(sql, new String[] { jxnd }, new String[] { "bzdm",
				"bzmc" });
	}

	// 得到辅导员所负责班级列表
	public List<HashMap<String, String>> getFdyBjList(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select b.bjdm, b.bjmc from fdybjb a, view_njxyzybj b where a.bjdm = b.bjdm"
				+ " and a.zgh = ? order by b.bjdm";
		return dao.getList(sql, new String[] { zgh }, new String[] { "bjdm",
				"bjmc" });
	}

	// 得到辅导员所负责学院列表
	public List<HashMap<String, String>> getFdyXyList(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct(b.xydm), b.xymc from fdybjb a, view_njxyzybj b"
				+ " where a.bjdm = b.bjdm and a.zgh = ? order by b.xydm";
		return dao.getList(sql, new String[] { zgh }, new String[] { "xydm",
				"xymc" });
	}

	// 得到辅导员所负责专业列表
	public List<HashMap<String, String>> getFdyZyList(String zgh) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct(b.zydm), b.zymc from fdybjb a, view_njxyzybj b"
				+ " where a.bjdm = b.bjdm and a.zgh = ? order by b.zydm";
		return dao.getList(sql, new String[] { zgh }, new String[] { "zydm",
				"zymc" });
	}

	// 得到已建制列表
	public List<HashMap<String, String>> getLdList(String jxnd) {
		DAO dao = DAO.getInstance();
		String minJz = getMinJz().get("jzdm");
		String sql = "select '' bzdm, '---请选择---' bzmc, '000' bzdj from dual"
				+ " union select bzdm, (select getJxglbzss(bzdm, nj) sT from dual) || bzmc bzmc"
				+ ",bzdj from jxbzdmb where bzdj <> ?";
		sql += Base.isNull(jxnd) ? "" : " and nj = '" + jxnd + "'";
		sql += " order by bzdj";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { minJz }, new String[] { "bzdm", "bzmc" });
		return list;
	}

	/**
	 * 获得学生连队列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getXsLdList() {
		
		DAO dao = DAO.getInstance();
		String sql = "select distinct ldbh lddm,ldmc from view_jxmdxx where ldbh is not null order by ldbh";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] {  }, new String[] { "lddm", "ldmc" });
		return list;
	}
	
	// 得到已建制列表
	public List<HashMap<String, String>> getLdListMc(String xh, String jxnd) {
		DAO dao = DAO.getInstance();
		String nextMinBz = getnextMinJz().get("jzdm");
		String sql = "select bzdm, (select getJxglbzss(bzdm, nj) sT from dual) bzmc"
				+ " from jxbzdmb a where bzdj = ? and nj = ? and exists (select 1"
				+ " from jxgl_jxmdb b where b.xh = ? and a.bzdm = b.ldbh)";				
		return dao.getList(sql, new String[] { nextMinBz, jxnd, xh },
				new String[] { "bzdm", "bzmc" });
	}
	
	// 获得团队获奖连队列表
	public List<HashMap<String, String>> getTdList(String jxnd) {
		DAO dao = DAO.getInstance();
		String maxBz = getMaxJz().get("jzdm");
		String nextMaxBz = getnextMaxJz().get("jzdm");
		String query=Base.isNull(jxnd)?" is null ":" like ? ";
		String[] arr=Base.isNull(jxnd)? new String[] { maxBz, nextMaxBz }:new String[] { jxnd, maxBz, jxnd, nextMaxBz };
		jxnd=Base.isNull(jxnd)?"%":jxnd;
		
		String sql = "select * from (select '' bzdm,'----请选择----'bzmc from dual union"
				+ " select b.bzdm,a.bzmc||b.bzmc bzmc from (select a.bzdm, a.bzmc from jxbzdmb a"
				+ " where nj "+query+" and a.bzdj = ?) a, (select a.bzdm, a.bzmc, a.sjdm from jxbzdmb a"
				+ " where nj "+query+" and a.bzdj = ?) b where b.sjdm = a.bzdm) order by bzdm nulls first ";
		return dao.getList(sql, arr,
				new String[] { "bzdm", "bzmc" });
	}

	// 得到已建制列表(含最小编制)
	public List<HashMap<String, String>> getLdListB(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select '0000' bzdm, '---请选择---' bzmc, '000' bzdj from dual"
				+ " union select bzdm, (select getJxglbzss(bzdm, nj) sT from dual) || bzmc bzmc"
				+ ",bzdj from jxbzdmb";
		sql+=Base.isNull(jxnd)?"":"nj = '"+jxnd+"'";
		sql+=" order by bzdj";
		return dao.getList(sql, new String[] {}, new String[] { "bzdm",
				"bzmc" });
	}

	// 获得带队教官下拉框信息
	public List<HashMap<String, String>> getJgList(String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct jgbh,xm from jxgl_jgxxb where jxnd = ? order by jgbh";
		return dao.getList(sql, new String[] { nj }, new String[] { "jgbh",
				"xm" });
	}

	// 获得带队老师下拉框信息
	public List<HashMap<String, String>> getJsList(String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct jsdm,xm from jxgl_ddjsxxb where jxnd = ? order by jsdm";
		return dao.getList(sql, new String[] { nj }, new String[] { "jsdm",
				"xm" });
	}

	// 获得军训服装下拉框信息
	public List<HashMap<String, String>> getFzList() {
		DAO dao = DAO.getInstance();
		String sql = "select distinct fzdm, fzmc from nblg_jxgl_jxfz order by fzdm";
		return dao.getList(sql, new String[] {},
				new String[] { "fzdm", "fzmc" });
	}

	// 获得参训干部职务名下拉列表
	public List<HashMap<String, String>> getBzzwList(String bzdm) {
		DAO dao = DAO.getInstance();
		String bzdj = dao.getOneRs("select bzdj from jxbzdmb where bzdm = ?",
				new String[] { bzdm }, "bzdj");
		String sql = "select * from (select '0000' zwdm, '----请选择----' zwmc from dual union"
				+ " select zwdm,zwmc from nblg_jxgl_bzzwb where bzdm=? ) order by zwdm";
		return dao.getList(sql, new String[] { bzdj }, new String[] { "zwdm",
				"zwmc" });

	}

	// 获得军训单位下拉列表
	public List<HashMap<String, String>> getdwList() {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select '0000' bzdm, '----请选择----' bzmc from dual union"
				+ " select dwdm bzdm, dwmc bzmc from nblg_jxgl_jxdw) order by bzdm";
		return dao.getList(sql, new String[] {},
				new String[] { "bzdm", "bzmc" });
	}

	// 获得军训职位下拉列表
	public List<HashMap<String, String>> getzwList(String dwdm) {
		DAO dao = DAO.getInstance();
		String sql = "";
		if (dwdm != null && !"".equalsIgnoreCase(dwdm)) {
			sql = "select * from (select '0000' zwdm, '----请选择----' zwmc from dual union"
					+ " select zwdm, zwmc from nblg_jxgl_jxzw where dwdm = ? ) order by zwdm";
			return dao.getList(sql, new String[] { dwdm }, new String[] {
					"zwdm", "zwmc" });
		} else {
			sql = "select '0000' zwdm, '----请选择----' zwmc from dual ";
			return dao.getList(sql, new String[] {}, new String[] { "zwdm",
					"zwmc" });
		}
	}

	// 获得军训建制等级
	public List<String> getAllJz() throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select jzdm from jxjzdj order by jzdm";
		return dao.getList(sql, new String[] {}, "jzdm");
	}

	// 获得上级监制等级
	public List<HashMap<String, String>> getSjlJz(String sjdm, String nj)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select bzdm, bzmc from jxbzdmb where bzdj = (select bzdj from jxbzdmb where bzdm = ?)  and nj = ?";
		return dao.getList(sql, new String[] { sjdm, nj }, new String[] {
				"bzdm", "bzmc" });
	}

	public HashMap<String, String> queryOne(String[] colList, String pkValue,
			HashMap<String, String> map, String sql) {
		DAO dao = DAO.getInstance();
		// 通过主键查询单个数据用 获得HashMap<String, String>形式,并覆盖之前传入的HashMap里的值的通用方法
		int size = colList.length - 1;

		String[] rsTmp = dao.getOneRs(sql, new String[] { pkValue }, colList);
		for (int i = 0; i <= size; i++) {
			map.put(colList[i], (rsTmp != null) ? rsTmp[i] : "");

		}

		return map;
	}

	// 根据职工号查询教师个人信息
	public HashMap<String, String> getJsxx(String jsid) {
		DAO dao = DAO.getInstance();
		String sql = "select zgh jsdm, xm, xb,bmdm xydm, mz mzdm from view_fdyxx where zgh=?";
		return dao.getMap(sql, new String[] { jsid }, new String[] { "jsdm",
				"xm", "xb", "xydm", "mzdm" });
	}

	// 根据职工号查询教师个人信息
	public HashMap<String, String> getGbxx(String jsid) {
		DAO dao = DAO.getInstance();
		String sql = "select zgh jsdm, xm, xb,bmdm xydm,bmmc, mz mzdm from view_fdyxx where zgh=?";
		return dao.getMap(sql, new String[] { jsid }, new String[] { "jsdm",
				"xm", "xb", "xydm", "mzdm", "bmmc" });
	}

	// 根据学号查询教学生信息
	public HashMap<String, String> getXsxx(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xh, a.xm, a.xb, a.xydm, a.xymc bmmc,b.mz mzdm"
				+ " from view_xsjbxx a,view_xsxxb b where a.xh = ? and a.xh = b.xh";
		return dao.getMap(sql, new String[] { xh }, new String[] { "xh", "xm",
				"xb", "xydm", "mzdm", "bmmc" });
	}

	// 获取编制代码
	public String getJxbzdm() {
		DAO dao = DAO.getInstance();
		String sql = "select d.bzdm from (select rownum num, c.bzdm, c.tempbzdm"
				+ " from (select a.bzdm, (to_char(b.bzdm) - to_char(a.bzdm)) tempbzdm"
				+ " from (select rownum num ,t.* from (select t.* from jxbzdmb t order by bzdm) t)  a,"
				+ " (select rownum - 1 num,t.* from (select t.* from jxbzdmb t order by bzdm) t)"
				+ " b where a.num = b.num) c where c.tempbzdm > 1) d where d.num = 1";
		String bzdm1 = dao.getOneRs(sql, new String[] {}, "bzdm");
		int newDm = 0;
		if (bzdm1 != null && !"".equals(bzdm1)) {
			newDm = Integer.parseInt(bzdm1) + 1;
		}
		sql = " select MAX(t.bzdm)+1 bzdm from jxbzdmb t";
		String bzdm2 = dao.getOneRs(sql, new String[] {}, "bzdm");
		if (bzdm2 == null || "".equals(bzdm2)) {
			newDm = 1;
		}
		if ((bzdm1 == null || "".equals(bzdm1))
				&& (bzdm2 != null && !"".equals(bzdm2))) {
			newDm = Integer.parseInt(bzdm2);
		}
		String str = String.valueOf(newDm);
		if (str.length() == 1) {
			str = "000" + str;
		} else if (str.length() == 2) {
			str = "00" + str;
		} else if (str.length() == 3) {
			str = "0" + str;
		}
		return str;
	}

	// 获得建制名称
	public HashMap<String, String> getBzdj(String sjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select jzdm, jzmc from (select a.*, rownum num from (select t.* from jxjzdj t order by t.jzdm) a)"
				+ " where num = (select num + 1 from (select (rownum) num, jzdm from (select t.* from jxjzdj t order by t.jzdm))"
				+ " where jzdm = ?)";
		return dao.getMap(sql, new String[] { sjdm }, new String[] { "jzdm",
				"jzmc" });
	}

	//得到上级编制学年
	public String getSjXn(String sjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select * from jxbzdmb where bzdm = ?";
		return dao.getOneRs(sql, new String[] { sjdm }, "xn");
	}
	
	// 得到上级带队老师
	public String getJsOne(String sjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select a.jsdm from jxbzdmb t, jxgl_jsddxxb b, jxgl_ddjsxxb a"
				+ " where b.sfzld = t.bzdm and a.jsdm = t.jsdm and b.sfzld = ?";
		return dao.getOneRs(sql, new String[] { sjdm }, "jsdm");
	}

	// 得到上级带队教官
	public String getJgOne(String sjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select a.jgbh from jxbzdmb t, jxgl_jgddxxb b, jxgl_jgxxb a where "
				+ " b.sfzld = t.bzdm and a.jgbh = t.jgbh and b.sfzld = ?";
		return dao.getOneRs(sql, new String[] { sjdm }, "jgbh");
	}

	// 得到上级连队性别
	public String getSjxb(String sjdm) {
		DAO dao = DAO.getInstance();
		String sql = "select xb from jxbzdmb where bzdm = ?";
		return dao.getOneRs(sql, new String[] { sjdm }, "xb");
	}

	// 保存建制信息
	public boolean saveJxjz(JxglForm myForm, String doS,
			HttpServletRequest request) throws Exception {
		boolean bFlag = false;
		String xxdm = StandardOperation.getXxdm();
		String nj = Base.chgNull(myForm.getNj(), "", 1);
		String bzdm = Base.chgNull(myForm.getBzdm(), "", 1);
		String bzmc = Base.chgNull(myForm.getBzmc(), "", 1);
		String bzdj = Base.chgNull(myForm.getBzdj(), "", 1);
		String jsdm = Base.chgNull(myForm.getJsdm(), "", 1);
		String jgbh = Base.chgNull(myForm.getJgbh(), "", 1);
		String sjdm = Base.chgNull(myForm.getSjdm(), "", 1);
		String bz = Base.chgNull(myForm.getBz(), "", 1);
		String xn = Base.chgNull(myForm.getXn(), "", 1);
		String xb = myForm.getXb() == null
				|| "".equalsIgnoreCase(myForm.getXb()) ? "-" : myForm.getXb();
		// 宁波理工 军训服装
		String jxfz = Base.chgNull(myForm.getJxfz(), "", 1);
		// zdy = getJsXm(zdy);
		// jgmc = getJgXm(jgmc);

		String sHave = isJxJzCf(bzdm, nj);
		if ("-1".equalsIgnoreCase(sHave)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				bFlag = StandardOperation.insert("jxbzdmb", new String[] {
						"nj", "bzdm", "bzmc", "bzdj", "jsdm", "jgbh", "sjdm",
						"bz", "xb", "jxfz","xn" }, new String[] { nj, bzdm, bzmc,
						bzdj, jsdm, jgbh, sjdm, bz, xb, jxfz,xn }, request);
			} else {
				bFlag = StandardOperation.insert("jxbzdmb", new String[] {
						"nj", "bzdm", "bzmc", "bzdj", "jsdm", "jgbh", "sjdm",
						"bz", "xb","xn" }, new String[] { nj, bzdm, bzmc, bzdj,
						jsdm, jgbh, sjdm, bz, xb,xn }, request);
			}
		} else if (!"add".equalsIgnoreCase(doS)) {
			bFlag = StandardOperation.update("jxbzdmb", new String[] { "bzmc",
					"bzdj", "jsdm", "jgbh", "sjdm", "bz","xn" }, new String[] {
					bzmc, bzdj, jsdm, jgbh, sjdm, bz,xn }, "bzdm||nj", bzdm + nj,
					request);
		} else {
			request.setAttribute("have", "have");
		}
		return bFlag;
	}

	// 获得带队老师姓名
	public String getJsXm(String jsdm) {
		DAO dao = DAO.getInstance();
		String sql = "select xm from jxgl_ddjsxxb where jsdm = ?";
		return dao.getOneRs(sql, new String[] { jsdm }, "xm");
	}

	// 获得教官姓名
	public String getJgXm(String jgbh) {
		DAO dao = DAO.getInstance();
		String sql = "select xm from jxgl_jgxxb where jgbh = ?";
		return dao.getOneRs(sql, new String[] { jgbh }, "xm");
	}

	//TODO
	// 获得未分配军训编制的班级
	public List<HashMap<String, String>> getNoFpList(String nj, String xydm,
			String zydm, String xb,String xn) {
		DAO dao = DAO.getInstance();

		String xsxb = "";
		if ("1".equalsIgnoreCase(xb)) {
			xsxb = "男";
		} else if ("2".equalsIgnoreCase(xb)) {
			xsxb = "女";
		}else{
			xsxb = "%";
		}
		
		String sql = "select distinct a.bjdm, a.bjmc from view_njxyzybj a,(select t.bjdm, count(t.xh) num"
				+ " from view_xsjbxx t where t.xb like ? group by t.bjdm) c where a.nj = ? and a.bjdm=c.bjdm"
				+ " and xydm like ? and zydm like ? and not exists (select 1  from jxbzdmb b"
				+ " where a.bjmc = b.bzmc and b.nj = ? and b.xn = ? and b.xb like ? ) and not "
				+ " exists(select b.bzmc from jxbzdmb b where b.bzmc = a.bjmc and b.nj = ? and b.xn = ? and xb = '-')";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {xsxb,
				nj, xydm, zydm, nj,xn, xb, nj ,xn}, new String[] { "bjdm", "bjmc" });

		return list;
	}

	// 获得已分配军训编制的班级
	public List<HashMap<String, String>> getHadFpList(String sjdm, String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select b.bjdm bjdm, a.bzmc bjmc from jxbzdmb a,view_njxyzybj  b where a.sjdm = ?"
				+ " and a.nj = ? and a.bzmc = b.bjmc";
		return dao.getList(sql, new String[] { sjdm, nj }, new String[] {
				"bjdm", "bjmc" });
	}

	public String isJxJzCf(String bzdm, String nj) throws Exception {
		DAO dao = DAO.getInstance();
		String sFlag = "-1";
		String sql = "select count(*) num from jxbzdmb where bzdm = ? and nj = ?";
		String num = dao.getOneRs(sql, new String[] { bzdm, nj }, "num");
		if (!num.equalsIgnoreCase("0")) {
			sFlag = "1";
		} else {
			sql = "select count(*) num from view_njxyzybj where bjdm = ? and nj = ?";
			num = dao.getOneRs(sql, new String[] { bzdm, nj }, "num");
			if (!num.equalsIgnoreCase("0")) {
				sFlag = "1";
			}
		}
		return sFlag;
	}

	// 添加教官连队信息
	public boolean addJgxx(String jgbh, String sfzld, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jxgl_jgddxxb", "jgbh||sfzld",
				jgbh + sfzld, request);

		if (flg) {
			flg = StandardOperation.insert("jxgl_jgddxxb", new String[] {
					"jgbh", "sfzld" }, new String[] { jgbh, sfzld }, request);
		}
		return flg;
	}

	// 添加教师连队信息
	public boolean addJsxx(String jsdm, String sfzld, HttpServletRequest request)
			throws Exception {

		boolean flg = StandardOperation.delete("jxgl_jsddxxb", "jsdm||sfzld",
				jsdm + sfzld, request);

		if (flg) {
			flg = StandardOperation.insert("jxgl_jsddxxb", new String[] {
					"jsdm", "sfzld" }, new String[] { jsdm, sfzld }, request);
		}
		return flg;
	}

	// 添加军训名单
	public boolean addJxmd(String[] bjdm, String nj, String sjdm, String xb,String xn)
			throws Exception {

		DAO dao = DAO.getInstance();

		boolean flg = false;
		String xsxb = "";
		if ("1".equalsIgnoreCase(xb)) {
			xsxb = "男";
		} else if ("2".equalsIgnoreCase(xb)) {
			xsxb = "女";
		}
		xsxb = Base.chgNull(xsxb, "%", 0);

		// StringBuffer delXhSQL = new StringBuffer();

		// delXhSQL.append("delete jxgl_jxmdb a where a.nd=? and a.ldbh = ? and
		// a.xn= ?");
		// delXhSQL.append(" and exists in (select xh from view_xsjbxx b where
		// a.xh= b.xh and b.xb=?)");
		// boolean flg = dao.runUpdate(delXhSQL.toString(), new String[] { nj,
		// sjdm, xn,xsxb });
		//
		if (bjdm != null && bjdm.length > 0) {
			StringBuffer sb = new StringBuffer();
			String sql = "";
			for (int i = 0; i < bjdm.length; i++) {
				if (isLs(bjdm[i])) {
					sql = "delete from jxgl_jxmdb a where  nd='"
							+ nj
							+ "' and xn='"
							+ xn
							+ "' and exists (select 1 from view_xsjbxx b where b.bjdm='"
							+ bjdm[i] + "' and a.xh=b.xh and b.xb = '" + xsxb
							+ "')";
					sb.append(sql);
					sb.append("!!#!!");
					sql = "insert into jxgl_jxmdb(xh,nd,ldbh,xn) (select xh,'"
							+ nj + "','" + sjdm + "' ,'" + xn
							+ "' from view_xsjbxx where bjdm='" + bjdm[i]
							+ "' and xb like '" + xsxb + "')";
					sb.append(sql);
					sb.append("!!#!!");
				} else if (isXs(bjdm[i])) {
					sql = "delete from jxgl_jxmdb a where  nd='"
							+ nj
							+ "' and xn='"
							+ xn
							+ "' and exists (select 1 from view_newstuinfo b where b.bjdm='"
							+ bjdm[i] + "' and a.xh=b.ksh and b.xb= '" + xsxb
							+ "')";
					sb.append(sql);
					sb.append("!!#!!");
					sql = "insert into jxgl_jxmdb(xh,nd,ldbh,isxs,xn) (select ksh,'"
							+ nj
							+ "','"
							+ sjdm
							+ "','1','"
							+ xn
							+ "' from view_newstuinfo where bjdm='"
							+ bjdm[i]
							+ "' and xb like'" + xsxb + "')";
					sb.append(sql);
					sb.append("!!#!!");
				}
			}
			String[] pksql = sb.toString().split("!!#!!");
			int[] res = dao.runBatch(pksql);
			for (int i = 0; i < res.length; i++) {
				flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
				if (!flg)
					break;
			}
		}
		return flg;
	}

	// 删除该编制下的所有班级
	public boolean delAllBz(String nj, String sjdm) throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete jxgl_jxmdb a where a.nd=? and a.ldbh = ?";
		dao.runUpdate(sql, new String[] { nj, sjdm });

		sql = "select bzdm from jxbzdmb where sjdm=? and nj=?";
		List<HashMap<String, String>> bzList = dao.getList(sql, new String[] {
				sjdm, nj }, new String[] { "bzdm" });

		if (bzList != null && bzList.size() > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < bzList.size(); i++) {

				sql = "delete from jxgl_jgddxxb where sfzld = '"
						+ bzList.get(i) + "'";
				sb.append(sql);
				sb.append("!!#!!");

				sql = "delete from jxgl_jsddxxb where sfzld = '"
						+ bzList.get(i) + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
			String[] pksql = sb.toString().split("!!#!!");
			dao.runBatch(pksql);
		}

		sql = "delete from jxbzdmb where sjdm=? and nj = ?";
		boolean flg = dao.runUpdate(sql, new String[] { sjdm, nj });
		return flg;
	}

	// 判断是否新生
	public boolean isXs(String bjdm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flg = false;
		String sql = "select count(a.xh) num from view_newstuinfo a where a.bjdm = ?";
		String num = dao.getOneRs(sql, new String[] { bjdm }, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}

	// 判断是否老生
	public boolean isLs(String bjdm) throws Exception {
		DAO dao = DAO.getInstance();
		boolean flg = false;
		String sql = "select count(a.xh) num from view_xsjbxx a where a.bjdm = ?";
		String num = dao.getOneRs(sql, new String[] { bjdm }, "num");
		if (!"0".equalsIgnoreCase(num)) {
			flg = true;
		}
		return flg;
	}

	// 设置班级列表
	public boolean setBjList(String bjdm) throws Exception {
		DAO dao = DAO.getInstance();

		String sqlXh = "select bzdm from jxbzdmb a, view_njxyzybj b where a.bzmc = b.bjmc and b.bjdm = ?";
		String sqlKsh = "select bzdm from jxbzdmb a, view_jxgl_xsbjb b where a.bzmc = b.bjmc and b.bjdm = ?";
		String sqlJg = "delete from jxgl_jgddxxb where sfzld = ?";
		String sqlJs = "delete from jxgl_jsddxxb where sfzld = ?";
		String bzdmXh = dao.getOneRs(sqlXh, new String[] { bjdm }, "bzdm");
		String bzdmKsh = dao.getOneRs(sqlKsh, new String[] { bjdm }, "bzdm");
		String bzdm = "".equalsIgnoreCase(bzdmKsh) ? bzdmXh : bzdmKsh;

		dao.runUpdate(sqlJg.toString(), new String[] { bzdm });
		dao.runUpdate(sqlJs.toString(), new String[] { bzdm });
		// 非新生（有学号）
		String sql = "delete from jxgl_jxmdb where ldbh in (select b.bzdm from jxbzdmb a, jxbzdmb b"
				+ " where a.sjdm = b.bzdm and a.bzdm = ?) and exists (select 1 from view_xsjbxx c, jxbzdmb d"
				+ " where d.bzmc = c.bjmc and jxgl_jxmdb.xh = c.xh and d.bzdm = ?)";
		dao.runUpdate(sql, new String[] { bzdm, bzdm });
		// 新生（只有准考证号）
		sql = "delete from jxgl_jxmdb where ldbh in (select b.bzdm from jxbzdmb a, jxbzdmb b"
				+ " where a.sjdm = b.bzdm and a.bzdm = ?) and exists (select 1 from view_jxgl_xsbjb c, jxbzdmb d"
				+ " where d.bzmc = c.bjmc and jxgl_jxmdb.xh = c.ksh and d.bzdm = ?)";
		dao.runUpdate(sql, new String[] { bzdm, bzdm });
		sql = "delete from jxbzdmb where bzdm=?";
		dao.runUpdate(sql, new String[] { bzdm });

		return true;
	}

	// 添加军训建制
	public boolean addJxbz(String[] bjdm, String nj, String sjdm, String xb,
			String jsdm, String jgbh,String xn) throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = getMinJz();

		String sql = "";
		// 删除带队教官信息
		if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
			sql = "delete from jxgl_jgddxxb where jgbh = '"
					+ jgbh
					+ "' and sfzld in "
					+ "(select sfzld from jxgl_jgddxxb a, jxbzdmb b where a.sfzld = b.bzdm and b.sjdm = '"
					+ sjdm + "')";
			dao.runUpdate(sql, new String[] {});
		}
		// 删除带队教师信息
		if (jsdm != null && !"".equalsIgnoreCase(jsdm)) {
			sql = "delete from jxgl_jsddxxb where jsdm = '"
					+ jsdm
					+ "' and sfzld in "
					+ "(select sfzld from jxgl_jsddxxb a, jxbzdmb b where a.sfzld = b.bzdm and b.sjdm = '"
					+ sjdm + "')";
			dao.runUpdate(sql, new String[] {});
		}

		String bzdm = "";
		// String zdymc = getJsXm(jsdm);
		// String jgmc = getJgXm(jgbh);
		String bzdj = map.get("jzdm");

		sql = "delete from jxbzdmb where sjdm=? and nj =? and xb like ?";
		boolean flg = dao.runUpdate(sql, new String[] { sjdm, nj, xb });
		xb = "%".equals(xb) ? "-" : xb;
		if (flg) {
			for (int i = 0; i < bjdm.length; i++) {
				bzdm = getJxbzdm();
				String bjmc = dao.getOneRs(
						"select bjmc from view_njxyzybj where bjdm=?",
						new String[] { bjdm[i] }, "bjmc");
				sql = "insert into jxbzdmb(nj,bzdm,bzmc,bzdj,jgbh,jsdm,sjdm,xb,xn) values('"
						+ nj
						+ "','"
						+ bzdm
						+ "','"
						+ bjmc
						+ "','"
						+ bzdj
						+ "','"
						+ jgbh
						+ "','"
						+ jsdm
						+ "','"
						+ sjdm
						+ "','"
						+ xb + "','" + xn + "')";

				dao.runUpdate(sql, new String[] {});
			}
		}

		addLdJgJs(bjdm, jgbh, jsdm, sjdm);
		addJxmd(bjdm, nj, sjdm, xb, xn);
		return flg;
	}

	// 添加连队负责教官，老师
	public int[] addLdJgJs(String[] bjdm, String jgbh, String jsdm, String sjdm)
			throws Exception {

		DAO dao = DAO.getInstance();

		String[] sqlT = new String[bjdm.length * 2];
		int n = 0;
		String bzdmXh = "";
		String bzdmKsh = "";
		String bzdm = "";
		String sqlXh = "select bzdm from jxbzdmb a, view_njxyzybj b where a.bzmc = b.bjmc and b.bjdm = ?";
		String sqlKsh = "select bzdm from jxbzdmb a, view_jxgl_xsbjb b where a.bzmc = b.bjmc and b.bjdm = ?";

		for (int i = 0; i < bjdm.length; i++) {
			bzdmXh = dao.getOneRs(sqlXh, new String[] { bjdm[i] }, "bzdm");
			bzdmKsh = dao.getOneRs(sqlKsh, new String[] { bjdm[i] }, "bzdm");
			bzdm = "".equalsIgnoreCase(bzdmKsh) ? bzdmXh : bzdmKsh;
			if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
				sqlT[n] = "insert into jxgl_jgddxxb(jgbh,sfzld) values('"
						+ jgbh + "','" + bzdm + "')";
				n++;
			}

			if (jsdm != null && !"".equalsIgnoreCase(jsdm)) {
				sqlT[n] = "insert into jxgl_jsddxxb(jsdm,sfzld) values('"
						+ jsdm + "','" + bzdm + "')";
				n++;
			}
		}
		int[] res = dao.runBatch(sqlT);
		return res;
	}

	//TODO
	// 获得未分配军训编制的班级
	public List<HashMap<String, String>> getBjList(String nj, String xydm,
			String zydm, String xb, String xn) {
		DAO dao = DAO.getInstance();
		String xsxb = "";
		if ("1".equalsIgnoreCase(xb)) {
			xsxb = "男";
		} else if ("2".equalsIgnoreCase(xb)) {
			xsxb = "女";
		}else{
			xsxb = "%";
		}
		
		xydm = Base.chgNull(xydm, "%", 0);
		zydm = Base.chgNull(zydm, "%", 0);
		xsxb = Base.chgNull(xsxb, "%", 0);
		xb = "-".equals(xb) ? "%" : xb;

		String[] inputSQL = new String[] { xsxb, nj, zydm, xydm, nj, xn, xb, nj,xn  };
		String sql = "select distinct a.bjdm dm,a.bjmc mc from view_njxyzybj a,(select t.bjdm, count(t.xh) num"
				+ " from view_xsjbxx t where t.xb like ? group by t.bjdm) c where a.nj=? and zydm like ? and xydm like ?"
				+ " and not exists (select 1 from jxbzdmb b where a.bjmc = b.bzmc and b.nj = ? and b.xn = ? and b.xb like ? ) and a.bjdm=c.bjdm"
				+ " and not exists (select b.bzmc from jxbzdmb b where b.bzmc = a.bjmc and b.nj = ? and b.xn = ? and xb = '-') order by dm";

		String[] outputSQL = new String[] { "dm", "mc" };

		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}

	// 获得未分配军训编制的新生班级
	public List<HashMap<String, String>> getXsBjList(String nj, String xydm,
			String zydm, String xb, String xn) {
		DAO dao = DAO.getInstance();
		String xsxb = "";
		if ("1".equalsIgnoreCase(xb)) {
			xsxb = "男";
		} else if ("2".equalsIgnoreCase(xb)) {
			xsxb = "女";
		}

		xydm = Base.chgNull(xydm, "%", 0);
		zydm = Base.chgNull(zydm, "%", 0);
		xsxb = Base.chgNull(xsxb, "%", 0);
		xb = "-".equals(xb) ? "%" : xb;

		String[] inputSQL = new String[] { nj, zydm, xydm, nj, xn, xb, nj, xn };
		String sql = "select distinct a.bjdm dm,a.bjmc mc from view_newstuinfo a where a.nj=? and zydm like ? and xydm like ?"
				+ " and not exists (select 1 from jxbzdmb b where a.bjmc = b.bzmc and b.nj = ? and b.xn = ? and b.xb like ? )"
				+ " and not exists (select b.bzmc from jxbzdmb b where b.bzmc = a.bjmc and b.nj = ? and b.xn = ? and xb = '-') order by dm";

		String[] outputSQL = new String[] { "dm", "mc" };

		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}

	// 获得已分配军训编制的班级
	public List<HashMap<String, String>> getBjTList(String sjdm, String nj,
			String xn) {
		DAO dao = DAO.getInstance();
		String[] inputSQL = new String[] { sjdm, nj, xn };
		String sql = "select bzdm dm,bzmc mc from jxbzdmb where sjdm=? and nj = ? and xn = ?";
		String[] outputSQL = new String[] { "dm", "mc" };
		List<HashMap<String, String>> bjList = dao.getList(sql, inputSQL,
				outputSQL);

		return bjList;
	}

	// 获得新生学院列表
	public List<HashMap<String, String>> getXsXyList(String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct (xydm) xydm, xymc from view_newstuinfo where nj = ? order by xydm";
		return dao.getList(sql, new String[] { nj }, new String[] { "xydm",
				"xymc" });
	}

	// 获得新生专业列表
	public List<HashMap<String, String>> getXsZyList(String nj) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct (zydm) zydm, zymc from view_newstuinfo where nj = ? order by zydm";
		return dao.getList(sql, new String[] { nj }, new String[] { "zydm",
				"zymc" });
	}

	// 根据军训年度获得编制列表
	public List<HashMap<String, String>> chBzList(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select '0000' bzdm, '---请选择---' bzmc, '0000' bzdj from dual"
				+ " union select t.bzdm, (select getJxglbzss(t.bzdm, t.nj) sT from dual) || t.bzmc bzmc"
				+ ", t.bzdj from jxbzdmb t where t.nj = ? order by bzdj";
		return dao.getList(sql, new String[] { jxnd }, new String[] { "bzdm",
				"bzmc" });
	}

	public List<HashMap<String, String>> getZyList(String xydm) {
		DAO dao = DAO.getInstance();
		String sql = "select * from (select '' zydm,'---请选择---'zymc from dual union "
				+ " select distinct(zydm),zymc from view_newstuinfo where xydm = ?"
				+ " ) order by zydm nulls first";
		return dao.getList(sql, new String[] { xydm }, new String[] { "zydm",
				"zymc" });
	}

	// 获得建制信息
	public HashMap<String, String> getJxjzxx(String bzdm, String nj)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> stuList = new HashMap<String, String>();
		String sql = "select xn,nj,bzdm,bzmc,bzdj,(select b.jzmc from jxjzdj b where a.bzdj = b.jzdm) bzdjmc,"
				+ " jsdm,jgbh,sjdm,(select getJxglbzss(?,?) sT from dual) ssjz,bz,xb from jxbzdmb a where bzdm=? and nj=?";
		String[] colList = new String[] { "xn","nj", "bzdm", "bzmc", "bzdj",
				"bzdjmc", "jsdm", "jgbh", "sjdm", "ssjz", "bz", "xb" };
		String[] sLen = dao.getOneRs(sql, new String[] { bzdm, nj, bzdm, nj },
				colList);
		if (sLen != null) {
			for (int i = 0; i < colList.length; i++) {
				stuList.put(colList[i], sLen[i] != null ? sLen[i].toString()
						: "");
			}
		}
		return stuList;
	}

	public boolean delJxbz(String bzdm, String nj) throws Exception {

		DAO dao = DAO.getInstance();
		boolean b = false;
		String sT = Base.chgNull(dao.getOneRs(
				"select getJxglbz(?,?) sT from dual",
				new String[] { bzdm, nj }, "sT"), "!!##sptilOne##!!", 1);
		String[] stL = sT.split("!!##sptilOne##!!");
		String[] sqlT = new String[stL.length];

		int i = 1;
		StringBuffer delSQL = new StringBuffer(
				"delete jxgl_jxmdb a where a.nd=? and a.ldbh in ('" + bzdm
						+ "'");
		StringBuffer delJgSQL = new StringBuffer(
				"delete jxgl_jgddxxb a where a.sfzld in ('" + bzdm + "'");
		StringBuffer delJsSQL = new StringBuffer(
				"delete jxgl_jsddxxb a where a.sfzld in ('" + bzdm + "'");
		for (; i < stL.length; i++) {
			delSQL.append(",'");
			delSQL.append(stL[i]);
			delSQL.append("'");

			delJgSQL.append(",'");
			delJgSQL.append(stL[i]);
			delJgSQL.append("'");

			delJsSQL.append(",'");
			delJsSQL.append(stL[i]);
			delJsSQL.append("'");

			sqlT[i - 1] = "delete jxbzdmb where bzdm='" + stL[i] + "' and nj='"
					+ nj + "'";
		}

		delSQL.append(")");
		delJgSQL.append(")");
		delJsSQL.append(")");
		dao.runUpdate(delSQL.toString(), new String[] { nj });
		dao.runUpdate(delJgSQL.toString(), new String[] {});
		dao.runUpdate(delJsSQL.toString(), new String[] {});

		if (i > 1) {
			sqlT[i - 1] = "delete jxbzdmb where bzdm='" + bzdm + "' and nj='"
					+ nj + "'";
		} else {
			sqlT = new String[1];
			sqlT[0] = "delete jxbzdmb where bzdm='" + bzdm + "' and nj='" + nj
					+ "'";
		}
		int[] res = dao.runBatch(sqlT);
		for (i = 0; i < res.length; i++) {
			b = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!b)
				break;
		}
		return b;
	}

	public List searchJsghInfo(JxglForm model) {
		DAO dao = DAO.getInstance();

		String tableName = "view_fdyxx";
		String sql = "select zgh,xm,xb,bmmc xymc,xl,sfmc from " + tableName
				+ " ";
		String[] outputValue = { "zgh", "xm", "xb", "xymc", "xl", "sfmc" };
		String whereSql = getWhereSql(model).toString();

		return dao.rsToVator(sql + whereSql, value != null ? value
				.toArray(new String[0]) : new String[] {}, outputValue);
	}

	public StringBuffer getWhereSql(JxglForm model) {
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
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
			sb.append(" and xm like '%" + xm + "%'");
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

	// 获得机关干部列表
	public List<HashMap<String, String>> getJggbList(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xm||case when a.xb='女' then '(女)' else '' end xm, a.bmmc, a.jxdw, (select b.dwmc from nblg_jxgl_jxdw b where a.jxdw = b.dwdm) dwmc,"
				+ " a.jxzw, (select c.zwmc from nblg_jxgl_jxzw c where a.jxzw = c.zwdm) zwmc, a.bgdh, a.zzdh, a.sjdh,"
				+ "case when a.xnh is null then  '' when a.gblx = 'xs' then a.xnh || '（学生）' else a.xnh end xnh,"
				+ " a.dwlx from view_nblg_jxgbxx a where a.dwlx = 'jg' and a.jxnd = ? order by jxdw, jxzw";
		System.out.println("Jggb:" + sql);
		System.out.println("jxnd:" + jxnd);
		return dao.getList(sql, new String[] { jxnd }, new String[] { "xm",
				"bmmc", "dwmc", "zwmc", "bgdh", "zzdh", "sjdh", "xnh" });
	}

	// 获得营级列表
	public List<HashMap<String, String>> getYjgbList(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct (a.bzdm), c.xm||case when c.xb='女' then '(女)' else '' end xm, c.xymc bmmc, a.bzmc dwmc, a.bzmc || '指导员' zwmc, c.lxdh bgdh,"
				+ " c.zzdh, c.sjhm sjdh, '' xnh from jxbzdmb a, (select * from (select * from (select jzdm, jzmc, rownum num"
				+ " from jxjzdj order by jzdm)) where num = 1) b, view_jxgl_jsxx c where a.bzdj = b.jzdm"
				+ " and a.jsdm = c.jsdm and a.nj = ? order by a.bzdm";
		System.out.println("Yjgb:" + sql);
		System.out.println("jxnd:" + jxnd);
		return dao.getList(sql, new String[] { jxnd }, new String[] { "bzdm",
				"xm", "bmmc", "dwmc", "zwmc", "bgdh", "zzdh", "sjdh", "xnh" });
	}

	// 获得连级列表
	public List<HashMap<String, String>> getLjgbList(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct (a.bzdm) ljdm, c.xm ||case when c.xb='女' then '(女)' else '' end xm, c.xymc, a.bzdm,d.bzdm yjdm, d.bzmc yjmc, a.bzmc ljmc,"
				+ " a.bzmc || '指导员' zwmc, c.lxdh bgdh, c.zzdh, c.sjhm sjdh, '' xnh from jxbzdmb a,"
				+ " (select * from (select * from (select jzdm, jzmc, rownum num from jxjzdj order by jzdm))"
				+ " where num = 2) b, view_jxgl_jsxx c, jxbzdmb d where a.bzdj = b.jzdm and a.jsdm = c.jsdm"
				+ " and a.sjdm = d.bzdm and a.nj = ? order by d.bzdm";
		System.out.println("Ljgb:" + sql);
		System.out.println("jxnd:" + jxnd);
		return dao.getList(sql, new String[] { jxnd }, new String[] { "ljdm",
				"bzdm", "xm", "xymc", "yjdm", "yjmc", "ljmc", "zwmc", "bgdh",
				"zzdh", "sjdh", "xnh" });
	}

	// 获得排级列表
	public List<HashMap<String, String>> getPjgbList(String jxnd) {
		DAO dao = DAO.getInstance();
		String sql = "select a.xm||case when a.xb='女' then '(女)' else '' end xm, a.bmmc, a.jxdw, c.bzdm, c.bzmc, (select getJxglbzss(a.jxdw, a.jxnd) sT from dual) dwmc,"
				+ " a.jxzw, b.bzmc || (select c.zwmc from nblg_jxgl_bzzwb c where a.jxzw = c.zwdm) zwmc, a.bgdh, "
				+ " a.zzdh, a.sjdh, case when a.xnh is null then  '' when a.gblx = 'xs' then a.xnh || '（学生）' else a.xnh"
				+ " end xnh,a.dwlx from view_nblg_jxgbxx a, jxbzdmb b, jxbzdmb c where a.dwlx = 'cx' "
				+ " and a.jxdw = b.bzdm and b.sjdm = c.bzdm and b.nj= ? order by jxdw, jxzw";
		System.out.println("Pjgb:" + sql);
		System.out.println("jxnd:" + jxnd);
		return dao.getList(sql, new String[] { jxnd }, new String[] { "xm",
				"bmmc", "jxdw", "bzdm", "bzmc", "dwmc", "jxzw", "zwmc", "bgdh",
				"zzdh", "sjdh", "xnh" });
	}

	// 获得编制列表
	public List<HashMap<String, String>> getBzList(String jxnd) {
		DAO dao = DAO.getInstance();
		// String maxBz = getMaxJz().get("jzdm");
		String minBz = getMinJz().get("jzdm");
		String nextMinBz = getnextMinJz().get("jzdm");
		String nextMaxBz = getnextMaxJz().get("jzdm");

		String sql = "select a.*, b.pnum from (select a.xymc, b.yjdm, b.bzmc yjmc, b.yjzdy, b.fzmc, case"
				+ " when c.xb = '1' then '男' when c.xb = '2' then '女' end xb, e.bzdm ljdm, e.bzmc ljmc, e.xm ljzdy,"
				+ " c.bzdm pjdm, c.bzmc pjmc, a.bzmc bjmc, d.bjnum, a.nj from (select a.xymc,t.sjdm, t.bzdm, t.bzmc,"
				+ " t.xb, t.nj from jxbzdmb t, view_njxyzybj a where t.bzdj = ?  and t.bzmc = a.bjmc and t.nj = ?) a,"
				+ " (select a.sjdm,t.bzdm, c.bzdm yjdm, c.bzmc, d.xm yjzdy, e.fzmc from jxbzdmb t, jxbzdmb a, jxbzdmb b,"
				+ " jxbzdmb c, jxgl_ddjsxxb d, nblg_jxgl_jxfz e where t.bzdj = ? and t.sjdm = a.bzdm and a.sjdm = b.bzdm"
				+ " and b.sjdm = c.bzdm and t.jsdm = d.jsdm and c.jxfz = e.fzdm) b, (select t.bzdm, t.bzmc, t.xb"
				+ " from jxbzdmb t where t.bzdj = ?) c, (select count(b.xh) bjnum, b.bjdm, b.bjmc, case when b.xb = '男' then"
				+ " '1' when b.xb = '女' then '2' end xb from jxgl_jxmdb a, view_xsjbxx b where a.xh = b.xh group by b.bjdm,"
				+ " b.bjmc, b.xb) d, (select distinct (t.bzdm), t.bzmc, t.sjdm, a.xm from jxbzdmb t, jxgl_ddjsxxb a where t.bzdj = ?"
				+ " and t.jsdm = a.jsdm) e where a.bzdm = b.bzdm and a.sjdm = c.bzdm and a.xb = d.xb and a.bzmc = d.bjmc"
				+ " and e.sjdm = b.yjdm and e.bzdm = b.sjdm order by b.yjdm, e.bzdm, c.bzdm, a.xymc) a left join (select ljdm,"
				+ " ljmc, count(ljdm) pnum from view_nblg_jxbztjb where nj = ? group by ljdm, ljmc) b on a.ljdm = b.ljdm";
		System.out.println(sql);
		System.out.println("minBz:" + minBz);
		System.out.println("jxnd:" + jxnd);
		System.out.println("minBz:" + minBz);
		System.out.println("nextMinBz:" + nextMinBz);
		System.out.println("nextMaxBz:" + nextMaxBz);
		System.out.println("jxnd:" + jxnd);
		return dao.getList(sql, new String[] { minBz, jxnd, minBz, nextMinBz,
				nextMaxBz, jxnd }, new String[] { "xymc", "yjdm", "yjmc",
				"yjzdy", "fzmc", "xb", "ljmc", "ljdm", "ljzdy", "pjdm", "pjmc",
				"bjmc", "bjnum", "nj", "pnum" });
	}

	// 获得编制列表
	public List<HashMap<String, String>> getGtBzList(String xn)
			throws SQLException {
		DAO dao = DAO.getInstance();
		// String maxBz = getMaxJz().get("jzdm");
		// String minBz = getMinJz().get("jzdm");
		// String nextMinBz = getnextMinJz().get("jzdm");
		// String nextMaxBz = getnextMaxJz().get("jzdm");

		StringBuffer sql = new StringBuffer();
		StringBuffer tab = new StringBuffer();
		StringBuffer ord = new StringBuffer();
		String sq = "";

		String[] table = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z" };

		List<String> jzdjList = dao.getList(
				"select jzdm from jxjzdj order by jzdm", new String[] {},
				"jzdm");

		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		if (jzdjList != null && jzdjList.size() >= 2) {
			String[] colList = new String[jzdjList.size() * 3 + 3];
			int n = 0;
			for (int i = 0; i < jzdjList.size(); i++) {
				if (i == 0) {

					colList[n] = "nj";
					n++;
					colList[n] = table[i] + "bzdm";
					n++;
					colList[n] = table[i] + "bzmc";
					n++;
					colList[n] = table[i] + "xm";
					n++;

					sql.append("select a.nj," + table[i] + ".bzdm " + table[i]
							+ "bzdm," + table[i] + ".bzmc " + table[i]
							+ "bzmc," + table[i] + ".xm " + table[i] + "xm,");
					tab.append(" from (select t.nj,t.bzdm, t.bzmc,j.xm from jxbzdmb t,jxgl_jgxxb j where t.jgbh = j.jgbh and t.bzdj = '"
									+ jzdjList.get(i)
									+ "' and t.xn = '"
									+ xn
									+ "')" + table[i]);
					ord.append(" order by " + table[i] + ".bzdm");
				} else if (i != jzdjList.size() - 1) {

					colList[n] = table[i] + "bzdm";
					n++;
					colList[n] = table[i] + "bzmc";
					n++;
					colList[n] = table[i] + "xm";
					n++;

					sql.append(table[i] + ".bzdm " + table[i] + "bzdm,"
							+ table[i] + ".bzmc " + table[i] + "bzmc,"
							+ table[i] + ".xm " + table[i] + "xm,");
					tab.append(" left join (select t.bzdm, t.bzmc, t.sjdm,j.xm from jxbzdmb t,jxgl_jgxxb j where t.bzdj = '"
									+ jzdjList.get(i)
									+ "' and t.jgbh = j.jgbh and t.xn = '"
									+ xn
									+ "')"
									+ table[i]
									+ " on "
									+ table[i]
									+ ".sjdm = " + table[i - 1] + ".bzdm");
					ord.append("," + table[i] + ".bzdm");
				} else {

					colList[n] = table[i] + "bzdm";
					n++;
					colList[n] = table[i] + "bzmc";
					n++;
					colList[n] = "xm";
					n++;
					colList[n] = "xb";
					n++;
					colList[n] = "num";
					n++;
					sql.append(table[i] + ".bzdm " + table[i] + "bzdm,"
							+ table[i] + ".bzmc " + table[i] + "bzmc,"
							+ table[i] + ".xm," + table[i] + ".xb," + table[i]
							+ ".num ");
					tab.append(" left join (select t.bzdm, t.bzmc, t.sjdm, x.xb, j.xm, count(md.xh) num  from jxbzdmb t,"
									+ " view_xsjbxx x, jxgl_jxmdb md, jxgl_jgxxb j where t.bzdj = '"
									+ jzdjList.get(i)
									+ "' and t.xn='"
									+ xn
									+ "' and t.bzmc = x.bjmc and x.xh = md.xh and md.ldbh = t.sjdm and t.jgbh = j.jgbh group by t.bzdm, t.bzmc, t.sjdm, j.xm, x.xb) "
									+ table[i]
									+ " on "
									+ table[i]
									+ ".sjdm = "
									+ table[i - 1] + ".bzdm");
					ord.append("," + table[i] + ".bzdm,xb");
				}
			}
			sq = sql.toString() + tab.toString() + ord.toString();
			list = dao.getList(sq.toString(), new String[] {}, colList);
		}

		System.out.println(sq);
		// System.out.println("minBz:" + minBz);
		// System.out.println("jxnd:" + jxnd);
		// System.out.println("minBz:" + minBz);
		// System.out.println("nextMinBz:" + nextMinBz);
		// System.out.println("nextMaxBz:" + nextMaxBz);
		// System.out.println("jxnd:" + jxnd);
		return list;
	}

	// 获得编制列表
	public List<HashMap<String, String>> getJhBzList(String xn)
			throws SQLException {
		DAO dao = DAO.getInstance();
		String[] colList = new String[]{"nj","abzdm","abzmc","axm","bbzdm","bbzmc","bxm","cbzdm","cbzmc","cxm","dbzdm","dbzmc","xm","xb","num"};
		StringBuffer sql = new StringBuffer();
		sql.append("select a.nj,a.bzdm abzdm,a.bzmc abzmc,a.xm axm,b.bzdm bbzdm,b.bzmc bbzmc,");
		sql.append(" b.xm bxm,c.bzdm cbzdm,c.bzmc cbzmc,c.xm cxm,d.bzdm dbzdm,d.bzmc dbzmc,");
		sql.append(" d.xm,d.xb,d.num from (select t.nj, t.bzdm, t.bzmc, j.xm");
		sql.append(" from jxbzdmb t, jxgl_jgxxb j where t.jgbh = j.jgbh and t.bzdj = '001'");
		sql.append(" and t.xn = '"+xn+"') a left join (select t.bzdm, t.bzmc, t.sjdm, j.xm");
		sql.append(" from jxbzdmb t, jxgl_jgxxb j where t.bzdj = '002' and t.jgbh = j.jgbh");
		sql.append(" and t.xn = '"+xn+"') b on b.sjdm = a.bzdm left join (select t.bzdm, t.bzmc, t.sjdm, j.xm");
		sql.append(" from jxbzdmb t, jxgl_jgxxb j where t.bzdj = '003' and t.jgbh = j.jgbh");
		sql.append(" and t.xn = '"+xn+"') c on c.sjdm = b.bzdm left join (");
		sql.append(" select t.bzdm, t.bzmc, t.sjdm, x.xb, j.xm, count(md.xh) num");
		sql.append(" from jxbzdmb t, view_xsjbxx x, jxgl_jxmdb md, jxgl_jgxxb j");
		sql.append(" where t.bzdj = '004' and t.xn = '"+xn+"' and t.bzmc = x.bjmc ");
		sql.append(" and x.xh = md.xh and md.ldbh = t.sjdm and t.jgbh = j.jgbh");
		sql.append(" group by t.bzdm, t.bzmc, t.sjdm, j.xm, x.xb) d on d.sjdm =c.bzdm");
		sql.append(" order by a.bzdm, b.bzdm, c.bzdm, d.bzdm,xb");
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {}, colList);
		//System.out.println(sql);
		return list;
	}
	
	public List<String> getGtBzdjList() throws SQLException {
		DAO dao = DAO.getInstance();
		List<String> jzdjList = dao.getList(
				"select jzmc from jxjzdj order by jzdm", new String[] {},
				"jzmc");
		return jzdjList;
	}

	// 获取军训奖项列表
	public List<HashMap<String, String>> selectJxtdjxList() {
		DAO dao = DAO.getInstance();
		String sql = "select distinct jxdm,jxmc from jxjxdmb ";
		return dao.getList(sql, new String[] {},
				new String[] { "jxdm", "jxmc" });
	}
	
	public String getZbid(String zbid) {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from zgdd_zbrb where zbid=?";
		String num = dao.getOneRs(sql, new String[] { zbid }, "num");
		return num;
	}
	
	/**
	 * @describe 获得年级列表
	 * @author luo
	 */
	public List<HashMap<String, String>> getNjList() {
		DAO dao = DAO.getInstance();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select '' njdm, '---请选择---' njmc from dual union");
		sql.append(" select distinct (nj) njdm, nj njmc from view_xsjbxx order by njdm nulls first");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"njdm", "njmc" });
	}
	
	/**
	 * @describe 获得军训学年
	 * @author luo
	 */
	public String getJxxn(String bzdm) {
		DAO dao = DAO.getInstance();

		String sql = "select xn from jxbzdmb where bzdm = ?";

		return dao.getOneRs(sql.toString(), new String[] { bzdm }, "xn");
	}
}
