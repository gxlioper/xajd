/**
 * @部门:学工产品事业部
 * @日期：2014-6-24 上午09:39:48 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsq.xs;

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
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-6-25 下午13:08:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TxhdXssqDao extends SuperDAOImpl<TxhdXssqForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXssqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	/**
	 * 获取数据
	 */
	public List<HashMap<String, String>> getXmsqPageListXs(TxhdXssqForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,b.shzt,b.sqid,b.splc,a.sqrssx -nvl(c.count,0) syme,a.hdkssj||' 至 '||a.hdjssj hdsj,decode(b.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','未申请') shztmc from xg_rcsw_txhd_xmwh a ")
		.append("left join (select * from xg_rcsw_txhd_xmsq where xh = '").append(user.getUserName()).append("') b")
		.append("    on a.xmdm = b.xmdm ")
		.append(" left join (select xmdm,count(1) count from xg_rcsw_txhd_xmsq where shzt in (1,5) group by xmdm ) c on a.xmdm=c.xmdm ")
		.append(" where not exists (select 1")
		.append(" from xg_rcsw_txhd_xmsq t2")
		.append(" where t2.xmdm = a.xmdm")
		.append(" and t2.xh = '").append(user.getUserName()).append("'")
		.append(" and t2.shzt not in ('0', '3'))")
		.append(" and a.xq = '").append(Base.currXq).append("'")
		.append(" and a.xn = '").append(Base.currXn).append("' ")
		.append(" and a.sqkg = '1'")
		.append(" and (sysdate between to_date(nvl(a.sqkssj,'1990-01-01 00:00'), 'yyyy-mm-dd hh24:mi') ")
		.append(" and to_date(nvl(a.sqjssj,'2020-01-01 00:00'),'yyyy-mm-dd hh24:mi'))");
		
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:已申请项目
	 * @作者：夏夏[工号：1104]
	 */
	public List<HashMap<String, String>> getXmsqPageListXsYsq(TxhdXssqForm model,
			User user) throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select a.sqid,a.sqsj,a.splc,a.xq,a.xn,a.shzt,a.xh,a.xmdm,b.hdkssj,b.xmmc,b.hdjssj,b.sqrssx,b.hddd,decode(a.shzt,'0','未提交','1','通过','2','不通过','3','已退回','5','审核中','未申请') shztmc from xg_rcsw_txhd_xmsq a left join xg_rcsw_txhd_xmwh b on a.xmdm=b.xmdm where a.xh='")
		.append(user.getUserName()).append("'")
		.append(" and shzt not in ('3' , '0') ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(TxhdXssqForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());

		StringBuffer sql = new StringBuffer();
        sql.append("select a.*,b.xm,b.xb,b.xymc,b.bjmc from XG_RCSW_TXHD_JGB a left join(select xh,xm,xb,xymc,bjmc from view_xsbfxx) b on b.xh=a.xh ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return this.getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:剩余人数
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-26 下午13:08:17 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param qjsqid
	 * @return boolean 返回类型
	 * @throws
	 */
	public HashMap<String, String> getCount(String xmdm,String xq,String xn ) {
		StringBuffer sb1 = new StringBuffer();
		sb1.append("select count(*) sqrs from xg_rcsw_txhd_xmsq where xmdm = ? and xq=? and xn=?");
		HashMap<String, String> map=dao.getMapNotOut(sb1.toString(), new String[] {xmdm,xq,xn});
		
		return map;
	}
	/**
	 * @描述:TODO获取审批流程ID
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-6-26 下午13:08:17 
	 * @return String 返回类型
	 */
	public String getSplcID(String xmdm) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select b.shlc splc from  xg_rcsw_txhd_xmwh b where b.xmdm=? ");
		return dao.getOneRs(sql.toString(), new String[] {xmdm}, "splc");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setKey("sqid");
		this.setTableName("xg_rcsw_txhd_xmsq");
		this.setClass(TxhdXssqForm.class);
		
	}

	

}
