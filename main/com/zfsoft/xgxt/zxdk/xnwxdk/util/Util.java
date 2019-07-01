/**
 * @部门:学工产品事业部
 * @日期：2016-2-19 上午09:56:32 
 */  
package com.zfsoft.xgxt.zxdk.xnwxdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-2-19 上午09:56:32 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class Util extends SuperDAOImpl {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List getPageList(Object t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List getPageList(Object t, User user) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2016-2-19 上午09:58:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isNotExists(UtilForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//进行申请表和结果表的重复验证,qb(全部)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjk_sqb");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjk_jgb");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?)");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjk_sqb");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" xg_zdgxh_wxjk_jgb");
			sql.append(" where xn = ?");
			sql.append(" and xq = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXq());
			parameter.add(utilform.getXh());
		}
		String num = dao.getOneRs(sql.toString(), parameter.toArray(new String[]{}),"flag");
		if (!num.equals("0")){
			flag = false;
		}
		return flag;
	}
	
	//获取家庭成员信息
	public List<HashMap<String, String>> getjtcyxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.cyxm,");
		sql.append(" t1.mc cw,");
		sql.append(" t.cyxxdw,");
		sql.append(" t.cynsr cyysr");
		sql.append(" from xg_xszz_new_jtcyb t");
		sql.append(" left join xszz_jtcygxb t1");
		sql.append(" on t.cygx = t1.dm where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	//获取学生基本信息
	public HashMap<String, String> getxsxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xm,");
		sql.append(" t.xh,");
		sql.append(" t.mzmc mz,");
		sql.append(" t.sfzh,");
		sql.append(" t.xymc, ");
		sql.append(" t.zymc,");
		sql.append(" t.sjhm,");
		sql.append(" t.jtdz,");
		sql.append(" t.dzyx,");
		sql.append(" t.jtyb");
		sql.append(" from view_xsxxb t");
		sql.append(" where t.xh  = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	
}
