/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-11 ����02:00:16 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsftj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-11 ����02:00:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsftjService extends SuperServiceImpl<WsftjForm, WsftjDao>{
	private WsftjDao dao = new WsftjDao();
	
	public boolean sfkcx(WsftjForm t){
		return dao.sfkcx(t);
	}
	
	
}
