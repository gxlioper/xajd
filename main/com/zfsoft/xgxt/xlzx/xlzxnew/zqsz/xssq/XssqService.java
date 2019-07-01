package com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.xssq;

import java.sql.SQLException;
import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XssqService extends SuperServiceImpl<XssqForm,XssqDao> {
	public boolean checkExist(String xh) throws SQLException{
		int i = dao.checkExist(xh);
		return i >= 1;
	}
	
	/**
	 * 
	 * @描述: 查看
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-11 下午04:59:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh){
		return dao.getModelData(xh);
	}
}
