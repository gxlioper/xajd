/**
 * @部门:学工产品事业部
 * @日期：2016-8-19 下午05:41:07 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 三峡档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-25 下午06:53:18 
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class SxDaxxjgDao extends SuperDAOImpl<SxDaxxjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxDaxxjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxDaxxjgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from (select a.*, ");
			sql.append(" b.xm, ");
			sql.append(" b.bjmc, ");
			sql.append(" b.bjdm, ");
			sql.append(" b.nj, ");
			sql.append(" b.zydm, ");
			sql.append(" b.zymc, ");
			sql.append(" b.xydm, ");
			sql.append(" b.xymc ");
			sql.append(" from xg_xsxx_cqsxdaxxb a "); 
			sql.append(" left join view_xsbfxx b  "); 
			sql.append("on a.xh = b.xh) t "); 
			sql.append(" where 1 = 1"); 
			sql.append(searchTjByUser);
			sql.append(searchTj);
			return getPageList(t, sql.toString(), inputV);
	}
	
	protected void setTableInfo() {
		super.setTableName("XG_XSXX_CQSXDAXXB");
		super.setKey("daxxid");
		super.setClass(SxDaxxjgForm.class);		
	}
	public SxDaxxjgForm getModel() throws Exception{
		String sql = "select * from XG_XSXX_CQSXDAXXB ";
		return super.getModel(sql, new String[]{});
	}
}
