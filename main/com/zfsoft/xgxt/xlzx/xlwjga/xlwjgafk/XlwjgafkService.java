package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgafk;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * ����Σ����������
 */
public class XlwjgafkService extends SuperServiceImpl<XlwjgafkForm, XlwjgafkDao>{
	
	private XlwjgafkDao dao = new XlwjgafkDao();
	
	public XlwjgafkService() {
		super.setDao(dao);
	}
	/**
	 * ����ѧ�Ų�ѯ���¼�¼
	 */
	public XlwjgafkForm getModelByXh(String xh) throws Exception {
		return dao.getModelByXh(xh);
	}
	
}
