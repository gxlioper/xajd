package com.zfsoft.xgxt.xlzx.xlzxnew.xsxlpc;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XsxlpcService extends SuperServiceImpl<XsxlpcForm,XsxlpcDao> {
	/**
	 * 
	 * @����:
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����05:16:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJtqkMap(String xh){
		return dao.getJtqkMap(xh);
	}
	
	/**
	 * 
	 * @����:��֤�Ƿ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-8 ����06:52:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsNotExists(String xh){
		return dao.checkIsNotExists(xh);
	}
	
	/**
	 * 
	 * @����:��עȡ����ע
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-11-9 ����05:03:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ids
	 * @param sfgz
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean sz(String[] ids,String sfgz) throws Exception{
		return dao.sz(ids, sfgz);
	}
}
