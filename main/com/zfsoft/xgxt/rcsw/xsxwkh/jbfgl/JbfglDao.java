/**
 * @部门:学工产品事业部
 * @日期：2016-8-2 下午04:09:07 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 基本分管理 
 * @作者： caopei[工号:1352]
 * @时间： 2016-8-2 下午04:09:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JbfglDao extends SuperDAOImpl<JbfglForm>{


	@Override
	public List<HashMap<String, String>> getPageList(JbfglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(JbfglForm t, User user)throws Exception {
		// TODO 自动生成方法存根		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");

			StringBuilder sql = new StringBuilder();
			sql.append(" select * ");
			sql.append(" from (select a.*, ");
			sql.append(" b.xm, ");
			sql.append(" decode(a.bzrcpdj,'优秀','优秀(45)','良好','良好(43)','合格','合格(40)',a.bzrcpdj) bzrdj, ");
			sql.append(" decode(a.xscpdj,'优秀','优秀(45)','良好','良好(43)','合格','合格(40)',a.xscpdj) xsdj ,");
			sql.append(" b.xymc,b.zymc,b.zzmmmc,b.sjhm, ");
			sql.append(" b.bjmc, ");
			sql.append(" b.xb, ");
			sql.append(" b.nj, ");
			sql.append(" b.zydm,");
			sql.append(" b.bjdm,");
			sql.append(" b.xydm, ");	
			sql.append(" cast(bzrcpfz as int)+cast(xscpfz as int ) as zf  ");				
			sql.append(" from xg_xsxwkh_jbfb a "); 
			sql.append(" left join view_xsbfxx b  "); 
			sql.append("on a.xh = b.xh) t "); 
			sql.append(" where 1 = 1"); 
			sql.append(searchTjByUser);
			sql.append(searchTj);
			return getPageList(t, sql.toString(), inputV);
	}
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_xsxwkh_jbfb");
		super.setKey("jbfid");
		super.setClass(JbfglForm.class);
	}
	public JbfglForm getModel() throws Exception{
		String sql = "select * from xg_xsxwkh_jbfb ";
		return super.getModel(sql, new String[]{});
	}

	
	//检查有无重复(这里是要求每年只有一个学生的一条数据)
public String checkExistForSave(JbfglForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_xsxwkh_jbfb  ");
		sql.append(" where xh = ?  and xn = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getXn()}, "num");
		return num;
	}

	
	
}
