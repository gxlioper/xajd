/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:48:42 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.zbjg;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �����ȼ��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:48:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZbjgService extends SuperServiceImpl<ZbjgModel, ZbjgDao> {

	
	/**
	 * 
	 * @����: ���������б�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2015-3-20 ����04:23:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportList(ZbjgModel t, User user) throws Exception{
		
		return dao.getExportList(t, user);
	}
}
