/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-9 ����07:05:29 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsfcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-9 ����07:05:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsfcxDao extends SuperDAOImpl<WsfcxForm>{

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsfcxForm t)
			throws Exception {
		// TODO �Զ����ɷ������
		return null;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsfcxForm t, User user)
			throws Exception {
		String username = user.getUserName();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select a.*,(select count(1) from view_xg_gygl_new_cwxx d where a.ssxq = d.xqdm and a.lddm = d.lddm and a.qsh = d.qsh and d.xh is not null) rzrs,c.cws,substr(a.ccny, 0, 4) nd,substr(a.ccny, 6, 2) yf,f.bmmc,g.xqmc, ");
		sql.append(" (select b.dydj from xg_zjcm_gygl_wsjc_wsfdzb b where to_number(b.wsfsx) >= to_number(a.fz) and to_number(b.wsfxx) <= to_number(a.fz)) dj,");
		sql.append(" (select pfzmc from xg_zjcm_gygl_wsjc_pfz h where a.pfzid=h.pfzid) pfzmc");
		sql.append(" from xg_zjcm_gygl_wsjc_wsf a ");
		sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh = c.qsh ");
		sql.append(" left join zxbz_xxbmdm f on a.ssxy = f.bmdm");
		sql.append(" left join xqdzb g on a.xq = g.xqdm ");
		if(!user.getUserName().equals("zf01")){
			sql.append(" where exists (select 1 from xg_zjcm_gygl_wsjc_pfz_pfcy e where a.pfzid = e.pfzid and e.cyzgh = '" + username + "')");
		}
		sql.append(" ) t ");
		sql.append(" where 1=1 and t.tjzt = '1'");
		sql.append(searchTj);
		sql.append(" group by t.ssxy,t.lddm,t.qsh,t.wsfid,t.ssxq,t.ldmc,t.qsmc,t.pfzid,t.dfszid,t.ccny,t.ccr,t.ccrq,t.ccrip,t.fz,t.fzbz,t.xn,t.xq,t.tjzt,t.nj,t.rzrs,t.cws,t.bmmc,t.xqmc,t.nd,t.yf,t.dj,t.pfzmc ");
		sql.append(" order by t.ssxy asc ");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(WsfcxForm.class);
		super.setKey("wsfid");
		super.setTableName("xg_zjcm_gygl_wsjc_wsf");
	}
	
	public boolean cxtj(String wsfid) throws Exception{
		String sql = "update xg_zjcm_gygl_wsjc_wsf set tjzt = '0' where wsfid = ?";
		return dao.runUpdate(sql, new String []{wsfid});
	}
	
	public boolean updateWsfcx(WsfcxForm f) throws Exception{
		String sql = "update xg_zjcm_gygl_wsjc_wsf set fz = ?,fzbz = ? where wsfid = ?";
		return dao.runUpdate(sql, new String[]{f.getFz(),f.getFzbz(),f.getWsfid()});
	}
	
	//��ȡ���
	public String getCh(WsfcxForm f){
		String sql = "select ch from xg_gygl_new_qsxxb where lddm = ? and qsh = ?";
		return dao.getOneRs(sql, new String[]{f.getLddm(),f.getQsh()}, "ch");
	}
	
	//��ѯ��������Ϣ
	public Map<String, String> getWsfcx(WsfcxForm f){
		String sql = "select a.*,(select count(1) from view_xg_gygl_new_cwxx d where a.ssxq = d.xqdm and a.lddm = d.lddm and a.qsh = d.qsh and d.xh is not null) rzrs,b.ch,b.cws,c.pfzmc,f.bmmc xymc from xg_zjcm_gygl_wsjc_wsf a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh left join xg_zjcm_gygl_wsjc_pfz c on a.pfzid = c.pfzid left join zxbz_xxbmdm f on a.ssxy = f.bmdm  where a.wsfid = ?";
		return dao.getMapNotOut(sql, new String []{f.getWsfid()});
	}
}
