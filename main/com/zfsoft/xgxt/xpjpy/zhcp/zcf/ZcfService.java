/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-11 ����03:31:57 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcf;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2016-4-11 ����03:31:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZcfService extends SuperServiceImpl<ZcfForm, ZcfDao> {
	public HashMap<String, String> getZcfInfo(String xn,String xh){
		return dao.getZcfInfo(xn, xh);
	}
	
	public HashMap<String, String> getJkfInfo(String xn,String xh){
		return dao.getJkfInfo(xn, xh);
	}
	
	public List<HashMap<String, String>> getZcfList(String xn,String xh){
		return dao.getZcfList(xn, xh);
	}
}
