/**
 * @部门:学工产品事业部
 * @日期：2014-9-24 上午09:48:37 
 */  
package com.zfsoft.xgxt.xsxx.djjd.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 技术等级鉴定 --参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014-11-11 下午04:16:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class DjjdCsszDao extends SuperDAOImpl<DjjdCssz> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(DjjdCssz.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_jddjcssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DjjdCssz t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DjjdCssz t, User user)
			throws Exception {
		
		return null;
	}

	public DjjdCssz getModel() throws Exception{
		String sql = "select * from xg_xsxx_jddjcssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
