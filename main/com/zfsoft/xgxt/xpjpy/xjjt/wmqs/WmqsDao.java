/**
 * @部门:学工产品事业部
 * @日期：2018-1-19 下午02:02:56 
 */  
package com.zfsoft.xgxt.xpjpy.xjjt.wmqs;

import java.sql.SQLException;
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
 * @时间： 2018-1-19 下午02:02:56 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WmqsDao  extends SuperDAOImpl<WmqsForm>{
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zjdx_pjpy_wmqsb");
		super.setKey("guid");
		super.setClass(WmqsForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(WmqsForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t left join DM_ZJU_XQ a on  t.xqdm=a.dm");
		sql.append(" where 1=1 ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-19 下午05:46:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForAddSave(WmqsForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from ( SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t " +
				"left join DM_ZJU_XQ a " +
				"on  t.xqdm=a.dm where xn=? and xqmc=? and ldmc=? and qsh=?)"); 
		String[] inputV = new String[] {model.getXn(),model.getXqmc().trim(), model.getLdmc().trim(),model.getQsh()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}
	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-19 下午05:46:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForUpdate(WmqsForm model) {
		StringBuilder sql = new StringBuilder(
				"select count(1) num from ( SELECT t.*,a.xqmc FROM xg_zjdx_pjpy_wmqsb t " +
				" left join DM_ZJU_XQ a on  t.xqdm=a.dm )" +
				"  where xn=? and xqmc=? and ldmc=? and qsh=?  and guid<>?");
		String[] inputV = {model.getXn(),model.getXqmc().trim(),model.getLdmc().trim(),model.getQsh().trim(),model.getGuid()};
		
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/**
	 * @throws SQLException  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-22 上午09:21:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXqmcList() throws SQLException {
		String sql = "select distinct dm,xqmc from dm_zju_xq order by dm";
		return dao.getList(sql, new String[] {}, new String[] { "dm", "xqmc" });
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-22 上午10:22:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getLdSum(WmqsForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(0) num from  XG_GYGL_NEW_LDXXB where ldmc=? "); 
		String[] inputV = new String[] {model.getLdmc().trim()};
		String num = dao.getOneRs(sql.toString(), inputV, "num");
		return num;
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：lgx[工号：1553]
	 * @日期：2018-1-22 下午02:28:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqmc
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXqdm(String xqmc) {
		StringBuilder sql = new StringBuilder();
		sql.append("select dm  from  dm_zju_xq where xqmc=?"); 
		String[] inputV = new String[] {xqmc.trim()};
		String xqdm = dao.getOneRs(sql.toString(), inputV, "dm");
		return xqdm;
	}

}
