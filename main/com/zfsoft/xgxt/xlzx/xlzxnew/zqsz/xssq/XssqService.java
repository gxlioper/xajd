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
	 * @����: �鿴
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-11 ����04:59:44
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String,String> getModelData(String xh){
		return dao.getModelData(xh);
	}
}
