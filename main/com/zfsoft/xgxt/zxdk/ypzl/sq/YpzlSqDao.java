/**
 * @部门:学工产品事业部
 * @日期：2016-2-22 上午10:29:42 
 */  
package com.zfsoft.xgxt.zxdk.ypzl.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-2-22 上午10:29:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YpzlSqDao extends SuperDAOImpl<YpzlSqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlSqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YpzlSqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t4.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,nvl(t3.xm,t6.xm) sqrmc,t8.xqmc,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,");
		if("10511".equals(Base.xxdm)){
			sql.append("t5.lbmc ytmc,");
		}		
		sql.append(" decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc");
		sql.append(" from xg_zdgxh_ypzl_sqb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join yhb t6 on t1.sqr=t6.yhm left join view_xsbfxx t3 on t1.sqr=t3.xh");
		sql.append(" left join xqdzb t8 on t1.xq = t8.xqdm");
		sql.append(" left join dmk_yh t4 on t2.yhdm = t4.yhdm");
		if("10511".equals(Base.xxdm)){
			sql.append(" left join xg_zxdk_ytzjlbdmb t5 on t1.ytlb = t5.lbdm");
		}		
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
		super.setClass(YpzlSqForm.class);
		super.setKey("sqid");
		super.setTableName("xg_zdgxh_ypzl_sqb");
		
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ?";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
	public String getShlcID() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_zdgxh_ypzl_cssz ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	public boolean isHaveRecord(YpzlSqForm form){
		String sql = "select count(1) num from xg_zdgxh_ypzl_sqb where xh = ? and xn = ?";
		String sql1 = "select count(1)numm from xg_zdgxh_ypzl_jgb where xh = ? and xn = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh(),form.getXn()}, "num");
		String numm = dao.getOneRs(sql1, new String[]{form.getXh(),form.getXn()}, "numm");
		if(Integer.valueOf(num)>0 || Integer.valueOf(numm)>0){
			return true;
		}else{
			return false;
		}
	}
	
	public List<HashMap<String, String>> getYtlbList(){
		String sql = "select * from xg_zxdk_ytzjlbdmb";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public String getYtmc(String ytdm){
		String sql = "select lbmc from xg_zxdk_ytzjlbdmb where lbdm = ?";
		return dao.getOneRs(sql, new String[]{ytdm}, "lbmc");
	}
	

	
}
