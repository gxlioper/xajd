/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:48:37 
 */  
package com.zfsoft.xgxt.xsxx.cxpd.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��������--�������� 
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2015-1-14 ����01:52:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class CxpdCsszDao extends SuperDAOImpl<CxpdCssz> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CxpdCssz.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_cxpdcssz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdCssz t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CxpdCssz t, User user)
			throws Exception {
		
		return null;
	}

	public CxpdCssz getModel() throws Exception{
		String sql = "select * from xg_xsxx_cxpdcssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
