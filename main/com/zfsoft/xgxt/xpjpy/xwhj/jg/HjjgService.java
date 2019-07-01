/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-7-27 ����01:19:59 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������-����Ϣ����
 * @�๦������: �񽱽��  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-7-27 ����01:19:59 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjgService extends SuperServiceImpl<HjjgForm, HjjgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @����: �ظ���֤�����ӣ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:13:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(HjjgForm form) {
		return dao.isHaveRecordForjg(form);	
	}
	
	/**
	 * 
	 * @����: �ظ���֤���޸ģ�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:14:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjgU(HjjgForm form) {
		return dao.isHaveRecordForjgU(form);	
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-7-28 ����11:14:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveHjjg(HjjgForm model) throws Exception {
		
		boolean result = false;
		if ("save".equals(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		
		return result;	
	}
	
}
