/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-2-11 ����09:12:58 
 */  
package com.zfsoft.xgxt.zdxljk.ecmm;

import xgxt.base.Encrypt;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: ���������--�ر����ѧ�� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-2-11 ����09:11:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class EcmmService extends SuperServiceImpl<EcmmModel, EcmmDao> {

	
	public EcmmModel getModelByLogin(String zgh ,String ecmm) throws Exception{
		
		Encrypt e = new Encrypt();
		
		EcmmModel model = new EcmmModel();
		model.setZgh(zgh);
		model.setEcmm(e.encrypt(ecmm));
		
		return dao.getModelByLogin(model);
	}
	
	
	public boolean initEcmm(String[] zgh,String ecmm) throws Exception{
		
		Encrypt e = new Encrypt();
		
		return dao.initEcmm(zgh, e.encrypt(ecmm));
	}
}
