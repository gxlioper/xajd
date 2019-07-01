package com.zfsoft.xgxt.rcsw.zxzx.zxbksz;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class ZxbkszService extends SuperServiceImpl<ZxbkszForm, ZxbkszDao> {

	private ZxbkszDao dao = new ZxbkszDao();
	
	public ZxbkszService() {
		super.setDao(dao);
	}
	/**
	 * 是否已存在
	 */
	public boolean checkExistSave(ZxbkszForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * 增加咨询板块
	 */
	public boolean insertZxbksz(ZxbkszForm model) throws Exception {
		boolean result = super.runInsertTrim(model);
		return result;
	}
	/**
	 * 修改咨询板块
	 */
	public boolean updateZxbksz(ZxbkszForm model) throws Exception {
		boolean result = super.runUpdateTrim(model);
		return result;
	}
	/**
	 * 判断是否被用
	 */
	public String checkZxbkszDel(String values)throws Exception{
		return dao.checkZxbkszDel(values);
	}
	/**
	 * 获取使用中的记录
	 */
	public List<HashMap<String, String>> getZxbkszListSyz(String values)throws Exception{
		return dao.getZxbkszListSyz(values);
	}
	/**
	 * 获取所有启用的咨询板块
	 */
	public List<HashMap<String, String>> getAllOkZxbkszList()throws Exception{
		return dao.getAllOkZxbkszList();
	}
}
