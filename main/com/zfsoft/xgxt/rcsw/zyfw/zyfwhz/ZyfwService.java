/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-28 ����11:55:18 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�cp[���ţ�1352]
 * @ʱ�䣺 2016-12-28 ����11:55:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwService extends SuperServiceImpl<ZyfwForm,ZyfwDao>{

	public HashMap<String,String> getXsxx(String xh){
		return dao.getXsxx(xh);		
	}

	public List<HashMap<String, String>> getZyfwList(String xh, ZyfwForm model) {
		return dao.getZyfwList(xh,model);
	}

	public HashMap<String, String> getZsc(String xh, ZyfwForm model) {
		return dao.getZsc(xh,model);
	}

}
