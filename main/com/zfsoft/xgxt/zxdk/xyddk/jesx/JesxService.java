/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-11-7 ����03:13:46 
 */  
package com.zfsoft.xgxt.zxdk.xyddk.jesx;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-11-7 ����03:13:46 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JesxService extends SuperServiceImpl<JesxForm, JesxDao> {
	/**
	 * 
	 * @����:��ȡ������¼����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-7 ����05:43:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getJesxMap(String xlccdm){
		return dao.getJesxMap(xlccdm);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����:����
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-7 ����05:51:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveRs(String[] xlccdms,String[] jesxs) throws Exception{
		return dao.saveRs(xlccdms, jesxs);
	}
	
	/**
	 * 
	 * @����: �������List
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-11-8 ����09:51:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xlccdms
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getJesxList(String[] xlccdms){
		return dao.getJesxList(xlccdms);
	}

}
