/**
 * @部门:学工产品事业部
 * @日期：2016-2-29 上午11:03:30 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-2-29 上午11:03:30 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setKey("id");
		super.setTableName("XG_GYGL_NEW_ZZDSHL");		
	}
	
	public CsszForm getModel() throws Exception{
		String sql = "select * from XG_GYGL_NEW_ZZDSHL";
		return super.getModel(sql, new String[]{});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from XG_GYGL_NEW_ZZDSHL";
		return dao.runUpdate(sql, new String[]{});
	}
	
	
}
