/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-18 ����11:12:35 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.dmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �����ȼ�ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-18 ����11:12:35 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZpdjService extends SuperServiceImpl<ZpdjModel, ZpdjDao>{

	/**���ȼ����Ʋ�ѯ**/
	public String getCountByDjmc(String djmc){
		return dao.getCountByDjmc(djmc);
	}
}
