/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-22 ����05:02:28 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-22 ����05:02:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YxybCsszService extends SuperServiceImpl<YxybCsszForm, YxybCsszDao>{
	private YxybCsszDao dao = new YxybCsszDao();
	
	public YxybCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/** 
	 * @����:��ȡ������뿪��
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-22 ����05:05:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * String[] �������� 
	 * @throws 
	 */
	public String[] getSqShKg() throws Exception{
		return dao.getSqShKg();
	}
	
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
}
