/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-24 ����09:48:37 
 */  
package com.zfsoft.xgxt.xsxx.djjd.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ȼ����� --��������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-11 ����04:16:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DjjdCsszDao extends SuperDAOImpl<DjjdCssz> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(DjjdCssz.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_jddjcssz");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DjjdCssz t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DjjdCssz t, User user)
			throws Exception {
		
		return null;
	}

	public DjjdCssz getModel() throws Exception{
		String sql = "select * from xg_xsxx_jddjcssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
}
