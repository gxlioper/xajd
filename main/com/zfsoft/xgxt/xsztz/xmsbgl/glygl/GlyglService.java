/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����10:27:57 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2015-7-9 ����10:27:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GlyglService extends SuperServiceImpl<GlyglForm, GlyglDao> {
	
	public List<HashMap<String, String>> getYhList(GlyglForm t) throws Exception {
		return dao.getYhList(t);
	}

}
