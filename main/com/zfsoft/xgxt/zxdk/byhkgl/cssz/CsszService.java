/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-6 ����09:47:22 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: �������� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-6 ����09:47:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CsszService extends SuperServiceImpl<ByhkglCsszForm, CsszDao>{
	
	public ByhkglCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/**
	 * 
	 * @����:��ȡ������˿���
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-6 ����10:18:00
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
