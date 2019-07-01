/*
 * @Title: ѧ������������Ϣϵͳ
 * 
 * @ClassName: YlxfbDAO.java
 * 
 * @time: 2010-06-04 
 * 
 * @copyright: hz-zfsoft 
 */
package xgxt.pjpy.zjlg.ylxfb;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

/**
 * @desc �㽭�� - �������� - ����ѧ��༶����ά��DAO
 *                          ��������ɾ���ģ��飬��˵Ȳ���
 * @innerClass DAO.java
 * @author lt
 * @version 1.0 2010-06-04
 */
public class YlxfbDAO {

	DAO dao = DAO.getInstance();
	
	private final String QUERY_KSKCKFSSZ_SQL = "select szlx,tjlx,tjz,tjzd from zjlg_pjpy_tjsz where szlx='ylxfb' and xn=?";
	
	StringBuilder QUERY_PM_SQL = new StringBuilder("select k.bjdm,a.kcyxs,a.kcjgs,a.kckcx,b.ksyxs,b.ksjgs,b.kskcx,c.ynj,c.enj,d.wjcs,e.sszs,f.ajss,g.tsss,h.wmss from (")
	.append("select '306060101' bjdm from dual")
	.append(") k left join ")
	.append("(select a.kcyxs,a.kcjgs,a.kckcx,bjdm from (")
	.append("select (")
	.append("select count(a.xh) yxs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and cj>=80 and khfs is not null and khfs like '%����%'")
	.append(") kcyxs,(")
	.append("select count(a.xh) jgs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and cj>=60 and khfs is not null and khfs like '%����%'")
	.append(") kcjgs,(")
	.append("select count(a.xh) kcx from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and khfs is not null and khfs like '%����%'")
	.append(") kckcx,'306060101' bjdm from dual) a) a on k.bjdm = a.bjdm")
	.append(" left join ")
	.append("(")
	.append("select b.ksyxs,b.ksjgs,b.kskcx,b.bjdm from (")
	.append("select (")
	.append("select count(a.xh) yxs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and cj>=80 and khfs is not null and khfs like '%����%'")
	.append(") ksyxs,(")
	.append("select count(a.xh) jgs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and cj>=60 and khfs is not null and khfs like '%����%'")
	.append(") ksjgs,(")
	.append("select count(a.xh) kcx from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='2009-2010' and b.bjdm='306060101' and khfs is not null and khfs like '%����%'")
	.append(") kskcx,'306060101' bjdm from dual) b)b on k.bjdm=b.bjdm ")
	.append(" left join ")
	.append("(select c.ynj,c.enj,c.bjdm from (")
	.append("select (")
	.append("select avg(cj) from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='306060101' and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn='2009-2010'")
	.append(") ynj,(")
	.append("select avg(cj) from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='306060101' and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn='2009-2010'")
	.append(") enj,'306060101' bjdm from dual) c)c on k.bjdm=c.bjdm")
	.append(" left join ")
	.append("(")
	.append("select d.wjcs,d.bjdm from (")
	.append("select count(a.xh) wjcs,'306060101' bjdm from wjcfb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='306060101' and a.xn='2009-2010' and a.xxsh='ͨ��' and a.cfwh is not null and a.cfsj is not null")
	.append(") d) d on k.bjdm=d.bjdm")
	.append(" left join ")
	.append("(")
	.append("select e.sszs,e.bjdm from (")
	.append("select count(ssbh) sszs,'306060101' bjdm from (")
	.append("select distinct ssbh from view_xszsxx where bjdm='306060101'")
	.append(")) e) e on k.bjdm=e.bjdm")
	.append(" left join ")
	.append("(")
	.append("select f.ajss,f.bjdm from (")
	.append("select count(ssbh) ajss,'306060101' bjdm from ajqsb a where xn='2009-2010' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='306060101') ")
	.append(") f) f on k.bjdm=f.bjdm")
	.append(" left join ")
	.append("(")
	.append("select g.tsss,g.bjdm from (")
	.append("select count(ssbh) tsss,'306060101' bjdm from tsqsb a where xn='2009-2010' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='306060101') ")
	.append(") g) g on k.bjdm=g.bjdm")
	.append(" left join ")
	.append("(")
	.append("select h.wmss,h.bjdm from (")
	.append("select count(ssbh) wmss,'306060101' bjdm from wmqsb a where xn='2009-2010' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='306060101') ")
	.append(") h) h on k.bjdm=h.bjdm ");

