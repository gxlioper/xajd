/**
 * @部门:学工产品事业部
 * @日期：2017年5月4日 下午2:22:10 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务申请Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月4日 下午2:22:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwSqDao extends SuperDAOImpl<ZyfwSqForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		super.setClass(ZyfwSqForm.class);
		super.setKey("fwid");
		super.setTableName("xg_xsxx_zyfwgl_sqb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwSqForm t) throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwSqForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xqmc,t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t3.xb,");
		sql.append("decode(t1.shzt,'0','未提交','1','通过','2','不通过','3','退回','5','审核中',t1.shzt) shztmc,");
		sql.append("ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append("from xg_xsxx_zyfwgl_sqb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join view_xsbfxx t3 on t1.xh=t3.xh ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:判断当前时间是否有申请记录（包含结果表）
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午4:01:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwSqForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwSqForm zyfwSqForm) {
		
		String id = zyfwSqForm.getFwid() == null ? "null" : zyfwSqForm.getFwid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when (fwjssj<? or fwkssj>?) then '0' else '1' end sfcd from ");
		sql.append(" (select xh,xn,xq,fwid,fwkssj,fwjssj from xg_xsxx_zyfwgl_sqb union select xh,xn,xq,fwid,fwkssj,fwjssj from xg_xsxx_zyfwgl_jgb) ");
		sql.append("  where xh=? and xn=? and xq=? and fwid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {zyfwSqForm.getFwkssj(),zyfwSqForm.getFwjssj(),zyfwSqForm.getXh(),
				zyfwSqForm.getXn(),zyfwSqForm.getXq(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @描述:获取基础设置中设置的审核流程
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月5日 下午4:35:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getShlcID() {

		StringBuffer sql = new StringBuffer();
		sql.append(" select splc from xg_xsxx_zyfwgl_jcszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "splc");
	}
	
	/**
	 * @描述:重写：查询一条申请详细信息
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月9日 上午11:52:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fwid
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws Exception  
	 */
	public ZyfwSqForm getModel(String fwid) throws Exception{
		
		String sql = "select t1.*,t2.xqmc from xg_xsxx_zyfwgl_sqb t1 left join xqdzb t2 on t1.xq=t2.xqdm where fwid = ?";
		return super.getModel(sql, new String[]{fwid});
	}


}
