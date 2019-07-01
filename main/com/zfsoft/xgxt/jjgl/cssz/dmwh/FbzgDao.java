/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-25 ����03:25:16 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-25 ����03:25:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FbzgDao extends SuperDAOImpl<FbzgForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FbzgForm t)
			throws Exception {
		String sql = "select * from XSGGFW_JJFW_FBZGDYB a order by a.fbzgdm";
		return super.getPageList(t, sql, null);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FbzgForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(FbzgForm.class);
		super.setKey("FBZGDM");
		super.setTableName("XSGGFW_JJFW_FBZGDYB");
	}

}
