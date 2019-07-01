package com.zfsoft.xgxt.rcsw.hjjygl.hjjyjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class HjjyJgDao extends SuperDAOImpl<HjjyJgForm> {

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HjjyJgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(HjjyJgForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t4.xqmc,case when t1.jyzt='0' then'未归还' else '已归还' end jyztmc,t2.xm,t2.xb,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.sjhm,t2.bjdm,t2.bjmc,t2.nj,t2.mzmc,t2.zzmmmc,t3.jyyymc");
		sql.append(" from XG_RCSW_HZSF_HJJYJGB t1 left join view_xsxxb t2 on t1.xh=t2.xh left join XG_RCSW_HZSF_JYYYDMB t3 on t1.JYYYDM = t3.JYYYDM ");
		sql.append(" left join xqdzb t4 on t1.xq=t4.xqdm) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	public boolean isHaveGhJl(HjjyJgForm model) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1)num from XG_RCSW_HZSF_HJJYJGB where xh=? and jyzt='0' and jgid<> nvl(?,'-1')");
 		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getJgid()}, "num");
		return Integer.parseInt(num)>0;
	}
	@Override
	public HjjyJgForm getModel(HjjyJgForm model) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.xqmc, t3.jyyymc");
		sql.append(" from XG_RCSW_HZSF_HJJYJGB t1 left join XG_RCSW_HZSF_JYYYDMB t3 on t1.jyyydm=t3.jyyydm left join xqdzb t2 on t1.xq=t2.xqdm");
		sql.append(" where t1.jgid=?");
		return super.getModel(sql.toString(),new String[]{model.getJgid()});
	}

	@Override
	protected void setTableInfo() {
		super.setClass(HjjyJgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_RCSW_HZSF_HJJYJGB");

	}
	
}
