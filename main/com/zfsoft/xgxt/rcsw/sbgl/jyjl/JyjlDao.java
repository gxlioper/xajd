/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.jyjl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JyjlDao extends SuperDAOImpl<JyjlModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JyjlModel.class);
		super.setKey("id");
		super.setTableName("xg_rcsw_sbgl_jyjl");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JyjlModel t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append(" select t1.*,t2.mc flmc,t3.bh,t3.mc sbmc,t4.szbm bmdm,");
		sql.append(" t4.xm,t5.bmmc,t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" case when t1.ghsj is null then '未归还' else '已归还' end ghzt ");
		sql.append(" from xg_rcsw_sbgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join yhb t4 on t1.zgh = t4.yhm ");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.szbm = t5.bmdm ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" ) where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JyjlModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getModel(java.lang.Object)
	 */
	
	@Override
	public JyjlModel getModel(JyjlModel t) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.*,t2.mc flmc,t3.mc sbmc,t4.xm,t5.bmmc,t6.xm jbrxm,t7.xm ghjbrxm, ");
		sql.append(" case when t1.ghsj is null then '未归还' else '已归还' end ghzt ");
		sql.append(" from xg_rcsw_sbgl_jyjl t1 ");
		sql.append(" left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm ");
		sql.append(" left join xg_rcsw_sbgl_sbxx t3 on t1.sbdm = t3.dm ");
		sql.append(" left join yhb t4 on t1.zgh = t4.yhm ");
		sql.append(" left join ZXBZ_XXBMDM t5 on t4.szbm = t5.bmdm ");
		sql.append(" left join yhb t6 on t1.jbr = t6.yhm ");
		sql.append(" left join yhb t7 on t1.ghjbr = t7.yhm ");
		sql.append(" where t1.id = ? ");
		
		return super.getModel(sql.toString(), new String[]{t.getId()});
	}

	
}
