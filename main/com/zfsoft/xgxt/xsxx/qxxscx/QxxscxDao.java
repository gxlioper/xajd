/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-23 ����03:50:15 
 */  
package com.zfsoft.xgxt.xsxx.qxxscx;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ����ģ��
 * @�๦������: ȫУѧ����Ϣ��ѯ�����������ʦ��ѯ����ѧԺѧ�� 
 * @���ߣ� Meng.Wei[����:1186]
 * @ʱ�䣺 2016-8-23 ����03:50:15 
 * @�汾�� V5.18.26
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class QxxscxDao extends SuperDAOImpl<QxxscxForm>{
	/*
		����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxxscxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	    ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	*/
	@Override
	public List<HashMap<String, String>> getPageList(QxxscxForm t, User user)
			throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select xh, xm, xb, sjhm, nj, xymc, zymc, bjmc from view_xsbfxx where sfzx = '��У' or sfzx is null");
		sql.append(" )t where 1= 1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
	}
}
