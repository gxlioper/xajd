/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-22 ����08:59:54 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.xyddkhkwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-У԰�أ����ң�����-����ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� MengWei[����:1186]
 * @ʱ�䣺 2016-7-22 ����08:59:54 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XyddkhkwhService extends SuperServiceImpl<XyddkhkwhForm,XyddkhkwhDao>{
	/**
	 * @����: ��ȡ��ǰѧ��
	 * @���ߣ� ����[���ţ�1186]
	 * @���ڣ�2016-7-22 ����09:34:57
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
	 * @���ڣ�2016-7-22 ����09:35:13
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
		public boolean isHaveRecord(XyddkhkwhForm form){
			return dao.isHaveRecord(form);
		}
	}