/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-9 ����04:28:56 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1104]
 * @ʱ�䣺 2016-05-07 ����04:28:56 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HjjyCsszDao extends SuperDAOImpl<HjjyCsszForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HjjyCsszForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HjjyCsszForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	public HjjyCsszForm getModel() throws Exception{
		String sql = "select * from XG_RCSW_HZSF_HJJYCSSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}

	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from XG_RCSW_HZSF_HJJYCSSZ";
		return dao.runUpdate(sql, new String[]{});
	}
	public List<HashMap<String, String>> getJyyyList() throws Exception{
		String sql = "select *  from XG_RCSW_HZSF_JYYYDMB";
		return dao.getListNotOut(sql, new String[]{});
	}
	@Override
	protected void setTableInfo() {
		super.setClass(HjjyCsszForm.class);
		super.setKey("id");
		super.setTableName("XG_RCSW_HZSF_HJJYCSSZ");
	}
}
