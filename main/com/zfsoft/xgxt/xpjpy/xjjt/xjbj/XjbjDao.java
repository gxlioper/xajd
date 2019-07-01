/**
 * @部门:学工产品事业部
 * @日期：2018-1-15 上午09:39:56 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.xjbj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2018-1-15 上午09:39:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XjbjDao  extends SuperDAOImpl<XjbjForm> {
	@Override
	protected void setTableInfo() {
		super.setTableName("XG_ZJDX_PJPY_XJBJB");
		super.setKey("guid");
		super.setClass(XjbjForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XjbjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * 获奖名单结果查询
	 */
	public List<HashMap<String, String>> getPageList(XjbjForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select t.*,a.bjmc,a.nj,b.zymc,b.zydm,c.bmdm as xydm,c.bmmc as xymc from XG_ZJDX_PJPY_XJBJB t  ");
		sql.append(" left join BKS_BJDM a  on t.bjdm=a.bjdm " );
		sql.append(" left join BKS_ZYDM b on a.zydm=b.zydm " );
		sql.append(" left join ZXBZ_XXBMDM c on b.bmdm=c.bmdm " );
		sql.append(") t1 where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForAddSave(XjbjForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from ( select a.*,b.bjmc from XG_ZJDX_PJPY_XJBJB a left join BKS_BJDM b  "); 
		sql.append("on a.bjdm=b.bjdm) t where  xn = ? and bjmc = ? ");
		String[] inputV = new String[] {model.getXn(), model.getBjmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForUpdate(XjbjForm model) {

		StringBuilder sql = new StringBuilder(
				"select count(1) num from ( select a.*,b.bjmc from XG_ZJDX_PJPY_XJBJB a" +
				" left join BKS_BJDM b on a.bjdm=b.bjdm)" +
				"  where xn=?  and bjmc=?  and guid<>?");
		String[] inputV = {model.getXn(),model.getBjmc(),model.getGuid()};
		
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjdm(String bjmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select bjdm  from  BKS_BJDM t where bjmc = ?  "); 
		String rs = dao.getOneRs(sql.toString(), new String[]{bjmc}, "bjdm");
		return rs;
	}
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：linguoxia[工号：1553]
	 * @日期：2018-1-15 下午04:35:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getBjmc(String bjdm) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.bjmc  from  BKS_BJDM t where bjdm = ?  "); 
		String rs = dao.getOneRs(sql.toString(), new String[]{bjdm}, "bjmc");
		return rs;
	}
	/**
	 * @throws Exception  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-1-16 上午11:45:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjmc
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getBjxx(String bjdm){
		StringBuilder sql = new StringBuilder();
		
		sql.append("select t.nj, t.bjdm,t.bjmc,t2.zymc,t3.bmmc as xymc  from  BKS_BJDM t " +
				"left join BKS_ZYDM t2 on t.zydm=t2.zydm " +
				"left join ZXBZ_XXBMDM t3 on t2.bmdm=t3.bmdm  " +
				"where bjdm = ?  "); 
		
		String[] input = {bjdm};
		List<HashMap<String, String>> rs = dao.getListNotOut(sql.toString(), input);
		return rs;
	}



}
