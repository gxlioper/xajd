/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-5-11 ����10:10:15 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��ҵ�������
 * @�๦������: ��ҵ������  
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2016-5-11 ����10:10:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ByhkglJgService extends SuperServiceImpl<ByhkglJgForm, ByhkglJgDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @����: Ψһ���ж�
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-12 ����01:28:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(ByhkglJgForm form) {
		return dao.isHaveRecordForjg(form);		
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-12 ����01:28:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public boolean saveByhkgljg(ByhkglJgForm model) throws Exception {
		
		boolean result = false;
		if ("save".equals(model.getType())) {
			model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @����: ��ȡzqyymc
	 * @���ߣ�������[����:1123]
	 * @���ڣ�2016-5-10 ����01:44:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		return dao.getZqyymc(xh);
	}
}
