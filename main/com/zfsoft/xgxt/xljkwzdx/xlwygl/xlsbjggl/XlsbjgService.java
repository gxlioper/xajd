/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-30 ����05:23:21 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.xlsbjggl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-30 ����05:23:21 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlsbjgService extends SuperServiceImpl<XlsbjgForm, XlsbjgDao> {

	public XlsbjgService(){
		setDao(new XlsbjgDao());
	}
	
}
