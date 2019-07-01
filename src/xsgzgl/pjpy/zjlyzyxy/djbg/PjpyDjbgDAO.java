package xsgzgl.pjpy.zjlyzyxy.djbg;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计分析_类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author CMJ
 * @version 1.0
 */

public class PjpyDjbgDAO extends xsgzgl.pjpy.general.djbg.PjpyDjbgDAO {

	/**
	 * 获取学生信息
	 * 
	 * @author CMJ
	 */
	public HashMap<String, String> setXsxxInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh, a.xm, b.csrq,b.rxrq,  ");
		sql.append("a.nj, a.xymc, a.zymc, a.bjmc,b.sjhm,b.sfzh, ");
		sql.append("decode(b.xb,'1','男','2','女',b.xb) xb,  ");
		sql.append("(select c.zzmmmc from zzmmdmb c where b.zzmm = c.zzmmdm ) zzmmmc, ");
		sql.append("(select c.mzmc from mzdmb c where b.mz = c.mzdm) mzmc ");
		sql.append("from xg_view_pjpy_pjryk a left join xsxxb b ");
		sql.append("on a.xh=b.xh ");
		sql.append("where a.xh = ? ");

		HashMap<String, String> map = dao.getMap(sql.toString(),
				new String[] { xh }, new String[] { "xh", "xm", "xb", "csrq","sjhm","rxrq","sfzh",
						"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc"});

