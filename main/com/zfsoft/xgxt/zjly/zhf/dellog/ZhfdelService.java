/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-3-21 ����09:30:14 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�CP[����:1352]
 * @ʱ�䣺 2017-3-21 ����09:30:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfdelService extends SuperServiceImpl<ZhfdelForm, ZhfdelDao>{
private ZhfdelDao dao = new ZhfdelDao();
public ZhfdelService() {
	setDao(dao);
}

}
