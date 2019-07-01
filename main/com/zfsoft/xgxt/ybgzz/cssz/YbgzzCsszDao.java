/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:48:37 
 */  
package com.zfsoft.xgxt.ybgzz.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @类功能描述: 易班工作站-参数设置 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-1-28 下午02:27:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YbgzzCsszDao extends SuperDAOImpl<YbgzzCssz> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(YbgzzCssz.class);
		super.setKey("id");
		super.setTableName("xg_ybgzz_cssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbgzzCssz t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbgzzCssz t, User user)
			throws Exception {
		
		return null;
	}

	public YbgzzCssz getModel() throws Exception{
		String sql = "select * from xg_ybgzz_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
