/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��1��25�� ����9:04:18 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���Ž�����֯��ϵת������ģ��
 * @�๦������: ��������Service
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��1��25�� ����9:04:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao>{

	/**
	 * @throws Exception  
	 * @����:��ȡ����������Ϣ
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��1��25�� ����10:37:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * JcszForm �������� 
	 * @throws 
	 */
	public JcszForm getModel() throws Exception {
		return dao.getModel();
	}

}
