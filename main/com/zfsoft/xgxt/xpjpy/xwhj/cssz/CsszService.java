/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-23 ����05:59:20 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������(У���)
 * @�๦������: ��������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-23 ����05:59:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<XwhjCsszForm, CsszDao>{
	
	public XwhjCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @����:��ȡ������˿���
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-23 ����06:25:34
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
}
