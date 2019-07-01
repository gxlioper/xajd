/**
 * @部门:学工产品事业部
 * @日期：2017-9-23 上午11:06:15 
 */  
package com.zfsoft.xgxt.xszz.knsjgwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-9-23 上午11:06:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class knsjgwhDao extends SuperDAOImpl<knsjgwhForm>{
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(knsjgwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.nd,a.xh,a.zf,(case when length(a.zt)>10 then substr(a.zt,0,10)||'...' else a.zt end) zt,b.xm,b.bjdm,b.bjmc,'"+Base.xxmc+"' as xx,b.xydm,b.xymc,b.zydm,b.zymc,b.nj,b.sfzh,b.xb,b.sjhm,b.yhmc,b.yhkh,b.zzmmmc,b.mz");
		sql.append(" from xg_xszz_knsdcjgwhb a left join view_xsjbxx b on a.xh = b.xh");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by xydm,nj,zydm,bjdm ");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_knsdcjgwhb");
		super.setKey("id");
		super.setClass(knsjgwhForm.class);
		
	}
	@Override
	public List<HashMap<String, String>> getPageList(knsjgwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
}
