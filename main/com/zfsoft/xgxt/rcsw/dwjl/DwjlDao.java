/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:02:27 
 */  
package com.zfsoft.xgxt.rcsw.dwjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ��ɫͨ�����
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2015-1-21 ����11:02:27 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DwjlDao extends SuperDAOImpl<DwjlForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DwjlForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(DwjlForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * ");
		sql.append(" from (select a.*, ");
		sql.append(" b.xm, ");
		sql.append(" b.xymc,b.zymc,b.zzmmmc,b.sjhm, ");
		sql.append(" b.bjmc, ");
		sql.append(" b.xb, ");
		sql.append(" b.nj, ");
		sql.append(" b.zydm,");
		sql.append(" b.bjdm,");
		sql.append(" b.xydm ");	
		sql.append(" from xg_dwjl_dwjljgb a "); 
		sql.append(" left join view_xsbfxx b  "); 
		sql.append("on a.xh = b.xh) t "); 
		sql.append(" where 1 = 1"); 
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_dwjl_dwjljgb");
		super.setKey("id");
	}

	public boolean isExist(DwjlForm model) {
		String sql = "select count(1) num from xg_dwjl_dwjljgb where xh = ? and jlxx = ? and jlkssj=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getJlxx(),model.getJlkssj()}, "num");
		return Integer.valueOf(num)>0;
	}

}
