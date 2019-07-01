package com.zfsoft.xgxt.rcsw.xsxwkh.fjfgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class FjfglDao extends SuperDAOImpl<FjfglForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(FjfglForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(FjfglForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc");
		sql.append(" from xg_xsxwkh_fjfb t1 left join view_xsxxb t2 on t1.xh=t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	
	@Override
	public FjfglForm getModel(FjfglForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from xg_xsxwkh_fjfb t1");
		sql.append(" where t1.id=?");
		return super.getModel(sql.toString(),new String[]{model.getId()});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(FjfglForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxwkh_fjfb");

	}
	
}
