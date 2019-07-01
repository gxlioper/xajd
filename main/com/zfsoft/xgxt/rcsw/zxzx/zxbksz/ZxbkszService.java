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
	 * �Ƿ��Ѵ���
	 */
	public boolean checkExistSave(ZxbkszForm model, User user) throws Exception {
		return dao.checkExistSave(model, user);
	}
	/**
	 * ������ѯ���
	 */
	public boolean insertZxbksz(ZxbkszForm model) throws Exception {
		boolean result = super.runInsertTrim(model);
		return result;
	}
	/**
	 * �޸���ѯ���
	 */
	public boolean updateZxbksz(ZxbkszForm model) throws Exception {
		boolean result = super.runUpdateTrim(model);
		return result;
	}
	/**
	 * �ж��Ƿ���
	 */
	public String checkZxbkszDel(String values)throws Exception{
		return dao.checkZxbkszDel(values);
	}
	/**
	 * ��ȡʹ���еļ�¼
	 */
	public List<HashMap<String, String>> getZxbkszListSyz(String values)throws Exception{
		return dao.getZxbkszListSyz(values);
	}
	/**
	 * ��ȡ�������õ���ѯ���
	 */
	public List<HashMap<String, String>> getAllOkZxbkszList()throws Exception{
		return dao.getAllOkZxbkszList();
	}
}
