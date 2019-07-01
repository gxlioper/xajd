/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-11-23 ����08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.jcsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-11-23 ����08:40:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZzdyJcszService extends SuperServiceImpl<ZzdyJcszForm, ZzdyJcszDao> {
	private ZzdyJcszDao dao = new ZzdyJcszDao();
	
	public boolean isHave(ZzdyJcszForm model) {
		return dao.isHave(model);
	}
	
	public boolean isUsed(String[] values) {
		return dao.isUsed(values);
	}
	
	public boolean editSzxm(ZzdyJcszForm model) throws Exception {
		boolean result = true;
		if ("save".equals(model.getType())) {
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
		}
		return result;
	}
	
	public List<HashMap<String,String>> getXmList(String xn,String xq) throws Exception {
		return dao.getXmList(xn,xq);
	}

}
