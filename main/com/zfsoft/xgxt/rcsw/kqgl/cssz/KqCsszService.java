/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-10-26 ����09:33:58 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����_�������_����ģ��
 * @�๦������:  ��������Dao
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2016-10-26 ����09:33:58 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqCsszService extends SuperServiceImpl<KqCsszForm, KqCsszDao> {
	
	public KqCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2016-10-26 ����09:41:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}

}
