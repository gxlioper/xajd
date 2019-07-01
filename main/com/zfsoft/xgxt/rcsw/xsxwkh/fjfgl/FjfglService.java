package com.zfsoft.xgxt.rcsw.xsxwkh.fjfgl;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class FjfglService extends SuperServiceImpl<FjfglForm, FjfglDao>{
	FjfglDao dao = new FjfglDao();
	
	
	
	// ±£´æ
	public boolean saveFjfgl(FjfglForm model, User user) throws Exception {
		boolean flag=true;
		if ("save".equalsIgnoreCase(model.getType())) {
			flag=dao.runInsert(model);
		}else{
			flag=dao.runUpdate(model);
		}
	    return flag;
	}
	
}
