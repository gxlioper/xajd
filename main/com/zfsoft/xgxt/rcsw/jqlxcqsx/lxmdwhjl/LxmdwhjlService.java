/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��3��27�� ����1:50:25 
 */  
package com.zfsoft.xgxt.rcsw.jqlxcqsx.lxmdwhjl;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��3��27�� ����1:50:25 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LxmdwhjlService extends SuperServiceImpl<LxmdwhjlForm,LxmdwhjlDao>{

	/** 
	 * @����:����id��ѯ��У����ά����¼��Ϣ��������У������Ϣ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��3��28�� ����5:11:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jlid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getLxmdwhjlById(String jlid) {
		return dao.getLxmdwhjlById(jlid);
	}

}
