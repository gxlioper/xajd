/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.rcsw.sbgl.sbxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 代码维护
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SbxxDao extends SuperDAOImpl<SbxxModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(SbxxModel.class);
		super.setKey("dm");
		super.setTableName("xg_rcsw_sbgl_sbxx");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SbxxModel t)
			throws Exception {
		
		List<String> params = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.mc flmc from xg_rcsw_sbgl_sbxx t1 ");
		sql.append("left join xg_rcsw_sbgl_flxx t2 on t1.fldm = t2.dm where 1=1 ");
		
		if (!StringUtil.isNull(t.getFldm())){
			sql.append(" and t1.fldm = ? ");
			params.add(t.getFldm());
		}
		
		if (!StringUtil.isNull(t.getMc())){
			sql.append(" and t1.mc like '%'||?||'%' ");
			params.add(t.getMc());
		}
		
		if (!StringUtil.isNull(t.getBh())){
			sql.append(" and t1.bh like '%'||?||'%' ");
			params.add(t.getBh());
		}
		
		return super.getPageList(t, sql.toString(), params.toArray(new String[]{}));
	}

	
	/**按设备编号查询已经存在的数量*/
	public int getSbslByBh(String sbbh){
		
		String sql = "select count(1) count from xg_rcsw_sbgl_sbxx where bh=?";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{sbbh}, "count"));
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(SbxxModel t, User user)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_rcsw_sbgl_sbxx t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("dm=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_rcsw_sbgl_jyjl t2 where t1.dm=t2.sbdm)");
		
		return dao.runDelete(sql.toString(), values);
	}

}
