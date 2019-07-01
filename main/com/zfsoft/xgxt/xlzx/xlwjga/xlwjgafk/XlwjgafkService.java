package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgafk;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 心理危机个案反馈
 */
public class XlwjgafkService extends SuperServiceImpl<XlwjgafkForm, XlwjgafkDao>{
	
	private XlwjgafkDao dao = new XlwjgafkDao();
	
	public XlwjgafkService() {
		super.setDao(dao);
	}
	/**
	 * 根据学号查询最新记录
	 */
	public XlwjgafkForm getModelByXh(String xh) throws Exception {
		return dao.getModelByXh(xh);
	}
	
}
