/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:29:30 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2016-1-27 ����10:13:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class CsszService extends SuperServiceImpl<CsszForm, CsszDao> {
	
	public CsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}

}
