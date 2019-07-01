/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.xgxt.szdw.sygl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class SyglService extends SuperServiceImpl<SyglForm, SyglDao>{

	public boolean isExist(SyglForm myForm) {
		boolean flag = false;
		if("save".equalsIgnoreCase(myForm.getType())) {
			 flag =  dao.isExist(myForm);
		}else{
			 flag = dao.isExistforUpdate(myForm);
		}
		return flag;
	}

	public List<HashMap<String, String>> getBjList(SyglForm t, User user) throws Exception {
		return dao.getBjList(t, user);
	}

	public boolean bjFp(String sydm, String[] bjdmarr) throws Exception {
		return  dao.bjFp(sydm, bjdmarr);
	}

	public boolean bjQxfp(String sydm, String[] bjdmarr) throws Exception {
		return dao.bjQxfp(sydm, bjdmarr);
	}

	public boolean isHaveBj(String sydm) {
		return dao.isHaveBj(sydm);
	}

	public boolean syDelete(String sydm) throws Exception {
		return dao.syDelete(sydm);
	}

}
