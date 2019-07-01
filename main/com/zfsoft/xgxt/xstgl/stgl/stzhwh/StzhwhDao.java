/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 下午06:01:03 
 */  
package com.zfsoft.xgxt.xstgl.stgl.stzhwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xstgl.rtgl.rtsq.RtsqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-8-14 下午06:01:03 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StzhwhDao extends SuperDAOImpl<StzhwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(StzhwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	

	public List<HashMap<String, String>> getStzhwhList(StzhwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.stid," +
				    "t1.stxmmc,"+
				    "t1.stlbdm,"+
				    "t1.xmlbdm,"+
				    "t1.xn,"+
				    "t1.gkdw,"+
				    "t1.kssj,"+
				    "t1.jssj,"+
				    "t1.fzrlb," +
				"t1.stxj,"+
				    "t1.stfzr,"+
				    "t1.zdls,"+
				    "t1.sqsj,"+
				    "t1.lxdh,"+
				    "t1.sqkssj,"+
				    "t1.sqjssj,"+
				    "t1.sqkg,"+
				    "t1.splc,"+
				    "t1.jtr,"+
				    "t1.stsm,"+
				    "t1.sjly,"+
				    "t1.zdlszc,"+
				    "t1.zdlslxfs,"+
				    "t1.ssbm,"+
				    "t1.stclsj,"+
				    "t1.sthjqk,"+
				    "nvl(t1.cysl,0) cysl,"+
			
				"case when sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') and " +
				"to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 " + 
				"then '1' else '0' end bz,'" +Base.currXn+"' currxn,"+
				"t2.xmlbmc,t3.stlbmc,nvl(t4.xm,t1.jtr) jtrxm,x.zdlsxms zdlsxm,t6.xm stfzrxm ");
		sql.append("from xg_stgl_jtjg t1 left join xg_stgl_xmlb t2 on t1.xmlbdm = t2.xmlbdm left join xg_stgl_stlb t3 on t2.stlbdm=t3.stlbdm " +
				"left join（select a.stid,listagg(c.xm,',') within GROUP (order by c.xm) as zdlsxms from xg_stgl_jtjg a left join xg_stgl_zdlscy b on a.stid = b.stid  " +
				"     left join fdyxxb c on b.zgh = c.zgh  group by a.stid ) x on t1.stid = x.stid ");
		sql.append(" left join yhb t4 on t1.jtr=t4.yhm" +
				" left join yhb t5 on t1.zdls = t5.yhm"+
				" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t6"+
				" on t1.stfzr = t6.yhm"+
				") t where 1=1 ");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(StzhwhForm.class);
		super.setKey("id");
		super.setTableName("xg_rtgl_ztbg");
	}
	
	//暂定当前学年学期的维护数据
	public List<HashMap<String, String>> getzhwhList(String xn,String stid,String tnzt){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t.rtid,");
		sql.append(" t.stid,");
		sql.append(" t.tc,");
		sql.append(" t.sqly,");
		sql.append(" t.tnzt,");
		sql.append(" t1.id,");
		sql.append(" t1.cjpd,");
		sql.append(" t1.xn, ");
		sql.append(" t2.xm,");
		sql.append(" t2.xb,");
		sql.append(" t2.bjmc");
		sql.append(" from xg_rtgl_rtjg t");
		sql.append(" left join (select * from xg_rtgl_ztbg t1 where t1.xn = ?) t1");
		sql.append(" on t.rtid = t1.rtid ");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t.stid = ? ");
		if(tnzt != null && !tnzt.equals("")){
			sql.append(" and (t.tnzt = ?) ");
		}
		String[] inputvalue = new String[]{xn,stid,tnzt};
		if(tnzt == null || tnzt.equals("")){
			inputvalue = new String[]{xn,stid};
		}
		return dao.getListNotOut(sql.toString(), inputvalue);
	}
	
	//成员状态维护list
	public List<HashMap<String, String>> getStCyZtWhList(String stid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t.tc,");
		sql.append(" t.rtid,");
		sql.append(" t.sqly,");
		sql.append(" t1.xb,");
		sql.append(" t1.xm,");
		sql.append(" t.tnzt ");
		sql.append(" from xg_rtgl_rtjg t left join");
		sql.append(" view_xsjbxx t1 on  t.xh = t1.xh");
		sql.append(" where t.stid = ?  order by t.tnzt desc");
		return dao.getListNotOut(sql.toString(), new String[]{stid});
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(StzhwhForm t, User user)
	    throws Exception {
// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append(" select t.xh,");
		sql.append(" t.stid,");
		sql.append(" t.xq,");
		sql.append(" t.xn,");
		sql.append(" t.cjpd,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.nj,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t2.xmlbdm,");
		sql.append(" t2.stxmmc,");
		sql.append(" t2.zdls,");
		sql.append(" t3.xmlbmc,");
		sql.append(" t3.stlbdm,");
		sql.append(" t4.stlbmc,");
		sql.append(" t5.xm zdlsxm");
		sql.append(" from xg_rtgl_ztbg t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join xg_stgl_jtjg t2");
		sql.append(" on t.stid = t2.stid");
		sql.append(" left join xg_stgl_xmlb t3");
		sql.append(" on t2.xmlbdm = t3.xmlbdm");
		sql.append(" left join xg_stgl_stlb t4");
		sql.append(" on t3.stlbdm = t4.stlbdm");
		sql.append(" left join yhb t5");
		sql.append(" on t2.zdls = t5.yhm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
    }
	
	/**
	 * 成员成绩查看
	 */
	public List<HashMap<String, String>> getCycjlist(String stid,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh, t.xn, t.cjpd, t1.tc, t1.sqly, t2.xm, t2.bjmc, t2.xb");
		sql.append(" from xg_rtgl_ztbg t");
		sql.append(" left join xg_rtgl_rtjg t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" and t.stid = t1.stid");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t1.stid = ?");
		sql.append(" and t.xh = ?");
		sql.append(" order by t.xn desc");
		return dao.getListNotOut(sql.toString(), new String[]{stid,xh});
	}
	
}
