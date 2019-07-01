package com.zfsoft.xgxt.pjpy.xzhcp;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class ZhcpService extends SuperServiceImpl<ZhcpForm,ZhcpDao> {
	public ZhcpForm getModel() throws Exception{
		return dao.getModel();
	}
}
