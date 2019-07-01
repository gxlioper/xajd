/**
 * @部门:学工产品事业部
 * @日期：2013-7-20 下午01:30:49 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 班风竞赛管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-4-7 上午08:48:21 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class BfjsCsszDao extends SuperDAOImpl<BfjsCsszModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(BfjsCsszModel.class);
		setTableName("xg_qzxy_bfjs_cssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BfjsCsszModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BfjsCsszModel t, User user)
			throws Exception {
		return null;
	}

	
	/**
	 * 
	 * @描述:查询参数设置信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-7 上午08:49:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * BfjsCsszModel 返回类型 
	 * @throws
	 */
	public BfjsCsszModel getModel() throws Exception{
		
		String sql = "select t1.*,t1.xn||' '||t2.xqmc zqmc from xg_qzxy_bfjs_cssz t1 left join xqdzb t2 on t1.xq=t2.xqdm where rownum=1";
		
		return getModel(sql, new String[]{});
	}
	
	
	/**
	 * 
	 * @描述:更新评奖周期
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-7 上午08:50:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param Pdzq
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updatePdzq(String[] pdzq) throws Exception{
		
		String sql = "update xg_qzxy_bfjs_cssz set xn=? , xq=?";
		
		return dao.runUpdate(sql, pdzq);
	}
	
	
	/**
	 * 
	 * @描述:更新参数设置
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-7 上午08:50:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param key
	 * @param value
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBfjsCssz(String key,String value) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_qzxy_bfjs_cssz set ");
		sql.append(key);
		sql.append("=? where rownum=1");
		
		return dao.runUpdate(sql.toString(), new String[]{value});
	}

	/**
	 * 
	 * @描述:查询配置表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-4-7 上午08:50:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param csdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsz(String csdm) {

		String sql = "select csz from xg_pjpy_new_cspzb where csdm = ? ";
		
		return dao.getOneRs(sql, new String[]{csdm}, "csz");
		
	}
	/**
	 * 
	 * @描述: 查询当前周期是否有竞赛班级
	 * @作者：xiaxia [工号：1104]
	 * @日期：2016-4-22 下午05:10:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型
	 */
	public boolean isHaveJsbj(BfjsCsszModel model){
		
		String sql = "select count(1) num from xg_bfjs_fstjjlb where xn = ? and xq=?";
		
		return Integer.valueOf(dao.getOneRs(sql, new String[]{model.getXn(),model.getXq()}, "num")) > 0;
	}
	public boolean initJsbjByBj(BfjsCsszModel model , User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_bfjs_fstjjlb(xn,xq,bjdm) ");
		sql.append("select distinct ?,?,t1.bjdm from view_njxyzybj t1 where 1=1 ");
		
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		return dao.runUpdate(sql.toString(), new String[]{model.getXn(),model.getXq()});
	}
	public boolean initMrf(BfjsCsszModel model , User user) throws Exception{
		StringBuilder sql = new StringBuilder("insert into xg_qzxy_bfjs_bjfs(bjdm,xn,xq,xmdm,fs,lrr,lrsj) ");
		sql.append(" select t1.bjdm,?,?,t2.xmdm,t2.mrfs,'auto',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss')");
		sql.append(" from xg_bfjs_fstjjlb t1 left join xg_qzxy_bfjs_jsxm t2 on t1.xn=t2.xn and t1.xq=t2.xq");
		sql.append(" where t1.xn=? and t1.xq=? and t2.xmlx='3' ");
		sql.append(" and not exists (select 1 from xg_qzxy_bfjs_bjfs t3 where t1.bjdm=t3.bjdm and t2.xmdm=t3.xmdm)");
		if ("xy".equals(user.getUserType())){
			sql.append(" and xydm='")
			   .append(user.getUserDep())
			   .append("'");
		}
		return dao.runUpdate(sql.toString(), new String[]{model.getXn(),model.getXq(),model.getXn(),model.getXq()});
	}
	
}