		return map;
	}
	/**
	 * 获取学生成绩列表
	 * 
	 * @author CMJ
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
	 * 设置学生统计成绩：平均、最低、不及格数
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getXstjcjInfo(String xh, String xn) {
		
		DAO dao = DAO.getInstance();
		// TODO 自动生成方法存根
		StringBuilder sql=new StringBuilder();
		sql.append("select * from(select a.xn, a.xh, nvl(a.bxkms,0) bxkms,nvl(a.pjf,0) pjf,nvl(a.zdf,0) zdf, nvl(b.yxkms,0) yxkms, nvl(c.lhkms,0) lhkms,nvl(d.bjg,0) bjg from (");
		sql.append("select xn, xh, count(*) bxkms,round(avg(cj),2) pjf,min(to_number(cj)) zdf from view_zhhcjb where kcxz like '%必修%' group by xn, xh) a ");
		sql.append(" left join (select xn, xh, count(*) yxkms from cjb where kcxz like '%必修%' and to_number(cj) >= 90 group by xn, xh) b on a.xh = b.xh and a.xn = b.xn");
		sql.append(" left join (select xn, xh, count(*) lhkms from cjb where kcxz like '%必修%' and to_number(cj)>= 80 and to_number(cj)<=89 group by xn, xh) c on a.xn = c.xn and a.xh = c.xh ");
		sql.append(" left join (select xh, xn, count(*) bjg from cjb where bkcj is not null group by xh,xn) d on a.xn = d.xn and a.xh = d.xh");
		sql.append(" )where xh=? and xn=?");
		
		StringBuilder sql2=new StringBuilder();
		sql2.append("select * from (select a.xn,a.xq,a.xh, a.zd1, a.zyfnjzypm||'/'||c.zrs zyfnjzypm, a.zyfbjpm, a.zcfnjzypm||'/'||c.zrs zcfnjzypm, a.zcfbjpm, b.zydm, c.zrs ");
		sql2.append("from xg_pjpy_zhcpb a ");
		sql2.append("left join xsxxb b on a.xh = b.xh ");
		sql2.append("left join (select nj,zydm, count(*) zrs ");
		sql2.append(" from (select a.xh, b.zydm,b.nj from xg_pjpy_pjrykb a left join xsxxb b on a.xh = b.xh) group by zydm,nj) c on b.zydm = c.zydm and b.nj=c.nj)where xh=? and xn=? and xq='no'");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.putAll(dao.getMap(sql.toString(),new String[]{xh,xn},new String[]{"pjf","zdf","bxkms","yxkms","lhkms","bjg"}));
		map.putAll(dao.getMap(sql2.toString(),new String[]{xh,xn},new String[]{"zyfbjpm","zcfbjpm","zd1","zyfnjzypm","zcfnjzypm"}));
		return map;
		
	}
	/**
	 * 获得本次评奖审核意见
	 * 
	 * @date 2013-01-31
	 * @author CMJ
	 */
	public List<HashMap<String, String>> getBcsh(String xh, String xmmc) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 评奖周期
		String pjzq = jbszModel.getPjzq();

		StringBuilder sql = new StringBuilder();
		sql.append("select xh, shyj, shjb, sqly");
		sql.append(" from (select a.*, b.xmmc, c.sqly, d.xh shjb");
		sql.append(" from xg_pjpy_pjxmshb a,");
		sql.append("xg_pjpy_pjxmwhb b,xg_pjpy_pjxmsqb c,xg_xtwh_spbz d ");
		sql.append("where a.xmdm = b.xmdm");
		sql.append(" and a.xh = c.xh");
		sql.append(" and a.pjxn = c.pjxn");
		sql.append(" and a.pjxq = c.pjxq");
		sql.append(" and a.pjnd = c.pjnd");
		sql.append(" and a.xmdm = c.xmdm");
		sql.append(" and b.lcid = d.splc");
		sql.append(" and a.xtgwid = d.spgw");
		sql.append(" and b.xmmc='"+xmmc+"'");
		sql.append(" and a.xh=?");
		if ("xn".equalsIgnoreCase(pjzq)) {
			sql.append(" and b.pjxn=(select pjxn from xg_pjpy_xtszb) ");
		}
		if ("xq".equalsIgnoreCase(pjzq)) {
			sql.append(" and b.pjxn=(select pjxn from xg_pjpy_xtszb) ");
			sql.append(" and b.pjxq=(select pjxq from xg_pjpy_xtszb) ");
		}
		if ("nd".equalsIgnoreCase(pjzq)) {
			sql.append(" and pjnd=(select pjnd from xg_pjpy_xtszb) ");
		}

		sql.append(")");

		return dao.getList(sql.toString(), new String[]{xh}, new String[] { "xh", "shyj",
				"shjb","sqly" });
	}
	public List<HashMap<String, String>> getLssh(String xh, String xmmc, String xn) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select xh, shyj, shjb, sqly");
		sql.append(" from (select a.*, b.xmmc, c.sqly, d.xh shjb");
		sql.append(" from xg_pjpy_pjxmshb_backup a,");
		sql.append("xg_pjpy_pjxmwhb b,xg_pjpy_pjxmsqb_backup c,xg_xtwh_spbz d ");
		sql.append("where a.xmdm = b.xmdm");
		sql.append(" and a.xh = c.xh");
		sql.append(" and a.pjxn = c.pjxn");
		sql.append(" and a.pjxq = c.pjxq");
		sql.append(" and a.pjnd = c.pjnd");
		sql.append(" and a.xmdm = c.xmdm");
		sql.append(" and b.lcid = d.splc");
		sql.append(" and a.xtgwid = d.spgw");
		sql.append(" and b.xmmc='"+xmmc+"'");
		sql.append(" and a.xh=?");
		sql.append(" and b.pjxn=?");

		sql.append(")");

		return dao.getList(sql.toString(), new String[]{xh,xn}, new String[] { "xh", "shyj",
				"shjb","sqly" });
	}
	/**
	 * 学生本次评奖获奖情况
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> setXshjqkInfo(String xh, String xn) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql="select kzzd1,nvl(kzzd2,0)kzzd2,nvl(kzzd3,0)kzzd3,nvl(kzzd4,0)kzzd4 from xg_pjpy_pjxmsqb a where xh=? and pjxn=? and exists(select 1 from (select xmdm from xg_pjpy_pjxmwhb where xmmc like '%国家奖学金%') b where a.xmdm=b.xmdm )";
		HashMap<String, String> map = dao.getMap(sql,new String[]{xh,xn},new String[]{"kzzd1","kzzd2","kzzd3","kzzd4"});
		return map;
	}
	/**
	 * 学生历史评奖获奖情况
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> setXslshjqkInfo(String xh, String xn) {
		// TODO 自动生成方法存根
		DAO dao = DAO.getInstance();
		String sql="select kzzd1,nvl(kzzd2,0)kzzd2,nvl(kzzd3,0)kzzd3,nvl(kzzd4,0)kzzd4 from xg_pjpy_pjxmsqb_backup a where xh=? and pjxn=? and exists(select 1 from (select xmdm from xg_pjpy_pjxmwhb where xmmc like '%国家奖学金%') b where a.xmdm=b.xmdm )";
		HashMap<String, String> map = dao.getMap(sql,new String[]{xh,xn},new String[]{"kzzd1","kzzd2","kzzd3","kzzd4"});
		return map;
	}
}
