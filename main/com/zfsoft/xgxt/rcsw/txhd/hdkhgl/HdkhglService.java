/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-9-18 ����04:40:36 
 */  
package com.zfsoft.xgxt.rcsw.txhd.hdkhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2015-9-18 ����04:40:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HdkhglService extends SuperServiceImpl<hdkhForm,HdkhglDao> {
	//��ȡ���������list
	public List<HashMap<String, String>> getKhcjList(String xmdm) throws Exception{
		return dao.getKhcjList(xmdm);
	}
	
	//��ѯ
	public List<HashMap<String, String>> getKhglList(hdkhForm t, User user)
	throws Exception {
		// TODO �Զ����ɷ������
		return dao.getKhglList(t, user);
     }

}
