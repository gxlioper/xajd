package com.zfsoft.xgxt.demo;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/**
 * 
 * ����ɾ���顢��ʾ��service
 * 
 * @author qph
 * 213-4-8
 */
public class SimpleService extends SuperServiceImpl<SimpleForm, SimpleDao>{

	private SimpleDao dao = new SimpleDao();
	
	public SimpleService(){
		super.setDao(dao);
	}
}
