/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-10 ����05:25:02 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�  wanghj [���ţ�1004]
 * @ʱ�䣺 2014-2-10 ����05:25:02 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DaqdclService extends SuperServiceImpl<DaqdclForm, DaqdclDao> {

	private DaqdclDao dao = new DaqdclDao();
	
	public DaqdclService(){
		super.setDao(dao);
	}

	/**
	 * �жϵ����嵥���������Ƿ��ظ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDaqdclByName(DaqdclForm myForm) throws Exception{
		
		return dao.getDaqdclByName(myForm);
	}
	
	/**
	 * 
	 * @����:�����б�
	 * @���ڣ�2014-4-24 ����09:51:32
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getDaqdclAllList() throws Exception {
		
		return dao.getDaqdclAllList();
	}
}
