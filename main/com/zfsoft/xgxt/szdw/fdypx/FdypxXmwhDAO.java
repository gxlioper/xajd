/**
 * @部门:学工产品事业部
 * @日期：2013-7-5 下午02:38:48 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训项目维护
 * @作者： zhangjw
 * @时间： 2013-7-5 下午02:35:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmwhDAO extends SuperDAOImpl<FdypxXmwhForm>{

	/*
	      描述:辅导员培训申请列表
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmwhForm model)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append("select  xmdm,xmmc, pxdd, pxsj, sqkssj, sqjssj, sqkg, pxjj, fbsj ,fbr from xg_szdw_fdypxxm b ");
		sql.append("where (sysdate between to_date(nvl(sqkssj, '1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') and ");
		sql.append("to_date(nvl(sqjssj, '2020-01-01 00:00'), 'yyyy-mm-dd hh24:mi')+ 1) and b.sqkg = 1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述:项目维护高级查询
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmwhForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append(" select  xmdm,xmmc, pxdd, pxsj,a.bmdm,(select b.bmmc from ZXBZ_XXBMDM b where b.bmdm = a.bmdm ) zzbm, ");
		sql.append("sqkssj, sqjssj, sqkg, pxjj, fbsj ,fbr,pxxs from xg_szdw_fdypxxm a  where 1=1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("xmdm");
		super.setTableName("xg_szdw_fdypxxm");
	}

}
