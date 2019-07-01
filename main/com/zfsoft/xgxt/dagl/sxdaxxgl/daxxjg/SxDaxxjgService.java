/**
 * @部门:学工产品事业部
 * @日期：2016-8-19 下午05:01:29 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



public class SxDaxxjgService extends SuperServiceImpl <SxDaxxjgForm,SxDaxxjgDao>{
	private SxDaxxjgDao rd = new SxDaxxjgDao();
	public SxDaxxjgService() {
		setDao(rd);
	}
}
