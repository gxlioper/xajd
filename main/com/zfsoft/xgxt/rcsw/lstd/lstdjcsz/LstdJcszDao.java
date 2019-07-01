/**
 * @部门:学工产品事业部
 * @日期：2014-11-21 下午05:30:53 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdjcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 绿色通道
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cq [工号:785]
 * @时间： 2014-11-21 下午05:30:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdJcszDao extends SuperDAOImpl<LstdJcszForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdJcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdJcszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setTableName("xg_rcsw_lstd_jcsz");
		super.setClass(LstdJcszForm.class);

	}
	
	
	public LstdJcszForm getModel() throws Exception {
		
		StringBuilder sql = new StringBuilder(" select * from xg_rcsw_lstd_jcsz ");
		return super.getModel(sql.toString(), new String[]{});
	}
	
	/**
	 * 
	 * @描述:TODO(删除参数设置信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:21:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteLstdJcsz(LstdJcszForm model) throws Exception {
		
		boolean flag = false;
		String sql = " delete from xg_rcsw_lstd_jcsz ";
		flag = dao.runUpdate(sql, new String[]{});
		return flag;
	}

}
