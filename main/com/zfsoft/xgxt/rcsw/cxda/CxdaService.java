/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-17 ����10:07:13 
 */  
package com.zfsoft.xgxt.rcsw.cxda;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.szdw.xsgbgl.rzkh.rzkhjgForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-6-17 ����10:07:13 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CxdaService extends SuperServiceImpl<CxdaForm, CxdaDao> {
	//�ж��ڱ�ѧ�걾ѧ���ڽ�������Ƿ��г��ŵ�����¼
	public boolean checkExistForSave(CxdaForm model){
		return dao.checkExistForSave(model);
	}
	
	//��ȡѧ�ڶ��ձ��е�ѧ��
	public HashMap<String, String> getxqdz(String xqdm){
		return dao.getxqdz(xqdm);
	}
}

