/**
 * @部门:学工产品事业部
 * @日期：2014-7-8 下午07:36:23 
 */  
package com.zfsoft.xgxt.xsxx.bysxxgl.cssz;

import java.util.HashMap;

import xgxt.DAO.DAO;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2014-7-8 下午07:36:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BysXxCsSzDao extends DAO{
	
	/**
	 * 
	 * @描述:获取参数设置
	 * @作者：夏夏[工号：1104]
	 * @日期：2014-7-8 下午08:04:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCssz()throws Exception{
		String sql = "select a.bynd,a.kgzt,a.shlid from XG_BYSXX_XXXGCSSZB a";
		String[] inputValue = new String[] { };
		return getMapNotOut(sql, inputValue);
	}
	
	public boolean csSzAdd(BysXxCsSzForm model) throws Exception {
		String sql = " insert into XG_BYSXX_XXXGCSSZB(bynd,kgzt,shlid) values(?,?,?) ";
		return runUpdate(sql, new String[] { model.getBynd(),model.getKgzt(), model.getShlid()});
	}
	
	public boolean csSzDel(BysXxCsSzForm model) throws Exception {
		String sql = " delete from XG_BYSXX_XXXGCSSZB ";
		return runUpdate(sql, new String[] {});
	}
	
	/**
	 * 查询使用中的审核流
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> splCx() throws Exception{
		String sql=" select * from (select shlid,rownum r," +
				"(select count(*) ssjg from xg_bysxx_xxxgsqb  " +
				" where shjg <> '1') shjg " +
				" from XG_BYSXX_XXXGCSSZB) a where a.r=1 ";
		return getMap(sql, new String[]{}, new String[]{"shlid","shjg"});
	}


}
