/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:52:58 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约信息
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-10-10 下午02:52:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyxxDao extends SuperDAOImpl<WyxxModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_wyxx");
		super.setKey("htbh");
		super.setClass(WyxxModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WyxxModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WyxxModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t2.id,t2.xh,t3.xm,t1.htbh,t1.wyxq,t1.bz,");
		sql.append("t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t3.zybj,t3.zybjmc,t3.sydm1 sydm,t3.symc1 symc  ");
		sql.append("from xg_zxdk_wyxx t1 ");
		sql.append("left join xg_zxdk_xydkjgb t2 on t1.htbh = t2.htbh ");
		sql.append("left join view_xsjbxx t3 on t2.xh = t3.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

}
