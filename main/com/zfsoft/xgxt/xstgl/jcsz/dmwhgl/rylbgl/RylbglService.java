/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-9 ����04:37:53 
 */  
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.rylbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-9-9 ����04:37:53 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class RylbglService extends SuperServiceImpl<RylbglForm, RylbglDao> {
	//��֤���ݿ����Ƿ���ͬ������Ա�������
	public boolean isExistsSameRylbmc(String rylbmc,String rylbdm){
		return dao.isExistsSameRylbmc(rylbmc, rylbdm);
	}
	
	//ɾ����Ա���ʱ�ж������ų�Ա���������ų�Ա��������Ƿ����õ�����������
	public boolean isExistsRylbmc_user(String rylbdm){
		return dao.isExistsRylbmc_user(rylbdm);
	}
	
	//����������Ա���
	public boolean save(String rylbmc) throws Exception{
		return dao.save(rylbmc);
	}
	
	//��ȡ��Ա�������
	public String getRylbmc(String rylbdm){
		return dao.getRylbmc(rylbdm);
	}
}
