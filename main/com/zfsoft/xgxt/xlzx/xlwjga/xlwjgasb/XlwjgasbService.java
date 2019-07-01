package com.zfsoft.xgxt.xlzx.xlwjga.xlwjgasb;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 心理危机个案
 */
public class XlwjgasbService extends SuperServiceImpl<XlwjgasbForm, XlwjgasbDao>{
	
	private XlwjgasbDao dao = new XlwjgasbDao();
	
	public XlwjgasbService() {
		super.setDao(dao);
	}
	/**
	 * 查询危机程度
	 */
	public List<HashMap<String,String>> getWjcdList() throws Exception {
		return dao.getWjcdList();
	}
	/**
	 * 根据学号查询最新记录
	 */
	public XlwjgasbForm getModelByXh(String xh) throws Exception {
		return dao.getModelByXh(xh);
	}
	/**
	 * 根据学号查询所有记录
	 */
	public List<HashMap<String,String>> getModelListByXh(String xh) throws Exception {
		return dao.getModelListByXh(xh);
	}
	/**
	 * 解除 心理危机个案 
	 */
	public boolean cancelXlwjgasb(String[] idArr) throws Exception{
		return dao.cancelXlwjgasb(idArr);
	}
}
