/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:48:37 
 */  
package com.zfsoft.xgxt.ybgzz.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @�๦������: �װ๤��վ-�������� 
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2015-1-28 ����02:27:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class YbgzzCsszDao extends SuperDAOImpl<YbgzzCssz> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(YbgzzCssz.class);
		super.setKey("id");
		super.setTableName("xg_ybgzz_cssz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbgzzCssz t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YbgzzCssz t, User user)
			throws Exception {
		
		return null;
	}

	public YbgzzCssz getModel() throws Exception{
		String sql = "select * from xg_ybgzz_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
