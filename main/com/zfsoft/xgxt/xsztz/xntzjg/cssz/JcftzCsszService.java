/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-28 ����03:25:45 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-28 ����03:25:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzCsszService extends SuperServiceImpl<JcftzCsszForm, JcftzCsszDao>{
	public JcftzCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
	
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
}
