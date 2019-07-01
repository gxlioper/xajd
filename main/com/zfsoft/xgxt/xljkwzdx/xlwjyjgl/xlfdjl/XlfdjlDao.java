/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-3 ����01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;

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

public class XlfdjlDao extends SuperDAOImpl<XlfdjlForm> {
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlfdjlForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlfdjlForm t, User user)
			throws Exception {
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.zgh, a.fdid , a.sj,a.lx,a.fdlxdm,a.thnrfdcs," +
				"       (select aa.fdlxmc from XG_XLJK_XLWJYJ_FDLX aa where aa.fdlxdm = a.fdlxdm) fdlxmc," + 
				"       decode(a.lx , '0' , '̸��' , '1' , '����' , '') xlfdlxmc," + 
				"       b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc," +
				"       c.gzdj , d.lxdm , d.lxmc  , e.xm fdyxm " +
				"  from XG_XLJK_XLWJYJ_XLFDXXB a" +
				"  left join view_xsjbxx b" +
				"    on a.xh = b.xh" +
				"  left join XG_XLJK_XLWJYJ_XLWJYJK c" +
				"    on a.xh = c.xh " +
				"  left join XG_XLJK_XLWJYJ_XLWTLX d " +
				"    on c.lxdm = d.lxdm   left join view_fdyxx e on a.zgh = e.zgh " +
				") t1 where 1=1 ")
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
	public HashMap<String , String> xlfxxsxx(String fdid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.zgh, a.fdid , a.sj,a.lx,a.fdlxdm,a.thnrfdcs," +
				"       (select aa.fdlxmc from XG_XLJK_XLWJYJ_FDLX aa where aa.fdlxdm = a.fdlxdm) fdlxmc," + 
				"       decode(a.lx , '0' , '̸��' , '1' , '����' , '') xlfdlxmc," + 
				"       b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc," +
				"       c.gzdj , d.lxdm , d.lxmc   , e.xm fdyxm " +
				"  from XG_XLJK_XLWJYJ_XLFDXXB a" +
				"  left join view_xsjbxx b" +
				"    on a.xh = b.xh" +
				"  left join XG_XLJK_XLWJYJ_XLWJYJK c" +
				"    on a.xh = c.xh " +
				"  left join XG_XLJK_XLWJYJ_XLWTLX d " +
				"    on c.lxdm = d.lxdm    left join view_fdyxx e on a.zgh = e.zgh " +
				") t1 where fdid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{fdid});
	}
	
	
	
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XlfdjlForm.class);
		setKey("fdid");
		setTableName("XG_XLJK_XLWJYJ_XLFDXXB");
	}

}
