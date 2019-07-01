package com.zfsoft.xgxt.rcsw.zxzx.zxhf;


import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class ZxhfService extends SuperServiceImpl<ZxhfForm, ZxhfDao> {

	private ZxhfDao dao = new ZxhfDao();
	
	public ZxhfService() {
		super.setDao(dao);
	}
	/**
	 * ��ѯ�ظ�
	 */
	public boolean insertZxhf(ZxhfForm model) throws Exception {
		boolean result = super.runInsert(model);
		return result;
	}
	/**
	 * �޸���ѯ�ظ�
	 */
	public boolean updateZxhf(ZxhfForm model) throws Exception {
		boolean result = super.runUpdate(model);
		return result;
	}
	/**
	 * ɾ��
	 */
	public boolean deleteZxhf(String values) throws Exception {
		return dao.deleteZxhf(values);
	}
}
