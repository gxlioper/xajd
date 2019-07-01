package xgxt.pjpy.jhzy.dyf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class DyfDAO extends JhzyPjpyDAO {

	/**
	 * @author luo
	 * @describe 获得德育分列表
	 */
	public ArrayList<String[]> getDyfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = new DAO();
		
		//HashMap<String,String> map = getFzPm("3061001057","2008-2009","01");
		//List<HashMap<String,String>> map = getCjList("3061001057","2008-2009");
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{xq},"xqmc");
		String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.getJxjsqxq()},"xqmc");
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xqmc) ? " and 1=1" : " and xq='"+xqmc+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select t.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,dyf  from (select *");
		sql.append(" from view_jhzy_dyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql.append(" '' dyf from view_xsjbxx b where not exists(select 1 from jhzy_dyf a where ");
		sql.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query+" )");
		sql.append(" where r > "+model.getPages().getStart()+ " and r <= ");
		sql.append((model.getPages().getStart() + model.getPages().getPageSize()));

		System.out.println(sql);
		ArrayList<String[]> list =dao.rsToVator(sql.toString(), inPutList,colList);
		//	分页
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select count(*) num from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,dyf  from (select *");
		sql2.append(" from view_jhzy_dyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql2.append(" '' dyf from view_xsjbxx b where not exists(select 1 from jhzy_dyf a where ");
		sql2.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query);
			
		System.out.println(sql2);
		model.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql2.toString(), inPutList, "num")));
		
		return list;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存德育分
	 */
	public boolean saveDyf(DyfModel model) throws SQLException {
		
		DAO dao = new DAO();
		
		String[] xh = model.getXhV();
		String[] xn = model.getXnV();
		String[] xq = model.getXqV();
		String[] dyf = model.getDyf();
		
		boolean flg = false;
		String sql = "";
		String xqdm = "";
		String pk = "";
		StringBuffer sb = new StringBuffer();
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				sql="select xqdm from xqdzb where xqmc = ?";
				xqdm = dao.getOneRs(sql, new String[]{DealString.toGBK(xq[i])},"xqdm");
				pk=xh[i]+xn[i]+xqdm;
				
				sql="delete from jhzy_dyf where xh||xn||xq='"+pk+"'";
				sb.append(sql);
				sb.append("!!#!!");
				
				sql="insert into jhzy_dyf(xh,xn,xq,dyf) values('"+xh[i]+"','"+xn[i]+"','"+xqdm+"','"+dyf[i]+"')";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		String[] actsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得体育分列表
	 */
	public ArrayList<String[]> getTyfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = new DAO();
		
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{xq},"xqmc");
		String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.getJxjsqxq()},"xqmc");
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xqmc) ? " and 1=1" : " and xq='"+xqmc+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select t.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,tyf  from (select *");
		sql.append(" from view_jhzy_tyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql.append(" '' tyf from view_xsjbxx b where not exists(select 1 from jhzy_tyf a where ");
		sql.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query+" )");
		sql.append(" where r > "+model.getPages().getStart()+ " and r <= ");
		sql.append((model.getPages().getStart() + model.getPages().getPageSize()));

		//System.out.println(sql);
		ArrayList<String[]> list =dao.rsToVator(sql.toString(), inPutList,colList);
		//	分页
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select count(*) num from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,tyf  from (select *");
		sql2.append(" from view_jhzy_tyf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql2.append(" '' tyf from view_xsjbxx b where not exists(select 1 from jhzy_tyf a where ");
		sql2.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query);
			
		model.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql2.toString(), inPutList, "num")));
		
		return list;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存体育分
	 */
	public boolean saveTyf(DyfModel model) throws SQLException {
		
		DAO dao = new DAO();
		
		String[] xh = model.getXhV();
		String[] xn = model.getXnV();
		String[] xq = model.getXqV();
		String[] tyf = model.getTyf();
		
		boolean flg = false;
		String sql = "";
		String xqdm = "";
		String pk = "";
		StringBuffer sb = new StringBuffer();
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				sql="select xqdm from xqdzb where xqmc = ?";
				xqdm = dao.getOneRs(sql, new String[]{DealString.toGBK(xq[i])},"xqdm");
				pk=xh[i]+xn[i]+xqdm;
				
				sql="delete from jhzy_tyf where xh||xn||xq='"+pk+"'";
				sb.append(sql);
				sb.append("!!#!!");
				
				sql="insert into jhzy_tyf(xh,xn,xq,tyf) values('"+xh[i]+"','"+xn[i]+"','"+xqdm+"','"+tyf[i]+"')";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		String[] actsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得技能分列表
	 */
	public ArrayList<String[]> getJnfList(String tableName, DyfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		DAO dao = new DAO();
		
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		String xqmc = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{xq},"xqmc");
		String dqxq = dao.getOneRs("select xqmc from xqdzb where xqdm = ?", new String[]{Base.getJxjsqxq()},"xqmc");
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xqmc) ? " and 1=1" : " and xq='"+xqmc+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		StringBuffer sql = new StringBuffer();

		sql.append("select * from (select t.*,rownum r from (select distinct (xh),xm,xb,nj,xydm,");
		sql.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,jnf  from (select *");
		sql.append(" from view_jhzy_jnf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql.append(" '' jnf from view_xsjbxx b where not exists(select 1 from jhzy_jnf a where ");
		sql.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query+" )");
		sql.append(" where r > "+model.getPages().getStart()+ " and r <= ");
		sql.append((model.getPages().getStart() + model.getPages().getPageSize()));

		//System.out.println(sql);
		ArrayList<String[]> list =dao.rsToVator(sql.toString(), inPutList,colList);
		//	分页
		StringBuffer sql2 = new StringBuffer();
		sql2.append("select count(*) num from (select distinct (xh),xm,xb,nj,xydm,");
		sql2.append(" xymc,zydm,zymc,bjdm,bjmc,nvl(xn, dqxn) xn,nvl(xq, dqxq) xq,jnf  from (select *");
		sql2.append(" from view_jhzy_jnf union select xh,xm,xb,nj,xydm,xymc,zydm,");
		sql2.append(" zymc,bjdm,bjmc,'' xn,'"+Base.getJxjsqxn()+"' dqxn,'' xq,'"+dqxq+"' dqxq,");
		sql2.append(" '' jnf from view_xsjbxx b where not exists(select 1 from jhzy_jnf a where ");
		sql2.append(" a.xh||a.xn||a.xq = b.xh||'"+Base.getJxjsqxn()+"'||'"+Base.getJxjsqxq()+"')) order by bjdm,xh,xn,xq) t "+query);
			
		model.getPages().setMaxRecord(
				Integer.parseInt(dao
						.getOneRs(sql2.toString(), inPutList, "num")));
		
		return list;
	}

	/**
	 * @author luo
	 * @throws SQLException 
	 * @describe 保存技能分
	 */
	public boolean saveJnf(DyfModel model) throws SQLException {
		
		DAO dao = new DAO();
		
		String[] xh = model.getXhV();
		String[] xn = model.getXnV();
		String[] xq = model.getXqV();
		String[] jnf = model.getJnf();
		
		boolean flg = false;
		String sql = "";
		String xqdm = "";
		String pk = "";
		StringBuffer sb = new StringBuffer();
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				sql="select xqdm from xqdzb where xqmc = ?";
				xqdm = dao.getOneRs(sql, new String[]{DealString.toGBK(xq[i])},"xqdm");
				pk=xh[i]+xn[i]+xqdm;
				
				sql="delete from jhzy_jnf where xh||xn||xq='"+pk+"'";
				sb.append(sql);
				sb.append("!!#!!");
				
				sql="insert into jhzy_jnf(xh,xn,xq,jnf) values('"+xh[i]+"','"+xn[i]+"','"+xqdm+"','"+jnf[i]+"')";
				sb.append(sql);
				sb.append("!!#!!");
			}
		}
		String[] actsql = sb.toString().split("!!#!!");

		int[] res = dao.runBatch(actsql);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}
}
