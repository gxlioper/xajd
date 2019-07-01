/**
 * @部门:学工产品事业部
 * @日期：2017-3-29 上午09:42:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.zcxmdj;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-3-29 上午09:42:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcxmdjDao extends SuperDAOImpl<CsszForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_zcxmxxb");
		super.setKey("id");
		super.setClass(CsszForm.class);
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		return null;
	}

	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		return null;
	}
	
	public boolean zcxmDjPlbc(List<String[]> params) throws SQLException {
		String sql = " insert into xg_zjdx_pjpy_zcxmxxb(xmdm,mc,px) values(?,?,?) ";
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/*
	     修改保存操作，先删除同一个代码的相关信息
	 */
	public boolean delZcxmdj(String xmdm) throws Exception {
		String sql = " delete from xg_zjdx_pjpy_zcxmxxb where xmdm = ?";
		return dao.runUpdate(sql, new String[] { xmdm });
	}
}
