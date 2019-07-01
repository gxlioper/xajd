/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.sztz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


public class SzxfDao extends SuperDAOImpl<SzxfModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(SzxfModel.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_szxfmx");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SzxfModel t)
			throws Exception {
		
		
		return null;
	}

	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(SzxfModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.xqmc ");
		sql.append("from xg_rcsw_szxfmx t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	public List<HashMap<String, String>> getPageXfhzList(SzxfModel t, User user)
		throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.xqmc ");
		sql.append("from (select t1.xn,t1.xq,t1.xh,sum(xf) tzzf ");
		sql.append("from xg_rcsw_szxfmx t1 group by t1.xn,t1.xq,t1.xh) t1 ");
		sql.append("left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.String)
	 */
	
	@Override
	public SzxfModel getModel(SzxfModel model) throws Exception {
		
		String sql = "select t1.*,t2.xqmc from xg_rcsw_szxfmx t1 left join xqdzb t2 on t1.xq=t2.xqdm where t1.id = ?";
		return super.getModel(sql, new String[]{model.getId()});
	}
	

	/** 
	 * @描述:获取个人学分统计列表(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-12-2 上午09:32:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getPageGrxftjList(SzxfModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from view_sztz_grxftjxx t where 1=1 ");
		//sql.append(" and exists(select 1 from xg_rcsw_szxfmx a where a.xh = t.xh) ");
		sql.append(searchTjByUser);
		sql.append(searchTj);		
		return getPageList(t, sql.toString(), inputV);
	}

	
	

}
