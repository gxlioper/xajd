/**
 * @部门:学工产品事业部
 * @日期：2015-1-22 上午10:09:14 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务
 * @类功能描述: 保险管理-学生预约报销信息 
 * @作者： 沈晓波 [工号:1123]
 * @时间： 2015-1-22 上午10:09:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyybxxxDao extends SuperDAOImpl<XsyybxxxForm> {
	
	/*
 	  描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsyybxxxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
             描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyybxxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select t1.yybxid,t1.yysj,t1.blnr,t2.* from xg_rcsw_bxgl_xsyybxxxb t1 left join view_xsbfxx t2 on t1.xh = t2.xh order by yysj desc) a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
		
	}
	
	/*
   	  描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_BXGL_XSYYBXXXB");
		setKey("yybxid");
		setClass(XsyybxxxForm.class);

	}

}
