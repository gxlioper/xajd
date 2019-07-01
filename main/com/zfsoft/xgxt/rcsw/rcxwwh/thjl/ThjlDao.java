/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.rcxwwh.thjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 日常行为--谈话记录
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-12-1 下午01:35:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ThjlDao extends SuperDAOImpl<ThjlModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ThjlModel.class);
		super.setKey("thid");
		super.setTableName("xg_rcsw_rcthjlb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ThjlModel t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ThjlModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t5.xqmc,t3.xm jsxm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_rcsw_rcthjlb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join yhb t3 on t1.thjs=t3.yhm ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public ThjlModel getModel(ThjlModel t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t1.*,t2.xqmc,t3.xm jsxm from xg_rcsw_rcthjlb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join yhb t3 on t1.thjs = t3.yhm ");
		sql.append("where t1.thid = ?");
		
		return super.getModel(sql.toString(), new String[]{t.getThid()});
	}
	
}
