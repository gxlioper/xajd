/**
 * @部门:学工产品事业部
 * @日期：2016-4-18 下午02:01:07 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-18 下午02:01:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LkxxDao extends SuperDAOImpl<LkxxForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LkxxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LkxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.* from xg_gygl_lkxx t1 ");
		sql.append(" ) t where 1 = 1 ");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(LkxxForm.class);
		super.setKey("id");
		super.setTableName("xg_gygl_lkxx");	
	}
	
	//判断当天是否有住宿登记
	public boolean isHaveRecord(LkxxForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_gygl_lkxx where sfzh = ? and rzsj = ? ");
		if("update".equalsIgnoreCase(t.getType())){
			sql.append(" and id <> '"+ t.getId()+"'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{t.getSfzh(),t.getRzsj()}, "num");
		return Integer.valueOf(num) > 0;
	}
	
}
