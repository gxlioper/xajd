/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:12:01 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����02:12:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszService extends SuperServiceImpl<JcszForm,JcszDao>{
	
	public JcszForm getModel() throws Exception{
		return dao.getModel();
	}
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-1-7 ����02:54:59
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
