/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-19 ����01:54:07 
 */  
package com.zfsoft.xgxt.szdw.bjxwjl.bjxwjltj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-19 ����01:54:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class BjxwjltjService extends SuperServiceImpl<BjxwjltjForm, BjxwjltjDao> {

	
	public BjxwjltjService(){
		setDao(new BjxwjltjDao());
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-19 ����04:31:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getbjxx(String bjdm) {
		
		return dao.getbjxx(bjdm);
	}
	
	/**
	 * 
	 * @����:��ȡ�༶��Ϊ��Ϣ�б�
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-19 ����04:49:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bjdm
	 * @param type
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXwxx(String bjdm , String type) {
		
		return dao.getXwxx(bjdm , type);
	}
	
}
