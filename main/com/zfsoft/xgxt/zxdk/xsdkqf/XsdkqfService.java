/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-16 ����04:32:43 
 */  
package com.zfsoft.xgxt.zxdk.xsdkqf;

import xgxt.form.User;
import xgxt.xsxx.comm.sjy.jcsjcsh.SjyJcsjcshForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2018-5-16 ����04:32:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsdkqfService extends SuperServiceImpl<XsdkqfForm,XsdkqfDao>{
	XsdkqfDao dao = new XsdkqfDao();
	
	public boolean allSubmit(SjyJcsjcshForm model, User user) throws Exception {
		
		boolean flag = false;
		flag = dao.Fsznx();
		
		return flag;
	}
	public boolean Fsznx() throws Exception {

		return dao.Fsznx();
	}
}
