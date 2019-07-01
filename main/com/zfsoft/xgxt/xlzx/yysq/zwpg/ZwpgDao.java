/**
 * @部门:学工产品事业部
 * @日期：2018-3-6 下午05:04:29 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-3-6 下午05:04:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwpgDao extends SuperDAOImpl<ZwpgForm> {

	
	
	@Override
	protected void setTableInfo() {
		super.setKey("id");
		super.setTableName("XLZX_ZWPG");
		
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwpgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwpgForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-19 上午10:16:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * ZwpgForm 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getInfoByXh(String xh) {
		String sql = "select * from (select t.* from XLZX_ZWPG t  where  xh=? order by sj desc) where rownum=1";
		return dao.getMapNotOut(sql, new String[]{xh});
	}


	public  HashMap<String,String> getInfoById(String id) {
		String sql = "select * from XLZX_ZWPG where  id=? ";
		return dao.getMapNotOut(sql, new String[]{id});
	}
}
