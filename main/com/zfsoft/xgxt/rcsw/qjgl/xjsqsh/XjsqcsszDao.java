package com.zfsoft.xgxt.rcsw.qjgl.xjsqsh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class XjsqcsszDao extends SuperDAOImpl<XjsqCsszForm> {

	@Override
	public List<HashMap<String, String>> getPageList(XjsqCsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(XjsqCsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(XjsqCsszForm.class);
		this.setTableName("xg_xjsq_csszb");
		this.setKey("id");
	}
	
	public XjsqCsszForm getModel() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_xjsq_csszb ");
		return getModel(sql.toString(),new String[]{});
		
	}

}
