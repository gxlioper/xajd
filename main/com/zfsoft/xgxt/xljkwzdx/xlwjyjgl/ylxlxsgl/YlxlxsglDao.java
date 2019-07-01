/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.ylxlxsgl;

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

public class YlxlxsglDao extends SuperDAOImpl<YlxlxsglForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlxlxsglForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(YlxlxsglForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (");
		sql.append("select t.* , decode(t.yjkcount , '0' , '��' , '��') yjkcx, " +
				"decode(t.yjkcount , '0' , 'n' , 'y') yjkdm, decode(t.mtkssj , NULL , '��' , '��') sfmtmc , decode(t.mtkssj , NULL , 'n' , 'y') sfmtdm" +
				" from  (select a.*,  b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc ,  a.mtkssj||'~'||a.mtjssj mtsj_dc , b.xm fdyxm , " +
				"(select count(1) from XG_XLJK_XLWJYJ_XLWJYJK aa where aa.xh = a.xh) yjkcount , e.lxmc from  XG_XLJK_XLWJYJ_YLXLXSXXB a left join view_xsjbxx b on a.xh = b.xh " +
				"left join XG_XLJK_XLWJYJ_XLWTLX e on a.lxdm = e.lxdm  left join fdyxxb f on f.zgh = a.mtzgh ) t")
		.append(" ) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ��������Ϣ
	 * @���ߣ���С��[���ţ�1036]
	 * @���ڣ�2014-6-5 ����02:12:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String , String> ylxlxsxx(String xh) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* , decode(t.yjkcount , '0' , '��' , '��') yjkcx, " +
				"decode(t.yjkcount , '0' , 'n' , 'y') yjkdm, decode(t.mtkssj , NULL , '��' , '��') sfmtmc , decode(t.mtkssj , NULL , 'n' , 'y') sfmtdm" +
				" from  (select a.*,  b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc ,  " +
				"(select count(1) from XG_XLJK_XLWJYJ_XLWJYJK aa where aa.xh = a.xh) yjkcount , e.lxmc from  XG_XLJK_XLWJYJ_YLXLXSXXB a left join view_xsjbxx b on a.xh = b.xh " +
				"left join XG_XLJK_XLWJYJ_XLWTLX e on a.lxdm = e.lxdm) t where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
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
		String sql = "select count(1) rs from XG_XLJK_XLWJYJ_YLXLXSXXB a where a.xh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh}, "rs"));
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(YlxlxsglForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWJYJ_YLXLXSXXB");
	}

}
