/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014��6��11�� ����3:18:32 
 */  
package com.zfsoft.xgxt.base.service;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.model.SuperAuditModel;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014��6��11�� ����3:18:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public abstract class SuperAuditService<T extends SuperAuditModel, D extends SuperDAOImpl<T>> extends SuperServiceImpl<T,D>{
	/**
	 * 
	 * @����: ���һ�����ͨ���������һ�����ڲ������⣩
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��10�� ����2:13:20
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public abstract boolean afterLastAudit(T model);
	
	
	
	/**
	 * 
	 * @����: ȡ�����--ɾ����������
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014��6��10�� ����2:32:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public abstract boolean deleteResult(T model);
	
	
	
	
}
