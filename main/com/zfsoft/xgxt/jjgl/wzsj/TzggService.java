/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����02:12:06 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����02:12:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TzggService extends SuperServiceImpl<TzggForm, TzggDao> {
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(TzggForm t) throws Exception {
		return dao.runInsert(t);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(TzggForm t) throws Exception {
		return dao.runUpdate(t);
	}

	/**
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�zxb[���ţ�1036]
	 * @���ڣ�2014-9-12 ����02:19:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> getModelMap(TzggForm t) throws Exception{
		return dao.getModelMap(t);
	}
}
