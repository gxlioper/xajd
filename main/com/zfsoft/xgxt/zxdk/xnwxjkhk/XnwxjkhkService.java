/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-18 ����03:51:56 
 */  
package com.zfsoft.xgxt.zxdk.xnwxjkhk;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-18 ����03:51:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XnwxjkhkService extends SuperServiceImpl<XnwxjkhkForm, XnwxjkhkDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	XnwxjkhkDao dao = new XnwxjkhkDao();
	
	/** 
	 * @����:�ж��Ƿ��м�¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-25 ����04:22:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(XnwxjkhkForm form){
		return dao.isHaveRecord(form);
	}
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
		
}
