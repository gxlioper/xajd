package com.zfsoft.xgxt.rcsw.gjgl.qjsdxz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

public class QjsdxzService extends SuperServiceImpl<QjsdxzForm,QjsdxzDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����:�������ʱ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2018-1-11 ����03:00:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveQjsdxz(QjsdxzForm form) throws Exception{
		//��ɾ�����ʱ������
		dao.deleteRow();
		boolean rs = dao.runInsertNotCommit(form);
		return rs;
	}
}
