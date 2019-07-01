/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.xsxx.djjd.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JddjDmwhDao extends SuperDAOImpl<JddjDmwhModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JddjDmwhModel.class);
		super.setKey("dm");
		super.setTableName("xg_xsxx_jddjdm");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JddjDmwhModel t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_xsxx_jddjdm where 1=1 ");
		
		if (!StringUtil.isNull(t.getLx())){
			sql.append(" and lx = ? ");
			params.add(t.getLx());
		}
		
		if (!StringUtil.isNull(t.getDm())){
			sql.append(" and dm = ? ");
			params.add(t.getDm());
		}
		
		if (!StringUtil.isNull(t.getMc())){
			sql.append(" and mc like '%'||?||'%' ");
			params.add(t.getMc());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JddjDmwhModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_xsxx_jddjdm t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("dm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_xsxx_jddjb t2 where t1.dm=t2.xmdm or t1.dm=t2.jbdm)");
		
		return dao.runDelete(sql.toString(), values);
	}

}
