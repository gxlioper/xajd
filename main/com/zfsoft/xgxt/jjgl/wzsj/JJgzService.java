/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-11 ����02:13:30 
 */  
package com.zfsoft.xgxt.jjgl.wzsj;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-9-11 ����02:13:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JJgzService extends SuperServiceImpl<JJgzForm, JJgzDao> {

	/*
    ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
*/

	@Override
	public boolean runInsert(JJgzForm t) throws Exception {
		return dao.runInsert(t);
	}
	
	/*
	    ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	*/
	
	@Override
	public boolean runUpdate(JJgzForm t) throws Exception {
		return dao.runUpdate(t);
	}
}
