/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午03:25:16 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午03:25:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FbzgDao extends SuperDAOImpl<FbzgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FbzgForm t)
			throws Exception {
		String sql = "select * from XSGGFW_JJFW_FBZGDYB a order by a.fbzgdm";
		return super.getPageList(t, sql, null);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FbzgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(FbzgForm.class);
		super.setKey("FBZGDM");
		super.setTableName("XSGGFW_JJFW_FBZGDYB");
	}

}
