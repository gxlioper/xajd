package com.zfsoft.xgxt.gygl.gypynew.cssz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	/**
	 * 
	 * @����:��ȡ�������ñ������������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-24 ����04:34:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getSplc(){
		return dao.getSplc();
	}
}
