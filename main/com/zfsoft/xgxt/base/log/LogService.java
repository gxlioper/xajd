/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-9 ����02:49:03 
 */  
package com.zfsoft.xgxt.base.log;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-9-9 ����02:49:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LogService extends SuperServiceImpl<LogInfo, LogDao> {
	
	/**
	 * 
	 * @����:ͳ�Ƶ�¼��������������¼��־
	 * @���ߣ�taogj[���ţ�1075]
	 * @���ڣ�2017-9-12 ����03:22:30
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean insertDllog() throws Exception{
		return dao.insertDllog();
	}
}
