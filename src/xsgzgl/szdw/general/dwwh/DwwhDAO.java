package xsgzgl.szdw.general.dwwh;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.szdw.general.SzdwGeneralDAO;
import xsgzgl.szdw.general.SzdwGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_队伍维护_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DwwhDAO extends SzdwGeneralDAO {

	// ==================执行查询操作 begin =============================

	/**
	 * 获得思政队伍维护结果集
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getDwwhList(SzdwGeneralForm myForm, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		if ("10026".equals(Base.xxdm)) {
			searchTj = searchTj.replaceAll("jssf = [?]", " jssf like '%' || ? || '%' ");
		}
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "bmdm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from(");
		sql.append("select a.zgh pk,a.* ");
		sql.append("from (");
		sql.append("select a.* ,");
		sql.append("case ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '校级' then '校级用户' ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '院级' then '院级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '校级' then '兼任学校用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '院级' then '兼任院系用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '校级' then '班级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '院级' then '班级用户' ");
		sql.append("end yhsf, ");
		sql.append("decode(a.fdydbs,'0','否','是') sffdy, ");
		sql.append("decode(a.bzrdbs,'0','否','是') sfbzr ");
		sql.append("from ( ");
		sql.append("select a.sfbl,a.zgh,a.xm,a.zw,a.lxdh,a.bmdm,a.kzzd5,b.bmmc,a.xl,a.zc,a.zzmm,a.zjz, ");
		sql.append("decode(a.xb,'1','男','2','女',a.xb) xb, ");
		sql.append("decode(b.bmlb,'5','院级','校级') bmjb, ");
		sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
		sql.append("decode(e.yhm,null,'否','是') sfyh, a.sfjryx, ");
		sql.append("xx.dls ");
		if ("10698".equals(Base.xxdm)) {
			sql.append(",k.symc ");
		}
		if ("10026".equals(Base.xxdm)) {
			sql.append(",a.jssf ");
		}
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm=b.bmdm ");

		sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
		sql.append("   from fdybjb c ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by c.zgh) c ");
		sql.append("on a.zgh=c.zgh ");

		sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
		sql.append("  from bzrbbb d ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by d.zgh) d ");
		sql.append("on a.zgh=d.zgh ");
		
		sql.append("left join yhb e ");
		sql.append("on a.zgh=e.yhm ");
		if ("10698".equals(Base.xxdm)) {
			sql.append(" left join xg_xtwh_sydmb k on a.sydm=k.sydm ");
		}
		sql.append(" left join (select username, count(1) dls ");
		sql.append("  from xg_xtgl_log_dl ");
		sql.append("  group by username) xx ");
		sql.append(" on xx.username = a.zgh ");
		if ("12898".equals(Base.xxdm)) {
			sql.append(" where (a.sfbl='0'or a.sfbl is null) ");
		}
		sql.append(") a ");
		sql.append("where 1=1 ");
		sql.append(") a ");
		// sql.append(query);
		sql.append(" order by bmdm,xm ) a ");
		sql.append("where 1=1 ");

		// sql.append(searchTjByUser);
		sql.append(searchTj);

		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {

			sql.append(" and bmdm='" + userDep + "' ");
		}
		if ("10352".equalsIgnoreCase(Base.xxdm) && "szdw_general_dwwh.do".equalsIgnoreCase(myForm.getPath())) {
			
			sql.append(" and sfbl='1' ");
		}

		// ====================SQL拼装 end================================

		String[] colList = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls"};
		if(Base.xxdm.equals("10026")){
			     colList = new String[] { "pk", "zgh", "xm", "xb", "lxdh", "bmmc", "fdydbs", "bzrdbs", "yhsf","dls","jssf"};
		}

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonPageQuery(myForm.getPages(), sql.toString(), inputV, colList);

		return list;
	}

	/**
	 * 获得思政队伍信息
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getDwxx(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		if ("10698".equals(Base.xxdm)) {
			sql.append("select a.zgh,a.xm,a.lxdh,a.bmdm,b.bmmc,decode(a.xb,'1','男','2','女',a.xb) xb,d.sydm,d.symc from fdyxxb a ");
			sql.append("left join zxbz_xxbmdm b on a.bmdm=b.bmdm ");
			sql.append("left join xg_xtwh_sybjglb c on a.sydm=c.sydm ");
			sql.append("left join xg_xtwh_sydmb d on d.sydm=c.sydm ");
		}else {
			sql.append("select a.zgh,a.xm,a.lxdh,a.bmdm,b.bmmc,decode(a.xb,'1','男','2','女',a.xb) xb from fdyxxb a ");
			sql.append("left join zxbz_xxbmdm b on a.bmdm=b.bmdm ");
		}
		sql.append("where a.zgh=? ");

		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	/**
	 * 获得已经在用户库的用户列表
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 * @throws SQLException
	 */
	public String[] getYh(String[] zgh) throws SQLException {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.yhm ");
		sql.append("from yhb a ");
		sql.append("where 1=1 ");
		sql.append("and a.yhm in (");
		for (int i = 0; i < zgh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");

		return dao.getArray(sql.toString(), zgh, "yhm");
	}

	/**
	 * 获得年级列表
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getNjList(String lx, String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		String table = "fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb";

		sql.append("select distinct a.nj, ");
		sql.append("decode(b.zgh, null, 'no', 'yes') sfbb ");
		sql.append("from view_njxyzybj a ");
		sql.append("left join (select distinct c.zgh, d.nj ");
		sql.append("from " + table + " c, bks_bjdm d ");
		sql.append("where c.zgh = ? ");
		sql.append("and c.bjdm = d.bjdm) b ");
		sql.append("on a.nj = b.nj ");
		sql.append("order by nj desc ");

		return dao.getList(sql.toString(), new String[] { zgh }, new String[] { "nj", "sfbb" });
	}

	/**
	 * 获得部门列表
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmList(String lx, String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		String table = "fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb";

		sql.append("select a.bzdm, a.bzmc, a.bzdj, a.sjdm,a.sfbb, nvl(b.num, 1) num ");
		sql.append("from (select distinct to_char(a.nj) bzdm, ");
		sql.append("to_char(a.nj) bzmc, ");
		sql.append("'1' bzdj, ");
		sql.append("'' sjdm, ");
		sql.append("decode(z.zgh, null, 'no', 'yes') sfbb ");
		sql.append("from view_njxyzybj a ");
		sql.append("left join (select distinct c.zgh, d.nj ");
		sql.append("from " + table + " c, bks_bjdm d ");
		sql.append("where c.zgh = ? ");
		sql.append("and c.bjdm = d.bjdm) z ");
		sql.append("on a.nj = z.nj ");
		sql.append("union all ");
		sql.append("select to_char(b.nj) || b.xydm bzdm, ");
		sql.append("b.xymc bzmc, ");
		sql.append("'2' bzdj, ");
		sql.append("to_char(b.nj) sjdm, ");
		sql.append("decode(z.zgh, null, 'no', 'yes') sfbb ");
		sql.append("from (select distinct nj, xydm, xymc from view_njxyzybj) b ");
		sql.append("left join (select distinct c.zgh, d.nj, d.xydm ");
		sql.append("from " + table + " c, view_njxyzybj_all d ");
		sql.append("where c.zgh = ? ");
		sql.append("and c.bjdm = d.bjdm) z ");
		sql.append("on to_char(b.nj) || b.xydm = z.nj || z.xydm ");
		sql.append("union all ");
		sql.append("select to_char(c.nj) || c.zydm bzdm, ");
		sql.append("c.zymc bzmc, ");
		sql.append("'3' bzdj, ");
		sql.append("to_char(c.nj) || c.xydm sjdm, ");
		sql.append("decode(z.zgh, null, 'no', 'yes') sfbb ");
		sql.append("from (select distinct nj, zydm, zymc, xydm from view_njxyzybj) c ");
		sql.append("left join (select distinct c.zgh, d.nj, d.zydm ");
		sql.append("from " + table + " c, view_njxyzybj_all d ");
		sql.append("where c.zgh = ? ");
		sql.append("and c.bjdm = d.bjdm) z ");
		sql.append("on to_char(c.nj) || c.zydm = z.nj || z.zydm ");
		sql.append("union all ");
		sql.append("select d.bjdm bzdm, ");
		sql.append("d.bjmc bzmc, ");
		sql.append("'4' bzdj, ");
		sql.append("to_char(d.nj) || d.zydm sjdm, ");
		sql.append("decode(z.zgh, null, 'no', 'yes') sfbb ");
		sql.append("from (select distinct nj, bjdm, bjmc, zydm from view_njxyzybj) d ");
		sql.append("left join (select distinct c.zgh, c.bjdm ");
		sql.append("from " + table + " c ");
		sql.append("where c.zgh = ?) z ");
		sql.append("on d.bjdm = z.bjdm) a ");
		sql.append("left join (select sjdm bzdm, count(1) num ");
		sql.append("from (select distinct to_char(a.nj) bzdm, ");
		sql.append("to_char(a.nj) bzmc, ");
		sql.append("'1' bzdj, ");
		sql.append("'' sjdm ");
		sql.append("from view_njxyzybj a ");
		sql.append("union all ");
		sql.append("select to_char(b.nj) || b.xydm bzdm, ");
		sql.append("b.xymc bzmc, ");
		sql.append("'2' bzdj, ");
		sql.append("to_char(b.nj) sjdm ");
		sql.append("from (select distinct nj, xydm, xymc from view_njxyzybj) b ");
		sql.append("union all ");
		sql.append("select to_char(c.nj) || c.zydm bzdm, ");
		sql.append("c.zymc bzmc, ");
		sql.append("'3' bzdj, ");
		sql.append("to_char(c.nj) || c.xydm sjdm ");
		sql.append("from (select distinct nj, zydm, zymc, xydm ");
		sql.append("from view_njxyzybj) c ");
		sql.append("union all ");
		sql.append("select d.bjdm bzdm, ");
		sql.append("d.bjmc bzmc, ");
		sql.append("'4' bzdj, ");
		sql.append("to_char(d.nj) || d.zydm sjdm ");
		sql.append("from (select distinct nj, bjdm, bjmc, zydm ");
		sql.append("from view_njxyzybj) d) ");
		sql.append("group by sjdm) b ");
		sql.append("on a.bzdm = b.bzdm ");
		sql.append("order by bzdj, bzdm ");

		return dao.getList(sql.toString(), new String[] { zgh, zgh, zgh, zgh }, new String[] { "bzdm", "bzmc", "bzdj", "sjdm", "sfbb", "num" });
	}

	/**
	 * 获得部门列表
	 * 
	 * @date 2013-01-15
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmList() {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select * from (");
		sql.append("select bmdm,bmmc,");
		sql.append("case when length(bmmc)>6 then substr(bmmc,0,6)||'..' ");
		sql.append("else bmmc end bmsx, ");
		sql.append("F_PINYIN(substr(bmmc,0,1)) bmpy  ");
		sql.append("from zxbz_xxbmdm ");
		sql.append("where bmjb = '1' ");
		sql.append(") order by bmpy ");

		return dao.getList(sql.toString(), new String[] {}, new String[] { "bmdm", "bmmc", "bmpy", "bmsx" });
	}

	/**
	 * 获取辅导员信息
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getFdyInfo(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		if(Base.xxdm.equalsIgnoreCase("14008")){//如果为重庆三峡高等专科学校
			sql.append("select a.zgh, a.xm, a.bmdm, b.bmmc,a.bzxm,a.bzlxdh, nvl(c.num, 0) num ");
			sql.append("from fdyxxb a ");
			sql.append("left join zxbz_xxbmdm b ");
			sql.append("on a.bmdm = b.bmdm ");
			sql.append("left join (select zgh, count(1) num from fdybjb a ");
			sql.append(" where exists (select 1 from view_njxyzybj b where a.bjdm=b.bjdm ) group by zgh) c ");
			sql.append("on a.zgh = c.zgh ");
			sql.append("where a.zgh=? ");
		}else{
			sql.append("select a.zgh, a.xm, a.bmdm, b.bmmc, nvl(c.num, 0) num ");
			sql.append("from fdyxxb a ");
			sql.append("left join zxbz_xxbmdm b ");
			sql.append("on a.bmdm = b.bmdm ");
			sql.append("left join (select zgh, count(1) num from fdybjb a ");
			sql.append(" where exists (select 1 from view_njxyzybj b where a.bjdm=b.bjdm ) group by zgh) c ");
			sql.append("on a.zgh = c.zgh ");
			sql.append("where a.zgh=? ");
		}
		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	/**
	 * 获取班主任信息
	 * 
	 * @date 2013-01-11
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getBzrInfo(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		if(Base.xxdm.equalsIgnoreCase("14008")){
			sql.append("select a.zgh, a.xm, a.bmdm,a.bzxm,a.bzlxdh, b.bmmc, nvl(c.num, 0) num ");
			sql.append("from fdyxxb a ");
			sql.append("left join zxbz_xxbmdm b ");
			sql.append("on a.bmdm = b.bmdm ");
			sql.append("left join (select zgh, count(1) num from bzrbbb a where exists (select 1 from view_njxyzybj b where a.bjdm=b.bjdm ) group by zgh) c ");
			sql.append("on a.zgh = c.zgh ");
			sql.append("where a.zgh=? ");
		}else{
		sql.append("select a.zgh, a.xm, a.bmdm, b.bmmc, nvl(c.num, 0) num ");
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm = b.bmdm ");
		sql.append("left join (select zgh, count(1) num from bzrbbb a where exists (select 1 from view_njxyzybj b where a.bjdm=b.bjdm ) group by zgh) c ");
		sql.append("on a.zgh = c.zgh ");
		sql.append("where a.zgh=? ");
		}

		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	/**
	 * 获取辅导员班级列表
	 * 
	 * @date 2013-01-16
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFdybjList(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.nj,a.xymc,a.zymc,a.bjmc ");
		sql.append("from view_njxyzybj a ");
		sql.append(",fdybjb b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append("and b.zgh = ? ");
		sql.append("order by a.nj,a.xydm,a.zydm,a.bjdm");

		return dao.getList(sql.toString(), new String[] { zgh }, new String[] { "nj", "xymc", "zymc", "bjmc" });

	}

	/**
	 * 
	 * 修改弹出层页面
	 * 
	 * @作者：dql [工号：995]
	 * @日期：2013-8-21 下午12:28:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return ArrayList<String[]> 返回类型
	 * @throws
	 */
	public ArrayList<String[]> getFdybjList1(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.nj,a.xymc,a.zymc,a.bjmc,c.cnt bjrs ");
		sql.append("from view_njxyzybj a ");
		sql.append(",fdybjb b,( select bjdm,count(1)cnt from view_xsjbxx group by bjdm)c ");//通用增加班级人数显示（江西航空需求，但是考虑到实际可以通用）
		sql.append("where a.bjdm = b.bjdm and a.bjdm = c.bjdm ");
		sql.append("and b.zgh = ? ");
		sql.append("order by a.nj,a.xydm,a.zydm,a.bjdm");

		return dao.rsToVator(sql.toString(), new String[] { zgh }, new String[] { "nj", "xymc", "zymc", "bjmc","bjrs" });

	}

	/**
	 * 
	 * 修改弹出层页面
	 * 
	 * @作者：dql [工号：995]
	 * @日期：2013-8-21 下午12:28:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return ArrayList<String[]> 返回类型
	 * @throws
	 */
	public ArrayList<String[]> getBzrbjList1(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.nj,a.xymc,a.zymc,a.bjmc,c.cnt bjrs ");
		sql.append("from view_njxyzybj a ");
		sql.append(",bzrbbb b,( select bjdm,count(1)cnt from view_xsjbxx group by bjdm)c ");//通用增加班级人数显示（江西航空需求，但是考虑到实际可以通用）
		sql.append("where a.bjdm = b.bjdm  and a.bjdm = c.bjdm ");
		sql.append("and b.zgh = ? ");
		sql.append("order by a.nj,a.xydm,a.zydm,a.bjdm");

		return dao.rsToVator(sql.toString(), new String[] { zgh }, new String[] { "nj", "xymc", "zymc", "bjmc","bjrs" });
	}

	/**
	 * 获取班主任班级列表
	 * 
	 * @date 2013-01-16
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBzrbjList(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.nj,a.xymc,a.zymc,a.bjmc ");
		sql.append("from view_njxyzybj a ");
		sql.append(",bzrbbb b ");
		sql.append("where a.bjdm = b.bjdm ");
		sql.append("and b.zgh = ? ");
		sql.append("order by a.nj,a.xydm,a.zydm,a.bjdm");

		return dao.getList(sql.toString(), new String[] { zgh }, new String[] { "nj", "xymc", "zymc", "bjmc" });
	}

	/**
	 * 获取教职工信息列表
	 * 
	 * @date 2013-01-16
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getJzgxxList(String zgh, String xm, String bmdm, String dbqk, String lx) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		ArrayList<String> inputValue = new ArrayList<String>();

		sql.append("select a.zgh, a.xm,b.bmmc ");
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm = b.bmdm ");
		sql.append("where 1=1 ");

		if (!Base.isNull(zgh)) {
			sql.append("and a.zgh like ?");
			inputValue.add("%" + zgh + "%");
		}

		if (!Base.isNull(xm)) {
			sql.append("and a.xm like ?");
			inputValue.add("%" + xm + "%");
		}

		if (!Base.isNull(bmdm)) {
			sql.append("and a.bmdm = ? ");
			inputValue.add(bmdm);
		}

		if (!Base.isNull(dbqk)) {

			sql.append("and ");

			if ("yes".equalsIgnoreCase(dbqk)) {
				sql.append(" exists ");
			} else if ("no".equalsIgnoreCase(dbqk)) {
				sql.append(" not exists ");
			}
			sql.append("(");
			sql.append("select 1 from ");
			sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
			sql.append(" c where a.zgh = c.zgh ");
			sql.append(")");
		}

		return dao.getList(sql.toString(), inputValue.toArray(new String[] {}), new String[] { "zgh", "xm", "bmmc" });
	}

	/**
	 * 获得思政队伍编班结果集
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getSetupList(SzdwGeneralForm myForm, DwwhModel model, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		String zgh = model.getZgh();// 职工号

		String nj = model.getNjV();// 年级

		String xydm = model.getXyV();// 学院

		String zydm = model.getZyV();// 专业

		String bjdm = model.getBjV();// 班级

		String lx = model.getLx();// 类型

		String ywjs = model.getYwjs();// 有无教师

		String brdb = model.getBrdb();// 本人带班

		ArrayList<String> inputValue = new ArrayList<String>();

		// ====================SQL拼装================================

		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from(");
		sql.append("select a.bjdm pk,a.nj ");
		sql.append(",a.xydm,a.xymc,a.zydm,a.zymc ");
		sql.append(",a.bjdm,a.bjmc,nvl(b.num,'0') num ");
		sql.append(",decode(c.zgh,null,'no','yes') brdb ");
		sql.append(" ,t2.sydm,t2.symc ");
		sql.append(" from ");
		if("fdy".equalsIgnoreCase(lx)){
			sql.append("view_njxyzybj_fdy a ");
		} else {
			sql.append("view_njxyzybj_bzr a ");
		}
		sql.append(" left join XG_XTWH_SYBJGLB t1 on a.bjdm=t1.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		sql.append("left join (");
		sql.append("select bjdm,count(1) num from ");
		sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
		sql.append(" group by bjdm");
		sql.append(") b on a.bjdm=b.bjdm ");
		sql.append("left join (");
		sql.append("select zgh,bjdm from ");
		sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
		sql.append(" where zgh = ? ) c on a.bjdm=c.bjdm ");
		sql.append(" where 1=1 ");

		inputValue.add(zgh);

		if (!Base.isNull(nj)) {
			sql.append("and a.nj = ?");
			inputValue.add(nj);
		}

		if (!Base.isNull(xydm)) {
			sql.append("and a.xydm = ?");
			inputValue.add(xydm);
		}

		if (!Base.isNull(zydm)) {
			sql.append("and a.zydm = ?");
			inputValue.add(zydm);
		}

		if (!Base.isNull(bjdm)) {
			sql.append("and a.bjdm = ?");
			inputValue.add(bjdm);
		}

		if (!Base.isNull(ywjs)) {

			sql.append("and ");

			if ("yes".equalsIgnoreCase(ywjs)) {
				sql.append(" exists ");
			} else if ("no".equalsIgnoreCase(ywjs)) {
				sql.append(" not exists ");
			}
			sql.append("(");
			sql.append("select 1 from ");
			sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
			sql.append(" c where a.bjdm = c.bjdm ");
			sql.append(")");
		}

		if (!Base.isNull(brdb)) {

			sql.append("and ");

			if ("yes".equalsIgnoreCase(brdb)) {
				sql.append(" exists ");
			} else if ("no".equalsIgnoreCase(brdb)) {
				sql.append(" not exists ");
			}
			sql.append("(");
			sql.append("select 1 from ");
			sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
			sql.append(" d where a.bjdm = d.bjdm ");
			sql.append(" and d.zgh = ? ");
			sql.append(")");

			inputValue.add(zgh);
		}

		sql.append("order by a.nj desc,a.xydm,a.zydm,a.bjdm");
		sql.append(") a ");
		sql.append("where 1=1 ");

		// ====================SQL拼装 end==============================
		String[] colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc", "num", "brdb","symc" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonPageQuery(myForm.getPages(), sql.toString(), inputValue.toArray(new String[] {}), colList);

		return list;
	}

	/**
	 * 获取教职工列表
	 * 
	 * @date 2013-01-18
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getJzgList(String bjdm, String lx) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("select a.zgh,a.xm,a.lxdh,a.bmdm,b.bmmc, ");
		sql.append("decode(a.xb,'1','男','2','女',a.xb) xb ");
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm=b.bmdm ");
		sql.append("where exists ");
		sql.append("(");
		sql.append("select 1 from ");
		sql.append("fdy".equalsIgnoreCase(lx) ? "fdybjb" : "bzrbbb");
		sql.append(" c where a.zgh = c.zgh ");
		sql.append(" and c.bjdm=? ");
		sql.append(")");

		return dao.getList(sql.toString(), new String[] { bjdm }, new String[] { "zgh", "xm", "xb", "bmmc" });
	}

	/**
	 * 统计辅导员带班级信息
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countFdyDbj(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append(" select nvl(count(1),0)num from  fdybjb a where exists (select 1 from view_njxyzybj_fdy b where a.bjdm=b.bjdm ) and zgh =? group by zgh ");

		return dao.getOneRs(sql.toString(), new String[] { zgh }, "num");
	}

	/**
	 * 统计班主任带班级信息
	 * 
	 * @param zgh
	 * @author qlj
	 * @time 2013-1-24
	 * @return
	 */
	public String countBzrDbj(String zgh) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append(" select nvl(count(1),0)num from  bzrbbb a where exists (select 1 from view_njxyzybj_bzr b where a.bjdm=b.bjdm ) and zgh =? group by zgh");

		return dao.getOneRs(sql.toString(), new String[] { zgh }, "num");
	}

	// ==================执行查询操作 end =============================

	// ==================执行增加操作 begin=============================

	/**
	 * 【思政队伍维护】增加教师信息
	 * 
	 * @table fdyxxb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 * @param sydm 
	 */
	public boolean insertFdyxxb(String zgh, String xm, String xb, String bmdm, String lxdh, User user, String sydm) throws Exception {
		// 表名
		String tableName = "fdyxxb";
		StringBuilder sql = new StringBuilder();
		List<String[]> params = new ArrayList<String[]>();
		if ("10698".equals(Base.xxdm)) {
			sql.append("insert into fdyxxb (zgh,xm,xb,bmdm,lxdh,sfjryx,sydm)values(?,?,?,?,?,?,?)");
			String[] value = new String[] { zgh, xm, xb, bmdm, lxdh, "false",sydm };
			params.add(value);
		}else {
			sql.append("insert into fdyxxb (zgh,xm,xb,bmdm,lxdh,sfjryx)values(?,?,?,?,?,?)");
			String[] value = new String[] { zgh, xm, xb, bmdm, lxdh, "false" };
			params.add(value);
		}
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}

	/**
	 * 【思政队伍维护】增加到用户库
	 * 
	 * @table yhb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 */
	public boolean insertYhb(String[] zgh, String zdm, User user) throws Exception {

		// 表名
		String tableName = "yhb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into yhb ");
		sql.append("(");
		sql.append("yhm,kl,xm,zdm,szbm,dwdm,qx");
		sql.append(")");
		sql.append("select zgh,'{MD5}ISGMyneATSuhkiwz4BURBQ==' kl,");
		sql.append("xm,'" + zdm + "' zdm,bmdm,'01' dwdm,'1'qx ");
		sql.append("from fdyxxb ");
		sql.append("where 1=? ");
		sql.append("and zgh in (");
		for (int i = 0; i < zgh.length; i++) {
			if (i != 0) {
				sql.append(",");
			}
			sql.append("'" + zgh[i] + "'");
		}
		sql.append(")");
		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { "1" };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】增加辅导员编班
	 * 
	 * @table fdybjb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean insertFdybjb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "fdybjb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into fdybjb ");
		sql.append("(");
		sql.append("zgh,bjdm");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】撤销辅导员编班
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public boolean disfrockFdybjb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "fdybjb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from fdybjb where zgh=? and bjdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】增加班主任编班
	 * 
	 * @table bzrbbb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean insertBzrbbb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "bzrbbb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into bzrbbb ");
		sql.append("(");
		sql.append("zgh,bjdm");
		sql.append(")");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】撤销班主任编班
	 * 
	 * @date 2013-01-24
	 * @author qlj
	 */
	public boolean disfrockBzrbbb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "bzrbbb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete from bzrbbb where zgh=? and bjdm=? ");

		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 保存编班历史
	 */
	public boolean saveBbls(String zgh, String[] bjdm, String type, boolean isFdy) throws Exception {
		DAO dao = DAO.getInstance();
		String table = isFdy ? "fdybjblsb" : "bzrbblsb";
		StringBuilder sql=new StringBuilder();
		if("save".equals(type)){
			StringBuilder sqlExists = new StringBuilder();
			List<String> paramsExists = new ArrayList<String>();
			sqlExists.append(" select bjdm from "+table+" where ");
			for (int i = 0; i < bjdm.length; i++) {
				sqlExists.append(" (zgh=? and bjdm=? and qxfbsj is null) ");
				if(i < bjdm.length - 1){
					sqlExists.append(" or ");
				}
				paramsExists.add(zgh);
				paramsExists.add(bjdm[i]);
			}
			// 获取已编班的记录，避免历史表中出现重复编班
			List<String> existsList = dao.getList(sqlExists.toString(), paramsExists.toArray(new String[]{}), "bjdm");
			List<String[]> paramsNew = new ArrayList<String[]>();
			for (String bjdmOld : bjdm) {
				if(!existsList.contains(bjdmOld)){
					String[] value = new String[] { Base.currXn, Base.currXq, zgh, bjdmOld };
					paramsNew.add(value);
				}
			}
			if(paramsNew.size() > 0){
				sql.append(" insert into "+table+" (xn,xq,zgh,bjdm,fbsj,sfzr) values (?,?,?,?,to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),'1') ");
				return saveArrDate(sql.toString(), paramsNew, table);
			}else{
				return true;
			}
		}else{
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0; i < bjdm.length; i++) {
				String[] value = new String[] { Base.currXn, Base.currXq, zgh, bjdm[i] };
				params.add(value);
			}
			sql.append(" update "+table+" set qxfbsj=to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),sfzr='0',xn=?,xq=? where zgh=? and bjdm=? and qxfbsj is null ");
			return saveArrDate(sql.toString(), params, table);
		}
	}

	// ==================执行增加操作 end=============================

	// ==================执行修改操作 begin=============================

	/**
	 * 【思政队伍维护】修改教师信息
	 * 
	 * @table fdyxxb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 * @param sydm 
	 */
	public boolean updateFdyxxb(String zgh, String xm, String xb, String bmdm, String lxdh, User user, String sydm) throws Exception {
		// 表名
		String tableName = "fdyxxb";
		StringBuilder sql = new StringBuilder();
		List<String[]> params = new ArrayList<String[]>();
		if ("10698".equals(Base.xxdm)) {
			sql.append("update fdyxxb set xm=? ,xb=?,bmdm=? ,lxdh=?,sydm=? where zgh=? ");
			String[] value = new String[] { xm, xb, bmdm, lxdh,sydm, zgh };
			params.add(value);
		}else {
			sql.append("update fdyxxb set xm=? ,xb=?,bmdm=? ,lxdh=? where zgh=? ");
			String[] value = new String[] { xm, xb, bmdm, lxdh, zgh };
			params.add(value);
		}
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);
		return flag;
	}

	/**
	 * 【思政队伍维护】修改是否兼任院校
	 * 
	 * @table fdyxxb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 */
	public boolean updateFdyxxb(String[] zgh, String sfjryx, User user) throws Exception {

		// 表名
		String tableName = "fdyxxb";

		StringBuilder sql = new StringBuilder();
		sql.append("update fdyxxb ");
		sql.append("set sfjryx=? ");
		sql.append("where zgh=? ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < zgh.length; i++) {
			String[] value = new String[] { sfjryx, zgh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】修改用户组
	 * 
	 * @table yhb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 */
	public boolean updateYhb(String[] zgh, String zdm, User user) throws Exception {

		// 表名
		String tableName = "yhb";

		StringBuilder sql = new StringBuilder();
		sql.append("update yhb ");
		sql.append("set zdm=? ");
		sql.append("where yhm=? ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < zgh.length; i++) {
			String[] value = new String[] { zdm, zgh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行修改操作 end=============================

	// ==================执行删除操作 begin=============================

	/**
	 * 【思政队伍维护】删除教师信息
	 * 
	 * @table fdyxxb
	 * 
	 * @date 2013.01.13
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteFdyxxb(String[] pkValue, User user) throws Exception {

		// 表名
		String tableName = "fdyxxb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete fdyxxb ");
		sql.append(" where  zgh=?  ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < pkValue.length; i++) {
			String[] value = new String[] { pkValue[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除辅导员编班
	 * 
	 * @table fdybjb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteFdybjb(String zgh, User user) throws Exception {

		// 表名
		String tableName = "fdybjb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete fdybjb ");
		sql.append(" where  zgh=?  ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { zgh });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除辅导员编班
	 * 
	 * @table fdybjb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteFdybjb(String[] zgh, User user) throws Exception {

		// 表名
		String tableName = "fdybjb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete fdybjb ");
		sql.append(" where  zgh=?  ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < zgh.length; i++) {
			String[] value = new String[] { zgh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除班主任编班
	 * 
	 * @table bzrbbb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteBzrbbb(String zgh, User user) throws Exception {

		// 表名
		String tableName = "bzrbbb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete bzrbbb ");
		sql.append(" where  zgh=?  ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { zgh });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除班主任编班
	 * 
	 * @table bzrbbb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteBzrbbb(String[] zgh, User user) throws Exception {

		// 表名
		String tableName = "bzrbbb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete bzrbbb ");
		sql.append(" where  zgh=?  ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < zgh.length; i++) {
			String[] value = new String[] { zgh[i] };
			params.add(value);
		}

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除辅导员编班
	 * 
	 * @table fdybjb
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteFdybjb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "fdybjb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete fdybjb ");
		sql.append(" where zgh=?  ");
		sql.append(" and  bjdm=?  ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	/**
	 * 【思政队伍维护】删除班主任编班
	 * 
	 * @table bzrbbb
	 * 
	 * @date 2013.01.21
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteBzrbbb(String zgh, String[] bjdm, User user) throws Exception {

		// 表名
		String tableName = "bzrbbb";

		StringBuilder sql = new StringBuilder();
		sql.append(" delete bzrbbb ");
		sql.append(" where zgh=?  ");
		sql.append(" and  bjdm=?  ");

		List<String[]> params = new ArrayList<String[]>();

		for (int i = 0; i < bjdm.length; i++) {
			String[] value = new String[] { zgh, bjdm[i] };
			params.add(value);
		}
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行删除操作 end=============================

	// ==================执行初始化操作 begin=============================

	/**
	 * 【思政队伍维护】初始化查询条件
	 * 
	 * @date 2013.01.14
	 * 
	 * @author 伟大的骆
	 */
	public void initSearch(List<String[]> params) throws Exception {

		String tableName = "xg_search_szb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_search_szb ");
		sql.append("(");
		sql.append("path,tj,mc,lx,zd,ssmk,xssx");
		sql.append(") values");
		sql.append("(?,?,?,?,?,?,?)");

		saveArrDate(sql.toString(), params, tableName);

	}

	/**
	 * 根据职工号查询教职工信息
	 * 
	 * @param zgh
	 *            职工号
	 * @return
	 */
	public HashMap<String, String> getSzDwxxByZgh(String zgh) {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select  * from fdyxxb b where b.zgh =? ");
		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	public void updateJzgxx() {

	}

	/**
	 * 辅导员信息维护自定义导出
	 * 
	 * @param myForm
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getDwwhExportList(SzdwGeneralForm myForm, User user) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 权限过滤
		// String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
		// "xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select rownum r,a.* from(");
		sql.append("select a.zgh pk,a.* ");
		sql.append("from (");
		sql.append("select a.* , ");
		/*
		 * 添加民族名称 modify by xiaxia 
		 * 2014-10-25
		 */
		sql.append("(select d.mzmc from mzdmb d where a.mz=d.mzdm) mzmc,");
		sql.append("(select c.zzmmmc from zzmmdmb c where c.zzmmdm = a.zzmm) zzmmmc, "); // 添加政治面貌名称
		sql.append("case ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '校级' then '校级用户' ");
		sql.append("when a.fdydbs = '0' and a.bzrdbs = '0' and a.bmjb = '院级' then '院级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '校级' then '兼任学校用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'true' and a.bmjb = '院级' then '兼任院系用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '校级' then '班级用户' ");
		sql.append("when (a.fdydbs <> '0' or a.bzrdbs <> '0') and a.sfjryx = 'false' and a.bmjb = '院级' then '班级用户' ");
		sql.append("end yhsf, (select zwmc from zwb t where t.zwdm=a.zw)zwmc,(select sydq from dmk_sydq t where t.sydqdm=a.jgxs)jgxsmc");
		sql.append(",(select sydq from dmk_sydq t where t.sydqdm = a.kzzd2) csdmc");
		sql.append(" ,(select bmmc from zxbz_xxbmdm t where t.bmdm = a.rxszbm) rxszbmmc");
		sql.append(" ,(select bmmc from zxbz_xxbmdm t where t.bmdm = a.lxbm) lxbmmc");
		sql.append(",case when a.sfjryx='true' then '是' when a.sfjryx='false' then '否' else a.sfjryx end sfjryxmc, ");
		sql.append("decode(a.fdydbs,'0','否','是') sffdy, ");
		sql.append("decode(a.bzrdbs,'0','否','是') sfbzr ");
		sql.append("from ( ");
		sql.append("select a.*,b.bmmc, ");
		sql.append("decode(a.xb,'1','男','2','女',a.xb) xbmc, ");
		sql.append("decode(b.bmlb,'5','院级','校级') bmjb, ");
		sql.append("nvl(c.num,0) fdydbs,nvl(d.num,0) bzrdbs,");
		sql.append("decode(e.yhm,null,'否','是') sfyh, ");
		sql.append("xx.dls ");
		sql.append(",k.symc ");
		sql.append("from fdyxxb a ");
		sql.append("left join zxbz_xxbmdm b ");
		sql.append("on a.bmdm=b.bmdm ");
		sql.append(" left join (select c.zgh, count(distinct bjdm) num ");
		sql.append("   from fdybjb c ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by c.zgh) c ");
		sql.append(" on a.zgh=c.zgh ");
		sql.append(" left join (select d.zgh, count(distinct bjdm) num ");
		sql.append("  from bzrbbb d ");
		sql.append("  where bjdm in ");
		sql.append("       (select bjdm from view_njxyzybj) ");
		sql.append("  group by d.zgh) d ");
		sql.append(" on a.zgh=d.zgh ");
		sql.append("left join yhb e ");
		sql.append("on a.zgh=e.yhm ");
		sql.append(" left join xg_xtwh_sydmb k on a.sydm=k.sydm ");
		sql.append(" left join (select username, count(1) dls ");
		sql.append("  from xg_xtgl_log_dl ");
		sql.append("  group by username) xx ");
		sql.append(" on xx.username = a.zgh ");
		sql.append(") a ");
		sql.append("where 1=1 ");
		sql.append(") a ");
		sql.append(query);
		sql.append(" order by bmdm,xm ) a ");
		sql.append("where 1=1 ");

		String userType = user.getUserType();
		String userDep = user.getUserDep();
		if ("xy".equalsIgnoreCase(userType)) {
			sql.append(" and bmdm='" + userDep + "' ");
		}

		List<HashMap<String, String>> list = (ArrayList<HashMap<String, String>>) CommonQueryDAO.commonPageQueryForExportMap(myForm.getPages(), sql.toString(), inputV);

		return list;
	}

	/**
	 * @描述:(获取教职工信息)
	 * @作者：cmj[工号：913]
	 * @日期：2013-10-23 下午04:39:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getJzgxx(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select  a.* ,   (select xb from dmk_xb where xbdm = a.xb) xbmc ,(select bmmc from zxbz_xxbmdm t where a.bmdm=t.bmdm)bmmc,(select zwmc from zwb t where t.zwdm=a.zw)zwmc,");
		sql.append("(select zzmmmc from zzmmdmb t where t.zzmmdm=a.zzmm)zzmmmc,(select mzmc from mzdmb t where t.mzdm=a.mz)mzmc,(select t.zcmc from zcb t where t.zcdm = a.zc)zcmc ,");
		sql.append("(select sydq from dmk_sydq t where t.sydqdm=a.jgxs)jgxsmc from fdyxxb a where zgh=?");

		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}
	
	/** 
	 * (查看教职工信息)浙江医学高等专科学校
	 */
	public HashMap<String, String> ckJzgxx_13023(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select  a.* ,   (select xb from dmk_xb where xbdm = a.xb) xbmc ,(select bmmc from zxbz_xxbmdm t where a.bmdm=t.bmdm)bmmc,(select zwmc from zwb t where t.zwdm=a.zw)zwmc,");
		sql.append("(select zzmmmc from zzmmdmb t where t.zzmmdm=a.zzmm)zzmmmc,(select mzmc from mzdmb t where t.mzdm=a.mz)mzmc,");
		sql.append("(select sydq from dmk_sydq t where t.sydqdm=a.jgxs)jgxsmc from fdyxxb a where zgh=?");

		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	/**
	 * 
	 * @描述:TODO江西师范获取教职工信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 上午11:09:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getJzgxxJxsf(String zgh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select  a.* ,   (select xb from dmk_xb where xbdm = a.xb) xbmc ,(select bmmc from zxbz_xxbmdm t where a.bmdm=t.bmdm)bmmc,(select zwmc from zwb t where t.zwdm=a.zw)zwmc,");
		sql.append("(select zzmmmc from zzmmdmb t where t.zzmmdm=a.zzmm)zzmmmc,(select mzmc from mzdmb t where t.mzdm=a.mz)mzmc,");
		sql.append("(select lxmc from zzfdysflxb t where t.lxdm=a.kzzd17)sfjtlxmc,(select zcmc from zcb t where t.zcdm=a.zc)zcmc,");
		
		sql.append("(select zcmc from zcb t where t.zcdm=a.zc)zcmc,(select lxmc from fdybzlxb t where t.lxdm=a.kzzd11)bzlxmc,");
		sql.append("(select sydq from dmk_sydq t where t.sydqdm=a.jgxs)jgxsmc from fdyxxb a where zgh=?");

		DAO dao = DAO.getInstance();

		return dao.getMapNotOut(sql.toString(), new String[] { zgh });
	}

	/**
	 * 
	 * @描述:获取教职工信息导出
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-8 下午03:02:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getJzgxxList(SzdwGeneralForm model, User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		sql.append("select  rownum r,a.* ,   (select xb from dmk_xb where xbdm = a.xb) xbmc ,(select bmmc from zxbz_xxbmdm t where a.bmdm=t.bmdm)bmmc,(select zwmc from zwb t where t.zwdm=a.zw)zwmc,");
		sql.append("(select zzmmmc from zzmmdmb t where t.zzmmdm=a.zzmm)zzmmmc,(select mzmc from mzdmb t where t.mzdm=a.mz)mzmc,");
		sql.append("(select lxmc from fdybzlxb t where t.lxdm=a.kzzd11)bzmc,");
	    sql.append("(select zcmc from zcb t where t.zcdm=a.zc)zcmc,");
		sql.append("(select sydq from dmk_sydq t where t.sydqdm=a.jgxs)jgxsmc from fdyxxb a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		sql.append(" order by bmdm");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:获取辅导员所带班级
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-11 上午10:34:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return String 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getFdyBj(String zgh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select wm_concat(bjmc) sdbj,wm_concat(rs) bjrs, count(bjmc) bjgs,sum(rs) zrs from (select a.xymc,bjmc,wm_concat(d.rs) rs from view_njxyzybj a left join fdybjb b on a.bjdm = b.bjdm ");
		sql.append("left join (select bjdm,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by bjdm)d on b.bjdm=d.bjdm ");
		sql.append(" where  b.zgh = ? group by xymc,bjmc)) where bjgs<>0");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[] { zgh });
	}

	public List<HashMap<String, String>> getBzrBj(String zgh) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from(select wm_concat(bjmc) sdbj,wm_concat(rs) bjrs, count(bjmc) bjgs,sum(rs) zrs from (select a.xymc,bjmc,wm_concat(d.rs) rs from view_njxyzybj a left join bzrbbb b on a.bjdm = b.bjdm ");
		sql.append("left join (select bjdm,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by bjdm)d on b.bjdm=d.bjdm ");
		sql.append(" where  b.zgh = ? group by xymc,bjmc)) where bjgs<>0");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[] { zgh });
	}
	
	/**
	 * @描述：班主任、辅导员带班情况
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月28日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getDbxx(SzdwGeneralForm model, User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		sql.append(" select * from (select dense_rank()over(order by a.bmdm,a.zgh) rn,a.*,b.bmmc,c.zwmc,decode(xb,'1','男','2','女',xb)xbmc,d.bjdm,d.bjmc,d.zydm,d.zymc,d.xydm,d.xymc, ");
		sql.append(" trunc((to_char(sysdate, 'yyyymmdd') - (to_char(to_date(a.csrq,'yyyy-mm-dd'), 'yyyymmdd')))/10000) nl, ");
		sql.append(" d.bjrs,e.bjgs,e.zrs,f.zzmmmc,g.mzmc,h.lxmc bzmc,i.zcmc,j.sydq jgxsmc from fdyxxb a ");
		sql.append(" left join zxbz_xxbmdm b on a.bmdm=b.bmdm ");
		sql.append(" left join zwb c on a.zw=c.zwdm ");
		sql.append(" left join (select a.zgh,b.*,c.rs bjrs from (select distinct zgh,bjdm from (select * from fdybjb union select * from bzrbbb))a ");
		sql.append(" left join view_njxyzybj b on a.bjdm=b.bjdm ");
		sql.append(" left join (select bjdm, count(xh) rs from view_xsjbxx group by bjdm) c on a.bjdm=c.bjdm ");
		sql.append(" where rs>0 and bjmc is not null) d on a.zgh=d.zgh ");
		sql.append(" left join (select zgh,count(1) bjgs,sum(rs) zrs from (select a.zgh,b.*,c.rs from ( ");
		sql.append(" select distinct zgh,bjdm from (select * from fdybjb union select * from bzrbbb))a ");
		sql.append(" left join view_njxyzybj b on a.bjdm=b.bjdm ");
		sql.append(" left join (select bjdm, count(xh) rs from view_xsjbxx group by bjdm) c on a.bjdm=c.bjdm ");
		sql.append(" where rs>0 and bjmc is not null) group by zgh) e on a.zgh=e.zgh ");
		sql.append(" left join zzmmdmb f on a.zzmm=f.zzmmdm ");
		sql.append(" left join mzdmb g on a.mz=g.mzdm ");
		sql.append(" left join fdybzlxb h on a.kzzd11=h.lxdm ");
		sql.append(" left join zcb i on a.zc=i.zcmc ");
		sql.append(" left join dmk_sydq j on a.jgxs=j.sydqdm ");
		sql.append(" where e.bjgs>0 order by a.bmdm,a.zgh,d.xydm,d.zydm,d.bjdm ) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	

	/**
	 * 
	 * @描述:辅导员、班主任配备情况
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-11 下午04:16:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPbxxList(SzdwGeneralForm model, User user, List<HashMap<String, String>> dmList) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("select a.xydm,a.xymc,a.bzr,b.sumbj,c.sumxs,d.jzfdy  ");

		if (null != dmList) {
			for (int i = 0; i < dmList.size(); i++) {
				sql.append(",zzfdy" + i);
			}
		}
		sql.append(" from (select xydm,xymc,max(bzr)bzr from ( select a.xydm,a.xymc,to_char(WM_CONCAT(b.xm) over(partition by a.xydm order by a.xydm)) bzr from (select distinct xydm, xymc from view_njxyzybj) a ");
		sql.append("left join (select distinct c.xydm, a.zgh, b.xm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh left join view_njxyzybj c on a.bjdm = c.bjdm) b on a.xydm = b.xydm ) group by xydm,xymc) a ");
		sql.append("left join (select xydm,count(1) sumbj from view_njxyzybj b group by xydm) b on a.xydm=b.xydm left join ");
		sql.append("(select xydm,count(1) sumxs from view_xsbfxx where xydm is not null and (sfzx='在校' or sfzx is null) group by xydm) c on a.xydm =c.xydm left join ");
		sql.append("(select a.xydm,a.xymc,WM_CONCAT(distinct(b.xm))jzfdy from view_njxyzybj a ");
		sql.append("left join (select a.zgh, a.bjdm, b.xm from fdybjb a left join fdyxxb b on a.zgh = b.zgh where b.kzzd16 = '兼职辅导员'or b.kzzd16 is null or b.kzzd16='') b on a.bjdm=b.bjdm group by xydm,xymc order by xydm) d on a.xydm=d.xydm ");
		if (null != dmList) {
			for (int i = 0; i < dmList.size(); i++) {
				sql.append(" left join (select xydm,xymc,max(fdy) zzfdy" + i
						+ " from (select a.xydm,a.xymc,to_char(WM_CONCAT(b.xm) over(partition by a.xydm order by a.xydm)) fdy from (select distinct xydm,xymc from view_njxyzybj) a ");
				sql.append("left join (select distinct c.xydm,a.zgh, b.xm from fdybjb a left join fdyxxb b on a.zgh = b.zgh left join view_njxyzybj c on a.bjdm=c.bjdm where b.kzzd16='专职辅导员' and b.kzzd17= '" + dmList.get(i).get("lxdm")
						+ "') b on a.xydm=b.xydm ) group by xydm,xymc) t" + i + " on a.xydm= t" + i + ".xydm");
			}
		}
		sql.append(" where 1=1 order by a.xydm ");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	
	/**
	 * @描述：辅导员、班主任配备情况
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年6月29日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPbxxList1(SzdwGeneralForm model, User user, List<HashMap<String, String>> dmList) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("select a.xydm,a.xymc,a.bzr,b.sumbj,c.sumxs,d.jzfdy  ");

		if (null != dmList) {
			for (int i = 0; i < dmList.size(); i++) {
				sql.append(",zzfdy" + i);
			}
		}
		sql.append(" from (select xydm,xymc,max(bzr)bzr from ( select a.xydm,a.xymc,to_char(WM_CONCAT(b.xm) over(partition by a.xydm order by a.xydm)) bzr from (select distinct xydm, xymc from view_njxyzybj) a ");
		sql.append("left join (select c.xydm, a.zgh, b.xm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh left join view_njxyzybj c on a.bjdm = c.bjdm ");
		sql.append("union select bmdm xydm, zgh, xm from fdyxxb b where b.kzzd16 = '班主任' ");
		sql.append(") b on a.xydm = b.xydm ) group by xydm,xymc) a ");
		sql.append("left join (select xydm,count(1) sumbj from view_njxyzybj b group by xydm) b on a.xydm=b.xydm left join ");
		sql.append("(select xydm,count(1) sumxs from view_xsbfxx where xydm is not null and (sfzx='在校' or sfzx is null) group by xydm) c on a.xydm =c.xydm left join ");
		
		sql.append("( select xydm,WM_CONCAT(xm) jzfdy from( "); 
		sql.append("select c.xydm,a.zgh, b.xm from fdybjb a "); 
		sql.append("left join fdyxxb b on a.zgh = b.zgh "); 
		sql.append("left join view_njxyzybj c on a.bjdm = c.bjdm "); 
		sql.append("where b.kzzd16 = '兼职辅导员' or b.kzzd16 is null "); 
		sql.append("union "); 
		sql.append("select bmdm xydm,zgh,xm from fdyxxb b "); 
		sql.append("where b.kzzd16 = '兼职辅导员') group by xydm "); 
		sql.append(") d on a.xydm= d.xydm "); 
		
		if (null != dmList) {
			for (int i = 0; i < dmList.size(); i++) {
				sql.append("left join ( select xydm,WM_CONCAT(xm) zzfdy"+i+" from( "); 
				sql.append("select c.xydm,a.zgh, b.xm from fdybjb a "); 
				sql.append("left join fdyxxb b on a.zgh = b.zgh "); 
				sql.append("left join view_njxyzybj c on a.bjdm = c.bjdm "); 
				sql.append("where b.kzzd16 = '专职辅导员' and b.kzzd17 = '"+ dmList.get(i).get("lxdm")+"' "); 
				sql.append("union "); 
				sql.append("select bmdm xydm,zgh,xm from fdyxxb b "); 
				sql.append("where b.kzzd16 = '专职辅导员' and b.kzzd17 = '"+ dmList.get(i).get("lxdm")+"') group by xydm "); 
				sql.append(") t"+ i + " on a.xydm= t" + i + ".xydm "); 
				
			}
		}
		sql.append(" where 1=1 order by a.xydm ");
		DAO dao = DAO.getInstance();
		return dao.getListNotOut(sql.toString(), new String[] {});
	}
	

	/**
	 * 
	 * @描述:获取辅导员身份具体类型
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 上午09:36:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getFdySflx() {
		DAO dao = DAO.getInstance();
		return dao.getListNotOut("select * from zzfdysflxb", new String[] {});

	}
	/**
	 * 
	 * @描述: 获取兼职辅导员人数
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 下午12:34:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getJzfdyRs(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) jzfdyrs from (select distinct b.zgh, b.xm,a.xydm,a.xymc from view_njxyzybj a ");
		sql.append(" left join (select a.zgh, a.bjdm, b.xm, b.kzzd16 from fdybjb a  left join fdyxxb b on a.zgh = b.zgh) ");
		sql.append("b on a.bjdm = b.bjdm where b.kzzd16 = '兼职辅导员' or b.kzzd16 is null or b.kzzd16='' ) where xydm=?");
		DAO dao = DAO.getInstance();
		return dao.getOneRs(sql.toString(), new String[] {xydm}, "jzfdyrs");

	}
	
	/**
	 * 
	 * @描述: 获取班主任人数
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 下午12:34:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBzrRs(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) bzrrs from(select xydm,xymc,xm from (select a.xydm,a.xymc,b.xm from view_njxyzybj a ");
		sql.append(" left join (select a.zgh, a.bjdm, b.xm from bzrbbb a left join fdyxxb b on a.zgh = b.zgh) b on a.bjdm=b.bjdm ) ");
		sql.append("group by xydm,xymc,xm) where xydm =? group by xydm ");
		DAO dao = DAO.getInstance();
		return dao.getOneRs(sql.toString(), new String[] {xydm}, "bzrrs");

	}
	
	/**
	 * 
	 * @描述: 获取专职辅导员人数
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-8-12 下午12:34:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xydm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZzfdyRs(String xydm) {
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) zzfdyrs from ( select distinct b.zgh, b.xm,a.xydm,a.xymc from view_njxyzybj a ");
		sql.append(" left join (select a.zgh, a.bjdm, b.xm, b.kzzd16 from fdybjb a  left join fdyxxb b on a.zgh = b.zgh) ");
		sql.append("b on a.bjdm = b.bjdm where b.kzzd16 = '专职辅导员' ) where xydm=?");
		DAO dao = DAO.getInstance();
		return dao.getOneRs(sql.toString(), new String[] {xydm}, "zzfdyrs");

	}
	
	/**
	 * @描述: 思政队伍设置保存
	 * @作者：江水才[工号：1150]
	 * @日期：2014-12-10 下午02:39:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids 
	 * @param sf 是否思政队伍
	 * @return
	 * @throws Exception
	 */
	public boolean szdwSzSave(String ids, String sf) throws Exception{
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append(" update fdyxxb set sfbl=? where  ");
		params.add(sf);
		String[] arr = ids.split("!!array!!");
		for (int i = 0; i < arr.length; i++) {
			sql.append(" zgh=? ");
			params.add(arr[i]);
			if(i < arr.length - 1){
				sql.append(" or ");
			}
		}
		DAO dao = DAO.getInstance();
		return dao.runUpdate(sql.toString(), params.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 导出带班学生信息(江西航空)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-10-12 下午02:12:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDbStu(String zgh,String lx){
		String tableName = "bzrbbb";
		if("fdy".equals(lx)){
			tableName = "fdybjb";
		}
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.nj,a.xymc,a.zymc,a.bjmc,c.xh,c.xm,c.xb");
		sql.append(" from view_njxyzybj a,");
		sql.append(tableName +" b,view_xsjbxx c ");
		sql.append(" where a.bjdm = b.bjdm and a.bjdm = c.bjdm");
		sql.append(" and b.zgh = ? order by a.nj,a.xydm,a.zydm,a.bjdm,c.xh");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{zgh});
	}

	public List<HashMap<String, String>> getSyList() {
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select sydm,symc from xg_xtwh_sydmb ");
		return dao.getList(sql.toString(), new String[] {}, new String[] { "sydm", "symc"});
	}
}