	private final String QUERY_FDYXM_SQL = "select b.xm from fdybjb a,yhb b where a.zgh=b.yhm and bjdm=? and rownum<2";
	
	private final StringBuilder QUERY_EXPORTYLXFBXX_SQL = new StringBuilder("select bjdm,a.xymc,a.nj,a.bjmc,(select count(xh) from view_xsjbxx b where a.bjdm=b.bjdm) bjrs,")
			.append("cql||'%' cql,'' ksyxl,'' ksjgl,'' kcyxl,'' kcjgl,'' yej,")
			.append("sjtgl||'%' sjtgl,jsjtgl||'%' jsjtgl,bkl||'%' bkl,sxl||'%' sxl,'' aj,'' ts,'' wm,'' wjcs,bhhd,jsbjpj,pjpm,bz from view_zjlg_ylxfb a");
	
	private final String QUERY_BJDM_SQL = "select bjdm from zjlg_ylxfb a where xn=? and xysh='ͨ��' and xxsh='ͨ��' and exists (select 1 from view_njxyzybj b where xydm=? and a.bjdm=b.bjdm)";
	

	
	/**
	 * ��ѯ�༶����,����γ̷���������Ϣ
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> queryBjkskckxx(YlxfbModel model) {
		return dao.getList(QUERY_KSKCKFSSZ_SQL, new String[]{model.getXn()}, new String[]{"szlx", "tjlx", "tjz", "tjzd"});
	}
	
	/**
	 * ��ѯ�༶������Ϣ
	 * @param model
	 * @return
	 */
	public HashMap<String, String> queryPmxx(YlxfbModel model, String ynj, String enj) {
		StringBuilder QUERY_PM_SQL = new StringBuilder("select k.bjdm,a.kcyxs,a.kcjgs,a.kckcx,b.ksyxs,b.ksjgs,b.kskcx,c.ynj,c.enj,d.wjcs,e.sszs,f.ajss,g.tsss,h.wmss from (")
		.append("select '"+model.getBjdm()+"' bjdm from dual")
		.append(") k left join ")
		.append("(select a.kcyxs,a.kcjgs,a.kckcx,bjdm from (")
		.append("select (")
		.append("select count(a.xh) yxs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and cj>=80 and khfs is not null and khfs like '%����%'")
		.append(") kcyxs,(")
		.append("select count(a.xh) jgs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and cj>=60 and khfs is not null and khfs like '%����%'")
		.append(") kcjgs,(")
		.append("select count(a.xh) kcx from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and khfs is not null and khfs like '%����%'")
		.append(") kckcx,'"+model.getBjdm()+"' bjdm from dual) a) a on k.bjdm = a.bjdm")
		.append(" left join ")
		.append("(")
		.append("select b.ksyxs,b.ksjgs,b.kskcx,b.bjdm from (")
		.append("select (")
		.append("select count(a.xh) yxs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and cj>=80 and khfs is not null and khfs like '%����%'")
		.append(") ksyxs,(")
		.append("select count(a.xh) jgs from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and cj>=60 and khfs is not null and khfs like '%����%'")
		.append(") ksjgs,(")
		.append("select count(a.xh) kcx from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and b.bjdm='"+model.getBjdm()+"' and khfs is not null and khfs like '%����%'")
		.append(") kskcx,'"+model.getBjdm()+"' bjdm from dual) b)b on k.bjdm=b.bjdm ")
		.append(" left join ")
		.append("(select c.ynj,c.enj,c.bjdm from (")
		.append("select (")
		.append("select avg(cj) from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='"+model.getBjdm()+"' and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn='"+ynj+"'")
		.append(") ynj,(")
		.append("select avg(cj) from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='"+model.getBjdm()+"' and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn='"+enj+"'")
		.append(") enj,'"+model.getBjdm()+"' bjdm from dual) c)c on k.bjdm=c.bjdm")
		.append(" left join ")
		.append("(")
		.append("select d.wjcs,d.bjdm from (")
		.append("select count(a.xh) wjcs,'"+model.getBjdm()+"' bjdm from wjcfb a,view_xsjbxx b where a.xh=b.xh and b.bjdm='"+model.getBjdm()+"' and a.xn='"+model.getXn()+"' and a.xxsh='ͨ��' and a.cfwh is not null and a.cfsj is not null")
		.append(") d) d on k.bjdm=d.bjdm")
		.append(" left join ")
		.append("(")
		.append("select e.sszs,e.bjdm from (")
		.append("select count(ssbh) sszs,'"+model.getBjdm()+"' bjdm from (")
		.append("select distinct ssbh from view_xszsxx where bjdm='"+model.getBjdm()+"'")
		.append(")) e) e on k.bjdm=e.bjdm")
		.append(" left join ")
		.append("(")
		.append("select f.ajss,f.bjdm from (")
		.append("select count(ssbh) ajss,'"+model.getBjdm()+"' bjdm from ajqsb a where xn='"+model.getXn()+"' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='"+model.getBjdm()+"') ")
		.append(") f) f on k.bjdm=f.bjdm")
		.append(" left join ")
		.append("(")
		.append("select g.tsss,g.bjdm from (")
		.append("select count(ssbh) tsss,'"+model.getBjdm()+"' bjdm from tsqsb a where xn='"+model.getXn()+"' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='"+model.getBjdm()+"') ")
		.append(") g) g on k.bjdm=g.bjdm")
		.append(" left join ")
		.append("(")
		.append("select h.wmss,h.bjdm from (")
		.append("select count(ssbh) wmss,'"+model.getBjdm()+"' bjdm from wmqsb a where xn='"+model.getXn()+"' and xxsh='ͨ��' and exists (select 1 from view_xszsxx b where a.ssbh=b.ssbh and b.bjdm='"+model.getBjdm()+"') ")
		.append(") h) h on k.bjdm=h.bjdm ");
		String[] data = {
				model.getBjdm(), model.getXn(), model.getBjdm(),
				model.getKckcj(), model.getXn(), model.getBjdm(),
				model.getXn(), model.getBjdm(), model.getBjdm(), model.getXn(),
				model.getBjdm(), model.getKskcj(), model.getXn(),
				model.getBjdm(), model.getXn(), model.getBjdm(),
				model.getBjdm(), model.getBjdm(), ynj,
				model.getBjdm(), enj,model.getBjdm(),model.getBjdm(),model.getBjdm(),
				model.getXn(), model.getBjdm(), model.getBjdm(),
				model.getBjdm(), model.getXn(), model.getBjdm(),
				model.getBjdm(), model.getXn(), model.getBjdm(),
				model.getBjdm(), model.getXn(), model.getBjdm() };
		return dao.getMapNotOut(QUERY_PM_SQL.toString(), new String[]{});
		//HashMap<String, String> rs = dao.getMapNotOut(getQUERY_PM_SQL(), data);
		
	}

