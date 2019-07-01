/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-25 ����10:12:11 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsq.js;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ճ����񡪡���ѧ�
 * @�๦������: ��ѧ���ʦ����ҳ��DAO
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-25 ����10:12:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmsqJsDao extends SuperDAOImpl<TxhdXmsqJsForm> {

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmsqJsForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}
	
	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(TxhdXmsqJsForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from view_xg_rcsw_txhd_xmsq t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setTableName("xg_rcsw_txhd_xmsq");
		super.setKey("sqid");

	}

	/** 
	 * @����:ѡ����Ŀ����ҳ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����02:57:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmsqInfoList(String xh) {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.xmdm,a.xmmc,a.hdkssj||' �� '||a.hdjssj hdsj,a.hddd, ");
		sql.append("case when sqrssx - nvl(ysqrs,0) < 0 then 0 else sqrssx - nvl(ysqrs,0) end syme, ");
		sql.append("case when exists ( select 1 from xg_rcsw_txhd_xmsq c where a.xmdm=c.xmdm and xh = ?) then '1' else '0' end sfsq ");
		sql.append("from xg_rcsw_txhd_xmwh a left join ");
		sql.append("(select xmdm,count(1) ysqrs from xg_rcsw_txhd_xmsq where shzt in (5,1) group by xmdm) b on a.xmdm=b.xmdm where a.sqkg='1' ");
		sql.append("and (sysdate between to_date(nvl(sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and to_date(nvl(sqjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')) ");
		sql.append("and a.xn||a.xq in (select dqxn||dqxq from xtszb) ");
		
		return dao.getListNotOut(sql.toString(), new String[]{xh});
		
	}

	
	/**
	 * 
	 * @����:������Ŀ�����ѯʣ������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����02:40:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xmdm
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSymeForXmdm (String xmdm ) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.sqrssx-nvl(b.count,0) syme from xg_rcsw_txhd_xmwh a left join ");
		sql.append("(select xmdm,count(1) count from xg_rcsw_txhd_xmsq where xmdm = ? and shzt in (1,5) group by xmdm ) b on a.xmdm = b.xmdm where a.xmdm = ? ");
		
		return dao.getOneRs(sql.toString(), new String[]{xmdm,xmdm}, "syme");
	}
	
	/**
	 * 
	 * @����: ������Ŀ�����ѯ����������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-28 ����01:18:29
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getSqrs(String xmdm){
		
		String sql = "select count(1) count from xg_rcsw_txhd_xmsq where xmdm = ? and shzt not in (0,3) group by xmdm ";
		
		return dao.getOneRs(sql, new String[]{xmdm}, "count");
		
	}
	
}
