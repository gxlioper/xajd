/**
 * @部门:学工产品事业部
 * @日期：2015-9-7 下午04:04:59 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： ChenQ[工号:856]
 * @时间： 2015-9-7 下午04:04:59 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class RwfbyCsszDao extends SuperDAOImpl<RwfbyCssz> {

	@Override
	public List<HashMap<String, String>> getPageList(RwfbyCssz t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(RwfbyCssz t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZXDK_RWFBYCSSZB");
		super.setKey("id");
		super.setClass(RwfbyCssz.class);
	}

	
	public RwfbyCssz getModel() throws Exception {
		String sql = "select * from XG_ZXDK_RWFBYCSSZB where rownum=1";
		return super.getModel(sql, new String[]{});
	}
    
	
}
