package com.zfsoft.xgxt.rcsw.zxzx.xszxzx;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.zxzx.zxhf.ZxhfForm;
import com.zfsoft.xgxt.rcsw.zxzx.zxhf.ZxhfService;

public class XszxzxService extends SuperServiceImpl<XszxzxForm, XszxzxDao> {

	private XszxzxDao dao = new XszxzxDao();
	
	public XszxzxService() {
		super.setDao(dao);
	}
	/**
	 * 增加在线咨询
	 */
	public boolean insertXszxzx(XszxzxForm model, User user) throws Exception {
		boolean result = super.runInsert(model);
		return result;
	}
	/**
	 * 修改在线咨询
	 */
	public boolean updateXszxzx(XszxzxForm model, User user) throws Exception {
		boolean result = super.runUpdate(model);
		return result;
	}
	/**
	 * 删除在线咨询
	 */
	public boolean deleteXszxzx(String values) throws Exception {
		return dao.deleteXszxzx(values);
	}
}
