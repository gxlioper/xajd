/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午03:11:02 
 */  
package com.zfsoft.xgxt.khgl.jgcx.jgcx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.khgl.khpf.KhpfService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 考核管理
 * @类功能描述: 考核结果
 * @作者：cq [工号:785]
 * @时间： 2015-8-18 下午03:11:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JgcxDao extends SuperDAOImpl<JgcxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JgcxForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select xmid,xmmc,khdxid,kssj,jssj,xmms,khdxmc,khlx, ");
		sql.append("sum(case when khdxr is not null and sftj = '"+KhpfService.SFTJ_Y+"' then '1' else '0' end) sum ");
		sql.append("from(select distinct t1.*,t2.khdxmc,t2.khlx,t3.sftj,t4.xh||t5.zgh khdxr from xg_khgl_khxm t1 ");
		sql.append("left join xg_khgl_khdx t2 on t1.khdxid=t2.khdxid left join xg_khgl_khpf t3 on t1.xmid=t3.xmid ");
		sql.append("left join xsxxb t4 on t3.khdxr=t4.xh left join fdyxxb t5 on t3.khdxr=t5.zgh");
		
		//根据部门院级过滤数据
		String deptno = user.getUserDep();
		if(!"xx".equalsIgnoreCase(user.getUserStatus())) {		
			
			sql.append(" where (exists (select 1 from fdyxxb t6 where t3.khdxr = t6.zgh and bmdm = ? ) or exists ");		
			sql.append(" (select 1 from xsxxb t10 left join view_njxyzybj_all t11 on t10.bjdm = t11.bjdm where t3.khdxr = t10.xh  and t11.xydm = ?))");
			params.add(deptno);
			params.add(deptno);
		}
		sql.append(") where 1=1 ");

		sql.append(searchTj);
		sql.append(" group by xmid,xmmc,khdxid,kssj,jssj,xmms,khdxmc,khlx ");
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(params.toArray(new String[]{}), inputV);
		
		return getPageList(t, sql.toString(), both);
	}
	public List<HashMap<String, String>> lscpjgList(JgcxForm t, User user)
	throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t.*,t.xh khdxr from view_xg_khgl_khpftj t where 1=1 ");
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JgcxForm.class);
		super.setKey("xmid");
		super.setTableName("xg_khgl_khxm");

	}
	
	/**
	 * 
	 * @描述:考核对象类型为学生
	 * @作者：cq [工号：785]
	 * @日期：2015-8-19 下午01:50:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> xmjgToXs(JgcxForm t, User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select * from (");
		sql.append("select t2.*,t1.zf,rank() over (order by t1.zf desc) pm from ( ");
		sql.append("select xmid,khdxr,round(nvl(sum(szqz*zf/100),0),2) zf from ( ");
		sql.append("select xmid,khdxr,xmszid,szqz, ");
		sql.append("round(sum(case when a!='a' and e!= 'a' then zf else '0' end)/ ");
		sql.append("decode(sum(case when a!='a' and e!= 'a' then '1' else '0' end),'','1','0','1',");
		sql.append("sum(case when a!='a' and e!='a' then '1' else '0' end)),2) zf ");
		sql.append("from ( select xmid,khdxr,xmszid,zf,szqz, ");
		sql.append("lag(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) a, ");
		sql.append("lead(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) e ");
		sql.append("from (select t1.pfid,t1.xmid,t1.khbid,t1.pfr,t1.sftj,t1.sfyx,t1.khdxr,t1.tjsj,t1.zf,t1.xmszid,");
		sql.append("count(1) over(partition by khdxr,t1.xmszid) total,t2.jsfs,t2.jsfsbfb,t2.szqz from ( ");
		sql.append("select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("substr(t.xmszid,instr(t.xmszid, ',', 1, c.lv) + 1, ");
		sql.append("instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select  pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("',' || xmszid || ',' AS xmszid,length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf,xmszid from xg_khgl_khpf ");
		sql.append("where xmid=? and sftj = '"+KhpfService.SFTJ_Y+"' )) t, ");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 20) c ");
		sql.append("where c.lv <= t.cnt) t1 left join xg_khgl_khxm_sz t2 on t1.xmszid=t2.xmszid))");
		sql.append("group by xmid,khdxr,xmszid,szqz)  group by xmid,khdxr) t1 ");
		sql.append("left join view_xsxxb t2 on t1.khdxr=t2.xh where t2.xh is not null");
		params.add(t.getXmid());
		//根据部门院级过滤数据
		if(!"xx".equalsIgnoreCase(user.getUserStatus())) {
			sql.append(" and t2.xydm = ? ");
			params.add(user.getUserDep());
		}
		sql.append(") where 1=1 ");
		
		sql.append(searchTj);
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(params.toArray(new String[]{}), inputV);
		
		return getPageList(t, sql.toString(), both);//这段SQL在PLSQL中千万别格式化，别手贱。来自善良的xiaxia友情提示
	}
	
	
	/**
	 * 
	 * @描述:考核对象类型为教师
	 * @作者：cq [工号：785]
	 * @日期：2015-8-19 下午01:51:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> xmjgToJs(JgcxForm t, User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("select * from (");
		sql.append("select t2.*,t3.bmmc,(case t2.xb when '1' then '男' when '2' then '女' else t2.xb end) xbmc, ");
		sql.append("t1.zf,rank() over (order by t1.zf desc) pm from ( ");
		sql.append("select xmid,khdxr,round(nvl(sum(szqz*zf/100),0),2) zf from ( ");
		sql.append("select xmid,khdxr,xmszid,szqz, ");
		sql.append("round(sum(case when a!='a' and e!= 'a' then zf else '0' end)/ ");
		sql.append("decode(sum(case when a!='a' and e!= 'a' then '1' else '0' end),'','1','0','1',");
		sql.append("sum(case when a!='a' and e!='a' then '1' else '0' end)),2) zf ");
		sql.append("from ( select xmid,khdxr,xmszid,zf,szqz, ");
		sql.append("lag(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) a, ");
		sql.append("lead(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) e ");
		sql.append("from (select t1.pfid,t1.xmid,t1.khbid,t1.pfr,t1.sftj,t1.sfyx,t1.khdxr,t1.tjsj,t1.zf,t1.xmszid,");
		sql.append("count(1) over(partition by khdxr,t1.xmszid) total,t2.jsfs,t2.jsfsbfb,t2.szqz from ( ");
		sql.append("select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("substr(t.xmszid,instr(t.xmszid, ',', 1, c.lv) + 1, ");
		sql.append("instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select  pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("',' || xmszid || ',' AS xmszid,length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf,xmszid from xg_khgl_khpf ");
		sql.append("where xmid=? and sftj = '"+KhpfService.SFTJ_Y+"' )) t, ");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 20) c ");
		sql.append("where c.lv <= t.cnt) t1 left join xg_khgl_khxm_sz t2 on t1.xmszid=t2.xmszid))");
		sql.append("group by xmid,khdxr,xmszid,szqz)  group by xmid,khdxr) t1 ");
		sql.append("left join fdyxxb t2 on t1.khdxr=t2.zgh left join zxbz_xxbmdm t3 on t2.bmdm=t3.bmdm where t2.zgh is not null");
		params.add(t.getXmid());
		//根据部门院级过滤数据
		if(!"xx".equalsIgnoreCase(user.getUserStatus())) {		
			sql.append(" and t2.bmdm = ? ");
			params.add(user.getUserDep());
		}
		
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(params.toArray(new String[]{}), inputV);
		
		return getPageList(t, sql.toString(), both);
	}
	public List<HashMap<String, String>> xmjgToJsOfSdty(JgcxForm t, User user,List<HashMap<String,String>> pfzList) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select t2.*,t3.bmmc,(case t2.xb when '1' then '男' when '2' then '女' else t2.xb end) xbmc ");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",fs").append(i);
		}
		sql.append(",rank() over (PARTITION BY t3.bmmc order by t1.zf desc) xypm,t1.zf,rank() over (order by t1.zf desc) pm from ( ");
		sql.append("select t.xmid,t.khdxr");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",sum(t.zf").append(i).append(") fs").append(i);
		}
		sql.append(",nvl(sum(szqz*zf/100),0) zf from ( ");
		sql.append(" select t.xmid,t.khdxr,t.xmszid,t.szqz,t.zf");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",case when t.xmszid='").append(pfzList.get(i).get("xmszid")).append("' then t.zf else 0 end zf").append(i);
		}
		sql.append(" from(select xmid,khdxr,xmszid,szqz, ");
		sql.append("round(sum(case when a!='a' and e!= 'a' then zf else '0' end)/ ");
		sql.append("decode(sum(case when a!='a' and e!= 'a' then '1' else '0' end),'','1','0','1',");
		sql.append("sum(case when a!='a' and e!='a' then '1' else '0' end)),2) zf ");
		sql.append("from ( select xmid,khdxr,xmszid,zf,szqz, ");
		sql.append("lag(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) a, ");
		sql.append("lead(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) e ");
		sql.append("from (select t1.pfid,t1.xmid,t1.khbid,t1.pfr,t1.sftj,t1.sfyx,t1.khdxr,t1.tjsj,t1.zf,t1.xmszid,");
		sql.append("count(1) over(partition by khdxr,t1.xmszid) total,t2.jsfs,t2.jsfsbfb,t2.szqz from ( ");
		sql.append("select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("substr(t.xmszid,instr(t.xmszid, ',', 1, c.lv) + 1, ");
		sql.append("instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select  pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("',' || xmszid || ',' AS xmszid,length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf,xmszid from xg_khgl_khpf ");
		sql.append("where xmid=? and sftj = '"+KhpfService.SFTJ_Y+"' )) t, ");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 20) c ");
		sql.append("where c.lv <= t.cnt) t1 left join xg_khgl_khxm_sz t2 on t1.xmszid=t2.xmszid))");
		sql.append("group by xmid,khdxr,xmszid,szqz)t)t  group by xmid,khdxr) t1 ");
		sql.append("left join fdyxxb t2 on t1.khdxr=t2.zgh left join zxbz_xxbmdm t3 on t2.bmdm=t3.bmdm where t2.zgh is not null) where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{t.getXmid()}, inputV);
		
		return getPageList(t, sql.toString(), both);//这段SQL在PLSQL中千万别格式化，别手贱。来自善良的xiaxia友情提示
	}
	
	public List<HashMap<String, String>> xmjgToXsOfSdty(JgcxForm t, User user,List<HashMap<String,String>> pfzList) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select t2.*,t1.zf");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",t1.fs").append(i);
		}
		sql.append(",rank() over (PARTITION BY t2.xymc order by t1.zf desc) xypm,rank() over (order by t1.zf desc) pm from ( ");
		sql.append(" select t.xmid,t.khdxr");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",sum(zf").append(i).append(") fs").append(i);
		}
		sql.append(",nvl(sum(szqz*zf/100),0) zf from ( ");
		sql.append(" select t.xmid,t.khdxr,t.xmszid,t.szqz,t.zf");
		for (int i = 0; i < pfzList.size(); i++) {
			sql.append(",case when t.xmszid='").append(pfzList.get(i).get("xmszid")).append("' then t.zf else 0 end zf").append(i);
		}
		sql.append(" from(select xmid,khdxr,xmszid,szqz, ");
		sql.append("round(sum(case when a!='a' and e!= 'a' then zf else '0' end)/ ");
		sql.append("decode(sum(case when a!='a' and e!= 'a' then '1' else '0' end),'','1','0','1',");
		sql.append("sum(case when a!='a' and e!='a' then '1' else '0' end)),2) zf ");
		
		sql.append("from ( select xmid,khdxr,xmszid,zf,szqz, ");
		sql.append("lag(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) a, ");
		sql.append("lead(zf, (case jsfs when '2' then floor(total*jsfsbfb/2/100) else to_number(jsfs) end), 'a') ");
		sql.append("over(partition by xmid,khdxr,xmszid order by to_number(zf)) e ");
		sql.append("from (select t1.pfid,t1.xmid,t1.khbid,t1.pfr,t1.sftj,t1.sfyx,t1.khdxr,t1.tjsj,t1.zf,t1.xmszid,");
		sql.append("count(1) over(partition by khdxr,t1.xmszid) total,t2.jsfs,t2.jsfsbfb,t2.szqz from ( ");
		sql.append("select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("substr(t.xmszid,instr(t.xmszid, ',', 1, c.lv) + 1, ");
		sql.append("instr(t.xmszid, ',', 1, c.lv + 1) -(instr(t.xmszid, ',', 1, c.lv) + 1)) AS xmszid ");
		sql.append("from (select  pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf, ");
		sql.append("',' || xmszid || ',' AS xmszid,length(xmszid || ',') - nvl(length(REPLACE(xmszid, ',')), 0) AS cnt ");
		sql.append("FROM (select pfid,xmid,khbid,pfr,sftj,sfyx,khdxr,tjsj,zf,xmszid from xg_khgl_khpf ");
		sql.append("where xmid=? and sftj = '"+KhpfService.SFTJ_Y+"' )) t, ");
		sql.append("(select LEVEL lv from dual CONNECT BY LEVEL <= 20) c ");
		sql.append("where c.lv <= t.cnt) t1 left join xg_khgl_khxm_sz t2 on t1.xmszid=t2.xmszid))");
		sql.append("group by xmid,khdxr,xmszid,szqz)t)t  group by xmid,khdxr) t1 ");
		sql.append("left join view_xsbfxx t2 on t1.khdxr=t2.xh where t2.xh is not null) where 1=1 ");
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{t.getXmid()}, inputV);
		
		return getPageList(t, sql.toString(), both);//这段SQL在PLSQL中千万别格式化，别手贱。来自善良的xiaxia友情提示
	}
	
	/**
	 * 
	 * @描述:统计按学生找可被考核人
	 * @作者：cq [工号：785]
	 * @日期：2015-8-21 下午02:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> xsForKhr (JgcxForm t, User user) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select pfzid,? bmdm,? khdxr,pflx,khlx,pfzmc,sjfw,xmid,xmmc,khlx,khbid,xmszid,count(1),sum(decode(pfzt,'0',1,0)) wdf,round(sum(decode(pfzt,'0',1,0))/nvl(count(1),1)*100,2) wdfb, ");
		sql.append("sum(decode(pfzt,'1',1,0)) ydf,round(sum(decode(pfzt,'1',1,0))/nvl(count(1),1)*100,2) ydfb  from ( ");
		sql.append("select t1.pfzid,t1.pflx,t1.pfzmc,t4.sjfw,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,t1.pfr||t2.zgh||t3.xh pfr, ");
		sql.append("case when t5.pfid is null or t5.sftj = '"+KhpfService.SFTJ_N+"' then '0' else '1' end pfzt,t5.khdxr,t6.khlx from ( ");
		sql.append("select a.pfzid,a.pfzmc,a.pflx,b.khlx,b.sjfwdm,c.pfzgh||d.pfzxh pfr,a.sfnz,e.khbid,e.xmszid,f.xmid,f.xmmc,f.khdxid,f.kssj, ");
		sql.append("f.jssj from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_pfz_js c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_pfz_xs d on a.pfzid = d.pfzid left join xg_khgl_khxm_sz e on (a.sfnz = '"+KhpfService.SFNZ_N+"' and a.pfzid = e.pfzid) ");
		sql.append("or (a.sfnz = '"+KhpfService.SFNZ_Y+"' and a.pfzid = e.pfzid and b.sjfwdm = e.sjfwdm)  left join xg_khgl_khxm f ");
		sql.append("on f.xmid = e.xmid where (e.sjfwdm is not null or c.khdxr = ? or d.khdxr = ? ) and f.xmid = ? ");
		sql.append(") t1 left join (select a.zgh,bmdm xydm,decode(b.zgh,'','否','是') sffdy,decode(c.zgh,'','否','是') sfbzr, ");
		sql.append("case when b.zgh is not null or c.zgh is not null then '是' else '否' end bzrfdy, 'xx' xx from fdyxxb a ");
		sql.append("left join (select zgh from fdybjb where bjdm = (select bjdm from xsxxb where xh = ? ))  b on a.zgh=b.zgh ");
		sql.append("left join (select zgh from bzrbbb where bjdm = (select bjdm from xsxxb where xh = ?)) c on a.zgh=c.zgh) t2 ");
		sql.append("on (t1.sjfwdm = '"+KhpfService.XS_JS_BZR+"' and t2.sfbzr = '是') ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_FDY+"' and t2.sffdy = '是' ) ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_BZRFDY+"' and (t2.sfbzr = '是' or t2.sffdy = '是') ) ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_BYX+"' and t2.xydm = ? )  ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_XX+"' and t2.xx = 'xx') ");
		sql.append("left join (select xh,case xh when ? then 'br' else 'bb' end sjfw from xsxxb ");
		sql.append("where bjdm = (select bjdm from xsxxb where xh = ? )) t3 ");
		sql.append("on (t1.sjfwdm = '"+KhpfService.XS_XS_BB+"' and t3.sjfw='bb') or (t1.sjfwdm = '"+KhpfService.BR_BR+"' and t3.sjfw = 'br') ");
		sql.append("left join xg_khgl_pfz_nz t4 on t1.pflx=t4.khlx and t1.sjfwdm=t4.sjfwdm and t1.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("left join xg_khgl_khpf t5 on t1.xmid = t5.xmid and t1.khbid=t5.khbid and t1.pfr||t2.zgh||t3.xh = t5.pfr and t5.khdxr= ? ");
		sql.append("left join xg_khgl_khdx t6 on t1.khdxid=t6.khdxid ) group by pfzid,pflx,pfzmc,sjfw,xmid,xmmc,khbid,xmszid,khlx ");
		
		return dao.getListNotOut(sql.toString(), new String[]{
			t.getBmdm(),t.getKhdxr(),t.getKhdxr(),t.getKhdxr(),t.getXmid(),t.getKhdxr(),
			t.getKhdxr(),t.getBmdm(),t.getKhdxr(),t.getKhdxr(),t.getKhdxr()
			});
		
	}

	/** 
	 * @描述:统计按教师找可被考核人
	 * @作者：cq [工号：785]
	 * @日期：2015-8-24 下午01:44:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> jsForKhr(JgcxForm form, User user) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ? khdxr,? bmdm,pfzid,pflx,pfzmc,sjfw,xmid,xmmc,khlx,khbid,xmszid,count(1),sum(decode(pfzt,'0',1,0)) wdf, ");
		sql.append("round(sum(decode(pfzt,'0',1,0))/nvl(count(1),1)*100,2) wdfb, ");
		sql.append("sum(decode(pfzt,'1',1,0)) ydf,round(sum(decode(pfzt,'1',1,0))/nvl(count(1),1)*100,2) ydfb from ( ");
		sql.append("select t1.pfzid,t6.khlx,t1.pflx,t1.pfzmc,t4.sjfw,t1.xmid,t1.xmmc,t1.khbid,t1.xmszid,t1.pfr||t2.xh||t3.zgh pfr, ");
		sql.append("case when t5.pfid is null or t5.sftj = '"+KhpfService.SFTJ_N+"' then '0' else '1' end pfzt,t5.khdxr from ( ");
		sql.append("select a.pfzid,a.pfzmc,a.pflx,b.khlx,b.sjfwdm,c.pfzgh||d.pfzxh pfr,a.sfnz,e.khbid,e.xmszid,f.xmid,f.xmmc,f.khdxid,f.kssj, ");
		sql.append("f.jssj from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_pfz_js c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_pfz_xs d on a.pfzid = d.pfzid left join xg_khgl_khxm_sz e on (a.sfnz = '"+KhpfService.SFNZ_N+"' and a.pfzid = e.pfzid) ");
		sql.append("or (a.sfnz = '"+KhpfService.SFNZ_Y+"' and a.pfzid = e.pfzid and b.sjfwdm = e.sjfwdm)  left join xg_khgl_khxm f ");
		sql.append("on f.xmid = e.xmid where (e.sjfwdm is not null or c.khdxr = ? or d.khdxr = ? ) and f.xmid = ? ");
		sql.append(") t1 left join (select a.xh,a.xm,a.xydm,a.bjdm,case when a.bjdm in (select bjdm from fdybjb c where zgh = ? ) then  '是' else '否' end sffdy,");
		sql.append("case when a.bjdm in (select bjdm from bzrbbb c where zgh = ? ) then '是' else '否' end sfbzr,'xx'xx  from view_xsbfxx a where sfzx = '在校') t2 ");
		sql.append("on (t1.sjfwdm='"+KhpfService.JS_XS_BZR+"' and t2.sfbzr='是') ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_FDY+"' and t2.sffdy='是') ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_BZRFDY+"' and (t2.sfbzr='是' or t2.sffdy='是')) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_BYX+"' and t2.xydm= ? ) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_XX+"' and t2.xx='xx') ");
		sql.append("left join fdyxxb t3 on (t1.sjfwdm='"+KhpfService.JS_JS_BBM+"' and t3.bmdm = ? and t3.zgh <> ? )");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_JS_QX+"' and t3.zgh <> ? ) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.BR_BR+"' and t3.zgh = ? ) ");
		sql.append("left join xg_khgl_pfz_nz t4 on t1.pflx=t4.khlx and t1.sjfwdm=t4.sjfwdm and t1.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("left join xg_khgl_khpf t5 on t1.xmid = t5.xmid and t1.khbid=t5.khbid and t1.pfr||t2.xh||t3.zgh = t5.pfr and t5.khdxr= ? ");
		sql.append("left join xg_khgl_khdx t6 on t1.khdxid=t6.khdxid ) group by pfzid,pflx,pfzmc,sjfw,xmid,xmmc,khlx,khbid,xmszid ");
		
		return dao.getListNotOut(sql.toString(), new String[]{
			form.getKhdxr(),form.getBmdm(),form.getKhdxr(),form.getKhdxr(),form.getXmid(),form.getKhdxr(),form.getKhdxr(),
			form.getBmdm(),form.getBmdm(),form.getKhdxr(),form.getKhdxr(),form.getKhdxr(),form.getKhdxr()}
		);
	}

	/** 
	 * @描述:打分人查看_教师
	 * @作者：cq [工号：785]
	 * @日期：2015-8-24 下午06:08:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDfrListJs(JgcxForm model, User user) throws Exception{
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (");
		sql.append("select case when t5.pfid is null or t5.sftj = '"+KhpfService.SFTJ_N+"' then '0' else '1' end pfzt,t5.zf,t5.khbid,t7.* from ( ");
		sql.append("select a.pfzid,a.pfzmc,a.pflx,b.khlx,b.sjfwdm,c.pfzgh||d.pfzxh pfr,a.sfnz,e.khbid,e.xmszid,f.xmid,f.xmmc,f.khdxid,f.kssj, ");
		sql.append("f.jssj from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' left join xg_khgl_pfz_js c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_pfz_xs d on a.pfzid = d.pfzid left join xg_khgl_khxm_sz e on (a.sfnz = '"+KhpfService.SFNZ_N+"' and a.pfzid = e.pfzid) ");
		sql.append("or (a.sfnz = '"+KhpfService.SFNZ_Y+"' and a.pfzid = e.pfzid and b.sjfwdm = e.sjfwdm)  left join xg_khgl_khxm f ");
		sql.append("on f.xmid = e.xmid where (e.sjfwdm is not null or c.khdxr = ? or d.khdxr = ?) and f.xmid = ? and e.xmszid = ? ");
		sql.append(") t1 left join (select a.xh,a.xm,a.xydm,a.bjdm,case  when a.bjdm in (select bjdm from fdybjb c where zgh = ? ) then  '是' else '否' end sffdy,");
		sql.append("case when a.bjdm in (select bjdm from bzrbbb c where zgh = ? ) then '是' else '否' end sfbzr,'xx'xx  from view_xsbfxx a where sfzx = '在校') t2 ");
		sql.append("on (t1.sjfwdm='"+KhpfService.JS_XS_BZR+"' and t2.sfbzr='是') ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_FDY+"' and t2.sffdy='是') ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_BZRFDY+"' and (t2.sfbzr='是' or t2.sffdy='是')) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_BYX+"' and t2.xydm= ? ) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_XS_XX+"' and t2.xx='xx') ");
		sql.append("left join fdyxxb t3 on (t1.sjfwdm='"+KhpfService.JS_JS_BBM+"' and t3.bmdm = ? and t3.zgh <> ? ) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.JS_JS_QX+"'and t3.zgh <> ? ) ");
		sql.append("or (t1.sjfwdm='"+KhpfService.BR_BR+"'and t3.zgh = ? ) ");
		sql.append("left join xg_khgl_pfz_nz t4 on t1.pflx=t4.khlx and t1.sjfwdm=t4.sjfwdm and t1.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("left join xg_khgl_khpf t5 on t1.xmid = t5.xmid and t1.khbid=t5.khbid and t1.pfr||t2.xh||t3.zgh = t5.pfr and t5.khdxr= ? ");
		sql.append("");
		if(model.getPflx().equals(KhpfService.YHLX_XS)){// 学生
			sql.append("left join view_xsbfxx t7 on t1.pfr||t2.xh||t3.zgh = t7.XH ");
		}else{
			sql.append("left join (select a.*,b.bmmc,decode(a.xb,'1','男','2','女',a.xb) xbmc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm) t7 on t1.pfr||t2.xh||t3.zgh = t7.zgh");
		}
		
		sql.append(") where 1=1 ");
		if(model.getPfzt().equals(KhpfService.PFZT_WP)){
			sql.append("and pfzt = '"+KhpfService.PFZT_WP+"' ");
		}else{
			sql.append("and pfzt = '"+KhpfService.PFZT_YP+"' ");
		}
		
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
				model.getKhdxr(),model.getKhdxr(),model.getXmid(),model.getXmszid(),model.getKhdxr(),model.getKhdxr(),
				model.getBmdm(),model.getBmdm(),model.getKhdxr(),model.getKhdxr(),model.getKhdxr(),model.getKhdxr()
			}, inputV);
		
		return getPageList(model, sql.toString(), both);
	}

	
	/** 
	 * @描述:打分人查看_学生
	 * @作者：cq [工号：785]
	 * @日期：2015-8-24 下午06:08:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDfrListXs(JgcxForm model, User user) throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (");
		sql.append("select case when t5.pfid is null or t5.sftj = '"+KhpfService.SFTJ_N+"' then '0' else '1' end pfzt,t5.zf,t5.khbid,t7.* from (select a.pfzid,a.pfzmc,a.pflx,b.khlx,b.sjfwdm,c.pfzgh||d.pfzxh pfr,a.sfnz,e.khbid,e.xmszid,f.xmid,f.xmmc,f.khdxid,f.kssj, ");
		sql.append("f.jssj from xg_khgl_pfz a left join xg_khgl_pfz_nz b on a.pflx = b.pflx and a.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("left join xg_khgl_pfz_js c on a.pfzid = c.pfzid ");
		sql.append("left join xg_khgl_pfz_xs d on a.pfzid = d.pfzid left join xg_khgl_khxm_sz e on (a.sfnz = '"+KhpfService.SFNZ_N+"' and a.pfzid = e.pfzid) ");
		sql.append("or (a.sfnz = '"+KhpfService.SFNZ_Y+"' and a.pfzid = e.pfzid and b.sjfwdm = e.sjfwdm)  left join xg_khgl_khxm f ");
		sql.append("on f.xmid = e.xmid where (e.sjfwdm is not null or c.khdxr = ? or d.khdxr = ? ) and f.xmid = ? and e.xmszid = ? ");
		sql.append(") t1 left join (select a.zgh,bmdm xydm,decode(b.zgh,'','否','是') sffdy,decode(c.zgh,'','否','是') sfbzr, ");
		sql.append("case when b.zgh is not null or c.zgh is not null then '是' else '否' end bzrfdy, 'xx' xx from fdyxxb a ");
		sql.append("left join (select zgh from fdybjb where bjdm = (select bjdm from xsxxb where xh = ?))  b on a.zgh=b.zgh ");
		sql.append("left join (select zgh from bzrbbb where bjdm = (select bjdm from xsxxb where xh = ?)) c on a.zgh=c.zgh) t2 ");
		sql.append("on (t1.sjfwdm = '"+KhpfService.XS_JS_BZR+"' and t2.sfbzr = '是') ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_FDY+"' and t2.sffdy = '是' ) ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_BZRFDY+"' and (t2.sfbzr = '是' or t2.sffdy = '是')) ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_BYX+"' and t2.xydm = ?) ");
		sql.append("or (t1.sjfwdm = '"+KhpfService.XS_JS_XX+"' and t2.xx = 'xx') ");
		sql.append("left join (select xh,case xh when ? then 'br' else 'bb' end sjfw ");
		sql.append("from xsxxb where bjdm = (select bjdm from xsxxb where xh = ?)) t3 ");
		sql.append("on (t1.sjfwdm = '"+KhpfService.XS_XS_BB+"' and t3.sjfw='bb') or (t1.sjfwdm = '"+KhpfService.BR_BR+"' and t3.sjfw = 'br') ");
		sql.append("left join xg_khgl_pfz_nz t4 on t1.pflx=t4.khlx and t1.sjfwdm=t4.sjfwdm and t1.sfnz = '"+KhpfService.SFNZ_Y+"' ");
		sql.append("left join xg_khgl_khpf t5 on t1.xmid = t5.xmid and t1.khbid=t5.khbid and t1.pfr||t2.zgh||t3.xh = t5.pfr and t5.khdxr= ? ");
		sql.append("");
		if(model.getPflx().equals(KhpfService.YHLX_XS)||(model.getPflx().equals(KhpfService.YHLX_BR)&&KhpfService.YHLX_XS.equals(model.getKhlx()))){// 学生
			sql.append("left join view_xsbfxx t7 on t1.pfr||t2.zgh||t3.xh = t7.XH ");
		}else{
			sql.append("left join (select a.*,b.bmmc,decode(a.xb,'1','男','2','女',a.xb) xbmc from fdyxxb a left join zxbz_xxbmdm b on a.bmdm=b.bmdm) t7 on t1.pfr||t2.zgh||t3.xh = t7.zgh");
		}
		
		sql.append(" ) where 1=1 ");
		if(model.getPfzt().equals(KhpfService.PFZT_WP)){
			sql.append("and pfzt = '"+KhpfService.PFZT_WP+"' ");
		}else{
			sql.append("and pfzt = '"+KhpfService.PFZT_YP+"' ");
		}
		
		sql.append(searchTj);
		
		//将自己的参数和高级查询条件合并
		String[] both = (String[]) ArrayUtils.addAll(new String[]{
				model.getKhdxr(),model.getKhdxr(),model.getXmid(),model.getXmszid(),model.getKhdxr(),
				model.getKhdxr(),model.getBmdm(),model.getKhdxr(),model.getKhdxr(),model.getKhdxr()
			}, inputV);
		
		return getPageList(model, sql.toString(), both);
	}
	public List<HashMap<String,String>> getPfzListByXmid(String xmid)throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmszid,a.pfzid,b.pfzmc||'('||a.szqz||'%)' pfzmc from xg_khgl_khxm_sz a left join xg_khgl_pfz b on a.pfzid=b.pfzid where a.xmid=?");
		return dao.getListNotOut(sql.toString(), new String[] {xmid});
	}

	/** 
	 * @描述:根据项目id，班主任（职工号）List查询学生对班主任的汇总打分数据列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年6月5日 下午4:48:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @param bzrList
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsdbzrhzList(String xmid, List<String> bzrList) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT bjmc,round(pjf,2) pjf,round(pjf*0.15,2) pjfp15,cprs,myrs,bjmyrs,ybrs,bmyrs,round(myrs/cprs*100,2)||'%' mybl,");
		sql.append("round(bjmyrs/cprs*100,2)||'%' bjmybl,");
		sql.append("round(ybrs/cprs*100,2)||'%' ybbl,round(bmyrs/cprs*100,2)||'%' bmybl FROM ");
		sql.append("(SELECT vx.bjmc,avg(pf.zf) pjf,count(pf.pfr) cprs,");
		sql.append("sum(CASE WHEN to_number(nvl(pf.zf,0)) >= 85 THEN 1 ELSE 0 END ) myrs,");
		sql.append("sum(CASE WHEN to_number(nvl(pf.zf,0)) >= 75 AND to_number(nvl(pf.zf,0))<85 THEN 1 ELSE 0 END ) bjmyrs,");
		sql.append("sum(CASE WHEN to_number(nvl(pf.zf,0)) >= 60 AND to_number(nvl(pf.zf,0))<75 THEN 1 ELSE 0 END ) ybrs,");
		sql.append("sum(CASE WHEN to_number(nvl(pf.zf,0)) <60 THEN 1 ELSE 0 END ) bmyrs ");
		sql.append("FROM xg_khgl_khpf pf ");
		sql.append("LEFT JOIN XG_KHGL_KHXM_SZ sz ");
		sql.append("ON pf.xmszid = sz.xmszid ");
		sql.append("LEFT JOIN XG_KHGL_PFZ pfz ");
		sql.append("ON pfz.pfzid = sz.pfzid ");
		sql.append("LEFT JOIN VIEW_XSBFXX vx ");
		sql.append("ON pf.pfr = vx.xh ");
		sql.append("WHERE pf.sftj = '1' ");
		sql.append("AND pfz.pflx = '1' ");
		sql.append("AND pf.khdxr IN (");
		for(int i=0;i<bzrList.size();i++){
			sql.append("?");
			if(i!=bzrList.size()-1){
				sql.append(",");
			}
		}
		sql.append(") AND pf.xmid = ? ");
		sql.append("GROUP BY vx.bjdm,vx.bjmc) t");
		
		bzrList.add(xmid);
		return dao.getListNotOut(sql.toString(), bzrList.toArray(new String[]{}));
	}

}
