/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-4 ����10:19:15 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqjl;

import java.util.Map;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-8-4 ����10:19:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class kqjlSercice extends SuperServiceImpl<kqjlForm, kqjlDao> {
private kqjlDao dao = new kqjlDao();
	
	@SuppressWarnings("deprecation")
	public kqjlSercice(){
		super.setDao(dao);
	}

	/**
	 * @param kqsj  
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2017-8-7 ����04:59:47
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * Map<String,String> �������� 
	 * @throws 
	 */
	public Map<String, String> getKqjl(String xh, String kqsj) {
		// TODO �Զ����ɷ������
		return dao.getKqjl(xh,kqsj);
	}
	
	
	
	
	
	
}
