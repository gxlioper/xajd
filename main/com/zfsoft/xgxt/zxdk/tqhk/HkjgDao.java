/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-11-7 ����03:22:30 
 */  
package com.zfsoft.xgxt.zxdk.tqhk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������:������
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-11-7 ����03:22:30 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class HkjgDao extends SuperDAOImpl<HkjgModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_tqhkjgb");
		super.setKey("id");
		super.setClass(HkjgModel.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkjgModel t)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HkjgModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc hkztmc ");
		sql.append("from xg_zxdk_tqhkjgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_zxdk_hkztb t3 on t1.hkzt = t3.dm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public HkjgModel getModel(HkjgModel t) throws Exception {
		
		String sql = "select t1.*,t2.mc hkztmc from xg_zxdk_tqhkjgb t1 left join xg_zxdk_hkztb t2 on t1.hkzt = t2.dm where t1.id=?";
		
		return super.getModel(sql, new String[]{t.getId()});
	}

	
}
