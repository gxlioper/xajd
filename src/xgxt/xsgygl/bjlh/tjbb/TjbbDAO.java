package xgxt.xsgygl.bjlh.tjbb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.MakeQuery;

public class TjbbDAO{

	/**
	 * 获得学院名称
	 * 
	 * @throws Exception
	 */
	public String getXymc(String xydm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xymc from view_njxyzybj where xydm = ? order by xydm",
				new String[] { xydm }, "xymc");
	}
	
	/**
	 * 获得毕业年度列表
	 * 
	 * @throws Exception
	 */
	public List<HashMap<String,String>> getByndList() {
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		String nd = Base.currNd;
		for (int i = 0; i < 3; i++) {
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("dm", String.valueOf(Integer.parseInt(nd)+i));
			map.put("mc", String.valueOf(Integer.parseInt(nd)+i));
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 学院评奖评优名单汇总表
	 */
	public List<HashMap<String, String>> getSsqkList(String lddm) {
	
		DAO dao = DAO.getInstance();

		lddm = Base.isNull(lddm) ? "%" : lddm;
		
		StringBuffer sql = new StringBuffer();
		
//		sql.append("select lddm, ldmc, fjs, zcws, nvl(sum(cws),'0') dfpcws ");
//		sql.append("from (select a.*, b.cws, b.xydm ");
//		sql.append("from (select lddm, ldmc, count(qsh) fjs, sum(cws) zcws ");
//		sql.append("from view_ssxx where lddm like ? group by lddm, ldmc) a ");
//		sql.append("left join ssfpb b on b.ssbh like a.lddm || '%') ");
//		sql.append("group by lddm, ldmc, fjs, zcws order by lddm");
//		sql.append(") union select * from (select ''lddm, '总计' ldmc, sum(fjs) fjs, ");
//		sql.append("sum(zcws) zcws, sum(nvl(sum(cws), '0')) dfpcws from (select ");
//		sql.append("a.*, b.cws, b.xydm from (select lddm, ldmc, count(qsh) fjs, ");
//		sql.append("sum(cws) zcws from view_ssxx where lddm like '%' group by lddm, ldmc) a ");
//		sql.append("left join ssfpb b on b.ssbh like a.lddm || '%') ");
//		sql.append("group by lddm, ldmc,fjs,zcws  order by  lddm ) ");
		
		sql.append("select a.lddm, a.ldmc, a.fjs, a.zcws, (a.dfpcws -nvl(d.zcw,0)) dfpcws ");
		sql.append("from (select lddm, ldmc, fjs, zcws, nvl(zcws, '0') dfpcws ");
		sql.append("from (select a.*, b.cws, b.xydm ");
		sql.append("from (select lddm, ldmc, count(qsh) fjs, sum(cws) zcws from view_ssxx ");
		sql.append("where lddm like ? group by lddm, ldmc) a ");
		sql.append("left join ssfpb b on b.ssbh like a.lddm || '%') a ");
		sql.append("group by lddm, ldmc, fjs, zcws) a ");
		sql.append("left join (select lddm, count(xh) num from view_xszsxx group by lddm) c on c.lddm = a.lddm ");
		
		sql.append("left join (select lddm, sum(cc) zcw from (select a.ssbh, xydm, sum(a.cws) cc ");
		sql.append("from ssfpb a,ssxxb b where a.ssbh=b.ssbh group by a.ssbh, xydm union select a.ssbh, a.xydm, a.cc ");
		sql.append("from (select ssbh, xydm, count(xh) cc from view_xszsxx a ");
		sql.append("where not exists (select 1 from bjlh_twxsb b where a.xh = b.xh) ");
		sql.append("and not exists (select 1 from bjlh_tyjxsb c where a.xh = c.xh) ");
		sql.append("group by xydm, ssbh) a where not exists (select 1 from ssfpb b ");
		sql.append("where a.xydm || a.ssbh = b.xydm || b.ssbh group by xydm, ssbh)) a ");
		sql.append("left join ssxxb b on a.ssbh = b.ssbh group by lddm) d on a.lddm=d.lddm order by a.lddm ");
//		System.out.println(sql);
		return dao.getList(sql.toString(), new String[] {lddm}, new String[] { "lddm","ldmc","fjs","zcws","dfpcws"});
	}
		
	/**
	 * 获得学院房间情况
	 */
	public List<HashMap<String, String>> getFjqk(String xydm,String lddm) {
	
		DAO dao = DAO.getInstance();		
		lddm = Base.isNull(lddm) ? "%" : lddm;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		StringBuffer sql = new StringBuffer();
//		sql.append("select lddm, xydm, yycws, xlcws, nvl(sum(cws), 0) zcws ,(nvl(sum(cws), 0) - yycws)dfcws ");
//		sql.append("from (select a.lddm, '"+xydm+"' xydm, nvl(b.yycws, 0) yycws, ");
//		sql.append("nvl(c.xlcws, 0) xlcws, d.cws, nvl(e.zcws, 0) zcws ");
//		sql.append("from (select distinct (lddm), ldmc from view_ssxx where lddm like '"+lddm+"') a ");
//		sql.append("left join (select xydm, lddm, count(xh) yycws ");
//		sql.append("from view_xszsxx where xydm = '"+xydm+"' and lddm like '"+lddm+"' ");
//		sql.append("group by xydm, lddm) b on a.lddm = b.lddm ");
//		sql.append("left join (select lddm, sum(a.xlcws) xlcws ");
//		sql.append("from ssxxb a where exists (select 1 from view_xszsxx b where ");
//		sql.append("a.ssbh = b.ssbh) and lddm like '"+lddm+"' group by lddm) c on a.lddm = c.lddm ");
//		sql.append("left join (select a.ssbh, a.cws from ssfpb a ");
//		sql.append("where a.xydm = '"+xydm+"' and a.ssbh like '"+lddm+"' ) d on d.ssbh like a.lddm || '-%' ");
//		sql.append("left join (select lddm, sum(cws) zcws from view_ssxx a ");
//		sql.append("where exists (select 1 from view_xszsxx b where a.ssbh = b.ssbh ");
//		sql.append("and b.xydm = '"+xydm+"') and lddm like '"+lddm+"' group by lddm) e on a.lddm = e.lddm) ");
//		sql.append("group by lddm, xydm, yycws, xlcws, zcws order by lddm ");
		sql.append("select xydm,xymc,lddm,ldmc,yzrs,xlcws,(dfpcw-xlcws) dfpcw, (zcw+xlcws)zcw from ( ");
		sql.append("select a.xydm,a.xymc,a.lddm,a.ldmc,nvl(b.yzrs,0)yzrs,nvl(c.dfpcw,0)dfpcw, ");
		sql.append("nvl(d.zcw,0)zcw,nvl(d.xlcws,0)xlcws from( select  distinct  b.xydm,b.xymc,a.lddm,a.ldmc  ");
		sql.append("from view_ssxx a,(select distinct xydm,xymc from view_njxyzybj) b) a left join "); 
		sql.append("(select count(xh) yzrs, lddm, xydm from view_xszsxx ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by lddm, xydm)b on a.xydm=b.xydm and a.lddm=b.lddm left join (select xydm,lddm,sum(cws-yzrs)  ");
		sql.append("dfpcw from (select a.cws,a.lddm,a.xydm,nvl(b.yzrs,0) yzrs from  ");
		sql.append("(select a.cws, b.lddm,a.ssbh, a.xydm from ssfpb a, ssxxb b ");
		sql.append("where a.ssbh = b.ssbh) a left join(select count(xh) yzrs,ssbh, xydm from view_xszsxx  ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by ssbh,xydm) b on a.ssbh = b.ssbh and a.xydm=b.xydm ) ");
		sql.append("group by xydm,lddm )c on a.xydm=c.xydm and a.lddm=c.lddm ");
		sql.append("left join (select xydm, lddm, sum(cc) zcw,sum(xlcws)xlcws from (select a.ssbh,xydm, sum(a.cws) cc ");
		sql.append("from ssfpb a,ssxxb b where a.ssbh=b.ssbh group by a.ssbh, xydm union select a.ssbh, a.xydm, a.cc ");
		sql.append("from (select ssbh, xydm, count(xh) cc from view_xszsxx  ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by xydm, ssbh) a where not exists (select 1 from ssfpb b where a.xydm || a.ssbh = b.xydm || b.ssbh ");
		sql.append("group by xydm, ssbh)) a left join ssxxb b on a.ssbh = b.ssbh ");
		sql.append("group by xydm,lddm )d on a.xydm=d.xydm and a.lddm=d.lddm order by xydm,lddm) where lddm like ? and xydm like ?");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{lddm,xydm},  new String[]{"xydm","xymc","lddm","ldmc","yzrs","xlcws","dfpcw","zcw"});
	}
	
	/**
	 * 可分配床位数统计表
	 */
	public List<HashMap<String, String>> getCwfp(String xydm,String lddm,String bynd) {
	
		DAO dao = DAO.getInstance();
		
		lddm = Base.isNull(lddm) ? "%" : lddm;
		xydm = Base.isNull(xydm) ? "%" : xydm;
		bynd = Base.isNull(bynd) ? "":bynd + "%";
		StringBuffer sql = new StringBuffer();
//		sql.append("select lddm,ldmc,(dfpcws + num) cws from (select a.lddm,a.ldmc, ");
//		sql.append("nvl(sum(b.cws), 0)dfpcws,nvl(c.num, 0) num from (select distinct  ");
//		sql.append("(lddm), ldmc from view_ssxx where lddm like '"+lddm+"') a ");
//
//		sql.append("left join (select xydm, ssbh,cws from ssfpb b ");
//		sql.append("where xydm = '"+xydm+"' and exists(select 1 from ssxxb c where c.lddm like '"+lddm+"' and c.ssbh=b.ssbh  )) b "); 
//		sql.append("on b.ssbh like a.lddm || '-%' ");
//
//		sql.append("left join (select a.xydm,a.lddm, count(a.xh) num from view_xszsxx a ");
//		sql.append("where exists (select 1 from xsxxb b where a.xh = b.xh and b.sfyby = '是') ");
//		sql.append("and a.xydm = '"+xydm+"' and a.lddm like '"+lddm+"' group by xydm,lddm) c "); 
//		sql.append("on b.xydm = c.xydm and a.lddm=c.lddm group by a.lddm, a.ldmc, c.num) order by lddm ");
//		sql.append(" select a.lddm,a.ldmc,nvl(a.cws-b.yzcws,0) cws from (select lddm, ldmc, (dfpcws + num) cws ");
//		sql.append(" from (select a.lddm, a.ldmc, nvl(sum(b.cws), 0) dfpcws, nvl(c.num, 0) num ");
//		sql.append(" from (select distinct (lddm), ldmc from view_ssxx where lddm like '"+lddm+"') a ");
//		sql.append(" left join (select xydm, ssbh, cws from ssfpb b where xydm = '"+xydm+"' and exists (select 1 ");
//		sql.append(" from ssxxb c where c.lddm like '"+lddm+"' and c.ssbh = b.ssbh)) b on b.ssbh like ");
//		sql.append(" a.lddm || '-%' left join (select a.xydm, a .lddm, count(a.xh) num from view_xszsxx a ");
//		sql.append(" where exists (select 1 from xsxxb b where a.xh = b.xh and b.sfbys = '是') ");
//		sql.append(" and a.xydm = '"+xydm+"'  and a.lddm like '"+lddm+"' group by xydm, lddm) c on b.xydm = c.xydm ");
//		sql.append(" and a.lddm = c.lddm  group by a.lddm, a.ldmc, c.num) ) a left join ( ");
//		sql.append(" select lddm,count(xh)yzcws from view_xszsxx where lddm like '"+lddm+"' and xydm = '"+xydm+"' group by lddm  ");
//		sql.append(" ) b on a.lddm=b.lddm order by a.lddm ");
		sql.append("select * from (select a.lddm,a.xydm,nvl(b.num,0) num,a.yzrs,a.xlcws,(a.dfpcw-a.xlcws) dfpcw, (a.zcw+a.xlcws)zcw from (  ");
		sql.append("select a.xydm,a.xymc,a.lddm,a.ldmc,nvl(b.yzrs,0)yzrs,nvl(c.dfpcw,0)dfpcw,  ");
		sql.append("nvl(d.zcw,0)zcw,nvl(d.xlcws,0)xlcws from( select  distinct  b.xydm,b.xymc,a.lddm,a.ldmc   ");
		sql.append("from view_ssxx a,(select distinct xydm,xymc from view_njxyzybj) b) a left join   ");
		sql.append("(select count(xh) yzrs, lddm, xydm from view_xszsxx   ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by lddm, xydm)b on a.xydm=b.xydm and a.lddm=b.lddm left join (select xydm,lddm,sum(cws-yzrs)   ");
		sql.append("dfpcw from (select a.cws,a.lddm,a.xydm,nvl(b.yzrs,0) yzrs from   ");
		sql.append("(select a.cws, b.lddm,a.ssbh, a.xydm from ssfpb a, ssxxb b  ");
		sql.append("where a.ssbh = b.ssbh) a left join(select count(xh) yzrs,ssbh, xydm from view_xszsxx   ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by ssbh,xydm) b on a.ssbh = b.ssbh and a.xydm=b.xydm )  ");
		sql.append("group by xydm,lddm )c on a.xydm=c.xydm and a.lddm=c.lddm  ");
		sql.append("left join (select xydm, lddm, sum(cc) zcw,sum(xlcws)xlcws from (select a.ssbh,xydm, sum(a.cws) cc  ");
		sql.append("from ssfpb a,ssxxb b where a.ssbh=b.ssbh group by a.ssbh, xydm union select a.ssbh, a.xydm, a.cc  ");
		sql.append("from (select ssbh, xydm, count(xh) cc from view_xszsxx  ");
		sql.append("a where not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append("group by xydm, ssbh) a  where not exists (select 1 from ssfpb b where a.xydm || a.ssbh = b.xydm || b.ssbh  ");
		sql.append("group by xydm, ssbh)) a left join ssxxb b on a.ssbh = b.ssbh  ");
		sql.append("group by xydm,lddm )d on a.xydm=d.xydm and a.lddm=d.lddm order by xydm,lddm) a  ");
		sql.append("left join (select a.xydm,a.lddm,count(a.xh) num from view_xszsxx a where ");
		sql.append("not exists(select 1 from bjlh_twxsb b where a.xh=b.xh) and not exists(select 1 from bjlh_tyjxsb c where a.xh=c.xh) ");
		sql.append(" and exists(select 1 from view_xsxxb b where a.xh=b.xh and b.byny like ? )  ");
		sql.append("group by a.xydm,a.lddm ) b on a.xydm=b.xydm and a.lddm=b.lddm) where lddm like ? and xydm like ?");
		//System.out.println(sql);
		return dao.getList(sql.toString(), new String[]{bynd,lddm,xydm},  new String[]{"xydm","lddm","yzrs","xlcws","dfpcw","zcw","num"});
	}
	
	/**
	 * 查询各楼栋各学院房间数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryLdxyfjsxx(TjbbModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String[] queryList = new String[] {"lddm" };
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xydm","xymc","lddm","ldmc","yzrs","xlcws","dfpcw","zcw"};
		
		String[] xyArr = model.getXydmArr();
		String xydmWhereSql = appendXydmWhereSql(xyArr);
		
		StringBuffer sql = new StringBuffer("select lddm,(select ldmc from sslddmb b where a.lddm=b.lddm) ldmc,xydm,xymc,nvl(xyrs,0) yzrs,nvl(xlcws,0) xlcws,nvl(kcws,0) dfpcw,nvl(xyzs,0) zcw from (select a.lddm,a.xydm, b.xyrs, b.xlcws, b.kcws, b.xyzs,a.xymc from (select a.lddm, c.xydm,xymc")
		                           .append(" from sslddmb a,(select xydm, xymc from (select distinct xydm, xymc from view_njxyzybj union")
		                           .append(" select '0202' xydm, '团委' xymc from dual union select '0405' xydm, '体育教学部' xymc from dual")
		                           .append(" union select '0110' xydm, '科研处' xymc from dual union select '0117' xydm, '成人教育处' xymc from dual)) c")
		                           .append(" ) a left join (select lddm, xydm, xyrs, xlcws, kcws, (xyrs + xlcws + kcws) xyzs from (select xydm,lddm,")
		                           .append(" sum(bxyyzrs) xyrs,sum(xlcws) xlcws,sum(kcws) kcws from view_bjlh_tjjbxx")
		                           .append(" group by lddm, xydm)) b on a.lddm=b.lddm and b.xydm=a.xydm) a")
		                           .append(queryObject.getQueryString())
		                           .append(xydmWhereSql)
		                           .append(" order by xydm,lddm");
		
		return dao.getList(sql.toString(), queryObject.getInputList(), colList);
	}

	private String appendXydmWhereSql(String[] xyArr) {
		StringBuffer xydmWhereSql = new StringBuffer(" and xydm in ('");
		for (int i=0;i<xyArr.length;i++) {
			xydmWhereSql.append(xyArr[i]);
			if (i == xyArr.length - 1) {
				xydmWhereSql.append("')");
			} else {
				xydmWhereSql.append("','");
			}
		}
		return xydmWhereSql.toString();
	}
	
	/**
	 * 查询楼栋房间数信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryLdfjsxx(TjbbModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String[] queryList = new String[] {"lddm"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "lddm","ldmc","fjs","zcws","dfpcws"};
		StringBuffer sql = new StringBuffer("select * from (select a.lddm,a.ldmc,nvl(b.fjzs,0) fjs,nvl(b.cws,0) zcws,(nvl(b.cws,0) - nvl(c.yfpcws,0) - nvl(d.yzrs,0)) dfpcws from sslddmb a left join (select lddm,count(qsh) fjzs,")
		                           .append(" sum(nvl(cws,0)) cws from bjlh_ssxxb group by lddm) b on a.lddm=b.lddm left join ")
		                           .append(" (select lddm,sum(nvl(cws,0)) yfpcws from (select a.xydm,a.ssbh,a.cws,b.lddm from ssfpb a,bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) group by lddm) c on a.lddm=c.lddm")
		                           .append(" left join (select a.lddm,count(a.xh) yzrs from bjlh_xszsxxb a where zzbj='yes' and not exists (select 1 from ssfpb b where a.lddm||a.cs||a.qsh=b.ssbh) group by a.lddm) d on a.lddm=d.lddm)")
		                           .append(queryObject.getQueryString())
                                   .append(" order by lddm");
		return dao.getList(sql.toString(), queryObject.getInputList(), colList);
	}
	
	public List<HashMap<String, String>> getXymcListByxydm(String[] xyArr) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("select xydm,xymc from (select distinct xydm,xymc from view_njxyzybj ")
		                           .append("union select '0202' xydm,'团委' xymc from dual ")
				                   .append("union select '0405' xydm,'体育教学部' xymc from dual ")
				                   .append("union select '0110' xydm,'科研处' xymc from dual ")
				                   .append("union select '0117' xydm,'成人教育处' xymc from dual) where 1=1 ");
		String whereSql = appendXydmWhereSql(xyArr);
		return dao.getList(sql.toString() + whereSql + " order by xydm", new String[]{}, new String[]{"xydm", "xymc"});
	}
	
	/**
	 * 查询楼栋可分配床位信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryLdkfpcwxx(TjbbModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String[] queryList = new String[] {"lddm"};
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] { "lddm","ldmc","fjs","zcws","dfpcws"};
		StringBuffer sql = new StringBuffer("select * from (select a.lddm,a.ldmc,nvl(b.fjzs,0) fjs,nvl(b.cws,0) zcws,(nvl(b.cws,0) - nvl(c.yfpcws,0) - nvl(d.yzrs,0)) dfpcws from sslddmb a left join (select lddm,count(qsh) fjzs,")
                                  .append(" sum(nvl(cws,0)) cws from bjlh_ssxxb group by lddm) b on a.lddm=b.lddm left join ")
                                  .append(" (select lddm,sum(nvl(cws,0)) yfpcws from (select a.xydm,a.ssbh,a.cws,b.lddm from ssfpb a,bjlh_ssxxb b where a.ssbh=b.lddm||b.cs||b.qsh) group by lddm) c on a.lddm=c.lddm")
                                  .append(" left join (select a.lddm,count(a.xh) yzrs from bjlh_xszsxxb a where zzbj='yes' and not exists (select 1 from ssfpb b where a.lddm||a.cs||a.qsh=b.ssbh) group by a.lddm) d on a.lddm=d.lddm)")
                                  .append(queryObject.getQueryString())
                                  .append(" order by lddm");
		return dao.getList(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询各楼栋各学院床位数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> queryLdxydfpfjsxx(TjbbModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String[] queryList = new String[] {"lddm" };
		String[] likeList = null;
		MakeQuery queryObject = new MakeQuery();
		queryObject.makeQuery(queryList, likeList, model);
		String[] colList = new String[] {"xydm","lddm","dfpcw","bysrs"};
		
		String[] xyArr = model.getXydmArr();
		String xydmWhereSql = appendXydmWhereSql(xyArr);
		
		StringBuffer sql = new StringBuffer("select lddm,xydm,xymc,nvl(xyrs,0) yzrs,nvl(xlcws,0) xlcws,nvl(kcws,0) dfpcw,nvl(xyzs,0) zcw,0 bysrs from (select a.lddm,a.xydm, b.xyrs, b.xlcws, b.kcws, b.xyzs,a.xymc from (select a.lddm, c.xydm,xymc")
		                           .append(" from sslddmb a,(select xydm, xymc from (select distinct xydm, xymc from view_njxyzybj union")
		                           .append(" select '0202' xydm, '团委' xymc from dual union select '0405' xydm, '体育教学部' xymc from dual")
		                           .append(" union select '0110' xydm, '科研处' xymc from dual union select '0117' xydm, '成人教育处' xymc from dual)) c")
		                           .append(" ) a left join (select lddm, xydm, xyrs, xlcws, kcws, (xyrs + xlcws + kcws) xyzs from (select xydm,lddm,")
		                           .append(" sum(bxyyzrs) xyrs,sum(xlcws) xlcws,sum(kcws) kcws from view_bjlh_tjjbxx")
		                           .append(" group by lddm, xydm)) b on a.lddm=b.lddm and b.xydm=a.xydm) a")
		                           .append(queryObject.getQueryString())
		                           .append(xydmWhereSql)
		                           .append(" order by xydm,lddm");
		
		return dao.getList(sql.toString(), queryObject.getInputList(), colList);
	}
	
	/**
	 * 查询毕业生床位信息
	 * @param byny
	 * @return
	 */
	public List<HashMap<String, String>> queryBysfjsxx(String byny) {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer("select nvl(count(a.xh),0) bys,a.lddm,b.xydm from bjlh_xszsxxb a,(select (case when b.lx='团委生' then '0202' when b.lx='研究生' then '0110' when b.lx='体优生' then '0405' when b.lx='成教生' then '0117' else b.xydm end) xydm,xh,lx,byny from view_bjlh_xsxx b) b where a.xh=b.xh and a.lx=b.lx")
							       .append(" and zzbj='yes' and b.byny like ? group by a.lddm,b.xydm order by xydm,lddm");
		String[] colList = new String[]{"xydm","lddm", "bys"};
		return dao.getList(sql.toString(), new String[]{byny + "%"}, colList);
	}
}
