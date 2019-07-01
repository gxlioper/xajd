/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 下午04:28:56 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2016-05-07 下午04:28:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjyCsszDao extends SuperDAOImpl<HjjyCsszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HjjyCsszForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HjjyCsszForm t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	public HjjyCsszForm getModel() throws Exception{
		String sql = "select * from XG_RCSW_HZSF_HJJYCSSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}

	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from XG_RCSW_HZSF_HJJYCSSZ";
		return dao.runUpdate(sql, new String[]{});
	}
	public List<HashMap<String, String>> getJyyyList() throws Exception{
		String sql = "select *  from XG_RCSW_HZSF_JYYYDMB";
		return dao.getListNotOut(sql, new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(HjjyCsszForm.class);
		super.setKey("id");
		super.setTableName("XG_RCSW_HZSF_HJJYCSSZ");
	}
}
