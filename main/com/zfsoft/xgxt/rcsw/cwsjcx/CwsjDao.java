/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-6-19 ����03:43:00 
 */  
package com.zfsoft.xgxt.rcsw.cwsjcx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(�������ݲ�ѯ) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-6-19 ����03:43:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class CwsjDao extends SuperDAOImpl<CwsjForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CwsjForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: �߼���ѯ
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(CwsjForm model, User user)
			throws Exception {
		// TODO �Զ����ɷ������
		//���ɸ߼���ѯ�������������ֵ 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		//StringBuilder sql = new StringBuilder(" select * from (select a.*,(select xqmc from xqdzb e where e.xqdm=a.xq) xqmc,b.xm,b.xymc,b.xydm,b.zymc,b.zydm,b.bjmc,bjdm from rcsw_cwsjb a left join view_xsjbxx b on a.xh=b.xh) a where 1=1 ");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_RCSW_CWSJCX a where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}


	
	@Override
	protected void setTableInfo() {
		// TODO �Զ����ɷ������
		super.setTableName("rcsw_cwsjb");
		super.setKey("id");
		super.setClass(CwsjForm.class);
		
	}

	/** 
	 * @����:(��ȡѧ����������)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-8-28 ����07:26:14
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<String[]> �������� 
	 * @throws 
	 */
	public List<String[]> getStuCwsjList(String xh) {
		// TODO �Զ����ɷ������
		String sql="select xn,(select xqmc from XQDZB b where b.xqdm=a.xq)xq,zd1,zd2,zd3 from rcsw_cwsjb a where xh=?";
		return dao.rsToVator(sql, new String[]{xh}, new String[]{"xn","xq","zd2","zd3","zd1"});
	}

	
	/**
	 * 
	 * @����:����ѧ�Ų�ѯѧ����������
	 * @���ߣ�ligl
	 * @���ڣ�2013-11-30 ����03:07:04
	 * @�޸ļ�¼: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getCwsjList(String xh) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc ");
		sql.append("from rcsw_cwsjb t1 left join xqdzb t2 ");
		sql.append("on t1.xq=t2.xqdm where t1.xh=? order by t1.xn,xq");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}	
	
}
