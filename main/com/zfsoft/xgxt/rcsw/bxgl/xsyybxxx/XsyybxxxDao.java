/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-1-22 ����10:09:14 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.xsyybxxx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ�����
 * @�๦������: ���չ���-ѧ��ԤԼ������Ϣ 
 * @���ߣ� ������ [����:1123]
 * @ʱ�䣺 2015-1-22 ����10:09:14 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XsyybxxxDao extends SuperDAOImpl<XsyybxxxForm> {
	
	/*
 	  ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XsyybxxxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
             ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsyybxxxForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
		sql.append(" select * from (select t1.yybxid,t1.yysj,t1.blnr,t2.* from xg_rcsw_bxgl_xsyybxxxb t1 left join view_xsbfxx t2 on t1.xh = t2.xh order by yysj desc) a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
		
	}
	
	/*
   	  ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_BXGL_XSYYBXXXB");
		setKey("yybxid");
		setClass(XsyybxxxForm.class);

	}

}
