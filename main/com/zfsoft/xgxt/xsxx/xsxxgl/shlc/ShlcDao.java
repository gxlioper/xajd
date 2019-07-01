/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.shlc;

import java.util.HashMap;
import java.util.List;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称:学生信息 
 * @类功能描述: 修改审核流程
 * @作者： ligl 
 * @时间： 2013-12-11 下午01:35:38 
 * @版本： V1.0
 * @修改记录:
 */
public class ShlcDao extends SuperDAOImpl<ShlcModel> {

	@Override
	protected void setTableInfo() {
		this.setKey("");
		this.setTableName("");
		this.setClass(ShlcModel.class);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ShlcModel t)
			throws Exception {
		return null;
	}

	/*
	 * 描述: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object,
	 * xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ShlcModel t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	public List<HashMap<String,String>> getShYjForDjb(String ywid){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xtwh_shztb where shzt = '1' and ywid = ? order by shsj asc");
		return dao.getListNotOut(sql.toString(), new String[]{ywid});
	}

}
