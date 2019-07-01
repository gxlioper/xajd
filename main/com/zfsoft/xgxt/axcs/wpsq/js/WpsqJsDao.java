/**
 * @部门:学工产品事业部
 * @日期：2014-12-8 上午09:24:25 
 */
package com.zfsoft.xgxt.axcs.wpsq.js;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 爱心超市管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2014-12-8 上午09:24:25
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class WpsqJsDao extends SuperDAOImpl<WpsqJsForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WpsqJsForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(WpsqJsForm t, User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (select t.*,case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen ");
		sql.append(" from VIEW_AXCS_WPSQ t left join xg_xszz_axcsxmb t2 on t.xmdm = t2.xmdm)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 可申请物品信息
	 */
	public List<HashMap<String, String>> getKsqInfoList(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*,t1.mc xmlbmc from xg_xszz_axcsxmb t left join xg_xszz_axcslbb t1 on t.xmlb=t1.dm where not exists(select 1 from xg_axjz_axcswpsqb t2 where t.xmdm=t2.xmdm and t2.xh=?) ");
		sql.append(" and  t.sqkg='1' and (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and xn='" + Base.currXn + "'");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	/**
	 * 已申请物品信息
	 */
	public List<HashMap<String, String>> getYsqInfoList(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.mc xmlbmc  from xg_xszz_axcsxmb t left join xg_xszz_axcslbb t1 on t.xmlb=t1.dm where exists(select 1 from xg_axjz_axcswpsqb t2 where t.xmdm=t2.xmdm and t2.xh=?) ");
		sql.append(" and xn= '" + Base.currXn + "'");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpsqJsForm.class);
		super.setKey("sqid");
		super.setTableName("xg_axjz_axcswpsqb");

	}

}
