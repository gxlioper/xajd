package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgasb;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * ����Σ������
 */
public class XlwjgasbService extends SuperServiceImpl<XlwjgasbForm, XlwjgasbDao>{
	
	private XlwjgasbDao dao = new XlwjgasbDao();
	
	public XlwjgasbService() {
		super.setDao(dao);
	}
	/**
	 * ��ѯΣ���̶�
	 */
	public List<HashMap<String,String>> getWjcdList() throws Exception {
		return dao.getWjcdList();
	}
	/**
	 * ����ѧ�Ų�ѯ���¼�¼
	 */
	public XlwjgasbForm getModelByXh(String xh) throws Exception {
		return dao.getModelByXh(xh);
	}
	/**
	 * ����ѧ�Ų�ѯ���м�¼
	 */
	public List<HashMap<String,String>> getModelListByXh(String xh) throws Exception {
		return dao.getModelListByXh(xh);
	}
	/**
	 * ��� ����Σ������ 
	 */
	public boolean cancelXlwjgasb(String[] idArr) throws Exception{
		return dao.cancelXlwjgasb(idArr);
	}
}
