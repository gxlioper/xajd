/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-23 ����03:51:57 
 */  
package com.zfsoft.xgxt.xljkwzdx.zcsz.lxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������-��������-����ά��-��������
 * @�๦������: 
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-4-23 ����03:51:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdlxwhService extends SuperServiceImpl<FdlxwhForm, FdlxwhDao>{

	public FdlxwhService() {
		super.setDao(new FdlxwhDao());
	}
	
	/** 
	 * @����:(�������ʹ����Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����06:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean fdlxIsExist(FdlxwhForm model) {
		return dao.fdlxIsExist(model);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:��ȡȫ����������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-3 ����04:05:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllFdlxList() throws Exception{
		return dao.getAllList(new FdlxwhForm());
	}
	
	
	
}
