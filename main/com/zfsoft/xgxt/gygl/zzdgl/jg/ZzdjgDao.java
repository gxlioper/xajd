/**
 * @部门:学工产品事业部
 * @日期：2016-3-1 上午11:54:08 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.ypzl.jg.YpzljgForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：柳俊[工号:1282]
 * @时间： 2016-3-1 上午11:54:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdjgDao extends SuperDAOImpl<ZzdjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t8.xqmc,t1.xn || t8.xqmc xnxq,");
		sql.append(" decode(t1.qrzt,'1','通过','2','不通过','未确认') qrztmc");
		sql.append(" from XG_GYGL_NEW_ZZDJGB t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ZzdjgForm.class);
		super.setKey("jgid");
		super.setTableName("XG_GYGL_NEW_ZZDJGB");
	}
	
	public boolean isHaveRecordForjg(ZzdjgForm form){
		String sql = "select count(1) num from XG_GYGL_NEW_ZZDJGB where xh = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean deleteForSq(ZzdjgForm form) throws Exception{
		String sql = "delete from XG_GYGL_NEW_ZZDJGB where xh = ?";
		return dao.runUpdate(sql, new String[]{form.getXh()});
	}
	
	public boolean delByZzdid(String zzdid) throws Exception{
		String sql = "delete from XG_GYGL_NEW_ZZDJGB where zzdid = ?";	
		return dao.runUpdate(sql, new String[]{zzdid});		
	}
	
	//获取公寓管理员所管的住宿学生
	public List<HashMap<String, String>> getPageListForQr(ZzdjgForm t, User user)
			throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		String username = user.getUserName();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t3.xm,t3.xb,t3.xymc,t3.xydm,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t8.xqmc,t1.xn || t8.xqmc xnxq, ");
		sql.append(" decode(t1.qrzt,'1','通过','2','不通过','未确认') qrztmc");
		sql.append(" from XG_GYGL_NEW_ZZDJGB t1 left join view_xsbfxx t3 on t1.xh = t3.xh ");
		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" where exists (select 1 from");
		sql.append(" (select a.xh from view_xg_gygl_new_cwxx a left join xg_gygl_new_gyfdyb b on a.lddm = b.lddm where b.yhm = '" + username +"'");
		sql.append(" and a.xh is not null) t2 where t1.xh = t2.xh) ");			
		sql.append(" ) t where 1 = 1 ");
		if("dsh".equalsIgnoreCase(t.getQrzt())){
			sql.append(" and t.qrzt is null");
		}else{
			sql.append(" and t.qrzt is not null");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	

}
