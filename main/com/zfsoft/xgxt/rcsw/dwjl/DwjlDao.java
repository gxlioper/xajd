/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:02:27 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 绿色通道结果
 * @作者： cq [工号:785]
 * @时间： 2015-1-21 上午11:02:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DwjlDao extends SuperDAOImpl<DwjlForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DwjlForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DwjlForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select a.*, ");
		sql.append(" b.xm, ");
		sql.append(" b.xymc,b.zymc,b.zzmmmc,b.sjhm, ");
		sql.append(" b.bjmc, ");
		sql.append(" b.xb, ");
		sql.append(" b.nj, ");
		sql.append(" b.zydm,");
		sql.append(" b.bjdm,");
		sql.append(" b.xydm ");	
		sql.append(" from xg_dwjl_dwjljgb a "); 
		sql.append(" left join view_xsbfxx b  "); 
		sql.append("on a.xh = b.xh) t "); 
		sql.append(" where 1 = 1"); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dwjl_dwjljgb");
		super.setKey("id");
	}

	public boolean isExist(DwjlForm model) {
		String sql = "select count(1) num from xg_dwjl_dwjljgb where xh = ? and jlxx = ? and jlkssj=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getJlxx(),model.getJlkssj()}, "num");
		return Integer.valueOf(num)>0;
	}

}
