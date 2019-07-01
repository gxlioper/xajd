/**
 * @部门:学工产品事业部
 * @日期：2016-2-29 下午01:35:35 
 */  
package com.zfsoft.xgxt.gygl.zzdgl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-29 下午01:35:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdsqDao extends SuperDAOImpl<ZzdsqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZzdsqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t8.xqmc,t1.xn || t8.xqmc xnxq,t1.shzt shztx,");
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc");
		sql.append(" from XG_GYGL_NEW_ZZDSQB t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
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
		super.setClass(ZzdsqForm.class);
		super.setKey("zzdid");
		super.setTableName("XG_GYGL_NEW_ZZDSQB");
		
	}
	
	public boolean iszhusu(ZzdsqForm form) {
		String sql = "select count(1) num from view_xg_gygl_new_cwxx where xh = ?";
		String num = dao.getOneRs(sql, new String []{form.getXh()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public boolean isHaveRecord(ZzdsqForm form){
		String sql = "select count(1) num from XG_GYGL_NEW_ZZDSQB where xh = ? ";
		String sql1 = "select count(1)numm from XG_GYGL_NEW_ZZDJGB where xh = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		String numm = dao.getOneRs(sql1, new String[]{form.getXh()}, "numm");
		if(Integer.valueOf(num)>0 || Integer.valueOf(numm)>0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean isGlyqr(ZzdsqForm form){
		String sql = "select count(1) num from XG_GYGL_NEW_ZZDJGB where zzdid = ? and qrzt is not null";
		String num = dao.getOneRs(sql, new String[]{form.getZzdid()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public String getQxmForPrint(String xh){
		String sql = "select a.ldmc || b.qsh qsxx from view_xg_gygl_new_cwxx b left join xg_gygl_new_ldxxb a on a.lddm = b.lddm where b.xh = ?";
		return dao.getOneRs(sql, new String[]{xh}, "qsxx");
	}
	
}
