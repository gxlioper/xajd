/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-23 ����08:38:20 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-23 ����08:38:20 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	public JcszService(){
		setDao(new JcszDao());
	}
	/**
	 * 
	 * @����:��ѯ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-23 ����08:41:08
	 */
	public JcszForm getJcsz(){
		HashMap<String , String> jcsz = dao.getJcsz();
		if(jcsz.size() == 0)
			return null;
		
		JcszForm model = new JcszForm();
		model.setBjzbrcSplcid(jcsz.get("bjzbrc_splcid"));
		model.setGyzbrcSplcid(jcsz.get("gyzbrc_splcid"));
		model.setPsxxsbSplcid(jcsz.get("psxxsb_splcid"));
		
		return model;
	}
	
	/**
	 * @����:��������
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-23 ����08:47:44
	 */
	public boolean saveJcsz(JcszForm model){
		if(model == null)
			return false;
		
		return dao.saveJcsz(model);
	}
	
}
