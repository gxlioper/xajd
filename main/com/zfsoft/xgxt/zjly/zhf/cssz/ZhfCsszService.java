/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-17 ����01:37:57 
 */  
package com.zfsoft.xgxt.zjly.zhf.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ۺϷֲ�������(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-17 ����01:37:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfCsszService extends SuperServiceImpl<ZhfCsszForm, ZhfCsszDao>{
	/** 
	 * @����:��ȡ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2016-6-17 ����02:02:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * ZhfCsszForm �������� 
	 * @throws 
	 */
	public ZhfCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/** 
	 * @����:ɾ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�982]
	 * @���ڣ�2016-6-17 ����02:02:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean deleteJcsz() throws Exception{
		return dao.deleteJcsz();
	}
}
