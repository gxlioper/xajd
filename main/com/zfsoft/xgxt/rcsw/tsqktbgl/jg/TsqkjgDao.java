/**
 * @部门:学工产品事业部
 * @日期：2016-3-18 下午02:14:12 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.jg;

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
 * @作者：柳俊[工号:1282]
 * @时间： 2016-3-18 下午02:14:12 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqkjgDao extends SuperDAOImpl<TsqkjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsqkjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TsqkjgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from ( ");
		sql.append(" select t1.*,t2.xqmc,t3.xydm,t3.bjdm,t3.xb,t3.nj,t3.bjmc,t3.xymc,t3.xm,t3.mzmc,t3.zzmmmc,t3.jtdzxx,t3.lxdh,t3.sfzh,t3.zymc,(case when t5.xqmc is not null  then t4.xqmc || '、' || t5.xqmc else t4.xqmc end) xqxx,t4.xqmc xqmc1,t5.xqmc xqmc2,(case t1.clcj when '1' then  '院级' when '2' then '学工部' else t1.clcj end) clcjmc ");
		sql.append(" from xg_bjzyy_tsqktb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t4 on t1.xqdm1 = t4.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t5 on t1.xqdm2 = t5.xqdm ");
		sql.append(" ) a where 1=1 ");		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(TsqkjgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_bjzyy_tsqktb_jg");
	}
	
	public boolean isHaveRecordForjg(TsqkjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_bjzyy_tsqktb_jg where xh = ? and tbsj = ? ");
		if("update".equalsIgnoreCase(form.getType())){
			sql.append(" and jgid <> '" + form.getJgid() + "'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{form.getXh(),form.getTbsj()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean deleteForSq(TsqkjgForm form) throws Exception{
		String sql = "delete from xg_bjzyy_tsqktb_jg where xh = ? and tbsj = ?";
		return dao.runUpdate(sql, new String[]{form.getXh(),form.getTbsj()});	
	}
	
	public boolean delByLclyywid(String lclyywid) throws Exception{
		String sql = "delete from xg_bjzyy_tsqktb_jg where lylcywid = ?";		
		return dao.runUpdate(sql, new String[]{lclyywid});		
	}
	
	public Map<String, String> getTbjgxx(TsqkjgForm form){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t4.xqmc xqmc1,t5.xqmc xqmc2,t2.xqmc,(case t1.clcj when '1' then '院级' when '2' then '学工部' else t1.clcj end) clcjmc from xg_bjzyy_tsqktb_jg t1 ");
		sql.append(" left join xqdzb t2 on t1.xq = t2.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t4 on t1.xqdm1 = t4.xqdm ");
		sql.append(" left join xg_bjzyy_xqyb_xqfl t5 on t1.xqdm2 = t5.xqdm ");
		sql.append(" where t1.jgid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{form.getJgid()});
	}
	
}
