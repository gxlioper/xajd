/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午04:00:27 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午04:00:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QqlxwhDao extends SuperDAOImpl<QqlxwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QqlxwhForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QqlxwhForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	public List<HashMap<String, String>> getQqlxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(QqlxwhForm.class);
		super.setKey("lxdm");
		super.setTableName("XG_RCSW_ZWZXKQ_QQLXB");
		
	}

}
