package com.zfsoft.xgxt.dtjs.tyzcgl.tyzc;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * ��Աע��
 */
public class TyzcService extends SuperServiceImpl<TyzcForm, TyzcDao> {

	private TyzcDao dao = new TyzcDao();

	public TyzcService() {
		super.setDao(dao);
	}
	
	/**
	 * ��Աע����Ϣ
	 */
	public HashMap<String, String> getTyzcxx(String xn, String xh) throws Exception{
		return dao.getTyzcxx(xn, xh);
	}
	/**
	 * ����ɾ��
	 */
	public boolean tyzcDelete(String[] xhArr, String[] xnArr) throws Exception{
		return dao.tyzcDelete(xhArr, xnArr);
	}
	/**
	 * ��������
	 */
	public boolean tyzcInsert(String[] xhArr, String[] xnArr, String zcsj, String zcr) throws Exception{
		return dao.tyzcInsert(xhArr, xnArr, zcsj, zcr);
	}
	
}
