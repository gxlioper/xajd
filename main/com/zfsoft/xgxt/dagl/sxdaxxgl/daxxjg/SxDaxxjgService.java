/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-19 ����05:01:29 
 */  
package com.zfsoft.xgxt.dagl.sxdaxxgl.daxxjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



public class SxDaxxjgService extends SuperServiceImpl <SxDaxxjgForm,SxDaxxjgDao>{
	private SxDaxxjgDao rd = new SxDaxxjgDao();
	public SxDaxxjgService() {
		setDao(rd);
	}
}
