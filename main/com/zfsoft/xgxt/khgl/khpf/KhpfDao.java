/**
 * @部门:学工产品事业部
 * @日期：2015-8-12 下午02:41:05 
 */  
package com.zfsoft.xgxt.khgl.khpf;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import common.Globals;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核评分
 * @类功能描述: dao 
 * @作者：cq [工号:785]
 * @时间： 2015-8-12 下午02:41:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class KhpfDao extends SuperDAOImpl<KhpfForm> {

	private static final String PFLX_XSZP = "xszp";
	private static final String PFLX_BZPF = "bzpf";
	private static final String PFLX_BZRPF = "bzrpf";

	@Override
	public List<HashMap<String, String>> getPageList(KhpfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	
	@Override
	public List<HashMap<String, String>> getPageList(KhpfForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	};

	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setClass(KhpfForm.class);
		super.setKey("pfzid");
		super.setTableName("xg_khgl_pfz");
	}
	
	/**
	 * 
	 * @描述:教师查询
	 * @作者：cq [工号：785]
	 * @日期：2015-8-12 下午04:10:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJsPageList(KhpfForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		HashMap<String, String> szInfo = getSzInfo(user);
		
		//不要问哥为什么这样做，一切都为了效率
		String khdxid = "";
		if(!"0".equals(szInfo.get("bzr"))){
			khdxid+=KhpfService.JS_XS_BZR+"','"+KhpfService.JS_XS_BZRFDY+"','";
		}
		if(!"0".equals(szInfo.get("fdy"))){
			khdxid+=KhpfService.JS_XS_FDY+"','"+KhpfService.JS_XS_BZRFDY+"','";
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select t1.*,t2.ypcount,case when t1.dpcount-t2.ypcount=0 then '是' else '否' end pfzt from ( ");
		sql.append("select xmid,khbid,khbmc,khlx,xmmc,kssj,case when to_date(kssj, 'yyyy-mm-dd hh24:mi:ss')  > sysdate then '2' when to_date(jssj,'yyyy-mm-dd hh24:mi:ss')<sysdate then '1' else '0' end sfjs,jssj,khdxmc,sum(dpcount) dpcount from ( ");
		sql.append("select a.xmid,b.khbid,g.khbmc,f.khlx,a.xmmc,a.kssj,a.jssj,f.khdxmc, ");
		sql.append("case when c.pflx='"+KhpfService.YHLX_BR+"' then 1 else (nvl(d.count,0)+nvl(e.count,0)+nvl(h.count,0)) end dpcount ");
		sql.append("from xg_khgl_khxm a left join xg_khgl_khxm_sz b on a.xmid=b.xmid left join xg_khgl_pfz c on b.pfzid=c.pfzid ");
		sql.append("left join (select a.pfzid,count(1) count from xg_khgl_pfz a ");
		sql.append("left join xg_khgl_pfz_js b on a.pfzid=b.pfzid where b.pfzgh= ? group by a.pfzid) d on c.pfzid=d.pfzid ");
		sql.append("left join xg_khgl_khdx f on a.khdxid=f.khdxid ");
		sql.append("left join ("+getTabSql(user)+") e on b.sjfwdm=e.dm and c.sfnz='"+KhpfService.SFNZ_Y+"' and f.khlx='"+KhpfService.YHLX_XS+"' ");
		sql.append("left join xg_khgl_khb g on b.khbid=g.khbid ");
		sql.append("left join (select '"+KhpfService.JS_JS_QX+"' dm,count(1) count from fdyxxb b where zgh <> ? union ");
		sql.append("select '"+KhpfService.JS_JS_BBM+"' dm, count(case when bmdm = ? then 1 else null end) count ");
		sql.append("from fdyxxb b where zgh <> ? ) h on b.sjfwdm=h.dm and c.sfnz='"+KhpfService.SFNZ_Y+"' and f.khlx='"+KhpfService.YHLX_JS+"' ");
		sql.append("where c.pflx='"+KhpfService.YHLX_JS+"' or (c.pflx='"+KhpfService.YHLX_BR+"' ");
		sql.append("and (a.khdxid in ('"+khdxid+"') or a.khdxid in (select khdxid from xg_khgl_khdx_js where khzgh= ? )))) ");
		sql.append("group by xmid,khbid,khbmc,khlx,xmmc,kssj,jssj,khdxmc ) t1 ");
		sql.append("left join (select xmid,khbid,count(1) ypcount from (select xmid,khbid, ");
		sql.append("substr(t.xmszid,instr(t.xmszid, ',', 1, c.lv) + 1,instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select xmid,khbid,',' || xmszid || ',' AS xmszid,length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select xmid,khbid, xmszid from xg_khgl_khpf where sftj='"+KhpfService.SFTJ_Y+"' and pfr= ?)) t, ");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 30) c ");
		sql.append(" where c.lv <= t.cnt) group by xmid,khbid) t2 on t1.xmid=t2.xmid and t1.khbid=t2.khbid where t1.dpcount > 0 ) where 1=1 ");
		// 浙江商业技师 过滤未开始与已结束
		if("33333".equals(Base.xxdm)){
			sql.append(" and sfjs = '0' ");
		}
		sql.append(searchTj);
		
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
				user.getUserName(),user.getUserName(),user.getUserDep(),
				user.getUserName(),user.getUserName(),user.getUserName()
				}, inputV);
		
		return getPageList(t, sql.toString(), both);
	}
	
	
	/**
	 * 
	 * @描述:学生查询
	 * @作者：cq [工号：785]
	 * @日期：2015-8-12 下午02:57:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	
	public List<HashMap<String, String>> getXsPageList(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select t1.*,t2.ypcount,case when t1.dpcount-t2.ypcount=0 then '是' else '否' end pfzt from ( ");
		sql.append("select xmid,khbid,khbmc,khlx,xmmc,kssj,case when to_date(kssj, 'yyyy-mm-dd hh24:mi:ss')  > sysdate then '2' when to_date(jssj,'yyyy-mm-dd hh24:mi:ss')<sysdate then '1' else '0' end sfjs,jssj,khdxmc,sum(dpcount) dpcount from ( ");
		sql.append("select a.xmid,b.khbid,g.khbmc,f.khlx,a.xmmc,a.kssj,a.jssj,f.khdxmc, ");
		sql.append("case when c.pflx='"+KhpfService.YHLX_BR+"' then 1 else (nvl(d.count,0)+nvl(e.count,0)+nvl(h.count,0)) end dpcount ");
		sql.append("from xg_khgl_khxm a left join xg_khgl_khxm_sz b on a.xmid=b.xmid left join xg_khgl_pfz c on b.pfzid=c.pfzid ");
		sql.append("left join (select a.pfzid,count(1) count from xg_khgl_pfz a ");
		sql.append("left join xg_khgl_pfz_xs b on a.pfzid=b.pfzid where b.pfzxh= ? group by a.pfzid) d on c.pfzid=d.pfzid ");
		sql.append("left join xg_khgl_khdx f on a.khdxid=f.khdxid ");
		sql.append("left join (select '"+KhpfService.XS_XS_BB+"' dm,count(1) count from view_xsbfxx where bjdm in (select bjdm from xsxxb where xh = ? ) ");
		sql.append("and xh <> ? ) e on b.sjfwdm=e.dm and c.sfnz='"+KhpfService.SFNZ_Y+"' and f.khlx='"+KhpfService.YHLX_XS+"' ");
		sql.append("left join xg_khgl_khb g on b.khbid=g.khbid ");
		sql.append("left join (select '"+KhpfService.XS_JS_BZR+"' dm,count(1) count from bzrbbb where zgh in (select zgh from fdyxxb) and bjdm in (select bjdm from xsxxb where xh = ?) union ");
		sql.append("select '"+KhpfService.XS_JS_FDY+"' dm,count(1) count from fdybjb where zgh in (select zgh from fdyxxb) and bjdm in (select bjdm from xsxxb where xh = ?) union ");
		sql.append("select '"+KhpfService.XS_JS_BZRFDY+"',sum(count) count from( select count(1) count from bzrbbb where zgh in (select zgh from fdyxxb) and bjdm in (select bjdm from xsxxb where xh = ?) union ");
		sql.append("select count(1) count from fdybjb where zgh in (select zgh from fdyxxb) and bjdm in (select bjdm from xsxxb where xh = ?)) union ");
		sql.append("select '"+KhpfService.XS_JS_BYX+"' dm,count(1) from fdyxxb where bmdm = ? union ");
		sql.append("select '"+KhpfService.XS_JS_XX+"' dm,count(1) from fdyxxb) h on b.sjfwdm=h.dm ");
		sql.append("and c.sfnz='"+KhpfService.SFNZ_Y+"' and f.khlx='"+KhpfService.YHLX_JS+"' ");
		sql.append("where c.pflx='"+KhpfService.YHLX_XS+"' or (c.pflx='"+KhpfService.YHLX_BR+"' ");
		sql.append("and (a.khdxid in (select khdxid from xg_khgl_khdx_xs where khxh= ? ) ");
		sql.append("or (a.khdxid='xs')) ) )group by xmid,khbid,khbmc,khlx,xmmc,kssj,jssj,khdxmc) t1 ");
		sql.append("left join (select xmid,khbid,count(1) ypcount from (select xmid,khbid,substr(t.xmszid, ");
		sql.append("instr(t.xmszid, ',', 1, c.lv) + 1,instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select xmid,khbid,',' || xmszid || ',' AS xmszid, ");
		sql.append("length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select xmid,khbid, xmszid from xg_khgl_khpf where sftj='"+KhpfService.SFTJ_Y+"' and pfr= ? )) t,");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 30) c ");
		sql.append(" where c.lv <= t.cnt) group by xmid,khbid) t2 on t1.xmid=t2.xmid ");
		sql.append("and t1.khbid=t2.khbid where t1.dpcount > 0) where 1=1 ");
		// 浙江商业技师 过滤未开始与已结束
		if("33333".equals(Base.xxdm)){
			sql.append(" and sfjs = '0' ");
		}
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
				user.getUserName(),user.getUserName(),user.getUserName(),
				user.getUserName(),user.getUserName(),user.getUserName(),
				user.getUserName(),user.getUserDep(),user.getUserName(),
				user.getUserName()}, inputV);
	
		return getPageList(t, sql.toString(), both);
	}
	
	
	/**
	 * 
	 * @描述:教师评学生
	 * @作者：cq [工号：785]
	 * @日期：2015-8-13 上午11:33:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> teaEvaStu(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,substr(t1.xmmc,0,9)xn,t1.xmmc,t1.khbid,t1.xmszid,t1.xh khdxr,t2.* ");
		//湖南城市学院个性化
		if(Base.xxdm.equals(Globals.XXDM_HNCSXY)){
			sql.append(" ,case when t3.sftj='1' then '审核通过' else '未审核' end shztmc,t4.zf,t3.sftj shzt,nvl2(t3.zf,'已评','未评')sfyp,t4.xszpf,t4.bzpf,t4.bzrpf,t4.pm ");
		} else {
			sql.append(" ,t3.zf ");
		}
		sql.append("from ( ");
		sql.append("select pfzid,sjfwdm,xmid,xmmc,khbid,xh,wm_concat(xmszid) xmszid from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,nvl(nvl(nvl(nvl(nvl(t2.khdxr,t3.xh),t4.xh),t5.xh),t6.xh),t7.xh) xh ");
		sql.append("from (select a.pfzid, b.sjfwdm,d.xmid,d.xmmc,c.khbid,c.xmszid,b.khlx,e.sfnz,e.khdxid from xg_khgl_pfz a left join xg_khgl_pfz_nz b ");
		sql.append("on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_khxm_sz c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_khxm d on c.xmid = d.xmid left join xg_khgl_khdx e on d.khdxid = e.khdxid where c.xmid = ? and c.khbid = ? ");
		sql.append("and (b.khlx = '"+KhpfService.YHLX_XS+"' or b.khlx is null) and (a.sfnz='"+KhpfService.SFNZ_N+"' or (a.sfnz='"+KhpfService.SFNZ_Y+"' and b.sjfwdm=c.sjfwdm))) t1 ");
		sql.append("left join xg_khgl_pfz_js t2 on t1.pfzid = t2.pfzid left join (select a.xh from xsxxb a ");
		sql.append("where bjdm in (select bjdm from view_njxyzybj) and bjdm in");
		//湖南城市学院个性化
		if(Base.xxdm.equals(Globals.XXDM_HNCSXY)&&"false".equals(user.getBzrQx())){
			sql.append("(select ? bjdm from dual) ");
		}else{
			 sql.append(" (select bjdm from bzrbbb where zgh = ?) ");
		}
		sql.append(")t3 on t1.sjfwdm = '"+KhpfService.JS_XS_BZR+"' ");
		sql.append("left join (select a.xh from xsxxb a where bjdm in (select bjdm from view_njxyzybj) ");
		sql.append("and bjdm in (select bjdm from fdybjb where zgh = ?)) t4 on t1.sjfwdm = '"+KhpfService.JS_XS_FDY+"' ");
		sql.append("left join (select a.xh from xsxxb a where bjdm in (select bjdm from view_njxyzybj) ");
		sql.append("and (bjdm in (select bjdm from fdybjb where zgh = ?) ");
		sql.append("or bjdm in (select bjdm from bzrbbb where zgh = ?))) t5 on t1.sjfwdm = '"+KhpfService.JS_XS_BZRFDY+"' ");
		sql.append("left join (select a.xh from view_xsbfxx a where ");
		sql.append("bjdm in (select bjdm from view_njxyzybj) and a.xydm = ? ) t6 on t1.sjfwdm = '"+KhpfService.JS_XS_BYX+"' ");
		sql.append("left join (select a.xh from xsxxb a where bjdm in (select bjdm from view_njxyzybj)) t7 ");
		sql.append("on t1.sjfwdm = '"+KhpfService.JS_XS_XX+"' where (t2.pfzgh= ? or t1.khlx='"+KhpfService.YHLX_XS+"') ");
		sql.append("and (t1.sfnz = '"+KhpfService.SFNZ_Y+"' or ");
		sql.append("(t1.sfnz = '"+KhpfService.SFNZ_N+"' and nvl(nvl(nvl(nvl(nvl(t2.khdxr,t3.xh),t4.xh),t5.xh),t6.xh),t7.xh) in ");
		sql.append("(select khxh from xg_khgl_khdx_xs t8 where t1.khdxid=t8.khdxid))) ");
		sql.append(")group by pfzid,sjfwdm,xmid,xmmc,khbid,xh) t1 ");
		sql.append("left join view_xsbfxx t2 on t1.xh=t2.xh ");
		sql.append("left join xg_khgl_khpf t3 on t1.xmid = t3.xmid and t1.khbid = t3.khbid and t1.xh=t3.khdxr and t3.pfr= ? ");

		//湖南城市学院个性化
		if(Base.xxdm.equals(Globals.XXDM_HNCSXY)){
			sql.append(" left join view_xg_khgl_khpftj t4 on t2.xh=t4.xh ");
		}
		
		if(KhpfService.SFTJ_Y.equals(t.getSftj())){
			sql.append("where t3.sftj='"+KhpfService.SFTJ_Y+"'");
		} else {
			sql.append("where (t3.sftj='"+KhpfService.SFTJ_N+"' or t3.sftj is null)");
		}
		
		sql.append(" and t2.xh is not null order by t2.xh desc");
	    sql.append(") where 1=1 ");
		
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String []{
					t.getXmid(),t.getKhbid(),user.getUserName(),
					user.getUserName(),user.getUserName(),user.getUserName(),
					user.getUserDep(),user.getUserName(),user.getUserName()
				}, inputV);
		
		return getPageList(t, sql.toString(), both);
		
	}
	
	public List<HashMap<String, String>> jsPf(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,rank() over(partition by t.bjdm order by  nvl(t.zf,0) desc) as pm from ");
//		sql.append("(select t.*,nvl2(t.bzzf,'已评','未评') bzyp,nvl2(t.bzrzf,'已评','未评') bzryp,");
		sql.append("(select t.*,decode(t.zptjzt,'1','已提交','未提交') zpyp,decode(t.bztjzt,'1','已提交','未提交') bzyp,decode(t.bzrtjzt,'1','已提交','未提交') bzryp,");

		sql.append(" case when t.bztjzt='1' then '审核通过' else '未审核' end bzshztmc,t.bztjzt bzshzt,");
		sql.append(" case when t.bzrtjzt='1' then '审核通过' else '未审核' end bzrshztmc,t.bzrtjzt bzrshzt,");
		sql.append(" (to_number(nvl(t.zpzf,0)*c.zpqz)+ to_number(nvl(t.bzzf,0)*c.bzqz)+ to_number(nvl(t.bzrzf,0)*c.bzrqz))/100 zf");
		sql.append("  from(select a.xjh,a.xh,a.nj,a.xb,a.xm,c.bjmc,c.zydm,c.zymc,c.xydm,c.xymc,b.* from  view_xsjbxx a"); 
		sql.append(" left join (select a.xh xsxh,a.zhcpbjdm bjdm,a.xmid,a.zhcpbjxh,sum(nvl(a.zpf,0)*decode(b.pflx,'2','-1','1','1'))zpzf,sum(a.bzpf*decode(b.pflx,'2','-1','1','1'))bzzf,");
		sql.append(" sum(a.bzrpf*decode(b.pflx,'2','-1','1','1'))bzrzf,max(nvl(a.zptjzt,0)) zptjzt,max(nvl(a.bztjzt,0))bztjzt,");
		sql.append(" max(nvl(a.bzrtjzt,0))bzrtjzt from xg_khgl_khpfjlb a left join xg_khgl_tk_zbx b on a.zbmxid=b.zbmxid group by a.xh,a.xmid,a.zhcpbjdm,a.zhcpbjxh) b on a.xh=b.xsxh");
		sql.append(" left join VIEW_NJXYZYBJ_all c on b.bjdm=c.bjdm");
	    sql.append(" where a.bjdm in(");
		if("false".equals(user.getBzrQx())){
			sql.append("(select  bjdm from VIEW_NJXYZYBJ where bjmc = ?)) ");
		}else{
			 sql.append(" (select bjdm from bzrbbb where zgh = ?)) ");
		}
		sql.append(" order by a.bjdm)t,xg_khgl_pfqz c)t where 1=1"); 
		if("1".equals(t.getSftj())){
			HashMap<String,String> pfMap = getPfzd(t);
			sql.append(" and t.zpzf is not null ");
			sql.append(" and t."+pfMap.get("tjzt")+" <>'1'");
		}
		sql.append(searchTj);
		sql.append(" order by bjdm,to_number(pm),zhcpbjxh asc");
		
		return getPageList(t, sql.toString(), (String[]) ArrayUtils.addAll(new String []{user.getUserName()},inputV));
		
	}
	
	
	/**
	 * 
	 * @描述:教师评教师
	 * @作者：cq [工号：785]
	 * @日期：2015-8-13 上午11:59:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> teaEvaTea(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		HashMap<String, String> szInfo = getSzInfo(user);
		
		//不要问哥为什么这样做，一切都为了效率
		String khdxid = "";
		if(!"0".equals(szInfo.get("bzr"))){
			khdxid+=KhpfService.JS_XS_BZR+"','"+KhpfService.JS_XS_BZRFDY+"','";
		}
		if(!"0".equals(szInfo.get("fdy"))){
			khdxid+=KhpfService.JS_XS_FDY+"','"+KhpfService.JS_XS_BZRFDY+"','";
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,t1.zgh khdxr,t2.*,t3.zf from ( ");
		sql.append("select pfzid,sjfwdm,xmid,xmmc,khbid,zgh,wm_concat(xmszid) xmszid from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,nvl(nvl(nvl(t2.khdxr,t3.zgh),t4.zgh), ");
		sql.append("(case when t1.khlx='"+KhpfService.YHLX_BR+"' and t1.khdxid in ('"+khdxid+"') then '"+user.getUserName()+"' else '' end )");
		sql.append(") zgh from ( select a.pfzid, b.sjfwdm,d.xmid,d.xmmc,c.khbid,c.xmszid,b.khlx,e.sfnz,e.khdxid from xg_khgl_pfz a ");
		sql.append("left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_khxm_sz c ");
		sql.append("on a.pfzid = c.pfzid left join xg_khgl_khxm d on c.xmid = d.xmid left join xg_khgl_khdx e on e.khdxid =d.khdxid ");
		sql.append("where c.xmid = ? and c.khbid = ? and (b.khlx = '"+KhpfService.YHLX_JS+"' or b.khlx='"+KhpfService.YHLX_BR+"' or b.khlx is null) ");
		sql.append("and (a.sfnz='"+KhpfService.SFNZ_N+"' or (a.sfnz='"+KhpfService.SFNZ_Y+"' and b.sjfwdm=c.sjfwdm))) t1 ");
		sql.append("left join xg_khgl_pfz_js t2 on t1.pfzid = t2.pfzid ");
		sql.append("left join (select zgh from fdyxxb where bmdm = ? and zgh<> ?) t3 on t1.sjfwdm='"+KhpfService.JS_JS_BBM+"' ");
		sql.append("left join (select zgh from fdyxxb where zgh <> ?) t4 on t1.sjfwdm='"+KhpfService.JS_JS_QX+"' ");
		sql.append("where (t2.pfzgh= ? or t1.khlx='"+KhpfService.YHLX_JS+"' or t1.khlx='"+KhpfService.YHLX_BR+"') and (t1.sfnz = '"+KhpfService.SFNZ_Y+"' or ");
		sql.append("(t1.sfnz = '"+KhpfService.SFNZ_N+"' and nvl(nvl(nvl(t2.khdxr,t3.zgh),t4.zgh), ");
		sql.append("(case when t1.khlx='"+KhpfService.YHLX_BR+"' and t1.khdxid in ('"+khdxid+"') then '"+user.getUserName()+"' else '' end )) ");
		sql.append("in (select khzgh from xg_khgl_khdx_js t8 where t1.khdxid=t8.khdxid))) ");  
		sql.append(") group by pfzid,sjfwdm,xmid,xmmc,khbid,zgh) t1 ");
		sql.append("left join (select a.*,(select bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc,decode(a.xb,'1','男','2','女') xbmc from fdyxxb a) t2 on t1.zgh=t2.zgh ");
		sql.append("left join xg_khgl_khpf t3 on t1.xmid = t3.xmid and t1.khbid = t3.khbid and t1.zgh=t3.khdxr and t3.pfr= ?  ");
		if(KhpfService.SFTJ_Y.equals(t.getSftj())){
			sql.append("where t3.sftj='"+KhpfService.SFTJ_Y+"'");
		}else{
			sql.append("where (t3.sftj='"+KhpfService.SFTJ_N+"' or t3.sftj is null) ");
		}
		
		sql.append(" and t2.zgh is not null order by t2.zgh asc) where 1=1 ");
		
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
					t.getXmid(),t.getKhbid(),user.getUserDep(),
					user.getUserName(),user.getUserName(),
					user.getUserName(),user.getUserName()
				}, inputV);
		
		return getPageList(t, sql.toString(), both);
	}
	
	
	/**
	 * 
	 * @描述:学生评教师
	 * @作者：cq [工号：785]
	 * @日期：2015-8-13 下午03:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> stuEvaTea(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,t1.zgh khdxr,t2.*,t3.zf from ( ");
		sql.append("select pfzid,sjfwdm,xmid,xmmc,khbid,zgh,wm_concat(xmszid) xmszid from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid, ");
		sql.append("nvl(nvl(nvl(nvl(nvl(t2.khdxr,t3.zgh),t4.zgh),t5.zgh),t6.zgh),t7.zgh) zgh ");
		sql.append("from (select a.pfzid, b.sjfwdm,d.xmid,d.xmmc,c.khbid,c.xmszid,b.khlx,e.sfnz,e.khdxid from xg_khgl_pfz a left join xg_khgl_pfz_nz b ");
		sql.append("on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_khxm_sz c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_khxm d on c.xmid = d.xmid left join xg_khgl_khdx e on d.khdxid = e.khdxid where c.xmid = ? and c.khbid = ? ");
		sql.append("and (b.khlx = '"+KhpfService.YHLX_JS+"' or b.khlx is null) ");
		sql.append("and (a.sfnz='"+KhpfService.SFNZ_N+"' or (a.sfnz='"+KhpfService.SFNZ_Y+"' and b.sjfwdm=c.sjfwdm))) t1 left join xg_khgl_pfz_xs t2 on t1.pfzid = t2.pfzid ");
		sql.append("left join (select zgh from bzrbbb where bjdm = (select bjdm from xsxxb where xh = ? )) t3 on t1.sjfwdm = '"+KhpfService.XS_JS_BZR+"' ");
		sql.append("left join (select zgh from fdybjb where bjdm = (select bjdm from xsxxb where xh = ? )) t4 on t1.sjfwdm = '"+KhpfService.XS_JS_FDY+"' ");
		sql.append("left join (select zgh from bzrbbb where bjdm = (select bjdm from xsxxb where xh = ? ) ");
		sql.append("union select zgh from fdybjb where bjdm = (select bjdm from xsxxb where xh = ? )) t5 on t1.sjfwdm = '"+KhpfService.XS_JS_BZRFDY+"' ");
		sql.append("left join (select zgh from fdyxxb where bmdm = ? ) t6 on t1.sjfwdm = '"+KhpfService.XS_JS_BYX+"' ");
		sql.append("left join (select zgh from fdyxxb) t7 on t1.sjfwdm = '"+KhpfService.XS_JS_XX+"' ");
		sql.append("where (t2.pfzxh= ? or t1.khlx='"+KhpfService.YHLX_JS+"') and (t1.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("or (t1.sfnz='"+KhpfService.SFNZ_N+"' and nvl(nvl(nvl(nvl(nvl(t2.khdxr, t3.zgh), t4.zgh),t5.zgh),t6.zgh),t7.zgh) ");
		sql.append("in (select khzgh from xg_khgl_khdx_js t8 where t1.khdxid=t8.khdxid))) ");
		sql.append(") group by pfzid,sjfwdm,xmid,xmmc,khbid,zgh) t1 ");
		sql.append("left join (select a.*,(select bmmc from zxbz_xxbmdm b where a.bmdm=b.bmdm) bmmc,decode(a.xb,'1','男','2','女') xbmc from fdyxxb a) t2 on t1.zgh=t2.zgh ");
		sql.append("left join xg_khgl_khpf t3 on t1.xmid = t3.xmid and t1.khbid = t3.khbid and t1.zgh=t3.khdxr and t3.pfr= ? ");
		
		if(KhpfService.SFTJ_Y.equals(t.getSftj())){
			sql.append("where t3.sftj='"+KhpfService.SFTJ_Y+"'");
		}else{
			sql.append("where (t3.sftj='"+KhpfService.SFTJ_N+"' or t3.sftj is null) ");
		}
		sql.append(" and t2.zgh is not null) where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
					t.getXmid(),t.getKhbid(),user.getUserName(),
					user.getUserName(),user.getUserName(),user.getUserName(),
					user.getUserDep(),user.getUserName(),user.getUserName()
				}, inputV);
		
		return getPageList(t, sql.toString(), both);
		
	}
	
	
	/**
	 * 
	 * @描述:学生评学生
	 * @作者：cq [工号：785]
	 * @日期：2015-8-13 下午03:50:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> stuEvaStu(KhpfForm t, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,t1.xh khdxr,t2.*,t3.zf from ( ");
		sql.append("select pfzid,sjfwdm,xmid,xmmc,khbid,xh,wm_concat(xmszid) xmszid from ( ");
		sql.append("select t1.pfzid,t1.sjfwdm,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,nvl(nvl(t2.khdxr,t3.xh),");
		sql.append("(case when t1.khlx='"+KhpfService.YHLX_BR+"' and t1.khdxid in ('xs') then '"+user.getUserName()+"' else '' end) ");
		sql.append(") xh from ( select a.pfzid, b.sjfwdm,d.xmid,d.xmmc,c.khbid,c.xmszid,b.khlx,e.sfnz,e.khdxid from xg_khgl_pfz a ");
		sql.append("left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_khxm_sz c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_khxm d on c.xmid = d.xmid left join xg_khgl_khdx e on e.khdxid =d.khdxid ");
		sql.append("where c.xmid = ? and c.khbid = ? and (b.khlx = '"+KhpfService.YHLX_XS+"' or b.khlx='"+KhpfService.YHLX_BR+"' or b.khlx is null) ");
		sql.append("and (a.sfnz='"+KhpfService.SFNZ_N+"' or (a.sfnz='"+KhpfService.SFNZ_Y+"' and b.sjfwdm=c.sjfwdm))) t1 ");
		sql.append("left join xg_khgl_pfz_xs t2 on t1.pfzid = t2.pfzid ");
		sql.append("left join (select xh from xsxxb where bjdm = (select bjdm from xsxxb where xh = ? ) and xh <> ? ) t3 on t1.sjfwdm='"+KhpfService.XS_XS_BB+"' ");
		sql.append("where (t2.pfzxh= ?  or t1.khlx='"+KhpfService.YHLX_XS+"' or t1.khlx='"+KhpfService.YHLX_BR+"') and (t1.sfnz = '"+KhpfService.SFNZ_Y+"' or ");
		sql.append("(t1.sfnz = '"+KhpfService.SFNZ_N+"' and nvl(nvl(t2.khdxr,t3.xh),");
		sql.append("(case when t1.khlx='"+KhpfService.YHLX_BR+"' and t1.khdxid in ('xs') then '"+user.getUserName()+"' else '' end)) in ");
		sql.append("(select khxh from xg_khgl_khdx_xs t8 where t1.khdxid=t8.khdxid))) ");
		sql.append(") group by pfzid,sjfwdm,xmid,xmmc,khbid,xh) t1 ");
		sql.append("left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append("left join xg_khgl_khpf t3 on t1.xmid = t3.xmid and t1.khbid = t3.khbid and t1.xh=t3.khdxr and t3.pfr= ? ");
		
		if(KhpfService.SFTJ_Y.equals(t.getSftj())){
			sql.append("where t3.sftj='"+KhpfService.SFTJ_Y+"'");
		}else{
			sql.append("where (t3.sftj='"+KhpfService.SFTJ_N+"' or t3.sftj is null) ");
		}
		
		sql.append(" and t2.xh is not null) where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
					t.getXmid(),t.getKhbid(),user.getUserName(),
					user.getUserName(),user.getUserName(),
					user.getUserName()
				}, inputV);
		
		return getPageList(t, sql.toString(), both);
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:根据项目id和考核表id查询项目信息
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 上午09:40:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @param khbid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public KhpfForm getXmKhb(String xmid, String khbid) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select a.xmid,a.xmmc,b.khbid,c.khbmc,d.khlx from xg_khgl_khxm a left join xg_khgl_khxm_sz b on a.xmid=b.xmid ");
		sql.append("left join xg_khgl_khb c on b.khbid=c.khbid left join xg_khgl_khdx d on a.khdxid=d.khdxid ");
		sql.append(" where a.xmid = ? and b.khbid = ? and rownum =1 ");
		
		return getModel(sql.toString(), new String[]{xmid,khbid});
		
	}
	
	
	/**
	 * 
	 * @描述:保存考核评分
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午03:27:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveKhpf(KhpfForm form, User user) throws Exception{
		
		String sql = " insert into xg_khgl_khpf(xmid,khbid,pfr,khdxr,xmszid) values(?,?,?,?);";
		
		return dao.runUpdate(sql, new String[]{form.getXmid(),form.getKhbid(),user.getUserName(),form.getKhdxr(),form.getXmszid()});
	}
	
	
	/**
	 * 
	 * @描述:根据考核表ID查询考核项目
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午04:02:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXmInfo(String xmid){
		String sql = "select * from xg_khgl_khxm where xmid = ? ";
		return dao.getMapNotOut(sql, new String[]{xmid});
	}
	/**
	 * 
	 * @描述:获取评分信息状态
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-23 下午02:03:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getPfxxInfo(String xmid,User user){
		String sql = "select a.* from xg_khgl_khxm a where a.xmid = ? ";
		return dao.getMapNotOut(sql, new String[]{xmid});
	}
	
	public HashMap<String, String> getPfxxInfo_HNCS(String xmid,String xh){
		String sql = "select a.zptjzt sftj,a.bztjzt,a.bzrtjzt from xg_khgl_khpfjlb a where a.xmid = ? and a.xh=? and rownum=1 ";
		return dao.getMapNotOut(sql, new String[]{xmid,xh});
	}
	
	/**
	 * 
	 * @描述:查询辅导员信息
	 * @作者：cq [工号：785]
	 * @日期：2015-8-14 下午04:06:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getJsInfo(String zgh){
		String sql = "select a.zgh ry,a.*,b.bmmc xymc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm where a.zgh = ? ";
		return dao.getMapNotOut(sql, new String[]{zgh});
	}
	
	
	/**
	 * 查询学生信息
	 */
	public HashMap<String, String> getXsInfo(String xh){
		String sql = "select a.xh ry,a.* from view_xsbfxx a where a.xh = ? ";
		return dao.getMapNotOut(sql, new String[]{xh});
	}

	
	/**
	 * @throws Exception  
	 * @描述:保存考核评分状态表
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 上午11:44:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKhzt(KhpfForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("merge into xg_khgl_khpf a ");
		sql.append("using (select ? xmid,? khbid,? khdxr,? pfr,? xmszid ,'1' pfzt,? zf from dual) b on ");
		sql.append("(a.xmid=b.xmid and a.khbid=b.khbid and a.khdxr=b.khdxr and a.pfr=b.pfr) ");
		sql.append("when not matched then ");
		sql.append("insert(xmid,khbid,khdxr,pfr,xmszid,pfzt,zf) values(b.xmid, b.khbid, b.khdxr, b.pfr, b.xmszid,b.pfzt,b.zf)");
		sql.append(" when matched then update set a.pfzt=b.pfzt,a.xmszid=b.xmszid,");
		sql.append(" zf = (select sum(fs*decode(pflx,'2','-1','1','1')) from xg_khgl_khpf_pfmx t2 left join");
		sql.append(" xg_khgl_tk_zbx t3 on t2.khbid=t3.khbid and t2.zbmxid=t3.zbmxid where a.pfid=t2.pfid group by pfid)");
		
		return dao.runUpdate(sql.toString(), new String[]{
			model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),
			model.getXmszid(),model.getFs()
		});
	}

	
	
	/**
	 * @throws Exception  
	 * @描述:更新评分明细
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 下午01:24:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKhfs(KhpfForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("merge into xg_khgl_khpf_pfmx t1 ");
		sql.append("using (select (select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1) pfid, ");
		sql.append("? khbid, ? zbmxid from dual ) t2 ");
		sql.append("on (t1.pfid=t2.pfid and t1.khbid=t2.khbid and t1.zbmxid=t2.zbmxid) ");
		sql.append("when matched then ");
		sql.append("update set t1.fs = ? ");
		sql.append("when not matched then ");
		sql.append("insert(pfid,khbid,zbmxid,fs)");
		sql.append("values((select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1),");
		sql.append(" ?, ?, ?)");
		
		return dao.runUpdate(sql.toString(), new String[]{
			model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),
			model.getKhbid(),model.getZbmxid(),
			model.getFs(),model.getXmid(),model.getKhbid(),model.getKhdxr(),
			user.getUserName(),model.getKhbid(),model.getZbmxid(),model.getFs()
		});
	}

	public boolean batchSaveKhfs(KhpfForm model, User user) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("merge into xg_khgl_khpf_pfmx t1 ");
		sql.append("using (select (select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1) pfid, ");
		sql.append("? khbid, ? zbmxid from dual ) t2 ");
		sql.append("on (t1.pfid=t2.pfid and t1.khbid=t2.khbid and t1.zbmxid=t2.zbmxid) ");
		sql.append("when matched then ");
		sql.append("update set t1.fs = ? ");
		sql.append("when not matched then ");
		sql.append("insert(pfid,khbid,zbmxid,fs)");
		sql.append("values((select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1),");
		sql.append(" ?, ?, ?)");

		String [] zbmxidArr = model.getZbmxidArr();
		String [] fsArr = model.getFsArr();
		List<String []> paramList = new ArrayList<String[]>();
		for(int i=0;i<zbmxidArr.length;i++){
			String [] param = new String[]{
					model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),
					model.getKhbid(),zbmxidArr[i],
					fsArr[i],model.getXmid(),model.getKhbid(),model.getKhdxr(),
					user.getUserName(),model.getKhbid(),zbmxidArr[i],fsArr[i]
			};
			paramList.add(param);
		}

		return dao.runBatchBoolean(sql.toString(),paramList);
	}

	public boolean saveKhfs_HNCS(KhpfForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		String fslx="";
		sql.append("merge into xg_khgl_khpfjlb t1 using (select  ");
		sql.append("? xh,? xmid, ? zbmxid from dual ) t2 ");
		sql.append("on (t1.xh=t2.xh and t1.xmid=t2.xmid and t1.zbmxid=t2.zbmxid) ");
		sql.append("when matched then ");
		if(PFLX_XSZP.equals(model.getPflx())){
			fslx="zpf";
		}else if(PFLX_BZPF.equals(model.getPflx())){
			fslx="bzpf";
		}else{
			fslx="bzrpf";
		}
		sql.append("update  set "+fslx+" =? ");
		sql.append("when not matched then ");
		sql.append("insert(xh,xmid,zbmxid,"+fslx+")");
		sql.append("values(?, ?, ?, ?)");
		
		return dao.runUpdate(sql.toString(), new String[]{
			model.getKhdxr(),model.getXmid(),model.getZbmxid(),model.getFs(),
			model.getKhdxr(),model.getXmid(),model.getZbmxid(),model.getFs()
		});
	}
public boolean saveBz(KhpfForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("merge into xg_khgl_khpf_pfmx t1 ");
		sql.append("using (select (select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1) pfid, ");
		sql.append("? khbid, ? zbmxid from dual ) t2 ");
		sql.append("on (t1.pfid=t2.pfid and t1.khbid=t2.khbid and t1.zbmxid=t2.zbmxid) ");
		sql.append("when matched then ");
		sql.append("update set t1.bz=? ");
		sql.append("when not matched then ");
		sql.append("insert(pfid,khbid,zbmxid,bz)");
		sql.append("values((select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1),");
		sql.append(" ?, ?,?)");
		
		return dao.runUpdate(sql.toString(), new String[]{
			model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),
			model.getKhbid(),model.getZbmxid(),
			model.getBz(),model.getXmid(),model.getKhbid(),model.getKhdxr(),
			user.getUserName(),model.getKhbid(),model.getZbmxid(),model.getBz()
		});
	}
	
	/** 
	 * @描述:保存审核意见(武昌首义个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-2 下午04:25:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveKhYjjy(KhpfForm model, User user) throws Exception {		
		StringBuffer sql = new StringBuffer();
		sql.append("merge into xg_khgl_khpf_yjjyb t1 ");
		sql.append("using (select (select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1) pfid, ");
		sql.append("? khbid,? xmid from dual ) t2 ");
		sql.append("on (t1.pfid=t2.pfid and t1.khbid=t2.khbid and t1.xmid=t2.xmid) ");
		sql.append("when matched then ");
		sql.append("update set t1.yjjy = ? ");
		sql.append("when not matched then ");
		sql.append("insert(pfid,khbid,xmid,yjjy)");
		sql.append("values((select pfid from xg_khgl_khpf ");
		sql.append("where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1),");
		sql.append(" ?, ?, ?)");
		
		return dao.runUpdate(sql.toString(), new String[]{
			model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),
			model.getKhbid(),model.getXmid(),
			model.getYjjy(),model.getXmid(),model.getKhbid(),model.getKhdxr(),
			user.getUserName(),model.getKhbid(),model.getXmid(),model.getYjjy()
		});
	}
	
	/** 
	 * @描述:获取评价意见(武昌首义个性化)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-2 下午05:06:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String getKhYjjy(KhpfForm model, User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select yjjy from xg_khgl_khpf_yjjyb where pfid =  ");
		sql.append(" (select pfid from xg_khgl_khpf ");
		sql.append("  where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1)");
		return dao.getOneRs(sql.toString(), new String[] {model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName()},"yjjy");
		
	}

	/** 
	 * @描述:查询考核分数列表
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 下午01:44:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPfList(KhpfForm model, User user) {
	
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,case when t1.fzlx ='0' then t1.fzzdf||'～'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append("case when t1.pflx ='1' then '加分' else '减分' end pflxmc,t2.sftj,nvl(t3.fs,'')fs,t3.bz ");
		sql.append("from xg_khgl_tk_zbx t1 left join xg_khgl_khpf t2 on t1.khbid=t2.khbid ");
		sql.append("left join xg_khgl_khpf_pfmx t3 on t1.khbid=t3.khbid and t1.zbmxid =t3.zbmxid and t2.pfid = t3.pfid ");
		sql.append("where t2.xmid=? and t2.khbid=? and t2.khdxr=? and t2.pfr=?  order by to_number(xssx) ");
		String[] strr = new String[]{};
		if(xgxt.utils.String.StringUtils.isNotNull(model.getPfr())&&!"/".equals(model.getPfr())&&!"undefine".equals(model.getPfr())){
			strr = new String[]{
					model.getXmid(),model.getKhbid(),model.getKhdxr(),model.getPfr()
			};
		}else{
			strr = new String[]{
					model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName()
				};
		}
		return dao.getListNotOut(sql.toString(), strr);
	}
	/** 
	 * @描述:查询考核分数列表
	 * @作者：cq [工号：785]
	 * @日期：2015-8-15 下午01:44:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getKhPfList(KhpfForm model, User user) {
		
		HashMap<String, String> pfMap=getPfzd(model);
		beforeTheCurrently(model, user);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.*,t."+pfMap.get("tjzt")+" sftj,t."+pfMap.get("fslx")+" fs from (select t2.xmid,t2.xh,t1.*,case when t1.fzlx ='0' then t1.fzzdf||'～'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append("case when t1.pflx ='1' then '加分' else '减分' end pflxmc,nvl(t2.zpf,'')zpf,decode(t2.bztjzt,'1','已评','未评') bzyp,decode(t2.bzrtjzt,'1','已评','未评') bzryp,t2.zptjzt,t2.bztjzt,t2.bzrtjzt,nvl(t2.bzpf,'')bzpf,nvl(t2.bzrpf,'')bzrpf ");
		sql.append("from xg_khgl_tk_zbx t1 left join xg_khgl_khpfjlb t2 ");
		sql.append("  on t1.zbmxid = t2.zbmxid and t2.xmid = ? and t2.xh = ? ");
		sql.append("  left join xg_khgl_khb t3 on t1.khbid=t3.khbid  where t3.khbmc like '%综合素质测评%'");
		sql.append(" order by to_number(t1.xssx))t ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXmid(),model.getKhdxr()});
	}
	/**
	 * 
	 * @描述:评分统计
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-26 下午02:03:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getPfTjList(KhpfForm model, User user) {
		StringBuffer sql = new StringBuffer();
		sql.append("select yjzb,ejzb,khnr,fzzgf,sum(fs1) fs1,sum(fs2)fs2,sum(fs3)fs3,xssx from(select t1.*,case when t1.fzlx ='0' then t1.fzzdf||'～'||t1.fzzgf else t1.fzzdf end fzqj,");
		sql.append(" case when t1.pflx = '1' then '加分' else '减分' end pflxmc, t2.bzrtjzt, nvl(t2.zpf,0) fs1, nvl(t2.bzpf,0) fs2, nvl(t2.bzrpf,0) fs3");
		sql.append(" from xg_khgl_tk_zbx t1 left join xg_khgl_khpfjlb t2 on t1.zbmxid = t2.zbmxid and t2.xmid=?");
		sql.append(" where  t2.xh=? )group by  yjzb,ejzb,khnr,fzzgf,xssx  order by to_number(xssx) ");
		return dao.getListNotOut(sql.toString(), new String[]{model.getXmid(),model.getKhdxr()});
	}
	
	/**
	 * 
	 * @描述:总评信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-12-26 下午08:28:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZpxx(KhpfForm model) throws Exception {
		StringBuilder sql = new StringBuilder();
        sql.append(" select * from view_xg_khgl_khpftj where xmid=? and xh=? ");
		return dao.getMapNotOut(sql.toString(), new String[] { model.getXmid(),model.getKhdxr()});
	}
	public HashMap<String,String> getZpxxList(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_xg_khgl_khpftj where xh=? order by xn desc ");
		return dao.getMapNotOut(sql.toString(), new String[] {xh});
	}
	/**
	 * 
	 * @描述:湖南城市学院 个性化，要把上级加入到当前级
	 * @作者：cq [工号：785]
	 * @日期：2016-12-22 下午08:13:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean beforeTheCurrently(KhpfForm model, User user) {
		HashMap<String,String> pfMap =getPfzd(model);
		boolean bl = true;
		//第一级不需要返回信息
		if(StringUtils.isNotEmpty(pfMap.get("lastFslx"))){
			
			StringBuffer sql = new StringBuffer();
			sql.append("update  xg_khgl_khpfjlb a set a."+pfMap.get("fslx")+"=a."+pfMap.get("lastFslx"));
			sql.append(" where a.xmid=? and a.xh=? and a."+pfMap.get("fslx")+" is  null");
			
			try {
				bl = dao.runUpdate(sql.toString(), new String[]{model.getXmid(),model.getKhdxr()});
			} catch (Exception e) {
				// 插入成功不成功不重要，无需向上抛出错误
				e.printStackTrace();
			}
		}
		return bl;
	}

	/**
	 *  提交或审核后执行.
	 *  <p>无奈地沿着坑洼之路前行。</p>
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-29 14:14
	 * @param model
	 * @param user
	 * @return boolean
	 * @throw
	 */
	public boolean afterTheCurrently(KhpfForm model, User user) {

		HashMap<String,String> pfMap =getPfzd(model);
		boolean bl = true;
		if(StringUtils.isNotEmpty(pfMap.get("nextFslx"))){

			StringBuffer sql = new StringBuffer();
			sql.append("update  xg_khgl_khpfjlb a set a."+pfMap.get("nextFslx")+"=a."+pfMap.get("fslx"));
			sql.append(" where a.xmid=? and a.xh=? and nvl(a."+pfMap.get("nextTjzt")+",0)  !=  '1' ");
			sql.append(" and (select nvl(dxq,0) from xg_khgl_tk_zbx where zbmxid = a.zbmxid) = '1' ");
			try {
				bl = dao.runUpdate(sql.toString(), new String[]{model.getXmid(),model.getKhdxr()});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bl;
	}
	
	/**
	 * 
	 * @描述:湖南城市学院 个性化  根据xmid取上级项目名称，目前为了验收先写死，后面谁有空优化下
	 * @作者：cq [工号：785]
	 * @日期：2016-12-22 下午08:31:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getSjxmmc(String xmid){
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select case when xmmc like '%班组评分%' then '学生自评' ");
		sql.append("when xmmc like '%班主任评分%' then '班组评分' else '' end xmmc ");
		sql.append("from xg_khgl_khxm where xmid= ? ");
		
		return dao.getOneRs(sql.toString(),new String[]{xmid},"xmmc");
	}
	
	
	/**
	 * 
	 * @描述:验证是否可提交
	 * @作者：cq [工号：785]
	 * @日期：2015-8-17 上午11:34:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsCanSubmit(KhpfForm model, User user){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(1) count from xg_khgl_khb a left join xg_khgl_tk_zbx b on a.khbid=b.khbid ");
		sql.append("left join xg_khgl_khpf c on a.khbid=c.khbid ");
		sql.append("left join xg_khgl_khpf_pfmx d on c.pfid=d.pfid and b.zbmxid=d.zbmxid ");
		sql.append("where a.khbid = ? and c.xmid = ? and c.pfr = ? and c.khdxr = ? and d.fs is null ");
		
		String num = dao.getOneRs(sql.toString(), new String[]{
			model.getKhbid(),model.getXmid(),user.getUserName(),model.getKhdxr()
			}, "count");
		
		return Integer.valueOf(num) == 0;
	}

	
	/**
	 * @throws Exception  
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2015-8-17 上午11:51:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitBjzcf(KhpfForm model, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_khgl_khpf t1 set xmszid=?,pfzt=?,sftj = '"+KhpfService.SFTJ_Y+"',tjsj = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), ");
		sql.append("zf = (select sum(fs*decode(pflx,'2','-1','1','1')) from xg_khgl_khpf_pfmx t2 left join xg_khgl_tk_zbx t3 on t2.khbid=t3.khbid and t2.zbmxid=t3.zbmxid where t1.pfid=t2.pfid group by pfid)");
		sql.append(" where xmid=? and khbid=? and pfr=? and khdxr=? ");	
		return dao.runUpdate(sql.toString(), new String[]{model.getXmszid(),model.getShzt(),
			model.getXmid(),model.getKhbid(),user.getUserName(),model.getKhdxr()});
	}
	
	public boolean submitKhpf(KhpfForm model, User user) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("update xg_khgl_khpfjlb t1 set  ");
		if(PFLX_XSZP.equals(model.getPflx())){
			sql.append(" zptjzt=?,zptjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}else if(PFLX_BZPF.equals(model.getPflx())){
			sql.append(" bztjzt=?,bztjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}else{
			sql.append(" bzrtjzt=?,bzrtjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}
		sql.append(" where xmid=? and xh=? ");	
		return dao.runUpdate(sql.toString(), new String[]{model.getShzt(),
			model.getXmid(),model.getKhdxr()});
	}

	public boolean batchSubmitKhpf(KhpfForm model) throws Exception {
		String[] zbmxidArr = model.getZbmxidArr();
		String[] fsArr = model.getFsArr();
		List<String[]> paramList = new ArrayList<String[]>();

		StringBuffer sql = new StringBuffer();
		sql.append("update xg_khgl_khpfjlb t1 set  ");
		if(PFLX_XSZP.equals(model.getPflx())){
			sql.append("zpf=?,zptjzt=?,zptjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}else if(PFLX_BZPF.equals(model.getPflx())){
			sql.append("bzpf=?,bztjzt=?,bztjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}else{
			sql.append("bzrpf=?,bzrtjzt=?,bzrtjsj=to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') ");
		}
		sql.append(" where xmid=? and xh=? and zbmxid = ?");

		for (int i=0;i<zbmxidArr.length;i++){
			String [] param = new String[] {fsArr[i],model.getShzt(),model.getXmid(),model.getKhdxr(),zbmxidArr[i]};
			paramList.add(param);
		}

		return dao.runBatchBoolean(sql.toString(),paramList);
	}
	
	private HashMap<String,String> getPfzd(KhpfForm model){
		String fslx="";
		String lastFslx="";
		String nextFslx="";

		String tjzt="";
		String nextTjzt="";

		if(PFLX_XSZP.equals(model.getPflx())){
			fslx="zpf";
			tjzt="zptjzt";
			nextFslx="bzpf";
			nextTjzt="bztjzt";
		}else if(PFLX_BZPF.equals(model.getPflx())){
			fslx="bzpf";
			tjzt="bztjzt";
			lastFslx="zpf";
			nextFslx="bzrpf";
			nextTjzt="bzrtjzt";
		}else{
			fslx="bzrpf";
			tjzt="bzrtjzt";
			lastFslx="bzpf";
		}
		HashMap<String,String> pfMap = new HashMap<String,String>();
		pfMap.put("fslx", fslx);
		pfMap.put("tjzt", tjzt);
		pfMap.put("lastFslx", lastFslx);
		pfMap.put("nextFslx",nextFslx);
		pfMap.put("nextTjzt",nextTjzt);
		return pfMap;
	}
	
	/** 
	 * @描述:提交意见建议(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-4 下午03:03:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitYjjy(KhpfForm model,User user) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" update xg_khgl_khpf_yjjyb set sftj = '"+KhpfService.SFTJ_Y+"'");
		sql.append(" where pfid = (select pfid from xg_khgl_khpf ");
		sql.append(" where xmid = ? and khbid = ? and khdxr = ? and pfr = ? and rownum = 1) ");
		sql.append(" and xmid = ? and khbid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{model.getXmid(),model.getKhbid(),model.getKhdxr(),user.getUserName(),model.getXmid(),model.getKhbid()});
	}
	
	/** 
	 * @描述:是否教师(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-11-5 上午11:59:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isJs(KhpfForm model){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from fdyxxb where zgh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{model.getKhdxr()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	
	/**
	 * 
	 * @描述:根据xmid 删除考核评分
	 * @作者：cq [工号：785]
	 * @日期：2015-8-20 下午02:29:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delKhpfForXmid(String[] xmid) throws Exception {
		StringBuffer sql = new StringBuffer();
		 sql.append( "delete from xg_khgl_khpf where xmid in(");
		for (int i = 0; i < xmid.length; i++) {
			if(i!=0){
				sql.append(",");
			}
			sql.append("?");
		}
		sql.append(")");
		return dao.runUpdate(sql.toString(), xmid);
	}
	
	
	/**
	 * 
	 * @描述:删除无效的pfmx
	 * @作者：cq [工号：785]
	 * @日期：2015-8-20 下午02:29:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delWxPfmx() throws Exception{
		
		String sql = "delete from xg_khgl_khpf_pfmx where pfid not in (select pfid from xg_khgl_khpf)";
		
		return dao.runUpdate(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:查询自己不同身份所负责的人数
	 * @作者：cq [工号：785]
	 * @日期：2015-9-16 上午09:36:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSzInfo(User user){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(case when bjdm in(select bjdm from bzrbbb where zgh= ? ) then 1 else null end) bzr,");
		sql.append("count(case when bjdm in(select bjdm from fdybjb where zgh= ? ) then 1 else null end) fdy,");
		sql.append("count(case when xydm = ? then 1 else null end) byx,count(1) xx from view_xsbfxx where bjdm in (select bjdm from view_njxyzybj) ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{user.getUserName(),user.getUserName(),user.getUserDep()});
	}
	
	
	/**
	 * 查询自己不同身份所负责的人数返回转换成table语句
	 * 一切为了效率
	 */
	
	public String getTabSql(User user){
		
		HashMap<String, String> ham = getSzInfo(user);
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ( select '"+KhpfService.JS_XS_BZR+"' dm ,'"+ham.get("bzr")+"' count from dual union select '"+KhpfService.JS_XS_FDY+"','"+ham.get("fdy")+"' ");
		sql.append("from dual union select '"+KhpfService.JS_XS_BYX+"','"+ham.get("byx")+"' count from dual union  select '"+KhpfService.JS_XS_XX+"','"+ham.get("xx")+"' from dual) ");
		
		return sql.toString();
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 考核取消提交[浙大宁宁波理工学院个性化需求]
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-7 下午03:02:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param khpfForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelTjRecord(KhpfForm khpfForm) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" update XG_KHGL_KHPF set sftj = '2',pfzt = null where xmid = ? and khbid = ? and khdxr = ? and xmszid = ? and pfr = ? ");
		return dao.runUpdate(sql.toString(), new String[]{khpfForm.getXmid(),khpfForm.getKhbid(),khpfForm.getKhdxr(),khpfForm.getXmszid(),khpfForm.getPfr()});
	}

	/**
	 *  根据用户信息，获取班主任所代的班级的班组评分密码信息列表.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 11:51
	 * @param user
	 * @return List<HashMap<String,String>>
	 * @throw
	 */
	public List<HashMap<String,String>> getBzpfmmList(User user) {
		String yhm = user.getUserName();
		String sql = "SELECT a.bjmc username,y.BZPFKL password  FROM " +
				"  (SELECT bjdm,(SELECT bjmc FROM VIEW_NJXYZYBJ WHERE BJDM = b.BJDM) bjmc FROM bzrbbb b  " +
				"  WHERE zgh = ? AND BJDM IN (SELECT bjdm FROM view_xsjbxx)) a " +
				"LEFT JOIN yhb y ON a.bjmc = y.yhm";
		return dao.getListNotOut(sql,new String[]{yhm});
	}

	/**
	 *  班组评分密码保存.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 17:12
	 * @param username
	 * @param password
	 * @return boolean
	 * @throw Exception
	 */
	public boolean scbzpfmmSave(String username, String password,String kl) throws Exception {

		boolean rs = false;
		//判断已班级代码为用户名的用户是否存在，不存在则插入，存在则更新
		boolean isExist = pfzyhIsExist(username);
		if(isExist){
			String sql = "update yhb set  kl = ?,bzpfkl = ? where yhm = ?";
			rs = dao.runUpdate(sql,new String[]{kl,password,username});

		}else {
			String sql = "INSERT INTO YHB (YHM, KL, ZDM, XM, SZBM, DWDM, XSSX, QX, BZPFKL) " +
					"VALUES (?, ?, 'cd60dbdd47a2890fabdfe99c0897f52d', ?, (SELECT xydm FROM VIEW_NJXYZYBJ " +
					"WHERE BJMC = ?), null, null, '1', ?)";
			rs = dao.runUpdate(sql,new String[]{username,kl,username,username,password});
		}
		if(rs){
			//赋权
			rs = pfzyhFq(username);
		}
		return rs;
	}


	/**
	 *  根据用户名判断用户是否存在.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 17:22
	 * @param username
	 * @return boolean
	 * @throw
	 */
	public boolean pfzyhIsExist(String username){

		String sql = "select count(1) count from yhb where yhm = ?";
		String count = dao.getOneRs(sql,new String[]{username},"count");
		return Integer.valueOf(count)>0;
	}

	/**
	 *  评分组用户赋权.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-09-14 17:22
	 * @param username
	 * @return boolean
	 * @throw
	 */
	public boolean pfzyhFq(String username) throws Exception {
		boolean rs = true;
		String sql_del = "delete yhqxb where yhm = ?";
		String sql_add = "INSERT INTO YHQXB SELECT ?,gnmkdm,dxq FROM yhzqxb " +
				"WHERE ZDM = 'cd60dbdd47a2890fabdfe99c0897f52d'";

		rs = dao.runDelete(sql_del,new String[]{username}) >= 0;
		if(rs) {
			rs = dao.runUpdate(sql_add, new String[]{username});
		}
		return rs;
	}

	/**
	 *  获取班级测评成绩总列表（考核记录，含各一级指标总分）.
	 *
	 * @author <a href="www.gavinshow.com">GavinShow[1426]</a>
	 * @date 2017-11-22 09:57
	 * @param model
	 * @param user
	 * @return java.util.List<java.util.HashMap<java.lang.String,java.lang.String>>
	 * @throw Exception
	 */
	public List<HashMap<String,String>> getBjzccjzbList(KhpfForm model, User user) throws Exception {

		SearchModel searchModel = model.getSearchModel();

		//生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(searchModel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * FROM (");
		sql.append("SELECT x.xjh,x.xm,x.xb,x.xydm,x.xymc,x.zydm,x.zymc,x.bjmc,");
		sql.append("t.*,rank() OVER (PARTITION BY t.bjdm ORDER BY t.zf DESC) pm FROM (");
		sql.append("SELECT t.xsxh,t.xmid xn,t.bjdm,t.zhcpbjxh,");
		sql.append("sum(decode(t.yjzb,'思想品德素质',t.yjzbzf,0)) sxpdszf,");
		sql.append("sum(decode(t.yjzb,'专业文化素质',t.yjzbzf,0)) zywhszf,");
		sql.append("sum(decode(t.yjzb,'身心素质',t.yjzbzf,0)) sxszf,");
		sql.append("sum(decode(t.yjzb,'能力水平',t.yjzbzf,0)) nlspf,");
		sql.append("sum(decode(t.yjzb,'扣分',t.yjzbzf,0)) kf,");
		sql.append("sum(t.yjzbzf) zf ");
		sql.append("FROM (SELECT t.*,(t.zpzf * c.zpqz + t.bzzf * c.bzqz + t.bzrzf * c.bzrqz) / 100 yjzbzf ");
		sql.append("FROM (SELECT a.xh xsxh,a.xmid,a.zhcpbjdm bjdm,a.zhcpbjxh,b.YJZB,");
		sql.append("sum(nvl(a.zpf, 0) * decode(b.pflx, '2', '-1', '1', '1')) zpzf,");
		sql.append("sum(nvl(a.bzpf,0) * decode(b.pflx, '2', '-1', '1', '1')) bzzf,");
		sql.append("sum(nvl(a.bzrpf,0) * decode(b.pflx, '2', '-1', '1', '1')) bzrzf ");
		sql.append("FROM xg_khgl_khpfjlb a LEFT JOIN xg_khgl_tk_zbx b ON a.zbmxid = b.zbmxid ");
		sql.append("GROUP BY a.xh, a.xmid, a.zhcpbjdm, a.zhcpbjxh,b.YJZB) t,xg_khgl_pfqz c) t ");
		sql.append("GROUP BY t.xsxh,t.xmid,t.bjdm,t.zhcpbjxh) t LEFT JOIN view_xsjbxx x ON t.xsxh = x.xh ");
		sql.append(") t where 1=1 ");

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return dao.getListNotOut(sql.toString(), inputV);
	}
}
