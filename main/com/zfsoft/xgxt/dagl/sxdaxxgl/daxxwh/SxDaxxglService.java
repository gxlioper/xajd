/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-17 ����02:06:53 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class SxDaxxglService extends  SuperServiceImpl<SxDaxxglForm,SxDaxxglDao>{

	
	public List<HashMap<String, String>> getXsPageList(SxDaxxglForm t, User user) 
	throws Exception{
		return dao.getXsPageList(t, user);
	}

	public boolean saveDataXs(SxDaxxglForm t, String type)throws Exception {
		return dao.saveDataXs(t, type);
	}

	public boolean isExistQysj(SxDaxxglForm myForm) throws Exception{
		String num = dao.checkExistForSave(myForm);
		return Integer.valueOf(num) > 0;
	
	}
	public HashMap<String, String> getBjxx(String bjdm) throws Exception {
		return dao.getBjxx(bjdm);
	}
	
}
