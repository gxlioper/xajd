/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-19 ����05:01:29 
 */  
package com.zfsoft.xgxt.dagl.cqxxdaxxgl.daxxjg;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



public class CqxxDaxxjgService extends SuperServiceImpl <CqxxDaxxjgForm,CqxxDaxxjgDao>{
	private CqxxDaxxjgDao rd = new CqxxDaxxjgDao();
	public CqxxDaxxjgService() {
		setDao(rd);
	}
}
