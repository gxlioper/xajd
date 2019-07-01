/**
 * @部门:学工产品事业部
 * @日期：2016-5-17 上午09:36:24 
 */  
package com.zfsoft.xgxt.xszz.xszzbjpy.pycy;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 班级评议小组成员
 * @作者： 沈晓波[工号：1123]
 * @时间： 2016-5-17 上午09:36:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class PycyDao extends SuperDAOImpl<PycyForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PycyForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(PycyForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputValue = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from ( ");
		sql.append(" select a.xh,a.xm,a.SJHM,a.guid,a.sfxsdb,a.tjzt,a.tjztmc,a.sfxsdbmc,b.*,'' bz, ");
		sql.append(" case when a.ldmc is not null then a.ssmc else '' end ssmc from ( ");
		sql.append(" select a.xh,a.xm,a.bjdm,a.SJHM,b.tjzt,decode(b.tjzt,'1','已提交','未提交') tjztmc, ");
		sql.append(" b.guid,b.sfxsdb,decode(b.sfxsdb,'1','是','否') sfxsdbmc,c.ldmc,c.qsh,c.cwh, ");
		sql.append(" (c.ldmc || '' || c.qsh || '室' || c.cwh || '号床') ssmc ");
		sql.append(" from xg_xszz_new_knsrd_bjpyxz b left join view_xsbfxx a on a.xh=b.xh ");
		sql.append(" left join view_xg_gygl_new_cwxx c on a.XH = c.xh) a ");
		sql.append(" left join view_njxyzybj_all b on a.bjdm=b.bjdm ");
		sql.append(" ) t1 where 1=1 ");
		sql.append(searchTj);
		sql.append(searchTjByUser);
		
		return getPageList(t, sql.toString(), inputValue);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	
	

}
