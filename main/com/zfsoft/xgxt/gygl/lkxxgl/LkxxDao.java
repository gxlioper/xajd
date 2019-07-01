/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-4-18 ����02:01:07 
 */  
package com.zfsoft.xgxt.gygl.lkxxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-4-18 ����02:01:07 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LkxxDao extends SuperDAOImpl<LkxxForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LkxxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(LkxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.* from xg_gygl_lkxx t1 ");
		sql.append(" ) t where 1 = 1 ");
		//sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(LkxxForm.class);
		super.setKey("id");
		super.setTableName("xg_gygl_lkxx");	
	}
	
	//�жϵ����Ƿ���ס�޵Ǽ�
	public boolean isHaveRecord(LkxxForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_gygl_lkxx where sfzh = ? and rzsj = ? ");
		if("update".equalsIgnoreCase(t.getType())){
			sql.append(" and id <> '"+ t.getId()+"'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{t.getSfzh(),t.getRzsj()}, "num");
		return Integer.valueOf(num) > 0;
	}
	
}
