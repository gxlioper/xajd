/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����04:01:19 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����04:01:19 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QqlxwhService extends SuperServiceImpl<QqlxwhForm, QqlxwhDao>{
	
	private QqlxwhDao qqlxDao = new QqlxwhDao();
	
	/**
	 *��ѯȱ�������б�
	 */
	public List<HashMap<String, String>> getQqlxList(){
		return qqlxDao.getQqlxList();
	}

}
