/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.dsgl.smwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class SmwhService extends SuperServiceImpl<SmwhForm, SmwhDao>{

	public boolean isExist(SmwhForm model) {
		boolean flag = false;
		if("save".equalsIgnoreCase(model.getType())) {
			 flag =  dao.isExist(model);
		}else{
			 flag = dao.isExistforUpdate(model);
		}
		return flag;
	}



	
	
	
	
}
