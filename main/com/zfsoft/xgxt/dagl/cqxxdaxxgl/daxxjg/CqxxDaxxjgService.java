/**
 * @部门:学工产品事业部
 * @日期：2016-8-19 下午05:01:29 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



public class CqxxDaxxjgService extends SuperServiceImpl <CqxxDaxxjgForm,CqxxDaxxjgDao>{
	private CqxxDaxxjgDao rd = new CqxxDaxxjgDao();
	public CqxxDaxxjgService() {
		setDao(rd);
	}
}
