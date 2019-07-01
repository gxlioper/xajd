/**
 * @部门:学工产品事业部
 * @日期：2015-7-20 下午03:56:08 
 */  
package com.zfsoft.xgxt.xsztz.tzxmsh;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;
import xsgzgl.gygl.xyzsgl.sh.XyzsShForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.xsztz.xwtzxmjg.XwTzXmJgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-20 下午03:56:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsXmShDao extends SuperDAOImpl<XsXmShForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsXmShForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getSearchSztzShTjByUser(user, "t", "sbbmdm", "sbr");
		//String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw", "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t1.*,");
		sql.append(" t2.xm,");
		sql.append(" t2.xb,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.nj,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc,");
		sql.append(" t4.guid shid,");
		sql.append(" t4.gwid,");
		sql.append(" t4.shr,");
		sql.append(" t4.shyj,");
		sql.append(" t7.xqmc,");
		sql.append(" t8.jcxf,t8.sbbmdm,");
		sql.append(" t8.xmkssj,");
		sql.append(" t8.xmmc xmmc1,");
		sql.append(" t9.xmjbdm,");
		sql.append(" t9.xmjbmc,");
		sql.append(" t10.sskmdm,");
		sql.append(" t10.sskmmc,");
		sql.append(" t11.bmmc sbbmmc,");
		sql.append(" t6.mc || '[' ||");
		sql.append(" decode(t4.shzt,");
		sql.append("  '0',");
		sql.append(" '未审核',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '4',");
		sql.append(" '需重审',");
		sql.append(" '5',");
		sql.append(" '审核中') || ']' shztmc,");
		sql.append(" t6.gwz,");		
		sql.append(" t8.xfrdsqzt,");
		sql.append(" t8.xfrdjgzt,");		
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn");
		sql.append(" from xg_sztz_xsxmsq t1");
		sql.append(" left join view_xsjbxx t2");
		sql.append(" on t1.xh = t2.xh");
		sql.append(" left join xqdzb t7");
		sql.append(" on t1.xq = t7.xqdm");
		sql.append(" left join xg_sztz_xmjg t8");
		sql.append(" on t1.xmdm = t8.xmdm");
		sql.append(" left join xg_sztz_xmjb t9");
		sql.append(" on t8.xmjbdm = t9.xmjbdm");
		sql.append(" left join xg_sztz_sskm t10");
		sql.append(" on t8.sskmdm = t10.sskmdm");
		sql.append(" left join zxbz_xxbmdm t11");
		sql.append(" on t8.sbbmdm = t11.bmdm");
		sql.append(" left join xg_xtwh_shztb t4");
		sql.append(" on t1.sqid = t4.ywid");
		sql.append(" left join xg_xtwh_spgwyh t5");
		sql.append(" on t4.gwid = t5.spgw");
		sql.append(" left join xg_xtwh_spgw t6");
		sql.append(" on t4.gwid = t6.id");
		sql.append(" where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		//sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(XsXmShForm.class);
		super.setKey("sqid");
		super.setTableName("xg_sztz_xsxmsq");
	}
	
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_sztz_xsxmsq set shzt=?  where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}
	
	public String checkExistForSave2(String xh,String xq,String xn,String xmdm) {
		String flag = "";
		StringBuilder sql = new StringBuilder();
		sql.append("select t.jgid from xg_sztz_xs_sqjg t where t.xh = ? and t.xn = ? and xq = ? and xmdm = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {xh,xn,xq,xmdm}, "jgid");
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
	    sql.append(" select a.sqs + b.jgs count");
	    sql.append(" from (select count(1) sqs");
	    sql.append(" from (select t1.sqid,");
	    sql.append(" t1.xh,");
	    sql.append(" t2.shzt,");
	    sql.append(" t1.shzt flag,");
	    sql.append(" t2.gwid,");
	    sql.append(" t1.xn,");
	    sql.append(" t1.xq,");
	    sql.append(" t1.xmdm,");
	    sql.append(" row_number() over(partition by t1.sqid, t2.gwid order by t2.shsj desc) lvl");
	    sql.append(" from xg_sztz_xsxmsq t1");
	    sql.append(" left join xg_xtwh_shztb t2");
	    sql.append(" on t1.sqid = t2.ywid)");
	    sql.append(" where lvl = 1");
	    sql.append(" and shzt = '1'");
	    sql.append(" and gwid = ?");
	    sql.append(" and xmdm = ?");
	    sql.append(" and flag != '2' and flag != '3'");//过滤掉不通过，已退回的数据
	    sql.append(" and xn = ?");
	    sql.append(" and xq = ?) a,");
	    sql.append(" (select count(1) jgs");
	    sql.append(" from xg_sztz_xs_sqjg");
	    sql.append(" where xh not in");
	    sql.append(" (select xh");
	    sql.append(" from xg_sztz_xsxmsq");
	    sql.append(" where xmdm = ?)");
	    sql.append(" and xmdm = ?) b");
	    sql.append(" where 1 = 1");
	    sql.append(" ");
	    sql.append(" ");
	    sql.append(" ");
		
		return dao.getOneRs(sql.toString(), new String[]{gwid,xmdm,xn,xq,xmdm,xmdm}, "count");
	}
	

}
