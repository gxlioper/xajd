/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-22 ����10:49:29 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;


import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ��ԤԼ������Ϣ 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-22 ����10:49:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyybxxxService extends SuperServiceImpl<XsyybxxxForm, XsyybxxxDao> {
	
	public XsyybxxxService(){
		setDao(new XsyybxxxDao());
	}
	
}
