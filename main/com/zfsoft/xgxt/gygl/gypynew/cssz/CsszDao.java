package com.zfsoft.xgxt.gygl.gypynew.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class CsszDao extends SuperDAOImpl<CsszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(CsszForm.class);
		this.setKey("id");
		this.setTableName("xg_gygl_new_xjqscsszb");
	}
	
	/**
	 * 
	 * @描述:获取参数设置表里的审批流程
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-24 下午04:34:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getSplc(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_gygl_new_xjqscsszb");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}

}
