/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-2-22 ����05:09:55 
 */  
package com.zfsoft.xgxt.xpjpy.pjxmhz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-ͳ�ƹ���-������Ŀ����
 * @�๦������: ͳ��ÿѧ�ꡢÿѧ�ڡ�ÿ����Ŀ�Ļ�����
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2017-2-22 ����05:09:55 
 * @�汾�� Ver 5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class PjxmhzService extends SuperServiceImpl<PjxmhzForm, PjxmhzDao>{
	
	private PjxmhzDao dao = new PjxmhzDao();
	
	public PjxmhzService(){
		super.setDao(dao);
	}
	
	//������ѯ������Ŀ������Ϣ
	public List<HashMap<String, String>> viewRs(PjxmhzForm model,User user,Boolean fyFlag) throws Exception{
		return dao.viewRs(model, user,fyFlag);
	}
}
