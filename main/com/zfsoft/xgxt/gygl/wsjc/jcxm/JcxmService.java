/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-5-28 ����05:10:42 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcxm;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @�๦������: �����Ŀ
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-5-28 ����05:10:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcxmService extends SuperServiceImpl<JcxmModel, JcxmDao> {

	
	/**���ճ�id��ѯ�����Ŀ�б�**/
	public List<HashMap<String, String>> getRcxmList(String rcid,String jcdx)
		throws Exception {
		return dao.getRcxmList(rcid,jcdx);
	}
	
	/** 
	 * @����:�Ƿ����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-11-3 ����06:23:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isExist(JcxmModel model){
		return Integer.valueOf(dao.getCountJl(model))>0;
	}
}
