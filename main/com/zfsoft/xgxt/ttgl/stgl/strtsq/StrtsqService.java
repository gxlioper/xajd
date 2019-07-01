/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.ttgl.stgl.strtsq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class StrtsqService extends SuperServiceImpl<StrtsqForm, StrtsqDao>{

	public boolean rtsq(StrtsqForm model) {
		return false;
	}

	public boolean isExist(StrtsqForm model) {
		return dao.isExist(model);
	}

	public boolean saveRtsq(StrtsqForm model) throws Exception {
		return dao.runInsert(model);
	}

	public HashMap<String, String> getStxxInfo(StrtsqForm model) {
		return dao.getStxxInfo(model);
	}

	public List<HashMap<String,String>> getFzrxx(StrtsqForm myForm) {
		return dao.getFzrxx(myForm);
	}

	public boolean cancelSq(StrtsqForm model) throws Exception {
		return dao.cancelSq(model);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
