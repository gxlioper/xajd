/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:48:37 
 */  
package com.zfsoft.xgxt.zxdk.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-�������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-24 ����09:48:37 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxdkCsszDao extends SuperDAOImpl<ZxdkCssz> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZxdkCssz.class);
		super.setKey("id");
		super.setTableName("xg_zxdk_xydksz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxdkCssz t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZxdkCssz t, User user)
			throws Exception {
		return null;
	}

	public ZxdkCssz getModel() throws Exception{
		String sql = "select * from xg_zxdk_xydksz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
