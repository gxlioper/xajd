/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.wgwtwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-6-3 ����01:29:18 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WgwtwhDao extends SuperDAOImpl<WgwtwhForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WgwtwhForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WgwtwhForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select x.* , to_number(x.zf) zf_num , decode(x.yjkcount , '0' , '��' , '��') yjkcx, decode(x.yjkcount , '0' , 'n' , 'y') yjkdm from  (select a.*,  b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc ,  " +
				"(select count(1) from XG_XLJK_XLWJYJ_XLWJYJK aa where aa.xh = a.xh) yjkcount from  XG_XLJK_XLWJYJ_WGWT a left join view_xsjbxx b on a.xh = b.xh ) x ")
		.append(" ) t where 1 = 1 ") 
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	/**
	 * @throws SQLException 
	 * 
	 * @����:������ݿ��Ƿ����
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-5-26 ����10:54:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public int checkExist(String xh) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWJYJ_WGWT a where a.xh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh}, "rs"));
	}
	
	@Override
	protected void setTableInfo() {
		setClass(WgwtwhForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWJYJ_WGWT");
	}

}
