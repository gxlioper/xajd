/**
 * @部门:学工产品事业部
 * @日期：2016-8-23 下午03:50:15 
 */  
package com.zfsoft.xgxt.xsxx.qxxscx;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生信息管理模块
 * @类功能描述: 全校学生信息查询，方便浙大老师查询其他学院学生 
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-8-23 下午03:50:15 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QxxscxDao extends SuperDAOImpl<QxxscxForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxxscxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	@Override
	public List<HashMap<String, String>> getPageList(QxxscxForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select xh, xm, xb, sjhm, nj, xymc, zymc, bjmc from view_xsbfxx where sfzx = '在校' or sfzx is null");
		sql.append(" )t where 1= 1 ");
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
}
