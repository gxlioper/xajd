package xsgzgl.pjpy.gzdx.djbg;

import java.util.HashMap;

import xgxt.DAO.DAO;

public class PjpyDjbgDAO extends xsgzgl.pjpy.general.djbg.PjpyDjbgDAO{
	
	/**
	 * 登记表相关数据
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getXsxxInfo(String xh, String xn) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.xh, a.xm, b.csrq,  ");
		sql.append("a.nj, a.xymc, a.zymc, a.bjmc,f.bjgbmc zw,g.pjf,  ");
		sql.append("decode(b.xb,'1','男','2','女',b.xb) xb,b.xz,  ");
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
				xh, xn, xh, xh }, new String[] { "xh", "xm", "xb","xz", "csrq",
				"nj", "xymc", "zymc", "bjmc", "zzmmmc", "mzmc", "pjf", "zw","bjrs" });

		return map;
	}
	
	
	
	/**
	 * 在校期间是否受过处分 
	 * @param xh
	 * @return
	 */
	public String getSfcf(String xh){
		
		String sql = "select count(1) num from wjcfb where xh = ?";
		
		return DAO.getInstance().getOneRs(sql, new String[]{xh}, "num");
	}
	
	
	/**
	 * 综测成绩
	 * @param xh
	 * @param xn
	 * @return
	 */
	public HashMap<String, String> getZccjInfo(String xh, String xn) {
		
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
}
