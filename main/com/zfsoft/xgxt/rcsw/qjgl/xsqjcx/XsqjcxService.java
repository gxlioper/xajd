/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-3 ����03:47:11 
 */  
package com.zfsoft.xgxt.rcsw.qjgl.xsqjcx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-3 ����03:47:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsqjcxService extends SuperServiceImpl<XsqjcxForm, XsqjcxDao> {
	XsqjcxDao dao = new XsqjcxDao();
	public HashMap<String, String> Qjsqck(XsqjcxForm para){
		return dao.Qjsqck(para);
	}
	/** 
	 * @����:������ʷ
	 * @���ߣ�CP[���ţ�982]
	 * @���ڣ�2017-4-11 ����09:55:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplsList(XsqjcxForm model) {
		// TODO �Զ����ɷ������
		return dao.getSplsList(model);
	}
}
