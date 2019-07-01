/**
 * @部门:学工产品事业部
 * @日期：2016-2-24 上午11:36:40 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.ypzl.sq.YpzlSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-24 上午11:36:40 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzljgDao extends SuperDAOImpl<YpzljgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzljgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzljgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t4.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,");
		sql.append(" t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t8.xqmc,t6.yjshsj,t6.ejshsj");
		if("10511".equals(Base.xxdm)){
			sql.append(",t5.lbmc ytmc");
		}		
		sql.append(" from xg_zdgxh_ypzl_jgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" left join dmk_yh t4 on t2.yhdm = t4.yhdm");
		if("10511".equals(Base.xxdm)){
			sql.append(" left join xg_zxdk_ytzjlbdmb t5 on t1.ytlb = t5.lbdm");
		}
		sql.append(" left join (select a.*, b.shsj ejshsj, b.shzt ejshzt from (select a.* from (select a.ywid, a.shsj yjshsj, a.shzt yjshzt, ");
		sql.append(" row_number() over(partition by a.ywid order by a.shsj desc) rn from xg_xtwh_shztb a where  shzt = '1') a where a.rn = 2)");
		sql.append("  a left join (select b.*, row_number() over(partition by b.ywid order by b.shsj desc) rn from xg_xtwh_shztb b ");
		sql.append(" where  shzt = '1') b on a.ywid = b.ywid and b.rn = 1) t6 on t6.ywid = t1.lylcywid");
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
		super.setClass(YpzljgForm.class);
		super.setKey("jgid");
		super.setTableName("xg_zdgxh_ypzl_jgb");		
	}
	
	
	public boolean delByLclyywid(String lclyywid){
		String sql = "delete from xg_zdgxh_ypzl_jgb where lylcywid = ?";
		try {
			return dao.runUpdate(sql, new String[]{lclyywid});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isHaveRecordForjg(YpzljgForm form){
		String sql = "select count(1) num from xg_zdgxh_ypzl_jgb where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getXn()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public boolean deleteForSq(YpzljgForm form){
		String sql = "delete from xg_zdgxh_ypzl_jgb where xn = ? and xh = ?";
		try {
			return dao.runUpdate(sql, new String[]{form.getXn(),form.getXh()});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
