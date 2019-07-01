/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-22 ����10:58:15 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.cclxwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2015-1-22 ����10:58:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CclxwhDao extends SuperDAOImpl<CclxwhForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CclxwhForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CclxwhForm t, User user) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	/**
	 *��ѯ�������
	 */
	public List<HashMap<String, String>> getCclxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_CCLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 *��ѯ�������byID
	 */
	public HashMap<String, String> getCclxById(String lxdm){
		String sql = "select * from  XG_RCSW_ZWZXKQ_CCLXB where lxdm=?";
		return dao.getMapNotOut(sql, new String[]{lxdm});
	}
	
	public List<HashMap<String, String>> getQqlxList(){
		String sql = "select * from  XG_RCSW_ZWZXKQ_QQLXB";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	@Override
	protected void setTableInfo() {
		super.setClass(CclxwhForm.class);
		super.setKey("lxdm");
		super.setTableName("XG_RCSW_ZWZXKQ_CCLXB");
		
	}

}
