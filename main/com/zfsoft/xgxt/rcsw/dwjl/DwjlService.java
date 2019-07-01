/**
 * @部门:学工产品事业部
 * @日期：2015-1-21 上午11:03:03 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @className	： DwjlService
 * @description	： TODO(描述这个类的作用)
 * @author 		：CP（1352）
 * @date		： 2017-11-20 上午10:36:02
 * @version 	V1.0
 */

public class DwjlService extends SuperServiceImpl<DwjlForm, DwjlDao> {
	
	private DwjlDao dao = new DwjlDao();
	public static String _BCZSCID="-1";
	
	public DwjlService() {
		super.setDao(dao);
	}
	
	
	
	
	public boolean isExist(DwjlForm model) {
		// TODO Auto-generated method stub
		return dao.isExist(model);
	}
//	public boolean saveInfo(DwjlForm model) throws Exception {
//		// TODO Auto-generated method stub
//		return dao.saveInfo(model);
//	}
}
