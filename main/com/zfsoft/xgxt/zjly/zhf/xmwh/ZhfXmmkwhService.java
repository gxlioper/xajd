/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-6-27 ����09:55:38 
 */  
package com.zfsoft.xgxt.zjly.zhf.xmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ۺϷֹ���ģ��
 * @�๦������: ��Ŀά��(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-6-27 ����09:55:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZhfXmmkwhService extends SuperServiceImpl<ZhfXmmkwhForm, ZhfXmmkwhDao>{
	private ZhfXmmkwhDao dao = new ZhfXmmkwhDao();
	
	/** 
	 * @����:��֤�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����10:00:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(ZhfXmmkwhForm t){
		return dao.count(t)>0;
	}
	
	/** 
	 * @����:�Ƿ���ɾ����Ŀģ��(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-6-27 ����03:29:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmmkdms
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isCanDel(String[] xmmkdms){
		return dao.countJfxm(xmmkdms)<1;
	}
	
}
