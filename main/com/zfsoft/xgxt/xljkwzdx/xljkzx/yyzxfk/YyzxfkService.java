/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:36:49 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -ԤԼ��ѯ����
 * @�๦������: 
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:36:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YyzxfkService extends SuperServiceImpl<YyzxfkForm, YyzxfkDao>{

	public YyzxfkService() {
		super.setDao(new YyzxfkDao());
	}
}
