/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-6 ����04:29:02 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.xskqgl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ڹ���ģ��
 * @�๦������: ѧ�����ڹ���
 * @���ߣ� �ո־�[����:1075]
 * @ʱ�䣺 2014-6-6 ����04:29:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KqglService extends SuperServiceImpl<KqglForm, KqglDao> {
	
	private KqglDao dao = new KqglDao();
	
	public KqglService(){
		super.setDao(dao);
	}
	
	
	/**
	 * 
	 * @����:�ж��Ƿ��Ѵ��ڿ��ڵǼǱ��У�������ѧ�ţ�����ʱ�䣬�������ͣ�
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:03:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExistByKqdj(KqglForm form) 
			throws Exception {
		
		if("save".equalsIgnoreCase(form.getType())){
			//�����ж�
			String num = dao.checkExistForSave(form);
			return Integer.valueOf(num) > 0;
		}else {
			//�޸��ж�
			String num = dao.checkExistForUpdate(form);
			return Integer.valueOf(num) > 0;
		}
	}
	
	
	/**
	 * 
	 * @����:���ڵǼǽ�������鿴
	 * @���ߣ��ո־�[���ţ�1075]
	 * @���ڣ�2014-6-9 ����10:05:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param guid
	 * @return
	 * Map<String,String> �������� 
	 * @throws
	 */
	public Map<String, String> getOneKqdjList(String kqdjid) {
		
		return dao.getOneKqdjList(kqdjid);
	}
}
