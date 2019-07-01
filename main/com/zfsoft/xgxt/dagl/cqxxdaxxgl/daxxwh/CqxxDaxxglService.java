/**
 * @部门:学工产品事业部
 * @日期：2016-8-17 下午02:06:53 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class CqxxDaxxglService extends  SuperServiceImpl<CqxxDaxxglForm,CqxxDaxxglDao>{

	
	public List<HashMap<String, String>> getXsPageList(CqxxDaxxglForm t, User user) 
	throws Exception{
		return dao.getXsPageList(t, user);
	}

	public boolean saveDataXs(CqxxDaxxglForm t, String type)throws Exception {
		return dao.saveDataXs(t, type);
	}

	public boolean isExistQysj(CqxxDaxxglForm myForm) throws Exception{
		String num = dao.checkExistForSave(myForm);
		return Integer.valueOf(num) > 0;
	
	}
	public HashMap<String, String> getBjxx(String bjdm) throws Exception {
		return dao.getBjxx(bjdm);
	}
	
}