	public String getFdyxm(String bjdm) {
		return dao.getOneRs(QUERY_FDYXM_SQL, new String[]{bjdm}, "xm");
	}
	
	/**
	 * ��ѯ�����༶��������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryYlxfbHzData(YlxfbModel model) throws Exception {
		String[] queryList = new String[] { "xn", "xydm" };
		String[] likeList = new String[] {};
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);

		String[] colList = new String[] {"r", "xymc", "bjmc", "nj", "bjrs",
				"cql", "ksyxl", "ksjgl", "kcyxl", "kcjgl", "yej", "sjtgl",
				"jsjtgl", "bkl", "sxl", "aj", "ts", "wm", "wjcs", "bhhd",
				"jsbjpj", "pjpm", "bz" ,"bjdm"};
		return CommonQueryDAO.commonQueryNotFy("select a.*,rownum r from (" + getQUERY_EXPORTYLXFBXX_SQL(),
				queryObject.getQueryString() + " and xysh='ͨ��' and xxsh='ͨ��') a order by bjdm", queryObject.getInputList(),
				colList, model);
	}
	
	/**
	 * ��ѯѧԺ����ѧ��༶�����б���Ϣ
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String[] queryBjdmByxydm(YlxfbModel model) throws Exception{
		return dao.getArray(QUERY_BJDM_SQL, new String[]{model.getXn(), model.getXydm()}, "bjdm");
	}
	
	/**
	 * ��ѯ�༶������ص�������Ϣ
	 * @param model
	 * @param bjdmInSql
	 * @param ksfs
	 * @param kcfs
	 * @param ynj
	 * @param enj
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryBjpmList(YlxfbModel model, String bjdmInSql, String ksfs, String kcfs) throws Exception{
		StringBuilder sql = new StringBuilder("select * from (select k.bjdm,a.kcyxs,b.kcjgs,c.kckcx,d.ksyxs,e.ksjgs,f.kskcx,g.ynj,h.enj,i.wjcs,j.sszs,l.ajqs,m.tsqs,n.wmqs from (")
				.append("select bjdm from zjlg_ylxfb a where xn=? and xysh='ͨ��' and xxsh='ͨ��' and exists (select 1 from view_njxyzybj b where xydm=? and a.bjdm=b.bjdm)) k left join ")
				.append("(select count(a.xh) kcyxs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and cj>="+kcfs+" and khfs like '%����%' "+bjdmInSql+" group by bjdm) a on k.bjdm=a.bjdm ")
				.append("left join (select count(a.xh) kcjgs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and cj>=60 and khfs like '%����%' "+bjdmInSql+" group by bjdm) b on k.bjdm=b.bjdm ")
				.append("left join (select count(a.xh) kckcx,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and khfs like '%����%' "+bjdmInSql+" group by bjdm) c on k.bjdm=c.bjdm ")
				.append("left join (select count(a.xh) ksyxs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and cj>="+ksfs+" and khfs like '%����%' "+bjdmInSql+" group by bjdm) d on k.bjdm=d.bjdm ")
				.append("left join (select count(a.xh) ksjgs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and cj>=60 and khfs like '%����%' "+bjdmInSql+" group by bjdm) e on k.bjdm=e.bjdm ")
				.append("left join (select count(a.xh) kskcx,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and khfs like '%����%' "+bjdmInSql+" group by bjdm) f on k.bjdm=f.bjdm  ")
				.append("left join (select avg(cj) ynj,b.bjdm from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn=(to_number(b.nj)||'-'||(to_number(b.nj)+1)) "+bjdmInSql+" group by bjdm) g on k.bjdm=g.bjdm ")
				.append("left join (select avg(cj) enj,b.bjdm from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn=(to_number(b.nj+1)||'-'||(to_number(b.nj)+2)) "+bjdmInSql+" group by bjdm) h on k.bjdm=h.bjdm ")
				.append("left join (select count(a.xh) wjcs,b.bjdm from wjcfb a,view_xsjbxx b where a.xh=b.xh and a.xn=? and a.xxsh='ͨ��' and a.cfwh is not null and a.cfsj is not null group by bjdm) i on k.bjdm=i.bjdm ")
				.append("left join (select count(ssbh) sszs,bjdm from (select distinct ssbh,bjdm from view_xszsxx group by bjdm,ssbh) group by bjdm) j on k.bjdm=j.bjdm ")
				.append("left join (select count(ssbh) ajqs,bjdm from (")
				.append("select a.ssbh,b.bjdm from ajqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn=? and xxsh='ͨ��'")
				.append(") group by bjdm) l on k.bjdm=l.bjdm")
				.append(" left join (select count(ssbh) tsqs,bjdm from (")
				.append("select a.ssbh,b.bjdm from tsqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn=? and xxsh='ͨ��'")
				.append(") group by bjdm) m on k.bjdm=m.bjdm")
				.append(" left join (select count(ssbh) wmqs,bjdm from (")
				.append("select a.ssbh,b.bjdm from wmqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn=? and xxsh='ͨ��'")
				.append(") group by bjdm) n on k.bjdm=n.bjdm) order by bjdm");
//		StringBuilder sql = new StringBuilder("select * from (select k.bjdm,a.kcyxs,b.kcjgs,c.kckcx,d.ksyxs,e.ksjgs,f.kskcx,g.ynj,h.enj,i.wjcs,j.sszs,l.ajqs,m.tsqs,n.wmqs from (")
//		.append("select bjdm from zjlg_ylxfb a where xn='"+model.getXn()+"' and xysh='ͨ��' and xxsh='ͨ��' and exists (select 1 from view_njxyzybj b where xydm='"+model.getXn()+"' and a.bjdm=b.bjdm)) k left join ")
//		.append("(select count(a.xh) kcyxs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and cj>="+kcfs+" and khfs like '%����%' "+bjdmInSql+" group by bjdm) a on k.bjdm=a.bjdm ")
//		.append("left join (select count(a.xh) kcjgs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and cj>=60 and khfs like '%����%' "+bjdmInSql+" group by bjdm) b on k.bjdm=b.bjdm ")
//		.append("left join (select count(a.xh) kckcx,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and khfs like '%����%' "+bjdmInSql+" group by bjdm) c on k.bjdm=c.bjdm ")
//		.append("left join (select count(a.xh) ksyxs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and cj>="+ksfs+" and khfs like '%����%' "+bjdmInSql+" group by bjdm) d on k.bjdm=d.bjdm ")
//		.append("left join (select count(a.xh) ksjgs,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and cj>=60 and khfs like '%����%' "+bjdmInSql+" group by bjdm) e on k.bjdm=e.bjdm ")
//		.append("left join (select count(a.xh) kskcx,b.bjdm from cjb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and khfs like '%����%' "+bjdmInSql+" group by bjdm) f on k.bjdm=f.bjdm  ")
//		.append("left join (select avg(cj) ynj,b.bjdm from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn=(to_number(b.nj)||'-'||(to_number(b.nj)+1)) "+bjdmInSql+" group by bjdm) g on k.bjdm=g.bjdm ")
//		.append("left join (select avg(cj) enj,b.bjdm from view_zhhcjb a,view_xsjbxx b where a.xh=b.xh and kcxz like '%����%' and kcmc like '%Ӣ��%' and a.xn=(to_number(b.nj+1)||'-'||(to_number(b.nj)+2)) "+bjdmInSql+" group by bjdm) h on k.bjdm=h.bjdm ")
//		.append("left join (select count(a.xh) wjcs,b.bjdm from wjcfb a,view_xsjbxx b where a.xh=b.xh and a.xn='"+model.getXn()+"' and a.xxsh='ͨ��' and a.cfwh is not null and a.cfsj is not null group by bjdm) i on k.bjdm=i.bjdm ")
//		.append("left join (select count(ssbh) sszs,bjdm from (select distinct ssbh,bjdm from view_xszsxx group by bjdm,ssbh) group by bjdm) j on k.bjdm=j.bjdm ")
//		.append("left join (select count(ssbh) ajqs,bjdm from (")
//		.append("select a.ssbh,b.bjdm from ajqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn='"+model.getXn()+"' and xxsh='ͨ��'")
//		.append(") group by bjdm) l on k.bjdm=l.bjdm")
//		.append(" left join (select count(ssbh) tsqs,bjdm from (")
//		.append("select a.ssbh,b.bjdm from tsqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn='"+model.getXn()+"' and xxsh='ͨ��'")
//		.append(") group by bjdm) m on k.bjdm=m.bjdm")
//		.append(" left join (select count(ssbh) wmqs,bjdm from (")
//		.append("select a.ssbh,b.bjdm from wmqsb a,(select distinct ssbh,bjdm from view_xszsxx b where 1=1 "+bjdmInSql+" group by ssbh,bjdm) b where a.ssbh=b.ssbh and xn='"+model.getXn()+"' and xxsh='ͨ��'")
//		.append(") group by bjdm) n on k.bjdm=n.bjdm) order by bjdm");
		
		return dao.getList(sql.toString(), new String[] {model.getXn(),
			model.getXydm(), model.getXn(), model.getXn(), model.getXn(),
			model.getXn(), model.getXn(), model.getXn(), model.getXn(), model.getXn(),
			model.getXn(),model.getXn()}, new String[] { "bjdm", "kcyxs", "kcjgs",
				"kckcx", "ksyxs", "ksjgs", "kskcx", "ynj", "enj", "wjcs",
				"sszs", "ajqs", "tsqs", "wmqs" });
	}
	
	public String getQUERY_PM_SQL() {
		return QUERY_PM_SQL.toString();
	}

	public String getQUERY_EXPORTYLXFBXX_SQL() {
		return QUERY_EXPORTYLXFBXX_SQL.toString();
	}
	
	
}
