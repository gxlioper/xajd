/**
 * @����:ѧ����Ʒ��1����
 * @���ڣ�2017-7-7 ����09:50:25 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.cprytzjl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �������Ź���ģ��
 * @�๦������: ������Ա������¼ 
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-7-7 ����09:50:08 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CprytzjlService extends SuperServiceImpl<CprytzjlForm,CprytzjlDao>{
	
	private CprytzjlDao dao = new CprytzjlDao();
	public CprytzjlService(){
		super.setDao(dao);
	}

}
