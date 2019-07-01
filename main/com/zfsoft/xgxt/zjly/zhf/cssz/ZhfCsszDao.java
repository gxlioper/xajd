/**
 * @部门:学工产品事业部
 * @日期：2016-6-17 下午01:34:02 
 */  
package com.zfsoft.xgxt.zjly.zhf.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 参数设置(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-6-17 下午01:34:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfCsszDao extends SuperDAOImpl<ZhfCsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfCsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZhfCsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZhfCsszForm.class);
		super.setKey("id");
		super.setTableName("xg_zjlv_zhszf_cssz");		
	}
	
	public ZhfCsszForm getModel() throws Exception{
		String sql = "select * from xg_zjlv_zhszf_cssz";
		return super.getModel(sql, new String[]{});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from xg_zjlv_zhszf_cssz";
		return dao.runUpdate(sql, new String[]{});
	}
	
}
