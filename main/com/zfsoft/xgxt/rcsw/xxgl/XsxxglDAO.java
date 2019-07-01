/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午03:31:59 
 */  
package com.zfsoft.xgxt.rcsw.xxgl;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 学生献血管理DAO
 * @作者： zhangjw 
 * @时间： 2013-4-18 下午03:26:39 
 * @版本： V5.1.75
 */
public class XsxxglDAO extends SuperDAOImpl<XsxxglForm> {

	/*
	 * 描述: 
	 * @param t
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxglForm t)
			throws Exception {
		return null;
	}

	/*
	 * 描述: 高级查询献血列表
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl.getPageList(java.lang.Object, xgxt.form.User) 
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsxxglForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder("");
		sql.append(" select * from (select a.xxgldm,a.xh,b.xm,a.xn,a.xxsj,a.bz,b.xydm,b.zydm,b.bjdm,b.xymc,b.zymc,b.bjmc,b.xb from xg_rcsw_xsxxgl a  left join view_xsxxb b on a.xh = b.xh) a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	 * 描述: 
	 * @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo() 
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_xsxxgl");
		super.setKey("xxgldm");
	}

}
