/**
 * @部门:学工产品事业部
 * @日期：2016-3-9 下午07:05:29 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsfcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-9 下午07:05:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfcxDao extends SuperDAOImpl<WsfcxForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsfcxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
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
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
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
	
	//获取层号
	public String getCh(WsfcxForm f){
		String sql = "select ch from xg_gygl_new_qsxxb where lddm = ? and qsh = ?";
		return dao.getOneRs(sql, new String[]{f.getLddm(),f.getQsh()}, "ch");
	}
	
	//查询卫生分信息
	public Map<String, String> getWsfcx(WsfcxForm f){
		String sql = "select a.*,(select count(1) from view_xg_gygl_new_cwxx d where a.ssxq = d.xqdm and a.lddm = d.lddm and a.qsh = d.qsh and d.xh is not null) rzrs,b.ch,b.cws,c.pfzmc,f.bmmc xymc from xg_zjcm_gygl_wsjc_wsf a left join xg_gygl_new_qsxxb b on a.lddm = b.lddm and a.qsh = b.qsh left join xg_zjcm_gygl_wsjc_pfz c on a.pfzid = c.pfzid left join zxbz_xxbmdm f on a.ssxy = f.bmdm  where a.wsfid = ?";
		return dao.getMapNotOut(sql, new String []{f.getWsfid()});
	}
}
