/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-8 ����02:16:24 
 */  
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ��������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� huj
 * @ʱ�䣺 2013-6-8 ����02:16:24 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JzygService extends SuperServiceImpl<JzygForm, JzygDao> {

	private JzygDao dao = new JzygDao();
	
	public JzygService(){
		super.setDao(dao);
	}

	public HashMap<String,String> getXsjbxx(String zgh){
		return dao.getJzygxx(zgh);
	}

}
