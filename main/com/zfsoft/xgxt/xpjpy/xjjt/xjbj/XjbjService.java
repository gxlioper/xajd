/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-15 ����09:40:15 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.xjbj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-1-15 ����09:40:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XjbjService  extends SuperServiceImpl<XjbjForm, XjbjDao> {

	private XjbjDao dao = new XjbjDao();

	public XjbjService() {
		super.setDao(dao);
	}

	/** 
	 * @����:��֤ͬһѧ���°༶�Ƿ��ظ�(������һ�仰�����������������)
	 * @���ߣ��ֹ���[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExistByXjbj(XjbjForm model, String type)
	throws Exception {
		if ("add".equalsIgnoreCase(type)) {
			String num = dao.checkExistForAddSave(model);
			return Integer.valueOf(num) > 0;
		} else {
			String num = dao.checkExistForUpdate(model);
			return Integer.valueOf(num) > 0;
		}
	
	}
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getBjdm(String bjmc) {
		
		return dao.getBjdm(bjmc);
	}
	public String getBjmc(String bjdm) {
		
		return dao.getBjmc(bjdm);
	}
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�linguoxia[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:35:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm) {
		
		return dao.getBjxx(bjdm);
	}
}
