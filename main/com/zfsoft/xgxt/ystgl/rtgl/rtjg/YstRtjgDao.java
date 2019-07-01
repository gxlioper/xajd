/**
 * @部门:学工产品事业部
 * @日期：2015-8-7 上午10:41:27 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：xiaxia[工号:1104]
 * @时间：2016-02-18 上午10:41:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YstRtjgDao extends SuperDAOImpl<YstRtjgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YstRtjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YstRtjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t5.ystxmmc, t3.xmlbdm, t2.xm,t2.zydm, t2.zymc, t2.bjdm, t2.bjmc,");
		sql.append("t2.xydm, t2.xymc, t2.nj, t2.xb, t3.xmlbmc, t4.ystlbdm, t4.ystlbmc,");
		sql.append("t5.zdls, t5.xn, t5.jtr, t5.fzr, t5.kssj, t5.jssj, ");
		sql.append("t6.xm zdlsxm from xg_ystgl_rtjgb t1");
		sql.append(" left join view_xsbfxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join XG_YSTGL_YSTJGB t5");
		sql.append(" on t1.ystid = t5.ystid");
		sql.append(" left join XG_YSTGL_XMLB t3");
		sql.append(" on t5.xmlbdm = t3.xmlbdm");
		sql.append(" left join yhb t6 on t5.zdls = t6.yhm");
		sql.append(" left join XG_YSTGL_YSTLB t4");
		sql.append(" on t3.ystlbdm = t4.ystlbdm");
		sql.append(" order by t1.tnzt desc,t1.rtsj desc)t where 1= 1");
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
		super.setClass(YstRtjgForm.class);
		super.setKey("rtid");
		super.setTableName("xg_ystgl_rtjgb");
	}
	
	//获取社团信息用于社团成员信息维护以及查看
	public HashMap<String, String> getYstxxMap(YstRtjgForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.rtid,t.xh,t.rtxn,t.rtxq,t.sqly,t.tc,t1.*,t2.ystlbmc,t3.xmlbmc,t4.xm jtrxm,t5.xm stfzrxm,t6.xm zdlsxm,t7.bmmc ssbmmc,t8.zcmc ");
		sql.append(",t9.bmdm ssbm,t9.lxdh zdlslxfs,t10.gkdwmc ");
		sql.append(" from xg_ystgl_rtjgb t left join");
		sql.append(" XG_YSTGL_YSTJGB t1  on t.ystid=t1.ystid left join XG_YSTGL_YSTLB t2 on t1.ystlbdm=t2.ystlbdm");
		sql.append(" left join XG_YSTGL_XMLB t3 on t1.xmlbdm=t3.xmlbdm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb ) t4 on t1.jtr=t4.yhm ");
		sql.append(" left join (select xh yhm,xm from xsxxb union select yhm,xm from yhb) t5 on t1.fzr=t5.yhm");
		sql.append(" left join yhb t6 on t1.zdls = t6.yhm ");
		sql.append(" left join zcb t8 on t1.zdlszc = t8.zcdm");
		sql.append(" left join fdyxxb t9 on t1.zdls=t9.zgh");
		sql.append(" left join zxbz_xxbmdm t7 on  t9.bmdm = t7.bmdm");
		sql.append(" left join XG_YSTGL_GKDW t10 on t1.gkdwdm=t10.gkdwdm");
		sql.append(" where t.rtid=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getRtid()});
	}
	
	//获取社团成员信息（如果usertype = stu,只能查看本人的信息）
	public List<HashMap<String, String>> getStxxCylist(YstRtjgForm YstRtjg,User user){
		StringBuilder sql = new StringBuilder();
		String[]inputValue = new String[]{YstRtjg.getYstid()};
		sql.append("select t1.xh,");
		sql.append("t1.rtid,");
		sql.append("t1.sjly,");
		sql.append("t2.xm,");
		sql.append("t2.xb,");
		sql.append("t2.bjmc,");
		sql.append("t2.xymc,");
		sql.append("t1.sqly,");
		sql.append("t1.tc,");
		sql.append("t1.rylbdm");
		sql.append(" from xg_ystgl_rtjgb t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" where t1.stid = ? ");
		if(user.getUserType().equalsIgnoreCase("stu") || (YstRtjg.getRtid() != null &&  !YstRtjg.getRtid().equals("") )  ){
			sql.append(" and  t1.rtid = ? ");
			inputValue = new String[]{YstRtjg.getYstid(),YstRtjg.getRtid()};
		}
		return dao.getListNotOut(sql.toString(), inputValue);
	}
	
	public String getSqShKg(String stid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg ");
		sql.append(" from XG_YSTGL_YSTJGB t where 1=1 and t.stid = ?");
		return dao.getOneRs(sql.toString(),new String[]{stid},"sqkg");
	}
	
	
	
	//更新成员数量方法(add) by yxy 
	public boolean saveAddRtCySl(String stid,String num) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_YSTGL_YSTJGB set cysl = nvl(cysl,0)+?  where stid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{num,stid});
	}
	
	//更新成员数量方法(del) by yxy 
	public boolean saveDelRtCySl(String stid,String num) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_YSTGL_YSTJGB set cysl = nvl(cysl,0)-?  where stid = ? ");
		return dao.runUpdate(sql.toString(), new String[]{num,stid});
	}
	
}
