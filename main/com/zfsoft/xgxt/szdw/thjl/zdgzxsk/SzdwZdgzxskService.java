package com.zfsoft.xgxt.szdw.thjl.zdgzxsk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * 重点关注学生库维护
 */
public class SzdwZdgzxskService extends SuperServiceImpl<SzdwZdgzxskForm, SzdwZdgzxskDao> {
	
	private SzdwZdgzxskDao dao = new SzdwZdgzxskDao();
	
	public SzdwZdgzxskService() {
		super.setDao(dao);
	}

}
