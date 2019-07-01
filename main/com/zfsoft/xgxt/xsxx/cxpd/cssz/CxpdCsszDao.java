/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:48:37 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 操行评定--参数设置 
 * @作者： 屈朋辉[工号:445]
 * @时间： 2015-1-14 下午01:52:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class CxpdCsszDao extends SuperDAOImpl<CxpdCssz> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CxpdCssz.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_cxpdcssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdCssz t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdCssz t, User user)
			throws Exception {
		
		return null;
	}

	public CxpdCssz getModel() throws Exception{
		String sql = "select * from xg_xsxx_cxpdcssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
