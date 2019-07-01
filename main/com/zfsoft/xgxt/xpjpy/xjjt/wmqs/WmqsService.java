/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-1-19 ����02:04:06 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.wmqs;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-1-19 ����02:04:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsService  extends SuperServiceImpl<WmqsForm, WmqsDao>{
	private WmqsDao dao = new WmqsDao();

	public WmqsService() {
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
	public boolean isExistByWmqs(WmqsForm model, String type)
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
	 * @����:��ȡУ��(������һ�仰�����������������)
	 * @���ߣ��ֹ���[���ţ�1553]
	 * @���ڣ�2018-1-15 ����04:33:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXqmcList()throws Exception {
			
			return dao.getXqmcList();
		
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-1-22 ����10:20:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getLdSum(WmqsForm model) {
		// TODO �Զ����ɷ������
		return dao.getLdSum(model);
	}

	/** 
	 * @����:У������(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-1-22 ����02:27:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xqmc
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getXqdm(String xqmc) {
		
		return dao.getXqdm(xqmc);
	}
	
}
