/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午09:04:23 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午09:04:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LxshDao extends SuperDAOImpl<LxshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LxshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t.*,");
		sql.append(" t1.XM,");
		sql.append(" t1.XB,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.NJ,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.zybjmc,");
		sql.append(" t1.zybj,x.sydm,x1.symc,");
		sql.append(" t2.xqmc,");
		sql.append(" ssx.shengmc || ssx.shimc || ssx.xianmc ssxdz,");
		sql.append(" t3.mc lxgj,");
		sql.append(" t4.mc fxgj,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append(" t7.gwz, ");
		sql.append(" row_number() over(partition by t.sqid order by t5.shsj desc) rn ");
		sql.append(" from xg_rcsw_lxqxdj_lxqxdjsqb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join XG_XTWH_SYBJGLB x on t1.bjdm = x.bjdm ");
		sql.append(" left join XG_XTWH_SYDMB x1 on x.sydm = x1.sydm ");
		sql.append(" left join xqdzb t2");
		sql.append(" on t.xq = t2.xqdm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t3");
		sql.append(" on t.lxjtgjdm = t3.dm");
		sql.append(" left join xg_rcsw_lxqxdj_dmwhb t4");
		sql.append(" on t.fxjtgjdm = t4.dm");
		sql.append(" left join xg_view_dmk_qx ssx");
		sql.append(" on ssx.qxdm = t.mddssx");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.sqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on  t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7 on t5.gwid = t7.id");
		sql.append(" where t6.spyh ='" + user.getUserName() + "'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
	    sql.append(" and  rn = 1 ");
	    sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setKey("sqid");
		this.setClass(LxshForm.class);
		this.setTableName("xg_rcsw_lxqxdj_lxqxdjsqb");
	}

}
