/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-25 ����03:42:09 
 */  
package com.zfsoft.xgxt.szdw.gzjl.jcsz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-6-25 ����03:42:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GzjlJcszService extends SuperServiceImpl<GzjlJcszForm, GzjlJcszDao> {
	public GzjlJcszForm getModel() throws Exception{
		return dao.getModel();
	}
	/**
	 * 
	 * @����:��ȡ������˿���״̬
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2015-6-25 ����03:49:11
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
