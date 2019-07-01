/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.sybx.hsd;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 华师大-商业保险
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HsdSybxDao extends SuperDAOImpl<HsdSybxModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(HsdSybxModel.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_hsdsybx");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HsdSybxModel t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HsdSybxModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_rcsw_hsdsybx t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

}
