/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-8 ����09:24:25 
 */
package com.zfsoft.xgxt.axcs.wpsq.js;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ���ĳ��й���ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� xiaxia [����:1104]
 * @ʱ�䣺 2014-12-8 ����09:24:25
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class WpsqJsDao extends SuperDAOImpl<WpsqJsForm> {

	/*
	 * ����: @see
	 * com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WpsqJsForm t) throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}


	@Override
	public List<HashMap<String, String>> getPageList(WpsqJsForm t, User user) throws Exception {
		// ���ɸ߼���ѯ�������������ֵ
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (select t.*,case when t2.sqkg = 1 and sysdate between to_date(nvl(t2.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t2.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then 'true' else 'false' end isopen ");
		sql.append(" from VIEW_AXCS_WPSQ t left join xg_xszz_axcsxmb t2 on t.xmdm = t2.xmdm)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * ��������Ʒ��Ϣ
	 */
	public List<HashMap<String, String>> getKsqInfoList(String xh) throws Exception {

		StringBuilder sql = new StringBuilder();

		sql.append(" select t.*,t1.mc xmlbmc from xg_xszz_axcsxmb t left join xg_xszz_axcslbb t1 on t.xmlb=t1.dm where not exists(select 1 from xg_axjz_axcswpsqb t2 where t.xmdm=t2.xmdm and t2.xh=?) ");
		sql.append(" and  t.sqkg='1' and (sysdate between to_date(nvl(sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi')) and xn='" + Base.currXn + "'");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}
	/**
	 * ��������Ʒ��Ϣ
	 */
	public List<HashMap<String, String>> getYsqInfoList(String xh) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,t1.mc xmlbmc  from xg_xszz_axcsxmb t left join xg_xszz_axcslbb t1 on t.xmlb=t1.dm where exists(select 1 from xg_axjz_axcswpsqb t2 where t.xmdm=t2.xmdm and t2.xh=?) ");
		sql.append(" and xn= '" + Base.currXn + "'");
		return dao.getListNotOut(sql.toString(), new String[] { xh });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(WpsqJsForm.class);
		super.setKey("sqid");
		super.setTableName("xg_axjz_axcswpsqb");

	}

}
