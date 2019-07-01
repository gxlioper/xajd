package xsgzgl.pjpy.czzyjsxy.zhcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_池州职业技术学院_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class PjpyZhcpDAO extends xsgzgl.pjpy.general.zhcp.PjpyZhcpDAO {

	// =====================综测分begin===========================

	/**
	 * 参评组排名计算
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.cpzpm from( ");

		// -------------------参评组排名计算 begin----------------------
		sql.append(" select a.xh,a.cpzpm,cpz ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" cpz order by to_number(trim(zd1)) desc nulls last)) cpzpm ");
		sql.append(" from (select a.xh, zd1, cpz, xn, xq, nd ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		// -------------------参评组排名计算 end------------------------

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级学院排名,并且放入临时表
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean njxypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,xydm order by to_number(trim(zd1)) desc nulls last)) xypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级专业排名,并且放入临时表
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean njzypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,zydm order by to_number(trim(zd1)) desc nulls last)) zypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算班级排名,并且放入临时表
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" bjdm order by to_number(trim(zd1)) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		System.out.println(sql);
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 综测(上)班级排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zcsBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" bjdm order by to_number(trim(zd2)) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		System.out.println(sql);
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 综测(下)班级排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zcxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" bjdm order by to_number(trim(zd3)) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		System.out.println(sql);
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新综测(上)排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcspm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_pjpy_zhcpb  a set zd20=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新综测(下)排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateZcxpm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_pjpy_zhcpb  a set zd22=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}
	
	// =====================综测分end===========================

	// =====================智育分begin===========================

	/**
	 * 参评组智育分排名计算
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zyfcpzpm from( ");

		// -------------------参评组排名计算 begin----------------------
		sql.append(" select a.xh,a.zyfcpzpm,cpz ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" cpz order by to_number(fs1+fs2) desc nulls last)) zyfcpzpm ");
		sql
				.append(" from (select a.xh, nvl(zd5,0) fs1,nvl(zd9,0) fs2, cpz, xn, xq, nd ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		// -------------------参评组排名计算 end------------------------

		// 数据进入正式库后，清空临时表
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级学院智育排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njxyZypmjs(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,xydm order by to_number(fs1+fs2) desc nulls last)) xypm ");
		sql
				.append(" from (select a.xh, nvl(zd5,0) fs1,nvl(zd9,0) fs2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级专业排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njzyZypmjs(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,zydm order by to_number(fs1+fs2) desc nulls last)) zypm ");
		sql
				.append(" from (select a.xh, nvl(zd5,0) fs1,nvl(zd9,0) fs2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();
		return saveArrDate(sqlArr);
	}

	/**
	 * 计算班级智育排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(fs1+fs2) desc nulls last)) bjpm ");
		sql
				.append(" from (select a.xh,nvl(zd5,0) fs1,nvl(zd9,0) fs2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 智育(上)班级排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zysBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(fs1) desc nulls last)) bjpm ");
		sql
				.append(" from (select a.xh,nvl(zd5,0) fs1,nvl(zd9,0) fs2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 智育(下)班级排名
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public boolean zyxBjpm(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(fs2) desc nulls last)) bjpm ");
		sql
				.append(" from (select a.xh,nvl(zd5,0) fs1,nvl(zd9,0) fs2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新智育(上)排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateZyspm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zd21=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}
	
	/**
	 * 更新智育(下)排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateZyxpm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zd23=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}
	
	// =====================智育分end===========================

	// =====================综合分结果begin===========================
	/**
	 * 获取综测显示信息
	 * 
	 * @data 2012-12-10
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public List<String[]> getZhcpResultList(PjpyGeneralForm model, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		String zczq_tj = model.getSearchModel().getSearch_tj_zczq()[0];

		model.getSearchModel().setSearch_tj_zczq(null);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pmfs = jbszModel.getZcpm();

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;
		String[] inputSearch = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();

		String[] colList = new String[] { "xh", "xm", "nj", "xymc", "zymc",
				"bjmc", "zd4", "zd5", "zd6", "zd7", "zd2", "zcspm", "zyspm",
				"zd28", "zd30", "zd8", "zd9", "zd10", "zd11", "zd3", "zcxpm",
				"zyxpm", "zd27", "zd29" };

		sql.append(" select rownum r,a.*,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xh pkValue from( ");
		sql.append(" select xn,xq,nd,xh,xm,nj,xymc,xydm,zymc,zydm,bjdm,bjmc ");
		sql.append(" ,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13");
		sql.append(" ,zd14,zd15,zd16,zd17,zd18,zd19,zd20 zcspm,zd21 zyspm");
		sql.append(" ,zd22 zcxpm,zd23 zyxpm,zd24,zd25,zd26,zd27,zd28,zd29,zd30");
		sql.append(" ,zcfnjxypm,zcfnjzypm,zcfbjpm,zyfnjxypm,zyfnjzypm,zyfbjpm,cpzpm,zyfcpzpm,cpzdm,cpzmc,dyfbjpm from( select  a.*");
		sql.append(" ,b.nj,b.xymc,b.zymc,b.bjmc,b.cpzdm,b.cpzmc ");
		sql.append(" ,xydm,zydm,bjdm,xm ");
		sql.append("  from(select * from xg_pjpy_zhcpb where 1=1  ");

		String[] inPutList = null;

		String[] zczqArr = zczq_tj.split("luojw");
		String pjxn = zczqArr[0];
		String pjxq = zczqArr[1];
		String pjnd = zczqArr[2];

		sql.append("  and xn=?  and xq=? and nd=? ");
		inPutList = new String[] { pjxn, pjxq, pjnd };

		sql.append(" ) a left join ");
		sql.append(" xg_view_pjpy_pjryk ");
		sql.append(" b on a.xh=b.xh  ");

		if ("0".equalsIgnoreCase(pmfs)) {

			sql.append(" order by cpzdm,to_number(zd1) desc ");
		} else if ("1".equalsIgnoreCase(pmfs)) {

			sql.append(" order by nj,xydm,to_number(zd1) desc ");
		} else if ("2".equalsIgnoreCase(pmfs)) {

			sql.append(" order by nj,zydm,to_number(zd1) desc ");
		} else if ("3".equalsIgnoreCase(pmfs)) {

			sql.append(" order by bjdm,to_number(zd1) desc ");
		}

		sql.append(" )a)a  where 1=1 ");
		sql.append(searchTj);

		return CommonQueryDAO.commonQuery(sql.toString(), "", dao.unionArray(
				inPutList, inputSearch), colList, model);
	}

	// =====================综合分结果end===========================
	/**
	 * 获取当前周期综测项目
	 * 
	 * @return
	 */
	public List<Object> getZcByZqList(String xh) {
		
		DAO dao=DAO.getInstance();

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		List<HashMap<String, String>> zcxmByZqList = getZhcpXmByZq();

		StringBuilder zd = new StringBuilder();

		List<Object> bczcInfo = new ArrayList<Object>();

		String zd1mc = "";

		int n = 0;

		String[] top = new String[zcxmByZqList.size()-1];
		String[] colList = new String[zcxmByZqList.size()-1];

		for (int i = 0; i < zcxmByZqList.size(); i++) {

			HashMap<String, String> zcxmByZqMap = zcxmByZqList.get(i);

			if (!"zd1".equalsIgnoreCase(zcxmByZqMap.get("xmdm"))) {

				if (n != 0) {

					zd.append(",");
				}

				zd.append(zcxmByZqMap.get("xmdm"));

				top[n] = zcxmByZqMap.get("xmmc");
				colList[n] = zcxmByZqMap.get("xmdm");
				n++;

			} else {

				zd1mc = zcxmByZqMap.get("xmmc");
			}

		}
		//top[zcxmByZqList.size() - 1] = zd1mc;
		//colList[zcxmByZqList.size() - 1] = "zd1";

		//top[zcxmByZqList.size()] = "综测分班级排名";
		//colList[zcxmByZqList.size()] = "zcfbjpm";

		zd.append(",zd20");
		zd.append(",zd21");
		zd.append(",zd22");
		zd.append(",zd23");
		zd.append(",zd27");
		zd.append(",zd28");
		zd.append(",zd29");
		zd.append(",zd30");
		String[]kzzdArr={"zd20","zd21","zd22","zd23","zd27","zd28","zd29","zd30","zd1","zcfbjpm"};
		
		String[]kzzdTop={"综测排名[上]","智育排名[上]","综测排名[下]","智育排名[下]"
				,"等级[下]","等级[上]","备注[下]","备注[上]","综测总分","综测分班级排名"};
		zd.append(",zd1");
		zd.append(",zcfbjpm");

		bczcInfo.add(dao.unionArray(top, kzzdTop));

		sql.append("( select  ");
		sql.append(zd);
		sql.append(" from xg_pjpy_zhcpb where  ");
		sql.append("   xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and nd = ? ");
		sql.append("  and xh = ?) ");

		bczcInfo.addAll(CommonQueryDAO.commonQueryNotFy(sql.toString(), "",new String[] { pjxn, pjxq, pjnd, xh },
				dao.unionArray( colList, kzzdArr), null));

		return bczcInfo;
	}
}
