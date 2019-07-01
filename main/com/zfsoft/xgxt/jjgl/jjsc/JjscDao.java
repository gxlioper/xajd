/**
 * @部门:学工产品事业部
 * @日期：2014-8-18 下午02:37:52 
 */  
package com.zfsoft.xgxt.jjgl.jjsc;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import java.util.HashMap;
import java.util.List;

/** 
 * @类功能描述: 家教年级
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-8-18 下午02:37:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JjscDao extends SuperDAOImpl<JjscForm> {


	@Override
	protected void setTableInfo() {
		super.setTableName("XSGGFW_JJFW_JJGSWHB");
		super.setKey("jjbh");
		super.setClass(JjscForm.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjscForm jjscForm) throws Exception {
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(JjscForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql =new StringBuilder();
		sql.append("select * from XSGGFW_JJFW_JJGSWHB");
		sql.append(" where 1 = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return super.getPageList(t, sql.toString(), inputV);
	}

}
