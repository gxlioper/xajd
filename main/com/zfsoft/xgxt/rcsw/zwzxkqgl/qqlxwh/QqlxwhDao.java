/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-26 ����04:00:27 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-26 ����04:00:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QqlxwhDao extends SuperDAOImpl<QqlxwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QqlxwhForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(QqlxwhForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	public List<HashMap<String, String>> getQqlxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(QqlxwhForm.class);
		super.setKey("lxdm");
		super.setTableName("XG_RCSW_ZWZXKQ_QQLXB");
		
	}

}
