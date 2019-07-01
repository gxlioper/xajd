package com.zfsoft.xgxt.xlzx.zxsgly;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;


public class ZxsglyService extends SuperServiceImpl<ZxsglyForm,ZxsglyDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: �������Ա��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-3-17 ����04:34:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zxsform
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveForm(ZxsglyForm zxsform) throws Exception{
		String[] zghs = zxsform.getZghs();
		List<String[]> zghArr = new ArrayList<String[]>();
		boolean rs = true;
		if(zghs == null || zghs.length == 0){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		for(int i=0;i<zghs.length;i++){
			zghArr.add(new String[]{zghs[i]});
		}
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return dao.saveForm(zghArr);
	}

	/**
	 * @����:����ְ�����ж��Ƿ�����ѯʦ����Ա
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��8�� ����1:42:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 * @throws Exception
	 */
	public boolean isZxsGly(String zgh) throws Exception {
		
		return dao.isZxsGly(zgh);
	}
}
