/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-6 ����03:17:03 
 */  
package com.zfsoft.xgxt.zxdk.dkbc.jcjy;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgDao;
import com.zfsoft.xgxt.zxdk.dkbc.bcjg.BcjgModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ѧ����-�����ҵ
 * @�๦������: ѧ�������ҵ��Ϣ��¼
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-12-6 ����03:18:23 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcjyService extends SuperServiceImpl<JcjyModel, JcjyDao>{
	/**
	 * ȡ��ҵ������ƺʹ���
	 */
	public List<HashMap<String, String>> getHylbList(){
		return dao.getHylbList();
	}
	
	/**
	 * ͬһ��ѧ�Ŵ������ֻ�ܴ���һ����¼
	 */
	public boolean isHaveRecord(String xh,String dclb){
		return dao.isHaveRecord(xh,dclb);
	}
	
	/**
	 * ȡ��ҵ�������
	 */
	public String getHylb(String xh){
		return dao.getHylb(xh);
	}
}
