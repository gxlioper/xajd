/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-21 ����04:14:03 
 */  
package com.zfsoft.xgxt.zxdk.syddk.hkwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-21 ����04:14:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SyddkhkwhService extends SuperServiceImpl<SyddkhkwhForm,SyddkhkwhDao>{
/**
 * @����: ��ȡ��ǰѧ��
 * @���ߣ�����[���ţ�1186]
 * @���ڣ�2016-7-21 ����04:39:53
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param xqdm
 * @return
 * String �������� 
 * @throws
 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
/**
 * @����: Ψһ���ж�
 * @���ߣ� ����[���ţ�1186]
 * @���ڣ�2016-7-21 ����04:41:30
 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
 * @param form
 * @return
 * boolean �������� 
 * @throws
 */
	public boolean isHaveRecord(SyddkhkwhForm form){
		return dao.isHaveRecord(form);
	}
}
