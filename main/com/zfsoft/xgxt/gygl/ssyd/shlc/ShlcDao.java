/**
 * @部门:学工产品事业部
 * @日期：2013-9-9 下午12:07:04 
 */  
package com.zfsoft.xgxt.gygl.ssyd.shlc;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 宿舍异动模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间  2013-09-22 16:51
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ShlcDao extends SuperDAOImpl<ShlcForm> {

	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_gygl_new_shlc");
		this.setClass(ShlcForm.class);
	}

	
	@Override
	public List<HashMap<String, String>> getPageList(ShlcForm t)
			throws Exception {
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_shlc");
		return dao.getListNotOut(sb.toString(), null);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ShlcForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	
	public HashMap<String,String> getXx(String splcid){
		String sql="select * " +
				" from XG_GYGL_NEW_SHLC where splcid = ? ";
		return dao.getMapNotOut(sql, new String[]{splcid});
	}

	public String checkSsydlx(String ssydlx) throws Exception {
		String sql="select count(*) count" +
		" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getOneRs(sql, new String[]{ssydlx}, "count") ;
	}
	
	public HashMap<String,String> getXxBySsydlx(String ssydlx){
		String sql="select * " +
				" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getMapNotOut(sql, new String[]{ssydlx});
	}

	public List<HashMap<String,String>> getShlcList(){
		String sql = "select * from XG_GYGL_NEW_SHLC";
		return dao.getListNotOut(sql, new String[]{});
	}

	public boolean delete() throws Exception{
		String sql = "delete from XG_GYGL_NEW_SHLC";
		return dao.runUpdate(sql, new String[]{});
	}
	
	public boolean insert(ShlcForm t) throws SQLException{
		List<String[]> list = t.getParamList();
		String sql = "insert into XG_GYGL_NEW_SHLC (ssydlx,splcid) values(?,?)";
		return dao.runBatchBoolean(sql, list);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2018-3-13 下午05:54:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ssydlx
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getSplcid(String ssydlx) {
		String sql="select splcid " +
		" from XG_GYGL_NEW_SHLC where ssydlx = ? ";
		return dao.getOneRs(sql, new String[]{ssydlx}, "splcid") ;
	}

}
