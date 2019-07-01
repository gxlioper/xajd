/**
 * @部门:学工产品事业部
 * @日期：2015-1-22 上午10:58:15 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-22 上午10:58:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CclxwhDao extends SuperDAOImpl<CclxwhForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CclxwhForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CclxwhForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 *查询抽查类型
	 */
	public List<HashMap<String, String>> getCclxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_CCLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 *查询抽查类型byID
	 */
	public HashMap<String, String> getCclxById(String lxdm){
		String sql = "select * from  XG_RCSW_ZWZXKQ_CCLXB where lxdm=?";
		return dao.getMapNotOut(sql, new String[]{lxdm});
	}
	
	public List<HashMap<String, String>> getQqlxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(CclxwhForm.class);
		super.setKey("lxdm");
		super.setTableName("XG_RCSW_ZWZXKQ_CCLXB");
		
	}

}
