package com.zfsoft.xgxt.pjpy.xzhcp;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jcsz.XyzsJcszForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

public class ZhcpDao extends SuperDAOImpl<ZhcpForm> {

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZhcpForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setClass(ZhcpForm.class);
		this.setKey("sqid");
		this.setTableName("xg_pjpy_new_zhcpdjcsszb");
	}
	
	public ZhcpForm getModel() throws Exception{
		String sql = "select * from xg_pjpy_new_zhcpdjcsszb where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
