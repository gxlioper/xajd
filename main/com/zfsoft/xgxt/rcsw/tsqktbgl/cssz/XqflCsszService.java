/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����09:26:46 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.cssz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-17 ����09:26:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XqflCsszService extends SuperServiceImpl<XqflCsszForm, XqflCsszDao>{
	private XqflCsszDao dao = new XqflCsszDao();
	
	public XqflCsszForm getModel() throws Exception{
		return dao.getModel();
	}
	
	/** 
	 * @����:��ȡ������˿���
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-17 ����09:29:25
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
