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
 * @ģ������: ������-��������-����ά��-������������
 * @�๦������: 
 * @���ߣ���־��[����:1060]
 * @ʱ�䣺 2014-4-23 ����03:51:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlwtlxwhService extends SuperServiceImpl<XlwtlxwhForm, XlwtlxwhDao>{

	public XlwtlxwhService() {
		super.setDao(new XlwtlxwhDao());
	}
	
	/** 
	 * @����:(�����������ʹ����Ƿ����)
	 * @���ߣ���־�� [���ţ�1060]
	 * @���ڣ�2014-4-24 ����06:49:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 */
	public boolean xlwtlxIsExist(XlwtlxwhForm model) {
		return dao.xlwtlxIsExist(model);
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
	public List<HashMap<String, String>> getAllXlwtList() throws Exception{
		return dao.getAllList(new XlwtlxwhForm());
	}
	
}
