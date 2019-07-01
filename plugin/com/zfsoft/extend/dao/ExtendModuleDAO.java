/**
 * @部门:学工产品事业部
 * @日期：2015-6-3 上午09:50:23 
 */  
package com.zfsoft.extend.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendModule;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-3 上午09:50:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendModuleDAO extends SuperDAOImpl<ExtendModule> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModule t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModule t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ExtendModule.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_MODULE");
	}

}
