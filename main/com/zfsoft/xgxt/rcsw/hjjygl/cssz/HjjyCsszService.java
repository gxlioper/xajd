/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-05-07����04:29:30 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.cssz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2016-05-07����04:29:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjyCsszService extends SuperServiceImpl<HjjyCsszForm, HjjyCsszDao> {
	
	public HjjyCsszForm getModel() throws Exception{
		return dao.getModel();
	}

	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
	public List<HashMap<String, String>> getJyyyList() throws Exception{
		return dao.getJyyyList();
	}


}
