/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-20 ����04:01:44 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.kqsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.rcsw.sjgl.twsj.TwsjForm;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-20 ����04:01:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqsjService extends SuperServiceImpl<KqsjForm, KqsjDao>{
	KqsjDao dao = new KqsjDao();

	public KqsjService() {
		super.setDao(dao);
	}
	
	//����
	public boolean saveKqsj(KqsjForm model) throws Exception{
		boolean result = dao.runInsert(model);
		return result;
	}
	
	//�޸ı���
	public boolean updateKqsj(KqsjForm model) throws Exception {
		boolean updateResult = dao.runUpdate(model);
		return updateResult;
	}
	
	//ɾ��
	public int delKqsj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	//�жϼ�¼�ڸ�ѧ���Ƿ����
	public boolean checkExist(KqsjForm form) {
		boolean flag = false;
		if("save".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForSave(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
		else if ("update".equalsIgnoreCase(form.getType())){
			String num = dao.checkExistForUpdate(form);
			if(!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}		
		return flag;
	}
	
	
}
