/**
 * @部门:学工产品事业部
 * @日期：2014年6月9日 下午1:51:34 
 */  
package com.zfsoft.xgxt.comm.shlc.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.comm.shlc.model.CsszModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 通用申请审核-参数设置
 * @作者： 屈朋辉[工号:445]
 * @时间： 2014年6月9日 下午1:51:34 
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
		super.setTableName("xg_xtwh_sqsh_cssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
			throws Exception {
		return null;
	}

}
