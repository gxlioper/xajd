/**
 * @部门:学工产品事业部
 * @日期：2013-7-2 下午06:53:42 
 */  
package com.zfsoft.xgxt.szdw.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:参数设置 DAO
 * @作者： zhangjw
 * @时间： 2013-7-2 下午06:53:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwCsszDAO extends SuperDAOImpl<SzdwCsszForm>{

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwCsszForm t)
			throws Exception {
		return null;
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SzdwCsszForm t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述:
	 */
	
	@Override
	public SzdwCsszForm getModel(String keyValue) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*, case when kg=1 and sysdate between to_date(nvl(kssj,'1990-01-01'),'yyyy-mm-dd') ");
		sql.append("and to_date(nvl(jssj,'2020-01-01'),'yyyy-mm-dd')+1 then 'true' else 'false' end isopen from xg_xtwh_shlcsqkzb a ");
		sql.append("where a.key = ? ");
		return super.getModel(sql.toString(), new String[] { keyValue });
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xtwh_shlcsqkzb");
		super.setKey("key");
		super.setClass(SzdwCsszForm.class);
	}

}
