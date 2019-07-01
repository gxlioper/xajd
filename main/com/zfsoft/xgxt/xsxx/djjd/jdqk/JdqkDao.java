/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-10-29 ����09:43:18 
 */  
package com.zfsoft.xgxt.xsxx.djjd.jdqk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @�๦������: �����ȼ��������
 * @���ߣ� ����� [����:445]
 * @ʱ�䣺 2014-10-29 ����09:43:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JdqkDao extends SuperDAOImpl<JdqkModel> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JdqkModel.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_jddjb");
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JdqkModel t)
			throws Exception {
		
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JdqkModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,t5.xqmc,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t3.mc xmmc,t4.mc jbmc ");
		sql.append("from xg_xsxx_jddjb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_xsxx_jddjdm t3 on t1.xmdm = t3.dm and t3.lx='1' ");
		sql.append("left join xg_xsxx_jddjdm t4 on t1.jbdm = t4.dm and t4.lx='2' ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public JdqkModel getModel(JdqkModel t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t3.mc xmmc,t4.mc jbmc,t5.xqmc ");
		sql.append("from xg_xsxx_jddjb t1 ");
		sql.append("left join xg_xsxx_jddjdm t3 on t1.xmdm = t3.dm and t3.lx='1' ");
		sql.append("left join xg_xsxx_jddjdm t4 on t1.jbdm = t4.dm and t4.lx='2' ");
		sql.append("left join xqdzb t5 on t1.xq=t5.xqdm ");
		sql.append(" where t1.id = ? ");
		return super.getModel(sql.toString(), new String[]{t.getId()});
	}

	
	
}
