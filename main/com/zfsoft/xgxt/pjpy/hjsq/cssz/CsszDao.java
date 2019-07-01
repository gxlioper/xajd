package com.zfsoft.xgxt.pjpy.hjsq.cssz;

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
		this.setTableName("xg_xsxx_new_hjqk_csszb");
	}
	
	public CsszForm getModel() throws Exception{
		String sql = "select * from xg_xsxx_new_hjqk_csszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
