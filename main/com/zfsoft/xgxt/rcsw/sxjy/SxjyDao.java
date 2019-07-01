/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-21 ����11:02:27 
 */  
package com.zfsoft.xgxt.rcsw.sxjy;

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

public class SxjyDao extends SuperDAOImpl<SxjyForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SxjyForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SxjyForm t, User user)
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
		sql.append(" from xg_sxjy_sxjyjgb a "); 
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
		super.setTableName("xg_sxjy_sxjyjgb");
		super.setKey("id");
	}

	public boolean isExist(SxjyForm model) {
		String sql = "select count(1) num from xg_sxjy_sxjyjgb where xh = ? and xxkt = ? and xxkssj=? " ;
		String num = dao.getOneRs(sql, new String[]{model.getXh(),model.getXxkt(),model.getXxkssj()}, "num");
		return Integer.valueOf(num)>0;
	}

}
