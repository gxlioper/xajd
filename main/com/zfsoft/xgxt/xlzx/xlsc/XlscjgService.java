/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-21 ����11:51:52 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ����ģ��
 * @�๦������: ����ɸ�鷽����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-21 ����11:51:34 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlscjgService extends SuperServiceImpl<XlscjgForm,XlscjgDao>{
	private XlscjgDao dao = new XlscjgDao();
	
	/**
	 * @����: ����Ψһ���ж�
	 * @���ߣ� Meng.Wei[���ţ�1186]
	 * @���ڣ� 2016-12-21 ����04:50:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws
	 */
	public boolean uniqueness(XlscjgForm model) 
		throws Exception {
		String num = dao.checkForSave(model);
		return Integer.valueOf(num) > 0;
	}
}
