/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-22 ����10:57:45 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-22 ����10:57:45 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CclxwhService extends SuperServiceImpl<CclxwhForm, CclxwhDao>{
	private CclxwhDao cclxwhDao = new CclxwhDao();
	/**
	 *��ѯ��������б�
	 */
	public List<HashMap<String, String>> getCclxList(){
		return cclxwhDao.getCclxList();
	}
	
	/**
	 *��ѯ�������
	 */
	public HashMap<String, String> getCclxById(String lxdm){
		return cclxwhDao.getCclxById(lxdm);
	}
	
	/**
	 *��ѯȱ�������б�
	 */
	public List<HashMap<String, String>> getQqlxList(){
		return cclxwhDao.getQqlxList();
	}

}
