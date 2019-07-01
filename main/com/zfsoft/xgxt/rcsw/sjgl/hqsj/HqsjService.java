/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-21 ����02:53:18 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-21 ����02:53:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HqsjService extends SuperServiceImpl<HqsjForm, HqsjDao> {
	HqsjDao dao = new HqsjDao();

	public HqsjService() {
		super.setDao(dao);
	}
	
	//����
	public boolean saveHqsj(HqsjForm model) throws Exception{
		boolean result = dao.runInsert(model);
		return result;
	}
	
	//�޸ı���
	public boolean updateHqsj(HqsjForm model) throws Exception{
		boolean updateResult = dao.runUpdate(model);
		return updateResult;
	}
	
	//ɾ��
	public int delHqsj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	//�жϼ�¼�ڸ�ѧ���ѧ���Ƿ����
	public boolean checkExist(HqsjForm form) {
		boolean flag = false;
		if("save".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForSave(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
		else if("update".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForUpdate(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}		
		return flag;
	}
}
