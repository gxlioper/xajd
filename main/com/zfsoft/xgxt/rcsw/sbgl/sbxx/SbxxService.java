/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbxx;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �豸����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:48:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SbxxService extends SuperServiceImpl<SbxxModel, SbxxDao> {

	/**���豸��Ų�ѯ�Ѿ����ڵ�����*/
	public int getSbslByBh(String sbbh){
		return dao.getSbslByBh(sbbh);
	}
}
