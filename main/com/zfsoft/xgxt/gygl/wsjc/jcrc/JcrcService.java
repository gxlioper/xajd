/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-1 ����09:02:48 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @�๦������:����ճ�
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-6-1 ����09:02:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcrcService extends SuperServiceImpl<JcrcModel, JcrcDao> {

	private static final String TJZT_WTJ = "0";
	private static final String TJZT_YTJ = "1";
	
	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(JcrcModel t) throws Exception {
		
		String id = UniqID.getInstance().getUniqIDHash();
		
		t.setId(id);
		
		dao.saveRcxm(id, t.getXmdm());
		return super.runInsert(t);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(JcrcModel t) throws Exception {
		dao.delRcxm(t.getId());
		dao.saveRcxm(t.getId(), t.getXmdm());
		return super.runUpdate(t);
	}
	
	
	/**��ѯ�ճ���Ŀ***/
	public String[] getRcxmArr(String rcid) throws SQLException{
		return dao.getRcxmArr(rcid);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		dao.delRcxm(values);
		return super.runDelete(values);
	}

	
	/**�ύ�ճ�**/
	public boolean runSubmit(String[] values) throws Exception{
		return dao.updateTjzt(TJZT_YTJ, values);
	}
	
	
	/**ȡ���ύ�ճ�**/
	public boolean runCancel(String[] values) throws Exception{
		return dao.updateTjzt(TJZT_WTJ, values);
	}
	
	/**�ύͬ�����µ�XG_QSFMX���Ϻ�Ϸ����Ի���**/
	public boolean saveSubmit(String[] values) throws Exception{
		return dao.saveWsf(values);
	}
	
	/**ȡ���ύͬ��ɾ����Ӧ����XG_QSFMX���Ϻ�Ϸ����Ի���**/
	public boolean delCancel(String[] values) throws Exception{
		return dao.delWsf(values);
	}
	
}
