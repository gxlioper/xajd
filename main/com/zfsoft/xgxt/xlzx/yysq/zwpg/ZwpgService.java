/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-3-6 ����05:04:48 
 */  
package com.zfsoft.xgxt.xlzx.yysq.zwpg;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-3-6 ����05:04:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZwpgService extends SuperServiceImpl<ZwpgForm, ZwpgDao>{
	private ZwpgDao dao = new ZwpgDao();
	public ZwpgService(){
		super.setDao(dao);
	}
	/** 
	 * @����:�������ȡ�����������µ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2018-3-19 ����10:15:51
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * ZwpgForm �������� 
	 * @throws 
	 */
	public HashMap<String, String> getInfoByXh(String xh) {
		return dao.getInfoByXh(xh);
	}
	public HashMap<String, String> getInfoById(String id) {
		return dao.getInfoById(id);
	}
	
	
	
}
