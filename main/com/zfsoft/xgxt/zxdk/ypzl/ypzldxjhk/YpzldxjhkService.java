/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-3 ����11:07:57 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.ypzldxjhk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ƽ������ѧ�𻹿���Ϣά��
 * @�๦������: ��ƽ������ѧ�𻹿���Ϣά�� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-3 ����11:07:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzldxjhkService extends SuperServiceImpl<YpzldxjhkForm, YpzldxjhkDao>{
	
	/**
	 * 
	 * @����: ��ȡ��ǰѧ��
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����08:30:14
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
	 * 
	 * @����: Ψһ���ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-5-4 ����08:59:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecord(YpzldxjhkForm form){
		return dao.isHaveRecord(form);
	}

}
