/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-1-18 ����06:07:07 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.twsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.pjpy.zyjgl.grzyjwh.GrzyjwhForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-1-18 ����06:07:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TwsjService extends SuperServiceImpl<TwsjForm, TwsjDao>{
	TwsjDao dao = new TwsjDao();

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public TwsjService() {
		super.setDao(dao);
	}
	
	//����
	public boolean saveTwsj(TwsjForm model) throws Exception{
		boolean result = dao.runInsert(model);
		return result;
	}
	
	//�޸ı���
	public boolean updateTwsj(TwsjForm model) throws Exception {
		boolean updateResult = dao.runUpdate(model);
		return updateResult;
	}
	
	//ɾ��
	public int delTwsj(String[] ids) throws Exception {
		return runDelete(ids);
	}
	
	//�жϼ�¼�ڸ�ѧ���Ƿ����
	public boolean checkExist(TwsjForm form) {
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
