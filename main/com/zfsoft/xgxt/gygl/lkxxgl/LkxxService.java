/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-18 ����02:14:57 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-18 ����02:14:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LkxxService extends SuperServiceImpl<LkxxForm, LkxxDao>{
	private LkxxDao dao = new LkxxDao();
	
	/** 
	 * @����:�жϵ����Ƿ���ס�޼�¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-18 ����03:55:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(LkxxForm t){
		return dao.isHaveRecord(t);
	}
}
