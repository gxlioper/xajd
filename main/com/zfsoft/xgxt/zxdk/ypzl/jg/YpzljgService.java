/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-2-24 ����11:41:41 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.jg;

import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-2-24 ����11:41:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YpzljgService extends SuperServiceImpl<YpzljgForm, YpzljgDao>{
	private YpzljgDao dao = new YpzljgDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/**
	 * 
	 * @����:�ж��Ƿ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-25 ����10:17:45
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param form
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHaveRecordForjg(YpzljgForm form){
		return dao.isHaveRecordForjg(form);
	}
	
	
	
	/** 
	 * @����:������
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-2-25 ����10:20:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveYpzljg(YpzljgForm model) throws Exception {
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
