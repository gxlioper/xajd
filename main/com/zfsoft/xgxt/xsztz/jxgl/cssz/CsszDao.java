/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:28:56 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;



/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�����[����:1282]
 * @ʱ�䣺 2016-1-27 ����10:06:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class CsszDao extends SuperDAOImpl<CsszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	public CsszForm getModel() throws Exception{
		String sql = "select * from xg_sztz_xnjx_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from xg_sztz_xnjx_cssz";
		return dao.runUpdate(sql, new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setKey("id");
		super.setTableName("xg_sztz_xnjx_cssz");
	}
}
