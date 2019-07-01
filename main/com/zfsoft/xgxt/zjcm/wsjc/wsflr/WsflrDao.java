/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午11:06:34 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.util.IPRequest;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-8 上午11:06:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsflrDao extends SuperDAOImpl<WsflrForm>{
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsflrForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WsflrForm t, User user)
			throws Exception {
		String username = user.getUserName();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from ( ");
		sql.append(" select a.xn,a.xq,sum(case when a.tjzt='1' then 1 else 0 end) ytjqs,b.wwsj,b.wwzzsj,a.dfszid, ");
		sql.append(" sum(case when a.tjzt = '0' then 1 else 0 end) wtjqs,a.ccny,substr(a.ccny, 0, 4) nd,substr(a.ccny, 6, 2) yf,b.wwsj || ' 至  ' || b.wwzzsj kfwhsj,c.xqmc ");
		sql.append(" from xg_zjcm_gygl_wsjc_wsf a left join xg_zjcm_gygl_wsjc_dfsz b on a.dfszid = b.dfszid ");
		sql.append(" left join xqdzb c on a.xq = c.xqdm ");
		sql.append(" where exists (select 1 from xg_zjcm_gygl_wsjc_pfz_pfcy d where a.pfzid = d.pfzid and d.cyzgh = '" + username + "')");
		sql.append(" group by a.dfszid,a.xn,a.xq,a.ccny,b.wwsj,b.wwzzsj,c.xqmc) t ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		sql.append(" order by t.xn desc,t.xq desc,t.wwsj desc ");
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(WsflrForm.class);
		super.setKey("wsfid");
		super.setTableName("xg_zjcm_gygl_wsjc_wsf");
	}
	
	public List<HashMap<String, String>> getPageListForLr(WsflrForm t, User user) throws Exception{
		String username = user.getUserName();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select a.*,(case when a.tjzt='1' then '已提交' else '未提交' end) tjztmc,(select count(1) from view_xg_gygl_new_cwxx d where a.ssxq = d.xqdm and a.lddm = d.lddm and a.qsh = d.qsh and d.xh is not null) rzrs,c.cws,substr(a.ccny, 0, 4) nd,substr(a.ccny, 6, 2) yf ");
		sql.append(" from xg_zjcm_gygl_wsjc_wsf a ");
		sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh = c.qsh ");
		sql.append(" where exists (select 1 from xg_zjcm_gygl_wsjc_pfz_pfcy e where a.pfzid = e.pfzid and e.cyzgh = '" + username + "')");
		sql.append(" order by a.tjzt asc,a.lddm asc,a.qsh asc ");
		sql.append(" ) where 1=1 ");
		if(t.getCcny()!= null){
			sql.append(" and ccny = '" + t.getCcny()+"' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	public boolean saveWsf(WsflrForm t, User user) throws Exception{
		String ccrq = GetTime.getTimeByFormat(DATE_FORMAT);
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjcm_gygl_wsjc_wsf set ccrq ='" + ccrq +"',ccr = '"+ user.getUserName() +"',ccrip ='"+ t.getCcrip() +"'");
		if(null != t.getFz()){
			sql.append(" ,fz = '" + t.getFz() +"'");
		}
		if(null != t.getFzbz()){
			sql.append(" ,fzbz = '" + t.getFzbz()+"'");
		}
		sql.append(" where wsfid = ?");
		return dao.runUpdate(sql.toString(), new String[]{t.getWsfid()});
	}
	
	public boolean tj(String wsfid, User user,WsflrForm t) throws Exception{
		String ccrq = GetTime.getTimeByFormat(DATE_FORMAT);
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_zjcm_gygl_wsjc_wsf set tjzt = '1',ccrq ='" + ccrq +"',ccr = '"+ user.getUserName() +"',ccrip ='"+ t.getCcrip() +"'");
		sql.append("where wsfid = ?");
		return dao.runUpdate(sql.toString(), new String[]{wsfid} );
	}
	
	public boolean checkIsCanSubmit(String wsfid) throws Exception{
		String sql = "select fz from xg_zjcm_gygl_wsjc_wsf where wsfid = ?";
		String fz =  dao.getOneRs(sql, new String[]{wsfid}, "fz");
		return (fz != null && !fz.equalsIgnoreCase(""));
	}
	
	public List<HashMap<String,String>> getCcqsList(WsflrForm t,User user) throws Exception{
		String username = user.getUserName();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select a.*,(case when a.tjzt='1' then '已提交' else '未提交' end) tjztmc,(select count(1) from view_xg_gygl_new_cwxx d where a.ssxq = d.xqdm and a.lddm = d.lddm and a.qsh = d.qsh) rzrs,c.cws,e.bmmc, ");
		sql.append(" (select b.dydj from xg_zjcm_gygl_wsjc_wsfdzb b where to_number(b.wsfsx) >= to_number(a.fz) and to_number(b.wsfxx) <= to_number(a.fz)) dj ");
		sql.append(" from xg_zjcm_gygl_wsjc_wsf a ");
		sql.append(" left join xg_gygl_new_qsxxb c on a.lddm = c.lddm and a.qsh = c.qsh ");
		sql.append(" left join zxbz_xxbmdm e on a.ssxy = e.bmdm ");
		sql.append(" where exists (select 1 from xg_zjcm_gygl_wsjc_pfz_pfcy e where a.pfzid = e.pfzid and e.cyzgh = '" + username + "')");
		sql.append(" and ccny = '"+t.getCcny()+"'");
		if(t.getTjzt().equalsIgnoreCase("1")){
			sql.append(" and tjzt = '1'");
		}else if(t.getTjzt().equalsIgnoreCase("0")){
			sql.append(" and tjzt = '0'");
		}
		sql.append("order by a.tjzt asc,a.lddm asc,a.qsh asc) ");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
}
