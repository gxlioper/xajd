/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������: ����ά��
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:43:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JyjlDao extends SuperDAOImpl<JyjlModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JyjlModel.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_sbgl_jyjl");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JyjlModel t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append(" select t1.*,t2.mc flmc,t3.bh,t3.mc sbmc,t4.szbm bmdm,");
		sql.append(" t4.xm,t5.bmmc,t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" case when t1.ghsj is null then 'δ�黹' else '�ѹ黹' end ghzt ");
		sql.append(" from xg_rcsw_sbgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join yhb t4 on t1.zgh = t4.yhm ");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.szbm = t5.bmdm ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JyjlModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public JyjlModel getModel(JyjlModel t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.mc flmc,t3.mc sbmc,t4.xm,t5.bmmc,t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" case when t1.ghsj is null then 'δ�黹' else '�ѹ黹' end ghzt ");
		sql.append(" from xg_rcsw_sbgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join yhb t4 on t1.zgh = t4.yhm ");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.szbm = t5.bmdm ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" where t1.id = ? ");
		
		return super.getModel(sql.toString(), new String[]{t.getId()});
	}

	
}
