/**
 * @部门:学工产品事业部
 * @日期：2015-8-14 上午08:39:50 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-02-19 上午08:39:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstRtshDao extends SuperDAOImpl<YstRtshForm> {

	/*
    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
*/

@Override
public List<HashMap<String, String>> getPageList(YstRtshForm t)
		throws Exception {
	// TODO 自动生成方法存根
	return null;
}

/*
    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
*/

@Override
public List<HashMap<String, String>> getPageList(YstRtshForm t, User user)
		throws Exception {
	String searchTj = SearchService.getSearchTj(t.getSearchModel());
	String[] inputV = SearchService.getTjInput(t.getSearchModel());
	String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
	String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
	StringBuffer sql = new StringBuffer();
	sql.append("select t.* from (");
	sql.append("select t1.*,t2.xm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.xydm,t2.xymc,t2.nj,t2.xb,t3.xmlbdm,t7.ystlbdm,t8.splc,");
	sql.append(" t4.guid shid,");
	sql.append(" t4.gwid,t4.shzt shztx,");
	sql.append(" t4.shr,");
	sql.append(" t4.shyj,");
	sql.append(" t9.xm zdlsxm,");
	sql.append(" t6.mc || '[' ||");
	sql.append(" decode(t4.shzt,");
	sql.append(" '0',");
	sql.append("'未审核',");
	sql.append(" '1', ");
	sql.append(" '通过',");
	sql.append(" '2', ");
	sql.append(" '不通过',");
	sql.append(" '3', ");
	sql.append(" '退回', ");
	sql.append(" '4', ");
	sql.append(" '需重审', ");
	sql.append(" '5', ");
	sql.append("  '审核中') || ']' shztmc, ");
	sql.append(" t6.gwz, t8.ystxmmc,t8.xn yxxn,t7.ystlbmc,");
	sql.append("  row_number() over(partition by t1.rtid order by t4.shsj desc) rn ");
	sql.append(" from xg_ystgl_rtsqb t1");
	sql.append(" left join view_xsbfxx t2");
	sql.append(" on t1.xh = t2.xh");
	sql.append(" left join XG_YSTGL_YSTJGB t8");
	sql.append(" on t1.ystid = t8.ystid");
	sql.append(" left join XG_YSTGL_XMLB t3");
	sql.append("  on t8.xmlbdm = t3.xmlbdm");
	sql.append(" left join XG_YSTGL_ystlb t7");
	sql.append(" on t3.ystlbdm = t7.ystlbdm");
	sql.append(" left join xg_xtwh_shztb t4");
	sql.append(" on t1.rtid = t4.ywid");
	sql.append(" left join xg_xtwh_spgwyh t5");
	sql.append(" on t4.gwid = t5.spgw");
	sql.append(" left join xg_xtwh_spgw t6");
	sql.append(" on t4.gwid = t6.id");
	sql.append(" left join yhb t9");
	sql.append(" on t8.zdls = t9.yhm");
	sql.append(" where t5.spyh ='" + user.getUserName() + "' ");
	String shlx = t.getShzt();
	if (!shlx.equals("dsh")) {
		sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
	} else {
		sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
	}
	sql.append(" ) t where 1=1 ");
	sql.append(" and  rn = 1 ");
	sql.append(searchTjByUser);
	sql.append(searchTj);
	sql.append(shgwzByUser);
	return getPageList(t, sql.toString(), inputV);
}



public boolean updateSqjl(String ywid, String shzt) throws Exception{
	String sql = "update xg_ystgl_rtsqb set shzt=?  where rtid = ?";
	
	return dao.runUpdate(sql, new String[]{shzt,ywid});
	
}

public String checkExistForSave2(String xh,String ystid) {
	String flag = "";
	StringBuilder sql = new StringBuilder();
	sql.append("select t.rtid from xg_ystgl_rtjgb t where t.xh = ? and t.ystid = ? ");
	String num = dao.getOneRs(sql.toString(), new String[] {xh,ystid}, "rtid");
	if (num != null && ! num.equals("") ){
		flag = num;
	}
	return flag;
}


public String getRskzXh(String xmdm){
	
	StringBuilder sql = new StringBuilder();
	
	sql.append(" select xh from xg_sztz_xmjg a left join XG_XTWH_SPBZ b on a.splc=b.splc and b.spgw=(replace(a.rskzjb,',','')) where xmdm = ? ");
	return dao.getOneRs(sql.toString(), new String[]{xmdm}, "xh");
}

public HashMap<String, String> getDataById(String xmdm) throws Exception {
	StringBuilder sb = new StringBuilder();
	sb.append(" select * ");
	sb.append(" from  xg_sztz_xmjg ");
	sb.append(" where xmdm=?");
	String[] inputValue = { xmdm };
	return dao.getMapNotOut(sb.toString(), inputValue);
}

public String getTgrs( String gwid, String xmdm,String xn,String xq){
	
	StringBuilder sql = new StringBuilder();
	sql.append(" select count(1) count");
	sql.append("  from (select t1.sqid,");
	sql.append("  t1.xh,");
	sql.append("  t2.shzt,");
	sql.append("  t2.gwid,");
	sql.append("  t1.xn,");
	sql.append("  t1.xq,");
	sql.append("  t1.xmdm,");
	sql.append("  row_number() over(partition by t1.sqid, t2.gwid order by t2.shsj desc) lvl");
	sql.append("  from  xg_sztz_xsxmsq t1");
	sql.append("  left join xg_xtwh_shztb t2");
	sql.append("  on t1.sqid = t2.ywid)");
	sql.append("   where lvl = 1 and shzt = '1' and gwid = ? and xmdm = ? and xn=? and xq=?");
	
	return dao.getOneRs(sql.toString(), new String[]{gwid,xmdm,xn,xq}, "count");
}
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(YstRtshForm.class);
		super.setKey("rtid");
		super.setTableName("xg_ystgl_rtsqb");
	}

}
