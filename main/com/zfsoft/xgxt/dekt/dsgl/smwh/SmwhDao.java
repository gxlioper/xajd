/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.smwh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class SmwhDao extends SuperDAOImpl<SmwhForm>{

	@Override
	public List<HashMap<String, String>> getPageList(SmwhForm t)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sb.append("select * from (");
		sb.append("select t.*,decode(t.sftj,'1','是','0','否',t.sftj) sftjmc from xg_dekt_smwhb t");
		sb.append(" )  where 1 = 1");
		sb.append(searchTj);
		return getPageList(t, sb.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(SmwhForm t, User user)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setKey("smid");
		super.setTableName("xg_dekt_smwhb");
	}

	public boolean isExist(SmwhForm model) {
		String sql = "select count(1) num from xg_dekt_smwhb where nj = ? and ssm =? and author=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getNj(),model.getSsm(),model.getAuthor()}, "num");
		return Integer.valueOf(num)>0;
	}

	public boolean isExistforUpdate(SmwhForm model) {
		String sql = "select count(1) num from xg_dekt_smwhb where nj = ? and ssm =? and author=? and smid <> ?" ;
		String num = dao.getOneRs(sql, new String[]{model.getNj(),model.getSsm(),model.getAuthor(),model.getSmid()}, "num");
		return Integer.valueOf(num)>0;
	}

}
