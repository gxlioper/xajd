/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-2 ����04:09:55 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ֹ���
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-8-2 ����04:09:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JbfglService extends SuperServiceImpl <JbfglForm,JbfglDao> {
	
	private JbfglDao rd = new JbfglDao();

	public JbfglService() {
		setDao(rd);
	}

	
public boolean isExistQysj(JbfglForm myForm) 
	throws Exception {
			String num = dao.checkExistForSave(myForm);
			return Integer.valueOf(num) > 0;
		
	}


	
}
