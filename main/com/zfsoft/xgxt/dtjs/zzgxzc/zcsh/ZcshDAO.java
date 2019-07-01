/**
 * @部门:学工产品事业部
 * @日期：2017-2-10 下午04:56:46 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-10 下午04:56:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcshDAO extends SuperDAOImpl<ZcshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZcshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append(" select *");
		sql.append(" from (select t.*,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XB,");
		sql.append(" floor(months_between(SYSDATE, to_date(t1.csrq,'yyyy-mm-dd'))/ 12) nl,");
		sql.append(" case t1.xz when '3' then '大学专科' else '大学本科' end xlmc,");
		sql.append(" t1.sfzh,");
		sql.append(" t1.sjhm,");
		sql.append(" t1.mzmc,");
		sql.append(" t1.XM,");
		sql.append(" t1.csrq,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t2.jc,");
		sql.append(" t3.dzbmc,");
		sql.append(" t5.guid shid,");
		sql.append(" t5.shzt shztx,");
		sql.append(" t5.gwid,");
		sql.append(" t5.shr,");
		sql.append(" t5.shyj,");
		sql.append(" t7.mc || '[' ||");
		sql.append(" decode(t5.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,");
		sql.append(" t7.gwz,");
		sql.append(" row_number() over(partition by t.sqid order by t5.shsj desc) rn");
		sql.append(" from xg_dtjs_zzgxzc_zzgxzcsqb t");
		sql.append(" left join VIEW_XSBFXX t1");
		sql.append(" on t.xh = t1.XH");
		sql.append(" left join zzmmdmb t2");
		sql.append(" on t1.ZZMM = t2.zzmmdm");
		sql.append(" left join xg_dtjs_zzgxzc_dzbdmb t3");
		sql.append(" on t.szdzb = t3.dzbdm");
		sql.append(" left join xg_xtwh_shztb t5");
		sql.append(" on t.sqid = t5.ywid");
		sql.append(" left join xg_xtwh_spgwyh t6");
		sql.append(" on  t5.gwid = t6.spgw");
		sql.append(" left join xg_xtwh_spgw t7 on t5.gwid = t7.id ");
		sql.append(" where t6.spyh ='" + user.getUserName() + "'");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t5.shzt<>0 and  t5.shzt<>4)");
		} else {
			sql.append(" and (t5.shzt=0  or t5.shzt = 4 )");
		}
		sql.append(" ) t");
		sql.append(" where 1 = 1 and  rn = 1");
		sql.append(" ");
		sql.append(searchTjByUser);
	    sql.append(searchTj);
	    sql.append(shgwzByUser);
	    return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		this.setKey("sqid");
		this.setTableName("XG_DTJS_ZZGXZC_ZZGXZCSQB");
		this.setClass(ZcshForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存到结果表
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 上午11:57:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zcsh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveZcJg(ZcshForm zcsh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_dtjs_zzgxzc_zzgxzcjgb");
		sql.append(" (jgid,");
		sql.append(" xh,");
		sql.append(" szdzb,");
		sql.append(" sfsn,");
		sql.append(" jsdzz,");
		sql.append(" sqdw,");
		sql.append(" dfjzrq,");
		sql.append(" sfkjhyzm,");
		sql.append(" jsxbh,");
		sql.append(" sjly,");
		sql.append(" lclyid,");
		sql.append(" sqr,");
		sql.append(" sqsj) ");
		sql.append(" values(?,?,?,?,?,?,?,(SELECT sfkjhyzm FROM xg_dtjs_zzgxzc_zzgxzcsqb WHERE sqid = ?),?,?,?,?,?)");
		sql.append(" ");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{zcsh.getSqid(),zcsh.getXh(),zcsh.getSzdzb(),zcsh.getSfsn(),
			zcsh.getJsdzz(),zcsh.getSqdw(),zcsh.getDfjzrq(),zcsh.getSqid(),zcsh.getJsxbh(),"1",zcsh.getSqid(),zcsh.getSqr(),zcsh.getSqsj()});
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJg(String jgid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_DTJS_ZZGXZC_ZZGXZCJGB where jgid = ?");
		return dao.runUpdate(sql.toString(),new String[]{jgid});
	}
	
	/**
	 *
	 * @描述: 是否存在结果表中
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:23:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotExistInjgb(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from XG_DTJS_ZZGXZC_ZZGXZCJGB where xh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{xh}, "num");
		return "0".equals(num) ? true : false;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:10:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delJgByXh(String xh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_DTJS_ZZGXZC_ZZGXZCJGB where xh = ?");
		return dao.runUpdateNotCommit(sql.toString(),new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-13 下午02:46:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotJsxInJgb(String jsxbh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select count(1) num from xg_dtjs_zzgxzc_zzgxzcjgb t where t.jsxbh = ?");
		String num = dao.getOneRs(sql.toString(), new String[]{jsxbh}, "num");
		return "0".equals(num) ? true :false;
	}

	/** 
	 * @描述:根据审批流程id，获取审批流程岗位
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年4月14日 上午10:48:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getSplcgwList(String splcid) {
		String sql="select * from xg_xtwh_spbz where splc=? order by xh ";
		return  dao.getListNotOut(sql, new String[]{splcid});
	}
}
