package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * �ص��עѧ����ά��
 */
public class SzdwZdgzxskService extends SuperServiceImpl<SzdwZdgzxskForm, SzdwZdgzxskDao> {
	
	private SzdwZdgzxskDao dao = new SzdwZdgzxskDao();
	
	public SzdwZdgzxskService() {
		super.setDao(dao);
	}

}
