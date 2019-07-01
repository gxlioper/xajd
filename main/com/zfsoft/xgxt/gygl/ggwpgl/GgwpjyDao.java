/**
 * @部门:学工产品事业部
 * @日期：2016-4-19 上午11:49:26 
 */  
package com.zfsoft.xgxt.gygl.ggwpgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.sbgl.jyjl.JyjlModel;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-4-19 上午11:49:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GgwpjyDao extends SuperDAOImpl<GgwpjyForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GgwpjyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(GgwpjyForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuilder sql = new StringBuilder();		
		sql.append("select * from (");
		sql.append(" select t1.*,t2.mc flmc,t3.bh,t3.mc sbmc,t4.xm,t4.xb,t4.nj,t4.xydm,t4.xymc,t4.zydm,t4.zymc,t4.bjdm,t4.bjmc, ");
		sql.append(" t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" (case t1.ghzt when '0' then '未归还' when '1' then '已归还' else ' ' end) ghztmc ");
		sql.append(" from xg_gygl_ggwpgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join view_xsbfxx t4 on t1.xh = t4.xh ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);		
		return super.getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(GgwpjyForm.class);
		super.setKey("id");
		super.setTableName("xg_gygl_ggwpgl_jyjl");
	}
	
	//是否归还
	public boolean sfgh(GgwpjyForm t){
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from xg_gygl_ggwpgl_jyjl where xh = ? and sbdm = ? and ghzt = ?");
		if("update".equalsIgnoreCase(t.getType())){
			sql.append(" and id <> '"+ t.getId()+"'");
		}
		String num = dao.getOneRs(sql.toString(), new String[]{t.getXh(),t.getSbdm(),"0"}, "num");
		return Integer.valueOf(num)<=0;
	}
	
	@Override
	public GgwpjyForm getModel(GgwpjyForm t) throws Exception {		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.mc flmc,t3.mc sbmc,t4.xm,t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" (case t1.ghzt when '0' then '未归还' when '1' then '已归还' else ' ' end) ghztmc ");
		sql.append(" from xg_gygl_ggwpgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join view_xsbfxx t4 on t1.xh = t4.xh ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" where t1.id = ? ");
		
		return super.getModel(sql.toString(), new String[]{t.getId()});
	}
	
}
