package com.zfsoft.xgxt.gygl.gypynew.gypyjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class GypyJgDao extends SuperDAOImpl<GypyJgForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GypyJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GypyJgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		// TODO 自动生成方法存根
		StringBuilder sql = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from (");
		sql.append(" select t.*, t1.ldmc, t1.ch,");
		sql.append(" decode(t.cxzt,");
		sql.append(" '0',");
		sql.append(" '否',");
		sql.append(" '1',");
		sql.append(" '是',");
		sql.append(" t.cxzt) cxztmc");
		sql.append(" from xg_gygl_new_xjqsjgb t");
		sql.append(" left join view_xg_gygl_new_qsxx t1");
		sql.append(" on t.lddm = t1.lddm");
		sql.append("  and t.qsh = t1.qsh");
		sql.append(" ) where 1=1");
		if("stu".equals(user.getUserType())){
			sql.append("  and lddm||qsh in(select lddm||qsh from view_xg_gygl_new_cwxx where xh ='"+user.getUserName()+"') ");
		}
		sql.append(searchTj);
		sql.append(" order by sqsj desc");
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(GypyJgForm.class);
		this.setTableName("xg_gygl_new_xjqsjgb");
		this.setKey("jgid");
	}

}
