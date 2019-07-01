package xgxt.xsgygl.zjcm;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.write.Label;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.MakeQuery;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.GyglTyDAO;
import xgxt.xsgygl.GyglTyForm;

public class ZjcmGyglDAO extends GyglTyDAO {

	/**
	 * 获取系统周数信息
	 * 
	 * @author luo
	 */
	public HashMap<String, String> getXtZsInfo() {
		DAO dao = DAO.getInstance();

		String sql = "select qsrq, xqzs from xtszb where rownum = 1";
		HashMap<String, String> map = dao.getMap(sql, new String[] {},
				new String[] { "qsrq", "xqzs" });
		return map;
	}

	/**
	 * 判断数据库中存储的周次数目与系统设置表是否一致
	 * 
	 * @author luo
	 */
	public Boolean isZcYz(String xqzs, String xn, String xq) {
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "select count(zc) zzc from gygl_xnxqzsb where xn = ? and xq = ?";
		String zzc = dao.getOneRs(sql, new String[] { xn, xq }, "zzc");

		if (!xqzs.equalsIgnoreCase(zzc)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 判断数据库中卫生检查等级是否维护
	 * 
	 * @author luo
	 */
	public Boolean isWsjcdj() {
		DAO dao = DAO.getInstance();
		boolean flag = false;

		String sql = "select count(*) num from zjcm_wsjcdjb";
		String num = dao.getOneRs(sql, new String[] {}, "num");

		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 修改卫生检查分
	 * 
	 * @author luo
	 * @throws Exception
	 */
	public Boolean updateWsjcf(String fs, String pkValue,
			HttpServletRequest request) throws Exception {

		String tableName = "zjcm_wsjcb";
		String[] columns = new String[] { "fs" };
		String[] values = new String[] { fs };
		String primaryKey = "xn||xq||jcqs||jcsj";

		return StandardOperation.update(tableName, columns, values, primaryKey,
				pkValue, request);
	}

	/**
	 * 获得总寝室列表
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getZqsList(ZjcmGyglModel model) {

		DAO dao = DAO.getInstance();

		String needNj = model.getNeedNj();
		String tableName = ("no".equalsIgnoreCase(needNj)) ? "view_zjcm_wsjctj_nonj"
				: "view_zjcm_wsjctj_nj";

		String[] colList = null;
		StringBuffer sql = new StringBuffer();
		if ("no".equalsIgnoreCase(needNj)) {
			sql
					.append(" select distinct (a.xydm), a.xymc, nvl(b.num,0) num from view_njxyzybj a");
			sql
					.append(" left join (select count(distinct(ssbh)) num,xydm,xymc from ");
			sql.append(tableName);
			sql.append(" group by xydm,xymc order by xydm) b");
			sql.append(" on a.xydm = b.xydm order by xydm");

			colList = new String[] { "xydm", "xymc", "num" };
		} else {
			// sql.append(" select distinct (a.xydm), a.xymc,a.nj, nvl(b.num,0)
			// num from view_njxyzybj a");
			// sql.append(" left join (select count(distinct(ssbh))
			// num,nj,xydm,xymc from ");
			// sql.append(tableName);
			// sql.append(" group by nj,xydm,xymc order by xydm,nj) b");
			// sql.append(" on a.xydm = b.xydm and a.nj = b.nj order by
			// xydm,nj");

			sql.append(" select * from (select a.*, nvl(b.num, 0) num");
			sql.append(" from (select distinct (a.xydm), a.xymc, '无学生入住' nj");
			sql
					.append(" from view_njxyzybj a union all select distinct (a.xydm), ");
			sql.append(" a.xymc, to_char(a.nj) from view_njxyzybj a) a");
			sql
					.append(" left join (select count(distinct(ssbh)) num, nj, xydm, xymc");
			sql.append(" from " + tableName + " group by nj, xydm, xymc");
			sql
					.append(" order by xydm, nj) b on a.xydm = b.xydm and a.nj = to_char(b.nj))");
			sql.append(" order by xydm, nj");

			colList = new String[] { "xydm", "xymc", "nj", "num" };
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, colList);

		return list;
	}

	/**
	 * 获得卫生检查统计列表
	 * 
	 * @author luo
	 */
	public List<HashMap<String, String>> getTjList(ZjcmGyglModel model) {

		DAO dao = DAO.getInstance();

		String xn = model.getXn();
		String xq = model.getXq();
		String needNj = model.getNeedNj();
		String bblx = model.getBblx()
				.substring(0, model.getBblx().length() - 1);
		String tableName = ("no".equalsIgnoreCase(needNj)) ? "view_zjcm_wsjctj_nonj"
				: "view_zjcm_wsjctj_nj";

		String[] colList = null;
		StringBuffer sql = new StringBuffer();
		if ("no".equalsIgnoreCase(needNj)) {
			sql.append(" select xydm, xymc, count(ssbh) num from ");
			sql.append(tableName);
			sql.append(" where xn=? and xq=? and mc like ?||'%'");
			sql.append(" group by xydm, xymc");

			colList = new String[] { "xydm", "xymc", "num" };
		} else {
			sql.append(" select xydm, xymc,nj, count(ssbh) num from ");
			sql.append(tableName);
			sql.append(" where xn=? and xq=? and mc like ?||'%'");
			sql.append(" group by nj,xydm, xymc");

			colList = new String[] { "xydm", "xymc", "nj", "num" };
		}

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xn, xq, bblx }, colList);

		return list;
	}

	/**
	 * 获得公寓报修所属统计列表
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjSsList(GyglTyForm model) {

		DAO dao = DAO.getInstance();

		ArrayList<String> outputValue = new ArrayList<String>();

		String tjfw = model.getTjfw();

		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(getBxQuery(model));
		// 条件范围
		StringBuffer tjfwSql = new StringBuffer();
		// 分组范围
		StringBuffer groupSql = new StringBuffer();
		groupSql.append(" group by ");
		String orderBy = "";

		orderBy = getBxFw(model, outputValue, tjfwSql, groupSql, orderBy);

		outputValue.add("bxrs");
		outputValue.add("ywx");
		outputValue.add("wwx");
		outputValue.add("ypj");
		outputValue.add("wpj");

		StringBuffer sql = new StringBuffer();
		sql.append(" select a.*,nvl(b.ywx, '0') ywx,");
		sql.append(" (a.bxrs - nvl(b.ywx, '0')) wwx,");
		sql.append(" nvl(c.ypj, '0') ypj,");
		sql.append(" (a.bxrs - nvl(c.ypj, '0')) wpj from (");
		// ===================统计报修人数==============================
		sql.append(" select count(1) bxrs ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(groupSql);
		// ===================统计已维修人数==============================
		sql.append(" ) a left join (");
		sql.append(" select count(1) ywx ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and sfwg = '是' ");
		sql.append(groupSql);
		sql.append(" ) b ");
		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			sql.append(" on a.nj = b.nj ");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			sql.append(" on a.xydm = b.xydm ");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			sql.append(" on a.zydm = b.zydm ");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			sql.append(" on a.bjdm = b.bjdm ");
		} else if ("ld".equalsIgnoreCase(tjfw)) {// 统计范围（楼栋）
			sql.append(" on a.lddm = b.lddm ");
		}
		// ===================统计已评价人数==============================
		sql.append(" left join (");
		sql.append(" select count(1) ypj ");
		sql.append(tjfwSql);
		sql.append(" from view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and fwtd is not null ");
		sql.append(groupSql);
		sql.append(" ) c ");
		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）
			sql.append(" on a.nj = c.nj ");
		} else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）
			sql.append(" on a.xydm = c.xydm ");
		} else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）
			sql.append(" on a.zydm = c.zydm ");
		} else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）
			sql.append(" on a.bjdm = c.bjdm ");
		} else if ("ld".equalsIgnoreCase(tjfw)) {// 统计范围（楼栋）
			sql.append(" on a.lddm = c.lddm ");
		}

		sql.append(orderBy);

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * 获得公寓报修材料统计列表
	 * 
	 * @author luo
	 */
	public ArrayList<String[]> getBxTjClList(GyglTyForm model) {
		DAO dao = DAO.getInstance();

		ArrayList<String> outputValue = new ArrayList<String>();

		// 条件范围
		StringBuffer tjfwSql = new StringBuffer();
		// 分组范围
		StringBuffer groupSql = new StringBuffer();
		groupSql.append(" group by lxmc");

		String orderBy = "";
		getBxFw(model, outputValue, tjfwSql, groupSql, orderBy);

		// 统计范围
		String tjfw = model.getTjfw();
		// 材料类型
		String tjcllx = model.getTjcllx();
		// 材料名称
		String tjclmc = model.getTjclmc();
		// 查询条件
		StringBuilder query = new StringBuilder();
		query.append(getBxQuery(model));

		StringBuffer sql = new StringBuffer();
		sql.append("select sum(clsl) num,sum(zj) zj ");
		sql.append(tjfwSql);
		if ("cllx".equalsIgnoreCase(tjfw)) {
			outputValue.add("lxmc");
			sql.append(",b.cllx,b.lxmc ");
		} else {
			outputValue.add("clmc");
			outputValue.add("lxmc");
			sql.append(",b.clmc,b.lxmc ");
		}
		sql.append(" from view_gygl_zjcm_bxcl b,view_gygl_zjcm_gybx a ");
		sql.append(query);
		sql.append(" and a.xh = b.xh and a.bxsj = b.bxsj");
		sql.append(Base.isNull(tjcllx) ? "" : " and b.cllx = '" + tjcllx + "'");
		sql.append(Base.isNull(tjclmc) ? "" : " and b.clmc like '%" + tjclmc
				+ "%'");

		if ("cllx".equalsIgnoreCase(tjfw)) {
			groupSql.append(" ,cllx ");
			groupSql.append(" order by cllx ");
		} else {
			groupSql.append(" ,clmc ");
			groupSql.append(" order by clmc ");
		}
		sql.append(groupSql);

		outputValue.add("num");
		outputValue.add("zj");

		ArrayList<String[]> list = dao.rsToVator(sql.toString(),
				new String[] {}, outputValue.toArray(new String[] {}));

		return list;
	}

	/**
	 * @param model
	 * @param query
	 * @return
	 */
	private String getBxQuery(GyglTyForm model) {

		StringBuilder query = new StringBuilder();

		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 校区
		String xqdm = model.getXqdm();
		// 楼栋
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室
		String qsh = model.getQsh();
		// 是否收费
		String sfsf = model.getSfsf();
		// 是否完工
		String sfwg = model.getSfwg();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 性别
		String xb = model.getXb();
		// 开始时间
		String kssj = model.getQuerygreaterequal_bxsj();
		// 结束时间
		String jssj = model.getQuerylessequal_bxsj();

		query.append(" where 1 = 1 ");
		query.append(Base.isNull(nj) ? "" : " and a.nj = '" + nj + "'");
		query.append(Base.isNull(xb) ? "" : " and a.xb = '" + xb + "'");
		query.append(Base.isNull(xydm) ? "" : " and a.xydm = '" + xydm + "'");
		query.append(Base.isNull(zydm) ? "" : " and a.zydm = '" + zydm + "'");
		query.append(Base.isNull(bjdm) ? "" : " and a.bjdm = '" + bjdm + "'");
		query.append(Base.isNull(xqdm) ? "" : " and a.xqdm = '" + xqdm + "'");
		query.append(Base.isNull(lddm) ? "" : " and a.lddm = '" + lddm + "'");
		query.append(Base.isNull(cs) ? "" : " and a.cs = '" + cs + "'");
		query.append(Base.isNull(qsh) ? "" : " and a.qsh = '" + qsh + "'");
		query.append(Base.isNull(sfsf) ? "" : " and a.sfsf = '" + sfsf + "'");
		query.append(Base.isNull(sfwg) ? "" : " and a.sfwg = '" + sfwg + "'");
		query.append(Base.isNull(kssj) ? "" : " and a.bxsj >= '" + kssj + "'");
		query.append(Base.isNull(jssj) ? "" : " and a.bxsj <= '" + jssj + "'");
		query.append(Base.isNull(xh) ? "" : " and a.xh like '%" + xh + "%'");
		query.append(Base.isNull(xm) ? "" : " and a.xm like '%" + xm + "%'");

		return query.toString();
	}

	/**
	 * @param model
	 * @param outputValue
	 * @param tjfwSql
	 * @param groupSql
	 * @param orderBy
	 * @return
	 */
	private String getBxFw(GyglTyForm model, ArrayList<String> outputValue,
			StringBuffer tjfwSql, StringBuffer groupSql, String orderBy) {

		// 统计范围
		String tjfw = model.getTjfw();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 校区
		String xqdm = model.getXqdm();
		// 楼栋
		String lddm = model.getLddm();
		// 层数
		String cs = model.getCs();
		// 寝室
		String qsh = model.getQsh();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 性别
		String xb = model.getXb();
		
		if ("nj".equalsIgnoreCase(tjfw)) {// 统计范围（年级）

			outputValue.add("nj");
			groupSql.append(" nj ");
			tjfwSql.append(",a.nj ");
			orderBy = " order by nj";

		} else if ("xy".equalsIgnoreCase(tjfw)) {// 统计范围（学院）

			outputValue.add("xymc");
			groupSql.append(" xydm,xymc ");
			tjfwSql.append(",a.xydm,a.xymc ");
			orderBy = " order by xydm";

		} else if ("zy".equalsIgnoreCase(tjfw)) {// 统计范围（专业）

			outputValue.add("zymc");
			groupSql.append(" zydm,zymc ");
			tjfwSql.append(",a.zydm,a.zymc ");
			orderBy = " order by zydm";

		} else if ("bj".equalsIgnoreCase(tjfw)) {// 统计范围（班级）

			outputValue.add("bjmc");
			groupSql.append(" bjdm,bjmc ");
			tjfwSql.append(",a.bjdm,a.bjmc ");
			orderBy = " order by bjdm";

		} else if ("ld".equalsIgnoreCase(tjfw)) {// 统计范围（楼栋）

			outputValue.add("ldmc");
			groupSql.append(" lddm,ldmc ");
			tjfwSql.append(",a.lddm,nvl(a.ldmc,'无')ldmc ");
			orderBy = " order by lddm";

		}

		if (!Base.isNull(nj) && !"nj".equalsIgnoreCase(tjfw)) {
			// outputValue.add("zydm");
			outputValue.add("nj");
			tjfwSql.append(",a.nj ");
			groupSql.append(" ,nj ");
		}

		if (!Base.isNull(xydm) && !"xy".equalsIgnoreCase(tjfw)) {
			// outputValue.add("xydm");
			outputValue.add("xymc");
			tjfwSql.append(",a.xydm,a.xymc ");
			groupSql.append(" ,xydm,xymc ");
		}

		if (!Base.isNull(zydm) && !"zy".equalsIgnoreCase(tjfw)) {
			// outputValue.add("zydm");
			outputValue.add("zymc");
			tjfwSql.append(",a.zydm,a.zymc ");
			groupSql.append(" ,zydm,zymc ");
		}

		if (!Base.isNull(bjdm) && !"bj".equalsIgnoreCase(tjfw)) {
			// outputValue.add("bjdm");
			outputValue.add("bjmc");
			tjfwSql.append(",a.bjdm,a.bjmc ");
			groupSql.append(" ,bjdm,bjmc ");
		}

		if (!Base.isNull(xqdm)) {
			// outputValue.add("xqdm");
			outputValue.add("xqmc");
			tjfwSql.append(",a.xqdm,a.xqmc ");
			groupSql.append(" ,xqdm,xqmc ");
		}

		if (!Base.isNull(lddm) && !"ld".equalsIgnoreCase(tjfw)) {
			// outputValue.add("lddm");
			outputValue.add("ldmc");
			tjfwSql.append(",a.lddm,a.ldmc ");
			groupSql.append(" ,lddm,ldmc ");
		}

		if (!Base.isNull(cs)) {
			outputValue.add("cs");
			tjfwSql.append(",a.cs ");
			groupSql.append(" ,cs ");
		}

		if (!Base.isNull(qsh)) {
			outputValue.add("qsh");
			tjfwSql.append(",a.qsh ");
			groupSql.append(" ,qsh ");
		}

		if (!Base.isNull(xh) && !Base.isNull(xm)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		} else if (!Base.isNull(xh)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		} else if (!Base.isNull(xm)) {
			outputValue.add("xh");
			outputValue.add("xm");
			tjfwSql.append(",a.xh,a.xm ");
			groupSql.append(" ,a.xh,a.xm ");
		}

		return orderBy;
	}

	
	
	/**
	 * 卫生检查录入（列表）
	 * 
	 * @author quph
	 */
	public ArrayList<String[]> getWsjcQueryList(String tableName,
			ZjcmGyglModel model, String[] colList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();

		String kssj = model.getKssj();
		String jssj = model.getJssj();
		String other_query = "";

		if (!Base.isNull(kssj)) {
			other_query += " and jcsj >='" + kssj + "'";
		}
		if (!Base.isNull(jssj)) {
			other_query += " and jcsj <='" + jssj + "'";
		}

		String[] queryList = new String[] { "xqdm", "lddm", "cs", "qsh", "xn",
				"xq" };
		String[] njxyzybjList = new String[] { "nj", "xydm", "zydm", "bjdm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, new String[] {}, model);
		String query = myQuery.getQueryString();
		String[] inputList = myQuery.getInputList();

		myQuery.makeQuery(njxyzybjList, new String[] {}, model);
		String njxyzybjQuery = myQuery.getQueryString();
		String[] njxyzybjInputList = myQuery.getInputList();

		String[] allInputList = StringUtils.joinStrArr(njxyzybjInputList,
				inputList);

		StringBuilder sql = new StringBuilder();

		sql.append("select count(*) count from (select * from (select a.* from ");
		sql.append(tableName);
		sql.append(" a where exists (select 1 from (select * from view_xszsxx ");
		sql.append(njxyzybjQuery);
		sql.append(")b where b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh))");
		sql.append(query);
		sql.append(other_query);
		sql.append(")");
		
		
		Class myClass = model.getClass();
		Pages pages = (Pages) myClass.getMethod("getPages", (Class[]) null)
				.invoke(model, (Object[]) null);
		pages.setMaxRecord(Integer.parseInt(dao.getOneRs(sql.toString(),
				allInputList, "count")));

		StringBuilder sb = new StringBuilder();
		
		sb.append("select * from (select a.*,rownum r from (select a.* from ");
		sb.append(tableName);
		sb.append(" a where exists (select 1 from (select * from view_xszsxx ");
		sb.append(njxyzybjQuery);
		sb.append(")b where b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh)) a");
		sb.append(query);
		sb.append(other_query);
		sb.append(") where r > ");
		sb.append(pages.getStart());
		sb.append(" and r <= ");
		sb.append((pages.getStart() + pages.getPageSize()));
		
		return dao.rsToVator(sb.toString(), allInputList, colList);
	}
	
	/**
	 * 最大卫生检查次数
	 */
	public HashMap<String,String>getMaxTimes(GyglTyForm form){
		DAO dao =DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		if(!"".equalsIgnoreCase(form.getNj()) && null!=form.getNj()){
			sb.append(" and nj='"+form.getNj()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getXydm()) && null!=form.getXydm()){
			sb.append(" and xydm='"+form.getXydm()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getZydm()) && null!=form.getZydm()){
			sb.append(" and zydm='"+form.getZydm()+"'");
		}
		String sql=" select nvl(max(zcs), '0') zcs from (select nvl(max(zcs), '0') zcs from (select count(*) zcs, lddm, qsh from view_zjcm_wsjc ";
		sql+=" where xq = ?  and xn = ?  group by lddm, qsh) a, view_xszsxx b   where a.lddm = b.lddm  and a.qsh = b.qsh "+sb+"  group by a.lddm, a.qsh)";
		System.out.println(sql);
		return dao.getMap(sql, new String[]{form.getTjxq(),form.getTjxn()}, new String[]{"zcs"});
	}
	
	/**
	 * 按宿舍卫生检查时间排列
	 * 卫生检查信息
	 */
	public List<HashMap<String,String>>getWsjcxx(GyglTyForm form){
		DAO dao =DAO.getInstance();
		String sql=" select a.lddm,a.qsh,a.fs,a.JCSJ,RANK()OVER(PARTITION BY SSBH  ORDER BY JCSJ) Rank from  view_zjcm_wsjc a where a.xn=? and a.xq=? ";
		return dao.getList(sql, new String[]{form.getTjxn(),form.getTjxq()}, new String[]{"lddm","qsh","jcsj","fs"});
	}
	
	/**
	 * 学生住宿信息
	 */
	public List<HashMap<String,String>>getXszsxx(GyglTyForm form){
		DAO dao =DAO.getInstance();
		StringBuilder sb=new StringBuilder();
		if(!"".equalsIgnoreCase(form.getNj()) && null!=form.getNj()){
			sb.append(" and nj='"+form.getNj()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getXydm()) && null!=form.getXydm()){
			sb.append(" and xydm='"+form.getXydm()+"'");
		}
		
		if(!"".equalsIgnoreCase(form.getZydm()) && null!=form.getZydm()){
			sb.append(" and zydm='"+form.getZydm()+"'");
		}
		String sql=" select * from view_xszsxx where 1=1 "+sb;
		return dao.getList(sql, new String[]{}, new String[]{"xh","xm","xb","xymc","nj","bjmc","sfzh","lddm","ldmc","qsh","cwh","sfbz","xqdm","lxdh"});
	}
	
}
