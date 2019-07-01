/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-29 ����03:35:04 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������ѯ���´�-��������ѯ -ԤԼ��ѯ����
 * @�๦������: 
 * @���ߣ� ��־��[����:1060]
 * @ʱ�䣺 2014-4-29 ����03:35:04 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class YyzxfkDao extends SuperDAOImpl<YyzxfkForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(YyzxfkForm.class);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YyzxfkForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t.yyzt,t2.zzaprq,t2.zxs,t2.zxzt,t3.xm zxsmc ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 ")
		   .append("on t.xh = t1.xh ")
		   .append("left join XG_XLZX_XLZXB_WZDX t2 ")
		   .append("on t.sqid = t2.sqid ")
		   .append("left join VIEW_FDYXX t3 ")
		   .append("on t2.zxs = t3.zgh ")
		   .append("where 1=1 ")
		   .append(searchTj);
		   
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YyzxfkForm t, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

}
