/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-21 ����11:49:08 
 */  
package com.zfsoft.xgxt.xsztz.xwtzxmjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2015-7-21 ����11:49:08 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XwTzXmJgService extends SuperServiceImpl<XwTzXmJgForm, XwTzXmJgDao> {
	public HashMap<String, String> getHdMap(XwTzXmJgForm model){
		return dao.getHdMap(model);
	}
	
	public List<HashMap<String, String>> getSsKmList(){
		return dao.getSsKmList();
	}
	
	public List<HashMap<String, String>> getXmJbList(){
		return dao.getXmJbList();
	}
	
	public boolean checkExistForSave(XwTzXmJgForm model) {
		return dao.checkExistForSave(model);
	}
}
