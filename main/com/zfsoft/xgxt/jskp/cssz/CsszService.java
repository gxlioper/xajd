package com.zfsoft.xgxt.jskp.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @������ ��ȡ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:26:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param lx
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplc(String lx){
		return dao.getSplc(lx);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-4 ����04:47:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cssz
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveData(CsszForm cssz) throws Exception{
		return dao.saveData(cssz);
	}
	
	/**
	 * @����: ȡ�������ñ��е��Ƿ����
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2017-11-20 ����08:04:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSfsh() throws Exception{
		return dao.getSfsh();
	}
}
