/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:30:48 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;


import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:30:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlfdjlService extends SuperServiceImpl<XlfdjlForm, XlfdjlDao> {

	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����02:12:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> xlfxxsxx(String fdid) throws Exception{
		return dao.xlfxxsxx(fdid);
	}

	public XlfdjlService(){
		setDao(new XlfdjlDao());
	}
}
