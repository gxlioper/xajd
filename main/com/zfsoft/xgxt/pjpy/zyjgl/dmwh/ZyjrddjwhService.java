/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-12-21 ����01:58:37 
 */  
package com.zfsoft.xgxt.pjpy.zyjgl.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-רҵ������-����ά��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2015-12-21 ����01:58:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */


public class ZyjrddjwhService extends SuperServiceImpl<ZyjrddjwhForm, ZyjrddjwhDao>{
	private ZyjrddjwhDao dao = new ZyjrddjwhDao();

	public ZyjrddjwhService() {
		this.setDao(dao);
	}
	
	public boolean checkDjdmExist(String[] ids) {
		return dao.checkDjdmExist(ids);
	}
	
	
	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: XXXX����ģ��
	 * @�๦������: �жϵȼ������Ƿ���� 
	 * @���ߣ� ����[����:1282]
	 * @ʱ�䣺 2015-12-21 ����02:04:34 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean checkIsExist(ZyjrddjwhForm myForm) {
		// TODO �Զ����ɷ������
		return dao.checkIsExist(myForm);
	} 
	
	
	
}
