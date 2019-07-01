/**
 * @部门:学工产品事业部
 * @日期：2015-8-18 下午04:49:54 
 */  
package com.zfsoft.xgxt.rcsw.hdkhgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xsgzgl.gygl.xyzsgl.jg.XyzsglForm;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-8-18 下午04:49:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HdkhglDAO extends SuperDAOImpl<HdkhglForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdkhglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HdkhglForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.xh,");
		sql.append(" t.xmmc,");
		sql.append(" t.hddd,");
		sql.append(" t.hdkssj || ' 至 ' || t.hdjssj hdsj,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t.guid hdjgid,");
		sql.append(" t1.sfcj,");
		sql.append(" t1.qqyy,");
		sql.append(" t1.jlygx,");
		sql.append(" t1.id,");
		sql.append(" t2.xm,");
		sql.append(" t2.xb,");
		sql.append(" t2.bjdm,");
		sql.append(" t2.bjmc,");
		sql.append(" t2.nj,");
		sql.append(" t2.zydm,");
		sql.append(" t2.zymc,");
		sql.append(" t2.xydm,");
		sql.append(" t2.xymc");
		sql.append(" from xg_rcsw_txhd_jgb t");
		sql.append(" left join XG_RCSW_TXHD_HDKHB t1");
		sql.append(" on t.guid = t1.hdjgid");
		sql.append(" and t.xh = t1.xh");
		sql.append(" left join view_xsjbxx t2");
		sql.append("  on t.xh = t2.xh");
		sql.append(" order by t.xmmc,t.xn desc,t.xq desc");
		sql.append(" )t where 1= 1  ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(HdkhglForm.class);
		super.setKey("id");
		super.setTableName("XG_RCSW_TXHD_HDKHB");
	}
	
	public HashMap<String, String> getXsHdKhxx(HdkhglForm hdgl){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xh,");
		sql.append(" t.xmmc,");
		sql.append(" t.hddd,");
		sql.append(" t.guid hdjgid,");
		sql.append(" t.hdkssj || ' 至 ' || t.hdjssj hdsj,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.sfcj,");
		sql.append(" t1.qqyy,");
		sql.append(" t1.jlygx,");
		sql.append(" t1.id");
		sql.append(" from xg_rcsw_txhd_jgb t");
		sql.append(" left join XG_RCSW_TXHD_HDKHB t1");
		sql.append(" on t.guid = t1.hdjgid");
		sql.append(" and t.xh = t1.xh");
		sql.append(" where  t.guid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{hdgl.getHdjgid()});
	}
	
}
