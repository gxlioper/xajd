package com.zfsoft.xgxt.rcsw.hjjygl.hjjyjg;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

public class HjjyJgService extends SuperServiceImpl<HjjyJgForm, HjjyJgDao>{
	HjjyJgDao dao = new HjjyJgDao();
	
	
	
	// ����
	public boolean saveHjjyJg(HjjyJgForm model, User user) throws Exception {
		boolean flag=true;
		if ("save".equalsIgnoreCase(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
			flag=dao.runInsert(model);
		}else{
			flag=dao.runUpdate(model);
		}
	    return flag;
	}
	
	/**
	 * �ж��Ƿ��������¼
	 */
	public boolean isHaveGhJl(HjjyJgForm model) throws Exception {
		return dao.isHaveGhJl(model);
	}

	
	
}
