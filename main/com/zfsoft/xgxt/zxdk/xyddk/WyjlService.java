/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-10 ����02:57:56 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ΥԼ��¼
 * @���ߣ� �Ƴ���
 * @ʱ�䣺 2015-11-26 ����9:41:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WyjlService extends SuperServiceImpl<WyjlModel, WyjlDao> {

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-26 ����01:16:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * Object �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWyztList() {
		WyjlDao dao = new WyjlDao();
		return dao.getWyztList();
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-26 ����01:58:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String getCountByXh(WyjlModel model){
			
		return dao.getCountByXh(model);
	}
	
	/**
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-26 ����01:58:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFkList(WyjlModel model) throws Exception{
			
		return dao.getFkList(model);
	}
	
	/**
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ƴ���
	 * @���ڣ�2015-11-30  ����08:58:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getDkxxList(WyjlModel model) throws Exception{
			
		return dao.getDkxxList(model);
	}

}