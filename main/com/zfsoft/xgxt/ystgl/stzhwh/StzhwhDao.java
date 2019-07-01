/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 下午06:01:03 
 */  
package com.zfsoft.xgxt.ystgl.stzhwh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

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
		String searchTjByUser = SearchService.getSearchSztzShTjByUser(user, "t", "xydm", "");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*, nvl(t6.num,0) cysl,case when sysdate between to_date(nvl(t1.kssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') and " );
		sql.append(" to_date(nvl(t1.jssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1  ");
		sql.append(" then '1' else '0' end bz,'" +Base.currXn+"' currxn,t2.xmlbmc,t3.ystlbmc,nvl(t4.xm,t1.jtr) jtrxm,t5.xm zdlsxm ,t7.gkdwdm xydm,t7.gkdwmc ");
		sql.append("from XG_YSTGL_YSTJGB t1 left join XG_YSTGL_XMLB t2 on t1.xmlbdm = t2.xmlbdm left join XG_YSTGL_ystlb t3 on t2.ystlbdm=t3.ystlbdm ");
		sql.append(" left join yhb t4 on t1.jtr=t4.yhm left join yhb t5 on t1.zdls = t5.yhm left join XG_YSTGL_GKDW t7 on t1.gkdwdm=t7.gkdwdm left join  (select count(1) num,ystid from xg_ystgl_rtjgb  where tnzt='正常' group by ystid) t6 on t1.ystid=t6.ystid) t where 1=1 ");
		sql.append(searchTjByUser);
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
		super.setTableName("XG_YSTGL_RTGL_CJPD");
	}
	
	//暂定当前学年学期的维护数据
	public List<HashMap<String, String>> getzhwhList(String xn,String ystid,String tnzt){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t.rtid,");
		sql.append(" t.ystid,");
		sql.append(" t.tc,");
		sql.append(" t.sqly,");
		sql.append(" t.tnzt,");
		sql.append(" t1.id,");
		sql.append(" t1.cjpd,");
		sql.append(" t1.xn, ");
		sql.append(" t2.xm,");
		sql.append(" t2.xb,");
		sql.append(" t2.bjmc");
		sql.append(" from xg_ystgl_rtjgb t");
		sql.append(" left join (select * from XG_YSTGL_RTGL_CJPD t1 where t1.xn = ?) t1");
		sql.append(" on t.rtid = t1.rtid ");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t.ystid = ? ");
		if(tnzt != null && !tnzt.equals("")){
			sql.append(" and (t.tnzt = ?) ");
		}
		String[] inputvalue = new String[]{xn,ystid,tnzt};
		if(tnzt == null || tnzt.equals("")){
			inputvalue = new String[]{xn,ystid};
		}
		return dao.getListNotOut(sql.toString(), inputvalue);
	}
	
	//成员状态维护list
	public List<HashMap<String, String>> getStCyZtWhList(String ystid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t.tc,");
		sql.append(" t.rtid,");
		sql.append(" t.sqly,");
		sql.append(" t1.xb,");
		sql.append(" t1.xm,");
		sql.append(" t.tnzt ");
		sql.append(" from xg_ystgl_rtjgb t left join");
		sql.append(" view_xsjbxx t1 on  t.xh = t1.xh");
		sql.append(" where t.ystid = ?  order by t.tnzt desc");
		return dao.getListNotOut(sql.toString(), new String[]{ystid});
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
		sql.append(" t6.ystid,");
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
		sql.append(" t2.ystxmmc,");
		sql.append(" t2.zdls,");
		sql.append(" t3.xmlbmc,");
		sql.append(" t3.ystlbdm,");
		sql.append(" t4.ystlbmc,");
		sql.append(" t5.xm zdlsxm");
		sql.append(" from XG_YSTGL_RTGL_CJPD t left join Xg_Ystgl_Rtjgb t6 on t.rtid=t6.rtid");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join XG_YSTGL_YSTJGB t2");
		sql.append(" on t6.ystid = t2.ystid");
		sql.append(" left join XG_YSTGL_XMLB t3");
		sql.append(" on t2.xmlbdm = t3.xmlbdm");
		sql.append(" left join XG_YSTGL_ystlb t4");
		sql.append(" on t3.ystlbdm = t4.ystlbdm");
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
	public List<HashMap<String, String>> getCycjlist(String ystid,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh, t.xn, t.cjpd, t1.tc, t1.sqly, t2.xm, t2.bjmc, t2.xb");
		sql.append(" from XG_YSTGL_RTGL_CJPD t left join Xg_Ystgl_Rtjgb t6 on t.rtid=t6.rtid");
		sql.append(" left join xg_ystgl_rtjgb t1");
		sql.append(" on t6.xh = t1.xh");
		sql.append(" and t6.ystid = t1.ystid");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t.xh = t2.xh");
		sql.append(" where t1.ystid = ?");
		sql.append(" and t.xh = ?");
		sql.append(" order by t.xn desc");
		return dao.getListNotOut(sql.toString(), new String[]{ystid,xh});
	}
	public int delCycj(String[] values) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_YSTGL_RTGL_CJPD where rtid=?");
		return dao.runDelete(sql.toString(), values);
	} 
}
