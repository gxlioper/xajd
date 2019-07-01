/**
 * @部门:学工产品事业部
 * @日期：2016-4-11 下午03:38:39 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcf;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-4-11 下午03:38:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcfDao extends SuperDAOImpl<ZcfForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcfForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.xh,");
		sql.append(" t.jqpjf,");
		sql.append(" t.pjxn xn,");
		sql.append(" t.xnpjcj,");
		sql.append(" t.xnpjcjpm,");
		sql.append(" t.zhszf,");
		sql.append(" t.zhszfpm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xb,");
		sql.append(" t1.xm,");
		sql.append(" t1.nj,");
		sql.append(" t1.zymc from zfsoft_bpmx.xg_view_pjpy_zcfsb t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(") t where 1= 1  ");
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
		
	}
	
	public HashMap<String, String> getZcfInfo(String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from zfsoft_bpmx.xg_view_pjpy_zcfsb");
		sql.append(" where xh = ? and pjxn = ?");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	public HashMap<String, String> getJkfInfo(String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from zjly_qfzh");
		sql.append(" where xh = ? and xn = ?");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,xn});
	}
	
	public List<HashMap<String, String>> getZcfList(String xn,String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_view_rcsw_rcxwjl where xh = ? and xn = ? order by xn,xh");
		return dao.getListNotOut(sql.toString(), new String[]{xh,xn});
	}
}
