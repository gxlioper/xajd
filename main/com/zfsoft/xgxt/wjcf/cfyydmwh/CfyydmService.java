/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-10-25 ����09:14:32 
 */  
package com.zfsoft.xgxt.wjcf.cfyydmwh;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: Υ�͹���ģ��
 * @�๦������: (����ԭ�����ά��) 
 * @���ߣ� ������[����:913]
 * @ʱ�䣺 2013-10-25 ����09:07:01 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CfyydmService extends SuperServiceImpl<CfyydmForm, CfyydmDao> {
	
	private CfyydmDao dao=new CfyydmDao();
	public CfyydmService(){
		this.setDao(dao);
	}
	/** 
	 * @ϵͳ����: ѧ����������ϵͳ
	 * @ģ������: Υ�͹���ģ��
	 * @�๦������: (����ԭ�������Ƿ����) 
	 * @���ߣ� ������[����:913]
	 * @ʱ�䣺 2013-10-24 ����10:52:35 
	 * @�汾�� V1.0
	 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
	 */
	public boolean checkIsExist(CfyydmForm myForm) {
		// TODO �Զ����ɷ������
		return dao.checkIsExist(myForm);
	}

}
