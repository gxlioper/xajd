/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-9-23 ����11:06:15 
 */  
package com.zfsoft.xgxt.xszz.knsjgwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2017-9-23 ����11:06:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class knsjgwhDao extends SuperDAOImpl<knsjgwhForm>{
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(knsjgwhForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.id,a.nd,a.xh,a.zf,(case when length(a.zt)>10 then substr(a.zt,0,10)||'...' else a.zt end) zt,b.xm,b.bjdm,b.bjmc,'"+Base.xxmc+"' as xx,b.xydm,b.xymc,b.zydm,b.zymc,b.nj,b.sfzh,b.xb,b.sjhm,b.yhmc,b.yhkh,b.zzmmmc,b.mz");
		sql.append(" from xg_xszz_knsdcjgwhb a left join view_xsjbxx b on a.xh = b.xh");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(" order by xydm,nj,zydm,bjdm ");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xszz_knsdcjgwhb");
		super.setKey("id");
		super.setClass(knsjgwhForm.class);
		
	}
	@Override
	public List<HashMap<String, String>> getPageList(knsjgwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	
}
