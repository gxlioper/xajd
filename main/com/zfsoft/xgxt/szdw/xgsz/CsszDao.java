/**
 * @部门:学工产品事业部
 * @日期：2015-6-23 上午08:52:00 
 */  
package com.zfsoft.xgxt.szdw.xgsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 德育自评参数设置
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-23 上午08:52:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CsszModel.class);
		super.setKey("id");
		super.setTableName("xg_szdw_fdyxx_cssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
			throws Exception {
		return null;
	}
	
	public CsszModel getModel() throws Exception{
		String sql = "select * from xg_szdw_fdyxx_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}

}
