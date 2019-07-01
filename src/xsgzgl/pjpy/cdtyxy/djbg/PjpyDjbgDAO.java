package xsgzgl.pjpy.cdtyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_登记表格_广东建设职业技术学院_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ljw
 * @version 1.0
 */

public class PjpyDjbgDAO extends CommDAO {

	/**
	 * 获取学生信息
	 * 
	 * @author ljw
	 */
	public HashMap<String, String> setXsxxInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh, a.xm, b.csrq,  ");
		sql.append("a.nj, a.xymc, a.zymc, a.bjmc,f.bjgbmc zw,g.pjf,  ");
		sql.append("decode(b.xb,'1','男','2','女',b.xb) xb,  ");
		sql.append("(select c.zzmmmc from zzmmdmb c where b.zzmm = c.zzmmdm ) zzmmmc,bjrs, ");
		sql.append("(select c.mzmc from mzdmb c where b.mz = c.mzdm) mzmc ");
		sql.append("from xg_view_pjpy_pjryk a left join xsxxb b ");
		sql.append("on a.xh=b.xh ");
		sql.append("left join (select d.xh, d.bjgbdm, e.bjgbmc ");
		sql.append("from sxjy_bjgbxxb d, sxjy_bjgbzlb e ");
		sql.append("where d.bjgbdm = e.bjgbdm ");
		sql.append("and d.xh = ? ");
		sql.append("and rownum = 1) f ");
		sql.append("on a.xh = f.xh ");
		sql.append("left join (select xh, round(avg(cj), 1) pjf ");
		sql.append("from cjb ");
		sql.append("where xn =? ");
		sql.append("and xh = ?  and regexp_like(cj,'^\\d{1,10}\\.?\\d{0,10}$') ");
		sql.append("group by xh) g ");
		sql.append("on a.xh = g.xh ");
		sql.append(" left join (select count(1) bjrs,bjdm from xg_pjpy_pjrykb group by bjdm )x on a.bjdm=x.bjdm ");
		sql.append("where a.xh = ? ");

		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				xh, xn, xh, xh }, new String[] { "xh", "xm", "xb", "csrq",
				"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc", "pjf", "zw","bjrs" });

		return map;
	}
	
	/**
	 * 获取学生成绩列表
	 * 
	 * @author ljw
	 */
	public List<HashMap<String, String>> getXscjList(String xh, String xn) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc, cj  ");
		sql.append("from cjb  ");
		sql.append("where xh = ?  ");
		sql.append("and xn = ?  ");
		
		List<HashMap<String, String>> list = dao.getList(sql.toString(), new String[] {
			xh,xn }, new String[] { "kcmc", "cj"});

		return list;
	}
	
	/**
	 * 获取学生综测信息
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getXszcInfo(String xh, String xn) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select zd1 zcf, zd2 dyf,zd3 zyf,zd4 tyf,zcfbjpm  ");
		sql.append("from xg_pjpy_zhcpb  ");
		sql.append("where xh = ?  ");
		sql.append("and xn = ?  ");
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
			xh,xn }, new String[] { "zcf", "dyf","zyf","tyf","zcfbjpm"});

		return map;
	}
	
	/**
	 * 学生评奖信息
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getXspjInfo(String xh, String xn,String xmmc ) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.sqly  ");
		
		sql.append(" from ( select xh,xmdm,pjxn,sqly from xg_pjpy_pjxmsqb_backup a where xh = ? union  ");
		sql.append(" select xh,xmdm,pjxn,sqly from xg_pjpy_pjxmsqb b where xh = ? )a,xg_pjpy_pjxmwhb b ");
		
		sql.append(" where a.pjxn=b.pjxn and a.xmdm=b.xmdm ");
		sql.append("and a.pjxn = ?  ");
		sql.append("and b.xmmc = ?  ");
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
			xh,xh,xn,xmmc }, new String[] { "sqly"});

		return map;
	}
	
	/**
	 * 学生评奖信息
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getKnsjb(String xh, String xn ) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xszz_knsb  where xmdm='5002' and xh=? and xn=?  ");
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
			xh,xn }, new String[] { "xmzzjb"});

		return map;
	}
	
	/**
	 * 学生平均
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getXspjcjInfo(String xh, String xn ) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(round(avg(cj),2),0)avgcj  ");
		
		sql.append(" from (select cj from cjb where (kcxz='必修课' or kcxz='限选课') and kcmc not like '%体育%'  and regexp_like(cj,'^\\d{1,10}\\.?\\d{0,10}$') and xn=? and xh=? )");
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
			xn,xh }, new String[] { "avgcj"});

		return map;
	}
	
	/**
	 * 最低成绩
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getZdcjInfo(String xh, String xn ) {
		
		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append(" select nvl(round(min(cj),2),0)mincj  ");
		
		sql.append(" from (select cj from cjb where (kcxz='必修课' or kcxz='限选课') and kcmc not like '%体育%' and regexp_like(cj,'^\\d{1,10}\\.?\\d{0,10}$') and xn=? and xh=? )");
		
		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
			xn,xh }, new String[] { "mincj"});

		return map;
	}
}