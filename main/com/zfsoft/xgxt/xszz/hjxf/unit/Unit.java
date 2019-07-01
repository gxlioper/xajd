/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 下午07:02:04 
 */  
package com.zfsoft.xgxt.xszz.hjxf.unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.base.message.MessageUtil;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-3-15 下午07:02:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class Unit extends SuperDAOImpl {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	private static final String  RDZQ = MessageUtil.getText("xszz.knsrd.sqzq");
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

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
	public boolean isNotExists(UnitForm utilform){
		StringBuilder sql = new StringBuilder();
		String type = utilform.getType();
		ArrayList<String> parameter = new ArrayList<String>();
		boolean flag = true;
		//进行申请表和结果表的重复验证,qb(全部)
		if(type.equalsIgnoreCase("qb")){
			sql.append(" select sum(flag) flag from");
			sql.append(" (select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFSQB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?");
			sql.append(" union all");
			sql.append(" select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFJGB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?)");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXh());
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("sq")){
			sql.append(" select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFSQB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
			parameter.add(utilform.getXh());
		}else if(type.equalsIgnoreCase("jg")){
			sql.append(" select count(1) flag from ");
			sql.append(" XG_XSZZ_NEW_HJXFJGB");
			sql.append(" where xn = ?");
			sql.append(" and xh = ?");
			parameter.add(utilform.getXn());
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
		sql.append(" t.cynsr,");
		sql.append(" t.cyzy");
		sql.append(" from xg_xszz_new_jtcyb t");
		sql.append(" left join xszz_jtcygxb t1");
		sql.append(" on t.cygx = t1.dm where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	//获取学生基本信息
	public HashMap<String, String> getxsxx(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.xm,");
		sql.append(" t.xb,");
		sql.append(" t.xh,");
		sql.append(" t.mzmc mz,");
		sql.append(" t.sfzh,");
		sql.append(" t.xymc, ");
		sql.append(" t.zymc,");
		sql.append(" t.sjhm,");
		sql.append(" t.jtdz,");
		sql.append(" t.jtdh,");
		sql.append(" t.nj,");
		sql.append(" t.xlmc");
		sql.append(" from view_xsxxb t");
		sql.append(" where t.xh  = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	//获取审批流
	public String getJqjzsj() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select jqjzsj from XG_XSZZ_NEW_HJXFJBSZ ");
		return dao.getOneRs(sql.toString(), new String[] {}, "jqjzsj");
	}
	
	/**
	 * 
	 * @描述:获取困难生贫困等级
	 * @作者：yxy[工号：1206]
	 * @日期：2016-3-16 下午02:22:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsdj(String xh,String xn,String xq){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc from xg_xszz_new_knsjgb t ");
		sql.append(" left join ");
		sql.append(" xg_xszz_new_kndcdmb t1");
		sql.append(" on t.rddc = t1.dcdm ");
		sql.append(" where t.xh = ?  and t.xn = ?  and (t.xq = 'on' or t.xq = ?)");
		return dao.getOneRs(sql.toString(), new String[]{xh,xn,xq}, "dcmc");	
	}
	
	/**
	 * @描述:TODO(浙江大学__获取困难生贫困等级 方法)√√√√√
	 * @作者：MengWei[工号：1186]
	 * @日期：2016-7-26 下午06:03:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getKnsdjZJDX(String xh){
		StringBuffer sql = new  StringBuffer();
		sql.append(" select t1.dcmc from view_knsjgb_fqxrd t ");
		sql.append(" left join xg_xszz_new_kndcdmb t1 on t.rddc = t1.dcdm ");
		sql.append(" where t.xh = ? ");
		return dao.getOneRs(sql.toString(), new String[]{xh}, "dcmc");	
	}
	
	public List<HashMap<String, String>> getHistoryHjInfo(String xh){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.xn,t1.xqmc xq,t.sqsj,t.hjje,t.jqjzsj");
		sql.append(" from  XG_XSZZ_NEW_HJXFJGB t");
		sql.append(" left join xqdzb t1 on t.xq = t1.xqdm");
		sql.append(" where t.xh = ?");
		return dao.getListNotOut(sql.toString(), new String[]{xh});
	}
	
	//家庭情况调查表
	public HashMap<String, String> getJtqkdc(String xh){
		StringBuilder sql = new StringBuilder();
		sql.append("  select a.jtrjysr,a.jtrs from xg_xszz_new_jtqkdcb a ");
		sql.append(" where a.xh = ? ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}
	
	//空list
	public List<String> getNullList(int num){
		String xm = "0";
		List<String> nulllist = new ArrayList<String>();
		for (int i = 0; i < num; i++) {
			nulllist.add(xm);
		}
		
		return nulllist;
	}
}
