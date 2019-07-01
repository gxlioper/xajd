/**
 * @部门:学工产品事业部
 * @日期：2017年5月10日 上午8:42:24 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq.ZyfwSqForm;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 志愿服务管理模块
 * @类功能描述: 志愿服务结果Dao
 * @作者： xuwen[工号:1426]
 * @时间： 2017年5月10日 上午8:42:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwJgDao extends SuperDAOImpl<ZyfwJgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		
		super.setClass(ZyfwJgForm.class);
		super.setKey("fwid");
		super.setTableName("xg_xsxx_zyfwgl_jgb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJgForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZyfwJgForm t, User user) throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select t.* from (");
		sql.append("select t1.*,t2.xqmc,t3.xm,t3.xydm,t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.nj,t3.xb,");
		sql.append("ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz ");
		sql.append("from xg_xsxx_zyfwgl_jgb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join view_xsbfxx t3 on t1.xh=t3.xh ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:判断结果表中指定时间段内是否有重复记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月12日 上午8:39:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zyfwJgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isRepeat(ZyfwJgForm zyfwJgForm) {
		
		String id = zyfwJgForm.getFwid() == null ? "null" : zyfwJgForm.getFwid();
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) num from(select case when (fwjssj<? or fwkssj>?) then '0' else '1' end sfcd ");
		sql.append("from xg_xsxx_zyfwgl_jgb where xh=? and xn=? and xq=? and fwid <>?)where sfcd='1' ");
		String num = dao.getOneRs(sql.toString(), new String[] {zyfwJgForm.getFwkssj(),zyfwJgForm.getFwjssj(),zyfwJgForm.getXh(),
				zyfwJgForm.getXn(),zyfwJgForm.getXq(),id}, "num");
		return Integer.valueOf(num) > 0;
	}

	/** 
	 * @描述:根据学号查询该生志愿服务结果信息列表
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月12日 上午11:59:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getZyfwJgListByXh(String xh) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xqmc,ssx.shengmc||ssx.shimc||ssx.xianmc|| t1.fwdd fwddxxdz,");
		sql.append("t1.fwkssj||'-'||t1.fwjssj fwsj ");
		sql.append("from xg_xsxx_zyfwgl_jgb t1 ");
		sql.append("left join xqdzb t2 on t1.xq=t2.xqdm ");
		sql.append("left join xg_view_dmk_qx ssx on ssx.qxdm=t1.fwddssx ");
		sql.append("WHERE xh = ? ORDER BY xn desc,xq desc,fwkssj desc");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
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
	public ZyfwJgForm getModel(String fwid) throws Exception{
		
		String sql = "select t1.*,t2.xqmc from xg_xsxx_zyfwgl_jgb t1 left join xqdzb t2 on t1.xq=t2.xqdm where fwid = ?";
		return super.getModel(sql, new String[]{fwid});
	}

}
