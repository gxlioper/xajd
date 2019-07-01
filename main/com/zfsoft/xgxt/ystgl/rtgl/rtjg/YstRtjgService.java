/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-8-7 ����10:41:06 
 */  
package com.zfsoft.xgxt.ystgl.rtgl.rtjg;

import java.util.HashMap;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2015-8-7 ����10:41:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YstRtjgService extends SuperServiceImpl<YstRtjgForm, YstRtjgDao> {
	
	//��ȡ������Ϣ
	public HashMap<String, String> getYstxxMap(YstRtjgForm t) throws Exception{
		return dao.getYstxxMap(t);
	}
	public boolean editYstRtjg(YstRtjgForm model) throws Exception {
		model.setRtxn(Base.currXn);
		model.setRtxq(Base.currXq);
		boolean result = true;
		if ("save".equals(model.getType())) {
			String rtid = UniqID.getInstance().getUniqIDHash();
			model.setRtid(rtid);
			result = dao.runInsert(model);
		} else {
			result = dao.runUpdate(model);
			
		}
		return result;
	}
	
	
}
