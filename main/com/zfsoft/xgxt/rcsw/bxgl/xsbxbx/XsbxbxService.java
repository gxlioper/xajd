/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����02:03:02 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsbxbx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ�����ձ��� 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-26 ����02:03:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsbxbxService extends SuperServiceImpl<XsbxbxForm, XsbxbxDao> {
	
	public XsbxbxService(){
		setDao(new XsbxbxDao());
	}
	
	//�鿴ÿ���������������Ϣ
	public HashMap<String,String> getXsbxbx(String bxid) {
		
		return dao.getXsbxbx(bxid); 
	}
	
	//����֤����ӡ
	public HashMap<String, String> bxbxZm(String bxid) throws Exception{
		
		return dao.bxbxZm(bxid);
	}
}
